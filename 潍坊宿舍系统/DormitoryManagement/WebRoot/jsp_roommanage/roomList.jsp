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
<script src="layui/layui.js"></script>
<script src="layui/layui.all.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>

<body style="height: 100%">
<div>
<br>
</div>
<div class="layui-form">
	<div class="layui-form-item" align="left">
    <a href="RoomServlet?method=getAllAdd">
    <button class="layui-btn" lay-submit="" lay-filter="demo1" style="width: 150px;">添加房间</button></a>
     <!--   <input type="text" name="build" lay-verify="title" autocomplete="off" placeholder="请输入楼号查询" class="layui-input"">
       <a href="RoomServlet?method=getAllByBname"> -->
       
       <form action="RoomServlet?method=getAllByBname" method="post">
        <input style="width: 200px" type="text" name="build" lay-verify="title" autocomplete="off" placeholder="请输入楼号查询" class="layui-input">
        <input class="layui-btn" style="height: 32px" type="submit" value="搜索"> ${name}
       </form>
       
   <%--  
    	<label>通过楼号查询</label>
    	<select name="buildname" lay-filter="aihao">
        <c:forEach items="${all2}" var="aaa">
              <option value="RoomServlet?method=getAllByBname&buildid=${aaa.buildid}" onclick="location='index.jsp'">${aaa.buildname}</option>
          </c:forEach>
      </select> --%>
    	

       <form action="RoomServlet?method=getRoomByRoomNum" method="post">
        <input style="width: 200px" type="text" name="rNum" lay-verify="title" autocomplete="off" placeholder="请输入房间人数查询" class="layui-input"">
        <input class="layui-btn" style="height: 32px" type="submit" value="搜索">${name1}
       </form>
       
         </a>
    </div>
    
      
    </div>
	<table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
      <col width="150">
      <col width="150">
      <col width="150">
     <%--  <col width="150">
      <col width="100"> --%>
    </colgroup>
    <thead align="center">
      <tr>
        <th>房间号</th>
        <th>床号</th>
        <th>房间人数</th>
        <th>楼号</th>
        <!-- <th></th>
        <th></th> -->
        
       
      </tr> 
    </thead>
    
    <tbody>
    <c:forEach items="${all}" var="item">
      <tr>
        <td>${item.roomno}</td>
        <td>${item.roombed}</td>
        <td>${item.roomnum}</td>
        <td>${item.buildid.buildname}</td>
        
       <%--  <td><a href="RoomServlet?method=del&id=${item.roomid}">删除</a></td>   --%>
        <%-- <td><a href="RoomServlet?method=getByID&id=${item.roomid }">修改</a></td> --%>        
        </td> 
      </tr>
      </c:forEach>
    </tbody>
  </table>
  </div>
</div>
<a class="layui-btn" href="ExportServlet?method=room">导出宿舍表</a>
<script src="./layui/layui.all.js" charset="utf-8"></script>
</body>
</html>