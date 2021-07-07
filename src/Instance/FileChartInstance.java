package Instance;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import DAO.FileDAO;
import FileInterface.FileChart;
import FileInterface.FileProperty;

public class FileChartInstance implements FileChart{
	
	@Override
	//��ȡ�ļ��б�
	public File[] getFileList(String path) {
		File file = new File(path);
		return file.listFiles();
	}

	@Override
	//��������޸�ʱ��ʱ��������
	public File[] timeSortedList(String path, boolean isUp) {
		File[] result = new File(path).listFiles();
		Arrays.sort(result, new Comparator<File>() {
			//��������
			@Override
			public int compare(File o1, File o2) {
				if(isUp) {
					if(o1.lastModified()>o2.lastModified())
						return 1;
					else if(o1.lastModified()<o2.lastModified())
						return -1;
					else
						return 0;
				}
				else {
					if(o1.lastModified()>o2.lastModified())
						return -1;
					else if(o1.lastModified()<o2.lastModified())
						return 1;
					else
						return 0;
				}
			}
			
		});
		return result;
	}

	@Override
	//�����ļ���������
	public File[] typeSortedList(String path, boolean isUp) {
		File[] result = new File(path).listFiles();
		Arrays.sort(result,new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				FileProperty property = new FilePropertyInstance();
				if(isUp)
					return property.getFileType(o1).charAt(0) - property.getFileType(o2).charAt(0);
				else
					return property.getFileType(o2).charAt(0) - property.getFileType(o1).charAt(0);
			}
		});
		return result;
	}

	@Override
	//�����ļ���������
	public File[] nameSortedList(String path, boolean isUp) {
		File[] result = new File(path).listFiles();
		Arrays.sort(result,new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				if(isUp)
					return o1.getName().charAt(0) - o2.getName().charAt(0);
				else
					return o2.getName().charAt(0) - o1.getName().charAt(0);
			}
		});
		return result;
	}
	
	public static void main(String[] args) {
		FileChartInstance instance = new FileChartInstance();
		FileProperty property = new FilePropertyInstance();
//		for(File file : instance.timeSortedList("D:\\\\Downloads\\", false)) {
//			System.out.println(file.getName() + " �޸�ʱ��:" + property.getModifiedTime(file));
//		}
//		for(File file : instance.typeSortedList("D://Downloads", false)) {
//			System.out.println(file.getName()+" �ļ�����:"+property.getFileType(file));
//		}
//		for(File file : instance.nameSortedList("D://Downloads", true)) {
//			System.out.println(file.getName());
//		}
	}

	@Override
	public List<File> getAllVisitPath() {
		FileDAO fileDAO = new FileDAO();
		return fileDAO.getAllVisitPath();
	}

	@Override
	public boolean insertVisitPath(String path) {
		FileDAO fileDAO = new FileDAO();
		return fileDAO.insertVisitPath(path);
	}

	@Override
	public boolean deleteVisitPath(String path) {
		FileDAO fileDAO = new FileDAO();
		return fileDAO.deleteVisitPath(path);
	}

	@Override
	public boolean hasHadFile(String path) {
		FileDAO fileDAO = new FileDAO();
		return fileDAO.hasHadFile(path);
	}
	

}
