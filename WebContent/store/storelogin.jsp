
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
	<link href="/css/store.css" rel="stylesheet" type="text/css" />
	<link href="/css/layout.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="/css/jquery.validation.css" />

	<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<link rel="stylesheet" href="/css/bootstrap.css" media="screen" />
</head>

<body>

	<div id="main">
		<!-- header -->
		<script type="text/javascript">
			$(function() {
				$('#header').load('/load/header.html');
				$('#header2').load('/load/header2.html');
				$('#foot').load('/load/foot.html');
			});
		</script>
		<div id="header"></div>
		<div id="content">
			<div id="header2"></div>
			<div class="wrapper">
				<div class="aside">
					<ul class="nav001">
						<li><a href="#none">添加作品</a></li>
						<li><a href="#none">上传作品图片</a></li>
						<!--  
						<li><a href="#none">登记作品版权</a></li>
						-->
						<li><a href="#none">售卖方式</a></li>
						<li><a href="#none">确认发布</a></li>
					</ul>
<!-- 					<div class="box"> -->
<!-- 						<div class="inner"> -->
<!-- 							<ul class="list1"> -->
<!-- 								<li><a href="#">微博</a></li> -->
<!-- 								<li><a href="#">微信</a></li> -->
<!-- 								<li><a href="#">备用链接</a></li> -->
<!-- 								<li><a href="#">备用链接</a></li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				<!-- /.nav -->
				<div class="mainContent">
					<br /> <br />
					<form action="/shop/store/login.action"
						class="validation-form-container" enctype="multipart/form-data"
						method="post" style="width: 55%; text-align: center">

						<h2>欢迎回来</h2>

						<br>
						<div class="field">
							<div class="ui left labeled input">
								<input type="text" name="phone" placeholder="手机号" />

								<div class="ui corner label">
									<i class="asterisk icon">*</i>
								</div>
							</div>
						</div> <br>
						
						<div class="field">
							<div class="ui left labeled input">
								<input type="password" name="password" placeholder="密码" />

								<div class="ui corner label">
									<i class="asterisk icon">*</i>
								</div>
							</div>
						</div> <br>
						
						<button type="submit" class="btn btn-primary" style="width: 45%; float: left">
							<strong>&nbsp;登录&nbsp;</strong>
						</button> 
						<a href="/user/register.html" class="btn btn-default" style="width: 45%; float: right"> 
							<strong>&nbsp;注册&nbsp;</strong>
						</a> <br style="clear: both" />
						
						<c:if test="${not empty errorMsg}">
							<div class="login-error-tip">${errorMsg}</div>
						</c:if>
						
					</form>

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
