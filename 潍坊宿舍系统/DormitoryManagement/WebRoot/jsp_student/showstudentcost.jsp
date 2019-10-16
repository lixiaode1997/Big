<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="layui-form" align="center">

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
					<th>学年</th>
					<th>宿舍费</th>
					<th>水电费</th>
					<th></th>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${map}" var="map">

					<tr>
						<td>第${map.key.costid}学年</td>
						<td>${map.key.costroom}</td>
						<td>${map.key.costwater}</td>
					

						<td><c:if test="${map.value==1}">
								<button class="layui-btn" lay-submit="" lay-filter="demo1"
									style="width: 150px;">已缴费</button>
							</c:if> 
							<c:if test="${map.value==0}">
								<a
									href="CostServlet?method=addstudentcost&id=${map.key.costid}&stuid=${login.stuid}">
									<button class="layui-btn" lay-submit="" lay-filter="demo1"
										style="width: 150px;">缴费</button>
								</a>
							</c:if></td>
							</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>