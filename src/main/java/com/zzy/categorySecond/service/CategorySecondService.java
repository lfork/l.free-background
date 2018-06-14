package com.zzy.categorySecond.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;

import com.zzy.BaseUtils.DateConvert;
import com.zzy.BaseUtils.Page;
import com.zzy.categorySecond.dao.CategorySecondDao;
import com.zzy.categorySecond.pojo.CategorySecond;
import com.zzy.goods.pojo.Goods;
import com.zzy.user.pojo.User;

public class CategorySecondService {
	
	private CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public List<CategorySecond> findCsList() {
		
		List<CategorySecond> categorySeconds = categorySecondDao.findCsList();
		Hibernate.initialize(categorySeconds);
		return categorySeconds;
	}

	public Page<Goods> getPageCsList(String csId, String pageNo) {
		
		Page<Goods> page = new Page<>(Integer.parseInt(pageNo));
		
		int totalItemNumber = categorySecondDao.findCountCsList(Integer.parseInt(csId));
		
		if(Integer.parseInt(pageNo) <= 0){
			pageNo = "1";
		}
		
		if(Integer.parseInt(pageNo) > (int)Math.ceil(totalItemNumber / 20.0)){
			pageNo = (int)Math.ceil(totalItemNumber / 20.0) + "";
		}
		
		List<Object[]> list = categorySecondDao.findCsListByPage(Integer.parseInt(csId), (Integer.parseInt(pageNo) - 1) * page.getPageSize(), page.getPageSize());
		List<Goods> list2 = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = list.get(i);
			Goods temp = new Goods();
			temp.setgId((Integer) obj[0]);
			temp.setgName((String) obj[1]);
			temp.setgSellPrice((double) obj[2]);
			temp.setgCoverImage((String) obj[3]);
			temp.setgMakeDate((String) obj[4]);
			User user = new User();
			user.setUserId(Integer.parseInt(list.get(i)[5].toString()));
			temp.setUser(user);
			list2.add(temp);
		}
		
		page.setList(list2);
		page.setTotalItemNumber(totalItemNumber);
		
		return page;
	}

	public CategorySecond findCsByCsId(int parseInt) {
		return categorySecondDao.findCsByCsId(parseInt);
	}


	
}
