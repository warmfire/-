<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ page import="model.CoursesModel" import="java.util.*"%>
<%@ page import="model.ClassesModel" import="java.util.*"%>
<%@ page import="model.StudentUserModel" import="java.util.*"%>
<%@ page import="model.OutGradesModel" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>打分系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/zujian.css" rel="stylesheet">

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

<script type="text/javascript">
	function isHidden(oDiv) {
		var a = [ 'zu11', 'zu21', 'zu31', 'zu41', 'zu51', 'zu61', 'zu71',
				'zu81', 'zu91', 'zu101', 'zu111', 'zu121', 'zu131', 'zu141',
				'zu151', 'zu161', 'zu171', 'zu181', 'zu191', 'zu201', 'zu211',
				'zu221', 'zu231', 'zu241' ];
		for (var i = 0; i < a.length; i++) {
			if (oDiv == a[i]) {
				var vDiv = document.getElementById(a[i]);
				if (vDiv.style.display == 'block') {
					vDiv.style.display = 'none';
				} else {
					vDiv.style.display = 'block';
				}
			} else {
				var vDiv = document.getElementById(a[i]);
				vDiv.style.display = 'none';
			}
		}
	}
</script>


<jsp:useBean id="cursDao" class="dao.CoursesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<CoursesModel> cursList = null;
	cursList = cursDao.queryCursList();
	
	CoursesModel cm = new CoursesModel();
	cm.setCursId(request.getParameter("cursId"));
	String cursName = cursDao.selectcurs(cm);
%>
<jsp:useBean id="stuNameDao" class="dao.StudentsDatabaseDao" scope="page"></jsp:useBean>
<%
	StudentUserModel stuName = new StudentUserModel();
	stuName = stuNameDao.execQuery("*","stuId",request.getParameter("id"));
%>
<jsp:useBean id="ogDao" class="dao.OutGradesDatabaseDao" scope="page"></jsp:useBean>
<%
	List<OutGradesModel> outgrades = null;
	OutGradesModel ogm = new OutGradesModel();
	ogm.setCursName(cursName);
	ogm.setClassName(stuName.getStuClass());
	int groups = ogDao.selectGroup(ogm);
%>
<script type="text/javascript">
	function change() {
		var index = document.getElementById("cursName").selectedIndex;
		var id = index;
		var stuid = document.getElementById("stuid").value;
		var str = "/DFSystem/zujian2.jsp?id=" + stuid + "&cursId=00" + id;
		window.location.href = str;
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
				href="/DFSystem/zunei.jsp?id=<%=request.getParameter("id")%>">打分系统 - 查看打分</a>
			<input type="text" id="stuid" value="<%=request.getParameter("id")%>" style="display:none;" ></input>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/DFSystem/zujian.jsp?id=<%=request.getParameter("id")%>&cursId=<%=request.getParameter("cursId")%>">返回</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> 欢迎您： <%=stuName.getStuName()%> 
					<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li role="separator" class="divider"></li>
						<li><a href="StudentServlet?method=0">退出</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>

	<form method="post" name="zujian">

		<nav>
		<ul class="pager">
			<li><a
				href="/DFSystem/zunei.jsp?id=<%=request.getParameter("id")%>">组内打分</a></li>
			<div class="btn-group">
				<select id="cursName" width="100" name="cursName"
					onchange="change()" class="btn btn-default dropdown-toggle">
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

			<div id="zu">
				<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand">第<%=i%>组
						</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1" onclick="isHidden('zu<%=i%>1')">
						<ul class="nav navbar-nav">
							<li><a></a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">打分<span class="caret"></span></a></li>
						</ul>
					</div>
				</div>
				</nav>
				<div id="zu<%=i%>1" style="display: none;">
					<div class="input-group">
						<div class="inputTitle_long">姓名</div>
						<div class="inputTitle_long">课程名</div>
						<div class="inputTitle_short">工作量25%</div>
						<div class="inputTitle_short">创新20%</div>
						<div class="inputTitle_short">技术20%</div>
						<div class="inputTitle_short">美观20%</div>
						<div class="inputTitle_short">进步15%</div>
						<div class="inputTitle_short">总分100%</div>
					</div>

					<form
						action="StudentServlet?method=11&id=<%=request.getParameter("id")%>
							&cursId=<%=request.getParameter("cursId")%>&groupid=<%=i%>"
						method="post" name="zujian">
						<jsp:useBean id="ogDaog" class="dao.OutGradesDatabaseDao"
							scope="page"></jsp:useBean>
						<%
						OutGradesModel ogm1 = new OutGradesModel();
						List<OutGradesModel> stuGrades = null;
						ogm1.setCursName(cursName);
						ogm1.setStuGroup(i);
						ogm1.setGrader(request.getParameter("id"));
						stuGrades = ogDaog.execQuery(ogm1);
					%>
						<%
						for (OutGradesModel og : stuGrades) {
					%>
						<div class="input-group">
							<input type="text" class="inputgrades_long"
								name="<%= og.getStuId() %>name" value="<%=og.getStuName()%>"
								aria-describedby="basic-addon1"> <input type="text"
								class="inputgrades_long" name="cursname"
								value="<%=og.getCursName()%>" aria-describedby="basic-addon1">
							<input type="text" class="inputgrades_short"
								name="<%= og.getStuId() %>workloads"
								value="<%=og.getWorkloads()%>" aria-describedby="basic-addon1">
							<input type="text" class="inputgrades_short"
								name="<%= og.getStuId() %>original"
								value="<%=og.getOriginal()%>" aria-describedby="basic-addon1">
							<input type="text" class="inputgrades_short"
								name="<%= og.getStuId() %>technology"
								value="<%=og.getTechnology()%>" aria-describedby="basic-addon1">
							<input type="text" class="inputgrades_short"
								name="<%= og.getStuId() %>beauty" value="<%=og.getBeauty()%>"
								aria-describedby="basic-addon1"> <input type="text"
								class="inputgrades_short" name="<%= og.getStuId() %>express"
								value="<%=og.getExpress()%>" aria-describedby="basic-addon1">
							<input type="text" class="inputgrades_short"
								name="<%= og.getStuId() %>allgrades"
								value="<%=og.getAllGrades()%>" aria-describedby="basic-addon1">
						</div>
						<%
						}
					%>
						<div class="addHeight"></div>
						<div class="btn_submit">
							<button type="submit" class="btn btn-primary">更新</button>
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