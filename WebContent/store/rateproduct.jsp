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
	<link rel="stylesheet" href="/css/jquery.validation.css" />
	<link rel="stylesheet" href="/css/bootstrap.css" media="screen" />
	<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/easyform.js"></script>

	<script type="text/javascript">
	$(function() {
	$('#header').load('/load/header.html');
	$('#header2').load('/load/header2.html');
	$('#foot').load('/load/foot.html');
	});

	$(document).ready(function(){
	$('#rate_product').easyform();
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
						<li><a href="/puckart/store/addProduct_pic_manager.action?productID=${product.id}">上传作品图片</a></li>
						<c:if test="${ShowCopyrightStep}">
						<li><a href="/puckart/store/addProduct_copyright_manager.action?productID=${product.id}">登记作品版权</a></li>
						</c:if>
						<li><a href="/puckart/store/addProduct_rate_manager.action?productID=${product.id}" class="current">售卖方式</a></li>
						<li><div>确认发布</div></li>
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
					</div> <!-- /.nav -->
					<div class="mainContent">
						<div class="txt1"></div>
						作品编号：No.${product.id}<br/>
						<div class="line-hor"></div>
						<div class="wrapper">
							<form id="rate_product"
								action="/puckart/store/rate_product.action?productID=${product.id}" method="post"
								class="validation-form-container" enctype="multipart/form-data">

								<div class="field">
									<label>售卖方式</label>
									<div class="ui left labeled input">
										<select name="salemodel" id="salemodel">
											<option value="0">一口价</option>
											<option value="1">在线拍卖</option>
										</select>
									</div>
								</div>
								<div class="field">
									<label>定价/起价</label>
									<div class="ui left labeled input">
										<input id="price" name="price" type="text"
											   easyform="money;real-time;" message="请使用正数，不超过2位小数" easytip="disappear:lost-focus;theme:blue;"/>

											<div class="ui corner label">
												<i class="asterisk icon">*</i>
											</div>
									</div>
								</div>
								<div class="field">
									<label>库存数</label>
									<div class="ui left labeled input">
										<input id="stock" name="stock" type="text"
											   easyform="uint:1;real-time;" message="请使用正数" easytip="disappear:lost-focus;theme:blue;"/>

											<div class="ui corner label">
												<i class="asterisk icon">*</i>
											</div>
									</div>
								</div>
								<div class="field">
									<label>佣金</label>
									<div class="ui left labeled input">

										<select name="rate" id="rate">
											<option value="0.1">10%</option>
											<option value="0.2">20%</option>
											<option value="0.3">30%</option>
											<option value="0.5">50%</option>
										</select>
									</div>
								</div>

								<div class="field">
									<label>配送方式</label>
									<div class="ui left labeled input">
										<select name="delivery" id="delivery">
											<option value="0">物流发货</option>
											<option value="1">门店自取</option>
										</select>
									</div>
								</div>

								<input value="下一步" type="submit" class="btn btn-default" name="nextpage" style="float: right;">
								<br style="clear: both" />
							</form>


						</div>
					</div>
			</div>
		</div>
		<!-- footer -->
		<div id="foot"></div>
	</div>
	<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
</body>
</html>
