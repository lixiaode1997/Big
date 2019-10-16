<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="<%=basePath%>/layui/css/layui.css"
	media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

<script type="text/javascript"
	src="<%=basePath%>/layui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script type="text/javascript">
	$(function() {
	
	  $.ajax({
	  
	    
					type : "POST",
					url : "${pageContext.request.contextPath }/TuServlet2",
					dataType: "json",
					success : function(jsonstr) {
					

		echarts.init($("#main")[0]).setOption({
			backgroundColor : '#2c343c',

			title : {
				text : '宿舍楼学生分布图',
				left : 'center',
				top : 20,
				textStyle : {
					color : '#ccc'
				}
			},

			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},

			
			series : [ {
				name : '显示数据',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '50%' ],
				data : jsonstr.data.sort(function(a, b) {
					return a.value - b.value;
				}),
				roseType : 'radius',
				label : {
					normal : {
						textStyle : {
							color : '#DBDBDB'
						}
					}
				},
				labelLine : {
					normal : {
						lineStyle : {
							color : 'rgba(255, 255, 255, 0.3)'
						},
						smooth : 0.2,
						length : 10,
						length2 : 20
					}
				},
				itemStyle : {
					normal : {
						color : '#53868B',
						shadowBlur : 200,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				},

				animationType : 'scale',
				animationEasing : 'elasticOut',
				animationDelay : function(idx) {
					return Math.random() * 200;
				}
			} ]
		});
		},error:function(data) {
				        alert("error");
			     }
      });
	});
</script>
	
<style type="text/css">
.rightmenu {
	font-size: 12px;
	padding: 5px 10px;
	border-radius: 2px;
}

.rightmenu li {
	line-height: 20px;
	cursor: pointer;
}

ul.layui-tab-title li:first-child i {
	display: none;
}
</style>



<body>


<ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="treenav" style="margin-top: 2px">

		<li class="layui-nav-item"><a href="javascript:;" class="site-url" data-id="9" data-title="查询个人信息" data-url="AdminServlet?method=showAdmin&adminid=${login.adminid }"/>查询个人信息</a>
		</li>
		
    	<li class="layui-nav-item"><a href="javascript:;" class="site-url">宿舍管理</a>
    		<dl class="layui-nav-child">
    			<dd><a href="javascript:;" class="site-url" data-id="8" data-title="查询宿舍" data-url="RoomServlet?method=getAllRoom"/>查询宿舍</a></dd>
    		</dl>
    	</li>
    	
   	 	<li class="layui-nav-item"><a href="javascript:;" class="site-url">学生管理</a>
   			 <dl class="layui-nav-child">
    		<a href="javascript:;" class="site-url" />学生入住</a>
    		<dl class="layui-nav-child"> 
    		
    		<li><a href="javascript:;" class="site-url" data-id="11" data-title="单人入住" data-url="StudentServlet?method=getWeiRuZhuStudent"/>单人入住</a></li>
    		<li><a href="javascript:;" class="site-url" data-id="11" data-title="批量入住" data-url="StudentServlet?method=getWeiRuZhuStudent2">批量入住</a></li>
    		</dl>    		
    	
    	<dd>
    	
    	<a href="javascript:;" class="site-url" />学生退宿</a>
    		<dl class="layui-nav-child">
    		<li><a href="javascript:;" class="site-url" data-id="11" data-title="单人退宿" data-url="StudentMidServlet?method=getAllRuzhu2&adminid=${login.adminid}"/>单人退宿</a></li>
    		<li><a href="javascript:;" class="site-url" data-id="11" data-title="批量退宿" data-url="StudentMidServlet?method=getAllRuZhu&adminid=${login.adminid}">批量退宿</a></li>
    		</dl>

    	</dd>
    		<dd><a href="javascript:;" class="site-url" data-id="1" data-title="出入登记" data-url="RecordServlet?method=all"/>出入登记</a></dd>
    		<dd><a href="javascript:;" class="site-url" />学生换宿</a>
    			<dl class="layui-nav-child">
                  	 <li><a href="javascript:;" class="site-url" data-id="4" data-title="单人换宿" data-url="jsp_superadmin/changeroom1.jsp">单人换宿</a></li>
                  	 <li><a href="javascript:;" class="site-url" data-id="2" data-title="双人互换" data-url="jsp_superadmin/changeroom2.jsp">双人互换</a></li>
                  	 <li><a href="javascript:;" class="site-url" data-id="3" data-title="宿舍换址" data-url="jsp_superadmin/changeroom3.jsp">宿舍换址</a></li>
                </dl>
    		</dd>
    		
    		<dd><a href="javascript:;" class="site-url" data-id="1" data-title="学生查询" data-url="StudentServlet?method=getAll"/>学生查询</a></dd>
    		<dd><a href="javascript:;" class="site-url" data-id="6" data-title="审批报修" data-url="NoticeServlet?method=adminGetAll&flag=1"/>审批报修</a></dd>
    	</dl>
    </li>
    
    <li class="layui-nav-item"><a href="javascript:;" class="site-url">公告管理</a>
    		<dl class="layui-nav-child">
    			<dd><a href="javascript:;" class="site-url" data-id="45" data-title="查询公告" data-url="BoradServlet?method=getAll"/>查询公告</a></dd>
    		</dl>
    	</li>
    </ul>

	<div class="layui-tab layui-tab-brief" lay-filter="contentnav" lay-allowClose="true" style="margin-left:200px;margin-top:0;">
		<ul class="layui-tab-title">

		 <li class="layui-this">宿管</li>

		</ul>
		
		<ul class="layui-bg-green rightmenu"
			style="display: none;position: absolute;">
			<li data-type="closethis">关闭当前</li>
			<li data-type="closeall">关闭所有</li>
		</ul>
		<div class="layui-tab-content" style="padding:0;">
			<div class="layui-tab-item layui-show">
				<div
					style="height: 100%; width: 80%; margin-top: 30px;margin-left: 30px">
					<p style="font-size: 30px;color: #53868B">欢迎使用北京五道口幼儿园宿舍管理系统</p>
					<div id="main" style="width: 600px;height:400px;"></div>
				</div>

				<script type="text/html" id="barDemo"></script>
			</div>
		</div>
	</div>

	<script src="<%=basePath%>./layui/layui.all.js" charset="utf-8"></script>


	<script type="text/javascript">
		layui.use('element', function() {
			var element = layui.element;
			var active = {
				tabAdd : function(url, id, name) {
					element.tabAdd('contentnav', {
						title : name,
						content : '<iframe data-frameid="' + id
								+ '" scrolling="auto" frameborder="0" src="'
								+ url + '" style="width:100%;"></iframe>',
						id : id
					});
					rightMenu();
					iframeWH();
				},
				tabChange : function(id) {
					element.tabChange('contentnav', id);
				},
				tabDelete : function(id) {
					element.tabDelete('contentnav', id);
				},
				tabDeleteAll : function(ids) {
					$.each(ids, function(index, item) {
						element.tabDelete('contentnav', item);
					});
				}
			};
			$(".site-url").on(
					'click',
					function() {
						var nav = $(this);
						var length = $("ul.layui-tab-title li").length;
						if (length <= 0) {
							active.tabAdd(nav.attr("data-url"), nav
									.attr("data-id"), nav.attr("data-title"));
						} else {
							var isData = false;
							$.each($("ul.layui-tab-title li"), function() {
								if ($(this).attr("lay-id") == nav
										.attr("data-id")) {
									isData = true;
								}
							});
							if (isData == false) {
								active.tabAdd(nav.attr("data-url"), nav
										.attr("data-id"), nav
										.attr("data-title"));
							}
							active.tabChange(nav.attr("data-id"));
						}
					});
			function rightMenu() {
				//右击弹出
				$(".layui-tab-title li").on('contextmenu', function(e) {
					var menu = $(".rightmenu");
					menu.find("li").attr('data-id', $(this).attr("lay-id"));
					l = e.clientX;
					t = e.clientY;
					menu.css({
						left : l,
						top : t
					}).show();
					return false;
				});
				//左键点击隐藏
				$("body,.layui-tab-title li").click(function() {
					$(".rightmenu").hide();
				});
			}
			$(".rightmenu li").click(function() {
				if ($(this).attr("data-type") == "closethis") {
					active.tabDelete($(this).attr("data-id"));
				} else if ($(this).attr("data-type") == "closeall") {
					var tabtitle = $(".layui-tab-title li");
					var ids = new Array();
					tabtitle.each(function(i) {
						ids.push($(this).attr("lay-id"));
					});
					//如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
					active.tabDeleteAll(ids);
				}
				$('.rightmenu').hide(); //最后再隐藏右键菜单
			});
			function iframeWH() {
				var H = $(window).height() - 80;
				$("iframe").css("height", H + "px");
			}
			$(window).resize(function() {
				iframeWH();
			});
		});
	</script>

	<script>
		layui
				.use(
						'table',
						function() {
							var table = layui.table;
							//监听工具条
							table
									.on(
											'tool(demo)',
											function(obj) {
												var data = obj.data;
												if (obj.event === 'frozen') {
													layer
															.confirm(
																	'确定冻结？',
																	function(
																			index) {
																		console
																				.log(obj);
																		console
																				.log(data);
																		$
																				.ajax({
																					url : "frozenUserServlet",
																					type : "POST",
																					data : {
																						'uid' : data.uid
																					},
																					dataType : "text",
																					success : function(
																							data) {
																						if (data == null) {
																							layer
																									.msg(
																											"冻结失败",
																											{
																												icon : 5
																											});
																						} else {
																							layer
																									.close(index);
																							location
																									.reload();
																							layer
																									.msg(
																											"冻结成功",
																											{
																												icon : 6
																											});
																						}
																					},
																					error : function() {
																						layer
																								.msg(
																										"冻结失败"
																												+ date.bid,
																										{
																											icon : 5
																										});
																					},
																				});
																	});
												} else if (obj.event === 'edit') {
													layer
															.open({
																shadeClose : true,
																shade : false,
																maxmin : true, //开启最大化最小化按钮
																area : [
																		'500px',
																		'550px' ],
																type : 1,
																closeBtn : false,
																shift : 25,
																content : '<blockquote class="layui-elem-quote layui-text">注意事项: 请填写相关的数据信息! </blockquote>'
																		+ '<form class="layui-form"> '
																		+

																		'<div class="layui-form-item">'
																		+

																		'<input id="uid"  type="hidden" required lay-verify="required" class="layui-input" value="'+data.uid+'" >'
																		+ '<div class="layui-inline">'
																		+ '<label class="layui-form-label">登录用户名</label>'
																		+ '<div class="layui-input-inline">'
																		+ '<input id="uloginname" disabled="disabled"  type="text" required lay-verify="required" class="layui-input" value="'+data.uloginname+'" >'
																		+ '</div></div>'
																		+

																		'</div>'
																		+ '<div class="layui-form-item">'
																		+

																		'<div class="layui-inline">'
																		+ '<label class="layui-form-label">用户昵称：</label>'
																		+ '<div class="layui-input-inline">'
																		+ '<input id="unickname"  type="text" required lay-verify="required" class="layui-input" value="'+data.unickname+'" >'
																		+ '</div></div>'
																		+

																		'</div>'
																		+

																		'<div class="layui-form-item">'
																		+

																		'<div class="layui-inline">'
																		+ '<label class="layui-form-label">密码：</label>'
																		+ '<div class="layui-input-inline">'
																		+ '<input id="upwd" type="text"  required lay-verify="required" class="layui-input" value="'+data.upwd+'" >'
																		+ '</div></div>'
																		+

																		'</div>'
																		+

																		'<div class="layui-form-item">'
																		+

																		'<div class="layui-inline">'
																		+ '<label class="layui-form-label">状态</label>'
																		+ '<div class="layui-input-inline">'
																		+ '<select id="ufrozen" lay-filter="sex">'
																		+ '<option value="0" selected="">正常</option>'
																		+ '<option value="1">冻结</option>'
																		+ '</select>'
																		+ '</div></div>'
																		+

																		'</div>'
																		+

																		'</form>',

																btnAlign : 'c',
																btn : [ '确定',
																		'取消' ],
																yes : function(
																		index,
																		layero) {

																	var formData = {
																		uid : $(
																				"#uid")
																				.val(),
																		unickname : $(
																				"#unickname")
																				.val(),
																		upwd : $(
																				"#upwd")
																				.val(),
																		ufrozen : $(
																				"#ufrozen")
																				.val(),
																	};

																	console
																			.log(formData);
																	$
																			.post(
																					'zXLUserupdServlet',
																					formData,
																					function(
																							data) {

																						console
																								.log(window.location.href);
																						//判断是否发送成功
																						if (data.code == 200) {
																							layer
																									.msg(
																											"修改失败",
																											{
																												icon : 5
																											});
																						} else {
																							layer
																									.close(index);
																							layer
																									.msg(
																											"修改成功",
																											{
																												icon : 6
																											});
																						}
																					})
																},
																btn2 : function(
																		index,
																		layero) {
																	layer
																			.msg("取消");
																},
																cancel : function() {
																	layer
																			.msg("关闭窗口");
																},
																success : function(
																		data) {
																	layui
																			.use(
																					'form',
																					function() {
																						var form = layui.form; //只有执行了这一步，部分表单元素才会修饰成功
																						form
																								.render();
																					});
																},
																end : function() {
																	location
																			.reload();
																}
															});
												}

											});

						});
	</script>
</body>
</html>