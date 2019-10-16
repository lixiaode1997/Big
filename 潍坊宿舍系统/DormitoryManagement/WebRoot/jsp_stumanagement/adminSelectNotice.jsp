<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectNotice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   
    <table width="888" height="65" border="1">
		<tr>
			<td width="104" align="center" valign="middle">申请编号</td>
			<td width="104" align="center" valign="middle">申请内容</td>
			<!-- <td width="124" align="center" valign="middle">申请时间</td> -->
			<td width="124" align="center" valign="middle">申请房间</td>
			<td width="124" align="center" valign="middle">申请状态</td>
			<!-- <td width="350" align="center" valign="middle">操作</td> -->
		</tr>
		<c:forEach items="${list}" var="li">
		<tr>
			<td align="center" valign="middle">${li.noticeid}</td>
			<td align="center" valign="middle">${li.noticecontent}</td>
			<td align="center" valign="middle">${li.roomid.roomno}</td>
			<%-- <td align="center" valign="middle">${li.noticeDate}</td> --%>
			<td align="center" valign="middle">
				
					<c:if test="${li.noticeflag==1}"><a href="NoticeServlet?method=updateFlag2&id=${li.noticeid}">待审核</a></c:if>
				
			</td>

		</tr>
		</c:forEach>
	</table>
	<a href="jsp_stumanagement/admin.jsp">返回首页</a>
  </body>
</html>
