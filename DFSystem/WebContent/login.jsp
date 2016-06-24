<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - 打分系统</title>
<%@ include file="modelJSP/ref.jsp"%>
<style type="text/css">
body {
	margin:0px;
	background-image:url(img/indexBg.jpg);
	background-repeat:no-repeat;
	width:100%;
}
div{
	font-family:微软雅黑;
}
</style>

<script type="text/javascript">
	function isHidden() {
		document.getElementById('mm-w').style.display = 'none';
		document.getElementById('xh-w').style.display = 'none';
		if (document.login.id.value.length == 0) {
			document.getElementById('xh-w').style.display = 'block';
		} 
		if (document.login.pwd.value.length == 0) {
			document.getElementById('mm-w').style.display = 'block';
		}
		if (document.login.id.value.length != 0 && document.login.pwd.value.length != 0)
			document.forms[0].submit();
		
	}
	function forgit(){
		alert("请联系错觉工作室负责人：15158715552 / 655552");
	}
</script>

</head>
<body id="body">
	<div id="login-bg">
		<div id="login-cell">
		<form  action = "StudentServlet?method=1" method = "post" name="login">
			<div class="btn-group btn-group-lg " role="group">
				<div in="btn">
					<div id="btn1">
						<button type="button" class="btn btn-primary btn-block">登录</button>
					</div>
					<div id="btn2">
						<a href="/DFSystem/resign.jsp"><button type="button" class="btn btn-danger  btn-block">注册</button></a>
					</div>
				</div>
			</div>
			
			<div class="input-group btn-block">
				<input id="xh" type="text" class="form-control" placeholder="学号/工号"
					aria-describedby="basic-addon1" name="id">
			</div>
			<div id="xh-w" class="alert alert-danger" role="alert"
				style="display: none;">请输入你的学/工号</div>
			<div class="input-group btn-block">
				<input id="mm" type="password" class="form-control" placeholder="密码"
					aria-describedby="basic-addon1"  name="pwd">
				
			</div>
			<div id="mm-w" class="alert alert-danger" role="alert"
				style="display: none;">请输入你的密码</div>

			<div><span style="color:#FFFFFF;">请选择身份：</span>
				<select name="shenfen" width="100">
	                <option value ="学生">学生</option>
	                <option value ="教师">教师</option>
	            </select>
	            <span style="color:#FFFFFF;" onclick="forgit()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;忘记密码？</span>
            </div>
			

			<div class="btn-group btn-block" role="group">
				<button type="button" class="btn btn-default  btn-block"
					onclick="isHidden()">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
			</div>
			<div class="btn-group btn-block" role="group">
				<a href="/DFSystem/index.jsp"><button type="button" class="btn btn-default  btn-block">返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</button></a>
			</div>
		</form>
		</div>
	</div>

</body>
</html>