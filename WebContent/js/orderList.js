var orderListObj = {
	
	iurl: '/puckart',
		
	init: function(){
		
		var me = this;
		
		me.bindEvents();
		
	},
	
	/**
	 * 绑定事件
	 */
	bindEvents: function(){
		
		var me = this;
		
		//付款
    	$(document).on('click', '.operate .pay-order', function(){
    		
    		var orderId = $(this).attr('data-bind');
    		
    		window.location.href = me.iurl+'/payment/pay.action?orderId='+orderId;

        });
		
		//取消订单
    	$(document).on('click', '.operate .cancel-order', function(){
    		
    		var orderId = $(this).attr('data-bind');
    		
    		layui.use('layer', function(){
                var layer = layui.layer;
                layer.confirm('您确定要取消订单吗?', {icon: 3, title:'提示'}, function(index){
                	
                    me.cancelOrder(orderId);
            	  
                    layer.close(index);
            	});
        	});

        });
    	
    	//确认订单
    	$(document).on('click', '.operate .confirm-order', function(){
    		
    		var orderId = $(this).attr('data-bind');
    		
    		layui.use('layer', function(){
                var layer = layui.layer;
                layer.confirm('请确认是否已收到货?', {icon: 3, title:'提示'}, function(index){
                	
                    me.confirmOrder(orderId);
            	  
                    layer.close(index);
            	});
        	});

        });

	},
	
	confirmOrder: function(orderId){
		
		var me = this;

		var param = "?rd="+Math.random()
				+ "&orderId="+orderId;
		
		var url = me.iurl + "/order/confirmOrder.action";
		
		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : param,
            success : function(result) {
            	
                if(result){
                	
                	if(result.l == 1){
                		me.goToLogin();
                        return;
                	}
                	
                	if(result.auth == 401){
                		me.showAlertDlg("无权限操作!");
                		return;
                	}
                }
                
                if(result && result.success){
                	
                	layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.alert('确认收货成功！', {icon: 1}, function(index){
                        	  
                        	window.location.href = me.iurl+'/order/list.action';
                        	
                		    layer.closeAll('page');
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
            	me.showAlertDlg("网络发生异常");
            }
        });
	},
	
	
	cancelOrder: function(orderId){
		
		var me = this;

		var param = "?rd="+Math.random()
				+ "&orderId="+orderId;
		
		var url = me.iurl + "/order/cancelOrder.action";
		
		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : param,
            success : function(result) {
            	
                if(result){
                	
                	if(result.l == 1){
                		me.goToLogin();
                        return;
                	}
                	
                	if(result.auth == 401){
                		me.showAlertDlg("无权限操作!");
                		return;
                	}
                }
                
                if(result && result.success){
                	
                	layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.alert('订单已取消！', {icon: 1}, function(index){
                        	  
                        	window.location.href = me.iurl+'/order/list.action';
                        	
                		    layer.closeAll('page');
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
            	me.showAlertDlg("网络发生异常");
            }
        });
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
	orderListObj.init();    
});
