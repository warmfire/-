package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Database;
import model.ClassesModel;
import model.CoursesModel;
import model.InGradesModel;
import model.StudentUserModel;


public class ClassesDatabaseDao{

	private Connection conn = Database.getDatabaseInstance().getConnection();
	
	
	/* 查询课程表 */
	public ClassesModel execQuery(String select, String column, String arg){
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClassesModel classes = null;
		String sql = "select " + select + " from classes" + " where " + column + "=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, arg);
			rs = ps.executeQuery();
			if(rs.next()){
				classes = new ClassesModel();
				classes.setClassID(rs.getString(2));
				classes.setClassName(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	/* 添加课程 */
	public boolean addClass(ClassesModel classes){
		PreparedStatement ps = null;
		Boolean flag = false;
		String sql = "insert into classes(classID, className) values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, classes.getClassID());
			ps.setString(2, classes.getClassName());
			if(ps.executeUpdate() > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 班级下拉框
	 */
	
	public List<ClassesModel> queryClassList() {
		PreparedStatement ps = null;		
		List<ClassesModel> list = new ArrayList<ClassesModel>();
		String sql = null;
		sql = "select * from classes";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ClassesModel addCursModel = new ClassesModel();
				addCursModel.setClassName(rs.getString(3));
				list.add(addCursModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
