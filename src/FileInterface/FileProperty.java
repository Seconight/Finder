package FileInterface;

import java.io.File;

//文件属性功能接口
public interface FileProperty {
	
	/**
	 * 获取文件夹数量
	 * @param path 文件路径
	 * @return int,文件夹数量
	 */
	public int getFolderNumber(File currentFile);
	
	/**
	 * 获取文件大小
	 * @param path 文件路径
	 * @return double,文件大小
	 */
	public double getFileSize(File currentFile);
	
	/**
	 * 判断文件是否只读
	 * @param path 文件路径
	 * @return boolean
	 */
	public boolean isOnlyRead(File currentFile);
	
	/**
	 * 判断文件是否隐藏
	 * @param path 文件路径
	 * @return boolean
	 */
	public boolean isHide(File currentFile);
	
	/**
	 * 获取文件类型
	 * @param path 文件路径
	 * @return string, type
	 */
	public String getFileType(File currentFile);
	
	/**
	 * 获取文件修改时间
	 * @param path 文件路径
	 * @return string, time
	 */
	public String getModifiedTime(File currentFile);
	
}
