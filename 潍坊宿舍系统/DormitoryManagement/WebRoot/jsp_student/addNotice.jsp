<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>

<body style="height: 100%" >
	${login.roomid.roomid}
	<form action="../NoticeServlet?method=add" method="post">
		内容：<br>
		<input type="hidden" name="id" value="${list.noticeid}">
	 	<textarea name="content" rows="10" cols="15"></textarea><br> 
		<input type="hidden" name="flag" value="0">
		<input type="hidden" name="room" value="${login.roomid.roomid}">
	
		<input type="submit" value="提交">
		
	</form>

</body>


</html>