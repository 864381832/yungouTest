package com.main;

import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.historyBuyRecord.GetHistoryBuyRecord;
import com.userBuyList.GetUserBuyList;
import com.userBuyList.UserBuyDataType;
import com.util.HttpGetUtil;

public class Main {
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		// GetHistoryBuyRecord.GetBuyRecord(1, "2015-12-28%2014:29:00",
		// "2015-12-28%2014:29:59");
		// GetUserBuyList.GetUserBuyList(2600186,1);
		// GetUserBuyList.GetCodeNum(2653442);
		// GetUserBuyList.GetGoodsPeriodInfo(22504, 25242);
		// GetUserBuyList.GetUserBuyGoodsCodeInfo(2653442);
		// GetHistoryBuyRecord.GetBuyGoodsCode("2015-12-28 19:07:25.951");
		// TestUrl();
		count();
		System.out.println("执行耗时 : " + (System.currentTimeMillis() - beginTime) / 1000f + " 秒 ");
	}

	public static void count() {
		double Max = 0;
		double MaxX = 0;
		double MaxY = 0;
		for(double x = 1000; x < 4600; x = x + 1){
			for(double y = 1; y < 5110 - x; y = y + 1){
				double value = y / 5110 * (4600 - y - x * 0.5) + x / 5110 * (4600 - x * 2 - y)
						- (5110 - x - y) / 5100 * y;
				if (value > Max) {
					Max = value;
					MaxX = x;
					MaxY = y;
					System.out.println("最大：Max:" + Max + "   MaxX:" + MaxX + "   MaxY:" + MaxY);
				}
			}
		}
		System.out.println("Max:" + Max + "   MaxX:" + MaxX + "   MaxY:" + MaxY);
	}

	public static String TestUrl() {
		// 获取云购期数 goodsID商品id codeID商品网址id
		// 返回值{"codePeriod":24698,"codeID":2654546,"codeState":1}
		// codePeriod商品id codeID商品网址id codeState揭晓状态：1进行中、2等待揭晓、3已经揭晓
		String url = "http://api.1yyg.com/JPData?action=getGoodsPeriodPage&goodsID=22504&codeID=2654407&FIdx=1&EIdx=5&IsCount=0";
		String refererUrl = "http://www.1yyg.com/lottery/2654407.html";

		// 查询商品期数是否存在 goodsID商品id period期数
		// 返回值{"code":0,"codeID":2654637,"codeState":3}
		// url =
		// "http://api.1yyg.com/JPData?action=getGoodsPeriodInfo&goodsID=22504&period=24707";
		// refererUrl = "http://www.1yyg.com/lottery/2654407.html";

		// 获取本期中奖云购码 codeID商品网址id
		// 返回值{"buyTime":"2015-12-27 19:44:45.645","rnoNum":"10004140,10000061"}
		// buyTime云购时间 rnoNum云购码
		// url =
		// "http://api.1yyg.com/JPData?action=getUserBuyGoodsCodeInfo&codeID=2654407";
		// refererUrl = "http://www.1yyg.com/lottery/2654407.html";

		// 获取商品最后购买时间100条购买记录
		// 返回值{'code':0,'recordEnd1':[{"buyTime":"","buyName":"","userWeb":"","buyID":,"buyCode":2663993,"buyNum":1,"goodsPeriod":15,"goodsName":""}],
		// 'recordEnd2':[{"buyTime":"","timeCodeVal":"","buyName":"","userWeb":"","buyID":318702471,"buyCode":2654407,"buyNum":2764,"goodsPeriod":24683,"goodsName":""}],
		// 'recordEnd3':[{"buyTime":"","buyName":"","userWeb":"","buyID":,"buyCode":2608228,"buyNum":32,"goodsPeriod":545,"goodsName":""}
		// recordEnd1未知码 recordEnd2最后前95条数据 recordEnd3最后前5条数据
		// url =
		// "http://api.1yyg.com/JPData?action=getLotteryRecords&codeId=2665165";
		// refererUrl = "http://www.1yyg.com/lottery/2665165.html";

		// 获取云够码
		// 返回值{'code':0,'data':[{'rnoNum':10003203}]}
		// url =
		// "http://api.1yyg.com/JPData?action=GetUserBuyCodeByBuyid&codeid=2654407&buyid=318702471";
		// refererUrl = "http://www.1yyg.com/lottery/2654407.html";

		// 获取参与记录
		// 返回值{"Code":0,"Count":55,"Data":{"Tables":{"BuyList":{"Rows":[{"userName":"","userPhoto":"","userWeb":"","buyNum":"","buyIP":"","buyIPAddr":"","buyTime":"","buyDevice":"0","buyID":"318702471"}
		// url =
		// "http://api.1yyg.com/JPData?action=GetUserBuyListByCodeEnd&codeID=2654407&FIdx=1&EIdx=10&isCount=0";
		// refererUrl = "http://www.1yyg.com/lottery/2654407.html";

		// 获取本期中奖详情
		// 返回值{"code":0,"codePeriod":25043,"codeRNO":10003451,"codeRTime":"","buyTime":"","price":"","buyCount":3488,"userName":"","userNC":"","ipAddr":"","goodsName":"","goodsPic":"","userPhoto":"","userWeb":"","codeType":0})
		// codeRTime揭晓时间 buyTime云购时间 price价格 buyCount购买数量
		// url =
		// "http://api.1yyg.com/JPData?action=GetBarcodernoInfo&codeID=2665135";
		// refererUrl = "http://www.1yyg.com/";

		// 获取最新购买记录
		// 返回值{'code':0,'listItems':[{'userName':'','goodsID':21877,'','goodsPic':'','buyID':319911394,'userWeb':'','userPhoto':''}]})
		url = "http://api.1yyg.com/JPData?action=GetUserBuyNewList&buyID=319815943";
		refererUrl = "http://www.1yyg.com/";

		String content = HttpGetUtil.getHttpData(url, refererUrl);
		System.out.println("内容：" + content);

		// String url2 =
		// "http://api.1yyg.com/JPData?action=GetUserBuyListByCodeEnd&codeID=2654407&FIdx=21&EIdx=30&isCount=0";
		// String refererUrl2 = "http://www.1yyg.com/lottery/2654407.html";
		// // http://www.1yyg.com/lottery/2654407.html
		//
		// String content2 = HttpGetUtil.getHttpData(url2, refererUrl2);
		// System.out.println("内容：" + content2);

		// sysData(content);
		return content;
	}

	public static void sysData(String content) {
		String data = content.split("Rows\":", 2)[1];
		data = data.substring(0, data.length() - 5);
		Gson gson = new Gson();
		List<UserBuyDataType> d3 = gson.fromJson(data, new TypeToken<List<UserBuyDataType>>() {
		}.getType());
		for (UserBuyDataType maps : d3) {
			System.out.println(maps);
		}
	}

}