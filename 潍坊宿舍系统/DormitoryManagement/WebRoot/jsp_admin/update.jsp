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
<div class="layui-form">
  <form action="AdminServlet?method=update" method="post">
  <input type="hidden" name="adminid" value="${admi.adminid}">
  <div>
  <br>
  <h2 align="center"><strong>修改</strong></h2>
  <br>
  </div>

  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">宿管名称</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" name="adminname" value="${admi.adminname}" lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
    </div>

  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">宿管密码</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" name="adminpwd" value="${admi.adminpwd}"  lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input">
    </div>
  </div>
  
  
  
  <!--   <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">所管楼号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" name="buildname" lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input">
    </div>
  </div> -->
  
  
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">所管楼号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <select name="buildname" lay-filter="aihao">
        <c:forEach items="${bui}" var="list">
              <option value="${list.buildid}">${list.buildname}</option>
          </c:forEach>
      </select>
    </div>
  </div> 
  
  
  
  
  
  
   <div class="layui-form-item">
    <div align="center">
    <div style="width: 100%; ">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认修改</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1)">返回</button>
    </div>
    </div>
  </div> 
 
   
  </form> 
   
</div>

</body>
<script src="./layui/layui.all.js" charset="utf-8"></script>

</html>