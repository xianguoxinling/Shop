var sellerOrdersObj = {
	
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
		
		//发货
    	$(document).on('click', '.operate .send', function(){
    		
    		var orderId = $(this).attr('data-bind');
    		
    		me.openDeliveryLayer(orderId);
    		
        });
    	
    	//表单
    	layui.use('form', function(){
    		var form = layui.form();
    		form.verify({
    			deliveryCompany: function(value){
    				if(value.length==0){
    					return '物流公司名称不能为空';
    				}
    				if(value.length>64){
    					return '物流公司名称不能超过64个字符';
    				}
    			},
    			deliveryNumber: function(value){
    				if(value.length==0){
    					return '物流单号不能为空';
    				}
    				if(value.length>128){
    					return '物流单号不能超过128个字符';
    				}
    			},
    			remark: function(value){
    				if(value.length>128){
    					return '备注信息不能超过128个字符';   					
    				}
    			}
    		}); 
		  //监听提交
		  form.on('submit(submitDeliveryForm)', function(data){
		    
			  layui.use('layer', function(){
				  var layer = layui.layer;
	              layer.confirm('是否确认提交?', {icon: 3, title:'提示'}, function(index){
	                	
	                    me.saveDeliveryInfo(data.field);
	            	  
	                    layer.close(index);
	              });
			  });
		    
			  return false;
		  });
		  
		});
    	
	},
	
	saveDeliveryInfo :function(field){
		
		var me = this;
		
		var url = me.iurl + "/store/saveDeliveryInfo.action";  
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : field,
            success : function(result) {
            	
                if(result){
                	if(result.l == 1){
                        me.goToLogin();
                        return;
                    }
                	
                	if(result.auth == 401){
                		me.showAlertDlg("无权限操作");
                		return;
                	}
                }
                
                if(result && result.success){
                	
                	
                	layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.alert('提交成功！', {icon: 1}, function(index){
                        	  
                        	window.location.href = '/puckart/store/seller_orders.action';
                        	
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
            }
        });
	},
	
	openDeliveryLayer : function(orderId){
		
		var me = this;
		
		var url = me.iurl + "/store/fetchDeliveryInfo.action";
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : {"orderId":orderId},
            success : function(result) {
            	
                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }
                
                if(result){
                	
                	if(result.l == 1){
                        me.goToLogin();
                        return;
                    }
                	
                	if(result.auth == 401){
                		me.showAlertDlg("无权限操作");
                		return;
                	}
                	
                	var html = me.generateLayerHtml(result.deliveryInfo,orderId);
                	
                	layui.use('layer', function(){
        	            var layer = layui.layer;
        	            layer.open({
        	                title: '填写物流信息',
        	                type: 1,
        	                area: ['600px','400px'],
        	                resize: false,
        	                move:false,
        	                content: html
        	            });
        	            layer.ready(function(){
        	            	
        	            });
        	        });
                }
            },
            error:function(XMLHttpResponse ){
            }
        });		
	},
	
	generateLayerHtml: function(delivery,orderId){
		
		var deliveryId='',
			deliveryCompany='',
			deliveryNumber='',
			remark='';
		
		if(delivery){
			deliveryId = ' value="'+delivery.deliveryId+'"';
			orderId = ' value="'+delivery.orderId+'"';
			deliveryCompany = ' value="'+delivery.deliveryCompany+'"';
			deliveryNumber = ' value="'+delivery.deliveryNumber+'"';
			remark = ' value="'+delivery.remark+'"';
		}else{
			orderId = ' value="'+orderId+'"';
		}		
		
		var html = 
			'<div class="layui-form delivery-form-layer">'+ 
				'<input type="hidden" name="deliveryId" id="deliveryId" '+ deliveryId +'/>'+
				'<input type="hidden" name="orderId" id="orderId" '+ orderId +'/>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label"><span style="color:red">*</span>物流公司：</label>'+
					'<div class="layui-input-block deliveryCompany-tx">'+
						'<input type="text" name="deliveryCompany" required lay-verify="deliveryCompany"'+
							' placeholder="请输入物流公司名称" autocomplete="off" class="layui-input"'+ deliveryCompany +'/>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label"><span style="color:red">*</span>物流单号：</label>'+
					'<div class="layui-input-block deliveryNumber-tx">'+
						'<input type="text" name="deliveryNumber" required lay-verify="deliveryNumber"'+
							' placeholder="请输入物流单号" autocomplete="off" class="layui-input"'+ deliveryNumber +'/>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label">备注：</label>'+
					'<div class="layui-input-block remark-tx">'+
						'<input type="text" name="remark" required lay-verify="remark"'+
							' placeholder="备注信息（选填）" autocomplete="off" class="layui-input"'+ remark +'/>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<div class="layui-input-block">'+
						'<button class="layui-btn layui-btn-danger" lay-submit lay-filter="submitDeliveryForm">确认发货</button>'+
					'</div>'+
				'</div>';
		
		return html;
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
	sellerOrdersObj.init();    
});