<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="./layui/css/layui.css" media="all">
<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

<script type="text/javascript" src="./layui/js/jquery-3.3.1.min.js"></script>
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

	<ul class="layui-nav layui-nav-tree layui-nav-side"
		lay-filter="treenav" style="margin-top: 2px">
		<li class="layui-nav-item"><a href="javascript:;"
			class="site-url">查询信息</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="javascript:;" class="site-url" data-id="1"
						data-title="查询个人信息"
						data-url="StudentServlet?method=getById&id=${login.stuid}" />查询个人信息</a>
				</dd>
				<dd>
					<a href="javascript:;" class="site-url" data-id="2"
						data-title="查询宿舍信息"
						data-url="StudentMidServlet?method=getRoomBySid&id=${login.stuid}" />查询宿舍信息</a>
				</dd>
			</dl></li>

		<li class="layui-nav-item"><a href="javascript:;"
			class="site-url">申请信息</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="javascript:;" class="site-url" data-id="3"
						data-title="申请报修"
						data-url="NoticeServlet?method=getAll&id=${login.stuid}" />申请报修</a>
				</dd>
			</dl></li>

		<li class="layui-nav-item"><a href="javascript:;"
			class="site-url">缴费管理</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="javascript:;" class="site-url" data-id="4"
						data-title="查看缴费信息"
						data-url="CostServlet?method=showstudentcost&id=${login.stuid }" />查看缴费信息</a>
				</dd>
			</dl></li>

		<li class="layui-nav-item"><a href="javascript:;"
			class="site-url">公告管理</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="javascript:;" class="site-url" data-id="34"
						data-title="查看公告信息" data-url="BoradServlet?method=getAll2" />查看公告信息</a>
				</dd>
			</dl></li>


	</ul>
	<div class="layui-tab layui-tab-brief" lay-filter="contentnav"
		lay-allowClose="true" style="margin-left: 200px; margin-top: 0;">
		<ul class="layui-tab-title">
			<li class="layui-this">学生系统</li>
		</ul>
		<ul class="layui-bg-green rightmenu"
			style="display: none; position: absolute;">
			<li data-type="closethis">关闭当前</li>
			<li data-type="closeall">关闭所有</li>
		</ul>
		<div class="layui-tab-content" style="padding: 0;">
			<div class="layui-tab-item layui-show">
				<div
					style="height: 100%; width: 80%; margin-top: 30px; margin-left: 30px">
					<div class="layui-form">
						<div class="layui-form-item" align="left"></div>
						<table class="layui-table">
							<colgroup>
								<col width="100">
								<col width="100">
								<col width="100">
								<col width="150">
								<col width="150">
								<col width="150">
								<col width="150">
								<col width="100">
							</colgroup>
							<thead align="center">
								<tr>
									<th>公告编号</th>
									<th>公告内容</th>
									<th>公告时间</th>
									<th>公告人</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${list}" var="li">
									<tr>
										<td valign="middle">${li.boradId}</td>
										<td valign="middle">${li.boradCOntent}</td>
										<td valign="middle">${li.boradDate}</td>
										<td valign="middle">${li.admin.adminname}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>


				<!--  <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="frozen">冻结</a> -->
				<script type="text/html" id="barDemo"></script>
			</div>
		</div>
	</div>

	<script src="./layui/layui.all.js" charset="utf-8"></script>


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