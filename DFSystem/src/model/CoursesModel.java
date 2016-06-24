package model;

public class CoursesModel {	
	
	//课程表模型
	
	private int id;	
	private String cursName;
	private String cursId;
	private String teacher;
	
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
	public String getCursId() {
		return cursId;
	}
	public void setCursId(String cursId) {
		this.cursId = cursId;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	
}
