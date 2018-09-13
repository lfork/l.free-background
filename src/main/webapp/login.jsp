<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		
	<form action="http://www.lfork.top/22y/user_login" method="post">
		学号 ： <input type="text" name="studentId" /><br>
		password ： <input type="text" name="userPassword" /><br>
		<input type="submit" value="提交"/>
	</form>
	
	<%
		out.write("java.lang.NullPointerException"); //session.getAttribute("user").toString()
	%>
		
</body>
</html>