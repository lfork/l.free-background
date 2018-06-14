package com.zzy.school.dao;

import java.util.List;

import com.zzy.HibernateUtil.BaseDao;
import com.zzy.school.pojo.School;

public class SchoolDao extends BaseDao{

	public List<School> getSchoolList() {
		
		String hql = "from School";
		
		List<School> schools = getSession().createQuery(hql).list();
		
		return schools;
	}

	public School getSchoolById(Integer id) {
		
		School school = (School) getSession().get(School.class, id);
		
		return school;
	}
	
	
	
}
