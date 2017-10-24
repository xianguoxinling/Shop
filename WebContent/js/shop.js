var Shop = {
	
	iurl: '/puckart',
				
	init: function(){
			    
		var me = this;
		
		me.bindEvents();
		
		me.initArea();
	},
	
	initArea: function(){
		
		var area = $('#area').val();
//		var area = '安徽省/合肥市';
		var prov,city,dist;
		
		if($.trim(area)){
			
			var areas = area.split('/');
			if(areas.length>0){
				prov = areas[0];
			}
			if(areas.length>1){
				city = areas[1];
			}
			if(areas.length>2){
				dist = areas[2];
			}
		}
		
    	var $myCityPicker = $('#area');
    	
    	if(prov && city && dist){
    		$myCityPicker.citypicker({
    	        province: prov,
    	        city: city,
    	        district: dist
    	    });
    	}else if(prov && city){
    		$myCityPicker.citypicker({
    	        province: prov,
    	        city: city,
    	        district: ''
    	    });
    	}else if(prov){
    		$myCityPicker.citypicker({
    	        province: prov,
    	        city: '',
    	        district: ''
    	    });
    	}else{
    		$myCityPicker.citypicker();
    	}
	},
	
	/**
	 * 绑定事件
	 */
	bindEvents: function(){
		
		var me = this;
		
    	$(document).on('click', '.shop-form #submitShop', function(){
    		
    		var obj,value;
    		
    		//店铺名称
    		obj = $('#name'),value = obj.val();
    		if(!$.trim(value)){
    			obj.easytip().show("店铺名称不能为空!");
    			return;
    		}else if(value.length>32){
    			obj.easytip().show("店铺名称最大长度为32!");
    			return;
    		}
    		//经营地址
    		obj = $('#area'),value = obj.val();
    		if(!$.trim(value)){
    			$('#areaLbl').easytip({left:300,class:"easy-blue",disappear:1000}).show("经营地址不能为空!");
    			return;
    		}
    		//店铺标志
    		obj = $('#logo'),value = obj.val();
    		if($.trim(value)){
    			var hzs = ".gif,.jpg,.png,.jpeg";
    			var hz = value.substring(value.lastIndexOf("."), value.length);
    			if(hzs.indexOf(hz.toLowerCase()) == -1) {
    				obj.easytip().show("店铺标志只支持GIF、JPG、JPEG、PNG格式");
                    return;
                }
    		}    		
    		//店铺简介
    		obj = $('#shortDesc'),value = obj.val();
    		if($.trim(value) && value.length>32){
    			obj.easytip().show("店铺简介最大长度为32!");
    			return;
    		}
    		$('#shopForm').submit();
    	});
	}
};

$(function(){	
	Shop.init();    
});