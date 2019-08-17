/**
 * 首页js
 */
define(["jquery","bootstrap","validatMessage","csrf","base64"],function($){
	layui.use(['form',"laypage","layer"], function(){
		var form = layui.form,laypage = layui.laypage,layer = layui.layer;
		
		/* 记住密码  */
		//1.localStorage
		//判断是否有，有的话，取第一个
		if (window.localStorage) {
			if (window.localStorage.getItem("checkFlag") == "true") {
				$("#userName").val(Base64.decode(window.localStorage.getItem(Base64.encode("dchipUserName"))));
				$("#password").val(Base64.decode(window.localStorage.getItem(Base64.encode("dchipPWD"))));
				$(".remember").prop('checked', true);
			} else {
				$(".remember").prop('checked', false);
			}
		}else{
			$(".remember").attr("disabled","disabled");
			$(".rem_msg").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		//“记住密码”监听事件
		$(".remember").change(function () {
			//判断是否勾选
			var checkFlag = $(this).prop('checked');
			if (checkFlag) { //勾选状态下
				if (!window.localStorage) { //浏览器不支持记住密码这个功能，弹出提示信息，清除勾选状态
					$(".rem_msg").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				}
			}
		});


//		//2. 获得coolie 的值
//		function getCookie(name){    
//		   var cookieArray=document.cookie.split("; "); // 得到分割的cookie名值对
//		   var cookie=new Object();    
//		   for (var i=0;i<cookieArray.length;i++){    
//		      var arr=cookieArray[i].split("=");       // 将名和值分开
//		      if(arr[0]==name)return unescape(arr[1]); // 如果是指定的cookie，则返回它的值
//		   } 
//		   return ""; 
//		}
		
		
		
		//权限选择
		$('.dropdown-menu li').click(function(){
			$(this).siblings().removeClass("active");
			$(this).addClass("active");
			var con = $(this).children().html();
			$("#dLabel").html(con+"<span class='caret'></span>");
			$("#dchiptype").val($(this).attr("id"));
			
			//移除error-css
			$("#dLabel").css({"border":"","background-color":"","color":""});
		})
		
		//下拉触发
		$('.dropdown').on('show.bs.dropdown', function () {
		  // do something…
		})
		
//		  // 记住密码功能
//        var str = getCookie("loginUser");
//        if(str.length!= 0){
//        	$(".remember").prop("checked", true); 
//        }
//        var username = str.split("#")[0];
//        var password = str.split("#")[1];
//        // 自动填充用户名和密码
//        $("#userName").val(username);
//        $("#password").val(password);
        
		
		$("#loginform").validate({
	// onfocusout: true, //失去焦点
			rules: {
				userName: {
					required:true,
					remote: {
					        url: contextPath+"/admin/validateHasUserName",
					        type: "post",
					        data: {
					        	userName: function() {
					            return $( "#userName" ).val();
					          }
					        },
					        dataFilter:function(rs,type){
					        	return rs;
					        }
					      
					}
				},
				password: {
					required: true,
					minlength: 6,
					remote: {
				        url: contextPath+"/admin/validatePwd",
				        type: "post",
				        data: {
				        	userName: function() {
						        return $( "#userName" ).val();
						       },
				        	password: function() {
				            return $( "#password" ).val();
				          }
				        },
				        dataFilter:function(rs,type){
				        	return rs;
				        }
				      
					}
				}
				
			},
			messages: {
				userName: {
					required:"用户名不能为空!",
					remote:"用户名不存在!"
				},
				password: {
					required: "密码不能为空!",
					minlength: "密码长度不能少于6位!",
					remote:"密码不正确！"
				}
			},
			errorClass: "invalid",
			success: "valid",
			errorPlacement: function(error, element) {
				/*
				 * $( element ) .closest( "form" ) .find( "label[for='" +
				 * element.attr( "id" ) + "']" ) .append( error );
				 */
				$(element).css({"border":"1px solid #ff00009e","background-color":"#ff000017","color":"red"});
				$( element ).closest( ".control-group" ).append( error );
			},
			errorElement: "span",
			success:function(el){
				var id = el.attr("id").split("-")[0];
				$("#"+id).removeAttr("style");
				el.remove();
			},
			submitHandler:function(form) {
				var obj=$("#dchiptype").val();
				var userName = $("#userName").val();
				var passWord = $("#password").val();
				if(obj != null && obj != ""){
					$.ajax({
						  url: contextPath+"/admin/loginIn",
						  dataType:'json',
						  type:'post',
						  data : {
								userName : userName,
								password : passWord,
//								remember:$(".remember").is(':checked')?1:0,
								dchipId:obj
							},
						  complete: function(XHR, TS){
							  var returnObj = eval('(' + XHR.responseText + ')');
							  if(returnObj.code != 200){
								  layer.open({
									  title: '提示',
									  scrollbar:false,
									  offset: ['200px'],
									  content: returnObj.msg,
									}); 
							  }else{
								  var checkFlag = $(".remember").prop('checked');
								  if (checkFlag) {//勾选状态下，需要修改或新增
										localStorage.setItem("checkFlag", "true");
										localStorage.setItem(Base64.encode("dchipUserName"), Base64.encode(userName));
										localStorage.setItem(Base64.encode("dchipPWD"), Base64.encode(passWord));
								  } else {
										if (localStorage.getItem(Base64.encode("dchipUserName"))) {
											localStorage.setItem("checkFlag", "false");
											localStorage.removeItem(Base64.encode("dchipUserName"));
											localStorage.removeItem(Base64.encode("dchipPWD"));
										}
								  }

								  window.location.href = contextPath+returnObj.returnUrl+"?id="+obj; 
							  }
						  }
					});
				}else{
					$("#dLabel").css({"border":"1px solid #ff00009e","background-color":"#ff000017","color":"red"});
					$(".caret").css("color", "red");
//					$("#dLabel").closest( ".control-group" ).append( "<span style='color:red;font-size:13px;'>请选择</span>" );
				}
				return false;
		    }
		});
		$("#userName").keydown(function(){
			$("#password").val("");
		});
		
		// 忘记密码
		$(".forget").click(function(){
			var html = `<div style="margin-top:20px;text-align: center;">
						  <strong><label>请在此处留名：</label></strong>
						  <div class="layui-inline" style="display: inline-block;">
						    <input class="layui-input" id="name" style="border: 1px solid #e6e6e6;">
						  </div>
						</div>`;
			layer.open({
			  title:"高级验证",
			  type: 1,
			  area: ['400px', '150px'],
			  fixed: true, // 固定
			  maxmin: true,
			  content: html,
			  btn:["确定"],
			  btnAlign: 'c',
			  anim: 1,
			  yes: function(index, layero){
				    // 按钮【按钮一】的回调
				  console.log(index);
				  var name = $("#name").val();
				  
				  // ajax 高级验证
				  $.post(contextPath+"/admin/seniorValidate",{"name":name},function(rt){
					 
				  },"json");
				  
					$.ajax({
						  url: contextPath+"/admin/seniorValidate",
						  dataType:'json',
						  type:'post',
						  data : {
							  name : name
							},
						  complete: function(XHR, TS){
							  var returnObj = eval('(' + XHR.responseText + ')');
							  if(returnObj.code != 200){
								  layer.msg(returnObj.msg, {
									  icon: 2,
									  time: 2000 // 2秒自动关闭（
									}, function(){
										layer.close(this);
								  }); 
							  }else{
								  layer.msg(returnObj.msg, {
									  icon: 1,
									  time: 10000 // 10秒自动关闭（
									}, function(){
										layer.closeAll();
								  }); 
							  }
						  }
					});
			  }
			});
		})
	

/*
 * //大写锁定已打开 var txt = $("#password"); function show() { var ele = $("#tip");
 * $(ele).css("left", txt.offset().left + 2); var top = txt.offset().top +
 * txt.height() + 5; $(ele).css("top", top); $(ele).slideDown(200); } var
 * isCapslockOn; function CapsLock_keyPress(e) { var e = event || window.event;
 * var keyCode = e.keyCode || e.which;//按键的keyCode。 var isShift = e.shiftKey ||
 * (keyCode == 16) || false;//shift键是否按住。 if ( ((keyCode >= 65 && keyCode <= 90) &&
 * !isShift) // CapsLock打开，且没有按住shift键。 || ((keyCode >= 97 && keyCode <= 122) &&
 * isShift))// CapsLock打开，且按住shift键。 isCapslockOn = true; else isCapslockOn =
 * false; } function CapsLock_keydown(e) { var keyCode = window.event ?
 * e.keyCode : e.which; if (keyCode == 20 && isCapslockOn == true) isCapslockOn =
 * false; else if (keyCode == 20 && isCapslockOn == false) isCapslockOn = true; }
 * function tip() { var ele = $("#tip"); if (isCapslockOn && ele.is(":hidden"))
 * show(); else if (!isCapslockOn && !ele.is(":hidden")) ele.hide(); }
 * //keyPress可以判断当前CapsLock状态，但不能捕获CapsLock键。
 * $(doc).keypress(CapsLock_keyPress); //keyDown可以捕获CapsLock键，但不能判断CapsLock的状态。
 * $(doc).keydown(CapsLock_keydown);
 * 
 * 
 * txt.keyup(tip).focus(function () { if (isCapslockOn) show(); }).blur(function () {
 * $("#tip").hide(); });
 */
	
	})
})


