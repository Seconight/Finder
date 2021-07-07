package Instance;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import FileInterface.FileProperty;

public class FilePropertyInstance implements FileProperty{

	//�ļ��и���
	@Override
	public int getFolderNumber(File currentFile) {
		if(currentFile.isDirectory())
			return currentFile.listFiles().length;
		return 0;
	}

	//�ļ�(��)��С
	@Override
	public double getFileSize(File currentFile) {
		//Ϊ�˸�����Ĵ��Σ���������PropertyFrame��ʵ��
		return 0;
	}

	//�Ƿ�ֻ��
	@Override
	public boolean isOnlyRead(File currentFile) {
		return !currentFile.canWrite();
	}

	//�Ƿ�����
	@Override
	public boolean isHide(File currentFile) {
		return currentFile.isHidden();
	}

	//�ļ�����
	@Override
	public String getFileType(File currentFile) {
		String type;
		if(currentFile.isDirectory()) {
			type = new String("�ļ���");
		}
		else {
			//attention: "." is undefined in Java
			String[] splitName = currentFile.getName().split("\\.");
			if(splitName.length == 1) {
				type = splitName[0];
			}
			else {
				type = splitName[splitName.length-1];
			}
			type += "�ļ�";
		}
		return type;
	}

	//�޸�ʱ��
	@Override
	public String getModifiedTime(File currentFile) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date(currentFile.lastModified()));
	}

}
