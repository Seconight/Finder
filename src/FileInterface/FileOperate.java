package FileInterface;


public interface FileOperate {
	
	/**
	 * 复制文件
	 * @param source 源文件路径
	 * @param destination 目标路径
	 * @return true为成功,否则反之
	 */
	public boolean copyFile(String source, String destination);
	
	/**
	 * 删除文件
	 * @param path 目标文件路径
	 * @return true为成功,否则反之
	 */
	public boolean deleteFile(String path);

}
