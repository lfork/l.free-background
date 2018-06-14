package com.zzy.review.dao;

import java.util.List;

import com.zzy.HibernateUtil.BaseDao;
import com.zzy.review.pojo.Review;

public class ReviewDao extends BaseDao{

	public void save(Review review) {
		getSession().save(review);
	}

	public List<Object[]> getReviewByGoodsId(String goodsId) {
		String sql = "SELECT r.r_id, r.r_context, r.r_maketime, u.u_id, u.u_name, u.u_imagepath FROM review r,USER u WHERE r.g_id = ? AND u.u_id = r.u_id";
		List<Object[]> list = getSession().createSQLQuery(sql).setInteger(0, Integer.parseInt(goodsId)).setMaxResults(5).list();
		return list;
	}
	
	
	
}
