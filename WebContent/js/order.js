var orderObj = {
	
	iurl: '/puckart',
		
	init: function(){
		
		var me = this;
		
		me.bindEvents();
		
		//库存
		$(".quantity-txt").each(function() {
            var ss = $(this).attr('_stock');
            if(!ss){
                return;
            }
            ss = ss.split("_");
            var id = ss[ss.length - 1];

            var outPids = $('#outPids').val();
            var skus = outPids.split(',');
            for(var i=0,len=skus.length; i<len; i++){
                if(skus[i] == id){
                	info = "<span style='color:#e4393c'>无货</span>";
                    $(this).html(info);
                    break;
                }
            }    
            
        });
    	
    	layui.use('form', function(){
    		var form = layui.form();
    		form.verify({
    			contactName: function(value){
    				if(value.length==0){
    					return '收货人姓名不能为空';
    				}
    				if(value.length>10){
    					return '收货人姓名不能超过10个字符';
    				}
    			},
    			areaName: function(value){
    				if(value.length==0){
    					return '所在地区不能为空';
    				}
    			},
    			address: function(value){
    				if(value.length==0){
    					return '详细地址不能为空';
    				}
    				if(value.length>25){
    					return '详细地址不能超过25个字符';
    				}
    			},
    			telephone: function(value){
    				if(value.length>20){
    					return '备用电话不能超过20个字符';   					
    				}
    			},
    			postcode: function(value){
    				if(value.length>0){
    					if(!new RegExp("^[0-9]{6}$").test(value)){
      				      return '请输入正确的邮政编码';
    					}
    				}
    			}
    		}); 
		  //监听提交
		  form.on('submit(submitAddrForm)', function(data){
		    
		    me.saveUserAddress(data.field);
		    
		    return false;
		  });
		  
		});
	},
	
	/**
	 * 绑定事件
	 */
	bindEvents: function(){
		
		var me = this;
		
		//打开填写收货信息Layer
    	$(document).on('click', '.addr-form #addAddr', function(){
    		me.openAddreseeLayer();
    	});
    	
    	//地址hover
    	$(document).on('mouseenter', '.addr-form-li', function(){
    		$(this).addClass('li-hover');
        }).on('mouseleave', '.addr-form-li', function(){
    		$(this).removeClass('li-hover');
        });
    	
    	//地址修改和删除事件
    	$(document).on('click', '.info-btns .edit', function(){
            //var addressId = $(this).attr('data');
            me.openAddreseeLayer();
        });
    	$(document).on('click', '.info-btns .delete', function(){
            var addressId = $(this).attr('data');
            me.deleteUserAddress(addressId);
        });
    	//支付方式切换
		$('.payment-form .payment-form-btns button').on('click', function(){
    		
    		var pre = $(".payment-form .payment-form-btns .layui-btn-warm");
    		pre.removeClass('layui-btn-warm');
    		pre.addClass('layui-btn-primary');

        	$(this).removeClass('layui-btn-primary');
        	$(this).addClass('layui-btn-warm');
        	
        	var payment = $(this).attr('data-bind');
        	$('#payment').val(payment);
        });
		//提交订单
		$('.order-submit-btn').on('click',function(){
			me.submitOrder();
		});
	},
	/**
	 * 新增、保存收货地址
	 * @param data
	 */
	saveUserAddress:function(field){
		
		var me = this;
		
		var url = me.iurl + "/saveUserAddr.action";  
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : field,
            success : function(result) {
            	
                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }
                
                if(result && result.success){
                	
                	me.refreshPage(result);
        		    
        		    layer.closeAll('page');
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
	/**
	 * 删除收货地址
	 * @param addressId
	 */
	deleteUserAddress:function(addressId){
		
		var me = this;
		
		var url = me.iurl + "/delUserAddr.action";
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : "addressId=" + addressId,
            success : function(result) {
            	
                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }
                
                if(result && result.success){
                	
                	me.refreshPage(result);
                	
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
	
	/**
	 * 打开地址Layer
	 */
	openAddreseeLayer: function(){
		
		var me = this;
		
		var url = me.iurl + "/fetchUserAddr.action";
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : "?rd=" + Math.random(),
            success : function(result) {
            	
                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }
                
                var html = me.generateLayerHtml(result.userAddress);
            	
            	layui.use('layer', function(){
    	            var layer = layui.layer;
    	            layer.open({
    	                title: '填写收货信息',
    	                type: 1,
    	                area: ['850px','600px'],
    	                resize: false,
    	                move:false,
    	                content: html
    	            });
    	            layer.ready(function(){
    	            	var prov = $('#prov').val();
    	            	var city = $('#city').val();
    	            	var dist = $('#dist').val();
    	            	
    	            	var $myCityPicker = $('#myCityPicker');
    	            	
    	            	if(prov && city && dist){
    	            		$myCityPicker.citypicker({
    	            	        province: prov,
    	            	        city: city,
    	            	        district: dist
    	            	    });
    	            	}else{
    	            		$myCityPicker.citypicker();
    	            	}
    	            	
    	            });
    	        });
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	/**
	 * 生成Layer页面
	 * @param userAddress
	 * @returns
	 */
	generateLayerHtml: function(userAddress){
		
		var addressId='',
			prov='',
			city='',
			dist='',
			contactName='',
			address='',
			contactTel='',
			contactTel2='',
			postcode='';
		
		if(userAddress){
			addressId = ' value="'+userAddress.addressId+'"';
			prov = ' value="'+userAddress.prov+'"';
			city = ' value="'+userAddress.city+'"';
			dist = ' value="'+userAddress.dist+'"';
			contactName = ' value="'+userAddress.contactName+'"';
			address = ' value="'+userAddress.address+'"';
			contactTel = ' value="'+userAddress.contactTel+'"';
			contactTel2 = ' value="'+userAddress.contactTel2+'"';
			postcode = ' value="'+userAddress.postcode+'"';
		}
		
		var html = 
			'<div class="layui-form addr-form-layer">'+ 
				'<input type="hidden" name="addressId" id="addressId" '+ addressId +'/>'+
				'<input type="hidden" name="prov" id="prov" '+ prov +'/>'+
				'<input type="hidden" name="city" id="city" '+ city +'/>'+
				'<input type="hidden" name="dist" id="dist" '+ dist +'/>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label"><span style="color:red">*</span>收货人：</label>'+
					'<div class="layui-input-block contactName-tx">'+
						'<input type="text" name="contactName" required lay-verify="contactName"'+
							' placeholder="请输入收货人姓名" autocomplete="off" class="layui-input"'+ contactName +'/>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label"><span style="color:red">*</span>所在地区：</label>'+
					'<div class="layui-input-block areaName-tx">'+
						'<input type="text" name="areaName" required lay-verify="areaName"'+
							' autocomplete="off" class="layui-input" data-toggle="city-picker" id="myCityPicker">'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label"><span style="color:red">*</span>详细地址：</label>'+
					'<div class="layui-input-block address-tx">'+
						'<input type="text" name="address" required lay-verify="address"'+
							' placeholder="请输入详细地址" autocomplete="off" class="layui-input"'+ address +'/>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<div class="layui-inline">'+
						'<label class="layui-form-label"><span style="color:red">*</span>手机号码：</label>'+
						'<div class="layui-input-inline contactTel-tx">'+
							'<input type="text" name="contactTel" required lay-verify="phone"'+
								' placeholder="请输入手机号码" autocomplete="off" class="layui-input"'+ contactTel+'/>'+
						'</div>'+
					'</div>'+
					'<div class="layui-inline">'+
						'<label class="layui-form-label">备用电话：</label>'+
						'<div class="layui-input-inline contactTel-tx">'+
							'<input type="text" name="contactTel2" required lay-verify="telephone"'+
								' placeholder="请输入备用电话(选填)" autocomplete="off" class="layui-input"'+ contactTel2 +'/>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<label class="layui-form-label">邮政编码：</label>'+
					'<div class="layui-input-block postcode-tx">'+
						'<input type="text" name="postcode" lay-verify="postcode"'+
							' placeholder="请输入邮政编码(选填)" autocomplete="off" class="layui-input"'+ postcode +'/>'+
					'</div>'+
				'</div>'+
				'<div class="layui-form-item">'+
					'<div class="layui-input-block">'+
						'<button class="layui-btn layui-btn-danger" lay-submit lay-filter="submitAddrForm">保存收货信息</button>'+
					'</div>'+
				'</div>';
		
		return html;
	},
	
	refreshPage: function(result){
		//收货信息
	    $('.addr-form').html(result.addFormHtml);
	    //确认信息
	    $('.payInfo-shadow').html(result.payInfoShadowHtml);
	    //确认信息
	    $('.cart-item-list').html(result.cartItemListHtml);
    	
	    $('#outPids').val(result.outPids);
	    $('#addressId').val(result.addressId);
	},
	
	submitOrder: function(){
		
		var me = this;
		
		var canSubmitFlag=false;
		
		var addressId = $('#addressId').val();
		if(addressId.length == 0){
			alert("请填写收货信息");
			return;
		}
		var payment = $('#payment').val();
		
		var param = "?rd="+Math.random()
				+ "&addressId="+addressId
				+ "&payment="+payment;
		
		var url = me.iurl + "/order/submitOrder.action";
		
		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : param,
            success : function(result) {
            	
                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }
                
                if(result && result.success){
                	
                	var orderId = result.orderId;
                	window.location.href = me.iurl+'/payment/pay.action?orderId='+orderId;
                	
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
	orderObj.init();    
});
