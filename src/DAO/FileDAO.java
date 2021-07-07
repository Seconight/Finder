package DAO;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileDAO extends BaseDAO{
	
	//��ȡȫ����ݷ����б�
	public List<File> getAllVisitPath() {
		//�������ݿ�
		connect();
		//��ý��
		List<File> result = new ArrayList<File>();
		//sql���
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
		//�ر�����
		disconnect();
		return result;
	}
	
	//����һ���µĿ�ݷ���·��
	public boolean insertVisitPath(String path) {
		//�������ݿ�
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
		//�ر����ݿ�
		disconnect();
		return true;
	}
	
	//ɾ��һ����ݷ���·��
	public boolean deleteVisitPath(String path) {
		//�������ݿ�
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
		
		//�ر����ݿ�
		disconnect();
		return true;
	}
	
	//����ļ��Ƿ��Ѿ�����
	public boolean hasHadFile(String path) {
		//�������ݿ�
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
		//�ر����ݿ�
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
