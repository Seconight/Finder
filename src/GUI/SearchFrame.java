package GUI;

/**
 * 
 * @author Jiaxing Tian
 * @school WuHan University of Technology
 * @email JiaxingTian@whut.edu.cn or 614300076@qq.com
 * @Date 2021/6/19
 * 
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;

//ËÑË÷ÎÄ¼þ´°¿Ú
public class SearchFrame extends JFrame {

	private JPanel contentPane;
	public JTextField searchPath;
	public JTextField keyWords;
	public File[] searchResult;
	public boolean searchInCurrentPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					SearchFrame frame = new SearchFrame(new M);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFrame(MainFrame mainFrame) {
		setTitle("\u641C\u7D22\u6587\u4EF6");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/finder.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		searchPath = new JTextField();
		searchPath.setFont(new Font("ºÚÌå", Font.PLAIN, 20));
		searchPath.setBounds(76, 274, 412, 42);
		contentPane.add(searchPath);
		searchPath.setColumns(10);
		
		JRadioButton currentPathSearch = new JRadioButton("\u5F53\u524D\u8DEF\u5F84\u641C\u7D22");
		currentPathSearch.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		currentPathSearch.setBounds(102, 158, 177, 29);
		currentPathSearch.setContentAreaFilled(false);
		currentPathSearch.setFocusPainted(false);
		
		
		JRadioButton targetPathSearch = new JRadioButton("\u5728\u6307\u5B9A\u8DEF\u5F84\u641C\u7D22");
		targetPathSearch.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		targetPathSearch.setBounds(102, 215, 177, 29);
		targetPathSearch.setContentAreaFilled(false);
		targetPathSearch.setFocusPainted(false);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(currentPathSearch);
		buttonGroup.add(targetPathSearch);
		
		currentPathSearch.setSelected(true);
		searchPath.setEditable(false);
		//ÎªbuttonÔö¼Ó¼àÌýÆ÷
		currentPathSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPath.setText("");
				searchPath.setEditable(false);
			}
		});
		targetPathSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchPath.setEditable(true);
			}
		});
		
		contentPane.add(currentPathSearch);
		contentPane.add(targetPathSearch);
		
		JButton searchButton = new JButton("\u641C\u7D22");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ÔÚµ±Ç°ÎÄ¼þ¼ÐËÑË÷
				if(currentPathSearch.isSelected()) {
					mainFrame.searchPath = mainFrame.currentFilePath;
				}
				//ÔÚÖ¸¶¨ÎÄ¼þ¼ÐËÑË÷
				else {
					mainFrame.searchPath = searchPath.getText();
				}
				//ÉèÖÃËÑË÷¹Ø¼ü×Ö
				mainFrame.keywords = keyWords.getText();
				dispose();
			}
		});
		searchButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 21));
		searchButton.setBounds(144, 342, 123, 42);
		contentPane.add(searchButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 21));
		cancelButton.setBounds(326, 342, 123, 42);
		contentPane.add(cancelButton);
		
		JLabel fileKeyWordLabel = new JLabel("\u8BF7\u8F93\u5165\u641C\u7D22\u5173\u952E\u5B57:");
		fileKeyWordLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		fileKeyWordLabel.setBounds(61, 59, 177, 42);
		contentPane.add(fileKeyWordLabel);
		
		keyWords = new JTextField();
		keyWords.setFont(new Font("ºÚÌå", Font.PLAIN, 20));
		keyWords.setColumns(10);
		keyWords.setBounds(240, 60, 248, 42);
		contentPane.add(keyWords);
		
		
	}
}
