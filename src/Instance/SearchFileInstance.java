package Instance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import FileInterface.SearchFile;

public class SearchFileInstance implements SearchFile{

	//在对应路径寻找文件,返回文件数组
	@Override
	public List<File> searchFileList(String searchPath, String keywords) {
		File searchPachFile = new File(searchPath);
		//路径存在
		if(searchPachFile.exists()) {
			if(searchPachFile.isDirectory()) {
				List<File> result = new ArrayList<>();
				for(File current : searchPachFile.listFiles()) {
					if(current.getName().contains(keywords)) {
						result.add(current);
					}
				}
				return result;
			}
			//路径不是文件夹
			else {
				return null;
			}
		}
		else
			return null;
	}
	
	public static void main(String[] args) {
		SearchFileInstance instance = new SearchFileInstance();
		List<File> toSearch = instance.searchFileList("D://Downloads", "png");
		System.out.println("find file in "+toSearch.get(0).getParent());
		for(File file : toSearch) {
			System.out.println(file.getName());
		}
	}

}
