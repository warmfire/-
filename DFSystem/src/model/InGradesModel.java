package model;

public class InGradesModel {

	//组间打分表模型
	
	private int id;
	private String stuName;
	private String stuId;
	private String cursName;	
	private int stuGroup;
	private String stuPart;
	private int workloads;
	private int offer;
	private int attitude;
	private int cooperate;
	private int progress;	
	private int selfgrades;
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
	public String getStuPart() {
		return stuPart;
	}
	public void setStuPart(String stuPart) {
		this.stuPart = stuPart;
	}
	public int getWorkloads() {
		return workloads;
	}
	public void setWorkloads(int workloads) {
		this.workloads = workloads;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}
	public int getAttitude() {
		return attitude;
	}
	public void setAttitude(int attitude) {
		this.attitude = attitude;
	}
	public int getCooperate() {
		return cooperate;
	}
	public void setCooperate(int cooperate) {
		this.cooperate = cooperate;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public int getSelfgrades() {
		return selfgrades;
	}
	public void setSelfgrades(int selfgrades) {
		this.selfgrades = selfgrades;
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
	
}
