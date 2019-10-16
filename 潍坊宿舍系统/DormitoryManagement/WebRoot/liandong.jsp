<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	//out.println(path);
	//request.getContextPath()可以返回当前项目的名字，我的：/shengshiqu
	//request.getServletContext().setAttribute("path", path);
%>
<!-- 这个语句是用来拼装当前网页的相对路径的。 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地址选择</title>
</head>
<body>
	<div style="margin: 100px auto; border: 2px soild">
		<label>性别：</label> <select id="province"
			style="width: 120px;height: 30px;border: 1px solid red; border-radius: 4px;">
		</select> <label>系别：</label> <select id="city"
			style="width: 120px;height: 30px;border: 1px solid red; border-radius: 4px;"></select>
		<!-- <label>区（县）：</label> <select id="area"
			style="width: 120px;height: 30px;border: 1px solid red; border-radius: 4px;"></select> -->
	</div>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		alert("data");
		getprovince();//网页加载完成时自动执行 此方法 
		$("#province").change(function() {//表示id="province"的标签中的值改变时调用此方法
			getcity($(this).val());
		});
		$("#city").change(function() {//以上同理
			getarea($(this).val());
		});
		function getprovince() {
			//alert("data");//使用ajax获取后台返回的数据
			$.ajax({
				type : "post",//规定请求的类型（GET 或 POST）。
				url : "LianDongServlet",//规定发送请求的 URL。默认是当前页面。
				async : true, //表示请求是否异步处理。默认是 true。
				datatype : "json",//默认类型也为json格式
				success : function(data) {
					alert(data);
					//data:代表返回的数据
					//alert(data)
					$.each(data, function(n, item) {
						var option = $("<option></option>");//设置option标签
						option.val(item.id);//设置option标签中的value值为数据库表中id字段中的值
						option.text(item.name);//设置option标签中的text值为数据库表中name字段中的值
						option.appendTo($("#province"));//将 option添加到id为province标签的结尾（仍然在内部
					});
					//alert($("#province").val());
					getcity($("#province").val());//调用getcity方法，并插入参数$("#province").val()(即：id字段中的值)
				}
			});
		}
		function getcity(id) {
			$("#city").empty();//清空之前的数据
			$.ajax({
				type : "get",
				url : "LianDongServlet?id=" + id,
				data : {
					flag : "city"//向服务器发送flag：作为标志字段
				},
				success : function(data) {
					$.each(data, function(n, item) {
						var option = $("<option></option>");
						option.val(item.id);
						option.text(item.name);
						option.appendTo($("#city"));
					});
					//alert($("#city").val());
					getarea($("#city").val());
				}
			});
		}
		/* function getarea(id) {
			$("#area").empty();
			$.ajax({
				type : "get",
				url : "LianDongServlet?id=" + parseInt(id),//将数据转成int 类型
				data : {
					flag : "area"
				},
				success : function(data) {
					$.each(data, function(n, item) {
						var option = $("<option></option>");
						option.val(item.id);
						option.text(item.name);
						option.appendTo($("#area"));
					});
				}
			});
		}*/
	}); 
</script>
</html>
<!-- //省市联动源码连接：http://pan.baidu.com/s/1nuTQCLb
//sql数据库连接：http://pan.baidu.com/s/1qYqLdOK -->