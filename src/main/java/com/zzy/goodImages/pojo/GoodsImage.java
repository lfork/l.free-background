package com.zzy.goodImages.pojo;

import java.util.Date;

import com.zzy.goods.pojo.Goods;

public class GoodsImage {
	
	private Integer gImageId;
	private String gImageFile;
	private String gImageDesc;
	private Goods goods;
	
	public Integer getgImageId() {
		return gImageId;
	}
	public void setgImageId(Integer gImageId) {
		this.gImageId = gImageId;
	}
	public String getgImageFile() {
		return gImageFile;
	}
	public void setgImageFile(String gImageFile) {
		this.gImageFile = gImageFile;
	}
	public String getgImageDesc() {
		return gImageDesc;
	}
	public void setgImageDesc(String gImageDesc) {
		this.gImageDesc = gImageDesc;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
