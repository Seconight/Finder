package Instance;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import FileInterface.FileOperate;

public class FileOperateInstance implements FileOperate{

	//�����ļ������ļ���
	@Override
	public boolean copyFile(String source, String destination) {
		// TODO Auto-generated method stub
		File sourceFile = new File(source);
		if(!sourceFile.isDirectory()) {
			try {
//				System.out.println("���ڸ����ļ���"+sourceFile.getName());
				//�ֽ�������������ȡ�ļ�
				FileInputStream in = new FileInputStream(source);
				//�ֽ����������д���ļ�
				FileOutputStream out;
				out = new FileOutputStream(destination);
				//�Ѷ�ȡ��������д�����ļ�
				//���ֽ��������ô�һЩ   1*1024*1024=1M
				byte[] bs = new byte[1*1024*1024];	
				int count = 0;
				while((count = in.read(bs))!=-1){
					out.write(bs,0,count);
				}
				//�ر���
				in.close();
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
		else {
			//����Ŀ��Ŀ¼��File����
			File destDir = new File(destination);	
			//���Ŀ��Ŀ¼������
			if(!destDir.exists()){
				//����Ŀ��Ŀ¼
				destDir.mkdir();
			}
			//��ȡԴĿ¼�µ�File�����б�
			File[]files = sourceFile.listFiles();
			for (File file : files) {
				//ƴ���µ�Դ�ļ���Ŀ���ļ���·��
				String strFrom = source + File.separator + file.getName();
//				System.out.println(strFrom);
				String strTo = destination + File.separator + file.getName();
//				System.out.println(strTo);
				//�ݹ���ø���
				if(!copyFile(strFrom,strTo))
					return false;
			}
			return true;
		}
	}

	//ɾ���ļ�(��)
	@Override
	public boolean deleteFile(String path) {
		File file = new File(path);
        //�ݹ�ɾ��Ŀ¼
        if(file.isDirectory()){
            File[] childrenFiles = file.listFiles();
            for (File childFile:childrenFiles){
                if(!deleteFile(childFile.getPath()))
                	return false;
            }
        }
        //ɾ�� �ļ�����Ŀ¼
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
