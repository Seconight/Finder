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
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

//转到窗口
public class TurnFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TurnFrame frame = new TurnFrame(new MainFrame());
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
	public TurnFrame(MainFrame mainFrame) {
		setTitle("\u8DF3\u8F6C");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/finder.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setLocationRelativeTo(mainFrame);
		setBounds(100, 100, 715, 286);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel turnLabel = new JLabel("\u8F93\u5165\u8DF3\u8F6C\u8DEF\u5F84:");
		turnLabel.setFont(new Font("华文细黑", Font.PLAIN, 22));
		turnLabel.setBounds(61, 51, 170, 46);
		contentPane.add(turnLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 21));
		textField.setBounds(246, 60, 387, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton turnButton = new JButton("\u8F6C\u5230");
		turnButton.setFont(new Font("幼圆", Font.PLAIN, 22));
		turnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File turn = new File(textField.getText());
				if(!turn.exists()) {
					JOptionPane.showMessageDialog(null, "<html><font face=\"微软雅黑\" size=\"5\">地址不存在!</font></html>");
				}
				else {
					if(!turn.isDirectory()) {
						try {
							Desktop.getDesktop().open(new File(textField.getText()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						//设置上一级currentPath
						mainFrame.currentFilePath = textField.getText();
					}
				}
				dispose();
			}
		});
		turnButton.setBounds(194, 146, 134, 46);
		contentPane.add(turnButton);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("幼圆", Font.PLAIN, 22));
		button.setBounds(431, 146, 134, 46);
		contentPane.add(button);
	}
}
