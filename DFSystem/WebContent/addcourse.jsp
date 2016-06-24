<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="model.TeachersModel" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>打分系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style222.css">

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
a {
	font-family: 微软雅黑
}
div {
	font-family: 微软雅黑
}
body {
	background-color: #EEEEEE
}
</style>
<script type="text/javascript">
	function isHidden() {
		document.getElementById('mm-w').style.display = 'none';
		document.getElementById('xh-w').style.display = 'none';
		if (document.add.cursId.value.length == 0) {
			document.getElementById('xh-w').style.display = 'block';
		} 
		if (document.add.cursName.value.length == 0) {
			document.getElementById('mm-w').style.display = 'block';
		}
		if (document.add.cursId.value.length != 0 && document.add.cursName.value.length != 0)
			document.forms[0].submit();
		
	}
</script>
<jsp:useBean id="teaNameDao" class="dao.TeachersDatabaseDao" scope="page"></jsp:useBean>
<%
	TeachersModel teaName = new TeachersModel();
	teaName = teaNameDao.execQuery("*","teaId",request.getParameter("id"));
%>
</head>
<body>
	<%
		if (session.getAttribute("isLogin").equals("2")) {
			response.sendRedirect("index.jsp");
		}
	%>
	<script src="/js/collapse.js"></script>
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/DFSystem/teacher.jsp?id=<%= request.getParameter("id")%>">打分系统 - 添加课程</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
							<li><a href="/DFSystem/teacher.jsp?id=<%= request.getParameter("id")%>&cursId=001&classId=001">返回</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">  欢迎您：<%=teaName.getTeaName()%> 
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li role="separator" class="divider"></li>
							<li><a href="StudentServlet?method=0">退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div id="login-bg">
		<div id="login-cell">
		<form  action = "StudentServlet?method=3&id=<%= request.getParameter("id")%>" method = "post" name="add">
						
			<div class="input-group btn-block">
				<input id="xh" type="text" class="form-control" placeholder="课程ID，如：001"
					aria-describedby="basic-addon1" name="cursId">
			</div>
			<div id="xh-w" class="alert alert-danger" role="alert"
				style="display: none;">请输入课程ID</div>
			<div class="input-group btn-block">
				<input id="mm" type="text" class="form-control" placeholder="课程名"
					aria-describedby="basic-addon1"  name="cursName">
			</div>
			<div id="mm-w" class="alert alert-danger" role="alert"
				style="display: none;">请输入课程名</div>			

			<div class="btn-group btn-block" role="group">
				<button type="button" class="btn btn-default  btn-block"
					onclick="isHidden()">提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</button>
			</div>
			<div class="btn-group btn-block" role="group">
				<a href="/DFSystem/teacher.jsp?id=<%= request.getParameter("id")%>"><button type="button" class="btn btn-default  btn-block">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</button></a>
			</div>
		</form>
		</div>
	</div>

</body>
</html>