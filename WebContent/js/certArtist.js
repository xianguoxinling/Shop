var cert = {

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
		
		//提交
    	$(document).on('click', '#verifyForm .submitVerify', function(){
    		
    		me.submitVerify();
    		    		
        });
	},
	
	submitVerify: function(){
		
		var me = this;
		
		var msg = '';
		var verifyResult = $('#verifyResult').val() || '';
		var verifyOpinion = $('#verifyOpinion').val() || '';
		var artistId = $('#artistId').val() || '';
		
		if(verifyResult.length==0){
			msg = '审核结果不能为空';
		}else if(verifyOpinion.length==0){
			msg = '审核意见不能为空';
		}
		
		if(msg.length>0){
			layui.use('layer', function(){
                var layer = layui.layer;
                layer.alert(msg, {icon: 2}, function(index){
                	layer.close(index);
            	});
        	});
			return;
		}
		
		if(verifyResult == '0'){
			msg = '是否确认审核不通过？';
		}else if(verifyResult == '1'){
			msg = '是否确认审核通过？';
		}else{
			return;
		}

		layui.use('layer', function(){
			  var layer = layui.layer;
              layer.confirm( msg, {icon: 3, title:'提示'}, function(index){
          		
          		var url = me.iurl + "/adm/cert/artist/submitVerify.action";  
                  
                  jQuery.ajax({
                      type : "POST",
                      dataType : "json",
                      url : url,
                      data : {'verifyResult':verifyResult,'verifyOpinion':verifyOpinion,'artistId':artistId},
                      success : function(result) {
                      	
//                          if(result){
//                          	if(result.l == 1){
//                                  me.goToLogin();
//                                  return;
//                            }
//                          	
//                          	if(result.auth == 401){
//                          		layer.alert("无权限操作", {icon: 2}, function(index){
//                                	layer.close(index);
//                            	});
//                          		return;
//                          	}
//                          }
                          
                          if(result && result.success){
                        	  
                        	  layer.alert('提交成功！', {icon: 1}, function(index){
                              	  
                              		window.location.href = '/puckart/adm/cert/artist/list.action';
                              	
                              		layer.close(index);
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
            	  
            	  layer.close(index);
              });
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
}

$(function(){
	cert.init();    
});