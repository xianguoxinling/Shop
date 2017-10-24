var Search = {
	
	iurl: '/puckart',
	
	shopId: $('#shopId').val() || '',
	
	cache : {},//翻页缓存	
		
	init: function(){
			    
		var me = this;
		
		me.bindEvents();

		me.search(1);
	},
	
	/**
	 * 绑定事件
	 */
	bindEvents: function(){
		
		var me = this;
		
		//cond tab
		$(document).on('click', '.shop-cond .cond', function(){
			
			var conds = $('.shop-cond .cond');
			var cur = $(this);
			
			conds.removeClass('current');
			cur.addClass('current');
			
			//默认取升序
			var cl = 'up';
			var dr = '1';
			if(cur.hasClass('up')){
				cl = 'down';
				dr = '0';
			}
			
			conds.removeClass('up');
			conds.removeClass('down');
			cur.addClass(cl);			
        	
        	var orderby = cur.attr('orderby');
        	
        	Search.page(me.fetchP(orderby,dr));
		});
			
		//goto btn bind event
		$(document).on('click', '.page-btn', function(){
			var goPage = $(".page-go input").val(); //跳转页数
				
			var np = $(".allPage").html();//总页数
			if(goPage > np){
				goPage = np;
			}
			me.search(goPage);
			//清空用户跳转页数
			$(".page-go input").val("");
    	});
	},
	
	fetchP: function(page, orderby, direction){
		
		var me = this;
		var pre = me.getParam("p");
		
		var p = '';
		
		if(!orderby){
			if(pre && pre.split('-').length>0){
				orderby = pre.split('-')[0];
			}else{
				orderby = 0;
			}
		}
		p = p + orderby;

		
		if(!direction){			
			if(pre && pre.split('-').length>10){
				direction = pre.split('-')[1];
			}else{
				direction = 0;
			}
		}
		p = p + '-' + direction;
		
		if(!page){
			if(pre && pre.split('-').length>2){
				page = pre.split('-')[2];
			}else{
				page = 1;
			}
		}
		p = p + '-' + page;
		
		return p;
	},
	
	search: function(page,orderby,direction){
		
		var me = this;
		
		var shopId = me.shopId;
		var url = me.iurl + '/shop/searchData.action';
		
		var param = me.fetchP(page,orderby,direction);
		var o,d,p;
		
		o = param.split('-')[0];
		d = param.split('-')[1];
		p = param.split('-')[2];
		
		var reg = /^[1-9][0-9]*$/;
		if (!reg.test(p)) {
			p=1;
		}
		
		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : {"shopId":shopId,"o":o,"d":d,"p":p},
            success : function(result) {
            	
                if(result){
                	
                	if(result.isEmpty){
                		me.renderEmpty();
                		return;
                	}
                	
                	result = result.queryResult;
                	
                	var total = result.total;//总记录数
                	var pojos = result.pojos;//
                	
                	var html = me.refreshHtml(pojos);
                	//加载页面
                	me.renderSearch(p,total,html);
                	//缓存数据
                	try{
	                	
                	}catch(e){
                		
                	}
                }
                
            },
            error:function(XMLHttpResponse ){
            }
        });
	},
	
	/**
	 * 查询结果为空
	 * @param originKeyword
	 */
	renderEmpty: function(){
		
		var emptyHtml = 
			'<div class="empty-container">'+
				'<div class="empty-msg">'+
					'<span>抱歉，店铺内暂时没有上架的艺术品</span>'+
				'</div>'+
			'</div>';
		$('.product-box').html(emptyHtml);
	},

	/**
	 * 查询结果
	 * @param p
	 * @param total
	 * @param html
	 */
	renderSearch: function(p,total,html){
		
		var me = this;
		
		$('.product-grids').html(html);
		
		$("#Pagination").pagination(total, {
            num_edge_entries: 1,
            num_display_entries: 3,
            items_per_page:30,
            current_page:p-1,
            callback: function(targetPage){
            	//页面跳转回调
            	//targetPage从0开始计数
            	//翻页 keyword不变
            	me.search(targetPage+1);
            }
        });
		
    	window.scrollTo(0,0);
	},
	/**
	 * 刷新查询结果html
	 * @param pojos
	 */
	refreshHtml: function(pojos){
		
		var html = '';

		for(var index in pojos){
			
			var pojo = pojos[index];
			
			var id = pojo.id;
			var name = pojo.name;
			var price = pojo.price;
			var imagePath = pojo.imagePath;
			
			var href = "location.href='/product/"+id+".html'";
			var shortName = name;
			if(shortName.length>13){
				shortName = shortName.substr(0,12)+'..';
			}
			
			var style = 'product-grid fade';
			if(index%3 == 2){
				style += ' last-grid';
			}
			var single = 
				'<div class="'+style+'" onclick="'+href+'">'+
					'<div class="product-pic">'+
						'<a href="#"><img src="'+imagePath+'" title="'+name+'"></a>'+
					'</div>'+
					'<div class="product-info">'+
						'<div class="product-info-cust">'+
							'<a href="#">'+shortName+'</a>'+
						'</div>'+
						'<div class="product-info-price">'+
							'<a href="#">'+price+'</a>'+
						'</div>'+
						'<div class="clear"></div>'+
					'</div>'+
					'<div class="more-product-info">'+
						'<span></span>'+
					'</div>'+
				'</div>';
			
			html+=single;
		}		
		return html;
	},
	/**
	 * url变更
	 * @param value
	 */
	page: function  (value) {
		var me = this;
		
        var obj = new Object();
        obj['p'] = value;
        obj.rand = Math.random();
        history.replaceState(obj, '', '?p=' + value);
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
};

$(function(){	
	
	Search.init();    
});