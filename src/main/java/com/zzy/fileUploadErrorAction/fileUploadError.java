package com.zzy.fileUploadErrorAction;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.JsonUtils.StatusUtils;

public class fileUploadError extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		
		JsonBean<String> jsonBean = new JsonBean<>();
		
		jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
		jsonBean.setMsg("所上传的文件格式不正确，请重新填写");
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}
	
}
