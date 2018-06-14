package com.zzy.review.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.review.pojo.Review;
import com.zzy.review.service.ReviewService;

public class ReviewAction extends ActionSupport{
	
	private ReviewService reviewService;
	
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	private String userId;
	private String goodsId;
	private String reviewContext;
	
	public void setReviewContext(String reviewContext) {
		this.reviewContext = reviewContext;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public String reviewSave(){
		JsonBean<Review> jsonBean = reviewService.save(userId, goodsId, reviewContext);
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

}
