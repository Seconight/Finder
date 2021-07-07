package Instance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import FileInterface.DiskInfo;

public class DiskInfoInstance implements DiskInfo{
	
	@Override
	//获取磁盘信息
	public List<String> getDiskInfo() {
		
		//存放结果
		List<String> result = new ArrayList<>();
		
		//获取根目录
		File[] roots = File.listRoots();
		for(File file : roots) {
			result.add(file.getPath());
		}
		
		return result;
	}
	
	public File[] getDisks() {
		return File.listRoots();
	}
	
//	public static void main(String[] args) {
//		File[] roots = File.listRoots();
//		System.out.println(roots[0].isDirectory());
//		System.out.println(roots[0].getPath());
//		System.out.println(roots[0].isFile());
//		if(roots[0].getName().equals("")) System.out.println("aaaaaa");
//		System.out.println("C盘大小:"+roots[0].length());
//	}


}
