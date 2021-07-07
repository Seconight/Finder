package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import DAO.FileDAO;
import FileInterface.DiskInfo;
import FileInterface.FileChart;
import FileInterface.FileOperate;
import FileInterface.FileProperty;
import FileInterface.SearchFile;
import GUIEntity.FastVisitCellRenderer;
import GUIEntity.FileListCellRenderer;
import GUIEntity.NodeCellRenderer;
import Instance.DiskInfoInstance;
import Instance.FileChartInstance;
import Instance.FileOperateInstance;
import Instance.FilePropertyInstance;
import Instance.SearchFileInstance;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JTree;
import javax.swing.JList;
import java.awt.event.MouseMotionAdapter;

/**
 * 
 * @author Jiaxing Tian
 * @school WuHan University of Technology
 * @email JiaxingTian@whut.edu.cn or 614300076@qq.com
 * @Date 2021/6/19
 * 
 */

//主界面
public class MainFrame extends JFrame {

	//主容器
	private JPanel contentPane;
	
	//显示组件
	JTree fileTree;		//文件树
	TreePath treePath;	//树路径
	JPanel infoPanel;	//信息栏
	JLabel fileNumber;	//文件数量标签
	JLabel fileSize;	//文件大小标签
	JLabel size;		//文件数量
	JLabel number;		//文件大小
	JComboBox comboBox;	//排序方式复选框
	JLabel sortLabel;	//排序方式标签
	JList fileChart;	//文件列表
	DefaultListModel defaultListModel;	//文件列表Model
	JPanel fastVisitPane;				//快速访问区域
	JLabel fastVisitLabel;				//快速访问标签
	DefaultListModel fastListModel;		//快速访问列表Model
	JPanel folderIndexPane;				//文件夹索引区域
	JLabel indexLabel;					//索引标签
	JScrollPane fileTreeScrollPane;		//文件树滚轮区域
	JScrollPane fileChartScrollPane;	//文件列表滚轮区域
	JPopupMenu folderMenu;				//文件夹右击菜单
	JPopupMenu fileMenu;				//文件右击菜单
	
	//逻辑组件
	DiskInfo diskInfo = new DiskInfoInstance();				//磁盘信息
	FileChart chart = new FileChartInstance();				//文件列表
	FileProperty property = new FilePropertyInstance();		//文件属性
	SearchFile search = new SearchFileInstance();			//文件搜索
	FileOperate operate = new FileOperateInstance();		//文件操作
	
	//定义itself
	MainFrame _this = this;
	
	//当前文件路径
	String currentFilePath = new String();
	
	//复制路径
	String copyPath = new String("");
	
	//维护两个栈,保持前进和返回的目录
	//在回退时当前路径入栈,在前进(跳转)时弹栈,点击按钮跳转到top位置
	Deque<String> front = new ArrayDeque<String>();
	//在前进(跳转)时入栈,在后退时弹栈,跳转到top位置
	//for example
	// A/B/C front() back(A,A/B)
	// A/B front(A/B/C) back(A)
	// D/F front() back(A,A/B)
	Deque<String> back = new ArrayDeque<String>();
	
	//搜索功能维护变量
	String keywords = new String();		//搜索关键字
	String searchPath = new String();	//搜索路径
	
	//前进、后退组件
	private JLabel nextPageLabel;
	private JLabel backPageLabel;
	//快速访问区域
	private final JScrollPane fastVisitScrollPane = new JScrollPane();
	//快速访问列表
	private JList fastVisitList;
	private JLabel pasteLabel;
	//快速访问区域功能
//	FileDAO  = new FileDAO();

	/**
	 * Launch the application.入口函数
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		//设置标题
		setTitle("Finder");
		//设置图标
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/finder.png"));
		//设置关闭按钮
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置大小
		setBounds(100, 100, 1353, 880);
		//主容器及其格式
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		//信息栏
		infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(null);
		infoPanel.setBounds(295, 668, 1036, 156);
		contentPane.add(infoPanel);
		
//		//文件数量标识
//		fileNumber = new JLabel("文件数量:");
//		fileNumber.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
//		fileNumber.setBounds(26, 96, 114, 49);
//		infoPanel.add(fileNumber);
		
//		//文件大小表示
//		fileSize = new JLabel("文件大小:");
//		fileSize.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
//		fileSize.setBounds(15, 29, 114, 40);
//		infoPanel.add(fileSize);
		
//		//文件大小变量
//		size = new JLabel("root");
//		size.setFont(new Font("Consolas", Font.PLAIN, 24));
//		size.setBounds(142, 34, 139, 37);
//		infoPanel.add(size);
		
//		//文件数量变量
//		number = new JLabel("2");
//		number.setFont(new Font("Consolas", Font.PLAIN, 24));
//		number.setBounds(155, 105, 101, 37);
//		infoPanel.add(number);
		
		//排序复选框
		String[] sortMethods = {"按文件名称升序","按文件名称降序",
								"按操作时间升序","按操作时间降序",
								"按文件类型升序","按文件类型降序"};
		comboBox = new JComboBox(sortMethods);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
		comboBox.setBounds(258, 87, 194, 47);
		//Lamda表达式为comboBox添加事件
		comboBox.addItemListener(e -> {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				String sortType = (String)comboBox.getSelectedItem();
				File[] sortedFiles;
				if(sortType.equals("按文件名称升序")) {
					sortedFiles = chart.nameSortedList(currentFilePath, true);
				}
				else if(sortType.equals("按文件名称降序")) {
					sortedFiles = chart.nameSortedList(currentFilePath, false);
				}
				else if(sortType.equals("按操作时间升序")) {
					sortedFiles = chart.timeSortedList(currentFilePath, true);
				}
				else if(sortType.equals("按操作时间降序")) {
					sortedFiles = chart.timeSortedList(currentFilePath, false);
				}
				else if(sortType.equals("按文件类型升序")) {
					sortedFiles = chart.typeSortedList(currentFilePath, true);
				}
				else  {
					sortedFiles = chart.typeSortedList(currentFilePath, false);
				}
				//刷新文件列表
				defaultListModel.clear();
				for(File file : sortedFiles) {
					defaultListModel.addElement(file);
				}
				fileChart.setModel(defaultListModel);
				fileChart.setCellRenderer(new FileListCellRenderer());
			}
		});
		infoPanel.add(comboBox);
		
		//排序标签
		sortLabel = new JLabel("\u6392\u5E8F\u65B9\u5F0F");
		sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sortLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
		sortLabel.setBounds(286, 34, 114, 40);
		infoPanel.add(sortLabel);
		
		//新建文件夹功能按钮
		JLabel newFolderLabel = new JLabel("\u65B0\u5EFA\u6587\u4EF6\u5939");
		newFolderLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			
			//以下两个函数使鼠标移动出现边框效果
			@Override
			public void mouseExited(MouseEvent e) {
				newFolderLabel.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				newFolderLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//点击新建文件夹按钮进入对应页面
				NewFolderFrame newFolderFrame = new NewFolderFrame(currentFilePath) {
					@Override
					//重写dispose方法
					public void dispose() {
						//非根目录
						if(!currentFilePath.equals("")) {
							turnToPath(new File(currentFilePath));
							super.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">不能在根目录创建文件夹!</font></html>");
							super.dispose();
						}
					}
				};
				newFolderFrame.setVisible(true);
			}
		}
		);
		newFolderLabel.setBounds(590, 34, 96, 100);
		newFolderLabel.setHorizontalAlignment(JLabel.CENTER);
		newFolderLabel.setVerticalTextPosition(JLabel.BOTTOM);
		newFolderLabel.setHorizontalTextPosition(JLabel.CENTER);
		Icon newFolderIcon = new ImageIcon("img/newFolder.png");
		newFolderIcon = new ImageIcon(((ImageIcon) newFolderIcon).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		newFolderLabel.setIcon(newFolderIcon);
		newFolderLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		infoPanel.add(newFolderLabel);
		
		//转到功能按钮
		JLabel turnLabel = new JLabel("\u8F6C\u5230");
		turnLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}	
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//以下两个函数使鼠标移动出现边框效果
			@Override
			public void mouseExited(MouseEvent e) {
				turnLabel.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				turnLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//维护栈
				back.push(currentFilePath);
				
				// TODO 转到功能按钮
				TurnFrame turnFrame = new TurnFrame(_this) {
					@Override
					public void dispose() {
						if(currentFilePath.equals(""))
							turnToDisk();
						else {
							File turnFile = new File(currentFilePath);
							if(turnFile.exists()) {
								if(turnFile.isDirectory())
									turnToPath(turnFile);
								else {
									JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">不能跳转到文件!</font></html>");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">地址不存在!</font></html>");
							}
						}
						super.dispose();
					}
				};
				turnFrame.setVisible(true);
			}
		});
		turnLabel.setBounds(701, 34, 96, 98);
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		turnLabel.setVerticalTextPosition(JLabel.BOTTOM);
		turnLabel.setHorizontalTextPosition(JLabel.CENTER);
		Icon turnIcon = new ImageIcon("img/turn.png");
		turnIcon = new ImageIcon(((ImageIcon) turnIcon).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		turnLabel.setIcon(turnIcon);
		turnLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		infoPanel.add(turnLabel);
		
		//搜索功能按钮
		JLabel searchLabel = new JLabel("\u641C\u7D22");
		searchLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//以下两个函数使鼠标移动出现边框效果
			@Override
			public void mouseExited(MouseEvent e) {
				searchLabel.setBorder(null);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				searchLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//进入搜索界面
				SearchFrame searchFrame = new SearchFrame(_this) {
					@Override
					public void dispose() {
						//指定文件夹搜索
						if(!_this.searchPath.equals(_this.currentFilePath)) {
							//维护back栈
							back.push(currentFilePath);
						}
						//跳转到指定界面
						turnToSearchPath(search.searchFileList(_this.searchPath, _this.keywords));
						super.dispose();
					}
				};
				
				searchFrame.setVisible(true);
			}
		});
		searchLabel.setBounds(802, 34, 96, 99);
		searchLabel.setHorizontalAlignment(JLabel.CENTER);
		searchLabel.setVerticalTextPosition(JLabel.BOTTOM);
		searchLabel.setHorizontalTextPosition(JLabel.CENTER);
		Icon searchIcon = new ImageIcon("img/search.png");
		searchIcon = new ImageIcon(((ImageIcon) searchIcon).getImage().getScaledInstance(47, 47, Image.SCALE_SMOOTH));
		searchLabel.setIcon(searchIcon);
		searchLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		infoPanel.add(searchLabel);
		
		//控制台功能按钮
		JLabel consoleLabel = new JLabel("\u63A7\u5236\u53F0");
		consoleLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//以下两个函数使鼠标移动出现边框效果
			@Override
			public void mouseExited(MouseEvent e) {
				consoleLabel.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				consoleLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO 控制台按钮功能
				try {
					Runtime.getRuntime().exec("cmd /k start cd "+currentFilePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		consoleLabel.setBounds(909, 35, 89, 98);
		consoleLabel.setHorizontalAlignment(JLabel.CENTER);
		consoleLabel.setHorizontalTextPosition(JLabel.CENTER);
		consoleLabel.setVerticalTextPosition(JLabel.BOTTOM);
		//设置对应图标并调整大小
		Icon consoleIcon = new ImageIcon("img/comm.png");
		consoleIcon = new ImageIcon(((ImageIcon) consoleIcon).getImage().getScaledInstance(47, 47, Image.SCALE_SMOOTH));
		consoleLabel.setIcon(consoleIcon);
		consoleLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		infoPanel.add(consoleLabel);
		
		nextPageLabel = new JLabel("\u524D\u8FDB");
		nextPageLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				nextPageLabel.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				nextPageLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO 前进按钮功能
				if(!front.isEmpty()) {
					String target = front.pop();
					back.push(currentFilePath);
					if(target.equals("")) {
						turnToDisk();
					}
					else {
						turnToPath(new File(target));
					}
				}
			}
		});
		nextPageLabel.setHorizontalAlignment(JLabel.CENTER);
		nextPageLabel.setHorizontalTextPosition(JLabel.CENTER);
		nextPageLabel.setVerticalTextPosition(JLabel.BOTTOM);
		Icon nextPageIcon = new ImageIcon("img/next.png");
		nextPageIcon = new ImageIcon(((ImageIcon) nextPageIcon).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		nextPageLabel.setIcon(nextPageIcon);
		nextPageLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		nextPageLabel.setBounds(124, 43, 96, 82);
		infoPanel.add(nextPageLabel);
		
		backPageLabel = new JLabel("\u540E\u9000");
		backPageLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				backPageLabel.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				backPageLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			//后退按钮功能
			public void mouseClicked(MouseEvent e) {
				if(!back.isEmpty()) {
					String target = back.pop();
					front.push(currentFilePath);
					if(target.equals("")) {
						//转到磁盘
						turnToDisk();
					}
					else {
						turnToPath(new File(target));
					}
				}
			}
		});
		backPageLabel.setHorizontalAlignment(JLabel.CENTER);
		backPageLabel.setHorizontalTextPosition(JLabel.CENTER);
		backPageLabel.setVerticalTextPosition(JLabel.BOTTOM);
		Icon backPageIcon = new ImageIcon("img/back.png");
		backPageIcon = new ImageIcon(((ImageIcon) backPageIcon).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		backPageLabel.setIcon(backPageIcon);
		backPageLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		backPageLabel.setBounds(24, 43, 96, 82);
		infoPanel.add(backPageLabel);
		
		pasteLabel = new JLabel("\u7C98\u8D34");
		pasteLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pasteLabel.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				pasteLabel.setBorder(new LineBorder(new Color(135, 206, 235),3));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(copyPath.equals("")) {
					JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">没有要粘贴的文件</font></html>");
				}
				else if(currentFilePath.equals("")) {
					JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">不能在根目录粘贴</font></html>");
				}
				else {
					//复制成功
					if(operate.copyFile(copyPath, currentFilePath+"\\"+new File(copyPath).getName())) {
						JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">复制成功!</font></html>");
						turnToPath(new File(currentFilePath));
					}
					else {
						//复制失败
						JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">复制无效.请检查文件是否可访问</font></html>");
					}
				}
				
			}
		});
		pasteLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		pasteLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		pasteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon pasteIcon = new ImageIcon("img/paste.png");
		pasteIcon = new ImageIcon(pasteIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		pasteLabel.setIcon(pasteIcon);
		pasteLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		pasteLabel.setBounds(479, 34, 96, 100);
		infoPanel.add(pasteLabel);
		
		//******************************快捷访问栏*************************************
		
		//快速访问栏
		fastVisitPane = new JPanel();
		fastVisitPane.setLayout(null);
		fastVisitPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		fastVisitPane.setBackground(Color.WHITE);
		fastVisitPane.setBounds(0, 429, 295, 395);
		contentPane.add(fastVisitPane);
		
		//快速访问标签
		fastVisitLabel = new JLabel("\u5FEB\u901F\u8BBF\u95EE");
		fastVisitLabel.setOpaque(true);
		fastVisitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fastVisitLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
		fastVisitLabel.setBackground(new Color(135, 206, 235));
		fastVisitLabel.setBounds(0, 0, 295, 34);
		fastVisitPane.add(fastVisitLabel);
		fastVisitScrollPane.setBounds(0, 33, 295, 362);
		fastVisitPane.add(fastVisitScrollPane);
		
		//定义快速访问列表
		fastVisitList = new JList();
		//列表model
		fastListModel = new DefaultListModel();
		//TODO:初始化fastList
		for(File file : chart.getAllVisitPath()) {
			fastListModel.addElement(file);
		}
		//加载Model
		fastVisitList.setModel(fastListModel);
		//设置renderer
		fastVisitList.setCellRenderer(new FastVisitCellRenderer());
		//列表样式
		fastVisitList.setBorder(new LineBorder(new Color(0,0,0)));
		fastVisitList.setFont(new Font("微软雅黑",Font.PLAIN,18));
		//列表初始值-1
		fastVisitList.setSelectedIndex(-1);
		
		//鼠标移动高亮
		fastVisitList.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				//获取移动位置
				Point current = e.getPoint();
				int currentIndex = fastVisitList.locationToIndex(current);
				//设置对应项被选中
				fastVisitList.setSelectedIndex(currentIndex);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		//快捷访问右击菜单
		JPopupMenu fastVisitMenu = new JPopupMenu();
//		fastVisitMenu.setBackground(new Color(0,0,0));
		JMenuItem openFastItem = new JMenuItem("打开");
		openFastItem.setBackground(new Color(255,255,255));
		openFastItem.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		openFastItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//进入指定目录或者打开文件
				//获取对应文件
				File current = (File)fastVisitList.getSelectedValue();
				if(current.exists()) {
					if(current.isDirectory()) {
						//维护栈
						back.push(currentFilePath);
						//跳转到指定目录
						turnToPath(current);
					}
					else {
						//打开文件
						try {
							Desktop.getDesktop().open(current);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">打开文件失败!</font></html>");
						}
					}
				}
				else {
					//目录已经不存在
					JOptionPane.showMessageDialog(null, "");
				}
			}
		});
		//移除快速访问按钮
		JMenuItem deleteItem = new JMenuItem("移除快速访问");
		deleteItem.setFont(new Font("微软雅黑",Font.PLAIN,20));
		deleteItem.setBackground(new Color(255,255,255));
		//删除按钮动作
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取对应项
				File toDelete = (File) fastVisitList.getSelectedValue();
				//更新列表
				fastListModel.removeElement(toDelete);
				fastVisitList.setModel(fastListModel);
				fastVisitList.setCellRenderer(new FastVisitCellRenderer());
				//在数据库中删除对应项
				chart.deleteVisitPath(toDelete.getPath());
			}
		});
		
		//添加菜单选项
		fastVisitMenu.add(openFastItem);
		fastVisitMenu.add(deleteItem);
		
		//鼠标动作
		fastVisitList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//左键
				if(e.getButton() == MouseEvent.BUTTON1) {
					//进入指定目录或者打开文件
					//获取对应文件
					File current = (File)fastVisitList.getSelectedValue();
					if(current.exists()) {
						if(current.isDirectory()) {
							//维护栈
							back.push(currentFilePath);
							//跳转到指定目录
							turnToPath(current);
						}
						else {
							//打开文件
							try {
								Desktop.getDesktop().open(current);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else {
						//目录已经不存在
						JOptionPane.showMessageDialog(null, "");
					}
				}
				//右键
				if(e.getButton() == MouseEvent.BUTTON3) {
					//显示右击菜单
					fastVisitMenu.show(fastVisitList, e.getX(), e.getY());
				}
			}
		});
		
		fastVisitScrollPane.setViewportView(fastVisitList);
		
		//文件夹索引栏
		folderIndexPane = new JPanel();
		folderIndexPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		folderIndexPane.setLayout(null);
		folderIndexPane.setBackground(Color.WHITE);
		folderIndexPane.setBounds(0, 0, 295, 429);
		contentPane.add(folderIndexPane);
		
		//索引栏标签
		indexLabel = new JLabel("\u6587\u4EF6\u5939\u7D22\u5F15");
		indexLabel.setOpaque(true);
		indexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indexLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 24));
		indexLabel.setBackground(new Color(135, 206, 235));
		indexLabel.setBounds(0, 0, 295, 34);
		folderIndexPane.add(indexLabel);
		
		//文件树滚轮区域
		fileTreeScrollPane = new JScrollPane();
		fileTreeScrollPane.setBounds(0, 31, 295, 398);
		folderIndexPane.add(fileTreeScrollPane);
		
		
		//******************************文件树*************************************
		
		//树的根结点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的电脑");

		//列出初始结点(磁盘)
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(roots[i].getPath());

            root.add(node);
        }
        
        //构建树
        fileTree = new JTree(root);

        //树结点动作
        MouseListener ml = new MouseAdapter() {
        	//点击对应的文件夹
            public void mousePressed(MouseEvent e) {
                try {
                	//获取位置
                    TreePath tp = fileTree.getPathForLocation(e.getX(), e.getY());
                    if (tp != null) {
                        treePath = tp;
                        int size = treePath.getPath().length;
                        //计算对应的路径
                        StringBuffer pathname = new StringBuffer();
                        if (size > 0) {
                            for (int i = 1; i < size; i++) {
                                if (i > 2) {
                                    pathname.append("\\");
                                }
                                pathname.append(treePath.getPath()[i].toString());
                            }
                            File file = new File(pathname.toString());
                            File[] list = file.listFiles();
                            if(list==null){
                            	//回到主目录
                            	turnToDisk();
                            }
                            else {
                            	//跳转到指定目录
                            	for (int i = 0; i < list.length; i++) {
                            		if(list[i].isDirectory()) {
                            			String[] strings = list[i].getPath().split("\\\\");
                            			DefaultMutableTreeNode node = new DefaultMutableTreeNode(strings[strings.length - 1]);
                            			((DefaultMutableTreeNode) tp.getLastPathComponent()).add(node);
                            		}
                            	}
                            	turnToPath(file);
                            }
                            
                        }
                        
                    }
                } catch (Exception e1) {
                    System.out.println("error!");
                }
            }
        };
        fileTree.addMouseListener(ml);
        fileTree.setShowsRootHandles(true);
        fileTree.setRootVisible(true);
        fileTree.setFont(new Font("微软雅黑",Font.PLAIN,20));
        fileTree.setCellRenderer(new NodeCellRenderer());
		//添加树到滚轮区
		fileTreeScrollPane.setViewportView(fileTree);
		
      //******************************文件列表*************************************
        
		//文件列表区域
		fileChartScrollPane = new JScrollPane();
		fileChartScrollPane.setBounds(295, 0, 1036, 669);
		contentPane.add(fileChartScrollPane);
		
		
		//新建列表
		fileChart = new JList();
		//列表Model
		defaultListModel = new DefaultListModel();
		//初始化Model
		for(File disk : File.listRoots()) {
			defaultListModel.addElement(disk);
		}
		//加载Model
		fileChart.setModel(defaultListModel);
		//设置显示renderer
		fileChart.setCellRenderer(new FileListCellRenderer());
		//列表样式
		fileChart.setBorder(new LineBorder(new Color(0, 0, 0)));
		fileChart.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));

		//列表初始index-1
		fileChart.setSelectedIndex(-1);
		//设置鼠标移动高亮
		fileChart.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				//获取移动位置
				Point current = e.getPoint();
				int currentIndex = fileChart.locationToIndex(current);
				//设置对应项被选中
				fileChart.setSelectedIndex(currentIndex);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		//点击对应文件夹来跳到对应目录
		fileChart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//左键
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					//维护栈
					back.push(currentFilePath);
					
					File current = (File)fileChart.getSelectedValue();
					if(current.isDirectory())
						turnToPath(current);
					else {
						//如果是文件则打开文件
						try {
							Desktop.getDesktop().open(current);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				//右键
				if(e.getButton() == MouseEvent.BUTTON3) {
					File current = (File)fileChart.getSelectedValue();
					if(current.getParent()!=null)
						folderMenu.show(fileChart, e.getX(), e.getY());
					
				}
			}
		});
		
		
		fileChartScrollPane.setViewportView(fileChart);
		
		//***********************************右击菜单***********************************
		
		//文件夹右击菜单
		folderMenu = new JPopupMenu();
		//文件夹右击菜单项
		//打开按钮与删除按钮
		JMenuItem openItem, mDelete;
		folderMenu = new JPopupMenu();
		openItem = new JMenuItem("打开");
		openItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(openItem);
		mDelete = new JMenuItem("删除");
		mDelete.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(mDelete);
		
		//复制按钮
		JMenuItem copyItem = new JMenuItem("复制");
		copyItem.setFont(new Font("Microsoft YaHei UI",Font.PLAIN,20));
		folderMenu.add(copyItem);
		
		//属性按钮
		JMenuItem propertyItem = new JMenuItem("属性");
		propertyItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(propertyItem);
		
		//添加到快捷访问
		JMenuItem addFastItem = new JMenuItem("添加到快捷访问");
		addFastItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(addFastItem);
		
		//打开按钮动作
		openItem.addActionListener(e->{
			File current = (File)fileChart.getSelectedValue();
			//目录
			if(current.isDirectory())
				turnToPath(current);
			else {
				try {
					Desktop.getDesktop().open(current);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//删除按钮动作
		mDelete.addActionListener(e->{
			File current = (File) fileChart.getSelectedValue();
			operate.deleteFile(current.getPath());
			turnToPath(new File(currentFilePath));
		});
		
		//复制按钮动作
		copyItem.addActionListener(e->{
			File current = (File) fileChart.getSelectedValue();
			//设置复制路径
			copyPath = current.getPath();
		});
		
		//属性按钮动作
		propertyItem.addActionListener(e->{
			File current = (File)fileChart.getSelectedValue();
			PropertyFrame propertyFrame = new PropertyFrame(current);
			propertyFrame.setVisible(true);
			//另行启动一个线程开始计算
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						propertyFrame.fileNumber = 0;
						propertyFrame.directoryNum = 0;
						long currentSize = propertyFrame.getDictorySize(current.getPath());
						DecimalFormat df = new DecimalFormat("#.00");
						//更改文件大小标签
						if(currentSize >= 1073741824) {
							propertyFrame.size.setText(df.format(currentSize*1.0/1073741824)+"GB");
						}
						else if(currentSize >= 1048576) {
							propertyFrame.size.setText(df.format(currentSize*1.0/1048576)+"MB");
						}
						else if(currentSize >= 1024) {
							propertyFrame.size.setText(df.format(currentSize*1.0/1024)+"KB");
						}
						else {
							propertyFrame.size.setText(String.valueOf(currentSize*1.0)+"B");
						}
						//更改文件个数标签
						propertyFrame.number.setText(String.valueOf(propertyFrame.fileNumber));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		
		//添加到快捷访问按钮动作
		addFastItem.addActionListener(e->{
			File current = (File)fileChart.getSelectedValue();
			try {
				//更新快捷访问列表
				if(chart.hasHadFile(current.getPath())) {
					JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">当前文件已经在快捷访问了</font></html>");
				}
				else {
					chart.insertVisitPath(current.getPath());
					fastListModel.addElement(current);
					fastVisitList.setModel(fastListModel);
					fastVisitList.setCellRenderer(new FastVisitCellRenderer());
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
	//******************************逻辑函数*************************************
	
	//转到对应目录并刷新界面
	public void turnToPath(File current) {
		currentFilePath = current.getPath();
		defaultListModel.clear();
		for(File file : chart.getFileList(currentFilePath)) {
			defaultListModel.addElement(file);
		}
		fileChart.setModel(defaultListModel);
		fileChart.setCellRenderer(new FileListCellRenderer());
	}
	
	//转到磁盘并刷新界面
	public void turnToDisk() {
		defaultListModel.clear();
		for(File disk : File.listRoots()) {
			defaultListModel.addElement(disk);
		}
		fileChart.setModel(defaultListModel);
		fileChart.setCellRenderer(new FileListCellRenderer());
	}
	
	//转到搜索界面
	public void turnToSearchPath(List<File> files) {
		//路径不存在或路径非法
		if(files == null) {
			JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">路径不存在或路径非法</font></html>");
		}
		else {
			if(files.size()==0) {
				JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">不能在文件里搜索</font></html>");
			}
			else {
				//令搜索路径父目录为当前目录
				currentFilePath = files.get(0).getParent();
				//更新列表
				defaultListModel.clear();
				for(File file : files) {
					defaultListModel.addElement(file);
				}
				fileChart.setModel(defaultListModel);
				fileChart.setCellRenderer(new FileListCellRenderer());
			}
		}
	}
}
