<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑作品</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
<link href="/css/store.css" rel="stylesheet" type="text/css" />
<link href="/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.validation.css" rel="stylesheet" type="text/css" />
<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/bootstrap.css" media="screen" type="text/css" />
<link rel="stylesheet" href="/css/normalize.css" type="text/css" />

<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>

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

		<h1 class="page_title">在售作品</h1>
		<table class="cart_product">
			<tr class="bg">
				<th class="images"></th>
				<th class="name">作品名称</th>
				<th class="name">作品编号</th>
				<th class="price" style="text-align: center">价格</th>
				<th class="price" style="text-align: center">佣金</th>
				<th style="text-align: center">状态</th>
				<th class="edit" style="text-align: center">编辑</th>
				<th class="edit2" style="text-align: center">下架</th>
			</tr>

			<c:forEach items="${productlist}" var="product">
				<tr>
					<td class="images">
						<a href="/product/${product.id}.html">
							<img src="${product.cover}" title="" />
						</a>
					</td>
					<td class="name"><c:out value="${product.name}" /></td>
					<td class="name"><c:out value="${product.id}" /></td>
					<td class="price">￥<c:out value="${product.price}" /></td>


					<td class="price">
				    <script  type = "text/javascript" >
				        var rate = ${product.rate}
				        rate = rate*100;
				        document.write(rate);
				    </script>
				    %
				    </td>

					<td>正常</td>
					<td class="edit"><a title="Edit" href="edit.action?productID=${product.id}">编辑</a></td>
					<td class="edit2"><a title="close" href="offshelt.action?productID=${product.id}">下架</a></td>

				</tr>
			</c:forEach>

		</table>

		<div id="foot"></div>
	</div>
	<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
</body>
</html>
