(function(win, undefined) {
	var doc = win.document,
		docElem = doc.documentElement;
	var easyDialog = function() {
		var body = doc.body,
			isIE = !-[1],
			isIE6 = isIE && /msie 6/.test(navigator.userAgent.toLowerCase()),
			uuid = 1,
			expando = "cache" + (+new Date + "").slice(-8),
			cacheData = {};
		var Dialog = function() {};
		Dialog.prototype = {
			getOptions: function(arg) {
				var i, options = {},
					defaults = {
						container: null,
						overlay: true,
						drag: true,
						fixed: true,
						follow: null,
						followX: 0,
						followY: 0,
						autoClose: 0,
						lock: false,
						callback: null
					};
				for (i in defaults) {
					options[i] = arg[i] !== undefined ? arg[i] : defaults[i]
				}
				Dialog.data("options", options);
				return options
			},
			setBodyBg: function() {
				if (body.currentStyle.backgroundAttachment !== "fixed") {
					body.style.backgroundImage = "url(about:blank)";
					body.style.backgroundAttachment = "fixed"
				}
			},
			appendIframe: function(elem) {
				elem.innerHTML =
					'<iframe style="position:absolute;left:0;top:0;width:100%;height:100%;z-index:-1;border:0 none;filter:alpha(opacity=0)"></iframe>'
			},
			setFollow: function(elem, follow, x, y) {
				follow = typeof follow === "string" ? doc.getElementById(follow) : follow;
				var style = elem.style;
				style.position = "absolute";
				style.left = Dialog.getOffset(follow, "left") + x + "px";
				style.top = Dialog.getOffset(follow, "top") + y + "px"
			},
			setPosition: function(elem, fixed) {
				var style = elem.style;
				style.position = isIE6 ? "absolute" : fixed ? "fixed" : "absolute";
				if (fixed) {
					if (isIE6) {
						style.setExpression("top",
							'fuckIE6=document.documentElement.scrollTop+document.documentElement.clientHeight/2+"px"')
					} else {
						style.top = "50%"
					}
					style.left = "50%"
				} else {
					if (isIE6) {
						style.removeExpression("top")
					}
					style.top = docElem.clientHeight / 2 + Dialog.getScroll("top") + "px";
					style.left = docElem.clientWidth / 2 + Dialog.getScroll("left") + "px"
				}
			},
			createOverlay: function() {
				var overlay = doc.createElement("div"),
					style = overlay.style;
				style.cssText =
					"margin:0;padding:0;border:none;width:100%;height:100%;background:#333;opacity:0.6;filter:alpha(opacity=60);z-index:9999;position:fixed;top:0;left:0;";
				if (isIE6) {
					body.style.height = "100%";
					style.position = "absolute";
					style.setExpression("top", 'fuckIE6=document.documentElement.scrollTop+"px"')
				}
				overlay.id = "overlay";
				return overlay
			},
			createDialogBox: function() {
				var dialogBox = doc.createElement("div");
				dialogBox.style.cssText = "margin:0;padding:0;border:none;z-index:10000;";
				dialogBox.id = "easyDialogBox";
				return dialogBox
			},
			createDialogWrap: function(tmpl) {
				var header = tmpl.header ?
					'<h4 class="easyDialog_title" id="easyDialogTitle"><a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">&times;</a>' +
					tmpl.header + "</h4>" : "",
					yesBtn = typeof tmpl.yesFn === "function" ? '<button class="btn_highlight" id="easyDialogYesBtn">' + (typeof tmpl
						.yesText === "string" ? tmpl.yesText : "确定") + "</button>" : "",
					noBtn = typeof tmpl.noFn === "function" || tmpl.noFn === true ?
					'<button class="btn_normal" id="easyDialogNoBtn">' + (typeof tmpl.noText === "string" ? tmpl.noText : "取消") +
					"</button>" : "",
					footer = yesBtn === "" && noBtn === "" ? "" : '<div class="easyDialog_footer">' + noBtn + yesBtn + "</div>",
					dialogTmpl = ['<div class="easyDialog_content">', header, '<div class="easyDialog_text">' + tmpl.content +
						"</div>", footer, "</div>"
					].join(""),
					dialogWrap = doc.getElementById("easyDialogWrapper"),
					rScript = /<[\/]*script[\s\S]*?>/gi;
				if (!dialogWrap) {
					dialogWrap = doc.createElement("div");
					dialogWrap.id = "easyDialogWrapper";
					dialogWrap.className = "easyDialog_wrapper"
				}
				dialogWrap.innerHTML = dialogTmpl.replace(rScript, "");
				return dialogWrap
			}
		};
		Dialog.data = function(elem, val, data) {
			if (typeof elem === "string") {
				if (val !== undefined) {
					cacheData[elem] = val
				}
				return cacheData[elem]
			} else if (typeof elem === "object") {
				var index = elem === win ? 0 : elem.nodeType === 9 ? 1 : elem[expando] ? elem[expando] : elem[expando] = ++uuid,
					thisCache = cacheData[index] ? cacheData[index] : cacheData[index] = {};
				if (data !== undefined) {
					thisCache[val] = data
				}
				return thisCache[val]
			}
		};
		Dialog.removeData = function(elem, val) {
			if (typeof elem === "string") {
				delete cacheData[elem]
			} else if (typeof elem === "object") {
				var index = elem === win ? 0 : elem.nodeType === 9 ? 1 : elem[expando];
				if (index === undefined) return;
				var isEmptyObject = function(obj) {
						var name;
						for (name in obj) {
							return false
						}
						return true
					},
					delteProp = function() {
						delete cacheData[index];
						if (index <= 1) return;
						try {
							delete elem[expando]
						} catch (e) {
							elem.removeAttribute(expando)
						}
					};
				if (val) {
					delete cacheData[index][val];
					if (isEmptyObject(cacheData[index])) {
						delteProp()
					}
				} else {
					delteProp()
				}
			}
		};
		Dialog.event = {
			bind: function(elem, type, handler) {
				var events = Dialog.data(elem, "e" + type) || Dialog.data(elem, "e" + type, []);
				events.push(handler);
				if (events.length === 1) {
					var eventHandler = this.eventHandler(elem);
					Dialog.data(elem, type + "Handler", eventHandler);
					if (elem.addEventListener) {
						elem.addEventListener(type, eventHandler, false)
					} else if (elem.attachEvent) {
						elem.attachEvent("on" + type, eventHandler)
					}
				}
			},
			unbind: function(elem, type, handler) {
				var events = Dialog.data(elem, "e" + type);
				if (!events) return;
				if (!handler) {
					events = undefined
				} else {
					for (var i = events.length - 1, fn = events[i]; i >= 0; i--) {
						if (fn === handler) {
							events.splice(i, 1)
						}
					}
				}
				if (!events || !events.length) {
					var eventHandler = Dialog.data(elem, type + "Handler");
					if (elem.addEventListener) {
						elem.removeEventListener(type, eventHandler, false)
					} else if (elem.attachEvent) {
						elem.detachEvent("on" + type, eventHandler)
					}
					Dialog.removeData(elem, type + "Handler");
					Dialog.removeData(elem, "e" + type)
				}
			},
			eventHandler: function(elem) {
				return function(event) {
					event = Dialog.event.fixEvent(event || win.event);
					var type = event.type,
						events = Dialog.data(elem, "e" + type);
					for (var i = 0, handler; handler = events[i++];) {
						if (handler.call(elem, event) === false) {
							event.preventDefault();
							event.stopPropagation()
						}
					}
				}
			},
			fixEvent: function(e) {
				if (e.target) return e;
				var event = {},
					name;
				event.target = e.srcElement || document;
				event.preventDefault = function() {
					e.returnValue = false
				};
				event.stopPropagation = function() {
					e.cancelBubble = true
				};
				for (name in e) {
					event[name] = e[name]
				}
				return event
			}
		};
		Dialog.capitalize = function(str) {
			var firstStr = str.charAt(0);
			return firstStr.toUpperCase() + str.replace(firstStr, "")
		};
		Dialog.getScroll = function(type) {
			var upType = this.capitalize(type);
			return docElem["scroll" + upType] || body["scroll" + upType]
		};
		Dialog.getOffset = function(elem, type) {
			var upType = this.capitalize(type),
				client = docElem["client" + upType] || body["client" + upType] || 0,
				scroll = this.getScroll(type),
				box = elem.getBoundingClientRect();
			return Math.round(box[type]) + scroll - client
		};
		Dialog.drag = function(target, moveElem) {
			var clearSelect = "getSelection" in win ? function() {
					win.getSelection().removeAllRanges()
				} : function() {
					try {
						doc.selection.empty()
					} catch (e) {}
				},
				self = this,
				event = self.event,
				isDown = false,
				newElem = isIE ? target : doc,
				fixed = moveElem.style.position === "fixed",
				_fixed = Dialog.data("options").fixed;
			var down = function(e) {
				isDown = true;
				var scrollTop = self.getScroll("top"),
					scrollLeft = self.getScroll("left"),
					edgeLeft = fixed ? 0 : scrollLeft,
					edgeTop = fixed ? 0 : scrollTop;
				Dialog.data("dragData", {
					x: e.clientX - self.getOffset(moveElem, "left") + (fixed ? scrollLeft : 0),
					y: e.clientY - self.getOffset(moveElem, "top") + (fixed ? scrollTop : 0),
					el: edgeLeft,
					et: edgeTop,
					er: edgeLeft + docElem.clientWidth - moveElem.offsetWidth,
					eb: edgeTop + docElem.clientHeight - moveElem.offsetHeight
				});
				if (isIE) {
					if (isIE6 && _fixed) {
						moveElem.style.removeExpression("top")
					}
					target.setCapture()
				}
				event.bind(newElem, "mousemove", move);
				event.bind(newElem, "mouseup", up);
				if (isIE) {
					event.bind(target, "losecapture", up)
				}
				e.stopPropagation();
				e.preventDefault()
			};
			event.bind(target, "mousedown", down);
			var move = function(e) {
				if (!isDown) return;
				clearSelect();
				var dragData = Dialog.data("dragData"),
					left = e.clientX - dragData.x,
					top = e.clientY - dragData.y,
					et = dragData.et,
					er = dragData.er,
					eb = dragData.eb,
					el = dragData.el,
					style = moveElem.style;
				style.marginLeft = style.marginTop = "0px";
				style.left = (left <= el ? el : left >= er ? er : left) + "px";
				style.top = (top <= et ? et : top >= eb ? eb : top) + "px";
				e.stopPropagation()
			};
			var up = function(e) {
				isDown = false;
				if (isIE) {
					event.unbind(target, "losecapture", arguments.callee)
				}
				event.unbind(newElem, "mousemove", move);
				event.unbind(newElem, "mouseup", arguments.callee);
				if (isIE) {
					target.releaseCapture();
					if (isIE6 && _fixed) {
						var top = parseInt(moveElem.style.top) - self.getScroll("top");
						moveElem.style.setExpression("top", "fuckIE6=document.documentElement.scrollTop+" + top + '+"px"')
					}
				}
				e.stopPropagation()
			}
		};
		var timer, escClose = function(e) {
				if (e.keyCode === 27) {
					extend.close()
				}
			},
			clearTimer = function() {
				if (timer) {
					clearTimeout(timer);
					timer = undefined
				}
			};
		var extend = {
			open: function() {
				var $ = new Dialog,
					options = $.getOptions(arguments[0] || {}),
					event = Dialog.event,
					docWidth = docElem.clientWidth,
					docHeight = docElem.clientHeight,
					self = this,
					overlay, dialogBox, dialogWrap, boxChild;
				clearTimer();
				if (options.overlay) {
					overlay = doc.getElementById("overlay");
					if (!overlay) {
						overlay = $.createOverlay();
						body.appendChild(overlay);
						if (isIE6) {
							$.appendIframe(overlay)
						}
					}
					overlay.style.display = "block"
				}
				if (isIE6) {
					$.setBodyBg()
				}
				dialogBox = doc.getElementById("easyDialogBox");
				if (!dialogBox) {
					dialogBox = $.createDialogBox();
					body.appendChild(dialogBox)
				}
				if (options.follow) {
					var follow = function() {
						$.setFollow(dialogBox, options.follow, options.followX, options.followY)
					};
					follow();
					event.bind(win, "resize", follow);
					Dialog.data("follow", follow);
					if (overlay) {
						overlay.style.display = "none"
					}
					options.fixed = false
				} else {
					$.setPosition(dialogBox, options.fixed)
				}
				dialogBox.style.display = "block";
				dialogWrap = typeof options.container === "string" ? doc.getElementById(options.container) : $.createDialogWrap(
					options.container);
				boxChild = dialogBox.getElementsByTagName("*")[0];
				if (!boxChild) {
					dialogBox.appendChild(dialogWrap)
				} else if (boxChild && dialogWrap !== boxChild) {
					boxChild.style.display = "none";
					body.appendChild(boxChild);
					dialogBox.appendChild(dialogWrap)
				}
				dialogWrap.style.display = "block";
				var eWidth = dialogWrap.offsetWidth,
					eHeight = dialogWrap.offsetHeight,
					widthOverflow = eWidth > docWidth,
					heigthOverflow = eHeight > docHeight;
				dialogWrap.style.marginTop = dialogWrap.style.marginRight = dialogWrap.style.marginBottom = dialogWrap.style.marginLeft =
					"0px";
				if (!options.follow) {
					dialogBox.style.marginLeft = "-" + (widthOverflow ? docWidth / 2 : eWidth / 2) + "px";
					dialogBox.style.marginTop = "-" + (heigthOverflow ? docHeight / 2 : eHeight / 2) + "px"
				} else {
					dialogBox.style.marginLeft = dialogBox.style.marginTop = "0px"
				}
				if (isIE6 && !options.overlay) {
					dialogBox.style.width = eWidth + "px";
					dialogBox.style.height = eHeight + "px"
				}
				var closeBtn = doc.getElementById("closeBtn"),
					dialogTitle = doc.getElementById("easyDialogTitle"),
					dialogYesBtn = doc.getElementById("easyDialogYesBtn"),
					dialogNoBtn = doc.getElementById("easyDialogNoBtn");
				if (dialogYesBtn) {
					event.bind(dialogYesBtn, "click", function(event) {
						if (options.container.yesFn.call(self, event) !== false) {
							self.close()
						}
					})
				}
				if (dialogNoBtn) {
					var noCallback = function(event) {
						if (options.container.noFn === true || options.container.noFn.call(self, event) !== false) {
							self.close()
						}
					};
					event.bind(dialogNoBtn, "click", noCallback);
					if (closeBtn) {
						event.bind(closeBtn, "click", noCallback)
					}
				} else if (closeBtn) {
					event.bind(closeBtn, "click", self.close)
				}
				if (!options.lock) {
					event.bind(doc, "keyup", escClose)
				}
				if (options.autoClose && typeof options.autoClose === "number") {
					timer = setTimeout(self.close, options.autoClose)
				}
				if (options.drag && dialogTitle && !widthOverflow && !heigthOverflow) {
					dialogTitle.style.cursor = "move";
					Dialog.drag(dialogTitle, dialogBox)
				}
				if (!options.follow && !options.fixed) {
					var resize = function() {
						$.setPosition(dialogBox, false)
					};
					if (!widthOverflow && !heigthOverflow) {
						event.bind(win, "resize", resize)
					}
					Dialog.data("resize", resize)
				}
				Dialog.data("dialogElements", {
					overlay: overlay,
					dialogBox: dialogBox,
					closeBtn: closeBtn,
					dialogTitle: dialogTitle,
					dialogYesBtn: dialogYesBtn,
					dialogNoBtn: dialogNoBtn
				})
			},
			close: function() {
				var options = Dialog.data("options"),
					elements = Dialog.data("dialogElements"),
					event = Dialog.event;
				clearTimer();
				if (options.overlay && elements.overlay) {
					elements.overlay.style.display = "none"
				}
				elements.dialogBox.style.display = "none";
				if (isIE6) {
					elements.dialogBox.style.removeExpression("top")
				}
				if (elements.closeBtn) {
					event.unbind(elements.closeBtn, "click")
				}
				if (elements.dialogTitle) {
					event.unbind(elements.dialogTitle, "mousedown")
				}
				if (elements.dialogYesBtn) {
					event.unbind(elements.dialogYesBtn, "click")
				}
				if (elements.dialogNoBtn) {
					event.unbind(elements.dialogNoBtn, "click")
				}
				if (!options.follow && !options.fixed) {
					event.unbind(win, "resize", Dialog.data("resize"));
					Dialog.removeData("resize")
				}
				if (options.follow) {
					event.unbind(win, "resize", Dialog.data("follow"));
					Dialog.removeData("follow")
				}
				if (!options.lock) {
					event.unbind(doc, "keyup", escClose)
				}
				if (typeof options.callback === "function") {
					options.callback.call(extend)
				}
				Dialog.removeData("options");
				Dialog.removeData("dialogElements")
			}
		};
		return extend
	};
	var loaded = function() {
			win.easyDialog = easyDialog()
		},
		doScrollCheck = function() {
			if (doc.body) return;
			try {
				docElem.doScroll("left")
			} catch (e) {
				setTimeout(doScrollCheck, 1);
				return
			}
			loaded()
		};
	(function() {
		if (doc.body) {
			loaded()
		} else {
			if (doc.addEventListener) {
				doc.addEventListener("DOMContentLoaded", function() {
					doc.removeEventListener("DOMContentLoaded", arguments.callee, false);
					loaded()
				}, false);
				win.addEventListener("load", loaded, false)
			} else if (doc.attachEvent) {
				doc.attachEvent("onreadystatechange", function() {
					if (doc.readyState === "complete") {
						doc.detachEvent("onreadystatechange", arguments.callee);
						loaded()
					}
				});
				win.attachEvent("onload", loaded);
				var toplevel = false;
				try {
					toplevel = win.frameElement == null
				} catch (e) {}
				if (docElem.doScroll && toplevel) {
					doScrollCheck()
				}
			}
		}
	})()
})(window, undefined);
$(function() {
	window.LimitButton = function(config) {
		var defaultConfig = {
			validateStateUrl: "https://passport.58.com/wukong/userstate",
			buttonLimitWrapID: config.button,
			openCitys: []
		};
		this._mixin = function(b, e) {
			for (var k in e) {
				e.hasOwnProperty(k) && (b[k] = e[k])
			}
			return b
		};
		this.defaultConfig = defaultConfig;
		this.config = this._mixin(defaultConfig, config);
		this.init(this.config)
	};
	LimitButton.prototype = {
		constructor: LimitButton,
		init: function(config) {
			var that = this;
			that.loginState = this.validateState();
			this.initEvents();
			this.setDomain();
			if (config.showIframeDialog) {
				that.showIframeDialog = config.showIframeDialog
			}
		},
		setDomain: function() {
			document.domain = "58.com"
		},
		isArray: function(obj) {
			return Object.prototype.toString.call(obj) === "[object Array]"
		},
		getNextUrl: function() {
			return this.config.button.attr("href")
		},
		isOpenCity: function(cityid) {
			var that = this;
			if (this.isArray(that.config.openCitys) && that.config.openCitys.length == 0) {
				return true
			} else {
				for (var i = 0, len = that.config.openCitys.length; i < len; i++) {
					if (cityid == that.config.openCitys[i]) {
						return true
					}
				}
			}
			return false
		},
		initEvents: function() {
			var that = this;
			var cityid, isOpenCity;
			if (this.isArray(____json4fe["locallist"])) {
				cityid = ____json4fe["locallist"][0]["dispid"]
			} else {
				cityid = ____json4fe["locallist"]["dispid"]
			}
			isOpenCity = that.isOpenCity(cityid);
			$(this.config.buttonLimitWrapID).bind("click", function(event) {
				if (isOpenCity) {
					that.loginState = that.validateState();
					if (that.loginState.isLogin == "-1") {
						event.preventDefault();
						that.notLogin()
					} else if (that.loginState.isLogin == "0" && that.loginState.isBind == "-1") {
						event.preventDefault();
						that.loginNotBind()
					}
				}
			})
		},
		loginNotBind: function() {
			var initPollClickLog = function() {
				__timer && clearTimeout(timer);
				var callee = arguments.callee;
				if (typeof clickLog != "undefined") {
					clickLog("from=iframe_bind_show")
				} else {
					var __timer = setTimeout(function() {
						callee()
					}, 100)
				}
			};
			initPollClickLog();
			var postUrl = $("a.postBtn").attr("href");
			if (postUrl) {
				window.location.assign(postUrl)
			} else {
				easyDialog.open({
					container: {
						content: '<span title="关闭窗口" class="close_btn" id="closeBtn">×</span><iframe style="width:100%;height:95%;border:none;" frameborder="no" src="http://passport.58.com/swk/preLogin"></iframe>'
					},
					fixed: true,
					drag: false
				});
				$("#closeBtn").bind("click", function() {
					var initPollClickLog = function() {
						__timer && clearTimeout(timer);
						var callee = arguments.callee;
						if (typeof clickLog != "undefined") {
							clickLog("from=iframe_bind_close")
						} else {
							var __timer = setTimeout(function() {
								callee()
							}, 100)
						}
					};
					initPollClickLog()
				})
			}
		},
		notLogin: function() {
			var initPollClickLog = function() {
				__timer && clearTimeout(timer);
				var callee = arguments.callee;
				if (typeof clickLog != "undefined") {
					clickLog("from=iframe_login_show")
				} else {
					var __timer = setTimeout(function() {
						callee()
					}, 100)
				}
			};
			initPollClickLog();
			console.log("this:", $(this));
			var postUrl = $("a.postBtn").attr("href");
			if (postUrl) {
				window.location.assign(postUrl)
			} else {
				easyDialog.open({
					container: {
						content: '<span title="关闭窗口" class="close_btn" id="closeBtn">×</span><iframe style="width:100%;height:95%;border:none;" frameborder="no" src="http://passport.58.com/swk/preLogin"></iframe>'
					},
					fixed: true,
					drag: false
				});
				$("#closeBtn").bind("click", function() {
					var initPollClickLog = function() {
						__timer && clearTimeout(timer);
						var callee = arguments.callee;
						if (typeof clickLog != "undefined") {
							clickLog("from=iframe_login_close")
						} else {
							var __timer = setTimeout(function() {
								callee()
							}, 100)
						}
					};
					initPollClickLog()
				})
			}
		},
		validateState: function() {
			var that = this;
			$.ajax({
				url: that.config.validateStateUrl,
				type: "get",
				dataType: "jsonp",
				success: function(res) {
					window.beforelogindata = res;
					that.loginState = res
				},
				error: function(err) {
					console.log(err)
				}
			});
			return that.loginState
		},
		closeDialog: function() {
			easyDialog.close()
		}
	};
	return LimitButton
});
