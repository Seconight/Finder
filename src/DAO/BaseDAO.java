package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDAO {
	
	//常量
	private static String name = "root";
	private static String password = "123456Tt";
	//////在这里需要调整时区 版本问题
	private static String url = "jdbc:mysql://localhost:3306/finder?serverTimezone=UTC";
	
    //连接数据库对象
    static Connection databaseConnection;
    
    //进行数据库的连接
    protected void connect(){
    	try
	    {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        databaseConnection=DriverManager.getConnection(
	        		url,
	        		name,
	        		password);
	        //System.out.println("success!");
	     }
	      catch(Exception e)
	   {
	        e.printStackTrace();
	        System.out.println("数据库连接失败!");
	   }
    }
    
    //关闭数据库的连接
    protected void disconnect() {
    	try {
			databaseConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库关闭连接失败！");
		}
    }
    
    
}
