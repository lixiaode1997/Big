<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>修改职位</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>

<body style="height: 100%">
<div class="layui-form">

  <form action="position_updPosition" method="post">
  <input type="hidden" name="pid" value="${position1.pid }" />
  <div>
  <br>
  <h2 align="center"><strong>编辑</strong></h2>
  <br>
  </div>
  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">名称</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" name="pname" lay-verify="title" class="layui-input"
             value="${position1.pname }" >
    </div>
  </div>
  
  
 <%--  <div class="layui-form-item"  style="width: 550px; ">
    <label class="layui-form-label">是否热门</label>
    <div class="layui-input-block" style="width: 400px; ">
      <select name="phot">
        <option value="0" ${position1.phot==0?'selected="selected"':'' }>不热门</option>
        <option value="1" ${position1.phot==1?'selected="selected"':'' }>热门</option>
      </select>
    </div>
  </div> --%>
  
 
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">一级分类</label>
    <div class="layui-input-block" style="width: 400px; ">
      <select name="industry.iid">
          <c:forEach items="${industry }" var="indus">
              <option value="${indus.iid }" ${position1.industry.iid==indus.iid?'selected="selected"':'' }>${indus.iname }</option>
          </c:forEach>
      </select>
    </div>
  </div>
  
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">二级分类</label>
    <div class="layui-input-block" style="width: 400px; ">
      <select name="industry.iid">
          <c:forEach items="${industry }" var="indus">
              <option value="${indus.iid }" ${position1.industry.iid==indus.iid?'selected="selected"':'' }>${indus.iname }</option>
          </c:forEach>
      </select>
    </div>
  </div>
  
   <div class="layui-form-item">
    <div align="center">
    <div style="width: 100%; ">
      <button id="industryAction_update_do_submit" class="layui-btn" lay-submit="" lay-filter="demo1">确认修改</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1)">返回</button>
    </div>
    </div>
  </div> 
  </div>
  </form>    
</div>

</body>
<script src="./layui/layui.all.js" charset="utf-8"></script>

</html>