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
        <th>房间号</th>
        <th>床号</th>
        <th>房间人数</th>
        <th>楼号</th>
        <th></th>
        <th></th>
        
      </tr> 
    </thead>
    
    <tbody>
    <c:forEach items="${rList}" var="item">
      <tr>
        <td>${item.roomno}</td>
        <td>${item.roombed}</td>
        <td>${item.roomnum}</td>
        <td>${item.buildid.buildname}</td>
         
      </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>
</div>

</body>
