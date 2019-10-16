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
    <button class="layui-btn" lay-submit="" lay-filter="demo1" style="width: 150px;">添加按钮</button></a>  
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
        <th>宿管ID</th>
        <th>宿管名称</th>
        <th>宿管密码</th>
        <th>所管楼号</th>
        <th>操作</th>
       
       
      </tr> 
    </thead>
    
    <tbody>
    <c:forEach items="${adminList}" var="xd">
      <tr>
        <td>${xd.adminid}</td>
        <td>${xd.adminname}</td>
        <td>${xd.adminpwd}</td>
        <td>${xd.buildid.buildname}</td>  
        <td><a href="AdminServlet?method=byid2&adminid=${xd.adminid}">修改</a></td>      
      </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>


</body>
</html>