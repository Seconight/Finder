package GUIEntity;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.filechooser.FileSystemView;

import FileInterface.FileProperty;
import Instance.FilePropertyInstance;
import sun.awt.shell.*;

//�����޸��ļ���������
public class FileListCellRenderer extends JLabel implements ListCellRenderer{

	//ͼ��
	private ImageIcon icon;
	
	//�ļ���
	private String name;
	
	
	
	//
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		//��ȡ��ǰ�ļ�
		File currentfile = (File)value;
		//��ȡ����
		name = currentfile.getName();
		
		if(name.equals("")) {
			//��ʱΪ����Ŀ¼
			//����ͼ��
			Icon a = new ImageIcon("img\\a.jfif");
			icon = (ImageIcon) a;
			//����name
			name = currentfile.getPath();
			setText(name);
		}
		else//��ʱΪ����Ŀ¼
		{
			//����ͼ��
			if(currentfile.isDirectory())
				icon = new ImageIcon("img\\folder.png");
			else
				icon = (ImageIcon)FileSystemView.getFileSystemView().getSystemIcon(currentfile);
			//�����޸�ʱ��
			FileProperty fileProperty = new FilePropertyInstance();
			setText("<html>"+name+"<br><font face=\"Microsoft YaHei UI\" size=\"5\">" +"�޸�ʱ��:" + fileProperty.getModifiedTime(currentfile) 
			+ "      �ļ�����:" + fileProperty.getFileType(currentfile)+"</font></html>");
		
		}
		
		//����text
//		setText("<html>hello <br> world!</html>");
		
		
		//����ͼ��
		icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		setIcon(icon);
		
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else{
        	
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		
		setEnabled(true);
		this.setOpaque(true);
		
		//��������
		setFont(list.getFont());
		
//		//������ͼ���·�������ʾ
//		setVerticalTextPosition(JLabel.BOTTOM);
//		setHorizontalTextPosition(JLabel.CENTER);
		
		//����JLabel�Ĵ�С
		setPreferredSize(new Dimension(100,100));
		
		return this;
	}
	

}
