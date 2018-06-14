package com.zzy.goods.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.zzy.AppBeanUtils.GoodsBeanApp;
import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.BaseUtils.DateConvert;
import com.zzy.BaseUtils.FileDelete;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.goodImages.pojo.GoodsImage;
import com.zzy.goods.dao.GoodsDao;
import com.zzy.goods.pojo.Goods;
import com.zzy.review.dao.ReviewDao;
import com.zzy.review.pojo.Review;
import com.zzy.user.pojo.User;

public class GoodsService {
	private GoodsDao goodsDao;
	private ReviewDao reviewDao;
	
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public Goods save(Goods goods) {
		goods.setgMakeDate(DateConvert.DateConvertToLong(new Date()));
		goods.setgIsSold(1);
		goodsDao.save(goods);
		return null;
	}

	public GoodsBeanApp findGoodsById(String goodsId) {
		
		Goods goods = goodsDao.findGoodsById(goodsId);
		
		if(goods == null){
			return null;
		}
		
		String[] images = new String[goods.getGoodsImages().size() + 1];
		
		images[0] = goods.getgCoverImage();
				
		Set<GoodsImage> goodsImages = goods.getGoodsImages();
		
		Iterator<GoodsImage> iterator = goodsImages.iterator();
		int i = 1;
		while(iterator.hasNext()){
			images[i++] = iterator.next().getgImageFile();
		}
		
		List<Object[]> reviewList = reviewDao.getReviewByGoodsId(goodsId);
		List<Review> realReview = new ArrayList<>();
		
		for (Object[] b : reviewList) {
			User user = new User();
			user.setUserId((Integer)b[3]);
			user.setUserName((String)b[4]);
			user.setUserImagePath((String)b[5]);
			Review review = new Review((Integer)b[0], (String)b[1], (String)b[2], null, user);
			
			realReview.add(review);
		}

		
		GoodsBeanApp beanApp = new GoodsBeanApp(goods.getgId(), goods.getgName(), goods.getgSellPrice(), goods.getgBuyPrice(), goods.getgDesc(), goods.getgMakeDate(), goods.getUser().getUserId(), goods.getUser().getUserName(), goods.getUser().getUserImagePath(),goods.getUser().getUserSchool().getSchoolName() ,images);
		beanApp.setReviews(realReview);
		
		return beanApp;
	}

	public boolean deleteGoodsById(String goodsId) {
		
		Goods goods = goodsDao.findGoodsById(goodsId);
		
		if(goods == null){
			return false;
		}
		
		String[] images = new String[goods.getGoodsImages().size() + 1];
		
		images[0] = goods.getgCoverImage();
				
		Set<GoodsImage> goodsImages = goods.getGoodsImages();
		
		Iterator<GoodsImage> iterator = goodsImages.iterator();
		int i = 1;
		while(iterator.hasNext()){
			images[i++] = iterator.next().getgImageFile();
		}
		
		FileDelete.FileDelete(images);
		
		goodsDao.deleteGoodsById(goods);
		return true;
	}

	public JsonBean<List<GoodsPageBeanApp>> findGoodsByNameLike(String goodsLikeName) {
		
		JsonBean<List<GoodsPageBeanApp>> jsonBean = new JsonBean<>();
		
		if(goodsLikeName == null || "".equals(goodsLikeName.trim())){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("前端数据错误");
			jsonBean.setData(null);
			return jsonBean;
		}
		
		List<Object[]> list = goodsDao.findGoodsByNameLike(goodsLikeName);
		List<GoodsPageBeanApp> beanApps = new ArrayList<>();
		for (Object[] objects : list) {
			GoodsPageBeanApp beanApp = new GoodsPageBeanApp((Integer)objects[0], (String)objects[1], (double)objects[2], (String)objects[3], (String)objects[4], (Integer)objects[5]);
			beanApps.add(beanApp);
		}
		
		jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
		jsonBean.setMsg("查询成功");
		jsonBean.setData(beanApps);
		
		return jsonBean;
	}

	
}
