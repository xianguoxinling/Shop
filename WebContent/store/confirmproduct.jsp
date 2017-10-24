<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>版权登记</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
	<link href="/css/store.css" rel="stylesheet" type="text/css" />
	<link href="/css/layout.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/css/jquery.validation.css">
	<link rel="stylesheet" href="/css/bootstrap.css" media="screen">
	<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/css/normalize.css" type="text/css" />

	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/classify.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#header').load('/load/header.html');
		$('#header2').load('/load/header2.html');
		$('#foot').load('/load/foot.html');
	});
	</script>
</head>

<body>

	<div id="main">
		<!-- header -->

		<div id="header"></div>
		<div id="header2"></div>
		<div id="content">
			<div class="wrapper">
				<div class="aside">
					<ul class="nav001">
						<li><a href="/puckart/store/addProduct_manager.action" >添加作品</a></li>
						<li><a href="/puckart/store/addProduct_pic_manager.action?productID=${newproduct.id}">上传作品图片</a></li>
						<c:if test="${ShowCopyrightStep}">
						<li><a href="/puckart/store/addProduct_copyright_manager.action?productID=${newproduct.id}">登记作品版权</a></li>
						</c:if>
						<li><a href="/puckart/store/addProduct_rate_manager.action?productID=${newproduct.id}">售卖方式</a></li>
						<li><a href="/puckart/store/addProduct_confirm_manager.action?productID=${newproduct.id}" class="current">确认发布</a></li>
					</ul>
					<div class="box">
						<div class="inner">
							<ul class="list1">
								<li><a href="#">微博</a></li>
								<li><a href="#">微信</a></li>
								<li><a href="#">备用链接</a></li>
								<li><a href="#">备用链接</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /.nav -->
				<div class="mainContent" style="padding:0 ">
					<form id="confirmproduct" action="/puckart/store/confirm_product.action?productID=${newproduct.id}"
						method="post">

						<table class="table-hover">
							<tbody>
								<tr class="bg">
									<td style="padding:0">作品編号：</td>
									<td>${newproduct.id}</td>
								</tr>
								<tr >
									<td>作品名称：</td>
									<td>${newproduct.name}</td>
								</tr>
								<tr class="bg">
									<td>作者名称：</td>
									<td>${newproduct.authorName}</td>
								</tr>
								<tr>
									<td>作品分类：</td>

									<td>
										<input type="hidden" value=${newproduct.artType} id="arttype" />
										<script type="text/javascript">
											var obj = typedata;
											var v = $("#arttype").val();
											/*var v = 30201;*/
											var v1 = parseInt(v/10000);
											var v4 = (parseInt(v/100))-100*v1;
											var v2 = v4-1;
											var c = v1*10000+v4*100;

											$.each(obj,function(i,arr){
												if(arr.p[1]==v1*10000){
													document.write(arr.p[0]);
												}
											});
											document.write(">>");
											$.each(obj[v1].c,function(i,arr){
												if(arr.ct[1]==c){
													document.write(arr.ct[0]);
												}
											});
											document.write(">>");
											$.each(obj[v1].c[v2].d,function(i,arr){
												if(arr.dt[1]==v){
													document.write(arr.dt[0]);

												}
											});

										</script>
									</td>
								</tr>
								<tr class="bg">
									<td>售卖方式：</td>
									<td>
										<script type="text/javascript">
											var model = ${newproduct.saleModel}
											switch ( model ) {
												case 0:
													document.write("一口价");
													break;

												case 1:
													document.write("在线拍卖");
													break;

											}

										</script>
									</td>
								</tr>
								<tr>
									<td>定价/起价：</td>
									<td>${newproduct.price}</td>
								</tr>
								<tr>
									<td>库存数：</td>
									<td>${newproduct.stock}</td>
								</tr>
	                            <tr class="bg">
	                                <td>佣金：</td>
	                                <td>
										<script  type = "text/javascript" >
											var rate = ${newproduct.rate}
													rate = rate*100;
											document.write(rate);
										</script>%
									</td>
	                            </tr>
								<tr>
									<td>配送方式：</td>
									<td>
										<script type="text/javascript">
											var delivery = ${newproduct.delivery}
											switch ( delivery ) {
												case 0:
													document.write("物流发货");
													break;

												case 1:
													document.write("门店自取");
													break;
											}

										</script>
									</td>
								</tr>
								<tr class="bg">
									<td>作品描述：</td>
									<td>${newproduct.description}</td>
								</tr>
							</tbody>

						</table>
						<button type="submit" class="btn btn-primary" id="confirm" style="float: right">确认提交</button>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<div id="foot"></div>
	<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
</body>
</html>
