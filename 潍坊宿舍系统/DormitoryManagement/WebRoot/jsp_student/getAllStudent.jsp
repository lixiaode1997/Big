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
					<th>学生姓名</th>
					<th>学生学号</th>
					<th>学生性别</th>
					<th>所在系别</th>
					<th>学制</th>
					<th>缴费状态</th>
					<!-- <th>退宿</th> -->
				</tr>
			</thead>

			<tbody>

				<tr>
					<td>${li.stuname}</td>
					<td valign="middle">${li.stupwd}</td>
					<td  valign="middle"><c:if
							test="${li.stusex==1}">女</c:if> <c:if test="${li.stusex==0}">男</c:if>
					</td>
					<td valign="middle">${li.studept}</td>
					<td  valign="middle">${li.stuyear}</td>
					<td  valign="middle"><c:choose>
							<c:when test="${li.stupay==0}">未完成</c:when>
							<c:when test="${li.stupay==1}">已完成</c:when>
						</c:choose></td>

					<td align="center" valign="middle">
					<c:if test="${lol.flag==1}" >
					<a id="link" href="MiddleServlet?method=addMid&flag=1&stuid=${li.stuid}" >申請退宿</a>
					</c:if>
					<c:if test="${lol.flag==0}" >
					<a>审核中</a>
					</c:if>
					</td>

					<%-- <td  valign="middle"><a href="MiddleServlet?method=addMid&flag=1&stuid=${li.stuid}"></a></td>
 --%>
				</tr>

			</tbody>
		</table>
	</div>
<script type="text/javascript">
function changelink(){
     document.getElementById("link").innerHTML = "申请退宿";
}
</script>

</body>
</html>
