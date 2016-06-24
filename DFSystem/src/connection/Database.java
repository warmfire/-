package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	/**
	 * 单例模式
	 * */
	
	//数据库名
	private final static String name = "com.mysql.jdbc.Driver"; 
	
	//主机端口
	private final static String url = "jdbc:mysql://localhost:3306/dfsystem";
	
	//用户名
	private final static String user = "root";									
	
	//连接密码
	private final static String password = "123456";							

	//连接对象
	private static Connection conn = null;

	//Database对象
	private static Database database = null;
	
	//
	private Database(){
	}
	
	//静态块
	static {
		try {
			Class.forName(name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}

	//获取连接对象
	public static Database getDatabaseInstance(){
		if(database == null){
			//加锁，防止线程并发
			synchronized (Database.class) {
				if(conn == null){
					database = new Database();
				}
			}
		}
		return database;
	}
	
	//获取连接
	public Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭连接
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
