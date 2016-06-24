package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Database;
import model.StudentUserModel;

public class StudentsDatabaseDao{

	private Connection conn = Database.getDatabaseInstance().getConnection();
	
	/**
	 * 学生表查询
	 * 
	 * select stuId from Students where stuName=? 
	 * 
	 * @param select	stuId(需要返回值的列)
	 * @param colum		stuName(条件值的列)
	 * @param arg		?(条件的参数)
	 * */
	
	//图中stuId需要改成String类型
	public StudentUserModel execQuery(String select, String column, String arg){
		PreparedStatement ps = null;
		ResultSet rs = null;
		StudentUserModel student = null;
		String sql = "select " + select + " from students" + " where " + column + "=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, arg);
			rs = ps.executeQuery();
			if(rs.next()){
				student = new StudentUserModel();
				student.setStuId(rs.getString(2));
				student.setStuName(rs.getString(3));
				student.setStuPwd(rs.getString(4));
				student.setStuClass(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 学生表注册
	 * 
	 * @param stu 		包含stuId, stuName, stuPwd, stuClass
	 * */
	
	public boolean insertOperation(StudentUserModel stu){
		PreparedStatement ps = null;
		Boolean flag = false;
		String sql = "insert into students(stuId, stuName, stuPwd, stuClass) values(?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, stu.getStuId());
			ps.setString(2, stu.getStuName());
			ps.setString(3, MD5(stu.getStuPwd()));
			ps.setString(4, stu.getStuClass());
			if(ps.executeUpdate() > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 学生表更改密码
	 * 
	 * */
	
	public void changePwd(String id, String newpwd){
		PreparedStatement ps = null;
		String sql = null;
		sql = "update students set stuPwd = ? where stuId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, MD5(newpwd));
			ps.setString(2, id);
			if(ps.executeUpdate() > 0){
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
