<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<link rel="stylesheet" href="./layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
<script type="text/javascript" src="<%=basePath %>/js/jquery.js"></script>

</head>
</script>

<script type="text/javascript">
	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义 
		var timenow = new Date().getTime();
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。 
		obj.src = "IdentifyingServlet?d=" + timenow;
		/* random.action */
	}

</script>
<script type="text/javascript">
if (top.location !== self.location) {
top.location=self.location;
}
</script>
<body style="height: 100%; background:url(kk.jpg);">
<div>
     <br></b><br></b><br></b><br></b><br></b><br></b><br></b>
<div style="padding-left: 390px">
<div class="layui-form" style="width: 750px; height: 450px;" >
  <form action="StudentServlet?method=login" method="post" id="form" height:450px">
  
  
  <div>
  
  <br><br>
  <h1 align="center" >宿舍管理系统</h1><br>
  <br>
  </div>

  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block" style="width: 400px;">
      <input id="name" type="text" name="loginName" lay-verify="title" autocomplete="off" placeholder="请输入名称" class="layui-input">   
    </div>     
  </div>
  </div>
  
  <div align="center">
  <div class="layui-form-item" style="width: 550px; ">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-block" style="width: 400px; ">
      <input id="pwd" type="text" name="loginPwd" lay-verify="title" autocomplete="off" placeholder="请输入密码 " class="layui-input">
    </div>
  </div>
  </div>
	<div align="center" >
	<div  class="layui-form-item" style="width: 550px;">
		<input type="radio" name="type" value="student" checked="checked">学生
		<input type="radio" name="type" value="admin" >宿管
		<input type="radio" name="type" value="super" >超管
	</div>
  </div>
   <div class="layui-form-item">
    <div align="center">
    <div style="width: 100%; ">    	
				<table>
                  <tr>
                  	<form action="StudentServlet?method=login" method="post">
	                 
	                  <td height="100" colspan="2" class="top_hui_text"><input type="text" name="random" lay-verify="title" autocomplete="off" placeholder="请输入右侧验证码 " />
	                  <img style="font-size:10px;" src="${pageContext.request.contextPath}/IdentifyingServlet" onclick="changeValidateCode(this)" title="点击图片刷新验证码"  /></td>	          		 
                	</form>
               </tr>
               </table>
      <button id="su" class="layui-btn" lay-submit="" lay-filter="demo1">登录</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button> 
    </div>
    </div>
  </div> 
  </form> 
</div>
</div>
</div>
</body>
<script src="layui/layui.all.js" charset="utf-8"></script>
<script type="text/javascript">
	/* 不加#是通过标签名称取，加#是通过id取得 */
	
	
	$(document).ready(function(){
	
		$('form').submit(function(){
			var name=$('#name').val();
			var pwd=$('#pwd').val();
			if(name==""||name==null){
			alert("用户名不能为空");
			return false;
			}else if(pwd==""||pwd==null){
			alert("密码不能为空");
			return false;
			}else{
			return true;
			}
		});
	});
	
	
	
</script>
</html>