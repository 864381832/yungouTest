package com.historyBuyRecord;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.util.DateUtil;
import com.util.HttpGetUtil;

public class GetHistoryBuyRecord {

	private static int index = 0;// 计数
	private static BigInteger sumTotal = new BigInteger("0");// 时间总和

	/*
	 * 获取幸运云购码
	 */
	public static String GetBuyGoodsCode(String buyTime) {
		String[] buyTimeDate = DateUtil.getBuyTime(buyTime);
		int FIdx = 1;
		while (index < 100) {
			String buyRecord = GetBuyRecord(FIdx, buyTimeDate[0], buyTimeDate[1]);
			sysData(buyRecord, buyTime);
			FIdx += 80;
		}
		String string = sumTotal.remainder(new BigInteger("5188")).add(new BigInteger("10000001")).toString();
		System.out.println("中奖码" + string);
		return string;
	}

	public static String GetBuyRecord(int FIdx, String BTime, String ETime) {
		StringBuffer getUrl = new StringBuffer();
		getUrl.append("http://api.1yyg.com/JPData?action=getHistoryBuyRecord&FIdx=").append(FIdx);
		getUrl.append("&EIdx=").append(FIdx + 79);
		getUrl.append("&BTime=").append(BTime);
		getUrl.append("&ETime=").append(ETime);
		getUrl.append("&isCount=0");
		return HttpGetUtil.getHttpData(getUrl.toString(), "http://www.1yyg.com/HistoryBuyRecords.html");
		// sysData(HttpGetUtil.getHttpData(getUrl.toString(),
		// "http://www.1yyg.com/HistoryBuyRecords.html"));
	}

	/*
	 * 输出获取到的信息
	 */
	public static void sysData(String content, String buyTime) {
		System.out.println("查吗"+content);
		String data = content.split(",'data':", 2)[1];
		data = data.substring(0, data.length() - 2);
		Gson gson = new Gson();

		List<Map<String, Object>> dataList = gson.fromJson(data, new TypeToken<List<Map<String, Object>>>() {
		}.getType());
		for (Map<String, Object> maps : dataList) {
			if (maps.get("buyTime").equals(buyTime) || index > 0) {
				if (index >= 100) {
					break;
				}
				sumTotal = sumTotal
						.add(new BigInteger(DateUtil.getDateFormatStringToInt((String) maps.get("buyTime"))));
				++index;
			}
			// System.out.println("时间：" + maps.get("buyTime") + " 物品：" +
			// maps.get("buyName"));
		}
	}

}
