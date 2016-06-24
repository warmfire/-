package dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Database;
import model.TeachersModel;

public class TeachersDatabaseDao {
private Connection conn = Database.getDatabaseInstance().getConnection();
	
	public final static String MD5(String s) {
	    char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
	    try {
	        byte[] btInput = s.getBytes();
	        // ���MD5ժҪ�㷨�� MessageDigest ����
	        MessageDigest mdInst = MessageDigest.getInstance("MD5");
	        // ʹ��ָ�����ֽڸ���ժҪ
	        mdInst.update(btInput);
	        // �������
	        byte[] md = mdInst.digest();
	        // ������ת����ʮ�����Ƶ��ַ�����ʽ
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
	 * ��ʦ���ѯ
	 * 
	 * select teaId from teachers where teaName=? 
	 * 
	 * @param select	teaId(��Ҫ����ֵ����)
	 * @param colum		teaName(����ֵ����)
	 * @param arg		?(�����Ĳ���)
	 * */
	
	//ͼ��stuId��Ҫ�ĳ�String����
	public TeachersModel execQuery(String select, String column, String arg){
		PreparedStatement ps = null;
		ResultSet rs = null;
		TeachersModel student = null;
		String sql = "select " + select + " from teachers" + " where " + column + "=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, arg);
			rs = ps.executeQuery();
			if(rs.next()){
				student = new TeachersModel();
				student.setTeaId(rs.getString(2));
				student.setTeaName(rs.getString(3));
				student.setTeaPwd(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	/**
	 * ��ʦ��������
	 */
	
	public void changePwd(String id, String newpwd){
		PreparedStatement ps = null;
		String sql = null;
		sql = "update teachers set teaPwd = ? where teaId = ?";
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
