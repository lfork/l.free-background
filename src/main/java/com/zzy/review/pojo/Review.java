package com.zzy.review.pojo;

import com.zzy.goods.pojo.Goods;
import com.zzy.user.pojo.User;

public class Review {
	
	private Integer rId;
	private String rContent;
	private String rMakeDate;
	private Goods goods;
	private User user;
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrMakeDate() {
		return rMakeDate;
	}
	public void setrMakeDate(String rMakeDate) {
		this.rMakeDate = rMakeDate;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Review(String rContent, String rMakeDate, Goods goods, User user) {
		super();
		this.rContent = rContent;
		this.rMakeDate = rMakeDate;
		this.goods = goods;
		this.user = user;
	}
	public Review() {
		super();
	}
	@Override
	public String toString() {
		return "Review [rId=" + rId + ", rContent=" + rContent + ", rMakeDate=" + rMakeDate + ", goods=" + goods
				+ ", user=" + user + "]";
	}
	public Review(Integer rId, String rContent, String rMakeDate, Goods goods, User user) {
		super();
		this.rId = rId;
		this.rContent = rContent;
		this.rMakeDate = rMakeDate;
		this.goods = goods;
		this.user = user;
	}
	
	
}
