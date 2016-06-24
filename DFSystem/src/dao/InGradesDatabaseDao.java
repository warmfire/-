package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Database;
import model.InGradesModel;

public class InGradesDatabaseDao {

	private Connection conn = Database.getDatabaseInstance().getConnection();
	
	
	
	/**
	 * 组内表查询
	 * 
	 * @param InGradesModel model
	 * @return List<InGradesModel>
	 * */
	public List<InGradesModel> execQuery(InGradesModel model){
		
		InGradesModel inGrade = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		List<InGradesModel> list = new ArrayList<InGradesModel>();
		String sql = "select stuName, cursName, stuId, stuPart, workloads, offer, attitude, "
				+ "cooperate, progress, selfgrades, allGrades from ingrades where cursName=? and stuGroup=? and Grader=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCursName());
			ps.setInt(2, model.getStuGroup());
			ps.setString(3, model.getGrader());
			rs = ps.executeQuery();
			while(rs.next()){
				inGrade = new InGradesModel();
				inGrade.setStuName(rs.getString("stuName"));
				inGrade.setCursName(rs.getString("cursName"));
				inGrade.setStuId(rs.getString("stuId"));
				inGrade.setStuPart(rs.getString("stuPart"));
				inGrade.setWorkloads(rs.getInt("workloads"));
				inGrade.setOffer(rs.getInt("offer"));
				inGrade.setAttitude(rs.getInt("attitude"));
				inGrade.setCooperate(rs.getInt("cooperate"));
				inGrade.setProgress(rs.getInt("progress"));
				inGrade.setSelfgrades(rs.getInt("selfgrades"));
				inGrade.setAllGrades(rs.getInt("allGrades"));
				list.add(inGrade);
				System.out.println(list.size()+"123");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * 组内表打分
	 * 
	 * @param InGradesModel inGrade
	 * @return boolean
	 * */
	public boolean insertOperation(InGradesModel inGrade){
		Boolean b = false;
		PreparedStatement ps = null;
		String sql = null;
		sql = "insert into ingrades(stuName,stuId,cursName,stuGroup,stuPart,workloads,offer,"
				+ "attitude,cooperate,progress,selfgrades,allgrades,Grader) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inGrade.getStuName());
			ps.setString(2, inGrade.getStuId());
			ps.setString(3, inGrade.getCursName());
			ps.setInt(4, inGrade.getStuGroup());
			ps.setString(5, inGrade.getStuPart());
			ps.setInt(6, inGrade.getWorkloads());
			ps.setInt(7, inGrade.getOffer());
			ps.setInt(8, inGrade.getAttitude());
			ps.setInt(9, inGrade.getCooperate());
			ps.setInt(10, inGrade.getProgress());
			ps.setInt(11, inGrade.getSelfgrades());
			ps.setInt(12, inGrade.getAllGrades());
			ps.setString(13, inGrade.getGrader());
			if(ps.executeUpdate() > 0)
				b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 更新组内表打分
	 * 
	 * @param InGradesModel inGrade
	 * @return boolean
	 * */
	public boolean updateOperation(InGradesModel inGrade){
		Boolean b = false;
		PreparedStatement ps = null;
		String sql = null;
		sql = "update ingrades set stuPart = ?, workloads=?, offer=?, attitude=?, cooperate=?,"
				+ "progress=?, selfgrades=?, allgrades=? where stuName = ? and cursName=? and Grader=? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inGrade.getStuPart());
			ps.setInt(2, inGrade.getWorkloads());
			ps.setInt(3, inGrade.getOffer());
			ps.setInt(4, inGrade.getAttitude());
			ps.setInt(5, inGrade.getCooperate());
			ps.setInt(6, inGrade.getProgress());
			ps.setInt(7, inGrade.getSelfgrades());
			ps.setInt(8, inGrade.getAllGrades());
			ps.setString(9, inGrade.getStuName());
			ps.setString(10, inGrade.getCursName());
			ps.setString(11, inGrade.getGrader());
			if(ps.executeUpdate() > 0)
				b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 导出组内表excel
	 * 
	 * */
	
	public ArrayList setContent()
	{
		ArrayList<String> list = null;
		ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from ingrades";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list = new ArrayList<String>();
				for(int j = 0; j < 14; j++){
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
