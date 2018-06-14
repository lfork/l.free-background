package com.zzy.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.zzy.BaseUtils.DateConvert;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.categorySecond.dao.CategorySecondDao;
import com.zzy.categorySecond.pojo.CategorySecond;

public class JsonTest {
	public static void main(String[] args) {
//		JsonBean<List<CategorySecond>> result = new JsonBean<>();
//		CategorySecond c1 = new CategorySecond();
//		CategorySecond c2 = new CategorySecond();
//		CategorySecond c3 = new CategorySecond();
//		c1.setCsId(1);
//		c1.setCsName("enenen");
//		
//		c2.setCsId(2);
//		c2.setCsName("enenen2");
//		
//		c3.setCsId(3);
//		c3.setCsName("enenen3");
//		
//		
//		List<CategorySecond> cgts = new ArrayList<>();
//		
//		List<CategorySecond> categorySeconds = new CategorySecondDao().findCsList();
//		
//		System.out.println(categorySeconds.size());
//		
//		cgts.add(c1);
//		cgts.add(c2);
//		cgts.add(c3);
//		
//		result.setData(cgts);
//		
//		Gson g = new Gson();
//		
//		String str = g.toJson(result);
//		
//		System.out.println(str);
		
		
		System.out.println(System.getProperty("catalina.home"));
		System.out.println(new File("").getAbsolutePath());
		
	}

}
