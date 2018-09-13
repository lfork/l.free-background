package com.zzy.test;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TestJsonBean extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public static void main(String[] args) {

		// JsonBean jsonBean = new JsonBean();
		//
		// User user = new User();
		// user.setUserId(1123213);
		//
		// Gson gson = new Gson();
		// jsonBean.getData().put("user", user);
		//
		// String string = gson.toJson(jsonBean);
		// System.out.println(string);
//		String path= "E:/二手交易平台APP/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps\22y/userHeadImage\5a35682c-66bd-4e8b-a17a-58250e8256b2.png";
//		System.out.println(path.split("22y")[0]);
		
		System.out.println(School.getName(1));
	}
	

}
