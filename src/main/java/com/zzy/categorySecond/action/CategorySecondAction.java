package com.zzy.categorySecond.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zzy.BaseUtils.Page;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.categorySecond.dao.CategorySecondDao;
import com.zzy.categorySecond.pojo.CategorySecond;
import com.zzy.categorySecond.service.CategorySecondService;
import com.zzy.goods.pojo.Goods;

import sun.security.jca.GetInstance.Instance;

public class CategorySecondAction {

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	private String csId;
	private String pageNo;

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setCsId(String csId) {
		this.csId = csId;
	}

	private JsonBean bean = null;

	/**
	 * 得到 二级分类的 列表
	 * 
	 * @return
	 */
	public String getCsList() {
		JsonBean<List<CategorySecond>> bean1 = new JsonBean<>();
		List<CategorySecond> categorySeconds = categorySecondService.findCsList();
		
		JsonBean<List<CategorySecond>> result = new JsonBean<>();
		result.setData(categorySeconds);

		Gson g = new Gson();

		String str = g.toJson(result);

		System.out.println(str);
		System.out.println(categorySeconds.size() + " json test");

		if (categorySeconds == null || categorySeconds.size() == 0) {
			bean1.setMsg("数据查询失败");
			bean1.setId(StatusUtils.DATA_QUERY_FAIL);
		} else {
			bean1.setId(StatusUtils.OPERATION_SUCCESS);
			bean1.setMsg("操作成功");
			bean1.setData(categorySeconds);
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), bean1);

		return null;
	}

	public String getPageCsList() {

		bean = new JsonBean<Page<Goods>>();

		Page<Goods> page = null;

		if (csId == null || "".equals(csId)) {
			bean.setMsg("前端数据错误");
			bean.setId(StatusUtils.DATAFRONT_ERROR);
		} else {
			if (pageNo == null || "".equals(pageNo)) {
				pageNo = "1";
			}
			page = categorySecondService.getPageCsList(csId, pageNo);
			if (page == null) {
				bean.setMsg("数据查询失败");
				bean.setId(StatusUtils.DATA_QUERY_FAIL);
			} else {
				bean.setId(StatusUtils.OPERATION_SUCCESS);
				bean.setMsg("操作成功");
				bean.setData(page);
			}
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), bean);

		return null;
	}

}
