package com.zzy.user.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.appender.rolling.action.IfLastModified;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzy.AppBeanUtils.GoodsPageBeanApp;
import com.zzy.AppBeanUtils.PageApp;
import com.zzy.BaseUtils.FileUpload;
import com.zzy.JsonUtils.JsonBean;
import com.zzy.JsonUtils.ReturnJsonByResponse;
import com.zzy.JsonUtils.StatusUtils;
import com.zzy.user.pojo.User;
import com.zzy.user.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>, CookiesAware{

	private static final long serialVersionUID = 1L;
	
	// 接受界面传来的用户注册的 学号
	private String studentId;
	
	private String JSESSIONID;
	
	private String userSchoolId;
	
	public void setUserSchoolId(String userSchoolId) {
		this.userSchoolId = userSchoolId;
	}
	
	public void setJSESSIONID(String jSESSIONID) {
		JSESSIONID = jSESSIONID;
	}
	
//	private String cookieUser;
//	
//	public void setCookieUser(String cookieUser) {
//		this.cookieUser = cookieUser;
//	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	private String cursor;
	
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	private File image; // 上传所对应的文件
	private String imageContentType; // 对应上传文件的类型
	private String imageFilename; // 对应上传文件的名称

	private JsonBean<User> jsonBean = null;
	
	public String getStudentId() {
		return studentId;
	}

	public void setJsonBean(JsonBean<User> jsonBean) {
		this.jsonBean = jsonBean;
	}

	public File getImage() {
		return image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public String getImageFilename() {
		return imageFilename;
	}

	private UserService userService; // 使用业务层的java对象

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 模型 驱动 User 对象
	private User user = new User();

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		return user;
	}
	
	public String getUserGoodsByUid(){
		JsonBean<List<GoodsPageBeanApp>> bean = new JsonBean<>();
		PageApp<GoodsPageBeanApp> pageApp = userService.getUserGoodsByUidApp(studentId, cursor);
		if(pageApp == null){
			bean.setId(StatusUtils.DATA_QUERY_FAIL);
			bean.setMsg("数据查找失败！");
		}else{
			bean.setId(StatusUtils.OPERATION_SUCCESS);
			bean.setMsg("数据查找成功！");
			bean.setData(pageApp.getList());
		}
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), bean);
		return null;
	}
	
	
	/**
	 * 查看用户信息
	 * @return
	 */
	public String info(){
		
		User exitIdUser = null;
		jsonBean = new JsonBean<>();
		if(studentId == null || "".equals(studentId)){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("用户数据必须字段存在空值， 请完成必须的数据填写");
		}else{
			exitIdUser = userService.findUserByUserId(Integer.parseInt(studentId));
			if(exitIdUser != null){
				jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
				jsonBean.setMsg("数据查询正常");
				jsonBean.setData(exitIdUser);
			}else{
				jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
				jsonBean.setMsg("未查找到指定人员信息!");
			}
		}
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	// 接受注册信息处理
	public String regist() {

		jsonBean = new JsonBean<User>();


		User exitIdUser = userService.findUserByUserId(Integer.parseInt(studentId));
		User exitNameUser = userService.findUserByUserName(user.getUserName());
		
		if (exitIdUser != null) {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("学号已经注册！");
		} else if(exitNameUser != null){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("用户名已存在！");
		}else{
			if (userFieldNotNullTest(studentId) && userFieldNotNullTest(user.getUserPassword())
					&& userFieldNotNullTest(user.getUserName())) {
				
				int schoolId = 10;
				
				if(userSchoolId != null && "".equals(userSchoolId)){
					schoolId = Integer.parseInt(userSchoolId);
				}

				boolean b = userService.saveUserRegistInfo(studentId, user.getUserName(), user.getUserPassword(), schoolId);

				if (b) {
					jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
					jsonBean.setMsg("注册成功！");
				} else {
					jsonBean.setId(StatusUtils.DATA_UPDATE_FAIL);
					jsonBean.setMsg("信息有误，注册失败， 请重新注册！");
				}
			} else {
				jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
				jsonBean.setMsg("用户数据必须字段存在空值， 请完成必须的数据填写");
			}
		}

		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}
	
	/**
	 * ajax 验证用户名是否可用
	 * @return
	 */
	public String findByName(){
		
		jsonBean = new JsonBean<User>();
		
		User exitNameUser = userService.findUserByUserName(user.getUserName());
		
		if(exitNameUser != null){
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("用户名已存在！");
		}else{
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("用户名可用！");
		}
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	// 接受处理登录信息
	public String login() {
		
		jsonBean = new JsonBean<User>();

		User existUser = null;
		boolean flag = false;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if (userFieldNotNullTest(studentId) && userFieldNotNullTest(user.getUserPassword())) {

			
//			if(cookies != null){
//				for (Entry<String, String> cookie: cookies.entrySet()) {
//					String[] content = cookie.getValue().split("-");
//					for (int i = 0; i < content.length; i++) {
//						if(content[i].equals(studentId)){
//							flag = true;
//							break;
//						}
//					}
//					if(flag){
//						break;
//					}
//				}
//			}
			
//			if(JSESSIONID != null){
//				flag = JSESSIONID.equals(ServletActionContext.getRequest().getSession().getId());
//			}
			
			if (flag) {
				jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
				jsonBean.setMsg("cookie缓存信息登录，允许登录");
			} else {

				existUser = userService.findUserByTwoFiled(studentId, user);
				
				if (existUser != null) {
//					StringBuffer cookieStr = new StringBuffer(existUser.getUserId().toString() + ":" + existUser.getUserName());
//					if(!"".equals(user.getUserEmail())){
//						cookieStr.append(":");
//						cookieStr.append(existUser.getUserEmail());
//					}
//					String cookieStr = userToCookieUser(existUser);
//					Cookie cookie = new Cookie("cookieUser", cookieStr);
					ServletActionContext.getRequest().getSession().setAttribute("sessionUser", existUser);
//					cookie.setMaxAge(60 * 60 * 24 * 2);
//					response.addCookie(cookie);
					jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
					jsonBean.setMsg("允许登录");
					jsonBean.setData(existUser);
				} else {
					jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
					jsonBean.setMsg("账号， 或者密码错误， 请再次登录！");
				}
			}
		} else {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("用户登录数据必须字段存在空值， 请完成必须的数据填写");
		}
		ReturnJsonByResponse.ReturnJson(response, jsonBean);
		return null;
	}

	private String userToCookieUser(User existUser) {
		
		StringBuffer buffer = new StringBuffer("");
		
		buffer.append(existUser.getUserId());
		buffer.append("-");
		buffer.append(existUser.getUserName());

		buffer.append("-");
		if(userFieldNotNullTest(existUser.getUserEmail())){
			buffer.append(existUser.getUserEmail());
		}else{
			buffer.append("null");
		}
		
		buffer.append("-");
		if(userFieldNotNullTest(existUser.getUserPhone())){
			buffer.append(existUser.getUserPhone());
		}else{
			buffer.append("null");
		}
		
		buffer.append("-");
		if(userFieldNotNullTest(existUser.getUserImagePath())){
			buffer.append(existUser.getUserImagePath());
		}else{
			buffer.append("null");
		}
		
		buffer.append("-");
		if(userFieldNotNullTest(existUser.getUserAddress())){
			buffer.append(existUser.getUserAddress());
		}else{
			buffer.append("null");
		}
		
		buffer.append("-");
		if(userFieldNotNullTest(existUser.getUserDesc())){
			
			buffer.append(existUser.getUserDesc());
		}else{
			buffer.append("null");
		}
		
		return buffer.toString();
	}

	/**
	 * 处理前台对 ajax 验证studentId 是否已经进行注册
	 * 
	 * 
	 */

	public String findById() {

		jsonBean = new JsonBean<User>();

		User existUser = userService.findUserByUserId(Integer.parseInt(studentId));

		if (existUser == null) {
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("可以注册");
		} else {
			jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
			jsonBean.setMsg("学号已经被注册，请直接进行登录");
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);

		return null;
	}

	/**
	 * 接受 http://www.lfork.top/22y/use_editInfo?userId=XXX 路径进行处理
	 */
	public String editInfo() {

		jsonBean = new JsonBean<User>();

		User existUser = userService.findUserByUserId(Integer.parseInt(studentId));

		if (existUser == null) {
			jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			jsonBean.setMsg("未查找到指定人员信息!");
		} else {
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("人员找到， 没有显示的数据信息为空");
			jsonBean.setData(existUser);
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	/**
	 * 接受此路径 http://www.lfork.top/22y/user_save?userId=XXX 的请求处理
	 * 
	 * @throws FileNotFoundException
	 */
	public String save() throws FileNotFoundException {

		jsonBean = new JsonBean<User>();
		boolean b;

		// 接受页面传过来的学号信息 保存到模型驱动中 准备往后台传
		user.setUserId(Integer.parseInt(studentId));

//			// 将上传的图片保存在服务器， 然后设置驱动 user 中的头像路径， 准备好数据，然后传入 Service 进行保存操作
//			String uploadImagePath = FileUpload.fileUpload(image, imageContentType, imageFilename, "userHeadImage");
//			
//			if(uploadImagePath == null){
//				jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
//				jsonBean.setMsg("所传文件不能为空");
//			}else{
//				user.setUserImagePath(uploadImagePath);
//				// 传入 Service 层进行数据的保存
				b = userService.saveUserEditInfo(user);
				if (!b) {
					jsonBean.setId(StatusUtils.DATAFRONT_ERROR);
					jsonBean.setMsg("前端数据出错");
				} else {
					jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
					jsonBean.setMsg("保存成功");
					jsonBean.setData(user);
				}
//			}

		// 返回页面保存的数据结果
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		return null;
	}
	
	/**
	 * 接受此路径 http://www.lfork.top/22y/user_imageUpload?userId=XXX 的请求处理
	 * 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public String imageUpload() throws SQLException, IOException {

		JsonBean<String> bean = new JsonBean<String>();
		
		if(!userFieldNotNullTest(studentId)){
			bean.setId(StatusUtils.DATAFRONT_ERROR);
			bean.setMsg("学号信息出错");
		}else{
			// 接受页面传过来的学号信息 保存到模型驱动中 准备往后台传
			user.setUserId(Integer.parseInt(studentId));

				// 将上传的图片保存在服务器， 然后设置驱动 user 中的头像路径， 准备好数据，然后传入 Service 进行保存操作
			String uploadImagePath = FileUpload.fileUpload(image, imageContentType, imageFilename, "userHeadImage");
				
				if(uploadImagePath == null){
					bean.setId(StatusUtils.DATAFRONT_ERROR);
					bean.setMsg("所传文件不能为空");
				}else{
					user.setUserImagePath(uploadImagePath);
					int i = userService.saveUserImage(user);
					if(i == 1){
						bean.setId(StatusUtils.OPERATION_SUCCESS);
						bean.setMsg("保存成功");
						bean.setData(uploadImagePath);
					}
					else {
						bean.setId(StatusUtils.DATA_UPDATE_FAIL);
						bean.setMsg("保存失败");
						bean.setData(null);
					}
				}
		}

		

		// 返回页面保存的数据结果
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), bean);
		return null;
	}
	
	public String logout(){
		
		User sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute("sessionUser");
		
		jsonBean = new JsonBean<>();
		
		if(sessionUser != null){
			ServletActionContext.getRequest().getSession().removeAttribute("sessionUser");
			jsonBean.setData(null);
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("用户成功登出");
		}
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}
	
	public String getSessionUser(){
		
		User sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute("sessionUser");
		
		jsonBean = new JsonBean<>();
		
		if(sessionUser != null){
			jsonBean.setData(sessionUser);
			jsonBean.setId(StatusUtils.OPERATION_SUCCESS);
			jsonBean.setMsg("查询成功");
		}else{
			jsonBean.setData(null);
			jsonBean.setId(StatusUtils.DATA_QUERY_FAIL);
			jsonBean.setMsg("用户信息已经登出");
		}
		
		ReturnJsonByResponse.ReturnJson(ServletActionContext.getResponse(), jsonBean);
		
		return null;
	}

	/**
	 * 用户数据修时的数据非空验证
	 * 
	 * @param user2
	 * @return
	 */
	private boolean userDataNotNullTest(User user2) {

		if (userFieldNotNullTest(user2.getUserId()) && userFieldNotNullTest(user2.getUserEmail())
				&& userFieldNotNullTest(user2.getUserName()) && userFieldNotNullTest(user2.getUserPhone())) {
			return true;
		}

		return false;
	}

	/**
	 * 用户数行字段的非空校验
	 * 
	 * @param args
	 * @return
	 */
	private boolean userFieldNotNullTest(Object args) {

		if (args instanceof Integer) {
			args = (Integer) args;
			if (args == null || args.toString().length() != 10) {
				return false;
			}
		} else if (args instanceof String) {
			args = ((String) args).trim();
			if (args == null || "".equals(args)) {
				return false;
			}
		}
		return true;
	}
	
	private Map<String, String> cookies;
	
	@Override
	public void setCookiesMap(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	
}
