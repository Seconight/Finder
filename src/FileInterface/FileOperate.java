package FileInterface;


public interface FileOperate {
	
	/**
	 * �����ļ�
	 * @param source Դ�ļ�·��
	 * @param destination Ŀ��·��
	 * @return trueΪ�ɹ�,����֮
	 */
	public boolean copyFile(String source, String destination);
	
	/**
	 * ɾ���ļ�
	 * @param path Ŀ���ļ�·��
	 * @return trueΪ�ɹ�,����֮
	 */
	public boolean deleteFile(String path);

}
