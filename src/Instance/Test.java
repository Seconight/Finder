package Instance;

import java.io.File;
import java.util.Date;

import FileInterface.FileChart;

//≤‚ ‘¿‡
public class Test {
	public static void main(String[] args) {
//		FileChart fileChart = new FileChartInstance();
//		File[] files = fileChart.getFileList("D:\\Downloads");
//		for(File file : files) {
//			System.out.print(file.getName() + new Date(file.lastModified()));
//			
//		}
		File[] roots = File.listRoots();
		System.out.println(roots[0].getParent());
	}

}
