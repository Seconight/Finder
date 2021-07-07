package Instance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import FileInterface.DiskInfo;

public class DiskInfoInstance implements DiskInfo{
	
	@Override
	//��ȡ������Ϣ
	public List<String> getDiskInfo() {
		
		//��Ž��
		List<String> result = new ArrayList<>();
		
		//��ȡ��Ŀ¼
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
//		System.out.println("C�̴�С:"+roots[0].length());
//	}


}
