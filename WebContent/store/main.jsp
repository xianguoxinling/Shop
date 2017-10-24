<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>puckart | 我要开店</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="/css/store.css" type="text/css" />
	<link rel="stylesheet" href="/css/layout.css" type="text/css" />
	<link rel="stylesheet" href="/css/ie_style.css" type="text/css" />
	<link rel="stylesheet" href="/css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="/css/storeimage.css" type="text/css" />

	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>

	<script type="text/javascript">
                $(function () {
                    $('#header').load('/load/header.html');
                    $('#header2').load('/load/header2.html');
                    $('#foot').load('/load/foot.html');
                });
            </script>
</head>

<body>
	<div id="main">

		<div id="header"></div>
		<div id="content">
			<div id="header2"></div>
			<div class="wrapper">

				<div>
					<ul class="gallery-post-grid">

						<li class="spanimage">
							<div class="lineimage">
								<a href="/shop/production/create.action"><img
									src="/images/add.jpg" alt="添加作品" /></a>
							</div> <span class="project-details">添加作品</span>
						</li>

						<c:forEach items="${productlist}" var="product">
							<li class="spanimage">
								<div class="lineimage">
									<a href="/product/${product.id}.html"> <img
										src="${product.mainPic}" title="${product.name}" />
									</a>
								</div> <span class="project-details"> <c:out
										value="${product.name}" />
							</span>
							</li>
						</c:forEach>

						<script type="text/javascript">
                                    if ($ {fn: length(productlist)} > 7) {
                                    	
                                    } else {
                                        $(document).ready(function () {
                                            for (var i = (7 - $ {fn: length(productlist)}); i > 0; i--) {
                                                $("#" + i).load('/load/addimageitem.html');
                                            }
                                        });
                                    }
                                </script>

						<div id="1"></div>
						<div id="2"></div>
						<div id="3"></div>
						<div id="4"></div>
						<div id="5"></div>
						<div id="6"></div>
						<div id="7"></div>
					</ul>
				</div>

			</div>
		</div>

		<div id="foot"></div>
	</div>
	<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
</body>
</html>
