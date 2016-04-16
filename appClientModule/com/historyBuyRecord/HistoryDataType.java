package com.historyBuyRecord;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryDataType {
	private String buyID;// 购买id
	private String buyTime;// 购买时间
	private String timeCodeVal;
	private String buyName;// 用户名
	private String userWeb;// 用户id
	private String buyCode;// 购买产品网址id
	private int buyNum;// 购买数量
	private int goodsPeriod;// 商品期数
	private String goodsName;// 商品名称

	public String getBuyID() {
		return buyID;
	}

	public void setBuyID(String buyID) {
		this.buyID = buyID;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public String getTimeCodeVal() {
		return timeCodeVal;
	}

	public void setTimeCodeVal(String timeCodeVal) {
		this.timeCodeVal = timeCodeVal;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getUserWeb() {
		return userWeb;
	}

	public void setUserWeb(String userWeb) {
		this.userWeb = userWeb;
	}

	public String getBuyCode() {
		return buyCode;
	}

	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getGoodsPeriod() {
		return goodsPeriod;
	}

	public void setGoodsPeriod(int goodsPeriod) {
		this.goodsPeriod = goodsPeriod;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		SimpleDateFormat dd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date d = null;
		try {
			d = dd.parse(buyTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dataType [buyID=" + buyID + ", buyTime=" + d.toLocaleString()+"."+d.getTime() + ", timeCodeVal=" + timeCodeVal + ", buyName="
				+ buyName + ", userWeb=" + userWeb + ", buyCode=" + buyCode + ", buyNum=" + buyNum + ", goodsPeriod="
				+ goodsPeriod + ", goodsName=" + goodsName + "]";
	}
	
}
