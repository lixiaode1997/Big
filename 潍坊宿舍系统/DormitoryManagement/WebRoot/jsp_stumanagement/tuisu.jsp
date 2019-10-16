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
		<td width="104" align="center" valign="middle">id</td>
			<td width="104" align="center" valign="middle">学生姓名</td>
			<td width="104" align="center" valign="middle">宿舍号</td>
			<td width="124" align="center" valign="middle">楼号</td>
			<td width="124" align="center" valign="middle">退宿状态</td>
		</tr>
    </thead>
    
    <tbody>
    <c:forEach items="${midd}" var="m">
		
			<tr>
				<td align="center" valign="middle">${m.mid}</td>
				<td align="center" valign="middle">${m.stuid.stuname}</td>
				<td align="center" valign="middle">${m.roomid.roomno}</td>
				<td align="center" valign="middle">${m.roomid.buildid.buildname}</td>
				<td align="center" valign="middle"> <c:choose>
						 <c:when test="${m.flag==1}">
		<a href="MiddleServlet?method=del&flag=0&middleid=${m.mid}&stuid=${m.stuid.stuid}&roomid=${m.roomid.roomid}">待审批</a></c:when>
						<c:when test="${m.flag==0}">已审批</c:when> 
					</c:choose> </td>

			</tr>
		</c:forEach>
    </tbody>
  </table>
  </div>

</body>
</html>