<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.ClassesModel" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册 - 打分系统</title>
<%@ include file="modelJSP/ref.jsp"%>
<style type="text/css">
body {
	margin:0px;
	background-image:url(img/indexBg.jpg);
	background-repeat:no-repeat;
	width:100%;
}
</style>
<jsp:useBean id="classDao" class="dao.ClassesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<ClassesModel> classList = null;
	classList = classDao.queryClassList();
%>
<script type="text/javascript">
function isHidden(){
	document.getElementById('mm-w').style.display = 'none';
	document.getElementById('qrmm-w').style.display = 'none';
	document.getElementById('bj-w').style.display = 'none';
	document.getElementById('xm-w').style.display = 'none';
	document.getElementById('xh-w').style.display = 'none';
	if (document.resign.stuId.value.length == 0) {
		document.getElementById('xh-w').style.display = 'block';
	} 
	if (document.resign.password.value.length == 0) {
		document.getElementById('mm-w').style.display = 'block';
	} 
	if (document.resign.qrpassword.value.length == 0) {
		document.getElementById('qrmm-w').style.display = 'block';
	} 
	if (document.resign.name.value.length == 0) {
		document.getElementById('xm-w').style.display = 'block';
	} 
	if (document.resign.classname.value.length == 0) {
		document.getElementById('bj-w').style.display = 'block';
	}	 
	if (document.resign.qrpassword.value != document.resign.password.value) {
		document.getElementById('qrmm-error').style.display = 'block';
	} 
	if (document.resign.stuId.value.length != 0 && document.resign.password.value.length != 0
			&& document.resign.qrpassword.value.length != 0 && document.resign.name.value.length != 0
			&& document.resign.classname.value.length != 0 && document.resign.qrpassword.value == document.resign.password.value)
		document.forms[0].submit();
}
</script>
</head>
<body id="body">
	<div id="login-bg">
		<div id="login-cell">
		<form  action = "StudentServlet?method=2" method = "post" name="resign">
			<div class="btn-group btn-group-lg " role="group">
				<div in="btn">
					<div id="resin-btn1">
						<button type="button" class="btn btn-danger btn-block">注册</button>
					</div>
					<div id="resin-btn2">
						<a href="/DFSystem/login.jsp"><button type="button" class="btn btn-primary  btn-block">登录</button></a>
					</div>
				</div>
			</div>
			<div class="input-group btn-block">
				<input id="xh" type="text" class="form-control" placeholder="学号"
					aria-describedby="basic-addon1" name="stuId">
			</div>
			<div id="xh-w" class="alert alert-danger" role="alert" style="display:none;">学号不能为空</div>
			<div class="input-group btn-block">
				<input  id="mm" type="password" class="form-control" placeholder="密码"
					aria-describedby="basic-addon1" name="password">
			</div>
			<div id="mm-w" class="alert alert-danger" role="alert" style="display:none;">密码不能为空</div>
			<div class="input-group btn-block">
				<input  id="qrmm" type="password" class="form-control" placeholder="确认密码"
					aria-describedby="basic-addon1" name="qrpassword">
			</div>
			<div id="qrmm-w" class="alert alert-danger" role="alert" style="display:none;">确认密码不能为空</div>
			<div id="qrmm-error" class="alert alert-danger" role="alert" style="display:none;">两次密码不一致</div>
			<div class="input-group btn-block">
				<input  id="xm" type="text" class="form-control" placeholder="姓名"
					aria-describedby="basic-addon1" name="name">
			</div>
						<div id="xm-w" class="alert alert-danger" role="alert" style="display:none;">姓名不能为空</div>
			<div class="input-group btn-block">
				<select id="bj" name="classname" width="100" class="btn btn-default dropdown-toggle">
	                <option>- 请选择班级 -</option>
	                <%
					for (ClassesModel classname : classList) {
					%>		             
	                	<option><%=classname.getClassName()%></option>
			        <%
				 	}
					%>
            	</select>
			</div>
		 			<div id="bj-w" class="alert alert-danger" role="alert" style="display:none;">班级不能为空</div>
			<div class="btn-group btn-block" role="group">
				<button type="button" class="btn btn-default  btn-block" onclick="isHidden()">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</button>
			</div>
			<div class="btn-group btn-block" role="group">
				<a href="/DFSystem/index.jsp"><button type="button" class="btn btn-default  btn-block">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</button></a>
			</div>
		</form>
		</div>
	</div>

</body>
</html>