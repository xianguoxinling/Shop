var tcart = {
	getItem : function(sku) {
		return sku ? ('<article style="background: none;">'+
						'<div class="grid_3">'+
							'<div class="cart">'+
								'<a href="//www.puckart.com/product/'+sku.id+'.html"  target="_blank"><img src="'+sku.imgUrl+'"/></a>'+
							'</div>'+
						'</div>'+
						'<div class="grid_6">'+
							'<div class="entry_content" style="border:0">'+
								'<a class="hover_red" href="//www.puckart.com/product/'+sku.id+'.html"  target="_blank" title="'+sku.name+'"><h3 class="title">'+sku.name+'</h3></a>'+
								 
							
							'</div>'+
						'</div>'+
						'<div class="grid_6" style="margin-left:20px">'+
						 '<div style="text-align: right; margin-top: 60px;">' +
						 	'<input value="查看商品详情" type="button" onclick="javascript:window.location.href=\'/product/'+sku.id+'.html\'" style="margin-right: 20px;">' +
						 	'<input value="去购物车结算" type="button" onclick="javascript:window.location.href=\'/puckart/cart.action\'" >' +
						 '</div>'+	
						'</div>'+
						'<div class="clear"></div>'+
					'</article>') 
					: ('<article style="background: none;">'+
						'<div class="grid_3">'+
							'<div class="cart">'+
							'</div>'+
						'</div>'+
						'<div class="grid_6">'+
							'<div class="entry_content" style="border:0">'+								 
							
							'</div>'+
						'</div>'+
						'<div class="grid_6" style="margin-left:20px">'+
						 '<div style="text-align: right; margin-top: 60px;">' +
						 	'<input value="查看商品详情" type="button" onclick="javascript:window.location.href=\'/\'" style="margin-right: 20px;">' +
						 	'<input value="去购物车结算" type="button" onclick="javascript:window.location.href=\'/puckart/cart.action\'">' +
						 '</div>'+	
						'</div>'+
						'<div class="clear"></div>'+
					'</article>');
	},
	getResult : function(addItemSuccess, sku , pid) {
		
		return $((addItemSuccess ? '<h2 class ="success-message">商品已成功加入购物车！</h2> '
								 : '<h2 class ="fail-message">添加购物车失败,请返回重试</h2> ') + 
								 this.getItem(sku)
		);
	},
	//加载商品
	loadAddedProduct : function(pid,addItemSuccess){
		var me = this ;
		jQuery.ajax({
			type : "POST",
			dataType : "json",
			url : "/puckart/cart/tproduct.action"+"?pid="+pid+"&rid="+Math.random(),
			success : function(result) {
				$("#result").html(me.getResult(addItemSuccess,result.code==1 ? result.content : null,pid));
			},
			error:function(XMLHttpResponse){
			   $("#result").html(me.getResult(addItemSuccess,null,pid));
			}
		});
	},
	isAddItemSuccess : function(returnCode,isAlert) {
		if (returnCode == "2") {
			if(isAlert){
				alert("添加商品失败,已超出购物车最大容量!");
			}
			return false ;
		} else if (returnCode == "3") {
			if(isAlert){
				alert("添加商品失败,商品数量不能大于" + getParam("em"));
			}
			return false ;
		} else if (returnCode == "0") {
			if(isAlert){
				alert("添加商品失败!");
			}
			return false ;
		}
		return true ;
	},
	init:function(){
		var me = this;
		try{me.loadAddedProduct(getParam("pid"),me.isAddItemSuccess(getParam("rcd")));}catch(e){}
		try{setTimeout(function(){me.isAddItemSuccess(getParam("rcd"),true);},500);}catch(e){}
	}
};

function getParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

$(function(){
	tcart.init();
});
