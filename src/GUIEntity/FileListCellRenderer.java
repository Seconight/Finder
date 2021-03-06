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

//用来修改文件区域的外观
public class FileListCellRenderer extends JLabel implements ListCellRenderer{

	//图标
	private ImageIcon icon;
	
	//文件名
	private String name;
	
	
	
	//
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		//获取当前文件
		File currentfile = (File)value;
		//获取名称
		name = currentfile.getName();
		
		if(name.equals("")) {
			//此时为磁盘目录
			//设置图标
			Icon a = new ImageIcon("img\\a.jfif");
			icon = (ImageIcon) a;
			//设置name
			name = currentfile.getPath();
			setText(name);
		}
		else//此时为正常目录
		{
			//设置图标
			if(currentfile.isDirectory())
				icon = new ImageIcon("img\\folder.png");
			else
				icon = (ImageIcon)FileSystemView.getFileSystemView().getSystemIcon(currentfile);
			//设置修改时间
			FileProperty fileProperty = new FilePropertyInstance();
			setText("<html>"+name+"<br><font face=\"Microsoft YaHei UI\" size=\"5\">" +"修改时间:" + fileProperty.getModifiedTime(currentfile) 
			+ "      文件类型:" + fileProperty.getFileType(currentfile)+"</font></html>");
		
		}
		
		//设置text
//		setText("<html>hello <br> world!</html>");
		
		
		//设置图标
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
		
		//设置字体
		setFont(list.getFont());
		
//		//文字在图标下方居中显示
//		setVerticalTextPosition(JLabel.BOTTOM);
//		setHorizontalTextPosition(JLabel.CENTER);
		
		//设置JLabel的大小
		setPreferredSize(new Dimension(100,100));
		
		return this;
	}
	

}
