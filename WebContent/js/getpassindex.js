var getpass = {
	
	iurl: '/puckart',
	
	countdown: 60,
		
	init: function(){
		
		var me = this;
		
		$('.container').delegate('.btn-send', 'click', function(){
			me.sendVerifyCode();
        });
    	
    	layui.use('form', function(){
    		
    		var form = layui.form();
    		
    		//提交校验
    		form.verify({
    			account: function(value){
    				if(value.length==0){
    					return '请您输入用户名/邮箱/手机';
    				}
    				if(value.length>64){
    					return '不能超过64个字符';
    				}
    			},
    			verifyCode: function(value){
    				if(value.length==0){
    					return '验证码不能为空';
    				}
    			},
    			password: function(value){
    				if(value.length==0){
    					return '密码不能为空';
    				}
    			},
    			confirmPwd: function(value){
    				if(value.length==0){
    					return '确认密码不能为空';
    				}
    				var pwd = $('#password').val();
    				if(value!=pwd){
    					return '两次输入的密码必须一致';
    				}
    			}
    		}); 
    		//提交step1
    		form.on('submit(submitAccount)', function(data){
		    
    			me.submitAccount(data.field);
		    
    			return false;
    		});
			//提交step2
			form.on('submit(submitVerify)', function(data){
			    
			    me.submitVerify(data.field);
			    
			    return false;
			 });
			 //提交step3
			 form.on('submit(submitReset)', function(data){
				me.submitReset(data.field);
				    
				return false;
			 });
		  
		});
	},
	
	/**
	 * 提交用户名/邮箱/手机
	 * @param data
	 */
	submitAccount:function(field){
		
		var me = this;
		
		var url = me.iurl + "/getpassindex/submitAccount.action";  
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : field,
            success : function(result) {
                
                if(result && result.success){
                	
                	var userId = result.userId;
                	$('#userId').val(userId);
                	$('#step-one-form').submit();
        		    
                }else{
                    //服务端返回的错误信息
                    if(result.errorMessage){
                        me.showAlertDlg(result.errorMessage);
                    }
                }
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	submitVerify:function(field){
		
		var me = this;
				
		var url = me.iurl + "/getpassindex/checkVerifyCode.action";  
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : field,
            success : function(result) {
                
                if(result && result.success){
                	
                	$('#step-two-form').submit();
        		    
                }else{
                    //服务端返回的错误信息
                    if(result.errorMessage){
                        me.showAlertDlg(result.errorMessage);
                    }
                }
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	submitReset:function(field){
		
		var me = this;
				
		var url = me.iurl + "/getpassindex/submitReset.action";  
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : field,
            success : function(result) {
                
                if(result && result.success){
                	
                	layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.alert('密码重置成功！', {icon: 1}, function(index){
                        	  
                    	  layer.close(index);
                    	  
                    	  window.location.href = '/user/login.html';
                    	});
                	});
                	
        		    
                }else{
                    //服务端返回的错误信息
                    if(result.errorMessage){
                        me.showAlertDlg(result.errorMessage);
                    }
                }
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	sendVerifyCode:function(){
		
		var me = this;
		
		if(me.countdown != 60){
			return;
		}

		var url = me.iurl + "/getpassindex/sendVerifyCode.action";
		
		var userId = $('#userId').val();
		var verifyType = $('#verifyType').val();
		
		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : "userId="+ userId + "&verifyType="+verifyType,
            success : function(result) {
                
                if(result && result.success){
                	
                	var sendBtn = $('.btn-send');
                	
                	me.setTime(sendBtn);
                	
                }else{
                    //服务端返回的错误信息
                    if(result.errorMessage){
                        me.showAlertDlg(result.errorMessage);
                    }
                }
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	setTime: function(sendBtn){
		
		var me = this;
		
		if (me.countdown == 0) {
    		sendBtn.removeClass("layui-btn-disabled");    
    		sendBtn.html("发送验证码"); 
    		me.countdown = 60; 
    	} else {
    		sendBtn.addClass('layui-btn-disabled');;
    		sendBtn.html("重新发送(" + me.countdown + ")");
    		me.countdown--;
    		
    		setTimeout(function() { 
        		me.setTime(sendBtn);
        	},1000)
    	}
	},
	
	goToLogin: function(){
    	window.location.href = '/user/login.html';
    },
    
    showAlertDlg: function(msg){
    	
    	layer.closeAll('page');
    	
        var html = '<div class="tip-dlg">'
            + '<span style="margin-left: 20px;">'+msg+'</span>'
            + '</div>';

        layui.use('layer', function(){
            var layer = layui.layer;
	        layer.open({
	        	title: '提示',
	          	type: 1,
	          	area: ['350px', '120px'],
	          	shadeClose: true, //点击遮罩关闭
	          	content: html
	          });
        });
    }
};

$(function(){	
	getpass.init();    
});
