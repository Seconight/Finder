package Instance;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import FileInterface.FileOperate;

public class FileOperateInstance implements FileOperate{

	//复制文件或者文件夹
	@Override
	public boolean copyFile(String source, String destination) {
		// TODO Auto-generated method stub
		File sourceFile = new File(source);
		if(!sourceFile.isDirectory()) {
			try {
//				System.out.println("正在复制文件："+sourceFile.getName());
				//字节输入流——读取文件
				FileInputStream in = new FileInputStream(source);
				//字节输出流——写入文件
				FileOutputStream out;
				out = new FileOutputStream(destination);
				//把读取到的内容写入新文件
				//把字节数组设置大一些   1*1024*1024=1M
				byte[] bs = new byte[1*1024*1024];	
				int count = 0;
				while((count = in.read(bs))!=-1){
					out.write(bs,0,count);
				}
				//关闭流
				in.close();
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
		else {
			//创建目标目录的File对象
			File destDir = new File(destination);	
			//如果目的目录不存在
			if(!destDir.exists()){
				//创建目的目录
				destDir.mkdir();
			}
			//获取源目录下的File对象列表
			File[]files = sourceFile.listFiles();
			for (File file : files) {
				//拼接新的源文件和目标文件的路径
				String strFrom = source + File.separator + file.getName();
//				System.out.println(strFrom);
				String strTo = destination + File.separator + file.getName();
//				System.out.println(strTo);
				//递归调用复制
				if(!copyFile(strFrom,strTo))
					return false;
			}
			return true;
		}
	}

	//删除文件(夹)
	@Override
	public boolean deleteFile(String path) {
		File file = new File(path);
        //递归删除目录
        if(file.isDirectory()){
            File[] childrenFiles = file.listFiles();
            for (File childFile:childrenFiles){
                if(!deleteFile(childFile.getPath()))
                	return false;
            }
        }
        //删除 文件、空目录
        return file.delete();
	}

	public static void main(String[] args) {
		FileOperateInstance instance = new FileOperateInstance();
//		File source = new File("D:\\aaaa");
		File target = new File("D:\\Downloads\\test\\aaaa");
//		instance.copyFile(source.getPath(), target.getPath()+"\\"+source.getName());
		instance.deleteFile(target.getPath());
	}
	
}
