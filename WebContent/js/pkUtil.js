var pkUtil = {

	iurl: '/puckart',
	
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
	/**
	 * 是否为空
	 * @param str
	 * @returns {Boolean}
	 */
	isEmpty: function(str) {
		return (str == null || str.replace(/^\s+|\s+$|^[\u3000]+|[\u3000]+$/g, '') == '');
	}
}