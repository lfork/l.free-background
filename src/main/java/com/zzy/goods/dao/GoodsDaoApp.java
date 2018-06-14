package com.zzy.goods.dao;

import java.util.List;

import com.zzy.HibernateUtil.BaseDao;
import com.zzy.goods.pojo.Goods;

public class GoodsDaoApp extends BaseDao{

	public List<Object[]> getGoodsPage(String csId, String cursor) {
		String hql = "SELECT g.g_id, g.g_name, g.g_sellprice, g.g_coverimage, g.g_makedate,u.u_id,s.school_name FROM user u, goods g ,categorysecond cs, school s WHERE u.u_school = s.school_id AND g.cs_id = cs.cs_id AND g.u_id= u.u_id AND g.cs_id = ? AND g.g_issold = 1 AND (SELECT STR_TO_DATE(g.g_makedate,'%Y-%m-%d %H:%i:%s') <= STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s')) ORDER BY (SELECT STR_TO_DATE(g.g_makedate,'%Y-%m-%d %H:%i:%s')) DESC";
		List<Object[]> objects = getSession().createSQLQuery(hql).setInteger(0, Integer.parseInt(csId)).setString(1, cursor).setMaxResults(20).list();
		
		return objects;
	}

}
