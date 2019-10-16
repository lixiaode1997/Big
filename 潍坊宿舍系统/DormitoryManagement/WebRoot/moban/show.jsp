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
        <th>列名1</th>
        <th>列名2</th>
        <th>列名3</th>
        <th>。。。</th>
        <th>。。。</th>
        <th>。。。</th>
        <th>。。。</th>
        <th>。。。</th>
       
      </tr> 
    </thead>
    
    <tbody>
    <c:forEach items="${CompanyList}" var="company">
      <tr>
        <td>${company.cname}</td>
        <td>${company.caddress}</td>
        <td>${company.cscale}</td>
        <td>${company.csketch}</td>
        <td>
            <c:if test="${company.ciflegal==0}">合法</c:if>
            <c:if test="${company.ciflegal==1}">不合法</c:if>
        </td>
        <td>
        <c:if test="${company.cflag==0}">已被注册</c:if>
        <c:if test="${company.cflag==1}">未被注册</c:if>
        </td>
        <td><a href="company_editCompany.action?cid=${company.cid }">
        <img src="images/i_edit.gif" border="0" style="CURSOR: hand"></a>
        </td>        
        <td><a href="company_iceCompany.action?cid=${company.cid }">冻结</a>
        </td> 
      </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>

</body>
</html>