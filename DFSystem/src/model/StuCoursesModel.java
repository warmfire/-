package model;

public class StuCoursesModel {
	
	//选课表模型
	
	private int id;
	private String cursName;
	private String stuId;
	private int stuGroup;
	private int isLeader;
	
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
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public int getStuGroup() {
		return stuGroup;
	}
	public void setStuGroup(int stuGroup) {
		this.stuGroup = stuGroup;
	}
	public int getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(int isLeader) {
		this.isLeader = isLeader;
	}
	
	
}
