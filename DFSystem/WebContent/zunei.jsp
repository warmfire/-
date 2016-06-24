<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ page import="model.CoursesModel" import="java.util.*"%>
<%@ page import="model.StuCoursesModel" import="java.util.*"%>
<%@ page import="model.StudentUserModel" import="java.util.*"%>
<%@ page import="model.InGradesModel" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>打分系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/zunei.css" rel="stylesheet">

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

div {
	font-family: 微软雅黑
}

body {
	background-color: #EEEEEE
}
</style>
<jsp:useBean id="cursDao" class="dao.CoursesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<CoursesModel> cursList = null;
	cursList = cursDao.queryCursList();
	
	CoursesModel cm1 = new CoursesModel();
	cm1.setCursId(request.getParameter("cursId"));
	String cursName = cursDao.selectcurs(cm1);
%>
<jsp:useBean id="stuNameDao" class="dao.StudentsDatabaseDao" scope="page"></jsp:useBean>
<%
	StudentUserModel stuName = new StudentUserModel();
	stuName = stuNameDao.execQuery("*","stuId",request.getParameter("id"));
%>
<jsp:useBean id="stucursDao" class="dao.StuCoursesDatabaseDao" scope="page"></jsp:useBean>
<%
	StuCoursesModel scm = new StuCoursesModel();
	scm.setCursName(cursName);
	scm.setStuId(request.getParameter("id"));
	int group = stucursDao.selectgroup(scm);
%>
<jsp:useBean id="igdDaoDao" class="dao.InGradesDatabaseDao" scope="page"></jsp:useBean>
<%

	List<InGradesModel> igModel = null;
	InGradesModel igm = new InGradesModel();
	igm.setCursName(cursName);
	igm.setStuGroup(group);
	igm.setGrader("0");
	igModel = igdDaoDao.execQuery(igm);
%>
<script type="text/javascript">
	function change(){
		var index=document.getElementById("cursName").selectedIndex;
		var id = index;
		var stuid = document.getElementById("stuid").value;
		var str = "/DFSystem/zunei.jsp?id="+stuid+"&cursId=00"+id;
		window.location.href=str;
	}
</script>
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
			<a class="navbar-brand"
				href="/DFSystem/zunei.jsp?id=<%=request.getParameter("id")%>&cursId=001">打分系统 - 组内打分</a>
			<input type="text" id="stuid" value="<%=request.getParameter("id")%>" style="display:none;" ></input>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/DFSystem/zunei2.jsp?id=<%=request.getParameter("id")%>&cursId=<%=request.getParameter("cursId")%>">查看打分</a></li>
						<li><a
							href="/DFSystem/selectcourse.jsp?id=<%=request.getParameter("id")%>">添加课程</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> 欢迎您：
					<%=stuName.getStuName()%> 
 					<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="/DFSystem/changePwd.jsp?id=<%=request.getParameter("id")%>">更改密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="StudentServlet?method=0">退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>

	<form action="StudentServlet?method=7&id=<%=request.getParameter("id")%>&cursId=<%=request.getParameter("cursId")%>" method="post" name="zunei">
		<nav>
		<ul class="pager">
			<li><a
				href="/DFSystem/zujian.jsp?id=<%=request.getParameter("id")%>&cursId=<%=request.getParameter("cursId")%>">组间打分</a></li>
			<div class="btn-group">
				<select id="cursName" width="100" name="cursName" onchange="change()"
					class="btn btn-default dropdown-toggle">
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

		<div class="centerinput">
			<div class="input-group">
				<div class="inputTitle_long">姓名</div>
				<div class="inputTitle_long">课程名</div>
				<div class="inputTitle_long">角色</div>
				<div class="inputTitle_short">工作量25%</div>
				<div class="inputTitle_short">贡献20%</div>
				<div class="inputTitle_short">态度20%</div>
				<div class="inputTitle_short">合作20%</div>
				<div class="inputTitle_short">进步15%</div>
				<div class="inputTitle_long">自评分100%</div>
				<div class="inputTitle_long">互评分100%</div>
			</div>
			<div class="input-group">
					<%
						for (InGradesModel ig : igModel) {
					%> 
						<input type="text" name="<%= ig.getStuId() %>name" 
						class="inputgrades_long" value=<%= ig.getStuName() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="cursname" 
						class="inputgrades_long" value=<%= ig.getCursName() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>part" 
						class="inputgrades_long" value=<%= ig.getStuPart() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>workloads" 
						class="inputgrades_short" value=<%= ig.getWorkloads() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>offer" 
						class="inputgrades_short" value=<%= ig.getOffer() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>attitude" 
						class="inputgrades_short" value=<%= ig.getAttitude() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>cooperate" 
						class="inputgrades_short" value=<%= ig.getCooperate() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>progress" 
						class="inputgrades_short" value=<%= ig.getProgress() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>selfgrades" 
						class="inputgrades_long" value=<%= ig.getSelfgrades() %>
						aria-describedby="basic-addon1"> 
						<input type="text" name="<%= ig.getStuId() %>allgrades" 
						class="inputgrades_long" value=<%= ig.getAllGrades() %>
						aria-describedby="basic-addon1"> 
					<%
 						}
 					%> 				
			</div>

			<div class="btn_submit">
				<button type="submit" class="btn btn-primary">提交</button>
			</div>
		</div>

	</form>
</body>
</html>