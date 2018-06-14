package com.zzy.school.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.school.pojo.School;
import com.zzy.school.service.SchoolService;

public class SchoolAction extends ActionSupport{
	
	private SchoolService schoolService;
	
	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}
	
	public String getSchoolList(){
		
		JsonBean<List<School>> jsonBean = schoolService.getSchoolList();
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}
	
}
