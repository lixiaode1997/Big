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
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
<script type="text/javascript">
function docheckname(form){
	if(form.oldbuildno.value==null || form.oldbuildno.value==""){
	alert("楼号不能为空");
	return false;
	}else if(form.oldroomno.value==null || form.oldroomno.value==""){
	alert("宿舍号不能为空");
	return false;
	}else if(form.newbuildno.value==null || form.newbuildno.value==""){
	alert("楼号不能为空");
	return false;
	}else if(form.newroomno.value==null || form.newroomno.value==""){
	alert("宿舍号不能为空");
	return false;
	}else{
	return true;
	}
}
</script>
<script type="text/javascript">
//检查宿舍是否存在
 function docheckroom(){
  //通过控件的id获取控件的值
  var shenqingroomno=document.getElementById("oldroomno").value;  
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
     document.getElementById("sub").disabled=true;
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
//检查宿舍是否存在
 function docheckroom1(){
  //通过控件的id获取控件的值
  var shenqingroomno=document.getElementById("newroomno").value;  
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
     document.getElementById("lab1").innerHTML="<font color='green'>该宿舍号存在</font>";
     document.getElementById("sub").disabled=false;
    }else{
     document.getElementById("lab1").innerHTML="<font color='red'>该宿舍号不存在</font>";
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
</head>

<body style="height: 100%">
<div class="layui-form">
  <form action="/DormitoryManagement/RoomServlet?method=check2" method="post" onsubmit="return docheckname(this)">
  <div>
  <br>
  <h2 align="center"><strong>调换宿舍</strong></h2>
  <br>
  </div>

  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">原楼号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" id="oldbuildno" name="oldbuildno" lay-verify="title" autocomplete="off" placeholder="请输入原住楼号" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">原宿舍号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" id="oldroomno" name="oldroomno" lay-verify="title" autocomplete="off" placeholder="请输入原住宿舍号" class="layui-input" onblur="docheckroom()"><label id="lab"></label>
    </div>
  </div>
  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">新楼号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" id="newbuildno" name="newbuildno" lay-verify="title" autocomplete="off" placeholder="请输入要调换到的楼号" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">新宿舍号</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input type="text" id="newroomno" name="newroomno" lay-verify="title" autocomplete="off" placeholder="请输入要调换到的宿舍号" class="layui-input" onblur="docheckroom1()"><label id="lab1"></label><br>
    </div>
  </div>
  
   <div class="layui-form-item">
    <div align="center">
    <div style="width: 100%; ">
      <button class="layui-btn" id="sub" disabled="disabled" lay-submit="" lay-filter="demo1">确认提交</button>
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