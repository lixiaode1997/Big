<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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
					<th>学生姓名</th>
					<th>学生学号</th>
					<th>学生性别</th>
					<th>所在系别</th>
					<th>学制</th>
					<th>缴费状态</th>
					<th>操作</th>
				</tr>
    </thead>
    
    <tbody>
  
 
    <tr>
					<td>${ss.stuname}</td>
					<td>${ss.stuno}</td>
					<td  valign="middle">
							<c:if test="${ss.stusex==1}">女</c:if> 
							<c:if test="${ss.stusex==0}">男</c:if>
					</td>
					<td valign="middle">${ss.studept}</td>
					<td  valign="middle">${ss.stuyear}</td>
					<td  valign="middle"><c:choose>
							<c:when test="${ss.stupay==0}">未完成</c:when>
							<c:when test="${ss.stupay==1}">已完成</c:when>
						</c:choose></td>	
					<!-- <td><button>入住</button></td> -->
					<td><a href="StudentMidServlet?method=addMid&stuid=${ss.stuid }">
    					<button class="layui-btn" lay-submit="" 
    					lay-filter="demo1" style="width: 150px;">
    					入住</button></a> 
					</td>
				</tr>
    </tbody>
  </table>
  </div>

</body>
</html>