package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDAO {
	
	//����
	private static String name = "root";
	private static String password = "123456Tt";
	//////��������Ҫ����ʱ�� �汾����
	private static String url = "jdbc:mysql://localhost:3306/finder?serverTimezone=UTC";
	
    //�������ݿ����
    static Connection databaseConnection;
    
    //�������ݿ������
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
	        System.out.println("���ݿ�����ʧ��!");
	   }
    }
    
    //�ر����ݿ������
    protected void disconnect() {
    	try {
			databaseConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ�ر�����ʧ�ܣ�");
		}
    }
    
    
}
