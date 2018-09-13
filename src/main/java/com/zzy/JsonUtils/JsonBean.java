package com.zzy.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonBean<T> {

	/**
	 * 保存操作的状态信息
	 */
	private Integer id;

	/**
	 * 保存返回的的数据信息
	 */
	private String msg;

	/**
	 * 返回保存的数据
	 */
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
