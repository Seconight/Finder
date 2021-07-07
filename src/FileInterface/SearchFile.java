package FileInterface;

import java.io.File;
import java.util.List;

//搜索文件功能接口
public interface SearchFile {
	
	/**
	 * 根据搜索位置&关键词搜索文件获得列表
	 * @param keywords,搜索关键词
	 * @return File[]
	 */
	public List<File> searchFileList(String searchPath, String keywords);
	
}
