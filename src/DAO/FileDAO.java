package DAO;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileDAO extends BaseDAO{
	
	//获取全部快捷访问列表
	public List<File> getAllVisitPath() {
		//建立数据库
		connect();
		//获得结果
		List<File> result = new ArrayList<File>();
		//sql语句
		String sql = new String("select * from fastvisitpath;");
		Statement statement;
		try {
			statement = databaseConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String filePath = resultSet.getString("path");
				File currentFile = new File(filePath);
				result.add(currentFile);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭连接
		disconnect();
		return result;
	}
	
	//插入一个新的快捷访问路径
	public boolean insertVisitPath(String path) {
		//建立数据库
		connect();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = databaseConnection.prepareStatement(
					"insert into fastvisitpath values "
					+ "(?);");
			preparedStatement.setString(1, path);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//关闭数据库
		disconnect();
		return true;
	}
	
	//删除一个快捷访问路径
	public boolean deleteVisitPath(String path) {
		//连接数据库
		connect();
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = databaseConnection.prepareStatement(
					"delete from fastvisitpath where path=?;");
			preparedStatement.setString(1, path);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		//关闭数据库
		disconnect();
		return true;
	}
	
	//检查文件是否已经存在
	public boolean hasHadFile(String path) {
		//连接数据库
		connect();
		PreparedStatement statement;
		try {
			statement = databaseConnection.prepareStatement(
					"select * from fastvisitpath where path=?;");
			statement.setString(1, path);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String filePath = resultSet.getString("path");
				if(filePath.equals(path))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭数据库
		disconnect();
		return false;
	}
	
	//
	public static void main(String[] args) {
		FileDAO fileDAO = new FileDAO();
		List<File> getFiles = fileDAO.getAllVisitPath();
		for(File file : getFiles)
			System.out.println(file.getPath());
//		fileDAO.insertVisitPath("D:\\Documents");
//		fileDAO.deleteVisitPath("aaa");
		System.out.println(fileDAO.hasHadFile("D:\\Downloads"));
		System.out.println(fileDAO.hasHadFile("D:\\Documents"));
	}
}
