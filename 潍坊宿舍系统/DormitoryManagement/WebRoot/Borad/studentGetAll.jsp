<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>layui</title>
<title>My JSP 'getAllStudent.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->


</head>

<body style="height: 100%">
	<div>
		<br>
	</div>
	<div class="layui-form">
		<div class="layui-form-item" align="left"></div>
		<table class="layui-table">
			<colgroup>
				<col width="100">
				<col width="100">
				<col width="100">
				<col width="150">
				<col width="150">
				<col width="150">
				<col width="150">
				<col width="100">
			</colgroup>
			<thead align="center">
				<tr>
					<th>公告编号</th>
					<th>公告内容</th>
					<th>公告时间</th>
					<th>公告人</th>
				</tr>
			</thead>

			<tbody>
			<c:forEach items="${list}" var="li">
				<tr>
					<td valign="middle">${li.boradId}</td>
					<td valign="middle">${li.boradCOntent}</td>
					<td valign="middle">${li.boradDate}</td>
					<td  valign="middle">${li.admin.adminname}</td>		
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
