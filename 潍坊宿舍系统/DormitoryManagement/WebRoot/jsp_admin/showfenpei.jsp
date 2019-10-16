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
	<div class="layui-form-item" align="left">
    <a href="company_addCom.action">
    <!-- <button class="layui-btn" lay-submit="" lay-filter="demo1" style="width: 150px;">添加按钮</button></a>   -->
    </div>
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
        <th>姓名</th>
        <th>学号</th>
        <th>性别</th>
        <th>系别</th>
        <th>宿舍</th>
        <th>楼号</th>
       
      </tr> 
    </thead>
    
    <tbody>
    <c:forEach items="${all}" var="company">
      <tr>
        <td>${company.stuname}</td>
        <td>${company.stuno}</td>
        <td><c:if test="${company.stusex==0}">男</c:if>
        <c:if test="${company.stusex==1}">女</c:if></td>
        <td>${company.studept}</td>
        <td>${company.roomid.roomno}</td>
        <td>${company.roomid.buildid.buildname}</td>
        <%--<td>
        <c:if test="${company.cflag==0}">已被注册</c:if>
        <c:if test="${company.cflag==1}">未被注册</c:if>
        </td> 
        <td><a href="company_editCompany.action?cid=${company.cid }">
        <img src="images/i_edit.gif" border="0" style="CURSOR: hand"></a>
        </td>        
        <td><a href="company_iceCompany.action?cid=${company.cid }">冻结</a>
        </td> --%> 
      </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>

</body>
</html>