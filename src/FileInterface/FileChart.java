package FileInterface;

import java.io.File;
import java.util.List;

//文件列表功能接口
public interface FileChart {
	
	
	/**
	 * 获取文件列表
	 * @param path
	 * @return 文件列表
	 */
	public File[] getFileList(String path);
	
	/**
	 * 按照最后修改时间排序
	 * @param path 要排序的路径
	 * @param isUp 标识是否升序,true为升序,false为降序
	 * @return 排序后的文件序列
	 */
	public File[] timeSortedList(String path, boolean isUp);
	
	/**
	 * 按照文件类型排序(类型字母顺序)
	 * @param path
	 * @param isUp 标识是否升序,true为升序,false为降序
	 * @return 排序后的文件序列
	 */
	public File[] typeSortedList(String path, boolean isUp);
	
	/**
	 * 按照名称排序
	 * @param path 要排序的路径
	 * @param isUp 标识是否升序,true为升序,false为降序
	 * @return 排序后的文件序列
	 */
	public File[] nameSortedList(String path, boolean isUp);
	
	/**
	 * 获取全部快捷访问路径
	 * @return 路径列表
	 */
	public List<File> getAllVisitPath();
	
	/**
	 * 插入对应路径的文件
	 * @param path 文件路径
	 * @return
	 */
	public boolean insertVisitPath(String path);
	
	/**
	 * 删除对应路径的文件
	 * @param path 文件路径
	 * @return 
	 */
	public boolean deleteVisitPath(String path);
	
	/**
	 * 检查文件是否已经存在
	 * @param path 文件路径
	 * @return 存在为true
	 */
	public boolean hasHadFile(String path);
	
}
