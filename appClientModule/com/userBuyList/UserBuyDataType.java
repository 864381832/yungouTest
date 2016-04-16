package com.userBuyList;

public class UserBuyDataType {
	private String userName;// 用户名
	private String userPhoto;// 用户头像
	private String userWeb;// 用户id
	private int buyNum;// 购买数量
	private String buyIP;// 用户ip
	private String buyIPAddr;// ip地址
	private String buyTime;// 购买时间
	private int buyDevice;// 参与人次
	private String buyID;// 购买id

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserWeb() {
		return userWeb;
	}

	public void setUserWeb(String userWeb) {
		this.userWeb = userWeb;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public String getBuyIP() {
		return buyIP;
	}

	public void setBuyIP(String buyIP) {
		this.buyIP = buyIP;
	}

	public String getBuyIPAddr() {
		return buyIPAddr;
	}

	public void setBuyIPAddr(String buyIPAddr) {
		this.buyIPAddr = buyIPAddr;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public int getBuyDevice() {
		return buyDevice;
	}

	public void setBuyDevice(int buyDevice) {
		this.buyDevice = buyDevice;
	}

	public String getBuyID() {
		return buyID;
	}

	public void setBuyID(String buyID) {
		this.buyID = buyID;
	}

	@Override
	public String toString() {
		return "UserBuyDataType [userName=" + userName + ", userPhoto=" + userPhoto + ", userWeb=" + userWeb
				+ ", buyNum=" + buyNum + ", buyIP=" + buyIP + ", buyIPAddr=" + buyIPAddr + ", buyTime=" + buyTime
				+ ", buyDevice=" + buyDevice + ", buyID=" + buyID + "]";
	}

}
