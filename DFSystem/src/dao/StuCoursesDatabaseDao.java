package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Database;
import model.StuCoursesModel;

public class StuCoursesDatabaseDao{

	private Connection conn = Database.getDatabaseInstance().getConnection();
	private int group;
	
	/**
	 * 组别查询
	 */
	
	public int selectgroup(StuCoursesModel model){
		ResultSet rs = null;
		PreparedStatement ps = null;
		String s = "select stuGroup from stucourses where cursName = ? and stuId = ?";
		try {
			ps = conn.prepareStatement(s);
			ps.setString(1, model.getCursName());
			ps.setString(2, model.getStuId());
			rs = ps.executeQuery();
			if(rs.next()){
				group = rs.getInt("stuGroup");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return group;
	}
	
	/**
	 * 添加课程
	 */
	public boolean addCurs(StuCoursesModel model) {
		PreparedStatement ps = null;
		Boolean flag = false;
		String sql = "insert into stucourses(cursName, stuId, stuGroup, isLeader) values(?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCursName());
			ps.setString(2, model.getStuId());
			ps.setInt(3, model.getStuGroup());
			ps.setInt(4, model.getIsLeader());
			if(ps.executeUpdate() > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
