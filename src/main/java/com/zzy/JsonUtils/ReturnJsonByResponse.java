package com.zzy.JsonUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ReturnJsonByResponse {
	
	public static void ReturnJson(HttpServletResponse response, JsonBean bean){
		
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		Gson gson = new Gson();
		
		String result = gson.toJson(bean);
		
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter printWriter = null;
		
		try {
			printWriter = new PrintWriter(response.getWriter(), true);
			printWriter.println(result);
		} catch (IOException e) {
			
			printWriter.flush();
			if(printWriter != null){
				printWriter.close();
			}
		}
		
	}
	
	
}
