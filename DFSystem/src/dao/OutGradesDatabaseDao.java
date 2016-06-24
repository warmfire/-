package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Database;
import model.InGradesModel;
import model.OutGradesModel;

public class OutGradesDatabaseDao {
	
	private Connection conn = Database.getDatabaseInstance().getConnection();
	
	/**
	 * 组间表组别数量查询
	 * 
	 * @param OutGradesModel model
	 * @return int count
	 * */
	public int selectGroup(OutGradesModel model){
		ResultSet rs = null;
		PreparedStatement ps = null;
		int count = 0;		
		String sql = "select distinct stuGroup from outgrades where cursName=? and className=?";
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
	 * 组间表查询
	 * 
	 * @param OutGradesModel model
	 * @return List<OutGradesModel>
	 * */
	public List<OutGradesModel> execQuery(OutGradesModel model){
		OutGradesModel outGrade = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<OutGradesModel> list = new ArrayList<OutGradesModel>();
		String sql = "select stuName, stuId, cursName, stuGroup, workloads, original, technology, "
				+ "beauty, express, allGrades from outgrades where cursName=? and stuGroup=? and Grader=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCursName());
			ps.setInt(2, model.getStuGroup());
			ps.setString(3, model.getGrader());
			rs = ps.executeQuery();
			while(rs.next()){
				outGrade = new OutGradesModel();
				outGrade.setStuName(rs.getString("stuName"));
				outGrade.setStuId(rs.getString("stuId"));
				outGrade.setCursName(rs.getString("cursName"));
				outGrade.setStuGroup(rs.getInt("stuGroup"));
				outGrade.setWorkloads(rs.getInt("workloads"));
				outGrade.setOriginal(rs.getInt("original"));
				outGrade.setTechnology(rs.getInt("technology"));
				outGrade.setBeauty(rs.getInt("beauty"));
				outGrade.setExpress(rs.getInt("express"));
				outGrade.setAllGrades(rs.getInt("allGrades"));
				list.add(outGrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	
	
	
	/**
	 * 组间表打分
	 * 
	 * @param OutGradesModel outGrade
	 * @return boolean
	 * */
	public boolean insertOperation(OutGradesModel model){
		Boolean b = false;
		PreparedStatement ps = null;
		String sql = "insert into outgrades(stuName,stuId,cursName,className,stuGroup,workloads,"
				+ "original,technology,beauty,express,allGrades,Grader) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getStuName());
			ps.setString(2, model.getStuId());
			ps.setString(3, model.getCursName());
			ps.setString(4, model.getClassName());
			ps.setInt(5, model.getStuGroup());
			ps.setInt(6, model.getWorkloads());
			ps.setInt(7, model.getOriginal());
			ps.setInt(8, model.getTechnology());
			ps.setInt(9, model.getBeauty());
			ps.setInt(10, model.getExpress());
			ps.setInt(11, model.getAllGrades());
			ps.setString(12, model.getGrader());
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
	public boolean updateOperation(OutGradesModel model){
		Boolean b = false;
		PreparedStatement ps = null;
		String sql = "update outgrades set workloads=?,original=?,technology=?,beauty=?,express=?,allGrades=?"
				+ " where stuName=? and cursName=? and Grader=? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, model.getWorkloads());
			ps.setInt(2, model.getOriginal());
			ps.setInt(3, model.getTechnology());
			ps.setInt(4, model.getBeauty());
			ps.setInt(5, model.getExpress());
			ps.setInt(6, model.getAllGrades());
			ps.setString(7, model.getStuName());
			ps.setString(8, model.getCursName());
			ps.setString(9, model.getGrader());
			if(ps.executeUpdate() > 0)
				b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 导出组间表excel
	 * 
	 * */
	public ArrayList setContent()
	{
		ArrayList<String> list = null;
		ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from outgrades";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list = new ArrayList<String>();
				for(int j = 0; j < 13; j++){
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
