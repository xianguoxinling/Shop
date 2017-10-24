var avtivateObj = {
			
	init: function(){
			    		
		var token = this.getParam("token");

		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : '/puckart/email/activate.action',
            data : {"token": token},
            success : function(result) {
            	
                if(result){
                	
                	if(result.success){
                		
                		layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.alert('邮箱已成功激活！', {icon: 1}, function(index){
                            	window.location.href = '/index.html';
                            	layer.close(index);
                        	});
                    	});
                		
                	}else{
                		
                		var msg = result.msg;
                		layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.alert(msg, {icon: 2}, function(index){
                            	window.location.href = '/index.html';
                            	layer.close(index);
                        	});
                    	});
                	}
                }
                
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	/**
	 * 获取url参数，不解码
	 * @param name
	 * @returns
	 */
	getParam: function(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	    if (r != null) return r[2]; return null; //返回参数值 不解码
	},
	isEmpty: function(str) {
		return (str == null || str.replace(/^\s+|\s+$|^[\u3000]+|[\u3000]+$/g, '') == '');
	}
};

$(function(){	
	
	avtivateObj.init();    
});
