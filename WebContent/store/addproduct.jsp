<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>添加作品</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="/css/store.css" type="text/css" />
	<link rel="stylesheet" href="/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="/css/jquery.validation.css"
		type="text/css" />
	<link rel="stylesheet" href="/css/ie_style.css" type="text/css" />
	<link rel="stylesheet" href="/css/bootstrap.css" media="screen"
		type="text/css" />

	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/easyform.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/classify.js"></script>

	<script type="text/javascript">
		$(function() {
			$('#header').load('/load/header.html');
			$('#header2').load('/load/header2.html');
			$('#foot').load('/load/foot.html');
		});

		$(document).ready(function() {
			$('#addproduct').easyform();
		});
	</script>
</head>

<body>
	<div id="main">
		<!-- header -->

		<div id="header"></div>
		<div id="content">
			<div id="header2"></div>
			<div class="wrapper">
				<div class="aside">
					<ul class="nav001">
						<li><a href="/shop/production/create.action" class="current">添加作品</a></li>
						<li>
							<div>上传作品图片</div>
						</li>
						<c:if test="${ShowCopyrightStep}">
						<li>
							<div>登记作品版权</div>
						</li>
						</c:if>						
						<li>
							<div>售卖方式</div>
						</li>
						<li>
							<div>确认发布</div>
						</li>
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
				<div class="mainContent">
					<h2>欢迎</h2>

					<div class="txt1"></div>
					<div class="line-hor"></div>

					<div class="wrapper">
						<form id="addproduct" action="/shop/production/create.action?keyID=ccc65483"
							method="post" class="validation-form-container"
							enctype="multipart/form-data">
<!-- 							OnSubmit="return beforeSubmit(this);"> -->

							<div class="field">
								<label>作品名称</label>

								<div class="ui left labeled input">
									<input id="art_name" name="name" type="text"
										easyform="length:1-255;real-time;" message="请使用标准中英文字符"
										easytip="disappear:lost-focus;theme:blue;" />

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>分类</label>

								<div class="ui left labeled input">
									<input id="artist_name" name="category" type="text"
										easyform="length:2-100;real-time;" message="请使用标准中英文字符"
										easytip="disappear:lost-focus;theme:blue;" />

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
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
										<input id="stock" name="num" type="text"
											   easyform="uint:1;real-time;" message="请使用正数" easytip="disappear:lost-focus;theme:blue;"/>

											<div class="ui corner label">
												<i class="asterisk icon">*</i>
											</div>
									</div>
								</div>
<!-- 							<div class="field"> -->
<!-- 								<label>作品分类</label> <input type="hidden" value=0 id="arttype" /> -->
<!-- 								<div class="selectList"> -->
<!-- 									<select class="first" style="width: 32.7%"> -->
<!-- 										<option value="">请选择</option> -->
<!-- 									</select> <select class="second" style="width: 32.7%"> -->
<!-- 										<option value="">请选择</option> -->
<!-- 									</select> <select name="arttype" id="select3" class="third" -->
<!-- 										style="width: 32.7%"> -->
<!-- 										<option value="">请选择</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 								<script type="text/javascript"> -->
<!-- 									function beforeSubmit(addproduct) { -->
<!-- 										var select = $("#select3").val(); -->
<!-- 										/*  alert(select)*/ -->
<!-- 										if (select == 0) { -->
<!-- 											alert("请选择分类"); -->
<!-- 											return false; -->
<!-- 										} else { -->
<!-- 											return true; -->
<!-- 										} -->
<!-- 									} -->
<!-- 								</script> -->

<!-- 							</div> -->

							<div class="field">
								<label>作品描述</label>

								<div class="ui left labeled input">
									<textarea name="brief_introduction" id="description"
										style="height: 170px;"></textarea>

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>

							<input value="下一步" type="submit" class="btn btn-default"
								name="nextpage" style="float: right;"> <br
								style="clear: both" />
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
