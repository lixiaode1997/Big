<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
	/* 不加#是通过标签名称取，加#是通过id取得 */
	$(document).ready(function(){
		$('#form').submit(function(){
			var name=$('#name').val();
			var pwd=$('#pwd').val();
			if(name==""||name==null){
			alert("用户名不能为空");
			return false;
			}else if(pwd==""||pwd==null){
			alert("密码不能为空");
			return false;
			}else{
			return true;
			}
		});
	});
</script>
  </head>
  
  <body>
    <form action="StudentServlet?method=login" method="post" id="form">
    	用户名：<input id="name" type="text" name="loginName"><font id="warn" color="red"></font><br>
    	密码：<input id="pwd" type="text" name="loginPwd"><br>
    	<input id="su" type="submit" value="登录">
    </form>
  </body>
</html>
