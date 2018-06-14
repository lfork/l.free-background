package com.zzy.goods.service;

import java.util.ArrayList;
import java.util.List;

import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.AppBeanUtils.PageApp;
import com.zzy.goods.dao.GoodsDaoApp;
import com.zzy.goods.pojo.Goods;
import com.zzy.user.pojo.User;

public class GoodsServiceApp {
	
	private GoodsDaoApp goodsDaoApp;
		
	public void setGoodsDaoApp(GoodsDaoApp goodsDaoApp) {
		this.goodsDaoApp = goodsDaoApp;
	}

	public PageApp<GoodsPageBeanApp> getGoodsPage(String csId, String cursor) {
		
		PageApp<GoodsPageBeanApp> pageApp = new PageApp<>();
	
		List<Object[]> list = goodsDaoApp.getGoodsPage(csId, cursor);
		
		List<GoodsPageBeanApp> list2 = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			GoodsPageBeanApp temp = new GoodsPageBeanApp((Integer) obj[0],(String) obj[1],(double) obj[2], (String) obj[3], (String) obj[4], (Integer) obj[5], (String) obj[6]);
			list2.add(temp);
		}
		
		pageApp.setList(list2);
		
		return pageApp;
	}
}
