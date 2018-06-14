package com.zzy.goods.dao;

import java.util.List;

import org.hibernate.Hibernate;

import com.zzy.HibernateUtil.BaseDao;
import com.zzy.goods.pojo.Goods;

public class GoodsDao extends BaseDao{

	public void save(Goods goods) {
		getSession().saveOrUpdate(goods);
	}

	public Goods findGoodsById(String goodsId) {
		
		Goods goods = (Goods) getSession().get(Goods.class, Integer.parseInt(goodsId));
		
		if(goods == null){
			return null;
		}
		
		return goods;
	}

	public void deleteGoodsById(Goods goods) {
		getSession().delete(goods);
	}

	public List<Object[]> findGoodsByNameLike(String goodsLikeName) {
		
		String sql = "SELECT g.`g_id`, g.`g_name`, g.`g_sellprice`, g.`g_coverimage`, g.`g_makedate`, g.`u_id` FROM goods g WHERE g.`g_name` LIKE ? ORDER BY g.`g_makedate` DESC LIMIT ?";
		
		List<Object[]> list = getSession().createSQLQuery(sql).setString(0, "%" + goodsLikeName + "%").setInteger(1, 20).list();
		
		return list;
	}

}
