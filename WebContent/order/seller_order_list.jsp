<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="/css/store.css" type="text/css"/>
	<link rel="stylesheet" href="/css/ie_style.css" type="text/css"/>
	<link rel="stylesheet" href="/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="/css/ie_style.css" type="text/css" />
	<link rel="stylesheet" href="/css/orderList.css" type="text/css" />
	<link href="/js/layui/css/layui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/css/bootstrap.css" type="text/css"/>
</head>

<body>
	<div id="main">
		<!-- header -->

		<div id="header"></div>
		<div id="content" style="padding-bottom: 0px;">
			<div id="header2"></div>
			
		</div>
		
		<div class="clear"></div>
		
		<div class="mod-main mod-comm lefta-box" id="order02">
			<div class="mc">
			
				<table class="td-void order-tb">
					<colgroup>
						<col class="number-col">
                        <col class="consignee-col">
                        <col class="amount-col">
                        <col class="status-col">
                        <col class="operate-col">
                    </colgroup>
                    <thead>
                    	<tr>
                    		<th>订单详情</th>
                            <th>收货人</th>
                            <th>总金额</th>
                            <th>状态</th>
                             <th>操作</th>
                          </tr>
                    </thead>
                    
                    <c:forEach items="${orderList}" var="order">
					
						<tbody>
							<tr class="sep-row">
								<td colspan="5"></td>
							</tr>
							<tr class="tr-th">
								<td colspan="5">
									<span class="gap"></span> 
									<span class="dealtime" title="${order.updateTime}">${order.updateTime}</span> 
									<span class="number">订单号：
										<a target="_blank" href="/manager/order/detail.action?orderId=${order.id}">${order.id}</a>
									</span>
								</td>
							</tr>
							
							<c:forEach items="${order.OrderProduction}" var="detail" varStatus="status">
							
								<tr class="tr-bd">
									<td>
										<div class="goods-item">
											<div class="p-img">
												<a href="/product/${detail.id}.html" target="_blank">
													<img src="${detail.mainPic}" title="${detail.name}" data-lazy-img="done" width="60" height="60">
												</a>
											</div>
											<div class="p-msg">
												<div class="p-name">
													<a href="/product/${detail.id}.html" class="a-link"
														target="_blank" title="${detail.name}">${detail.name}</a>
												</div>
											</div>
										</div>
										<div class="goods-number">x${detail.num}</div>
										<div class="goods-repair"></div>
										<div class="clr"></div>
									</td>
									
<%-- 									<c:if test="${status.index == 0}">									 --%>
<%-- 										<td rowspan="${order.orderDetailList.size()}"> --%>
<!-- 											<div class="consignee contactName"> -->
<%-- 												<span class="txt">${order.contactName}</span> --%>
<!-- 											</div> -->
<!-- 										</td> -->
<%-- 										<td rowspan="${order.orderDetailList.size()}"> --%>
<!-- 											<div class="amount"> -->
<%-- 												<span>${order.totalAmount}</span> <br> --%>
<!-- 											</div> -->
<!-- 										</td> -->
<%-- 										<td rowspan="${order.orderDetailList.size()}"> --%>
<!-- 											<div class="status"> -->
<!-- 												<span> -->
<%-- 													<c:choose> --%>
<%-- 														<c:when test="${order.orderStatus == '02'}">待发货</c:when> --%>
<%-- 														<c:when test="${order.orderStatus == '03'}">已发货</c:when> --%>
<%-- 														<c:when test="${order.orderStatus == '04'}">待确认</c:when> --%>
<%-- 														<c:when test="${order.orderStatus == '05'}">已结束</c:when> --%>
<%-- 														<c:otherwise>订单状态异常</c:otherwise> --%>
<%-- 													</c:choose> --%>
<!-- 												</span> -->
<!-- 												<br> -->
<%-- 												<a href="/puckart/order/detail.action?orderId=${order.orderId}" target="_blank">订单详情</a> --%>
<!-- 											</div> -->
<!-- 										</td> -->
<%-- 										<td rowspan="${order.orderDetailList.size()}"> --%>
<!-- 											<div class="operate"> -->
<%-- 												<c:choose> --%>
<%-- 													<c:when test="${order.orderStatus == '02'}"> --%>
<%-- 														<a class="btn btn-show send" data-bind="${order.orderId}" href="javascript:void(0);">发货</a><br> --%>
<%-- 													</c:when> --%>
<%-- 													<c:when test="${order.orderStatus == '03'}"> --%>
<%-- 														<a class="btn btn-show send" data-bind="${order.orderId}" href="javascript:void(0);">修改发货信息</a><br> --%>
<%-- 													</c:when> --%>
<%-- 													<c:otherwise></c:otherwise> --%>
<%-- 												</c:choose> --%>
<!-- 											</div> -->
<!-- 										</td>									 -->
<%-- 									</c:if> --%>
									
								</tr>
							</c:forEach>							
							
						</tbody>
					
					</c:forEach>					
				</table>
				<c:if test="${ empty orderList}">
				
					<div class="empty-box">
	                    <div class="e-cont">
	                    	<h5>暂时没有订单记录</h5>
	                     </div>
	                 </div>				
				</c:if>
			</div>
		</div>
		
		<div id="foot"></div>
	</div>
	
	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/layui/layui.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/cookies.js"></script>
	<script type="text/javascript" src="/js/move-top.js"></script>
	<script type="text/javascript" src="/js/easing.js"></script>
	<script type="text/javascript" src="/js/sellerOrders.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('#header').load('/load/header.html');
            $('#header2').load('/load/header2.html');
            $('#foot').load('/load/foot.html');
		});
	</script>

	<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
	
</body>
</html>