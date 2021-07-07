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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;


//�½��ļ��д���
public class NewFolderFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFolderFrame frame = new NewFolderFrame("aaaa");
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
	public NewFolderFrame(String path) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/finder.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 443);
		setTitle("�½��ļ���");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel currentFileLabel = new JLabel("\u5F53\u524D\u6587\u4EF6\u5939:");
		currentFileLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		currentFileLabel.setBounds(60, 29, 120, 43);
		contentPane.add(currentFileLabel);
		
		JLabel currentFile = new JLabel("");
		currentFile.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		currentFile.setBounds(217, 31, 495, 43);
		currentFile.setText(path);
		contentPane.add(currentFile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 106, 484, 195);
		contentPane.add(scrollPane);
		
		//��ʼ������ͷ
		String[][] initTable = {{"1",""}};
		String[] title = {"���","�ļ�������"};
		//����tableModel,���е�һ�в��ɱ༭
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0) return false;
				return true;
			}
		};
		defaultTableModel.setDataVector(initTable, title);
		//table����model
		table = new JTable(defaultTableModel);
		table.setFont(new Font("Microsoft YaHei UI",Font.PLAIN,18));
		table.setRowHeight(30);
		//������ݾ�����ʾ
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		//���ñ༭״̬�����С
		JTextField textField = new JTextField();
		textField.setFont(new Font("Microsoft YaHei UI",Font.PLAIN,18));
		table.setCellEditor(new DefaultCellEditor(textField));
		//���ñ�ͷ�ĸ�ʽ
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setFont(new Font("Microsoft YaHei UI",Font.PLAIN,20));
		tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(),50));
		tableHeader.setBackground(new Color(255,255,255));
		scrollPane.setViewportView(table);
		
		
		//�����ļ����ť
		JButton addFolder = new JButton("\u65B0\u589E\u6587\u4EF6\u5939");
		addFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] addNew = new String[2];
				addNew[0] = String.valueOf(defaultTableModel.getRowCount()+1);
				addNew[1] = "";
				defaultTableModel.addRow(addNew);
			}
		});
		addFolder.setFont(new Font("�������_CNKI", Font.PLAIN, 20));
		addFolder.setBounds(579, 131, 145, 43);
		contentPane.add(addFolder);
		
		JButton deleteFolder = new JButton("\u5220\u9664\u6587\u4EF6\u5939");
		deleteFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deleteIndex = table.getSelectedRow();
				defaultTableModel.removeRow(deleteIndex);
				//����
				for(int i=1;i<=table.getRowCount();i++) {
					defaultTableModel.setValueAt(String.valueOf(i), i-1, 0);
				}
			}
		});
		deleteFolder.setFont(new Font("�������_CNKI", Font.PLAIN, 20));
		deleteFolder.setBounds(579, 229, 145, 43);
		contentPane.add(deleteFolder);
		
		//�½��ļ��а�ť
		JButton excuteButton = new JButton("\u65B0\u5EFA");
		excuteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�Ǹ�Ŀ¼
				if(!path.equals("")) {
					boolean isSuccess = true;
					for(int i=0;i<table.getRowCount();i++) {
						//��ȡ�ļ���
						String folderName = (String)table.getValueAt(i, 1);
						if(!folderName.equals("")) {
							File newFolder = new File(path+"//"+folderName);
							if(newFolder.exists()) {
								isSuccess = false;
								JOptionPane.showMessageDialog(null, "�ļ���"+newFolder.getName()+"�Ѵ���");
							}
							else {
								newFolder.mkdir();
							}
						}
					}
					if(isSuccess) {
						JOptionPane.showMessageDialog(null, "<html><font face=\"Microsoft YaHei UI\" size=\"8\">�ļ��н����ɹ�!</font></html>");
						dispose();
					}
				}
				else {
					dispose();
				}
			}
		});
		excuteButton.setFont(new Font("��Բ", Font.PLAIN, 20));
		excuteButton.setBounds(253, 325, 80, 41);
		contentPane.add(excuteButton);
		
		//ȡ����ť
		JButton CancelButton = new JButton("\u53D6\u6D88");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		CancelButton.setFont(new Font("��Բ", Font.PLAIN, 20));
		CancelButton.setBounds(463, 325, 80, 41);
		contentPane.add(CancelButton);
	}
}
