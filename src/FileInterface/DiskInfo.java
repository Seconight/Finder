package FileInterface;

import java.util.List;

//磁盘信息功能接口
public interface DiskInfo {
	
	/**
	 * 获得磁盘信息
	 * @return 磁盘列表,List(String)
	 */
	public List<String> getDiskInfo();

}
