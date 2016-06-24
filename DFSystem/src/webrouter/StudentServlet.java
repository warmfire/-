package webrouter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ClassesDatabaseDao;
import dao.CoursesDatabaseDao;
import dao.InGradesDatabaseDao;
import dao.OutGradesDatabaseDao;
import dao.StuCoursesDatabaseDao;
import dao.StudentsDatabaseDao;
import dao.TeacherGradesDatabaseDao;
import dao.TeachersDatabaseDao;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import model.ClassesModel;
import model.CoursesModel;
import model.InGradesModel;
import model.OutGradesModel;
import model.StuCoursesModel;
import model.StudentUserModel;
import model.TeaGradesModel;
import model.TeachersModel;
import util.WriteExcel;
import util.WriteExcelout;
import util.WriteExceltout;

public class StudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8602248480545448854L;
	private String method;
	private String sid;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		method = request.getParameter("method");
		HttpSession session = request.getSession();
		// 退出登录
		if (method.equals("0")) {
			session.setAttribute("isLogin", "2");
			response.sendRedirect("index.jsp");
		}
		// 登录
		else if (method.equals("1")) {
			String shenfen = request.getParameter("shenfen");
			if ("学生".equals(shenfen)) {
				StudentsDatabaseDao sdd = new StudentsDatabaseDao();
				StudentUserModel sum = sdd.execQuery("*", "stuId", request.getParameter("id"));
				try{
					if (request.getParameter("id").equals(sum.getStuId()) &&
							MD5(request.getParameter("pwd")).equals(sum.getStuPwd())) {
						session.setAttribute("isLogin", "1");
						out.print("<script>window.location.href=\"zunei.jsp?id=");
						out.print(request.getParameter("id"));
						out.print("&cursId=001\";</script>");
					} else {
						out.print("<script>alert(\"Login Failed\");window.location.href=\"login.jsp\";</script>");
					}
				}
				catch(Exception e){
					out.print("<script>alert(\"Login Failed\");window.location.href=\"login.jsp\";</script>");
				}
			} else if ("教师".equals(shenfen)) {
				TeachersDatabaseDao tdd = new TeachersDatabaseDao();
				TeachersModel tm = tdd.execQuery("*", "teaId", request.getParameter("id"));
				try{
					if (request.getParameter("id").equals(tm.getTeaId()) &&
							MD5(request.getParameter("pwd")).equals(tm.getTeaPwd())) {
						session.setAttribute("isLogin", "1");
						out.print("<script>window.location.href=\"teacher.jsp?id=");
						out.print(request.getParameter("id"));
						out.print("&cursId=001&classId=001\";</script>");
					} else {
						out.print("<script>alert(\"Login Failed\");window.location.href=\"login.jsp\";</script>");
					}
				}
				catch(Exception e){
					out.print("<script>alert(\"Login Failed\");window.location.href=\"login.jsp\";</script>");
				}
			} else {
				out.print("<script>alert(\"Login Failed\");window.location.href=\"login.jsp\";</script>");
			}
		}
		// 注册
		else if (method.equals("2")) {
			String stuId = request.getParameter("stuId");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String classname = request.getParameter("classname");
			StudentUserModel sum = new StudentUserModel();
			StudentsDatabaseDao sdd = new StudentsDatabaseDao();
			sum.setStuId(stuId);
			sum.setStuName(name);
			sum.setStuPwd(password);
			sum.setStuClass(classname);
			if (stuId.equals("") || password.equals("") || name.equals("") || classname.equals("")) {
				out.print("<script>alert(\"Register Failed\");window.location.href=\"resign.jsp\";</script>");
			} else {
				if (sdd.insertOperation(sum)) {
					out.print("<script>alert(\"Register Success\");window.location.href=\"login.jsp\";</script>");
				} else {
					out.print("<script>alert(\"Register Failed\");window.location.href=\"resign.jsp\";</script>");
				}
			}
		}
		// 添加课程
		else if (method.equals("3")) {
			String cursId = request.getParameter("cursId");
			String cursName = request.getParameter("cursName");
			String id = request.getParameter("id");
			CoursesModel cm = new CoursesModel();
			CoursesDatabaseDao cdd = new CoursesDatabaseDao();
			cm.setCursId(cursId);
			cm.setCursName(cursName);
			cm.setTeacher(id);
			if (cursId.equals("") || cursName.equals("") || id.equals("")) {
				out.print("<script>alert(\"Add Failed(0x00000001)\");window.location.href=\"addcourse.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			} else {
				if (cdd.addCurs(cm)) {
					out.print("<script>alert(\"Add Successful\");window.location.href=\"teacher.jsp?id=");
					out.print(request.getParameter("id"));
					out.print("&cursId=001&classId=001\";</script>");
				} else {
					out.print("<script>alert(\"Add Failed(0x00000002)\");window.location.href=\"addcourse.jsp?id=");
					out.print(request.getParameter("id"));
					out.print("&cursId=001&classId=001\";</script>");
				}
			}
		}
		// 添加班级
		else if (method.equals("4")) {
			String className = request.getParameter("className");
			String classId = request.getParameter("classId");
			ClassesModel cm = new ClassesModel();
			ClassesDatabaseDao cdd = new ClassesDatabaseDao();
			cm.setClassName(className);
			cm.setClassID(classId);
			if (className.equals("")) {
				out.print("<script>alert(\"Add Failed(0x00000001)\");window.location.href=\"addclass.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			} else {
				if (cdd.addClass(cm)) {
					out.print("<script>alert(\"Add Successful\");window.location.href=\"teacher.jsp?id=");
					out.print(request.getParameter("id"));
					out.print("&cursId=001&classId=001\";</script>");
				} else {
					out.print("<script>alert(\"Add Failed(0x00000002)\");window.location.href=\"addclass.jsp?id=");
					out.print(request.getParameter("id"));
					out.print("&cursId=001&classId=001\";</script>");
				}
			}
		}
		// 学生选课
		else if (method.equals("5")) {
			String course = request.getParameter("course");
			String isleader = request.getParameter("isleader");
			int groupid = Integer.parseInt(request.getParameter("groupid"));
			
			StudentsDatabaseDao sdd = new StudentsDatabaseDao();
			StudentUserModel stuName = new StudentUserModel();
			stuName = sdd.execQuery("*","stuId",request.getParameter("id"));
			
			InGradesDatabaseDao igdd = new InGradesDatabaseDao();
			InGradesModel igm = new InGradesModel();
			igm.setStuName(stuName.getStuName());
			igm.setStuId(request.getParameter("id"));
			igm.setCursName(course);
			igm.setStuGroup(groupid);
			igm.setStuPart("角色");
			igm.setWorkloads(0);
			igm.setCooperate(0);
			igm.setOffer(0);
			igm.setAttitude(0);
			igm.setProgress(0);
			igm.setSelfgrades(0);
			igm.setAllGrades(0);
			igm.setGrader("0");
			
			OutGradesDatabaseDao ogdd = new OutGradesDatabaseDao();
			OutGradesModel ogm = new OutGradesModel();
			ogm.setStuName(stuName.getStuName());
			ogm.setStuId(request.getParameter("id"));
			ogm.setCursName(course);
			ogm.setStuGroup(groupid);
			ogm.setClassName(stuName.getStuClass());
			ogm.setWorkloads(0);
			ogm.setOriginal(0);
			ogm.setTechnology(0);
			ogm.setBeauty(0);
			ogm.setExpress(0);
			ogm.setAllGrades(0);
			ogm.setGrader("0");
			
			TeacherGradesDatabaseDao tgdd = new TeacherGradesDatabaseDao();
			TeaGradesModel tgm = new TeaGradesModel();
			tgm.setStuName(stuName.getStuName());
			tgm.setStuId(request.getParameter("id"));
			tgm.setCursName(course);
			tgm.setGroupId(groupid);
			tgm.setClassName(stuName.getStuClass());
			tgm.setAllGrades(0);
			tgm.setGrader("0");
						
			String id = request.getParameter("id");
			StuCoursesModel scm = new StuCoursesModel();
			StuCoursesDatabaseDao scd = new StuCoursesDatabaseDao();
			scm.setCursName(course);
			scm.setStuId(id);
			scm.setStuGroup(groupid);
			if ("是".equals(isleader)) {
				scm.setIsLeader(1);
			} else if ("否".equals(isleader)) {
				scm.setIsLeader(0);
			}
			if (request.getParameter("groupid").equals("")) {
				out.print("<script>alert(\"Select Failed(0x00000001)\");window.location.href=\"selectcourse.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			} else {
				if (scd.addCurs(scm)) {
					if(igdd.insertOperation(igm)){
						if(ogdd.insertOperation(ogm)){
							if(tgdd.insertOperation(tgm)){
								out.print("<script>alert(\"Select Successful\");window.location.href=\"zunei.jsp?id=");
								out.print(request.getParameter("id"));
								out.print("\";</script>");
							}							
						}						
					}
				} else {
					out.print(
							"<script>alert(\"Select Failed(0x00000002)\");window.location.href=\"selectcourse.jsp?id=");
					out.print(request.getParameter("id"));
					out.print("\";</script>");
				}
			}
		} 
		//导出Excel
		else if (method.equals("6")) {
			outexcel();
			out.print(
					"<script>alert(\"Output Successful\");window.location.href=\"teacher.jsp?id=");
			out.print(request.getParameter("id"));
			out.print("&cursId=001&classId=001\";</script>");
		}
		//组内打分
		else if (method.equals("7")) {
			try{
				String id = request.getParameter("id");
				CoursesDatabaseDao cdd = new CoursesDatabaseDao();
				CoursesModel cm = new CoursesModel();
				cm.setCursId(request.getParameter("cursId"));
				String cursName = cdd.selectcurs(cm);
				
				StuCoursesDatabaseDao scdd = new StuCoursesDatabaseDao();
				StuCoursesModel scm = new StuCoursesModel();
				scm.setCursName(cursName);
				scm.setStuId(id);
				int group = scdd.selectgroup(scm);

				List<InGradesModel> igModel = null;
				InGradesDatabaseDao idd = new InGradesDatabaseDao();
				InGradesModel igm = new InGradesModel();
				igm.setCursName(cursName);
				igm.setStuGroup(group);
				igm.setGrader("0");
				igModel = idd.execQuery(igm);
				for (InGradesModel ig : igModel) {
					sid = ig.getStuId(); 
					InGradesModel igm1 = new InGradesModel();
					igm1.setStuName(request.getParameter(sid+"name"));
					igm1.setStuId(sid);
					igm1.setCursName(request.getParameter("cursname"));
					igm1.setStuGroup(group);
					igm1.setStuPart(request.getParameter(sid+"part"));
					igm1.setWorkloads(Integer.parseInt(request.getParameter(sid+"workloads")));
					igm1.setOffer(Integer.parseInt(request.getParameter(sid+"offer")));
					igm1.setAttitude(Integer.parseInt(request.getParameter(sid+"attitude")));
					igm1.setCooperate(Integer.parseInt(request.getParameter(sid+"cooperate")));
					igm1.setProgress(Integer.parseInt(request.getParameter(sid+"progress")));
					igm1.setSelfgrades(Integer.parseInt(request.getParameter(sid+"selfgrades")));
					igm1.setAllGrades(Integer.parseInt(request.getParameter(sid+"allgrades")));
					igm1.setGrader(id);
					if(idd.insertOperation(igm1)){
					}
				}
				out.print(
						"<script>alert(\"Output Successful\");window.location.href=\"zunei2.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
			catch(Exception e){
				out.print(
						"<script>alert(\"Output Failed\");window.location.href=\"zunei.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
		}
		//组间打分
		else if (method.equals("8")) {
			try{
				String id = request.getParameter("id");
				
				CoursesDatabaseDao cdd = new CoursesDatabaseDao();
				CoursesModel cm = new CoursesModel();
				cm.setCursId(request.getParameter("cursId"));
				String cursName = cdd.selectcurs(cm);
				
				StudentsDatabaseDao sdd = new StudentsDatabaseDao();
				StudentUserModel cm1 = new StudentUserModel();
				cm1 = sdd.execQuery("*", "stuId", id);
				String className = cm1.getStuClass();
				
				List<OutGradesModel> igModel = null;
				OutGradesDatabaseDao igdDaoDao = new OutGradesDatabaseDao();
				OutGradesModel igm = new OutGradesModel();
				igm.setCursName(cursName);
				igm.setStuGroup(Integer.parseInt(request.getParameter("groupid")));
				igm.setGrader("0");
				igModel = igdDaoDao.execQuery(igm);
				
				for (OutGradesModel ig : igModel) {
					sid = ig.getStuId(); 
					OutGradesModel igm1 = new OutGradesModel();
					igm1.setStuName(request.getParameter(sid+"name"));
					igm1.setStuId(sid);
					igm1.setCursName(request.getParameter("cursname"));
					igm1.setClassName(className);
					igm1.setStuGroup(Integer.parseInt(request.getParameter("groupid")));
					igm1.setWorkloads(Integer.parseInt(request.getParameter(sid+"workloads")));
					igm1.setOriginal(Integer.parseInt(request.getParameter(sid+"original")));
					igm1.setTechnology(Integer.parseInt(request.getParameter(sid+"technology")));
					igm1.setBeauty(Integer.parseInt(request.getParameter(sid+"beauty")));
					igm1.setExpress(Integer.parseInt(request.getParameter(sid+"express")));
					igm1.setAllGrades(Integer.parseInt(request.getParameter(sid+"allgrades")));
					igm1.setGrader(id);
					if(igdDaoDao.insertOperation(igm1)){
					}
				}
				out.print(
						"<script>alert(\"Output Successful\");window.location.href=\"zujian2.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
			catch(Exception e){
				out.print(
						"<script>alert(\"Output Failed\");window.location.href=\"zujian.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
		}
		//老师打分
		else if(method.equals("9")){
			try{
				String id = request.getParameter("id");
				
				CoursesDatabaseDao cdd = new CoursesDatabaseDao();
				CoursesModel cm = new CoursesModel();
				cm.setCursId(request.getParameter("cursId"));
				String cursName = cdd.selectcurs(cm);
				
				ClassesDatabaseDao sdd = new ClassesDatabaseDao();
				ClassesModel cm1 = new ClassesModel();
				cm1 = sdd.execQuery("*","classID",request.getParameter("classId"));
				
				TeacherGradesDatabaseDao tDao = new TeacherGradesDatabaseDao();
				TeaGradesModel tgm = new TeaGradesModel();
				List<TeaGradesModel> teaGrades = null;
				tgm.setCursName(cursName);
				tgm.setGroupId(Integer.parseInt(request.getParameter("groupid")));
				tgm.setGrader("0");
				teaGrades = tDao.execQuery(tgm);
				
				for (TeaGradesModel tg : teaGrades) {
					sid = tg.getStuId(); 
					TeaGradesModel tgm1 = new TeaGradesModel();
					tgm1.setCursName(cursName);
					tgm1.setClassName(cm1.getClassName());
					tgm1.setStuName(request.getParameter(sid+"stuname"));
					tgm1.setStuId(sid);
					tgm1.setGroupId(Integer.parseInt(request.getParameter("groupid")));
					tgm1.setAllGrades(Integer.parseInt(request.getParameter(sid+"allgrades")));
					tgm1.setGrader(request.getParameter("id"));
					if(tDao.insertOperation(tgm1)){
					}
				}
				out.print(
						"<script>alert(\"Output Successful\");window.location.href=\"teacher.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			}
			catch(Exception e){
				out.print(
						"<script>alert(\"Output Failed\");window.location.href=\"teacher.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			}
		}
		//更新组内打分
		else if(method.equals("10")){
			try{
				String id = request.getParameter("id");
				CoursesDatabaseDao cdd = new CoursesDatabaseDao();
				CoursesModel cm = new CoursesModel();
				cm.setCursId(request.getParameter("cursId"));
				String cursName = cdd.selectcurs(cm);
				
				StuCoursesDatabaseDao scdd = new StuCoursesDatabaseDao();
				StuCoursesModel scm = new StuCoursesModel();
				scm.setCursName(cursName);
				scm.setStuId(id);
				int group = scdd.selectgroup(scm);

				List<InGradesModel> igModel = null;
				InGradesDatabaseDao idd = new InGradesDatabaseDao();
				InGradesModel igm = new InGradesModel();
				igm.setCursName(cursName);
				igm.setStuGroup(group);
				igm.setGrader(id);
				igModel = idd.execQuery(igm);
				for (InGradesModel ig : igModel) {
					sid = ig.getStuId(); 
					InGradesModel igm1 = new InGradesModel();
					igm1.setStuName(request.getParameter(sid+"name"));
					igm1.setCursName(request.getParameter("cursname"));
					igm1.setStuPart(request.getParameter(sid+"part"));
					igm1.setWorkloads(Integer.parseInt(request.getParameter(sid+"workloads")));
					igm1.setOffer(Integer.parseInt(request.getParameter(sid+"offer")));
					igm1.setAttitude(Integer.parseInt(request.getParameter(sid+"attitude")));
					igm1.setCooperate(Integer.parseInt(request.getParameter(sid+"cooperate")));
					igm1.setProgress(Integer.parseInt(request.getParameter(sid+"progress")));
					igm1.setSelfgrades(Integer.parseInt(request.getParameter(sid+"selfgrades")));
					igm1.setAllGrades(Integer.parseInt(request.getParameter(sid+"allgrades")));
					igm1.setGrader(id);
					if(idd.updateOperation(igm1)){
					}
				}
				out.print(
						"<script>alert(\"Output Successful\");window.location.href=\"zunei.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
			catch(Exception e){
				out.print(
						"<script>alert(\"Output Failed\");window.location.href=\"zunei2.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
		}
		//更新组间打分
		else if(method.equals("11")){
			try{
				String id = request.getParameter("id");
				
				CoursesDatabaseDao cdd = new CoursesDatabaseDao();
				CoursesModel cm = new CoursesModel();
				cm.setCursId(request.getParameter("cursId"));
				String cursName = cdd.selectcurs(cm);
				
				StuCoursesDatabaseDao scdd = new StuCoursesDatabaseDao();
				StuCoursesModel scm = new StuCoursesModel();
				scm.setCursName(cursName);
				scm.setStuId(id);
				int group = scdd.selectgroup(scm);
				
				StudentsDatabaseDao sdd = new StudentsDatabaseDao();
				StudentUserModel cm1 = new StudentUserModel();
				cm1 = sdd.execQuery("*", "stuId", id);
				String className = cm1.getStuClass();
				
				List<OutGradesModel> igModel = null;
				OutGradesDatabaseDao igdDaoDao = new OutGradesDatabaseDao();
				OutGradesModel igm = new OutGradesModel();
				igm.setCursName(cursName);
				igm.setStuGroup(group);
				igm.setGrader("0");
				igModel = igdDaoDao.execQuery(igm);
				
				for (OutGradesModel ig : igModel) {
					sid = ig.getStuId(); 
					OutGradesModel igm1 = new OutGradesModel();
					igm1.setStuName(request.getParameter(sid+"name"));
					igm1.setStuId(sid);
					igm1.setCursName(request.getParameter("cursname"));
					igm1.setClassName(className);
					igm1.setWorkloads(Integer.parseInt(request.getParameter(sid+"workloads")));
					igm1.setOriginal(Integer.parseInt(request.getParameter(sid+"original")));
					igm1.setTechnology(Integer.parseInt(request.getParameter(sid+"technology")));
					igm1.setBeauty(Integer.parseInt(request.getParameter(sid+"beauty")));
					igm1.setExpress(Integer.parseInt(request.getParameter(sid+"express")));
					igm1.setAllGrades(Integer.parseInt(request.getParameter(sid+"allgrades")));
					igm1.setGrader(id);
					if(igdDaoDao.updateOperation(igm1)){
					}
				}
				out.print(
						"<script>alert(\"Output Successful\");window.location.href=\"zujian.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001\";</script>");
			}
			catch(Exception e){
				out.print(
						"<script>alert(\"Output Failed\");window.location.href=\"zujian2.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("\";</script>");
			}
		}
		//更新老师打分
		else if(method.equals("12")){
			try{
				String id = request.getParameter("id");
				
				CoursesDatabaseDao cdd = new CoursesDatabaseDao();
				CoursesModel cm = new CoursesModel();
				cm.setCursId(request.getParameter("cursId"));
				String cursName = cdd.selectcurs(cm);
				
				ClassesDatabaseDao sdd = new ClassesDatabaseDao();
				ClassesModel cm1 = new ClassesModel();
				cm1 = sdd.execQuery("*","classID",request.getParameter("classId"));
				
				TeacherGradesDatabaseDao tDao = new TeacherGradesDatabaseDao();
				TeaGradesModel tgm = new TeaGradesModel();
				List<TeaGradesModel> teaGrades = null;
				tgm.setCursName(cursName);
				tgm.setGroupId(Integer.parseInt(request.getParameter("groupid")));
				tgm.setGrader("0");
				teaGrades = tDao.execQuery(tgm);
				
				for (TeaGradesModel tg : teaGrades) {
					sid = tg.getStuId(); 
					TeaGradesModel tgm1 = new TeaGradesModel();
					tgm1.setCursName(cursName);
					tgm1.setClassName(cm1.getClassName());
					tgm1.setStuName(request.getParameter(sid+"stuname"));
					tgm1.setAllGrades(Integer.parseInt(request.getParameter(sid+"allgrades")));
					tgm1.setGrader(request.getParameter("id"));
					if(tDao.updateOperation(tgm1)){
					}
				}
				out.print(
						"<script>alert(\"Output Successful\");window.location.href=\"teacher.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			}
			catch(Exception e){
				out.print(
						"<script>alert(\"Output Failed\");window.location.href=\"teacher2.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			}
		}
		//学生更改密码
		else if(method.equals("13")){
			String id = request.getParameter("id");
			String oldpwd = request.getParameter("oldPwd");
			String newpwd = request.getParameter("newPwd");
			
			StudentsDatabaseDao sdd = new StudentsDatabaseDao();
			StudentUserModel sum = new StudentUserModel();
			sum = sdd.execQuery("*", "stuId", id);
			
			if(sum.getStuPwd().equals(MD5(oldpwd))){
				sdd.changePwd(id, newpwd);
				out.print(
						"<script>alert(\"Change Successful\");window.location.href=\"zunei.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001\";</script>");
			}
			else{
				out.print(
						"<script>alert(\"Change Failed\");window.location.href=\"changePwd.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001\";</script>");
			}
		}
		//老师更改密码
		else if(method.equals("14")){
			String id = request.getParameter("id");
			String oldpwd = request.getParameter("oldPwd");
			String newpwd = request.getParameter("newPwd");
			
			TeachersDatabaseDao tdd = new TeachersDatabaseDao();
			TeachersModel tm = new TeachersModel();
			tm = tdd.execQuery("*", "teaId", id);
			
			if(tm.getTeaPwd().equals(MD5(oldpwd))){
				tdd.changePwd(id, newpwd);
				out.print(
						"<script>alert(\"Change Successful\");window.location.href=\"teacher.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			}
			else{
				out.print(
						"<script>alert(\"Change Failed\");window.location.href=\"teacher.jsp?id=");
				out.print(request.getParameter("id"));
				out.print("&cursId=001&classId=001\";</script>");
			}
		}
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public void outexcel(){
		new WriteExcel();
		new WriteExcelout();
		new WriteExceltout();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
