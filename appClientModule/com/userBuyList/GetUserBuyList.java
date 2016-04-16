package com.userBuyList;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.historyBuyRecord.GetHistoryBuyRecord;
import com.util.HttpGetUtil;

public class GetUserBuyList {
	public static Integer index = 0;
	private static String codeRNO = null;
	private static boolean isFind = true;

	/*
	 * 查询商品期数是否存在 goodsID商品id period期数
	 */
	public static void GetGoodsPeriodInfo(int goodsID, int period) {
		StringBuffer getUrl = new StringBuffer();
		getUrl.append("http://api.1yyg.com/JPData?action=getGoodsPeriodInfo&goodsID=").append(goodsID);
		getUrl.append("&period=").append(period);
		String refererUrl = "http://www.1yyg.com/";
		// 返回值{"code":0,"codeID":2654637,"codeState":3}
		String content = HttpGetUtil.getHttpData(getUrl.toString(), refererUrl);
		Gson gson = new Gson();
		Map<String, String> d3 = gson.fromJson(content.substring(1, content.length() - 1),
				new TypeToken<Map<String, String>>() {
				}.getType());
		GetCodeNum(Integer.parseInt(d3.get("codeID")));
	}

	/*
	 * 获取中奖位置
	 */
	public static void GetCodeNum(int codeID) {
		String content = GetUserBuyList.GetUserBuyList(codeID, 1);
		int userBuyListCount = getUserBuyListCount(content);
		for (int i = 11; i < userBuyListCount; i += 10) {
			if (isFind) {
				GetUserBuyList(codeID, i);
			}
		}
	}

	/*
	 * 获取参与记录
	 */
	public static String GetUserBuyList(int codeID, int FIdx) {
		StringBuffer getUrl = new StringBuffer();
		getUrl.append("http://api.1yyg.com/JPData?action=GetUserBuyListByCodeEnd&codeID=").append(codeID);
		getUrl.append("&FIdx=").append(FIdx);
		getUrl.append("&EIdx=").append(FIdx + 9);
		getUrl.append("&isCount=0");
		String refererUrl = "http://www.1yyg.com/lottery/" + codeID + ".html";
		String content = HttpGetUtil.getHttpData(getUrl.toString(), refererUrl);
		// System.out.println(content);
		sysData(content, codeID, FIdx);
		return content;
	}

	/*
	 * 输出获取到的信息
	 */
	public static void sysData(String content, int codeID, int FIdx) {
		String data = content.split("Rows\":", 2)[1];
		data = data.substring(0, data.length() - 5);
		Gson gson = new Gson();
		List<Map<String, Object>> dList = gson.fromJson(data, new TypeToken<List<Map<String, Object>>>() {
		}.getType());
		if (FIdx == 1) {
			codeRNO = GetHistoryBuyRecord.GetBuyGoodsCode((String) dList.get(0).get("buyTime"));
		}
		for (Map<String, Object> maps : dList) {
			// System.out.println(maps);
			if (isFind) {
				GetUserBuyCode(codeID, (String) maps.get("buyID"));
			}
		}
	}

	/*
	 * 获取总页数
	 */
	public static int getUserBuyListCount(String content) {
		String data = content.split("Count\":", 2)[1];
		data = data.split(",\"Data", 2)[0];
		return Integer.parseInt(data);
	}

	/*
	 * 获取用户云够码
	 */
	public static void GetUserBuyCode(int codeID, String buyIP) {
		StringBuffer getUrl = new StringBuffer();
		getUrl.append("http://api.1yyg.com/JPData?action=GetUserBuyCodeByBuyid&codeid=").append(codeID);
		getUrl.append("&buyid=").append(buyIP);
		// http://api.1yyg.com/JPData?action=GetUserBuyCodeByBuyid&codeid=2549588&buyid=315549442
		// 返回值{'code':0,'data':[{'rnoNum':10003203}]}
		String refererUrl = "http://www.1yyg.com/lottery/" + codeID + ".html";
		sysBuyCode(HttpGetUtil.getHttpData(getUrl.toString(), refererUrl));
	}

	/*
	 * 输出云够码
	 */
	public static void sysBuyCode(String content) {
		String data = content.split(",'data':", 2)[1];
		data = data.substring(0, data.length() - 2);
		Gson gson = new Gson();
		List<Map<String, String>> dList = gson.fromJson(data, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		for (Map<String, String> maps : dList) {
			// System.out.println(maps.get("rnoNum"));
			if (maps.get("rnoNum").equals(codeRNO)) {
				System.out.println("中奖位：" + (5188 - index));
				isFind = false;
				break;
			}
			index++;
		}
	}

}
