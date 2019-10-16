(function(J) {
	var d = J,
		k = document,
		q = d.location,
		D = d.performance;
	if (!d.TJ58) {
		d.TJ58 = !0;
		null == d.String.prototype.trim && (d.String.prototype.trim = function() {
			return this.replace(/^\s\s*/, "").replace(/\s\s*$/, "")
		});
		d.TJ58Obj = {};
		var e = {
				curURL: q.href,
				referrer: k.referrer,
				protocol: q.protocol,
				window_size: k.documentElement.clientWidth + "x" + k.documentElement.clientHeight,
				screen_size: d.screen.width + "," + d.screen.height,
				domain: function() {
					var b = q.host.toLowerCase(),
						a = /.*?([^\.]+\.(com|org|net|biz|edu|cc)(\.[^\.]+)?)/;
					return a.test(b) ? "." + b.replace(a, "$1") : ""
				}(),
				navigation_type: function() {
					var b = "-1";
					try {
						D && D.navigation && (b = D.navigation.type)
					} catch (a) {
						return "-1"
					}
					return b
				}(),
				setCookie: function() {
					if (!k.cookie) return !1;
					var b = new Date;
					2 < arguments.length ? b.setTime(b.getTime() + 864E5 * arguments[2]) : b.setTime(b.getTime() + 18E5);
					2 <= arguments.length && (k.cookie = arguments[0] + "=" + escape(arguments[1]) + "; expires=" + b.toGMTString() +
						"; domain=" + e.domain + "; path=/")
				},
				getCookie: function(b) {
					if (!k.cookie) return "";
					var a;
					return (a = k.cookie.match(RegExp("(^| )" +
						b + "=([^;]*)(;|$)"))) ? unescape(a[2]) : ""
				},
				ajaxsend: function(b) {
					(new Image).src = b
				},
				getGTID: function(b, a, c) {
					function d(a, b, c) {
						a = ("" + a).length < b ? (Array(b + 1).join("0") + a).slice(-b) : "" + a;
						return -1 == c ? a : a.substring(0, c) + "-" + a.substring(c)
					}
					var e = {
						home: "1",
						index: "2",
						list: "3",
						detail: "4",
						post: "5",
						special: "6"
					};
					b = e[b] ? parseInt(e[b]).toString(16) : 0;
					a = a.split(",");
					a = a[a.length - 1];
					a = parseInt(a) ? parseInt(a).toString(16) : 0;
					c = c.split(",");
					c = c[c.length - 1];
					c = parseInt(c) ? parseInt(c).toString(16) : 0;
					e = (13).toString(16);
					return "llpccccc-tttt-txxx-xxxx-xxxxxxxxxxxx".replace(/x/g,
						function(a) {
							return (16 * Math.random() | 0).toString(16)
						}).replace(/ccccc/, d(a, 5, -1)).replace(/tttt-t/, d(c, 5, 4)).replace(/p/, d(b, 1, -1)).replace(/ll/, d(e, 2,
						-1))
				},
				setLocalStorage: function(b, a) {
					try {
						d.localStorage && d.localStorage.setItem(b, a)
					} catch (c) {}
				},
				getLocalStorage: function(b) {
					try {
						return d.localStorage ? d.localStorage.getItem(b) : ""
					} catch (a) {
						return ""
					}
				},
				getUUID: function(b) {
					var a = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(a) {
							var b = 16 * d.Math.random() | 0;
							return ("x" == a ? b : b & 3 | 8).toString(16)
						}),
						a = this.getCookie(b) || this.getLocalStorage(b) || a;
					this.setCookie(b, a, 365);
					this.setLocalStorage(b, a);
					return a
				},
				getRandom: function() {
					return d.Math.random()
				},
				bindElem: function(b, a, c, e, m) {
					if (d.$ && "string" == typeof b && "string" == typeof a && "function" == typeof c)
						if ("function" === typeof $(k).on) $(k).on(a, b, c);
						else "function" === typeof $(k).delegate ? $(k).delegate(b, a, c) : "function" === typeof $(b).bind && $(b).bind(
							a, c)
				},
				loadScript: function(b, a) {
					try {
						var c = k.createElement("script");
						c.type = "text/javascript";
						c.readyState ?
							c.onreadystatechange = function() {
								if ("loaded" == c.readyState || "complete" == c.readyState) c.onreadystatechange = null, a && a()
							} : c.onload = function() {
								a && a()
							};
						c.src = b;
						k.body.appendChild(c)
					} catch (d) {}
				}
			},
			f = {
				config: {
					trackLog: {
						server: "tracklog.58.com/pc/empty.js.gif",
						allParams: "site_name tag referrer post_count _trackParams userid smsc window_size _ga_utma trackURL rand_id".split(
							" "),
						uniqParams: ["tag", "rand_id"]
					},
					listShowLog: {
						server: "tracklog.58.com/PCv1/listshow/empty.js.gif",
						allParams: "tag bangbangid referrer site_name info_data trackURL rand_id".split(" "),
						uniqParams: ["tag", "info_data", "rand_id"]
					},
					listClickLog: {
						server: "tracklog.58.com/PCv1/listclick/empty.js.gif",
						allParams: "tag bangbangid referrer site_name info_data trackURL ClickID rand_id".split(" "),
						uniqParams: ["tag", "info_data", "rand_id"]
					},
					clickLog: {
						server: "tracklog.58.com/pc/click/empty.js.gif",
						allParams: "site_name tag from trackURL ClickID bangbangid referrer rand".split(" "),
						uniqParams: ["tag", "from", "rand"]
					},
					diaTrackLog: {
						server: "dialog.58.com/transfer",
						allParams: "DIATag tag referrer post_count _trackParams userid smsc window_size _ga_utma trackURL rand_id".split(
							" "),
						uniqParams: ["DIATag", "tag", "rand_id"]
					},
					diaClickLog: {
						server: "dialog.58.com/transfer",
						allParams: "DIATag from trackURL ClickID bangbangid referrer rand".split(" "),
						uniqParams: ["DIATag", "from", "rand"]
					},
					diaShowLog: {
						server: "dialog.58.com/transfer",
						allParams: "DIATag trackURL ClickID bangbangid referrer rand".split(" "),
						uniqParams: ["DIATag", "rand"]
					},
					gdtTrackLog: {
						server: "gdt.cm.58.com/gdtcm",
						allParams: ["city", "cate", "key", "plat"],
						uniqParams: ["city", "key", "plat"]
					},
					gdtTrackLog2: {
						server: "gdtcm.58.com/gdtcm",
						allParams: ["city", "cate", "key", "plat"],
						uniqParams: ["city", "key", "plat"]
					},
					actionLog: {
						server: "tracklog.58.com/PCv1/action/empty.js.gif",
						allParams: "site_name tag window_size action_data trackURL referrer rand_id".split(" "),
						uniqParams: ["tag", "action_data", "rand_id"]
					}
				},
				filterList: function(b) {
					var a =
						".58.com.cn portal.58.com faw-vw-dasweltauto.58.com 5858.com lieche.58.com dict.58.com/xiaoqu about.58.com m.58.com/city.html lieche.m.58.com"
						.split(" "),
						c;
					for (c in a)
						if (-1 !== b.indexOf(a[c])) return "YES";
					return "NO"
				},
				getBaseInfo: function() {
					var b = d.site_name || "58",
						a = d.encodeURIComponent(e.referrer),
						c = e.curURL,
						h = e.getUUID("58tj_uuid"),
						m = e.getCookie("bangbangid"),
						r = e.window_size,
						g = e.navigation_type,
						s = e.screen_size,
						p = d.____json4fe ? d.____json4fe : {},
						f = p._trackPagetype || "",
						k = p._trackURL || "",
						n = p._trackParams || [],
						l = p.GA_pageview || "",
						q = p.infoid || "",
						D = p.userid || "",
						O = p.smsc || "",
						p = p.sid || "",
						x = d._trackURL || k || "NA",
						v = {};
					try {
						v = "NA" === x ? {} : eval("(" + x + ")")
					} catch (S) {
						v = {}
					}
					var f = v.pagetype || f || d.page_type || "NA",
						k = v.post_count || d.post_count ||
						-1,
						l = v.Ga_pageview || l || "",
						y = v.cate || "",
						P = "" === y ? "" : y.split(",")[0],
						Q = "" === y && -1 === y.indexOf(",") ? "" : y.split(",")[1],
						G = v.area || "",
						M = "" === G ? "" : G.split(",")[0],
						N = e.getGTID(f, y, G),
						v = v.ROI || "",
						t = d._trackParams || n || [],
						z = [],
						n = "";
					if (t instanceof Array) {
						for (var n = 0, K = t.length; n < K; n++) t[n] && t[n].I && t[n].V && "string" === typeof t[n].V && z.push(t[n]
							.I + ":" + t[n].V.replace(/\|/g, "*"));
						n = encodeURIComponent(z.join("@@"))
					}
					var z = (t = e.curURL.match(/[\?&]iuType=[a-z]*_[0-9]*/)) ? t[0].split("=")[1].split("_") : ["", ""],
						t = z[0],
						z = z[1],
						K = e.getCookie("als"),
						E = e.getCookie("utm_source"),
						R = e.getCookie("utm_campaign"),
						F = e.getCookie("spm"),
						A, C, H;
					"" != e.getCookie("new_session") ? (A = e.getCookie("init_refer"), C = "0") : (A = d.encodeURIComponent(e.referrer),
						C = "1");
					H = "" != e.getCookie("new_uv") ? parseInt(e.getCookie("new_uv")) + ("0" == C ? 0 : 1) : 1;
					e.setCookie("new_session", C);
					e.setCookie("new_uv", H, 365);
					var B = e.referrer.split("/")[2] || "",
						u = this.getValByUrl(e.curURL, "utm_source"),
						w = this.getValByUrl(e.curURL, "spm");
					if (!e.referrer && ("NA" != u || "NA" != w)) A =
						"", E = "NA" === u ? "" : u, F = "NA" === w ? "" : w;
					else if (!e.referrer && "NA" == u && "NA" == w && "1" === C) F = E = A = "";
					else if (B) {
						var L = !1;
						"cast.58.com" == B ? L = !0 : -1 === B.indexOf(".58.com") && -1 === B.indexOf(".5858.com") && -1 === B.indexOf(
							".58cdn.com.cn") && (L = !0);
						L && (A = J.encodeURIComponent(e.referrer), E = "NA" === u ? "" : u, F = "NA" === w ? "" : w)
					}
					e.setCookie("utm_source", E);
					e.setCookie("spm", F);
					e.setCookie("init_refer", A);
					var B = "1.1.1.1.1." + H,
						u = [],
						w = x.indexOf("{"),
						g = {
							GTID: N,
							infoid: q,
							infotype: t,
							usertype: z,
							als: K,
							utm_source: E,
							utm_campaign: R,
							spm: F,
							new_session: C,
							init_refer: A,
							new_uv: H,
							UUID: h,
							bangbangid: m,
							navtype: g,
							sc: s,
							sid: p
						},
						I;
					for (I in g) g.hasOwnProperty(I) && u.push("'" + I + "':'" + g[I] + "'");
					u.join(",");
					x = "NA" !== x && -1 !== w ? x.substring(0, w + 1) + u + "," + x.substring(w + 1) : "{" + u + "}";
					return {
						site_name: b,
						referrer: a,
						UUID: h,
						bangbangid: m,
						pagetype: f,
						post_count: k,
						cate: y,
						cate1: P,
						cate2: Q,
						area: G,
						area1: M,
						city: M,
						GTID: N,
						ClickID: 1,
						ROI: v,
						curURL: c,
						_trackParams: n,
						userid: D,
						smsc: O,
						window_size: r,
						trackURL: x,
						Ga_pageview: l,
						_ga_utma: B,
						ClickIDPlus: function() {
							this.ClickID += 1
						}
					}
				},
				getValByUrl: function(b,
					a) {
					var c;
					c = b.match(RegExp("[&?]" + a + "=([^&|^#]*)"));
					return c instanceof Array ? c[1] : "NA"
				},
				sendLog: function(b, a) {
					var c = this.baseInfo,
						d = this.config[b];
					if (b && d && a && "object" === typeof a) {
						for (var m = [], r = d.allParams, g = 0, s = r.length; g < s; g++) m.push(r[g] + "=" + (a[r[g]] || c[r[g]] ||
							""));
						e.ajaxsend(e.protocol + "//" + d.server + "?" + m.join("&"))
					}
				},
				trackLog: function() {
					var b = this.baseInfo;
					this.sendLog("trackLog", {
						tag: "pvstatall",
						rand_id: e.getRandom()
					});
					if ("list" === b.pagetype || "detail" === b.pagetype) {
						var a = b.Ga_pageview.indexOf("?key="),
							a = -1 !== a ? b.Ga_pageview.substring(a + 5) : "";
						"https:" == e.protocol ? this.sendLog("gdtTrackLog2", {
							city: b.area,
							key: a,
							plat: "PC"
						}) : this.sendLog("gdtTrackLog", {
							city: b.area,
							key: a,
							plat: "PC"
						})
					}
				},
				clickLog: function(b) {
					var a = "",
						a = null != b && "from=" === b.substring(0, 5) ? b.replace("from=", "") : "default&" + b;
					this.sendLog("clickLog", {
						tag: "pvsiters",
						from: a,
						rand: e.getRandom()
					});
					setTimeout("GCIDPlus()", 300)
				},
				listClickLog: function() {
					var b = this,
						a = this.baseInfo;
					if (d.$ && "NA" !== a.pagetype && "NA" !== a.trackURL) {
						var c = {
								list: 1,
								jianli_list: 1,
								xiaoqu: 1,
								qijiandian: 1,
								item: 1,
								xinchong: 1
							},
							h = function() {
								if ("function" == typeof $(this).parents) {
									var c = "",
										c = $(this).parents("[logr]"),
										g = [],
										d = "",
										h = c.attr("logr").split("_"),
										m = c.attr("_pos"),
										f = c.attr("sortid"),
										k = c.attr("infoproperty"),
										l = h[h.length - 1];
									g.push(h[0], h[1], h[2], h[3]);
									l && (l = l.replace("ses^", "ses:"), d += l);
									l = "";
									l = "function" == typeof $("[logr]").index ? $("[logr]").index(c) + 1 : c.attr("pos");
									d = d + (f ? "@sortid:" + f : "") + (l ? "@pos:" + l : "");
									d += m ? "@npos:" + m : "";
									d += k ? "@infoproperty:" + k : "";
									"" != d && (0 === d.indexOf("@") &&
										(d = d.substring(1)), g.push(d));
									c = g.join("_");
									"NO" == b.filterList(a.curURL) && -1 != a.curURL.indexOf(".58.com") && (g = $(this).attr("href") || "#", -1 !=
										g.indexOf("javascript:") || "#" == g.substring(0, 1) || "NO" != b.filterList(g) || "/" != g.substring(0, 1) &&
										-1 == g.indexOf(".58.com") || g.match(/[\?&]iuType=/) || $(this).attr("href", g.trim() + (-1 == g.indexOf(
											"?") ? "?" : "&") + "iuType=" + h[0] + "_" + h[1]));
									b.sendLog("listClickLog", {
										tag: "pclistclick",
										info_data: c,
										rand_id: e.getRandom()
									});
									setTimeout("GCIDPlus()", 300)
								}
							},
							m = $("[tongji_label=listclick]");
						m && 0 < m.length ? $("[logr] [tongji_label=listclick]").bind("click", h) : 1 === c[a.pagetype] ? ($(
							"[logr] a.t").bind("click", h), "12" === a.cate2 ? ($("[logr] a.jjh_img").bind("click", h), $(
							"[logr] .img a").bind("click", h)) : "187" === a.cate1 || "42270" === a.cate1 ? $("[logr]").find("a:first").bind(
							"click", h) : "9225" === a.cate1 || "13952" === a.cate1 ? $("[logr] a.fl").bind("click", h) : $(
							"[logr] .img a").bind("click", h)) : "chexing" === a.pagetype && ($("[logr] a.at").bind("click", h), $(
							"[logr] .tdimg a").bind("click", h))
					}
				},
				listShowLog: function() {
					var b =
						this.baseInfo;
					if (d.$ && "list" === b.pagetype) {
						for (var a = [], c = $("[logr]"), b = b.trackURL.length + b.referrer.length, h = 0, m = c.length; h < m; h++)
							if ($(c[h]).attr("logr") && "function" == typeof $(c[h]).attr) {
								var f = [],
									g = "",
									s = $(c[h]).attr("logr").split("_"),
									p = $(c[h]).attr("_pos"),
									k = h + 1,
									q = $(c[h]).attr("sortid"),
									n = $(c[h]).attr("infoproperty"),
									l = s[s.length - 1];
								f.push(s[0], s[1], s[2], s[3]);
								l && (l = l.replace("ses^", "ses:"), g += l);
								g += q ? "@sortid:" + q : "";
								g += k ? "@pos:" + k : "";
								g += p ? "@npos:" + p : "";
								g += n ? "@infoproperty:" + n : "";
								"" != g && (0 === g.indexOf("@") &&
									(g = g.substring(1)), f.push(g));
								f = f.join("_");
								curLogrLen = f.length;
								g = a.join(",");
								4096 < b + curLogrLen + g.length && (this.sendLog("listShowLog", {
									tag: "pclistshow",
									info_data: g,
									rand_id: e.getRandom()
								}), a = []);
								a.push(f)
							} 0 != a.length && this.sendLog("listShowLog", {
							tag: "pclistshow",
							info_data: a.join(","),
							rand_id: e.getRandom()
						})
					}
				},
				bindTongji_tag: function() {
					if (d.$) {
						var b = this;
						e.bindElem("[tongji_tag]", "click", function() {
							var a = $(this).attr("tongji_tag"),
								c = $(this).text().trim();
							b.clickLog("from=" + a + "&text=" + encodeURIComponent(c) +
								"&tongji_type=tongji_tag")
						}, b)
					}
				},
				bindTongji_id: function() {
					if (d.$) {
						var b = this;
						e.bindElem("[tongji_id]", "click", function(a) {
							var c = a.srcElement ? a.srcElement : a.target;
							"A" == c.tagName.toUpperCase() && (a = $(c).attr("href") || "#", c = $(c).text(), -1 == a.indexOf(
								"javascript:") && "#" != a.substring(0, 1) && b.clickLog("from=" + $(this).attr("tongji_id") + "&text=" +
								encodeURIComponent(c) + "&to=" + encodeURIComponent(a) + "&tongji_type=tongji_id"))
						}, b)
					}
				},
				diaShowLog: function(b) {},
				bindAlsTag: function() {
					if (!e.getCookie("als") && d.$ && "function" ===
						typeof $("body").one) $("body").one("mouseover", function() {
						e.setCookie("als", "0", 365)
					});
					e.getCookie("isSpider") && e.setCookie("isSpider", "", 0)
				},
				bindHomeHeatMap: function() {
					var b = this,
						a = this.baseInfo;
					d.$ && "home" === a.pagetype && e.bindElem("[href]", "click", function(a) {
						var d = $(this).attr("href"),
							e = $(this).text().trim(),
							f = $(this).attr("tongji_tag") || "NA",
							g = $(this).offset().top,
							s = $(this).offset().left,
							k = a.pageX;
						a = a.pageY;
						b.clickLog("from=heatmap:" + encodeURIComponent(d) + "&tagX=" + s + "&tagY=" + g + "&mouseX=" + k +
							"&mouseY=" +
							a + "&text=" + encodeURIComponent(e) + "&tongji_tag=" + f)
					}, b)
				},
				insertMiGuan: function() {
					try {
						var b = "default";
						switch (this.baseInfo.cate1) {
							case "9224":
							case "9225":
							case "13941":
							case "13952":
								b = "yewu";
								break;
							case "1":
								b = "ershoufang";
								break;
							case "5":
								b = "shouji";
								break;
							case "832":
								b = "dog";
								break;
							case "4":
								b = "ershouche";
								break;
							default:
								b = "shenghuo"
						}
						var a = Math.ceil(1E14 * Math.random()),
							c = document.getElementsByTagName("body")[0],
							d = document.createElement("div");
						d.id = "addInfo";
						d.style.display = "none";
						var f = document.createElement("a");
						f.href = e.protocol + "//tracklog.58.com/detail/pc/" + b + "/" + a + "x.shtml";
						f.text = "\u63a8\u8350\u4fe1\u606f";
						d.appendChild(f);
						c.appendChild(d)
					} catch (k) {}
				},
				bindAddGTIDtoURL: function() {
					var b = this,
						a = this.baseInfo;
					d.$ && e.bindElem("a", "click", function(c) {
						if ("NO" == b.filterList(a.curURL) && -1 != a.curURL.indexOf(".58.com") && (c = $(this).attr("href") || "#",
								-1 == c.indexOf("javascript:") && "#" != c.substring(0, 1) && "NO" == b.filterList(c) && ("/" == c.substring(
									0, 1) || -1 != c.indexOf(".58.com"))))
							if (c.match(/[\?&]ClickID=\d*/)) $(this).attr("href",
								c.replace(/ClickID=\d*/, "ClickID=" + a.ClickID));
							else {
								var d = c.indexOf("?"); - 1 != d && -1 != c.substring(d).indexOf("statmark=t") && -1 != c.substring(d).indexOf(
									"#") ? (d = c.indexOf("statmark=t"), $(this).attr("href", c.substring(0, d + 10) + "&PGTID=" + a.GTID +
									"&ClickID=" + a.ClickID + c.substring(d + 10))) : $(this).attr("href", c.trim() + (-1 == d ? "?" : "&") +
									"PGTID=" + a.GTID + "&ClickID=" + a.ClickID)
							}
					}, b, a)
				},
				ActionObj: {
					data: []
				},
				actionFilter: function() {
					pagetypeArr = "home index list special jianli_index jianli_list reg PC_regist_sj".split(" ");
					var b = this.baseInfo,
						a = !1,
						d;
					for (d in pagetypeArr)
						if (b.pagetype == pagetypeArr[d]) {
							a = !0;
							break
						} return a ? !0 : !1
				},
				actionLog: function() {
					var b = this;
					b.actionFilter() && (e.bindElem("body", "click", function(a) {
						var c = b.ActionObj.data,
							h = k.body.scrollWidth || "",
							m = k.body.scrollHeight || "";
						a = a || d.event;
						var r = k.documentElement.scrollLeft || k.body.scrollLeft,
							g = k.documentElement.scrollTop || k.body.scrollTop,
							r = a.pageX || a.clientX + r || "-1";
						a = a.pageY || a.clientY + g || "-1";
						r = Math.floor(r);
						a = Math.floor(a);
						c.push("CLICK_" + h + "_" + m + "_" +
							r + "_" + a);
						c instanceof Array && 5 <= c.length && (c = c.join(","), f.ActionObj.data = [], b.sendLog("actionLog", {
							tag: "pcaction",
							action_data: c,
							rand_id: e.getRandom()
						}))
					}), d.$ && $(d).unload(function() {
						var a = b.ActionObj;
						(a = a ? a.data : "") && a instanceof Array && 0 < a.length && (a = a.join(","), f.ActionObj.data = [], b.sendLog(
							"actionLog", {
								tag: "pcaction",
								action_data: a,
								rand_id: e.getRandom()
							}))
					}))
				},
				performanceLog: function() {
					var b = this.baseInfo,
						a = !1,
						c = "home index list special post detail".split(" "),
						e;
					for (e in c)
						if (b.pagetype == c[e]) {
							a = !0;
							break
						} a && d.$ && d.performance && d.performance.timing && $(function() {
						function a(b, d, c) {
							return "number" === typeof b && "number" === typeof d ? (b -= d, b = Math.floor(0 > b ? -1 : b), -1 == b &&
								c ? c : b) : -1
						}

						function b(c) {
							var e = d.performance.timing,
								e = {
									loadPage: 0 == e.navigationStart ? a(e.loadEventEnd, e.fetchStart, c) : a(e.loadEventEnd, e.navigationStart,
										c),
									domReady: a(e.domComplete, e.responseEnd, c),
									redirect: a(e.redirectEnd, e.redirectStart, c),
									lookupDomain: a(e.domainLookupEnd, e.domainLookupStart, c),
									ttfb: 0 == e.navigationStart ? a(e.responseStart,
										e.fetchStart, c) : a(e.responseStart, e.navigationStart, c),
									request: a(e.responseEnd, e.requestStart, c),
									loadEvent: a(e.loadEventEnd, e.loadEventStart, c),
									appcache: a(e.domainLookupStart, e.fetchStart, c),
									unloadEvent: a(e.unloadEventEnd, e.unloadEventStart, c),
									connect: a(e.connectEnd, e.connectStart, c),
									DOMContentLoaded: a(e.domContentLoadedEventEnd, e.fetchStart, c)
								};
							c = [];
							for (var f in e) e.hasOwnProperty(f) && c.push("'" + f + "':'" + e[f] + "'");
							c.join(",");
							f = getTrackURL();
							e = f.indexOf("{");
							f = "NA" !== f && -1 !== e ? f.substring(0, e + 1) +
								c + "," + f.substring(e + 1) : "{" + c + "}";
							c = [];
							c.push("site_name=58");
							c.push("tag=performance");
							c.push("referrer=" + J.encodeURIComponent(document.referrer));
							c.push("trackURL=" + f);
							c.push("rand_id=" + d.Math.random());
							c = q.protocol + "//tracklog.58.com/PCv1/performance/empty.js.gif?" + c.join("&");
							(new Image).src = c;
							d.TJ58Obj.send = !0;
							clearInterval(d.TJ58Obj.PFORMINTERVAL)
						}
						d.TJ58Obj.PFORMCOUNT = -1;
						d.TJ58Obj.send = !1;
						$(d).unload(function() {
							d.TJ58Obj.send || b("close_" + Math.floor(d.performance.now()))
						});
						d.TJ58Obj.PFORMINTERVAL =
							setInterval(function() {
								d.TJ58Obj.PFORMCOUNT++;
								6 < d.TJ58Obj.PFORMCOUNT && clearInterval(d.TJ58Obj.PFORMINTERVAL);
								try {
									0 < d.performance.timing.loadEventEnd ? b() : 6 <= d.TJ58Obj.PFORMCOUNT && b("TIMEOUT_" + Math.floor(d.performance
										.now()))
								} catch (a) {
									clearInterval(d.TJ58Obj.PFORMINTERVAL)
								}
							}, 500)
					})
				},
				DMloadByTag: function() {
					try {
						var b = this.getValByUrl(e.curURL, "dm-statistic-detect"),
							a = e.getCookie("dm-statistic-detect");
						if (b = ("NA" == b ? "" : b) || a || "")
							if (d.TJ58ecdata = {}, d.TJ58ecdata.platform = "58PC", "https:" != q.protocol) switch (b) {
								case "2":
									e.loadScript(q.protocol +
										"//stat.58corp.com/static/js/referrer_alert.js");
									break;
								case "3":
									e.loadScript(q.protocol + "//stat.58corp.com/static/js/referrer_visual.js")
							}
					} catch (c) {}
				}
			};
		f.baseInfo = f.getBaseInfo();
		f.trackLog();
		f.listShowLog();
		f.listClickLog();
		f.bindAlsTag();
		f.bindTongji_tag();
		f.bindTongji_id();
		f.bindHomeHeatMap();
		f.bindAddGTIDtoURL();
		f.insertMiGuan();
		f.actionLog();
		d.clickLog = function(b) {
			f.clickLog(b)
		};
		d.showLog = function(b) {
			f.diaShowLog(b)
		};
		d.GCIDPlus = function() {
			f.baseInfo.ClickIDPlus()
		};
		d.ajaxlogr = function(b) {};
		d.getGTID =
			function() {
				return f.baseInfo.GTID
			};
		d.getTrackURL = function() {
			return f.baseInfo.trackURL
		};
		d._gaq = d._gaq || [];
		f.performanceLog();
		f.DMloadByTag()
	}
})(window);
