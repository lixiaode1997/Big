<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

<script type="text/javascript">
//检查宿舍是否存在
 function docheckroom(){
  //通过控件的id获取控件的值
  var shenqingroomno=document.getElementById("shenqingroomno").value;  
  var http=null;
  try{
   http=new XMLHttpRequest();
  }catch(ex){
   alert("对不起，您的浏览器不支持验证用户名字，请您升级 浏览器!");
   return;
  }
  //回调函数 call back
  //准备好，一会我发送完请求，服务器端响应了才回来调用你

  http.onreadystatechange=function(){
   if(http.readyState==4){
    var text=http.responseText;
    if(text==1){
     document.getElementById("lab").innerHTML="<font color='green'>该宿舍号存在</font>";
     document.getElementById("sub").disabled=false;
    }else{
     document.getElementById("lab").innerHTML="<font color='red'>该宿舍号不存在</font>";
    }
   }
  };
  
  //打开和服务器端的连接
  http.open("post","/DormitoryManagement/RoomServlet?method=docheck",true);
  //标注好是post请求
  http.setRequestHeader("content-type","application/x-www-form-urlencoded");
  //把请求发送到服务端
  http.send("shenqingroomno="+shenqingroomno);
 }
</script>
<script type="text/javascript">
function docheckname(form){
	if(form.shenqingpname.value==null || form.shenqingpname.value==""){
	alert("学生姓名不能为空");
	return false;
	}else if(form.shenqingstuno.value==null || form.shenqingstuno.value==""){
	alert("学生学号不能为空");
	return false;
	}else if(form.shenqingstuno.value.length!=8){
	alert("学生学号长度为8");
	return false;
	}else{
	return true;
	}
}
</script>
</head>

<body style="height: 100%">
<div class="layui-form">
  <form action="/DormitoryManagement/RoomServlet?method=selectsingle" method="post" name="form"
			id="form" onsubmit="return docheckname(this)">
  <div>
  <br>
  <h2 align="center"><strong>调换宿舍</strong></h2>
  <br>
  </div>

  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">申请人姓名</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" name="shenqingpname" id="shenqingpname" lay-verify="title" autocomplete="off" placeholder="请输入申请人姓名" class="layui-input">
    </div>
  </div>
  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">申请人学号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" name="shenqingstuno" id="shenqingstuno" lay-verify="title" autocomplete="off" placeholder="请输入申请人学号" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">调至宿舍</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" id="shenqingroomno" name="shenqingroomno" lay-verify="shenqingroomno" autocomplete="off" placeholder="请输入要调至的宿舍号" class="layui-input" onblur="docheckroom()"><label id="lab"></label><br>
    </div>
  </div>
  
   <div class="layui-form-item">
    <div align="center">
    <div style="width: 100%; ">
      <button id="sub" disabled="disabled" class="layui-btn" lay-submit="" lay-filter="demo1">确认提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <button type="reset" class="layui-btn layui-btn-primary" onclick="history.go(-1)">返回</button>
    </div>
    </div>
  </div> 
  </div>
   
  </form> 
   
</div>

</body>
<script src="/DormitoryManagement/WebRoot/layui/layui.all.js" charset="utf-8"></script>

</html>