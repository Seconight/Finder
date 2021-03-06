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

//??????
public class MainFrame extends JFrame {

	//??????
	private JPanel contentPane;
	
	//????????
	JTree fileTree;		//??????
	TreePath treePath;	//??????
	JPanel infoPanel;	//??????
	JLabel fileNumber;	//????????????
	JLabel fileSize;	//????????????
	JLabel size;		//????????
	JLabel number;		//????????
	JComboBox comboBox;	//??????????????
	JLabel sortLabel;	//????????????
	JList fileChart;	//????????
	DefaultListModel defaultListModel;	//????????Model
	JPanel fastVisitPane;				//????????????
	JLabel fastVisitLabel;				//????????????
	DefaultListModel fastListModel;		//????????????Model
	JPanel folderIndexPane;				//??????????????
	JLabel indexLabel;					//????????
	JScrollPane fileTreeScrollPane;		//??????????????
	JScrollPane fileChartScrollPane;	//????????????????
	JPopupMenu folderMenu;				//??????????????
	JPopupMenu fileMenu;				//????????????
	
	//????????
	DiskInfo diskInfo = new DiskInfoInstance();				//????????
	FileChart chart = new FileChartInstance();				//????????
	FileProperty property = new FilePropertyInstance();		//????????
	SearchFile search = new SearchFileInstance();			//????????
	FileOperate operate = new FileOperateInstance();		//????????
	
	//????itself
	MainFrame _this = this;
	
	//????????????
	String currentFilePath = new String();
	
	//????????
	String copyPath = new String("");
	
	//??????????,????????????????????
	//????????????????????,??????(????)??????,??????????????top????
	Deque<String> front = new ArrayDeque<String>();
	//??????(????)??????,????????????,??????top????
	//for example
	// A/B/C front() back(A,A/B)
	// A/B front(A/B/C) back(A)
	// D/F front() back(A,A/B)
	Deque<String> back = new ArrayDeque<String>();
	
	//????????????????
	String keywords = new String();		//??????????
	String searchPath = new String();	//????????
	
	//??????????????
	private JLabel nextPageLabel;
	private JLabel backPageLabel;
	//????????????
	private final JScrollPane fastVisitScrollPane = new JScrollPane();
	//????????????
	private JList fastVisitList;
	private JLabel pasteLabel;
	//????????????????
//	FileDAO  = new FileDAO();

	/**
	 * Launch the application.????????
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
		
		//????????
		setTitle("Finder");
		//????????
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/finder.png"));
		//????????????
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//????????
		setBounds(100, 100, 1353, 880);
		//??????????????
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		//??????
		infoPanel = new JPanel();
		infoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(null);
		infoPanel.setBounds(295, 668, 1036, 156);
		contentPane.add(infoPanel);
		
//		//????????????
//		fileNumber = new JLabel("????????:");
//		fileNumber.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
//		fileNumber.setBounds(26, 96, 114, 49);
//		infoPanel.add(fileNumber);
		
//		//????????????
//		fileSize = new JLabel("????????:");
//		fileSize.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
//		fileSize.setBounds(15, 29, 114, 40);
//		infoPanel.add(fileSize);
		
//		//????????????
//		size = new JLabel("root");
//		size.setFont(new Font("Consolas", Font.PLAIN, 24));
//		size.setBounds(142, 34, 139, 37);
//		infoPanel.add(size);
		
//		//????????????
//		number = new JLabel("2");
//		number.setFont(new Font("Consolas", Font.PLAIN, 24));
//		number.setBounds(155, 105, 101, 37);
//		infoPanel.add(number);
		
		//??????????
		String[] sortMethods = {"??????????????","??????????????",
								"??????????????","??????????????",
								"??????????????","??????????????"};
		comboBox = new JComboBox(sortMethods);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
		comboBox.setBounds(258, 87, 194, 47);
		//Lamda????????comboBox????????
		comboBox.addItemListener(e -> {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				String sortType = (String)comboBox.getSelectedItem();
				File[] sortedFiles;
				if(sortType.equals("??????????????")) {
					sortedFiles = chart.nameSortedList(currentFilePath, true);
				}
				else if(sortType.equals("??????????????")) {
					sortedFiles = chart.nameSortedList(currentFilePath, false);
				}
				else if(sortType.equals("??????????????")) {
					sortedFiles = chart.timeSortedList(currentFilePath, true);
				}
				else if(sortType.equals("??????????????")) {
					sortedFiles = chart.timeSortedList(currentFilePath, false);
				}
				else if(sortType.equals("??????????????")) {
					sortedFiles = chart.typeSortedList(currentFilePath, true);
				}
				else  {
					sortedFiles = chart.typeSortedList(currentFilePath, false);
				}
				//????????????
				defaultListModel.clear();
				for(File file : sortedFiles) {
					defaultListModel.addElement(file);
				}
				fileChart.setModel(defaultListModel);
				fileChart.setCellRenderer(new FileListCellRenderer());
			}
		});
		infoPanel.add(comboBox);
		
		//????????
		sortLabel = new JLabel("\u6392\u5E8F\u65B9\u5F0F");
		sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sortLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
		sortLabel.setBounds(286, 34, 114, 40);
		infoPanel.add(sortLabel);
		
		//??????????????????
		JLabel newFolderLabel = new JLabel("\u65B0\u5EFA\u6587\u4EF6\u5939");
		newFolderLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			
			//??????????????????????????????????
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
				//??????????????????????????????
				NewFolderFrame newFolderFrame = new NewFolderFrame(currentFilePath) {
					@Override
					//????dispose????
					public void dispose() {
						//????????
						if(!currentFilePath.equals("")) {
							turnToPath(new File(currentFilePath));
							super.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">??????????????????????!</font></html>");
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
		
		//????????????
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
			
			//??????????????????????????????????
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
				//??????
				back.push(currentFilePath);
				
				// TODO ????????????
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
									JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">??????????????!</font></html>");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">??????????!</font></html>");
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
		
		//????????????
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
			
			//??????????????????????????????????
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
				//????????????
				SearchFrame searchFrame = new SearchFrame(_this) {
					@Override
					public void dispose() {
						//??????????????
						if(!_this.searchPath.equals(_this.currentFilePath)) {
							//????back??
							back.push(currentFilePath);
						}
						//??????????????
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
		
		//??????????????
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
			
			//??????????????????????????????????
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
				//TODO ??????????????
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
		//??????????????????????
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
				//TODO ????????????
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
			//????????????
			public void mouseClicked(MouseEvent e) {
				if(!back.isEmpty()) {
					String target = back.pop();
					front.push(currentFilePath);
					if(target.equals("")) {
						//????????
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
					JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????????????</font></html>");
				}
				else if(currentFilePath.equals("")) {
					JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????????????</font></html>");
				}
				else {
					//????????
					if(operate.copyFile(copyPath, currentFilePath+"\\"+new File(copyPath).getName())) {
						JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????!</font></html>");
						turnToPath(new File(currentFilePath));
					}
					else {
						//????????
						JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????.????????????????????</font></html>");
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
		
		//******************************??????????*************************************
		
		//??????????
		fastVisitPane = new JPanel();
		fastVisitPane.setLayout(null);
		fastVisitPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		fastVisitPane.setBackground(Color.WHITE);
		fastVisitPane.setBounds(0, 429, 295, 395);
		contentPane.add(fastVisitPane);
		
		//????????????
		fastVisitLabel = new JLabel("\u5FEB\u901F\u8BBF\u95EE");
		fastVisitLabel.setOpaque(true);
		fastVisitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fastVisitLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
		fastVisitLabel.setBackground(new Color(135, 206, 235));
		fastVisitLabel.setBounds(0, 0, 295, 34);
		fastVisitPane.add(fastVisitLabel);
		fastVisitScrollPane.setBounds(0, 33, 295, 362);
		fastVisitPane.add(fastVisitScrollPane);
		
		//????????????????
		fastVisitList = new JList();
		//????model
		fastListModel = new DefaultListModel();
		//TODO:??????fastList
		for(File file : chart.getAllVisitPath()) {
			fastListModel.addElement(file);
		}
		//????Model
		fastVisitList.setModel(fastListModel);
		//????renderer
		fastVisitList.setCellRenderer(new FastVisitCellRenderer());
		//????????
		fastVisitList.setBorder(new LineBorder(new Color(0,0,0)));
		fastVisitList.setFont(new Font("????????",Font.PLAIN,18));
		//??????????-1
		fastVisitList.setSelectedIndex(-1);
		
		//????????????
		fastVisitList.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				//????????????
				Point current = e.getPoint();
				int currentIndex = fastVisitList.locationToIndex(current);
				//????????????????
				fastVisitList.setSelectedIndex(currentIndex);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		//????????????????
		JPopupMenu fastVisitMenu = new JPopupMenu();
//		fastVisitMenu.setBackground(new Color(0,0,0));
		JMenuItem openFastItem = new JMenuItem("????");
		openFastItem.setBackground(new Color(255,255,255));
		openFastItem.setFont(new Font("????????", Font.PLAIN, 20));
		openFastItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//????????????????????????
				//????????????
				File current = (File)fastVisitList.getSelectedValue();
				if(current.exists()) {
					if(current.isDirectory()) {
						//??????
						back.push(currentFilePath);
						//??????????????
						turnToPath(current);
					}
					else {
						//????????
						try {
							Desktop.getDesktop().open(current);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????????!</font></html>");
						}
					}
				}
				else {
					//??????????????
					JOptionPane.showMessageDialog(null, "");
				}
			}
		});
		//????????????????
		JMenuItem deleteItem = new JMenuItem("????????????");
		deleteItem.setFont(new Font("????????",Font.PLAIN,20));
		deleteItem.setBackground(new Color(255,255,255));
		//????????????
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//??????????
				File toDelete = (File) fastVisitList.getSelectedValue();
				//????????
				fastListModel.removeElement(toDelete);
				fastVisitList.setModel(fastListModel);
				fastVisitList.setCellRenderer(new FastVisitCellRenderer());
				//????????????????????
				chart.deleteVisitPath(toDelete.getPath());
			}
		});
		
		//????????????
		fastVisitMenu.add(openFastItem);
		fastVisitMenu.add(deleteItem);
		
		//????????
		fastVisitList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//????
				if(e.getButton() == MouseEvent.BUTTON1) {
					//????????????????????????
					//????????????
					File current = (File)fastVisitList.getSelectedValue();
					if(current.exists()) {
						if(current.isDirectory()) {
							//??????
							back.push(currentFilePath);
							//??????????????
							turnToPath(current);
						}
						else {
							//????????
							try {
								Desktop.getDesktop().open(current);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else {
						//??????????????
						JOptionPane.showMessageDialog(null, "");
					}
				}
				//????
				if(e.getButton() == MouseEvent.BUTTON3) {
					//????????????
					fastVisitMenu.show(fastVisitList, e.getX(), e.getY());
				}
			}
		});
		
		fastVisitScrollPane.setViewportView(fastVisitList);
		
		//????????????
		folderIndexPane = new JPanel();
		folderIndexPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		folderIndexPane.setLayout(null);
		folderIndexPane.setBackground(Color.WHITE);
		folderIndexPane.setBounds(0, 0, 295, 429);
		contentPane.add(folderIndexPane);
		
		//??????????
		indexLabel = new JLabel("\u6587\u4EF6\u5939\u7D22\u5F15");
		indexLabel.setOpaque(true);
		indexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indexLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 24));
		indexLabel.setBackground(new Color(135, 206, 235));
		indexLabel.setBounds(0, 0, 295, 34);
		folderIndexPane.add(indexLabel);
		
		//??????????????
		fileTreeScrollPane = new JScrollPane();
		fileTreeScrollPane.setBounds(0, 31, 295, 398);
		folderIndexPane.add(fileTreeScrollPane);
		
		
		//******************************??????*************************************
		
		//??????????
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("????????");

		//????????????(????)
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(roots[i].getPath());

            root.add(node);
        }
        
        //??????
        fileTree = new JTree(root);

        //??????????
        MouseListener ml = new MouseAdapter() {
        	//????????????????
            public void mousePressed(MouseEvent e) {
                try {
                	//????????
                    TreePath tp = fileTree.getPathForLocation(e.getX(), e.getY());
                    if (tp != null) {
                        treePath = tp;
                        int size = treePath.getPath().length;
                        //??????????????
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
                            	//??????????
                            	turnToDisk();
                            }
                            else {
                            	//??????????????
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
        fileTree.setFont(new Font("????????",Font.PLAIN,20));
        fileTree.setCellRenderer(new NodeCellRenderer());
		//??????????????
		fileTreeScrollPane.setViewportView(fileTree);
		
      //******************************????????*************************************
        
		//????????????
		fileChartScrollPane = new JScrollPane();
		fileChartScrollPane.setBounds(295, 0, 1036, 669);
		contentPane.add(fileChartScrollPane);
		
		
		//????????
		fileChart = new JList();
		//????Model
		defaultListModel = new DefaultListModel();
		//??????Model
		for(File disk : File.listRoots()) {
			defaultListModel.addElement(disk);
		}
		//????Model
		fileChart.setModel(defaultListModel);
		//????????renderer
		fileChart.setCellRenderer(new FileListCellRenderer());
		//????????
		fileChart.setBorder(new LineBorder(new Color(0, 0, 0)));
		fileChart.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));

		//????????index-1
		fileChart.setSelectedIndex(-1);
		//????????????????
		fileChart.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				//????????????
				Point current = e.getPoint();
				int currentIndex = fileChart.locationToIndex(current);
				//????????????????
				fileChart.setSelectedIndex(currentIndex);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
		//????????????????????????????
		fileChart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//????
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					//??????
					back.push(currentFilePath);
					
					File current = (File)fileChart.getSelectedValue();
					if(current.isDirectory())
						turnToPath(current);
					else {
						//????????????????????
						try {
							Desktop.getDesktop().open(current);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				//????
				if(e.getButton() == MouseEvent.BUTTON3) {
					File current = (File)fileChart.getSelectedValue();
					if(current.getParent()!=null)
						folderMenu.show(fileChart, e.getX(), e.getY());
					
				}
			}
		});
		
		
		fileChartScrollPane.setViewportView(fileChart);
		
		//***********************************????????***********************************
		
		//??????????????
		folderMenu = new JPopupMenu();
		//????????????????
		//??????????????????
		JMenuItem openItem, mDelete;
		folderMenu = new JPopupMenu();
		openItem = new JMenuItem("????");
		openItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(openItem);
		mDelete = new JMenuItem("????");
		mDelete.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(mDelete);
		
		//????????
		JMenuItem copyItem = new JMenuItem("????");
		copyItem.setFont(new Font("Microsoft YaHei UI",Font.PLAIN,20));
		folderMenu.add(copyItem);
		
		//????????
		JMenuItem propertyItem = new JMenuItem("????");
		propertyItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(propertyItem);
		
		//??????????????
		JMenuItem addFastItem = new JMenuItem("??????????????");
		addFastItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		folderMenu.add(addFastItem);
		
		//????????????
		openItem.addActionListener(e->{
			File current = (File)fileChart.getSelectedValue();
			//????
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
		
		//????????????
		mDelete.addActionListener(e->{
			File current = (File) fileChart.getSelectedValue();
			operate.deleteFile(current.getPath());
			turnToPath(new File(currentFilePath));
		});
		
		//????????????
		copyItem.addActionListener(e->{
			File current = (File) fileChart.getSelectedValue();
			//????????????
			copyPath = current.getPath();
		});
		
		//????????????
		propertyItem.addActionListener(e->{
			File current = (File)fileChart.getSelectedValue();
			PropertyFrame propertyFrame = new PropertyFrame(current);
			propertyFrame.setVisible(true);
			//????????????????????????
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						propertyFrame.fileNumber = 0;
						propertyFrame.directoryNum = 0;
						long currentSize = propertyFrame.getDictorySize(current.getPath());
						DecimalFormat df = new DecimalFormat("#.00");
						//????????????????
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
						//????????????????
						propertyFrame.number.setText(String.valueOf(propertyFrame.fileNumber));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		
		//??????????????????????
		addFastItem.addActionListener(e->{
			File current = (File)fileChart.getSelectedValue();
			try {
				//????????????????
				if(chart.hasHadFile(current.getPath())) {
					JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????????????????????</font></html>");
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
	
	//******************************????????*************************************
	
	//??????????????????????
	public void turnToPath(File current) {
		currentFilePath = current.getPath();
		defaultListModel.clear();
		for(File file : chart.getFileList(currentFilePath)) {
			defaultListModel.addElement(file);
		}
		fileChart.setModel(defaultListModel);
		fileChart.setCellRenderer(new FileListCellRenderer());
	}
	
	//??????????????????
	public void turnToDisk() {
		defaultListModel.clear();
		for(File disk : File.listRoots()) {
			defaultListModel.addElement(disk);
		}
		fileChart.setModel(defaultListModel);
		fileChart.setCellRenderer(new FileListCellRenderer());
	}
	
	//????????????
	public void turnToSearchPath(List<File> files) {
		//????????????????????
		if(files == null) {
			JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????????????????</font></html>");
		}
		else {
			if(files.size()==0) {
				JOptionPane.showMessageDialog(null, "<html><font face=\"????????\" size=\"5\">????????????????</font></html>");
			}
			else {
				//??????????????????????????
				currentFilePath = files.get(0).getParent();
				//????????
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
