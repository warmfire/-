package model;

public class TeaGradesModel {

	//教师打分表模型
	
	private int id;
	private String cursName;
	private String stuName;
	private String stuId;
	private int groupId;
	private int allGrades;
	private String Grader;
	private String className;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCursName() {
		return cursName;
	}
	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getAllGrades() {
		return allGrades;
	}
	public void setAllGrades(int allGrades) {
		this.allGrades = allGrades;
	}
	public String getGrader() {
		return Grader;
	}
	public void setGrader(String grader) {
		Grader = grader;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
