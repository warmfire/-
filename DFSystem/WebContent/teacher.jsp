<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.ClassesModel" import="java.util.*" %>
<%@ page import="model.CoursesModel" import="java.util.*" %>
<%@ page import="model.TeachersModel" import="java.util.*" %>
<%@ page import="model.TeaGradesModel" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>打分系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/teacher.css" rel="stylesheet">

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
a {
	font-family: 微软雅黑
}
div{
	font-family: 微软雅黑
}
body {
	background-color: #EEEEEE
}
</style>
<script type="text/javascript">
function isHidden(oDiv){
	var a = ['zu11','zu21','zu31','zu41','zu51','zu61','zu71','zu81','zu91','zu101','zu111','zu121','zu131','zu141','zu151','zu161','zu171','zu181','zu191','zu201','zu211','zu221','zu231','zu241'];			
	for (var i=0;i<a.length;i++)
	{
		if(oDiv==a[i])
		{
			var vDiv = document.getElementById(a[i]);	
			if(vDiv.style.display == 'block'){				
				vDiv.style.display = 'none';
			}
			else{				
				vDiv.style.display = 'block';
			}
		}
		else
		{
			var vDiv = document.getElementById(a[i]);
			vDiv.style.display = 'none';
		}
	}
}
</script>
<jsp:useBean id="classDao" class="dao.ClassesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<ClassesModel> classList = null;
	classList = classDao.queryClassList();
	
	ClassesModel classm = new ClassesModel();
	classm = classDao.execQuery("*","classId",request.getParameter("classId"));
	String className = classm.getClassName();
%>
<jsp:useBean id="cursDao" class="dao.CoursesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<CoursesModel> cursList = null;
	cursList = cursDao.queryCursList();
	
	CoursesModel cm1 = new CoursesModel();
	cm1.setCursId(request.getParameter("cursId"));
	String cursName = cursDao.selectcurs(cm1);
%>
<jsp:useBean id="teaNameDao" class="dao.TeachersDatabaseDao" scope="page"></jsp:useBean>
<%
	TeachersModel teaName = new TeachersModel();
	teaName = teaNameDao.execQuery("*","teaId",request.getParameter("id"));
%>
<jsp:useBean id="tgdd" class="dao.TeacherGradesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<TeaGradesModel> stuGp = null;
	TeaGradesModel tgm = new TeaGradesModel();
	tgm.setClassName(className);
	tgm.setCursName(cursName);
	int groups = tgdd.selectGroup(tgm);
%>
<script type="text/javascript">
	function change() {
		var classname = document.getElementById("className").selectedIndex;
		var index = document.getElementById("cursName").selectedIndex;
		var stuid = document.getElementById("teaid").value;
		var str = "/DFSystem/teacher.jsp?id=" + stuid + "&cursId=00" + index+ "&classId=00" +classname;
		window.location.href = str;
	}
</script>
</head>
<body>
	<script src="/js/collapse.js"></script>
	<nav class="navbar navbar-default navbar-static-top"> 
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/DFSystem/zunei.jsp">打分系统 - 教师打分</a>
			<input type="text" id="teaid" value="<%=request.getParameter("id")%>" style="display:none;" ></input>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
						<li><a href="/DFSystem/teacher2.jsp?id=<%=request.getParameter("id")%>&cursId=001&classId=001">查看打分</a></li>
						<li><a href="/DFSystem/addcourse.jsp?id=<%= request.getParameter("id")%>">添加课程</a></li>
						<li><a href="/DFSystem/addclass.jsp?id=<%= request.getParameter("id")%>">添加班级</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> 欢迎您：<%=teaName.getTeaName()%> <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="/DFSystem/changePwd2.jsp?id=<%=request.getParameter("id")%>">更改密码</a></li>
						<li><a href="StudentServlet?method=6&id=<%= request.getParameter("id")%>">导出成绩</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="StudentServlet?method=0">退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
	<form method="post" name="teacher">
	<nav>
	<ul class="pager">
		<div class="btn-group">
		    <select id="className" width="100" class="btn btn-default dropdown-toggle">
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
		<div class="btn-group">
		    <select id="cursName" onchange="change()" width="100" class="btn btn-default dropdown-toggle">
                 <option>- 请选择课程 -</option>
                 <%
					for (CoursesModel cursname : cursList) {
				 %>		             
                 	<option><%=cursname.getCursName()%></option>
		         <%
			 	 }
				 %>
            </select>
		</div>
	</ul>
	</nav>
	</form>
	<div class="centerinput">
		<%
			for (int i = 1; i <= groups; i++) {
		%>
		<div id="zu1">
			<nav class="navbar navbar-default">
			  <div class="container-fluid" >
			    <!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header">
			      <a class="navbar-brand">第<%=i%>组</a>
			    </div>
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"  onclick="isHidden('zu<%=i%>1')">
			      <ul class="nav navbar-nav">
			        <li><a></a></li>
			      </ul>
			      <ul class="nav navbar-nav navbar-right">
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
			          aria-haspopup="true" aria-expanded="false">打分<span class="caret"></span></a>
			        </li>
			      </ul>
			    </div>
			  </div>
			</nav>
			<div id="zu<%=i%>1" style="display:none;">
				<div class="input-group">
				    <div class="inputTitle_long">班级</div>
				    <div class="inputTitle_long">课程</div>
				    <div class="inputTitle_long">姓名</div>
				    <div class="inputTitle_long">学号</div>
				    <div class="inputTitle_short">总分</div>
				</div>
				<form
						action="StudentServlet?method=9&id=<%=request.getParameter("id")%>&classId=<%=request.getParameter("classId")%>
							&cursId=<%=request.getParameter("cursId")%>&groupid=<%=i%>"
						method="post" name="zujian">
				<jsp:useBean id="tDao" class="dao.TeacherGradesDatabaseDao" scope="page"></jsp:useBean>
				<%
					TeaGradesModel tgm1 = new TeaGradesModel();
					List<TeaGradesModel> stuGrades = null;
					tgm1.setCursName(cursName);
					tgm1.setGroupId(i);
					tgm1.setGrader("0");
					stuGrades = tDao.execQuery(tgm1);
					for (TeaGradesModel tg : stuGrades) {
				%>
				<div class="input-group">
					<input type="text" name="<%=tg.getStuId()%>classname"
						class="inputgrades_long" value="<%=tg.getClassName()%>"
						aria-describedby="basic-addon1">
					<input type="text" name="cursname"
						class="inputgrades_long" value="<%=tg.getCursName()%>"
						aria-describedby="basic-addon1">
					<input type="text" name="<%=tg.getStuId()%>stuname"
						class="inputgrades_long" value="<%=tg.getStuName()%>"
						aria-describedby="basic-addon1">
					<input type="text" name="<%=tg.getStuId()%>stuid"
						class="inputgrades_long" value="<%=tg.getStuId()%>"
						aria-describedby="basic-addon1"> 
					<input type="text" name="<%=tg.getStuId()%>allgrades"
						class="inputgrades_short" value="<%=tg.getAllGrades()%>"
						aria-describedby="basic-addon1"> 
				</div>
				
				<%
					}
				%>
				<div class="addHeight"></div>
				<div class="btn_submit">
				    <button type="submit" class="btn btn-primary">提交</button>
				</div>
			</form>
			</div>
		</div>
		
		<%
			}
		%>
	</div>
</body>
</html>