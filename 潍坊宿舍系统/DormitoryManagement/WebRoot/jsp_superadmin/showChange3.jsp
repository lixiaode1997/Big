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
<script>
			function checkInput(form) {

				if (form.shenqingroomno.value == form.tiaohuanroomno.value) {
					alert("同一宿舍，不予调换");
					return false;
				}else{
				alert("调换成功！");
				}
			}
		</script>
</head>

<body style="height: 100%">
<div>
<br>
</div>
<div class="layui-form">
<form action="RoomServlet?method=change4" method="post" name="form"
			id="form" onsubmit="return checkInput(this)">
	<div style="padding-left: 15px"><h3><stronge>宿舍信息</stronge></h3></div>
	<table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
    </colgroup>
    <thead align="center">
      <tr>
        <th>原宿舍楼</th>
        <th>原宿舍号</th>
      </tr> 
    </thead>
    
    <tbody>
      <tr>
        <td>${oldroom.buildid.buildname}</td>
        <td><input type="hidden" id="shenqingroomno" name="shenqingroomno" value="${oldroom.roomno}">${oldroom.roomno}</td>
        <td><input type="hidden" id="shenqingroomnum" name="shenqingroomnum" value="${oldroom.roomnum}"> ${oldroom.roomnum }</td>
      </tr>
    </tbody>
  </table>
  <div style="padding-left: 15px"><h3><stronge>学生信息</stronge></h3></div>
  <table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
      <col width="100">
      <col width="100">
    </colgroup>
    <thead align="center">
      <tr>
        <th>学生姓名</th>
        <th>学生学号</th>
        <th>学生性别</th>
        <th>学生学制</th>
        <th>学生系别</th>
      </tr> 
    </thead>
    
    <tbody>
      <c:forEach items="${oldstulist}" var="list">
      <tr>
        <td>${list.stuname}</td>
        <td>${list.stuno}</td>
        <td>
            <c:if test="${list.stusex==0}">男</c:if>
            <c:if test="${list.stusex==1}">女</c:if>
        </td>
        <td>${list.stuyear}年</td>
        <td>${list.studept}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <div style="padding-left: 15px"><h3><stronge>宿舍信息</stronge></h3></div>
  <table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
    </colgroup>
    <thead align="center">
      <tr>
        <th>新宿舍楼</th>
        <th>新宿舍号</th>
      </tr> 
    </thead>
    
    <tbody>
      <tr>
        <td>${newroom.buildid.buildname}</td>
        <td><input type="hidden" id="tiaohuanroomno" name="tiaohuanroomno" value="${newroom.roomno}">${newroom.roomno}</td>
      </tr>
    </tbody>
    </table>
    <div style="padding-left: 15px"><h3><stronge>学生信息</stronge></h3></div>
    <table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
      <col width="100">
      <col width="100">
    </colgroup>
    <thead align="center">
      <tr>
        <th>学生姓名</th>
        <th>学生学号</th>
        <th>学生性别</th>
        <th>学生学制</th>
        <th>学生系别</th>
      </tr> 
    </thead>
    <tbody>
      <c:forEach items="${newstulist}" var="list">
      <tr>
        <td>${list.stuname}</td>
        <td>${list.stuno}</td>
        <td>
            <c:if test="${list.stusex==0}">男</c:if>
            <c:if test="${list.stusex==1}">女</c:if>
        </td>
        <td>${list.stuyear}年</td>
        <td>${list.studept}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <div class="layui-form-item">
						<div align="center">
							<div style="width: 100%; ">
								<button class="layui-btn" lay-filter="demo1" type="submit">确认调换</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								<button type="reset" class="layui-btn layui-btn-primary"
									onclick="history.go(-1)">返回</button>
							</div>
						</div>
					</div>
 </form>
  </div>
</div>

</body>
</html>