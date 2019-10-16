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

				if (form.shenqingroom.value == form.tiaohuanroom.value) {
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
<form action="RoomServlet?method=change" method="post" name="form"
			id="form" onsubmit="return checkInput(this)">
	<table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
    </colgroup>
    <thead align="center">
      <tr>
        <th>申请人姓名</th>
        <th>申请人学号</th>
        <th>原住宿舍</th>
      </tr> 
    </thead>
    
    <tbody>
      <tr>
        <td>${shenqingmid.stuid.stuname}</td>
        <td><input type="hidden" id="shenqingstuno" name="shenqingstuno" value="${shenqingmid.stuid.stuno}">${shenqingmid.stuid.stuno}</td>
        <td><input type="hidden" id="shenqingroom" name="shenqingroom" value="${shenqingmid.roomid.roomno}"> ${shenqingmid.roomid.roomno}</td>
      </tr>
    </tbody>
  </table>
  <table class="layui-table">
    <colgroup>
      <col width="100">
      <col width="100">
      <col width="100">
    </colgroup>
    <thead align="center">
      <tr>
        <th>调换人姓名</th>
        <th>调换人学号</th>
        <th>原住宿舍</th>
      </tr> 
    </thead>
    
    <tbody>
      <tr>
        <td>${tiaohuanmid.stuid.stuno}</td>
        <td> <input id="tiaohuanstuno" type="hidden" name="tiaohuanstuno"  value="${tiaohuanmid.stuid.stuno}">${tiaohuanmid.stuid.stuno}</td>
        <td> <input id="tiaohuanroom" type="hidden" name="tiaohuanroom"  value="${tiaohuanmid.roomid.roomno}">${tiaohuanmid.roomid.roomno}</td>
      </tr>
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