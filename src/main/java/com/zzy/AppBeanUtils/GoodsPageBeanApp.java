package com.zzy.AppBeanUtils;

public class GoodsPageBeanApp {
	
	public GoodsPageBeanApp(Integer gId, String gName, double gSellPrice, String gCoverImage, String gMakeDate,
			Integer userId, String userSchool) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gSellPrice = gSellPrice;
		this.gCoverImage = gCoverImage;
		this.gMakeDate = gMakeDate;
		this.userId = userId;
		this.userSchool = userSchool;
	}
	private Integer gId;
	private String gName;
	private double gSellPrice;
	private String gCoverImage;
	private String gMakeDate;
	private Integer userId;
	private String userSchool;
	
	public String getUserSchool() {
		return userSchool;
	}
	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool;
	}
	public Integer getgId() {
		return gId;
	}
	public void setgId(Integer gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public double getgSellPrice() {
		return gSellPrice;
	}
	public void setgSellPrice(double gSellPrice) {
		this.gSellPrice = gSellPrice;
	}
	public String getgCoverImage() {
		return gCoverImage;
	}
	public void setgCoverImage(String gCoverImage) {
		this.gCoverImage = gCoverImage;
	}
	public String getgMakeDate() {
		return gMakeDate;
	}
	public void setgMakeDate(String gMakeDate) {
		this.gMakeDate = gMakeDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public GoodsPageBeanApp(Integer gId, String gName, double gSellPrice, String gCoverImage, String gMakeDate,
			Integer userId) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gSellPrice = gSellPrice;
		this.gCoverImage = gCoverImage;
		this.gMakeDate = gMakeDate;
		this.userId = userId;
	}
	public GoodsPageBeanApp() {
		super();
	}
}
