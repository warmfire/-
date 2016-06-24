package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	/**
	 * ����ģʽ
	 * */
	
	//���ݿ���
	private final static String name = "com.mysql.jdbc.Driver"; 
	
	//�����˿�
	private final static String url = "jdbc:mysql://localhost:3306/dfsystem";
	
	//�û���
	private final static String user = "root";									
	
	//��������
	private final static String password = "123456";							

	//���Ӷ���
	private static Connection conn = null;

	//Database����
	private static Database database = null;
	
	//
	private Database(){
	}
	
	//��̬��
	static {
		try {
			Class.forName(name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
	}

	//��ȡ���Ӷ���
	public static Database getDatabaseInstance(){
		if(database == null){
			//��������ֹ�̲߳���
			synchronized (Database.class) {
				if(conn == null){
					database = new Database();
				}
			}
		}
		return database;
	}
	
	//��ȡ����
	public Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ر�����
	public void closeConnection(ResultSet rs, Statement statement,
			Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
