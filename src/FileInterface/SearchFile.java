package FileInterface;

import java.io.File;
import java.util.List;

//�����ļ����ܽӿ�
public interface SearchFile {
	
	/**
	 * ��������λ��&�ؼ��������ļ�����б�
	 * @param keywords,�����ؼ���
	 * @return File[]
	 */
	public List<File> searchFileList(String searchPath, String keywords);
	
}
