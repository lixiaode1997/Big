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
        <th>申请编号</th>
        <th>申请内容</th>
        <th>申请房间</th>
        <th>申请状态</th>
      </tr> 
    </thead>
    
    <tbody>
    <c:forEach items="${list11}" var="li">
      <tr>
        <td>${li.noticeid}</td>
        <td>${li.noticecontent}</td>
        <td>${li.roomid.roomno}</td>
        <td align="center" valign="middle">	
			<c:if test="${li.noticeflag==1}"><a href="NoticeServlet?method=updateFlag2&id=${li.noticeid}">待审核</a></c:if>
		</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>

</body>
</html>