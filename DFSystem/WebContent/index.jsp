<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>打分系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
body{
	margin:0px;
	background-image:url(img/indexBg.jpg);
	background-repeat:no-repeat;
	width:100%;
}
div{
	font-family: 微软雅黑
}
a{
	color:#FFF;
}
</style>
</head>

<body>

	<div> 
		<nav>
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#" style="color:#FFFFFF;">打分系统 - 主页</a>
		    </div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		    	  <form class="navbar-form navbar-left" role="search">		        
			      	<a href="/DFSystem/login.jsp"><button type="button" class="btn btn-default">登录</button></a>
			      	<a href="/DFSystem/resign.jsp"><button type="button" class="btn btn-primary">注册</button></a>
			      </form>
		      </ul>
		    </div>
		  </div>
		</nav>
	</div>
	
	<div class="centerSt">
		<p>好好学习，天天向上！</p>
	</div>
	
	<div class="center">
		<a href="/DFSystem/resign.jsp"><button class="btn_center">立即注册</button></a>
	</div>
	
</body>
</html>