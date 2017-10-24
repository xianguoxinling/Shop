
var cartObj = {
    iurl: '/puckart',

    outPids: $('#outPids').val() || '',//下订单没货时通过url传参返回
    allPids: $('#allPids').val() || '',//全部pid

    init: function(){
    	
        // 绑定事件
        this.bindCartEvent();

        // 库存状态处理
        this.updateStoreState();
    },
    
 // 绑定事件：全部改为delegate
    bindCartEvent: function(){
        var me = this;

        // 选择一个商品、选择多个商品
        this.bindCheckEvent();
        
        //商品数量变更
        this.bindChangeNum();

        // 删除商品 打开弹出框
        $('.cart-main').delegate('.cart-remove', 'click', function(){
        	me.openRemoveDlg($(this));
        });
        //批量删除
        $('#cart-floatbar').delegate('a.remove-batch', 'click', function(){
            me.openRemoveDlg($(this));
        });
        
        // 删除商品 弹出框删除事件
        $('body').delegate('.cart-remove-dlg-sure', 'click', function(){
            me.remove($(this), $(this).attr('data-batch') || false);
        });
        // 删除商品 弹出框取消事件
        $('body').delegate('.cart-remove-dlg-cancel', 'click', function(){
        	layer.closeAll('page');
        });
        
        // 去结算：普通
        $('.cart-main').delegate('.submit-btn', 'click', function(){
            me.gotoBalance();
        });
        
        

    },
    // 删除商品 打开弹出框
    openRemoveDlg: function(el){
    	
    	var bhtml = el.hasClass('remove-batch') ? 'data-batch="true"' : '';
    	
    	if(bhtml){
            // 判断是否勾选商品
            if(!$(".item-selected").length){
                this.showAlertDlg('请至少选中一件商品！');
                return;
            }
        }

    	var html = '<div class="cart-remove-dlg">'
            + '<span class="cart-remove-msg">删除艺术品？</span>'
            + '<div class="cart-remove-btn">'
            + '<span>'
            + '<a href="#none" class="layui-btn layui-btn-primary layui-btn-small cart-remove-dlg-sure" '
            + '  data-bind="' + (el.attr("id") || '')
            + '" data-name="' + (el.attr("data-name") || '')
            + '" ' + bhtml + '>删除</a>'
            + '<a href="#none" class="layui-btn layui-btn-primary layui-btn-small cart-remove-dlg-cancel" >取消</a>'
            + '</span>'
            + '</div>'
            + '</div>';

    	layui.use('layer', function(){
            var layer = layui.layer;
	        layer.open({
	        	title: '删除',
	          	type: 1,
	          	area: ['350px', '120px'],
	          	shadeClose: true, //点击遮罩关闭
	          	content: html
	          });
    	});
    },
    
    // 删除商品 移出购物车
    remove: function(el, bbatch){
        //批量删除
        if(bbatch){
            this.removeBatch();
        } else{
            this.removeProduct(el);
        }
    },
    removeBatch: function(){
        var me = this;
        var html = '';

        var selected = $(".item-selected");
        if(selected && selected.length){//如果有选中商品
            
            this.updateCartInfo(this.iurl + '/batchRemoveFromCart.action',
                null,
                '批量删除商品失败',
                function(){
            		layer.closeAll('page');
                }
            );
        }else{
            this.showAlertDlg('请至少选中一件商品！');
        }
    },
 // 删除单件商品 移出购物车
    removeProduct: function(el){
        var db = el.attr('data-bind');
        var ss = db.split("_");        
        var pid = ss[1];
        
        var params = "pid=" + pid;
        
        this.removeSingle(params, pid, function(){
        	layer.closeAll('page');
        });
    },
  //删除单件商品 移出购物车
    removeSingle: function(params, pid, callback){

        if(this.checkSku(pid)){
            var url = this.iurl + "/removeFromCart.action";
            
            var outPids = this.outPids;
            
            var me = this;
            jQuery.ajax({
                type : "POST",
                dataType : "json",
                url : url,
                data : params + "&outPids=" + outPids + "&rd=" + Math.random(),
                success : function(result) {
                	
                    if(result && result.l == 1){
                        me.goToLogin();
                        return;
                    }
                    
                    if(result && result.success){
                    	
                    	if(me.isCartEmpty(result)){
                            return;
                        }
                    	// 删除
                        $('#product_' + pid).prop('outerHTML', '');
                        
                        me.updateTotalInfo(result);

                        if(callback){
                            callback(result);
                        }

                        // 库存状态处理
                        me.updateStoreState(result);
    					
    					$("#ids").val(result.ids);
                    	
                    }else{

                        var rem=result.errorMessage;
                        if(errorMessage){
                            me.showAlertDlg(errorMessage);
                        }
                    }
                },
                error:function(XMLHttpResponse ){
                }
            });

        } else{
            this.showAlertDlg('对不起，您删除的商品不存在！');
        }
    },
    
    //复选框 事件
    bindCheckEvent: function(){
        // 回调后复选框是可选的。
        $("input[type=checkbox]").attr("disabled",false)
                                 .removeAttr("disabled");

        $("input[checked=checked]").attr({"checked":"checked"});
		$(".cart-main input[type=checkbox]:not([checked])").prop("checked",false);
        this.toggleSelect();
        this.toggleSingleSelect();
    },
    
    //绑定全选事件
    toggleSelect: function(){
        var me = this;
        $('.cart-main').delegate('input[name=toggle-checkboxes]', 'click', function(){
            //点击复选框后所有复选框不可选
            $("input[type=checkbox]").attr("disabled",true);

            var selected = $(this).prop("checked");

            var act = selected ? 'selectAllItem' : 'cancelAllItem';
            var tip = selected ? '全部勾选商品失败' : '全部取消商品失败';

            me.updateCartInfo(me.iurl + "/cart/" + act + ".action" , '' , tip , function(){
            	$("input[name=toggle-checkboxes]").attr("disabled",false).removeAttr("disabled");
            } );
        });
    },
 // 数据刷新有两种：
    // 单选
    // 全选
    updateCartInfo: function(url, param, errorMessage, callback){
        var me = this;

        jQuery.ajax({
            type     : "POST",
            dataType : "json",
            url  : url,
            data : param + "&outPids=" + this.outPids + "&rd=" + Math.random(),
            success : function(result) {

                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }

                if(result && result.success){

                    if(me.isCartEmpty(result)){
                        return;
                    }
                    if(!result.modifyHtml){
                    	if(callback){
                    		callback(result);
                    	}
                    	return;
                    }
                    me.updateCartGoodsInfo(result);
                    me.updateTotalInfo(result);
                    
                    // 当购物车为空时，刷新当前页面，让其走cart,重新获取页面
                    if(!$('#cart-list').length){
                    	window.location.reload();
                    }
                    
                    if(callback){
                        callback(result);
                    }

                    // 库存状态处理
                    me.updateStoreState(result);
					
                }else{
                	
                    var rem=result.errorMessage;

                    if(errorMessage){
                        me.showAlertDlg(errorMessage);
                    }
                }
            },
            error:function(XMLHttpResponse ){
            	me.showAlertDlg("网络发生异常");
            }
        });
    },
    //单选：对全选有影响。
    toggleSingleSelect: function(){
        var me = this;
        $('.cart-main').delegate('input[name=checkItem]', 'click', function(){

            //点击复选框后所有复选框不可选
            $("input[type=checkbox]").attr("disabled", true);

            var mEl = $(this);

            var pid = $(this).val();

            if(me.checkSku(pid)){

                //是否勾选商品
                var cb = mEl.prop("checked");
                var act = cb ? 'selectItem' : 'cancelItem';
                var tip = cb ? '勾选商品失败，请刷新页面重试。' : '取消商品失败，请刷新页面重试。';

                var outPids = me.outPids;
                
                var params = "outPids=" + outPids + "&pid=" + escape(pid);
			
			    me.updateProductInfo(me.iurl + "/cart/" + act + ".action",
			        params,
			        tip,
			        function(result){
			            $("input[type=checkbox]").attr("disabled", false).removeAttr("disabled");
			        }
			    );
            }else{
                me.showAlertDlg('对不起，您选择的商品不存在！');
            }
        });
    },
 // 选中或去掉一个商品，
    // 对 select 操作(取消或勾选)
    // 对select操作可以处理为一个。
    // 更新商品
    updateProductInfo: function (url,params,errorMessage,callback){
        var me = this;
        me.doing = true;//控制数量点击
        
        jQuery.ajax({
            type : "POST",
            dataType : "json",
            url : url,
            data: params,
            success : function(result) {
            	me.doing = false;
                if(result && result.l == 1){
                    me.goToLogin();
                    return;
                }
                if(result && result.success){

                    if(!result.modifyHtml){
                    	if(callback){
                    		callback(result);
                    	}
                    	me.updateCartInfo(me.iurl + '/jcart.action' , null , '获取购物车失败' , null );
                    	return;
                    }
                    me.updateProductGoodsInfo(result);
                    me.updateTotalInfo(result);

                    if(callback){
                        callback(result);
                    }

                    // 库存状态处理
                    me.updateStoreState(result);
					
					$("#ids").val(result.ids);
                }else{
                    //服务端返回的错误信息
                    if(errorMessage){
                        me.showAlertDlg(errorMessage);
                    }
                }
            },
            error:function(XMLHttpResponse){
            	me.showAlertDlg("网络发生异常");
            }
        });
    },
    // 全部刷新，更新cart-list数据
    // 更新商品信息
    updateCartGoodsInfo: function(result){
        if(!result){
            return false;
        }

        $('#cart-list').prop('innerHTML',result.modifyHtml);
    },
    // 局部刷新，更新item数据
    // 更新商品信息
    updateProductGoodsInfo: function(result){
        if(!result){
            return false;
        }

        var mid = result.pid;
        var id = "#product_" + mid;
        $(id).prop('outerHTML', result.modifyHtml);
    },
    
    // 修改购物车累计信息 (价格、数量、全选状态等)
    updateTotalInfo: function(result){
        if(!result){
            return false;
        }
        
        var totalPrice = result.totalPrice || '0.00';
        var selectedCount = result.selectedCount;

        if(selectedCount == 0){
        	totalPrice = '0.00';
        }

        $(".amount-sum em").html(selectedCount);
        $(".sumPrice em").attr("data-bind", totalPrice)
                        .html("&#x00A5;"+ totalPrice);

		this.toggleAll(result.isCheckAll);
    },
    // 单选
    toggleSingle: function(el,flag){
    	$('input[name=checkItem]', el).each(function(){
            $(this).prop('checked', flag);
        });
    },
    // 全选
    toggleAll: function(flag){
        $("input[name=toggle-checkboxes]").each(function(){
            $(this).prop("checked",flag);
        });
    },
    
    
  //得到库存
    updateStoreState: function(result) {
    	var me = this;
        var allPids = result&&result.allPids || this.allPids;
        var outPids = result&&result.outPids || this.outPids;

        if(!allPids){
            return;
        }
        var url = me.iurl + "/storeState.action?allPids="+allPids+"&rd="+Math.random();
        
        $.getJSON(url, function(result) {
        	if(!result) {
                return;
            }
            var states = result;
            $(".quantity-txt").each(function() {
                var ss = $(this).attr('_stock');
                if(!ss){
                    return;
                }

                // 父节点
                var pnode = $(this).parents('.item-item');

                ss = ss.split("_");
                var id = ss[ss.length - 1];
                for (var skuId in states) {
					var state = states[skuId];
                    if (skuId == id) {
                        var info;
                        switch (state.a) {
                            case 0: info = "有货"; break;
                            default: 
                            		info = "<span style='color:#e4393c'>无货</span>";
                        }
                        //--如果订单返回到购物车,实际商品是无货,则把库存状态强制设置为无货
                        var skus = outPids.split(',');
                        for(var i=0,len=skus.length; i<len; i++){
                            if(skus[i] == id){
                                info = "<span style='color:#e4393c'>无货</span>";
                                // 库存为无货时，置灰背景、不勾选。
                                pnode.addClass('item-invalid');
                                break;
                            }
                        }
                        
                        $(this).html(info);

                        break;
                    }
                }
            });
        });
    },

    // 去结算
    gotoBalance: function(){
    	
    	var me = this;
    	
        var selected = $(".item-selected");
        if(selected && selected.length){
            try {
            	
                var orderInfoUrl = me.iurl +"/gotoBalance.action?rd=" + Math.random();
                
                window.location.href = orderInfoUrl;
                
            } catch (e) {
                
            }
        }else{
            this.showAlertDlg('请至少选中一件商品！');
        }
    },
    
    //商品数量变更
    bindChangeNum: function(){
    	
    	var me = this;
    	
    	$('.cart-main').delegate('a.increment', 'click', function(){
            if(me.doing) return;
            me.doing = true;

            var anode = $(this);
            var pnode = anode.parent();
            var inputEl = $('input', pnode);
            var cur = inputEl.val();
            cur++;
            inputEl.css('color','#fff');

            var uphtml = '<span class="upspan"><span style="position:relative;">' + (cur-1) + '</span></span>';
            var downhtml = '<span style="top:28px;" class="downspan"><span style="position:relative;">' + cur + '</span></span>';
            pnode.prepend(uphtml);
            pnode.append(downhtml);

            $(".upspan span:last").animate({top: -28}, "10");
            $(".downspan span:last").animate({top: -28}, "10",function(){
                $('.downspan,.upspan').remove();
                inputEl.css('color','#333');
                inputEl.val(cur);
                me.addSkuNum(anode);
            });
        });
    	
    	$('.cart-main').delegate('a.decrement', 'click', function(){
            if(me.doing) return;

            var anode = $(this);
            if(anode.hasClass('disabled')){
                return;
            }

            me.doing = true;

            var pnode = anode.parent();
            var inputEl = $('input', pnode);
            var cur = inputEl.val();
            cur--;
            if(cur == 0){
                return;
            }

            inputEl.css('color','#fff');
            var uphtml = '<span class="upspan"><span style="position:relative;">' + (cur+1) + '</span></span>';
            var downhtml = '<span style="top:-28px;" class="downspan"><span style="position:relative;">' + cur + '</span></span>';
            pnode.prepend(uphtml);
            pnode.append(downhtml);

            $(".upspan span:last").animate({top: 28}, "10");
            $(".downspan span:last").animate({top: 28}, "10",function(){
                $('.downspan,.upspan').remove();
                inputEl.css('color','#333');
                inputEl.val(cur);
                me.minusSkuNum(anode);
            });
        });

        //商品数量文本框获取焦点，保存之前的值
        $('.cart-main').delegate('div.quantity-form input', 'focus', function(event){
            var val = parseInt($(this).val());
            if(isNaN(val)){
                return;
            }

            if(val){
                $("#changeBeforeValue").val(val);
                $("#changeBeforeId").val($(this).attr("id"));
            }
        });

        //改变商品数量
        $('.cart-main').delegate('div.quantity-form input', 'change', function(event){
            me.inputSkuNum(this);
        });
    },
    
    // 增加商品数量+
    addSkuNum: function(obj){
    	var ss =$(obj).attr("data").split("_");
        var pid = ss[0];
        var pcount = ss[1];
        var num = parseInt(pcount) + 1;

        ss[1] = num;
        if(num > 0 && num < 1001){
            var params = "pid="+pid
                + "&pcount="+num;
            this.changeSkuNum(params, $(obj).prev());
        }else{
            //还原input值,弹出提示信息
            $(obj).prev().val(pcount);
            this.doing = false;
            if (num <= 0){
                this.showAlertDlg("艺术品数量必须大于0");
            }else {
                this.showAlertDlg("艺术品数量超限");
            }
        }
    },

    //减少商品数量-
    minusSkuNum: function(obj){
        var ss =$(obj).attr("data").split("_");
        var pid = ss[0];
        var pcount = ss[1];
        var num = parseInt(pcount) - 1;

        ss[1] = num;
        if(num > 0 && num < 1001){
            var params = "pid="+pid
                    + "&pcount="+num;
            this.changeSkuNum(params, $(obj).next());
        }else{//还原input值
            if(!num){
                $(obj).next().val(pcount);
            }
        }
    },

    //修改商品数量
    inputSkuNum: function(obj){
        var ss =$(obj).attr("data").split("_");
        var pid = ss[0];
        var pcount = ss[1];
        var num = $(obj).val();

        ss[1] = num;
        if(num > 0 && num < 1001 && pcount != num){
            var params = "pid="+pid
                    + "&pcount="+num;

            this.changeSkuNum(params, $(obj));
        }else{
            //还原input值,弹出提示信息
            $(obj).val(pcount);
            this.doing = false;
            if (num <= 0){
                this.showAlertDlg("艺术品数量必须大于0");
            }else {
                this.showAlertDlg("艺术品数量超限");
            }
        }
    },

    changeSkuNum: function(params, obj){
        var me = this;
        var mEl = $('input[type=checkbox]', obj.closest('.item-form'));
        me.updateProductInfo(me.iurl + "/cart/changeNum.action",
            params,
            "修改艺术品数量失败",
            function(){
//                me.toggleSingle(mEl,true);
                $("input[type=checkbox]").attr("disabled",false).removeAttr("disabled");
            }
        );
    },
    
    isCartEmpty: function(result){
        if(!result){
            return false;
        }

        // 购物车为空
        if(result.isCartEmpty){
            window.location.href = this.iurl + '/cart.action?rd=' + Math.random();
            return true;
        }
    },
    goToLogin: function(){
    	window.location.href = '/user/login.html';
    },
  //检查商品id
    checkSku: function(pid){
        if(!pid || isNaN(pid)){
            return false;
        }

        if(parseInt(pid) > 0){
            return true;
        }
        return false;
    },
    showAlertDlg: function(msg){
    	
    	
    	layui.use('layer', function(){
            var layer = layui.layer;
            layer.closeAll('page');
        });
    	
        var html = '<div class="cart-tip-dlg">'
            + '<span class="cart-tip-msg">'+msg+'</span>'
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
	cartObj.init();    
});
