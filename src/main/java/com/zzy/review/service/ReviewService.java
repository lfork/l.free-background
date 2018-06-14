package com.zzy.review.service;

import java.util.Date;

import com.zzy.BaseUtils.DateConvert;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.goods.dao.GoodsDao;
import com.zzy.goods.pojo.Goods;
import com.zzy.review.dao.ReviewDao;
import com.zzy.review.pojo.Review;
import com.zzy.user.dao.UserDao;
import com.zzy.user.pojo.User;

public class ReviewService {
	
	private ReviewDao reviewDao;
	private UserDao userDao;
	private GoodsDao goodsDao;
	
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public JsonBean<Review> save(String userId, String goodsId, String reviewContext) {
		
		JsonBean<Review> jsonBean = new JsonBean<>();
		
		User existUser = userDao.findUserByUserId(Integer.parseInt(userId));
		Goods existGoods = goodsDao.findGoodsById(goodsId);
		
		if(existUser == null){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("用户ID出错");
			return jsonBean;
		}
		
		if(existGoods == null){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("商品ID出错");
			return jsonBean;
		}
		
		Review review = new Review(reviewContext, DateConvert.DateConvertToLong(new Date()), existGoods, existUser);
		reviewDao.save(review);
		jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
		jsonBean.setMsg("保存成功");
		jsonBean.setData(null);
		
		return jsonBean;
	}
	
}
