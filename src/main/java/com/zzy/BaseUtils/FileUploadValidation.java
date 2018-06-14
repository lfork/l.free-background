package com.zzy.BaseUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUploadValidation {
	
	private static final Long FILE_MAX_SIZE = 2048l;
	private static final List<String> FILE_TYPE = new ArrayList<>();
	private static final List<String> FILE_EXTENSION = new ArrayList<>();
	
	static{
		FILE_TYPE.add("image/bmp");
		FILE_TYPE.add("image/png");
		FILE_TYPE.add("image/gif");
		FILE_TYPE.add("image/jpeg");
		FILE_TYPE.add("image/jpg");
		FILE_TYPE.add("image/x-png");
		FILE_TYPE.add("image/pjpeg");
		FILE_EXTENSION.add("jpg");
		FILE_EXTENSION.add("png");
	}
	
	/**
	 * 测试文件的大小
	 */
	public  boolean fileSizeValidate(File file){
		if(file.length() > FILE_MAX_SIZE){
			return false;
		}
		return true;
	}
	
	/**
	 * 测试文件的扩展的扩展名
	 */
	public boolean fileTypeValidate(File file){
		String fileName = file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf(","), fileName.length());
		if(FILE_EXTENSION.contains(fileType)){
			return true;
		}
		return false;
	}
	
}
