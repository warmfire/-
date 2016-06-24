package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Database;
import model.OutGradesModel;
import model.TeaGradesModel;

public class TeacherGradesDatabaseDao {
	
	private Connection conn = Database.getDatabaseInstance().getConnection();
	
	
	/**
	 * 教师表查询
	 * 
	 * @param OutGradesModel model
	 * @return List<OutGradesModel>
	 * */
	public List<TeaGradesModel> execQuery(TeaGradesModel model){
		TeaGradesModel teaGrade = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<TeaGradesModel> list = new ArrayList<TeaGradesModel>();
		String sql = "select className, cursName, stuName, stuId, allGrades from teagrades where cursName=? and groupId=? and Grader = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCursName());
			ps.setInt(2, model.getGroupId());
			ps.setString(3, model.getGrader());
			rs = ps.executeQuery();
			while(rs.next()){
				teaGrade = new TeaGradesModel();
				teaGrade.setClassName(rs.getString("className"));
				teaGrade.setCursName(rs.getString("cursName"));
				teaGrade.setStuName(rs.getString("stuName"));
				teaGrade.setStuId(rs.getString("stuId"));
				teaGrade.setAllGrades(rs.getInt("allGrades"));
				list.add(teaGrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	
	/**
	 * 教师表组别查询
	 * 
	 * @param OutGradesModel model
	 * @return int count
	 * */
	public int selectGroup(TeaGradesModel model){
		ResultSet rs = null;
		PreparedStatement ps = null;
		int count = 0;
		String sql = "select distinct groupId from teagrades where cursName=? and className=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCursName());
			ps.setString(2, model.getClassName());
			rs = ps.executeQuery();
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 组间表打分
	 * 
	 * @param OutGradesModel outGrade
	 * @return boolean
	 * */
	public boolean insertOperation(TeaGradesModel model){
		Boolean b = false;
		PreparedStatement ps = null;
		String sql = "insert into teagrades(cursName,className,stuName,stuId,groupId,allGrades,Grader)"
				+ " values(?,?,?,?,?,?,?)";
		try {
			System.out.println(model.getCursName()+model.getClassName()+model.getStuName()+model.getStuId()
			+model.getGroupId()+model.getAllGrades()+model.getGrader());
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCursName());
			ps.setString(2, model.getClassName());
			ps.setString(3, model.getStuName());
			ps.setString(4, model.getStuId());
			ps.setInt(5, model.getGroupId());
			ps.setInt(6, model.getAllGrades());
			ps.setString(7, model.getGrader());
			if(ps.executeUpdate() > 0)
				b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	

	/**
	 * 更新组间表打分
	 * 
	 * @param OutGradesModel outGrade
	 * @return boolean
	 * */
	public boolean updateOperation(TeaGradesModel model){
		Boolean b = false;
		PreparedStatement ps = null;
		String sql ="update teagrades set allGrades=? where cursName=? and className=? and stuName=? and Grader=? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, model.getAllGrades());
			ps.setString(2, model.getCursName());
			ps.setString(3, model.getClassName());
			ps.setString(4, model.getStuName());
			ps.setString(5, model.getGrader());
			if(ps.executeUpdate() > 0)
				b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 导出教师表excel
	 * 
	 * */
	public ArrayList setContent()
	{
		ArrayList<String> list = null;
		ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from teagrades";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list = new ArrayList<String>();
				for(int j = 0; j < 8; j++){
					list.add(rs.getString(j+1));
				}
				listAll.add(list);
			}
			return listAll;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
		}
		
	}
}
