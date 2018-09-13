package com.zzy.school.service;

import java.util.List;

import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.school.dao.SchoolDao;
import com.zzy.school.pojo.School;

public class SchoolService {
	
	private SchoolDao schoolDao;
	
	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	public JsonBean<List<School>> getSchoolList() {
		
		JsonBean<List<School>> jsonBean = new JsonBean<>();
		
		List<School> schools = schoolDao.getSchoolList();
		
		if(schools == null || schools.size() == 0){
			jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			jsonBean.setMsg("查询失败");
		}else{
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("查询成功");
			jsonBean.setData(schools);
		}
		
		
		return jsonBean;
	}
	
	
}
