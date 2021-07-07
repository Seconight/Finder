package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FileInterface.FileProperty;
import Instance.FilePropertyInstance;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Jiaxing Tian
 * @school WuHan University of Technology
 * @email JiaxingTian@whut.edu.cn or 614300076@qq.com
 * @Date 2021/6/19
 * 
 */

public class PropertyFrame extends JFrame {

	private JPanel contentPane;
	
	public static int directoryNum = 0;
	public static int fileNumber = 0;
	//文件(夹)大小
	public JLabel size;
	//文件个数
	public JLabel number;
	
	//可读性和隐藏
	JLabel read;
	JLabel hide;
	JButton readSetButton;
	JButton hideSetButton;
	
	FileProperty fileProperty = new FilePropertyInstance();
	private JTextField position;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PropertyFrame frame = new PropertyFrame(new File("D://Downloads"));
		frame.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					long size = frame.getDictorySize("D://Downloads");
					DecimalFormat df = new DecimalFormat("#.00");
					frame.size.setText(df.format(size*1.0/1073741824)+"GB");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public PropertyFrame(File currentFile) {
		setTitle("\u5C5E\u6027");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/finder.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 721);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel fileNameLabel = new JLabel("\u6587\u4EF6\u540D\u79F0");
		fileNameLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		fileNameLabel.setBounds(29, 113, 113, 40);
		contentPane.add(fileNameLabel);
		
		JLabel positionLabel = new JLabel("\u6587\u4EF6\u4F4D\u7F6E");
		positionLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		positionLabel.setBounds(29, 168, 113, 40);
		contentPane.add(positionLabel);
		
		JLabel sizeLabel = new JLabel("\u6587\u4EF6\u5927\u5C0F");
		sizeLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		sizeLabel.setBounds(29, 223, 113, 40);
		contentPane.add(sizeLabel);
		
		JLabel typeLabel = new JLabel("\u6587\u4EF6\u7C7B\u578B");
		typeLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		typeLabel.setBounds(29, 278, 113, 40);
		contentPane.add(typeLabel);
		
		JLabel readLabel = new JLabel("\u662F\u5426\u53EA\u8BFB\uFF1F");
		readLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		readLabel.setBounds(52, 458, 113, 40);
		contentPane.add(readLabel);
		
		JLabel hideLabel = new JLabel("\u662F\u5426\u9690\u85CF\uFF1F");
		hideLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		hideLabel.setBounds(52, 526, 113, 40);
		contentPane.add(hideLabel);
		
		read = new JLabel("No");
		read.setHorizontalAlignment(SwingConstants.CENTER);
		read.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		read.setBounds(278, 458, 113, 40);
//		if(fileProperty.isOnlyRead(currentFile)) {
//			read.setText("Yes");
//		}
//		else
//			read.setText("No");
		contentPane.add(read);
		
		hide = new JLabel("No");
		hide.setHorizontalAlignment(SwingConstants.CENTER);
		hide.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		hide.setBounds(278, 526, 113, 40);
//		if(fileProperty.isHide(currentFile)) {
//			hide.setText("Yes");
//		}
//		else {
//			hide.setText("No");
//		}
		contentPane.add(hide);
		
		JLabel type = new JLabel("");
		type.setHorizontalAlignment(SwingConstants.CENTER);
		type.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		type.setBounds(254, 278, 152, 40);
		//设置类型
		type.setText(fileProperty.getFileType(currentFile));
		contentPane.add(type);
		
		size = new JLabel("calculating...");
		size.setHorizontalAlignment(SwingConstants.CENTER);
		size.setFont(new Font("微软雅黑", Font.PLAIN, 21));
		size.setBounds(238, 222, 178, 40);
		//TODO:设置大小
		
		contentPane.add(size);
		
		JLabel name = new JLabel("calculating...");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("微软雅黑", Font.PLAIN, 21));
		name.setBounds(264, 112, 138, 40);
		//设置名称
		name.setText(currentFile.getName());
		contentPane.add(name);
		
		JLabel fileNumberLabel = new JLabel("\u6587\u4EF6\u4E2A\u6570");
		fileNumberLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 21));
		fileNumberLabel.setBounds(29, 339, 113, 40);
		contentPane.add(fileNumberLabel);
		
		number = new JLabel("calculating...");
		number.setHorizontalAlignment(SwingConstants.CENTER);
		number.setFont(new Font("微软雅黑", Font.PLAIN, 21));
		number.setBounds(253, 338, 153, 40);
		contentPane.add(number);
		
		position = new JTextField();
		position.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		position.setBounds(250, 177, 178, 27);
		contentPane.add(position);
		position.setColumns(10);
		position.setText(currentFile.getAbsolutePath());
		
		JLabel label = new JLabel("----------------------------------------");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 21));
		label.setBounds(29, 387, 377, 40);
		contentPane.add(label);
		
		JLabel icon = new JLabel("");
		icon.setBounds(197, 24, 99, 73);
		if(currentFile.isDirectory()) {
			ImageIcon folderIcon = new ImageIcon("img/foldericon.png");
			folderIcon = new ImageIcon(folderIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			icon.setIcon(folderIcon);
		}
		else {
			ImageIcon fileIcon = new ImageIcon("img/fileicon.png");
			fileIcon = new ImageIcon(fileIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			icon.setIcon(fileIcon);
		}
		contentPane.add(icon);
		
		readSetButton = new JButton("\u8BBE\u4E3A");
		readSetButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		readSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(readSetButton.getText().equals("设为只读")) {
					currentFile.setReadOnly();
				}
				else {
					currentFile.setWritable(true);
				}
				fresh(currentFile);
			}
		});
		readSetButton.setBounds(42, 588, 138, 49);
		contentPane.add(readSetButton);
		
		hideSetButton = new JButton("\u8BBE\u4E3A");
		hideSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hideSetButton.getText().equals("设为隐藏")) {
					try {
						Runtime.getRuntime().exec("attrib \""+currentFile.getAbsolutePath()+"\" +H");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						Runtime.getRuntime().exec("attrib \""+currentFile.getAbsolutePath()+"\" -H");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				fresh(currentFile);
			}
		});
		hideSetButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		hideSetButton.setBounds(253, 588, 138, 49);
		contentPane.add(hideSetButton);
		
		fresh(currentFile);
		
	}

	public void fresh(File currentFile) {
		if(fileProperty.isHide(currentFile)) {
			hide.setText("Yes");
			hideSetButton.setText("设为非隐藏");
		}
		else {
			hide.setText("No");
			hideSetButton.setText("设为隐藏");
		}
		if(fileProperty.isOnlyRead(currentFile)) {
			read.setText("Yes");
			readSetButton.setText("设为可读");
		}
		else {
			read.setText("No");
			readSetButton.setText("设为只读");
		}
		
	}
	
	public long getDictorySize(String path) {
		File file = new File(path);
		if (file.exists()) {
            //递归计算
            if (file.isDirectory()) {
                directoryNum++;
                File[] children=file.listFiles();
                if(children==null){
                    return 0;
                }
                long size=0;
                for (File f : children) {
                    size += getDictorySize(f.getPath());
                }
                return size;
            }
            else{
                fileNumber++;
                long size=file.length();
                return size;
            }
        }
        else{
//            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
	}
}
