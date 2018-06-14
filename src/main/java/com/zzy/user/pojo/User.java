package com.zzy.user.pojo;

import com.zzy.school.pojo.School;

/**
 * @author 周志远
 * 时间:2018-3-20
 * 说明：描述用户的 Pojo 类， 对应数据库中的 user 表
 */

public class User {
	
	
	private Integer userId;     //用户的ID
	
	
	private String userName;    //用户姓名
	
	
	private String userPassword;  //用户密码
	
	
	private String userEmail;     //用户邮箱地址
	
	
	private String userPhone;      //用户电话号码
	
	
	private String userAddress;    //用户的住址
	
	private School userSchool; //用戶的学校

	public School getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(School userSchool) {
		this.userSchool = userSchool;
	}

	private String userImagePath;   //用户头像文件存放路径
	 
	
	private String userDesc;          //用户描述
	
	
	private String userMakeDate;        //用户修改或者注册时间

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getUserImagePath() {
		return userImagePath;
	}


	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}


	public String getUserDesc() {
		return userDesc;
	}


	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}


	public String getUserMakeDate() {
		return userMakeDate;
	}


	public void setUserMakeDate(String userMakeDate) {
		this.userMakeDate = userMakeDate;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userPhone=" + userPhone + ", userAddress=" + userAddress + ", userSchool=" + userSchool.toString()
				+ ", userImagePath=" + userImagePath + ", userDesc=" + userDesc + ", userMakeDate=" + userMakeDate
				+ "]";
	}
}
