Array.prototype.indexOf = Array.prototype.indexOf || function(el) {
	for (var i = 0, n = this.length; i < n; i++) {
		if (this[i] === el) {
			return i
		}
	}
	return -1
};
$(function() {
	$("#posExp dl").hover(function() {
		$(this).toggleClass("bgColor")
	});
	$("#filter .itemInfo").mouseover(function() {
		$(this).find(".infoList").show();
		$(this).find("#divJobCate").show()
	});
	$("#filter .itemInfo").mouseout(function(ev) {
		var oEvent = ev || event;
		var to = oEvent.toElement || oEvent.relatedTarget;
		var oDiv = $(this).get(0);
		if (isChild(oDiv, to)) {
			return
		}
		$(this).find(".infoList").hide();
		$(this).find("#divJobCate").hide()
	});

	function isChild(oParent, obj) {
		while (obj) {
			if (obj == oParent) {
				return true
			}
			obj = obj.parentNode
		}
		return false
	}
	$("#selItem .loca_item").hover(function() {
		$("#urlc").addClass("showline");
		$(".searInfo").css("paddingBottom", "22px")
	}, function() {
		$("#urlc").removeClass("showline");
		$(".searInfo").css("paddingBottom", "24px")
	});
	$("#filter .infoList li").click(function() {
		var selData = $(this).html() + "<b></b>";
		var obj = $(this).parents(".infoList").prev();
		var defaultData = "<span>" + obj.attr("default") + "</span>" + "<b></b>";
		if ($(this).find("a").text() != "不限") {
			obj.html(selData)
		} else {
			obj.html(defaultData)
		}
		$(this).parents(".infoList").hide();
		return false
	});
	$("#filter .itemT").click(function() {
		return false
	});
	$("#moreTypeSel").bind("click", function() {
		$(this).find("i").toggleClass("dTopArrow1");
		$("#selItem").toggle()
	});
	if ($("#tabsbar").find(".changeHref").length > 0) {
		var obja = $("#tabsbar").find(".changeHref");
		for (var i = 0, lena = obja.length; i < lena; i++) {
			var newHref = "http://" + city_listname + ".58.com/" + obja.eq(i).attr("href").replace("/", "");
			obja.eq(i).attr("href", newHref)
		}
	}
	$("#sidebar-right ul").hide();
	$("#sidebar-right ul").eq(0).show();
	$("#sidebar-left li").hover(function() {
		var $this = $(this);
		var ulObj = $("#sidebar-right ul").eq($this.index());
		hoverTimer = setTimeout(function() {
			ulObj.show().siblings().hide();
			$this.addClass("curSel").siblings().removeClass("curSel")
		}, 200)
	}, function() {
		clearTimeout(hoverTimer)
	});
	var leibie = $(".jobcatebox");
	var jobcateHtml = "<tbody>";
	var trsHtml = "";
	for (var i = 0, leni = ____catelist.length; i < leni; ++i) {
		var cate = ____catelist[i];
		var categories = cate.cates;
		var trHtml = "<tr><th>" + cate.category + '</th><td><ul class="jobcatelist">';
		var lis = "";
		for (var j = 0, lenj = categories.length; j < lenj; ++j) {
			var links = "";
			var category = categories[j];
			var seCates = category.comms_getcatelist;
			var links = '<div class="subcate"><a href="/' + category.listname + '/" p="' + category.dsid + '" leibie="' +
				category.listname + '">全部</a>';
			for (var e = 0, lene = seCates.length; e < lene; ++e) {
				var d = seCates[e];
				if (d.isVisible) {
					links += '<a href="/' + d.catelist + '/" leibie="' + d.catelist + '">' + d.cateName + "</a>"
				}
			}
			links += "</div>";
			lis += '<li dsid="' + category.dsid + '"><p><span><a href="/' + category.listname + '/" leibie="' + category.listname +
				'">' + category.name + "</a></span></p>" + links + "</li>"
		}
		trHtml += lis + "</ul></td></tr>";
		trsHtml += trHtml
	}
	jobcateHtml += trsHtml + "</tbody>";
	leibie.html(jobcateHtml);
	$(".jobcatelist li").hover(function() {
		var div = $(this).find(".subcate");
		if ($(this).hasClass("selected")) {
			$(this).removeClass("selected");
			div.hide()
		} else {
			$(this).addClass("selected");
			div.show()
		}
	});
	$(".jobcatelist li a").click(function() {
		var obj = $("#divJobCate").prev();
		if ($(this).html() == "全部") {
			obj.html('<a href="' + $(this).attr("href") + '" leibie="' + $(this).attr("leibie") + '">' + $(this).parent().prev(
				"p").html() + "</a>" + "<b></b>")
		} else {
			obj.html('<a href="' + $(this).attr("href") + '" leibie="' + $(this).attr("leibie") + '">' + $(this).html() +
				"</a>" + "<b></b>")
		}
		$("#divJobCate").hide();
		return false
	});
	var dispid = ____json4fe.locallist.dispid;
	var cityname = ____json4fe.locallist.name;
	var listname = ____json4fe.locallist.listname;
	var proCap = "全" + cityname;
	$(".loca_item #urlc span").text(proCap);
	var url = "//api.58.com/comm/city/?api_type=json&api_pid=" + dispid + "&api_callback=?";
	$.getJSON(url, function(data) {
		var allList = "";
		var allcity = "";
		var links = "";
		var comms_getcitylist = data.comms_getcitylist;
		for (var i = 0, len = comms_getcitylist.length; i < len; ++i) {
			var d = comms_getcitylist[i];
			var citylist = d.listName;
			allcity = '<li><a href="/' + listname + '/" class="locasel" area="' + listname + '">全' + cityname + "</a>" +
				"</li>";
			links += '<li><a href="/' + d.citylist + '/" p="' + d.cityid + '" area="' + d.citylist + '">' + d.cityname +
				"</a></li>";
			allList = allcity + links
		}
		$("#selItem .distlist").html(allList);
		$("#selItem .distlist").find("li").click(function() {
			var obj = $(this).parents(".infoList").prev();
			obj.html($(this).html() + "<b></b>");
			$(this).parents(".infoList").hide();
			return false
		})
	});
	window.do_search_job = function() {
		var value = $("#keyword1").val(),
			url = "";
		if (value == "请输入职位名、公司名等关键字") {
			value = ""
		}
		url = location.href;
		var keyword = encodeURI(escape(encodeURIComponent(encodeURI(value))));
		var kn = "key=";
		var keypos = url.indexOf(kn);
		if (keypos > 0) {
			var keyend;
			if (url.indexOf("&", keypos) > 0) {
				keyend = url.indexOf("&", keypos)
			} else {
				keyend = url.length
			}
			url = url.replace(url.substring(keypos, keyend), kn + keyword)
		} else {
			url += (url.indexOf("?") > 0 ? "&" : "?") + kn + keyword
		}
		url = url.replace(/\/pn\d{1,}\//, "/").split("?")[1];
		var keyStatus = 0,
			urla = "",
			urlb = "",
			urlc = "",
			urld = "",
			urle = "",
			urlf = "",
			urlog = "";
		if (url != "") {
			url = "?" + url;
			keyStatus = 1
		} else {
			url = ""
		}
		if ($("#urla a").length > 0 && $(".itemT a").length == 1) {
			urla = $("#urla a").attr("leibie");
			urlog = urla
		} else if ($("#urla a").length > 0 && $("#urlc a").length > 0 && $(".itemT a").length == 2) {
			urla = $("#urla a").attr("leibie");
			urlog = urla
		} else if ($("#urla a").length < 1 && $("#urlc a").length > 0 && $(".itemT a").length == 1) {
			urla = "job"
		} else if ($(".itemT a").length < 1) {
			urla = "job"
		} else if ($("#urla a").length < 1) {
			urla = "job/"
		} else {
			urla = $("#urla a").attr("leibie") + "/";
			urlog = urla
		}
		if ($("#urlb a").length > 0) {
			urlb = $("#urlb a").attr("cln")
		} else {
			urlb = ""
		}
		if ($("#urlc a").length > 0 && $("#urlc a").attr("area") == listname) {
			urlc = ""
		} else if ($("#urlc a").length > 0) {
			urlc = $("#urlc a").attr("area") + "/"
		} else {
			urlc = ""
		}
		if ($("#urld a").length > 0 && url != "") {
			urld = $("#urld a").attr("data-value");
			url = url + "&"
		} else {
			urld = ""
		}
		if ($("#urle a").length > 0) {
			urle = $("#urle a").attr("exp")
		} else {
			urle = ""
		}
		if ($("#urlf a").length > 0) {
			urlf = $("#urlf a").attr("edu")
		} else {
			urlf = ""
		}
		url = urlc + urla + urlb + urle + urlf + url + urld;
		window.location.href = "http://" + listname + ".58.com/" + url;
		var logtext = "from=zhaopin_index_button_searchjob&" + "city=" + listname + "&" + "cate=" + urlog + "&" +
			"industry=" + urlb + "&" + "isfilled=" + keyStatus + "&" + "area=" + urlc + "&" + "salary=" + urld + "&" +
			"jobexperience=" + urle + "&" + "degree=" + urlf + "&";
		clickLog(logtext)
	};
	$("#searJob").click(function() {
		window.do_search_job()
	})
});
document.onkeydown = function(e) {
	var ev = document.all ? window.event : e;
	var value = $("#keyword1").val();
	if (ev.keyCode == 13 && $(".itemT").find("a").length > 0) {
		window.do_search_job()
	}
	if (ev.keyCode == 13 && value !== "请输入职位名、公司名等关键字" && value !== "") {
		window.do_search_job()
	}
};
$(function() {
	(function() {
		var city = ____json4fe.locallist.listname ? ____json4fe.locallist.listname : ____json4fe.locallist[0].listname;
		var cat1 = ____json4fe.catentry.listname ? ____json4fe.catentry.listname : ____json4fe.catentry[0].listname;
		try {
			var __userid = document.cookie.match(/UID=\d*/)[0].split("=")[1] || -1
		} catch (e) {
			var __userid = -1
		}
		var txt = '<div class="goTab" id="goTab"><ul>';
		txt += '<li class="gTop"><a href="javascript:;"><span>回到顶部</span><b></b></a></li>';
		txt +=
			"<style type=\"text/css\">.goTab{position:fixed; bottom:56px; right:10px;_position:absolute; z-index:999;}.goTab ul{margin:0;}.goTab li{position:relative; width:45px; height:45px; line-height:45px; margin-bottom:4px; font-size:14px; list-style:none;}.goTab li a{position:absolute; width:45px; height:45px; right:0;top:0; text-align:center; color:#fff !important; background-color:#ff923a; overflow:hidden;text-decoration: none; cursor:pointer; color:#fff;}.goTab li a:hover{background-color: #ff7709;}.goTab li b{background:url('//img.58cdn.com.cn/ui6/index/bg_goTab.png'); width:19px; height:23px; display:block; position:absolute; right:13px; top:11px;}.goTab li span{position: absolute; color:#fff; right:32px; width:92px;}.goTab .gTop b{background-position: 0 0;}.goTab .gPrev b{background-position: 0 -24px;}.goTab .gNext b{background-position: 0 -48px;}.goTab .gSuggest b{background-position: 0 -71px;}.goTab .gSuggest{margin-bottom:0;}</style>";
		$(document.body).append(txt);
		var $content = $("#goTab"),
			$top = $content.find(".gTop a"),
			$span = $content.find("span"),
			_wb = $span.width() + parseInt($span.css("right")),
			_bottom = parseInt($content.css("bottom")),
			_wh = $(window).height(),
			_ch = _wh - $content.height(),
			isIE = $.browser.msie && $.browser.version == "6.0";
		var st = function() {
			var sTop = $(window).scrollTop();
			if (isIE) {
				var maxTop = sTop + _ch - _bottom;
				$content.css("top", maxTop + "px")
			}
			sTop > _wh ? $top.show() : $top.hide()
		};
		st();
		$(window).bind("resize scroll", st);
		$top.click(function() {
			$(document).scrollTop(0);
			_gaq.push(["pageTracker._trackEvent", "index", "gotop_click", "/" + city + "/" + cat1 +
				"/index/gotopclick/returnbtn/"
			]);
			clickLog("from=index_gotopclick_returnbtn" + "&City=" + city + "&Cate=" + cat1 + "&LoginUid=" + __userid +
				"&rand=" + Math.random())
		});
		$top.hover(function() {
			$(this).stop(true).animate({
				width: _wb
			}, 300)
		}, function() {
			$(this).stop(true).animate({
				width: "100%"
			}, 300)
		})
	})()
});
var bjobList = {};
bjobList.setCookie = function(name, val) {
	var Days = 1;
	var exp = new Date;
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1e3);
	document.cookie = name + "=" + escape(val) + ";expires=" + exp.toGMTString()
};
bjobList.getCookie = function(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg)) return unescape(arr[2]);
	else return null
};
$(function() {
	var html = '<div class="bottom-bar" id="listBottomBar">' + '<a href="javascript:;" class="bottom-bar-close"></a>' +
		'<div class="content">' + '<span class="bottom-bar-txt">没有找到合适的工作？</span>' +
		'<a href="http://jianli.58.com/resumebase/" onClick=window.clickLog&&clickLog("from=zhaopin_job_JLshow_toCreate") target="_blank" class="bottom-bar-btn">1分钟留下信息</a>' +
		'<span class="bottom-bar-txt">我们会将你推荐给优秀企业！坐等好工作来找你！</span>' + "</div></div>";
	if (!document.getElementById("listBottomBar")) {
		$("body").append(html)
	}
	var isLogin = document.cookie.match(/UID=\d*/);
	if (isLogin) {
		isLogin = isLogin[0].split("=")[1] || ""
	}
	var isIE = !!window.ActiveXObject;
	var isIE6 = isIE && !window.XMLHttpRequest;
	var bottomBar = $(".bottom-bar");
	$(window).scroll(function() {
		var sTop = $(window).scrollTop() + $(window).height() - bottomBar.height() - 10;
		if (isIE6) {
			bottomBar.css({
				top: sTop
			})
		}
		if ($(".bottom-bar").css("display") !== "none") {
			return
		}
		if (!isLogin) {
			if (!bjobList.getCookie("jobBottomBar")) {
				$(".bottom-bar").slideDown("fast", function() {
					setTimeout(function() {
						clickLog("from=zhaopin_job_JLshow")
					}, 6e3)
				})
			}
		} else {
			if (!bjobList.getCookie("jobBottomBar")) {
				$.getJSON("//statisticszp.58.com/resume/getbyr?callback=?", function(data) {
					if (typeof data != "undefined" && data != null) {
						if (data.returnMessage == "success") {
							var count = eval("(" + data.entity + ")");
							if (count == 0) {
								$(".bottom-bar").slideDown("fast", function() {
									setTimeout(function() {
										clickLog("from=zhaopin_job_JLshow")
									}, 6e3)
								})
							}
						}
					}
				})
			}
		}
	});
	$(".bottom-bar-close").click(function() {
		$(".bottom-bar").slideUp();
		bjobList.setCookie("jobBottomBar", "1")
	});
	var list = $(".bottom-bar-btn");
	list.click(function() {
		bjobList.setCookie("jobBottomBar", "1")
	})
});
$(function() {
	var htmlStr =
		'<img style="position: absolute;bottom:6px;right: 2px;width: 324px;height: 22px;" src="//img.58cdn.com.cn/job/pc/full/common/0.1/400/400b.png" />';
	var cityArr = ["bj", "sh", "sz", "gz", "cd", "hrb", "zz", "tj", "wh", "cs", "cq", "sz", "fz", "hf", "hz", "nj", "sy",
		"cc", "qd", "jn", "dl", "sjz", "xm", "dg", "nn", "ty", "wx"
	];
	var aNodes = $(".tabsbar").find("a");
	if (____json4fe && ____json4fe.locallist && ____json4fe.locallist.listname) {
		var listname = ____json4fe.locallist.listname;
		if ($.inArray(listname, cityArr) != -1) {
			$(aNodes[aNodes.length - 1]).after(htmlStr)
		} else {
			var qudaohtmlStr =
				'<a style="position: absolute;bottom:4px;right: 2px;" href="http://buy.58.com/zhaopin?from=10044" target="_blank" onclick="clickLog(\'from=pc_zw_kszp\');"><img style="width: 324px;height: 22px;" src="//img.58cdn.com.cn/ztzp/2017/patch/xiaoguo2.png" /></a>';
			var qudaoCityArr = ["szkunshan", "nb", "fs", "yt", "longkou", "laizhou", "penglai", "ts", "qianan", "qz",
				"nananshi", "shishi", "jinjiangshi", "wz", "sx", "zhuji", "wf", "qingzhou", "shouguang", "gaomi", "dq", "zb",
				"nt", "rugao", "haimen", "qidong", "haian", "rudong", "tz", "linhai", "wenling", "yuhuan", "bd", "zhuozhou",
				"jining", "zoucheng", "cz", "jintan", "liyang", "xz", "pizhou", "xinyishi", "xa", "jx", "haining", "tongxiang",
				"linyi", "weihai", "rushan", "rongcheng", "as", "jh", "dongyang", "yongkang", "km", "luoyang", "hd", "wuan",
				"ny", "yancheng", "dongtai", "jianhu", "dy", "guangrao", "jm", "yz", "zj", "danyang", "yangzhong", "cangzhou",
				"renqiu", "nc", "mm", "ta", "xintai", "feicheng", "taizhou", "taixing", "jiangyan", "jingjiang", "xinghuashi",
				"jl", "zhangzhou", "longhai", "huizhou", "huidong", "boluo", "dz", "xt", "zs", "lf", "sanhe", "st", "zhanjiang",
				"bt", "hu", "yy", "huzhou", "changxing", "deqing", "anji", "yc", "yidou", "changde", "lc", "xf", "zaoyang", "zq",
				"zh", "hy", "jy", "zk", "xiangchengshi", "bz", "zouping", "lz", "zaozhuang", "tengzhou", "ha", "xc", "yuzhou",
				"xj", "hs", "pds", "ruzhou", "xx", "sq", "gl", "mianyang", "zmd", "qhd", "zhuzhou", "liling", "ay", "hshi", "gy",
				"shiyan", "erds", "liaoyang", "mas", "yx", "pj", "klmy", "hlr", "baoji", "wuhu", "wuzhou", "xiangtan", "sm",
				"lyg", "hk", "tw", "xn", "haikou", "yinchuan", "lasa", "donghai", "guanyun", "guannan", "sg", "jj", "px", "cn",
				"yj", "lw", "jingmen", "hg", "es", "shaoyang", "yongzhou", "songyuan", "hn", "huangshan", "fy", "la", "bozhou",
				"qinzhou", "yulin", "hc", "kaifeng", "hb", "puyang", "luohe", "suqian", "jinzhou", "jdz", "ganzhou", "ja",
				"panzhihua", "luzhou", "deyang", "nanchong", "yb", "ga", "qj", "bs", "lj", "cx", "honghe", "ws", "bn", "dali",
				"wuhai", "xl", "guyuan", "sanya", "pt", "liaoyuan", "ankang", "hanzhong", "rizhao", "juxian", "yanbian", "zd",
				"yingtan", "lvliang", "ls", "anqing", "jiaozuo", "wfd", "pld", "zhaoyuan", "zjk", "jincheng", "suzhou",
				"xiaogan", "hanchuan", "dandong", "xinzhou", "bengbu", "jingzhou", "mdj", "yuyao", "cixi", "heze", "xuancheng",
				"shanxian", "caoxian", "ningguo", "yuncheng", "linfen", "chenzhou", "yichun", "fushun", "wn", "hh", "shuyang",
				"benxi", "qqhr", "yk", "bc", "scnj", "changyuan", "yl", "shenmu", "sihong", "siyang", "chifeng", "suihua", "tl",
				"xiangshanxian", "zg", "ly", "chengde", "gg", "jms", "zjj", "quzhou", "zy", "jianyangshi", "anyuexian",
				"changzhi", "dt", "linxia", "yanshiqu", "liuzhou", "pl", "jixi", "qingyuan", "huantaixian", "qixianqu",
				"jinchang", "hami", "xianyang", "anshun", "cangnanxian", "zunyi", "renhuaishi", "lishui", "nd", "dingzhou",
				"zhoushan", "mg", "yangchun", "changji", "tianshui", "zhangqiu", "xy", "gt", "sd", "quanguo", "guanghanshi",
				"yq", "jz", "yanan", "zx", "hegang", "lepingshi", "yanling", "zc", "linyixian", "smx", "lingbaoshi", "ks",
				"changge", "huaibei", "qdn", "mz", "am", "zt", "lincang", "diqing", "dh", "pe", "sw", "haifengxian", "nujiang",
				"yili", "tlf", "ld", "ht", "aks", "tm", "kzls", "betl", "bygl", "ale", "shz", "tmsk", "wjq", "sn", "snj", "rkz",
				"xianning", "nq", "linzhi", "changdu", "suizhou", "qianjiang", "al", "rituxian", "gaizexian", "ya", "suining",
				"ms", "renshouxian", "ez", "liangshan", "xiantao", "guangyuan", "ganzi", "yich", "dazhou", "bazhong", "ab", "tc",
				"sys", "qth", "sl", "heihe", "shuozhou", "dxal", "ys", "huangnan", "hx", "haidong", "haibei", "jiyuan", "guoluo",
				"zw", "wzs", "wuzhong", "szs", "xam", "wenchang", "wlcb", "tongliao", "wanning", "hlbe", "tunchang",
				"qiongzhong", "bycem", "alsm", "hld", "fx", "cy", "xinyu", "sr", "fuzhou", "qh", "th", "sp", "gongzhuling",
				"baishan", "lingshui", "yiyang", "xiangxi", "ch", "df", "chizhou", "chuzhou", "tianchang", "tongling", "np",
				"da", "by", "dx", "cm", "gn", "jyg", "baoting", "baish", "jq", "danzhou", "ln", "tr", "qxn", "wuwei", "zhangye",
				"chaozhou", "heyuan", "qingyang", "yf", "qn", "lps", "baise", "chongzuo", "bh", "fcg", "hezhou", "lb", "bijie",
				"hainan", "wuyishan", "hexian", "qingxu", "yxx", "zhangbei", "hq", "taishan", "dafeng", "tongcheng", "kl",
				"xzpeixian", "yiwu", "lfyanjiao", "sansha", "yueqingcity", "ruiancity", "jiashanx", "baoyingx", "alt", "tac",
				"xionganxinqu"
			];
			if ($.inArray(listname, qudaoCityArr) != -1) {
				$(".tabs").append(qudaohtmlStr)
			}
		}
	}
});
$(function() {
	try {
		var cityName = ____json4fe.locallist.listname
	} catch (e) {
		return
	}
	var obj = $("#chinahr_job"),
		obj1 = $("#chinahrhide");
	if (obj.length == 0 || obj1.length == 0) {
		return
	}
	var city = {
		bj: "34,398",
		sh: "36,400",
		gz: "25,291",
		sz: "25,292",
		cd: "27,312",
		sy: "13,133",
		sjz: "11,111",
		dl: "13,134",
		tj: "35,399",
		cs: "24,277",
		wh: "23,264",
		xa: "30,358",
		su: "16,173",
		hz: "17,182",
		nj: "16,169",
		qd: "21,231",
		weihai: "21,237",
		cq: "37,401",
		tongling: "18,199",
		mianyang: "27,317",
		zj: "16,179",
		fx: "13,141",
		qz: "19,214",
		jining: "21,238",
		dz: "21,243",
		jn: "21,230",
		hrb: "15,156",
		cz: "16,172",
		wuwei: "31,373",
		pj: "13,143",
		zz: "22,247",
		fushun: "13,136",
		xm: "19,211",
		jl: "14,148",
		xuancheng: "18,209",
		bozhou: "18,207",
		liaoyang: "13,142",
		lc: "21,244",
		dg: "25,307",
		fs: "25,296",
		zs: "25,308",
		ta: "21,239",
		bd: "11,116",
		cc: "14,147",
		ty: "12,122",
		changde: "24,283",
		fz: "19,210",
		jinzhou: "13,139",
		tianshui: "31,372",
		jingzhou: "23,268",
		nb: "17,183",
		wx: "16,170",
		gy: "28,333",
		zb: "21,232",
		yt: "21,235",
		km: "29,342",
		huizhou: "25,301",
		zhuzhou: "24,278",
		zh: "25,293",
		hf: "18,193",
		ts: "11,112",
		nn: "38,402",
		nc: "20,219",
		wz: "17,184",
		heze: "21,246",
		cangzhou: "11,119",
		ny: "22,259",
		sx: "17,187",
		shaoyang: "24,281",
		bz: "21,245",
		nanchong: "27,322",
		huzhou: "17,186",
		xianyang: "30,361",
		yuncheng: "12,129",
		hb: "22,252",
		tr: "28,337",
		xf: "23,266",
		yc: "23,269",
		wf: "21,236",
		linyi: "21,242",
		pds: "22,250",
		xiangtan: "24,279",
		lf: "11,120",
		dy: "21,234",
		xz: "16,171",
		jh: "17,188",
		lyg: "16,175",
		hy: "24,280",
		luoyang: "22,249",
		qhd: "11,113",
		nt: "16,174",
		yancheng: "16,177",
		hd: "11,114",
		ha: "16,176",
		jx: "17,185",
		lz: "31,368",
		hu: "39,416",
		haikou: "26,309",
		dt: "12,123",
		xt: "11,115",
		yinchuan: "41,435",
		hs: "11,121",
		yz: "16,178",
		jm: "25,297",
		yy: "24,282",
		xj: "42,440",
		chenzhou: "24,286",
		rizhao: "21,240",
		tz: "17,191",
		wuhu: "18,194",
		bt: "39,417",
		taizhou: "16,180",
		jj: "20,222",
		puyang: "22,255",
		zaozhuang: "21,233",
		liuzhou: "38,403",
		zhanjiang: "25,298",
		suqian: "16,181",
		ganzhou: "20,225",
		jiaozuo: "22,251",
		sr: "20,229",
		deyang: "27,316",
		as: "13,135",
		kaifeng: "22,248",
		xy: "22,261",
		st: "25,294",
		ls: "27,321",
		hshi: "23,265",
		yb: "27,323",
		erds: "39,421",
		yongzhou: "24,287",
		chengde: "11,118",
		jz: "12,128",
		luohe: "22,257",
		xx: "22,253",
		zk: "22,262",
		nd: "19,218",
		gl: "38,404",
		lishui: "17,192",
		dq: "15,161",
		zmd: "22,263",
		zunyi: "28,335",
		yk: "13,140",
		fy: "18,203",
		shiyan: "23,267",
		sp: "14,149",
		zhangzhou: "19,215",
		yulin: "38,410",
		qingyuan: "25,306",
		bc: "14,154",
		jy: "25,457",
		jms: "15,163",
		xc: "22,256",
		pt: "19,212",
		xn: "32,382",
		mdj: "15,165",
		zq: "25,300",
		dandong: "13,138",
		qqhr: "15,157",
		anqing: "18,200",
		yichun: "20,227",
		sg: "25,295",
		hanzhong: "30,364",
		chifeng: "39,419",
		mm: "25,299",
		scnj: "27,320",
		wn: "30,362",
		xinyu: "20,223",
		ay: "22,254",
		xianning: "23,274",
		linfen: "12,131",
		quzhou: "17,189",
		ly: "19,217",
		sq: "22,260",
		sm: "19,213",
		hg: "23,273",
		yf: "25,458",
		jincheng: "12,126",
		yj: "25,305",
		zjk: "11,117",
		suzhou: "18,204",
		la: "18,206",
		px: "20,221",
		ja: "20,226",
		wuzhou: "38,405",
		changzhi: "12,125",
		hld: "13,146",
		bengbu: "18,195",
		ez: "23,271",
		xiaogan: "23,272",
		ch: "18,205",
		lw: "21,241",
		baoji: "30,360",
		np: "19,216",
		es: "23,276",
		ld: "24,289",
		tongliao: "39,420",
		lasa: "40,428",
		mz: "25,302",
		guangyuan: "27,318",
		dazhou: "27,325",
		lj: "29,347",
		yq: "12,124",
		yiyang: "24,285",
		luzhou: "27,315",
		ms: "27,326",
		dali: "29,354",
		shuozhou: "12,127",
		songyuan: "14,153",
		huangshan: "18,201",
		chuzhou: "18,202",
		jingmen: "23,270",
		ya: "27,327",
		xam: "39,425",
		suihua: "15,167",
		suizhou: "23,275",
		yl: "30,365",
		qinzhou: "38,408",
		lvliang: "12,132",
		baishan: "14,152",
		heyuan: "25,304",
		sanya: "26,310",
		zg: "27,313",
		bh: "38,406",
		liangshan: "27,332",
		guyuan: "41,438",
		tl: "13,144",
		hegang: "15,159",
		hn: "18,196",
		ankang: "30,366",
		gg: "38,409",
		hk: "43,454",
		benxi: "13,137",
		suining: "27,319",
		zy: "27,329",
		bijie: "28,338",
		aks: "42,445",
		cy: "13,145",
		huaibei: "18,198",
		diqing: "29,357",
		hlbe: "39,422",
		changji: "42,449",
		chaozhou: "25,456",
		xinzhou: "12,130",
		fuzhou: "20,228",
		sw: "25,303",
		bazhong: "27,328",
		yanan: "30,363",
		szs: "41,436",
		th: "14,151",
		zhoushan: "17,190",
		jdz: "20,220",
		smx: "22,258",
		hh: "24,288",
		lps: "28,334",
		anshun: "28,336",
		honghe: "29,351",
		fcg: "38,407",
		mas: "18,197",
		zjj: "24,284",
		panzhihua: "27,314",
		ganzi: "27,331",
		qj: "29,343",
		by: "31,371",
		qingyang: "31,377",
		baise: "38,411",
		sys: "15,160",
		ga: "27,324",
		yx: "29,344",
		pe: "29,348",
		dh: "29,355",
		hezhou: "38,412",
		liaoyuan: "14,150",
		yanbian: "14,155",
		yingtan: "20,224",
		bs: "29,345",
		bn: "29,352",
		dx: "31,378",
		hx: "32,389",
		lb: "38,414",
		bygl: "42,448",
		jixi: "15,158",
		ab: "27,330",
		qxn: "28,339",
		zt: "29,346",
		lincang: "29,349",
		cx: "29,353",
		tc: "30,359",
		sl: "30,367",
		linxia: "31,380",
		wuzhong: "41,437",
		zw: "41,439",
		klmy: "42,441",
		yili: "42,451",
		am: "44,455",
		yich: "15,162",
		chizhou: "18,208",
		jinchang: "31,370",
		jq: "31,376",
		ln: "31,379",
		chongzuo: "38,415",
		wlcb: "39,424",
		rkz: "40,431",
		ks: "42,446",
		qth: "15,164",
		heihe: "15,166",
		dxal: "15,168",
		xiangxi: "24,290",
		qdn: "28,340",
		qn: "28,341",
		ws: "29,350",
		nujiang: "29,356",
		jyg: "31,369",
		zhangye: "31,374",
		pl: "31,375",
		gn: "31,381",
		haidong: "32,383",
		haibei: "32,384",
		huangnan: "32,385",
		hainan: "32,386",
		guoluo: "32,387",
		ys: "32,388",
		hc: "38,413",
		wuhai: "39,418",
		xl: "39,426",
		alsm: "39,427",
		changdu: "40,429",
		sn: "40,430",
		nq: "40,432",
		al: "40,433",
		linzhi: "40,434",
		tlf: "42,442",
		hami: "42,443",
		ht: "42,444",
		betl: "42,450"
	};
	if (city[cityName]) {
		var hrjob = $("#chinahr_job .posList a");
		hrjob.each(function() {
			$(this).attr("href", encodeURI($(this).attr("href").replace(/\{(\d+)\}/g, city[cityName])))
		});
		obj.show();
		obj1.hide()
	}
});
$(function() {
	var cityDispid;
	try {
		cityDispid = ____json4fe.locallist.dispid
	} catch (e) {
		return
	}
	var cities = ["8716", "8680", "293", "296", "297", "13951", "13950", "357", "369", "399", "400", "401", "402", "408",
		"8398", "469", "477", "478", "500", "502", "503", "615", "11279", "618", "11263", "648", "649", "696", "698",
		"699", "725", "726", "834", "835", "864", "979", "9344", "9123", "1042", "2258", "2043", "2051", "2147", "10574",
		"2302", "9817", "9764", "9717", "2393", "2394", "2395", "2397", "2398", "9437", "9462", "9432", "2408", "10380",
		"3279", "3184", "3198", "11296", "3306", "5333", "5334", "5402", "5645", "9193", "5772", "10343", "7112", "11077",
		"7452", "8541", "8566", "8582", "8706", "9048", "9051", "9146", "9326", "9363", "9475", "9472", "18845", "18837",
		"9489", "9499", "9519", "9530", "9529", "9539", "9551", "9559", "9562", "9576", "9615", "9618", "9646", "9648",
		"9678", "9682", "9692", "9878", "9888", "9896", "9902", "9909", "9917", "9936", "9952", "9984", "10022", "10044",
		"10064", "10070", "10136", "10184", "10219", "10892", "10231", "10250", "10267", "10761", "10303", "10331",
		"10367", "10394", "10417", "10434", "10492", "10564", "10908", "11201", "11226", "11313", "12180", "12291",
		"13722", "20672", "16250", "20674", "16506", "17044", "20682", "20681", "20673", "17196", "17984", "17988",
		"20678", "20643", "20675", "20676", "20679"
	];
	if ($.inArray(cityDispid, cities) > -1) {
		$("#chinahr_job").hide()
	}
});
$(function() {
	var endTime = new Date("2017/07/25").getTime();
	var currTime = (new Date).getTime();
	var data = [{
		old: "财务/审计",
		"new": "格力工程机械",
		link: "http://newpage.chinahr.com/pages/super/glpclist?jobtype1=1109&jobtype2=11040"
	}, {
		old: "行政管理",
		"new": "格力电子电器",
		link: "http://newpage.chinahr.com/pages/super/glpclist?jobtype1=1109&jobtype2=11038"
	}, {
		old: "设计",
		"new": "格力客服",
		link: "http://newpage.chinahr.com/pages/super/glpclist?jobtype1=1100&jobtype2=11003"
	}, {
		old: "建筑/房产",
		"new": "格力软件研发",
		link: "http://newpage.chinahr.com/pages/super/glpclist?jobtype1=1103&jobtype2=11013"
	}, {
		old: "销售经理",
		"new": "格力硬件研发",
		link: "http://newpage.chinahr.com/pages/super/glpclist?jobtype1=1103&jobtype2=11012"
	}, {
		old: "实习生",
		"new": "更多格力职位",
		link: "http://newpage.chinahr.com/pages/super/gelipc "
	}];
	var $node = $("#chinahr_job .posList a");
	var cityListname;
	try {
		cityListname = ____json4fe.locallist.listname
	} catch (e) {
		return
	}
	var cities = ["zh", "wuhu", "zs", "hz", "wh", "sjz", "cs", "zz", "cq"];
	if ($.inArray(cityListname, cities) > -1 && currTime <= endTime) {
		var $thisNode;
		$.each(data, function(index, item) {
			$thisNode = $node.filter(function() {
				return $(this).text() == item.old
			});
			$thisNode.text(item.new).attr("href", item.link)
		})
	}
});
(function() {
	var spt = document.createElement("script");
	spt.type = "text/javascript";
	spt.charset = "utf-8";
	spt.id = "adstat_js";
	spt.async = true;
	spt.src = "//stat-58house.58che.com/ol_58house.js";
	var scr = document.getElementsByTagName("script")[0];
	scr.parentNode.insertBefore(spt, scr)
})();
$(function() {
	var cityArr = ["414", "1086", "10198", "872", "914", "2047", "821", "5695", "2303", "5756", "2307", "9481", "10219",
		"6788", "1091", "876", "5699", "5698", "5701", "921", "5690", "5693", "9470", "9471", "9473", "6955", "6954",
		"10201", "10202", "8532", "828", "830", "6791", "1095"
	];
	if (____json4fe && ____json4fe.locallist && ____json4fe.locallist.dispid) {
		var dispid = ____json4fe.locallist.dispid;
		if ($.inArray(dispid, cityArr) != -1) {
			$('.wid112 a[href*="jobfrom=canjiren"]').after('<i class="new">new</i>')
		}
	}
});
