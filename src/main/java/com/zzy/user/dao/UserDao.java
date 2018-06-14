package com.zzy.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.Work;

import com.zzy.HibernateUtil.BaseDao;
import com.zzy.goods.pojo.Goods;
import com.zzy.user.pojo.User;

public class UserDao extends BaseDao {

	public void saveUserRegistInfo(User user) {
		this.getSession().save(user);
	}

	// public User findUserByIdAndPassword(String studentId, String
	// userPassword) {
	//
	// String hql = "from User where userId = ?";
	// User user = (User) this.getSession().createQuery(hql).setInteger(0,
	// Integer.parseInt(studentId)).uniqueResult();
	// return user;
	// }

	public User findUserByUserId(Integer userId) {
		User user = (User) this.getSession().get(User.class, userId);
		return user;
	}

	public void saveUserEditInfo(User exitUser) {
		this.getSession().saveOrUpdate(exitUser);
	}

	public User findUserByCriteria(DetachedCriteria criteria) {
		return (User) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	public User findUserByUserName(String userName) {
		String hql = "from User where userName = ?";
		User user = (User) getSession().createQuery(hql).setString(0, userName).uniqueResult();
		return user;
	}

	public User findUserByTwoField(String fieldName, String fieldValue, String password) {
		User user = null;
		if(fieldName.equals("userId")){
			user = (User) getSession().createCriteria(User.class).add(Restrictions.eq(fieldName, Integer.parseInt(fieldValue)))
					.add(Restrictions.eq("userPassword", password)).uniqueResult();
		}else{
			user = (User) getSession().createCriteria(User.class).add(Restrictions.eq(fieldName, fieldValue))
					.add(Restrictions.eq("userPassword", password)).uniqueResult();
		}
		return user;
	}

	public User findUserByNameAndPassword(String studentId, String password) {
		String hql = "from User where userId = ? and userPassword = ?";
		User user = (User) getSession().createQuery(hql).setInteger(0, Integer.parseInt(studentId)).setString(1, password).uniqueResult();
		return user;
	}

	public User findUserByUserNameAndId(Integer userId, String userName) {
		String hql = "from User where userId != ? and userName = ?";
		User user = (User) getSession().createQuery(hql).setInteger(0, userId).setString(1, userName).uniqueResult();
		return user;
	}

	public List<Object[]> getUserGoodsByUid(Integer id) {
		
		String sql = "SELECT g.`g_id`, g.`g_name`, g.`g_sellprice`,g.`g_coverimage`,g.`g_makedate` FROM goods g WHERE g.`u_id` = ?";
		List<Object[]> goods = getSession().createSQLQuery(sql).setInteger(0, id).list();
		return goods;
	}

	public List<Object[]> getUserGoodsByUidApp(Integer id, String cursor) {
		String sql = "SELECT g.`g_id`, g.`g_name`, g.`g_sellprice`,g.`g_coverimage`,g.`g_makedate` FROM goods g WHERE g.`u_id` = ? and (SELECT STR_TO_DATE(g.g_makedate,'%Y-%m-%d %H:%i:%s') <= STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s')) ORDER BY g.g_makedate DESC, g.g_id ASC";
		List<Object[]> goods = getSession().createSQLQuery(sql).setInteger(0, id).setString(1, cursor).setMaxResults(20).list();
		return goods;
	}

	public int saveUserImage(User exitUser) throws SQLException {
		final Object[] objs = new Object[1];
		getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				String sql = "update user set u_imagepath = ? where u_id = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, exitUser.getUserImagePath());
				statement.setInt(2, exitUser.getUserId());
				objs[0] = statement.executeUpdate();
			}
		});
		
		return (int) objs[0];
	}

}
