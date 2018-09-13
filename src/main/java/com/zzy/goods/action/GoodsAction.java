package com.zzy.goods.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.zzy.AppBeanUtils.GoodsBeanApp;
import com.zzy.BaseUtils.FileUpload;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.categorySecond.pojo.CategorySecond;
import com.zzy.categorySecond.service.CategorySecondService;
import com.zzy.goodImages.pojo.GoodsImage;
import com.zzy.goods.pojo.Goods;
import com.zzy.goods.service.GoodsService;
import com.zzy.user.pojo.User;
import com.zzy.user.service.UserService;

public class GoodsAction implements ModelDriven<Goods> {

	private Goods goods = new Goods();
	private GoodsService goodsService;
	private UserService userService;
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	JsonBean jsonBean = null;
	
	//接受用户传过来的学号
	private String studentId;
	
	private String[] desc;
	
	public void setDesc(String[] desc) {
		this.desc = desc;
	}
	
	//接受用户传过来的二级分类ID
	private String csId;
	
	//接受传过来的商品ID
	private String goodsId;
	
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public void setCsId(String csId) {
		this.csId = csId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setJsonBean(JsonBean jsonBean) {
		this.jsonBean = jsonBean;
	}

	private File coverImage; // 上传所对应的文件
	private String coverImageContentType; // 对应上传文件的类型
	private String coverImageFilename; // 对应上传文件的名称

	public void setCoverImage(File coverImage) {
		this.coverImage = coverImage;
	}

	public void setCoverImageContentType(String coverImageContentType) {
		this.coverImageContentType = coverImageContentType;
	}

	public void setCoverImageFilename(String coverImageFilename) {
		this.coverImageFilename = coverImageFilename;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	private File[] images;
	private String[] imagesFileName;
	private String[] imagesContentType;

	public void setImages(File[] images) {
		this.images = images;
	}

	public void setImagesFileName(String[] imagesFileName) {
		this.imagesFileName = imagesFileName;
	}

	public void setImagesContentType(String[] imagesContentType) {
		this.imagesContentType = imagesContentType;
	}

	public String upload() throws IOException {

		jsonBean = new JsonBean<Goods>();
		System.out.println("--------------------------" + imagesFileName);
		String path = null;
		User existUser = null;
		CategorySecond categorySecond = null;

		Set<GoodsImage> goodsImages = new HashSet<>();

		if (studentId == null || "".equals(studentId) || csId == null || "".equals(csId) || goods.getgName() == null || "".equals(goods.getgName())) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("用户账号出错");
		}else{
			existUser = userService.findUserByUserId(Integer.parseInt(studentId));
			if(existUser == null){
				jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
				jsonBean.setMsg("查询失败");
			}else{
				categorySecond = categorySecondService.findCsByCsId(Integer.parseInt(csId));
				path = FileUpload.fileUpload(coverImage, coverImageContentType, coverImageFilename, "goodsImage");
				if(path == null){
					jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
					jsonBean.setMsg("封面文件图片信息不可以出错");
				}else{
					goods.setgCoverImage(path);
					if(images != null && images.length > 0){
						for (int i = 0; i < images.length; i++) {
							path = FileUpload.fileUpload(images[i], imagesContentType[i], imagesFileName[i], "goodsImage");
							GoodsImage goodsImage = new GoodsImage();
							goodsImage.setgImageFile(path);
							goodsImage.setGoods(goods);
							if(desc[i] == null || "".equals(desc[i])){
								goodsImage.setgImageDesc("");
							}else{
								goodsImage.setgImageDesc(desc[i]);
							}
							goodsImages.add(goodsImage);
						}
						goods.setGoodsImages(goodsImages);
//						for (GoodsImage goodsImage : goodsImages) {
//							goodsImage.setGoods(goods);
//						}
					}
					goods.setUser(existUser);
					goods.setCategorySecond(categorySecond);
					goodsService.save(goods);
					jsonBean.setMsg("商品上传成功");
					jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
				}
			}
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}
	
	/**
	 * 查询商品 通过商品ID, 安卓与Web进行通用
	 * @return
	 */
	public String getGoodsById(){
		
		jsonBean = new JsonBean<GoodsBeanApp>();
		
		if(goodsId == null || "".equals(goodsId)){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("商品ID出错");
		}else{
			GoodsBeanApp goodsBeanApp = goodsService.findGoodsById(goodsId);
			
			if(goods == null){
				jsonBean.setMsg("商品查询失败");
				jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			}else{
				Set<GoodsImage> images = goods.getGoodsImages();
				
				for (GoodsImage goodsImage : images) {
					goodsImage.setGoods(null);
				}
				jsonBean.setMsg("商品查询成功");
				jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
				jsonBean.setData(goodsBeanApp);
			}
		}
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}
	
	/**
	 * 删除商品 通过商品ID, 安卓与Web进行通用
	 * @return
	 */
	public String deleteGoodsById(){
		
		jsonBean = new JsonBean<GoodsBeanApp>();
		
		if(goodsId == null || "".equals(goodsId)){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("商品ID出错");
		}else{
			boolean b = goodsService.deleteGoodsById(goodsId);
			if(b){
				jsonBean.setData(StatusUtils.OPERATION_SUCCESS);
				jsonBean.setMsg("刪除成功");
			}else{
				jsonBean.setData(StatusUtils.DATA_DELETE_FAIL);
				jsonBean.setMsg("刪除失敗");
			}
		}
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}

	@Override
	public Goods getModel() {
		// TODO Auto-generated method stub
		return goods;
	}

}
