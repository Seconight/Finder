package Instance;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import FileInterface.FileProperty;

public class FilePropertyInstance implements FileProperty{

	//文件夹个数
	@Override
	public int getFolderNumber(File currentFile) {
		if(currentFile.isDirectory())
			return currentFile.listFiles().length;
		return 0;
	}

	//文件(夹)大小
	@Override
	public double getFileSize(File currentFile) {
		//为了更方便的传参，本函数在PropertyFrame中实现
		return 0;
	}

	//是否只读
	@Override
	public boolean isOnlyRead(File currentFile) {
		return !currentFile.canWrite();
	}

	//是否隐藏
	@Override
	public boolean isHide(File currentFile) {
		return currentFile.isHidden();
	}

	//文件类型
	@Override
	public String getFileType(File currentFile) {
		String type;
		if(currentFile.isDirectory()) {
			type = new String("文件夹");
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
			type += "文件";
		}
		return type;
	}

	//修改时间
	@Override
	public String getModifiedTime(File currentFile) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date(currentFile.lastModified()));
	}

}
