package com.zzy.goods.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.goods.service.GoodsService;

public class GoodsSerachAction extends ActionSupport{
	
	private GoodsService goodsService;
	
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	private String goodsLikeName;
	
	public void setGoodsLikeName(String goodsLikeName) {
		this.goodsLikeName = goodsLikeName;
	}
	
	public String getGoodsByName(){
		
		JsonBean<List<GoodsPageBeanApp>> jsonBean = new JsonBean<>();
		
		jsonBean = goodsService.findGoodsByNameLike(goodsLikeName);
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}
	
}
