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
		<!-- <div style="width: 200px;">
<form action="RecordServlet?method=importExcel" method="post" >
       <input type="file"  name="file_input" class="layui-input" style="height: 27px;"/>
       <button class="layui-btn" lay-submit="" lay-filter="demo1" style="width: 150px;" value="导入文件">导入文件</button>
</form>
</div> -->
		<div align="left" style="float:left">
			<a href="RecordServlet?method=time">
				<button class="layui-btn" lay-submit="" lay-filter="demo1"
					style="width: 150px;">添加出入记录</button>
			</a>

		</div>

		<div align="right">
			<form action="ExportServlet?method=record" method="post">

				<button class="layui-btn" lay-submit="" lay-filter="demo1"
					style="width: 150px;">导出记录表</button>
			</form>
		</div>
	</div>
	<div class="layui-form"style="height: 450px">

		<table class="layui-table">
			<colgroup>
				<col width="80">
				<col width="200">
				<col width="200">
				<col width="200">
				<col width="200">

				<col width="80">
			</colgroup>
			<thead align="center">
				<tr>
					<th>记录编号</th>
					<th>学生姓名</th>
					<th>学生学号</th>
					<th>离宿时间</th>
					<th>返校时间</th>
					<th>删除</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${recordlist}" var="record">
					<tr>
						<td>${record.recordid}</td>
						<td>${record.stuid.stuname}</td>
						<td>${record.stuid.stuno}</td>
						<td>${record.leavetime}</td>
						<td>${record.backtime}</td>
						<td><a
							href="RecordServlet?method=del&recordid=${record.recordid }">
								<img src="images/i_edit.gif" border="0" style="CURSOR: hand">
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
			</div>
<table align="center" style="width: 376px; ">
<tr>
   <td colspan="11" align="center">
    <a href="RecordServlet?method=all&page=1">首页</a>  
			<a href="RecordServlet?method=all&page=${page-1 }" onclick="javascript:return next0()">上一页</a> 
			<a href="RecordServlet?method=all&page=${page+1 }" onclick="javascript:return next()">下一页</a> 
			<a href="RecordServlet?method=all&page=${allpage }">尾页</a><form action="RecordServlet?method=all" method="post" onsubmit="return isBig()" style="width: 126px; ">
			<input type="text" name="page" id="inputType" style="width:40px">
			<input type="submit" value="跳转">
			</form>
     
   </td>
  </tr>
</table>
</body>
<script type="text/javascript">
	function next() {
		if (${page == allpage}) {
			alert("尾页");
			return false;
		}
	}
</script>
<script type="text/javascript">
	function next0() {
		if (${page == 1}) {
			alert("首页");
			return false;
		}
	}
</script>
<script type="text/javascript">
	function isBig() {
		var pageId = document.getElementById("inputType").value;
		var totalPage = ${allpage }
		if (pageId<1||pageId>totalPage) {
			alert("请重新输入");
			document.getElementById("inputType").value = "";
			return false;
		}
		return true;
	}
</script>
</html>