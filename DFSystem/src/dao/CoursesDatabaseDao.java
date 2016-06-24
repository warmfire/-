package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Database;
import model.CoursesModel;

public class CoursesDatabaseDao{

	private Connection conn = Database.getDatabaseInstance().getConnection();
	private String cursName;
	
	/**
	 * 根据课程id查询课程名
	 */
	
	public String selectcurs(CoursesModel model){
		ResultSet rs = null;
		PreparedStatement ps = null;
		String s = "select cursName from courses where cursId = ?";
		try {
			ps = conn.prepareStatement(s);
			ps.setString(1, model.getCursId());
			rs = ps.executeQuery();
			if(rs.next()){
				cursName = rs.getString("cursName");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return cursName;
	}
	
	/**
	 * 添加课程
	 */
	
	public boolean addCurs(CoursesModel acm) {
		PreparedStatement ps = null;
		Boolean flag = false;
		String sql = "insert into courses(cursId, cursName, teacher) values(?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, acm.getCursId());
			ps.setString(2, acm.getCursName());
			ps.setString(3, acm.getTeacher());
			if(ps.executeUpdate() > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 课程下拉框
	 */
	
	public List<CoursesModel> queryCursList() {
		PreparedStatement ps = null;		
		List<CoursesModel> list = new ArrayList<CoursesModel>();
		String sql = null;
		sql = "select * from courses";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CoursesModel addCursModel = new CoursesModel();
				addCursModel.setCursName(rs.getString(3));
				list.add(addCursModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
