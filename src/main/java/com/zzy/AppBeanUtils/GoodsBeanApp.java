package com.zzy.AppBeanUtils;

import java.util.ArrayList;
import java.util.List;

import com.zzy.review.pojo.Review;
import com.zzy.user.pojo.User;

public class GoodsBeanApp {
	
	private Integer gId;
	private String gName;
	private double gSellPrice;
	private double gBuyPrice;
	private String desc;
	private String gMakeDate;
	private Integer userId;
	private String username;
	private String userImage;
	private String userSchool;
	private String[] images;
	
	private List<Review> reviews = new ArrayList<>();
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
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
	public double getgBuyPrice() {
		return gBuyPrice;
	}
	public void setgBuyPrice(double gBuyPrice) {
		this.gBuyPrice = gBuyPrice;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	
	public GoodsBeanApp() {
		super();
	}
	public GoodsBeanApp(Integer gId, String gName, double gSellPrice, double gBuyPrice, String desc, String gMakeDate,
			Integer userId, String username, String userImage, String userSchool, String[] images) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gSellPrice = gSellPrice;
		this.gBuyPrice = gBuyPrice;
		this.desc = desc;
		this.gMakeDate = gMakeDate;
		this.userId = userId;
		this.username = username;
		this.userImage = userImage;
		this.userSchool = userSchool;
		this.images = images;
	}
	
}
