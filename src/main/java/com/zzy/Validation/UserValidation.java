package com.zzy.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

	/**
	 * 所运用的正则表达式的集合： 
	 * userId 的正则表达式："-?[1-9]\\d*" (数字) 
	 * userName的正则表达式："[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+"
	 * userPassword 的正则表达式："^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$" (只能位数字或者字母) 
	 * userEmail 的正则表达式："\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
	 * userPhone 的正则表达式："0?(13|14|15|18)[0-9]{9}"
	 * 
	 */

	public static final String regex_UserId = "-?[1-9]\\d*";

	public static final String regex_UserName = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";

	private static final String regex_UserPassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

	public static final String regex_UserEmail = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

	public static final String regex_UserPhone = "0?(13|14|15|18)[0-9]{9}";

	// 对注册时的数据进行验证
	public static boolean RegistValidation(String userId, String userName, String userPassword) {
		
		System.out.println(RegexValidation(userId.trim(), regex_UserId) && RegexValidation(userName.trim(), regex_UserName)
				&& RegexValidation(userPassword.trim(), regex_UserPassword));
		if (RegexValidation(userId.trim(), regex_UserId) && RegexValidation(userName.trim(), regex_UserName)
				&& RegexValidation(userPassword.trim(), regex_UserPassword)) {
			return true;
		}
		return false;
	}

	// 对修改用户信息后保存操作之前的数据进行修改
	public static boolean SaveValidation(String userId, String userName, String userEmail,
			String userPhone) {
		if (RegexValidation(userId, regex_UserId) && RegexValidation(userName, regex_UserName) && RegexValidation(userEmail, regex_UserEmail)
				&& RegexValidation(userPhone, regex_UserPhone)) {
			return true;
		}
		return false;
	}

	// 传入要验证的字段，以及需要的正则表达式， 进行字段的验证
	public static boolean RegexValidation(String args, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(args);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
	public static boolean userFieldNotNullTest(Object args) {

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

}
