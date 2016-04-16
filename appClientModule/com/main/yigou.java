package com.main;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;    

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.historyBuyRecord.GetHistoryBuyRecord;
import com.userBuyList.GetUserBuyList;
import com.userBuyList.UserBuyDataType;
import com.util.HttpGetUtil;
import com.util.HttpPostUtil;

public class yigou {
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		// GetHistoryBuyRecord.GetBuyRecord(1, "2015-12-28%2014:29:00",
		// "2015-12-28%2014:29:59");
		// GetUserBuyList.GetUserBuyList(2600186,1);
		// GetUserBuyList.GetCodeNum(2653442);
		// GetUserBuyList.GetGoodsPeriodInfo(22504, 25242);
		// GetUserBuyList.GetUserBuyGoodsCodeInfo(2653442);
		// GetHistoryBuyRecord.GetBuyGoodsCode("2015-12-28 19:07:25.951");
		TestUrl();
		System.out.println("执行耗时 : " + (System.currentTimeMillis() - beginTime) / 1000f + " 秒 ");
	}

	public static String TestUrl() {
		String url = null;
		String refererUrl = null;
		String content = null;

		// url =
		// "http://www.1yy1.com/products/getGoodsPeriodPage.action?id=30&codeID=0&pageNo=1&pageSize=5&IsCount=1";
		// content = HttpGetUtil.getHttpData(url, refererUrl);
		// System.out.println("内容：" + content);
		// url =
		// "http://localhost:8080/login/login.html?userName=admin&pwd=admin" +
		// URLEncoder.encode(
		// "';INSERT INTO `cardpassword` (`randomNo`, `cardPwd`, `money`,
		// `date`) VALUES ('445838161407', 'E45D860DF3F3E8DFFDC0A2A21C00C25D',
		// '100.00', '2016-03-19 19:25:18');");
		// content = HttpGetUtil.getHttpData(url, refererUrl);
		// System.out.println("内容：" + content);

		// url =
		// "http://localhost:8080/userAjax_login?userid=admin&loginInfo=admin" +
		// URLEncoder.encode(
		// "';INSERT INTO `cardpassword` (`randomNo`, `cardPwd`, `money`,`date`)
		// VALUES ('445838161407', 'E45D860DF3F3E8DFFDC0A2A21C00C25D','100.00',
		// '2016-03-19 19:25:18');");
		// url = "http://localhost:8080/YunGouAssist/userAjax_login";
		url = "http://localhost:8080/login/login.html";
		content = HttpPostUtil.getHttpData(url, refererUrl);
//		 content = HttpGetUtil.getHttpData(url, refererUrl);
		System.out.println("内容：" + content);

		// for (int i = 0; i < 200; i++) {
		// try {
		// Thread.sleep(1000);
		// url = "http://www.1yy1.com/products/checkUserLimitBuy.action?id=15";
		// content = HttpGetUtil.getHttpData(url, refererUrl);
		// System.out.print("内容：" + content);
		// // 添加购物车
		// url = "http://www.1yy1.com/list/isStatus.action?id=15";
		// content = HttpGetUtil.getHttpData(url, refererUrl);
		// System.out.println("内容：" + content);
		// // 获取购物车数量
		// // url = "http://www.1yy1.com/mycart/buyProductClick.html";
		// // content = HttpGetUtil.getHttpData(url, refererUrl);
		// // System.out.println("内容：" + content);
		//
		// url = "http://www.1yy1.com/mycart/getMyProductCart.html";
		// content = HttpGetUtil.getHttpData(url, refererUrl);
		// System.out.println("内容：" + content);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }

		// 查询计算详情
		// url =
		// "http://www.1yy1.com/lotteryDetail/getLotteryRecords.action?id=1097118";
		// content = HttpGetUtil.getHttpData(url, refererUrl);
		// for (int i = 0; i < 2; i++) {
		// int ii = i;
		// new Thread(new Runnable() {
		// public void run() {
		// String url =
		// "http://www.1yy1.com/products/ajaxPage.action?id=1096984&pageNo=" +
		// ii;
		// String content = HttpGetUtil.getHttpData(url, refererUrl);
		// sysData2(content);
		// }
		// }).start();
		// }
		return content;

	}

	public static void sysData2(String content) {
		List<Map<String, String>> d3 = new Gson().fromJson(content, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		BigInteger sumTotal = new BigInteger("0");// 时间总和
		List<String> dList = new ArrayList<String>();
		for (Map<String, String> maps : d3) {
			// System.out.println(maps);
			String url = "http://www.1yy1.com/lotteryDetail/getUserBuyCodeByBuyid.action?spellbuyrecordId="
					+ maps.get("buyId") + "&id=1096984";
			String content1 = HttpGetUtil.getHttpData(url, null);
			List<String> d1 = new Gson().fromJson(content1, new TypeToken<List<String>>() {
			}.getType());
			for (String s : d1) {
				dList.add(s);
			}
		}
		System.out.println("云购码：" + dList.indexOf("10000072"));
	}

	public static void sysData(String content) {
		List<Map<String, String>> d3 = new Gson().fromJson(content, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		BigInteger sumTotal = new BigInteger("0");// 时间总和
		for (Map<String, String> maps : d3) {
			if (maps.get("dateSum") != null) {
				sumTotal = sumTotal.add(new BigInteger(maps.get("dateSum")));
			}
		}
		String string = sumTotal.remainder(new BigInteger("98")).add(new BigInteger("10000001")).toString();
		System.out.println(string);
	}

}