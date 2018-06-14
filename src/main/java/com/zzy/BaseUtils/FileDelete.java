package com.zzy.BaseUtils;

import java.io.File;

public class FileDelete {
	
	public static int FileDelete(Object... path){
		
		int i = 0;
		boolean b;
		for (Object str : path) {
			File file = new File(System.getProperty("user.dir").split("bin")[0] + "webapps" + File.separator + "image" + str.toString());
			b = file.delete();
			if(b){
				i++;
			}
		}
		
		return i;
	}
	
	
}
