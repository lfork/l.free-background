package com.zzy.school.pojo;


public class School {
	
	private Integer id;
	private String schoolName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public School(Integer id, String schoolName) {
		super();
		this.id = id;
		this.schoolName = schoolName;
	}
	
	public School() {
		super();
	}
	
	@Override
	public String toString() {
		return "School [id=" + id + ", schoolName=" + schoolName + "]";
	}
}
