var Search = {
	
	iurl: '/puckart',
	keyword: '',
	originKeyword: '',
	
	cache : {},//翻页缓存
		
	init: function(){
		
	    $('#footer').load('/load/footer.html');
	    
		var me = this;
		
		var keyword = this.getParam("keyword");
		var originKeyword = decodeURI(keyword,"UTF-8"); //注意解码顺序
		var p = this.getParam("p");
		
		$('#header').load('/load/pdheader.html',function(){
			$('#keyword').val(me.originKeyword);//搜索框赋值
		});
		
		if(this.isEmpty(keyword)){
			me.renderEmpty("");
			return;
		}
		me.originKeyword = originKeyword;
		me.keyword = encodeURI(keyword,"UTF-8"); //仅进入页面时初始化keyword
		me.cache={};//刷页面时清空缓存
		
		//goto btn bind event
		$(document).on('click', '.page-btn', function(){
			var goPage = $(".page-go input").val(); //跳转页数
				
			var np = $(".allPage").html();//总页数
			if(goPage > np){
				goPage = np;
			}
			Search.page(goPage);
			me.search(goPage);
			//清空用户跳转页数
			$(".page-go input").val("");
    	});
		
		me.search(p);
	},
	
	search: function(p){
		
		var me = this;
		
//		var url = me.iurl + '/searchData.action';
		var url = '/es-server/searchData.action';
		
		var reg = /^[1-9][0-9]*$/;
		if (!reg.test(p)) {
			p=1;
		}
		
		//先查缓存
		if(me.cache[p]!=null){
			var cacheData = me.cache[p];
			me.renderSearch(p,cacheData.total,cacheData.html);
			return;
		}
		
		jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data : {"keyword":me.keyword,"p":p},
            success : function(result) {
            	
                if(result){
                	
                	if(result.isEmpty){
                		me.renderEmpty(me.originKeyword);
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
	                	var cacheData = {};
	                	cacheData.total = total;
	                	cacheData.html = html;
	                    me.cache[p] = cacheData;
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
	renderEmpty: function(originKeyword){
		
		var emptyHtml = 
			'<div class="empty-container">'+
				'<div class="empty-msg">'+
					'<span>抱歉，没有找到与“<em>'+originKeyword+'</em>”相关的艺术品</span>'+
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
            },
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
		var keyword = me.originKeyword;
		
        var obj = new Object();
        obj['keyword'] = keyword;
        obj['p'] = value;
        obj.rand = Math.random();
        history.replaceState(obj, '', '?keyword='+keyword+'&p=' + value);
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
	
	Search.init();    
});
