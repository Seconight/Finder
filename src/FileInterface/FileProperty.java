package FileInterface;

import java.io.File;

//�ļ����Թ��ܽӿ�
public interface FileProperty {
	
	/**
	 * ��ȡ�ļ�������
	 * @param path �ļ�·��
	 * @return int,�ļ�������
	 */
	public int getFolderNumber(File currentFile);
	
	/**
	 * ��ȡ�ļ���С
	 * @param path �ļ�·��
	 * @return double,�ļ���С
	 */
	public double getFileSize(File currentFile);
	
	/**
	 * �ж��ļ��Ƿ�ֻ��
	 * @param path �ļ�·��
	 * @return boolean
	 */
	public boolean isOnlyRead(File currentFile);
	
	/**
	 * �ж��ļ��Ƿ�����
	 * @param path �ļ�·��
	 * @return boolean
	 */
	public boolean isHide(File currentFile);
	
	/**
	 * ��ȡ�ļ�����
	 * @param path �ļ�·��
	 * @return string, type
	 */
	public String getFileType(File currentFile);
	
	/**
	 * ��ȡ�ļ��޸�ʱ��
	 * @param path �ļ�·��
	 * @return string, time
	 */
	public String getModifiedTime(File currentFile);
	
}
