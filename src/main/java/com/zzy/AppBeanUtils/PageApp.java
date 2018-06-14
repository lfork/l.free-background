package com.zzy.AppBeanUtils;

import java.util.List;

public class PageApp<T> {
	
	private String cursor;
	
	private List<T> list;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
