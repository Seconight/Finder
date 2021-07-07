package FileInterface;

import java.io.File;
import java.util.List;

//�ļ��б��ܽӿ�
public interface FileChart {
	
	
	/**
	 * ��ȡ�ļ��б�
	 * @param path
	 * @return �ļ��б�
	 */
	public File[] getFileList(String path);
	
	/**
	 * ��������޸�ʱ������
	 * @param path Ҫ�����·��
	 * @param isUp ��ʶ�Ƿ�����,trueΪ����,falseΪ����
	 * @return �������ļ�����
	 */
	public File[] timeSortedList(String path, boolean isUp);
	
	/**
	 * �����ļ���������(������ĸ˳��)
	 * @param path
	 * @param isUp ��ʶ�Ƿ�����,trueΪ����,falseΪ����
	 * @return �������ļ�����
	 */
	public File[] typeSortedList(String path, boolean isUp);
	
	/**
	 * ������������
	 * @param path Ҫ�����·��
	 * @param isUp ��ʶ�Ƿ�����,trueΪ����,falseΪ����
	 * @return �������ļ�����
	 */
	public File[] nameSortedList(String path, boolean isUp);
	
	/**
	 * ��ȡȫ����ݷ���·��
	 * @return ·���б�
	 */
	public List<File> getAllVisitPath();
	
	/**
	 * �����Ӧ·�����ļ�
	 * @param path �ļ�·��
	 * @return
	 */
	public boolean insertVisitPath(String path);
	
	/**
	 * ɾ����Ӧ·�����ļ�
	 * @param path �ļ�·��
	 * @return 
	 */
	public boolean deleteVisitPath(String path);
	
	/**
	 * ����ļ��Ƿ��Ѿ�����
	 * @param path �ļ�·��
	 * @return ����Ϊtrue
	 */
	public boolean hasHadFile(String path);
	
}
