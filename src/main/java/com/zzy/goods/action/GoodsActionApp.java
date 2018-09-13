package com.zzy.goods.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.AppBeanUtils.PageApp;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.goods.pojo.Goods;
import com.zzy.goods.service.GoodsServiceApp;

public class GoodsActionApp implements ModelDriven<Goods>{
	
	private Goods goods;
	private GoodsServiceApp goodsServiceApp;
	
	private String cursor;
	private String csId;
	
	public void setCsId(String csId) {
		this.csId = csId;
	}
	
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public void setGoodsServiceApp(GoodsServiceApp goodsServiceApp) {
		this.goodsServiceApp = goodsServiceApp;
	}
	
	public String getGoodsPageApp(){
		
		PageApp<GoodsPageBeanApp> pageApp = new PageApp<>();
		
		pageApp = goodsServiceApp.getGoodsPage(csId , cursor);
		
		JsonBean<List<GoodsPageBeanApp>> jsonBean = new JsonBean<>();
		
		if(pageApp.getList() == null ||pageApp.getList().size() == 0){
			jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			jsonBean.setMsg("数据加载失败！");
			
		}else{
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("数据加载成功！");
			
		}
		
		jsonBean.setData(pageApp.getList());
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}


	@Override
	public Goods getModel() {
		// TODO Auto-generated method stub
		return goods;
	}

}
