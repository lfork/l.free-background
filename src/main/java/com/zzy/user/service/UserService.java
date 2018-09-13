package com.zzy.user.service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.PropertyNotWritableException;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.loader.custom.ScalarResultColumnProcessor;

import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.AppBeanUtils.PageApp;
import com.zzy.BaseUtils.Base;
import com.zzy.BaseUtils.DateConvert;
import com.zzy.UUIDUtils.UUIDCreate;
import com.zzy.Validation.UserValidation;
import com.zzy.goods.pojo.Goods;
import com.zzy.school.dao.SchoolDao;
import com.zzy.school.pojo.School;
import com.zzy.user.dao.UserDao;
import com.zzy.user.pojo.User;

public class UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private SchoolDao schoolDao;
	
	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	public boolean saveUserRegistInfo(String studentId, String userName, String userPassword, int userSchoolId) {

		if (!UserValidation.RegistValidation(studentId, userPassword, userPassword)) {
			return false;
		}

		User user = new User();
		user.setUserId(Integer.parseInt(studentId));
		user.setUserName(userName);
		String password = Base.getBase64(userPassword);
		user.setUserPassword(password);
		user.setUserMakeDate(DateConvert.DateConvertToLong(new Date()));
		School userSchool = new School(userSchoolId, null);
		user.setUserSchool(userSchool);

		userDao.saveUserRegistInfo(user);

		return true;
	}

	public User findUserByTwoFiled(String studentId, User user) {
		
		String password = Base.getBase64(user.getUserPassword());
		
//		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		String fieldName = null;
		String fieldValue;
		
		if(UserValidation.RegexValidation(studentId, UserValidation.regex_UserId)){
//			criteria.add(Restrictions.eq("userId", Integer.parseInt(studentId))).add(Restrictions.eq("userPassword", password));
			fieldName = "userId";
		}else if(UserValidation.RegexValidation(studentId, UserValidation.regex_UserEmail)){
//			criteria.add(Restrictions.eq("userEmail",studentId)).add(Restrictions.eq("userPassword", password));
			fieldName = "userEmail";
		}else if(UserValidation.RegexValidation(studentId, UserValidation.regex_UserName)){
//			criteria.add(Restrictions.eq("userName", studentId)).add(Restrictions.eq("userPassword", password));
			fieldName = "userName";
		}
		
		fieldValue = studentId;
		
//		User loginUser = userDao.findUserByCriteria(criteria);
		User loginUser = userDao.findUserByTwoField(fieldName, fieldValue, password);
		
		if(loginUser != null){
			loginUser.setUserPassword(UUIDCreate.getUUID());
			return loginUser;
		}
		
		//只能使用 学号进行登录
//		if(!UserValidation.LoginValidation(studentId, userPassword)){
//			return null;
//		}
//		
//		User loginUser = userDao.findUserByIdAndPassword(studentId, userPassword);
//		
//		String password = Base.getFromBase64(loginUser.getUserPassword());
//		if(password.equals(password)){
//			loginUser.setUserPassword(UUIDCreate.getUUID());
//			return loginUser;
//		}
		
		return loginUser;
	}

	public User findUserByUserId(Integer userId) {

		if (userId == null) {
			return null;
		}

		User user = userDao.findUserByUserId(userId);

		if (user != null) {
			user.setUserPassword(UUIDCreate.getUUID());
			return user;
		}
		return null;

	}

	public boolean saveUserEditInfo(User user) {
			User exitUser = userDao.findUserByUserId(user.getUserId());

			if(UserValidation.userFieldNotNullTest(user.getUserName())){
				User user2 = userDao.findUserByUserNameAndId(user.getUserId(), user.getUserName());
				if(user2 == null){
					if(UserValidation.RegexValidation(user.getUserName(), UserValidation.regex_UserName)){
						exitUser.setUserName(user.getUserName());
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
			
			if(UserValidation.userFieldNotNullTest(user.getUserEmail())){
				if(UserValidation.RegexValidation(user.getUserEmail(), UserValidation.regex_UserEmail)){
					exitUser.setUserEmail(user.getUserEmail());
				}else{
					return false;
				}
			}
			
			if(UserValidation.userFieldNotNullTest(user.getUserPhone())){
				if(UserValidation.RegexValidation(user.getUserPhone(), UserValidation.regex_UserPhone)){
					exitUser.setUserPhone(user.getUserPhone());
				}else{
					return false;
				}
			}
			
			if(user.getUserSchool().getId() != null && user.getUserSchool().getId() != exitUser.getUserSchool().getId()){
				School school = schoolDao.getSchoolById(user.getUserSchool().getId());
				exitUser.setUserSchool(school);
			}
			
			exitUser.setUserDesc(user.getUserDesc());
			exitUser.setUserMakeDate(DateConvert.DateConvertToLong(new Date()));

			userDao.saveUserEditInfo(exitUser);

			return true;
	}

	public User findUserByUserName(String userName) {
		if(userName == null || "".equals(userName)){
			return null;
		}
		
		User user = userDao.findUserByUserName(userName);
		return user;
	}

	public User findUserByNameAndPassword(String studentId, String userPassword) {
		
		String password = Base.getBase64(userPassword);
		User user = userDao.findUserByNameAndPassword(studentId, password);
		return user;
	}

	public int saveUserImage(User user) throws SQLException {
		User exitUser = userDao.findUserByUserId(user.getUserId());

		if (exitUser.getUserImagePath() != null) {
			File file = new File(System.getProperty("user.dir").split("22y")[0] + "image" + exitUser.getUserImagePath());
			if(file != null && file.exists()){
				file.delete();
			}
		}
		exitUser.setUserImagePath(user.getUserImagePath());
		return userDao.saveUserImage(exitUser);
	}

	public List<GoodsPageBeanApp> getUserGoodsByUid(String studentId) {
		
		Integer id = Integer.parseInt(studentId);
		
		List<Object[]> goods = userDao.getUserGoodsByUid(id);
		
		List<GoodsPageBeanApp> goodsPageBeanApps = null;
		
		if(goods != null && goods.size() > 0){
			goodsPageBeanApps = new ArrayList<>();
			for (Object[] good : goods) {
				GoodsPageBeanApp app = new GoodsPageBeanApp((Integer)good[0], (String)good[1], (double)good[2], (String)good[3], (String)good[4], null);
				goodsPageBeanApps.add(app);
			}
		}
		
		
		return goodsPageBeanApps;
	}

	public PageApp<GoodsPageBeanApp> getUserGoodsByUidApp(String studentId, String cursor) {
		PageApp<GoodsPageBeanApp> pageApp = new PageApp<>();
		pageApp.setCursor(cursor);
		
		Integer id = Integer.parseInt(studentId);
		
		List<Object[]> goods = userDao.getUserGoodsByUidApp(id, cursor);
		
		List<GoodsPageBeanApp> goodsPageBeanApps = null;
		
		if(goods != null && goods.size() > 0){
			goodsPageBeanApps = new ArrayList<>();
			for (Object[] good : goods) {
				GoodsPageBeanApp app = new GoodsPageBeanApp((Integer)good[0], (String)good[1], (double)good[2], (String)good[3], (String)good[4], null);
				goodsPageBeanApps.add(app);
			}
		}else{
			return null;
		}
		
		pageApp.setList(goodsPageBeanApps);
		
		return pageApp;
	}

}
