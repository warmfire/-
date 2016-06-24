package model;

public class OutGradesModel {

	//组内打分表模型
	
	private int id;
	private String stuName;
	private String stuId;
	private String cursName;	
	private String className;	
	private int stuGroup;
	private int workloads;
	private int original;
	private int technology;
	private int beauty;
	private int express;
	private int allGrades;
	private String Grader;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCursName() {
		return cursName;
	}
	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	public int getStuGroup() {
		return stuGroup;
	}
	public void setStuGroup(int stuGroup) {
		this.stuGroup = stuGroup;
	}
	public int getWorkloads() {
		return workloads;
	}
	public void setWorkloads(int workloads) {
		this.workloads = workloads;
	}
	public int getOriginal() {
		return original;
	}
	public void setOriginal(int original) {
		this.original = original;
	}
	public int getTechnology() {
		return technology;
	}
	public void setTechnology(int technology) {
		this.technology = technology;
	}
	public int getBeauty() {
		return beauty;
	}
	public void setBeauty(int beauty) {
		this.beauty = beauty;
	}
	public int getExpress() {
		return express;
	}
	public void setExpress(int express) {
		this.express = express;
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
