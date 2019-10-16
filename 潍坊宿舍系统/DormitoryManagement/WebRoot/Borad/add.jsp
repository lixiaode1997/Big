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
	<div class="layui-form">
		<form action="BoradServlet?method=add" method="post" name="form"
			id="form" onsubmit="return checkInput(this)">
			<div>
				<br>
				<h2 align="center">
					<strong>添加公告</strong>
				</h2>
				<br>
			</div>

			<div align="center">
				<div class="layui-form-item" style="width: 550px; ">
					<label class="layui-form-label">公告内容</label>
					<div class="layui-input-block" style="width: 400px; ">
						<input type="text" name="boradcontent" id="stuname" lay-verify="title"
							autocomplete="off" placeholder="请输入内容" class="layui-input"
							>
					</div>
				</div>

				<input type="hidden" name="boradtime" id="leavetime" 
								lay-verify="title" autocomplete="off" placeholder="${time}"
								class="layui-input" value="${time}">
				
				
					<input type="hidden" value="${login.adminid}" name="boradperson">

					<div class="layui-form-item">
						<div align="center">
							<div style="width: 100%; ">
								<button class="layui-btn" lay-filter="demo1" type="submit">确认添加</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								<button type="reset" class="layui-btn layui-btn-primary"
									onclick="history.go(-1)">返回</button>
							</div>
						</div>
					</div>
				</div>
		</form>
		<script>
			function checkInput(form) {

				if (form.stuname.value == "") {
					alert(form.stuname.placeholder);
					//form.stuname.onfocus();
					return false;
				}
				
				if (form.stuno.value == "") {
					alert(form.stuno.placeholder);
					//form.stuno.onfocus();
					return false;
				} 
				if (form.stuno.value.length != 8) {
					alert("学号长度8位");
					//form.stuno.onfocus();
					return false;
				}
				if (form.backtime.value == "") {
					alert("选个回来时间");
					//form.backtime.onfocus();
					return false;
				}
			}
		</script>
	</div>

</body>
<script src="./layui/layui.all.js" charset="utf-8"></script>

</html>