package cn.com.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 使用该类管理数据库连接
 * @author adminitartor
 *
 */
public class DBUtil {
	
	// DBCP的连接池实例
	private static BasicDataSource ds;
	
	static{
		try {
			/*
			 * java.util.Properties
			 * 该类可以解析properties文件，并将
			 * 内容以类似Map的形式给我们使用。
			 * "="左面为key,"="右面为value.
			 */
			Properties prop = new Properties();
			//通过ClassLoader从classes下读取
			//properties文件，web项目必须这样。
			prop.load(DBUtil.class.getClassLoader()
				.getResourceAsStream("config.properties"));
			
			String driverName = prop.getProperty("driverName");
			String host = prop.getProperty("host");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(
				prop.getProperty("maxActive")
			);
			long maxWait = Long.parseLong(
				prop.getProperty("maxWait")
			);
			
			//初始化连接池
			ds = new BasicDataSource();
			//设置驱动名,原Class.forName()中的内容
			ds.setDriverClassName(driverName);
			ds.setUrl(host);
			ds.setUsername(username);
			ds.setPassword(password);
			//连接池中的最大连接数
			ds.setMaxActive(maxActive);
			//最大等待时间
			ds.setMaxWait(maxWait);
			
			System.out.println("初始化完毕!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取一个数据库连接
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		/*
		 * 通过连接池获取连接
		 * 连接池的:Connection getConnection()
		 * 是一个阻塞方法，通常会立刻返回连接池
		 * 中的可用连接，但是当连接池中没有可用
		 * 连接时，该方法会阻塞，阻塞时间由创建
		 * 连接池时通过setMaxWait()方法设置的最大
		 * 等待时间决定，当阻塞期内有可用连接时，
		 * 该方法会立刻接触阻塞并将可用连接返回，
		 * 若阻塞超时时仍然没有可用连接，该方法会
		 * 抛出超时异常。
		 * 
		 */
		return ds.getConnection();
	}
	
	/**
	 * 关闭给定的连接
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn != null){
				/*
				 * 该连接是通过连接池获取回来的，
				 * 这个连接的close方法的作用是将该
				 * 连接还给连接池，而并不是与数据库
				 * 断开了连接.以便该连接还可以重用。
				 */
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}
	}
	
}



