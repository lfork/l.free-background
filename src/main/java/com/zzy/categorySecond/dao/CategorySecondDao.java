package com.zzy.categorySecond.dao;

import java.util.List;

import org.hibernate.Hibernate;

import com.zzy.HibernateUtil.BaseDao;
import com.zzy.categorySecond.pojo.CategorySecond;
import com.zzy.goods.pojo.Goods;

public class CategorySecondDao extends BaseDao{

	public List<CategorySecond> findCsList() {
		String hql = "from CategorySecond cs";
		List<CategorySecond> categorySeconds = getSession().createQuery(hql).list();
		System.out.println("------------------------------------------------------------------------------------------------" + categorySeconds.size());
		return categorySeconds;
	}

	public int findCountCsList(int parseInt) {
		long count = 0;
		String hql = "select count(*) from Goods g join g.categorySecond cs where cs.csId = ?";
        count = (long) getSession().createQuery(hql).setInteger(0, parseInt).uniqueResult();
		return Integer.parseInt(String.valueOf(count));
	}

	public List<Object[]> findCsListByPage(int parseInt, int i, int j) {
		String hql = "select g.gId, g.gName, g.gSellPrice, g.gCoverImage, g.gMakeDate, g.user.userId from Goods g join g.categorySecond cs where cs.csId = ? and g.gIsSold = 1";
		List<Object[]> list = getSession().createQuery(hql).setInteger(0, parseInt).setFirstResult(i).setMaxResults(j).list();
		return list;
	}

	public CategorySecond findCsByCsId(int parseInt) {
		CategorySecond categorySecond = (CategorySecond) getSession().get(CategorySecond.class, parseInt);
		return categorySecond;
	}
}
