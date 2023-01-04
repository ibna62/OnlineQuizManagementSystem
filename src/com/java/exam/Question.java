package com.java.exam;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.java.profiles.Students;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import javax.swing.JTextField;

public class Question extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;	
	private JTextArea txtQuestion;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton opA;
	private JRadioButton opB;
	private JRadioButton opC;
	private JRadioButton opD;
	private JButton btnNext;
	private JButton btnSubmit;
	private JLabel lblNUmber;
	private JLabel lblNewLabel;
	private JLabel lblMarks;
	private JLabel lblMinute;
	private JLabel lblSecond;
	private JTextArea optionA;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_4;
	private JTextArea optionC;
	private JScrollPane scrollPane_2;
	private JTextArea optionB;
	private JScrollPane scrollPane_3;
	private JTextArea optionD;
	private JLabel lblDot;
	private JLabel lblRemainTime;
	private JLabel lblTime;
	private JLabel dayLabel;
	private JLabel dateLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtCode;
	private JTextField txtType;
	private JTextField txtMinutes;
	private JLabel lblNewLabel_2;
	private JTextField txtName;
	private JLabel lblNewLabel_3;
	private JTextField txtID;
	private JLabel lblNewLabel_4;
	private JTextField txtTotalQ;
	private JLabel lblNewLabel_5;
	private JButton btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question frame = new Question();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Question() {
		setLookAndFeel();
		initialize();
		connect();
		chk();
		Questions();
		showTime();
		currentTime();
		showDate();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");
			
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	public String number, answer, questionId = "1";
	public int marks = 0, minute = 0, second = 0;
	
	Timer timer;
	Calendar calendar;
	SimpleDateFormat timeFormat,dayFormat,dateFormat;
	String time,day,date;
	
	Timer updateTimer;
	int DELAY = 100;
	private JTextField txtTotalM;
	private JTextField txtObtainedM;
	private JLabel lblNewLabel_5_2;
	
	private void chk() {
		com.java.account.Login lg = new com.java.account.Login();
		txtName.setText(com.java.account.Login.name1);
		txtID.setText(com.java.account.Login.id1);
	}
	
	public void check_Ans() {
		String ans = "";
		if(opA.isSelected()) {
			ans = "A";
		}
		else if(opB.isSelected()) {
			ans = "B";
		}
		else if(opC.isSelected()) {
			ans = "C";
		}
		else if(opD.isSelected()) {
			ans = "D";
		}
		if(ans.equals(answer)) {
			marks = marks + 1;
			String m = String.valueOf(marks);
			lblMarks.setText(m);
		}
		
		int Q_id1 = Integer.parseInt(questionId);
		Q_id1 = Q_id1+1;
		questionId = String.valueOf(Q_id1);

		if(questionId.equals("10")) {
			btnNext.setEnabled(false);
		}

		buttonGroup.clearSelection();
	}
	
	public void Questions() {
		try {
			pst = con.prepareStatement("select * from questions where Question_ID = '"+questionId+"'");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {				
				lblNUmber.setText(rs.getString(1));
				txtQuestion.setText(rs.getString(2));
				
				optionA.setText(null);
				optionA.append(rs.getString(3));
				
				optionB.setText(null);
				optionB.append(rs.getString(4));
				
				optionC.setText(null);
				optionC.append(rs.getString(5));
				
				optionD.setText(null);
				optionD.append(rs.getString(6));
				
				answer = rs.getString(7);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void ans() {
		try {
			pst = con.prepareStatement("select Answer from questions");
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {				
				lblNUmber.setText(rs.getString(1));
				txtQuestion.setText(rs.getString(2));
				
				optionA.setText(null);
				optionA.append(rs.getString(3));
				
				optionB.setText(null);
				optionB.append(rs.getString(4));
				
				optionC.setText(null);
				optionC.append(rs.getString(5));
				
				optionD.setText(null);
				optionD.append(rs.getString(6));
				
				answer = rs.getString(7);
			}
		}
		catch(Exception ex) {
			
		}
	}
	
	private void submit() {
		try {
			check_Ans();
			timer.stop();
			second = 0;
			minute = 0;
			String name,id,inst,dep;
			int number;
			name = com.java.account.Login.name1;
			id = com.java.account.Login.id1;
			inst = com.java.account.Login.institute1;
			dep = com.java.account.Login.department1;
			number = Integer.parseInt(String.valueOf(marks));
			
			pst = con.prepareStatement("insert into result(Student_Name, ID, Institution, Department, Marks)values(?,?,?,?,?)");
			
			pst.setString(1, name);
			pst.setString(2, id);
			pst.setString(3, inst);
			pst.setString(4, dep);
			pst.setInt(5, number);
			
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Quiz Finished Successfully!");
			txtObtainedM.setText(String.valueOf(lblMarks.getText()));
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
	
	public void showTime() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				lblMinute.setText(String.valueOf(String.format("%02d", minute)));
				lblSecond.setText(String.valueOf(String.format("%02d", second)));
				
				if(second == 60) {
					second = 0;
					minute++;
					lblSecond.setText(String.valueOf(String.format("%02d", second)));
					lblMinute.setText(String.valueOf(String.format("%02d", minute)));
					
					if(minute == 10) {
						timer.stop();
						check_Ans();
						submit();
					}
				}
			}
		});
		timer.start();
	}
	
	public void currentTime() {
		updateTimer = new Timer(DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date currentTime = new Date();
				time = "hh:mm:ss a";
				DateFormat formatTime = new SimpleDateFormat(time);
				String format = formatTime.format(currentTime);
				lblTime.setText(format);
			}
			
		});
		updateTimer.start();
	}
	
	private void showDate() {
		day = dayFormat.format(Calendar.getInstance().getTime());
		dayLabel.setText(day);
		
		date = dateFormat.format(Calendar.getInstance().getTime());
		dateLabel.setText(date);
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
			UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(0, 102, 255)));
			UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
			UIManager.put("Button.arc", 999);
			UIManager.put("Component.arc", 999);
			UIManager.put("ProgressBar.arc", 999);
			UIManager.put("TextComponent.arc", 999);
			UIManager.put("ScrollBar.width", 7);
			UIManager.put("ScrollBar.trackArc", 999);
			UIManager.put("ScrollBar.thumbArc", 999);
			UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
			UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}*/

	private void initialize() {
		setUndecorated(true);
		setDefaultLookAndFeelDecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		panelExit = new JPanel();
		panelExit.setBounds(1340, 8, 13, 13);
		panelExit.setToolTipText("Exit");
		panelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelExit.setOpaque(true);
				panelExit.setBackground(Color.decode("#fc5744"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelExit.setOpaque(false);
				panelExit.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to close?", "Confirmation",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		panelExit.setBorder(null);
		panelExit.setOpaque(false);
		contentPane.add(panelExit);
		panelExit.setLayout(null);

		lblExit = new JLabel("x");
		lblExit.setBorder(null);
		lblExit.setFont(new Font("Corbel", Font.BOLD, 15));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(0, 1, 13, 13);
		panelExit.add(lblExit);

		panelMinimize = new JPanel();
		panelMinimize.setBounds(1300, 8, 16, 14);
		panelMinimize.setToolTipText("Minimize");
		panelMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelMinimize.setOpaque(true);
				panelMinimize.setBackground(Color.decode("#fc5744"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelMinimize.setOpaque(false);
				panelMinimize.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		panelMinimize.setLayout(null);
		panelMinimize.setOpaque(false);
		panelMinimize.setBorder(null);
		contentPane.add(panelMinimize);

		lblMinimize = new JLabel("-");
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setFont(new Font("Colonna MT", Font.BOLD, 30));
		lblMinimize.setBorder(null);
		lblMinimize.setBounds(0, 0, 14, 13);
		panelMinimize.add(lblMinimize);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBackground(new Color(0, 0, 0, 0));
		scrollPane_1.setBounds(686, 119, 550, 72);
		contentPane.add(scrollPane_1);
		
		txtQuestion = new JTextArea();
		txtQuestion.setForeground(Color.WHITE);
		txtQuestion.setBorder(null);
		txtQuestion.setWrapStyleWord(true);
		txtQuestion.setLineWrap(true);
		scrollPane_1.setViewportView(txtQuestion);
		txtQuestion.setOpaque(false);
		txtQuestion.setEditable(false);
		txtQuestion.setFont(new Font("Dialog", Font.BOLD, 20));
		
		opA = new JRadioButton("");
		opA.setForeground(Color.WHITE);
		opA.setName("");
		opA.setVerticalAlignment(SwingConstants.TOP);
		opA.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(opA);
		opA.setFont(new Font("Dialog", Font.BOLD, 40));
		opA.setBounds(640, 229, 40, 97);
		contentPane.add(opA);
		
		opB = new JRadioButton("");
		opB.setForeground(Color.WHITE);
		opB.setHorizontalAlignment(SwingConstants.CENTER);
		opB.setVerticalAlignment(SwingConstants.TOP);
		buttonGroup.add(opB);
		opB.setFont(new Font("Dialog", Font.PLAIN, 22));
		opB.setBounds(640, 337, 40, 97);
		contentPane.add(opB);
		
		opC = new JRadioButton("");
		opC.setForeground(Color.WHITE);
		opC.setHorizontalAlignment(SwingConstants.CENTER);
		opC.setVerticalAlignment(SwingConstants.TOP);
		buttonGroup.add(opC);
		opC.setFont(new Font("Dialog", Font.PLAIN, 22));
		opC.setBounds(640, 442, 40, 100);
		contentPane.add(opC);
		
		opD = new JRadioButton("");
		opD.setForeground(Color.WHITE);
		opD.setHorizontalAlignment(SwingConstants.CENTER);
		opD.setVerticalAlignment(SwingConstants.TOP);
		buttonGroup.add(opD);
		opD.setFont(new Font("Dialog", Font.PLAIN, 22));
		opD.setBounds(640, 553, 40, 97);
		contentPane.add(opD);
		
		btnNext = new JButton("NEXT");
		btnNext.setForeground(Color.WHITE);
		btnNext.setBackground(new Color(51, 51, 255));
		btnNext.setHorizontalAlignment(SwingConstants.LEADING);
		btnNext.setToolTipText("Next");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_Ans();
				Questions();
			}
		});
		btnNext.setBounds(686, 670, 130, 40);
		btnNext.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		ImageIcon nexticon = new ImageIcon(this.getClass().getClassLoader().getResource("next.png"));
		Image ni = nexticon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		nexticon = new ImageIcon(ni);
		btnNext.setIcon(nexticon);
		contentPane.add(btnNext);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(51, 51, 255));
		btnSubmit.setHorizontalAlignment(SwingConstants.LEADING);
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setToolTipText("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submit();
			}
		});
		btnSubmit.setBounds(1106, 670, 130, 40);
		btnSubmit.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		ImageIcon sub = new ImageIcon(this.getClass().getClassLoader().getResource("ok2.png"));
		Image sic = sub.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		sub = new ImageIcon(sic);
		btnSubmit.setIcon(sub);
		contentPane.add(btnSubmit);
		
		lblNUmber = new JLabel("00");
		lblNUmber.setForeground(Color.WHITE);
		lblNUmber.setVerticalAlignment(SwingConstants.TOP);
		lblNUmber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNUmber.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNUmber.setBounds(640, 119, 40, 72);
		contentPane.add(lblNUmber);
		
		lblNewLabel = new JLabel("MARKS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel.setBounds(640, 29, 83, 38);
		contentPane.add(lblNewLabel);
		
		lblMarks = new JLabel("");
		lblMarks.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarks.setForeground(Color.WHITE);
		lblMarks.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMarks.setBounds(715, 29, 48, 38);
		contentPane.add(lblMarks);
		
		lblMinute = new JLabel("00");
		lblMinute.setForeground(Color.WHITE);
		lblMinute.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinute.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMinute.setBounds(1146, 29, 40, 38);
		contentPane.add(lblMinute);
		
		lblSecond = new JLabel("00");
		lblSecond.setForeground(Color.WHITE);
		lblSecond.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecond.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSecond.setBounds(1196, 29, 40, 38);
		contentPane.add(lblSecond);
	
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(686, 229, 550, 97);
		contentPane.add(scrollPane);
		
		optionA = new JTextArea();
		optionA.setForeground(Color.WHITE);
		optionA.setFont(new Font("Dialog", Font.PLAIN, 18));
		optionA.setEditable(false);
		scrollPane.setViewportView(optionA);
		optionA.setBorder(null);
		optionA.setLineWrap(true);
		optionA.setWrapStyleWord(true);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(null);
		scrollPane_2.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);
		scrollPane_2.setBackground(new Color(0, 0, 0, 0));
		scrollPane_2.setBounds(686, 337, 550, 97);
		contentPane.add(scrollPane_2);
		
		optionB = new JTextArea();
		optionB.setForeground(Color.WHITE);
		scrollPane_2.setViewportView(optionB);
		optionB.setBorder(null);
		optionB.setWrapStyleWord(true);
		optionB.setLineWrap(true);
		optionB.setFont(new Font("Dialog", Font.PLAIN, 18));
		optionB.setEditable(false);
		optionB.setBorder(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(null);
		scrollPane_3.setOpaque(false);
		scrollPane_3.getViewport().setOpaque(false);
		scrollPane_3.setBackground(new Color(0, 0, 0, 0));
		scrollPane_3.setBounds(686, 445, 550, 97);
		contentPane.add(scrollPane_3);
		
		optionC = new JTextArea();
		optionC.setForeground(Color.WHITE);
		scrollPane_3.setViewportView(optionC);
		optionC.setBorder(null);
		optionC.setWrapStyleWord(true);
		optionC.setLineWrap(true);
		optionC.setFont(new Font("Dialog", Font.PLAIN, 18));
		optionC.setEditable(false);
		optionC.setBorder(null);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBorder(null);
		scrollPane_4.setOpaque(false);
		scrollPane_4.getViewport().setOpaque(false);
		scrollPane_4.setBackground(new Color(0, 0, 0, 0));
		scrollPane_4.setBounds(686, 553, 550, 97);
		contentPane.add(scrollPane_4);
		
		optionD = new JTextArea();
		optionD.setForeground(Color.WHITE);
		scrollPane_4.setViewportView(optionD);
		optionD.setBorder(null);
		optionD.setWrapStyleWord(true);
		optionD.setLineWrap(true);
		optionD.setFont(new Font("Dialog", Font.PLAIN, 18));
		optionD.setEditable(false);
		optionD.setBorder(null);
		
		lblDot = new JLabel(":");
		lblDot.setForeground(Color.WHITE);
		lblDot.setHorizontalAlignment(SwingConstants.CENTER);
		lblDot.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDot.setBounds(1171, 28, 40, 38);
		contentPane.add(lblDot);
		
		lblRemainTime = new JLabel("Remain Time");
		lblRemainTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemainTime.setForeground(Color.WHITE);
		lblRemainTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRemainTime.setBounds(1015, 29, 129, 38);
		contentPane.add(lblRemainTime);
		
		lblTime = new JLabel("");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblTime.setBounds(10, 29, 162, 38);
		contentPane.add(lblTime);
		
		dayLabel = new JLabel("");
		dayLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		dayLabel.setForeground(Color.WHITE);
		dayLabel.setBounds(182, 29, 112, 40);
		contentPane.add(dayLabel);
		
		dateLabel = new JLabel("");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		dateLabel.setBounds(304, 29, 162, 40);
		contentPane.add(dateLabel);
		
		/*lblNewLabel_1 = new JLabel("Exam Code:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 185, 112, 26);
		contentPane.add(lblNewLabel_1);
		
		txtCode = new JTextField();
		txtCode.setBorder(null);
		txtCode.setEditable(false);
		txtCode.setOpaque(false);
		txtCode.setForeground(Color.WHITE);
		txtCode.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtCode.setBounds(127, 185, 91, 26);
		contentPane.add(txtCode);
		txtCode.setColumns(10);*/
		
		txtType = new JTextField();
		txtType.setText("MCQ");
		txtType.setOpaque(false);
		txtType.setForeground(Color.WHITE);
		txtType.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtType.setEditable(false);
		txtType.setColumns(10);
		txtType.setBorder(null);
		txtType.setBounds(127, 229, 91, 26);
		contentPane.add(txtType);
		
		JLabel lblNewLabel_1_1 = new JLabel("Exam Type:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 229, 112, 26);
		contentPane.add(lblNewLabel_1_1);
		
		txtMinutes = new JTextField();
		txtMinutes.setText("10 Minutes");
		txtMinutes.setOpaque(false);
		txtMinutes.setForeground(Color.WHITE);
		txtMinutes.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMinutes.setEditable(false);
		txtMinutes.setColumns(10);
		txtMinutes.setBorder(null);
		txtMinutes.setBounds(127, 277, 112, 26);
		contentPane.add(txtMinutes);
		
		lblNewLabel_2 = new JLabel("Total Time:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 277, 112, 26);
		contentPane.add(lblNewLabel_2);
		
		txtName = new JTextField();
		txtName.setOpaque(false);
		txtName.setForeground(Color.WHITE);
		txtName.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBorder(null);
		txtName.setBounds(142, 325, 350, 26);
		contentPane.add(txtName);
		
		lblNewLabel_3 = new JLabel("Student Name:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 325, 137, 26);
		contentPane.add(lblNewLabel_3);
		
		txtID = new JTextField();
		txtID.setOpaque(false);
		txtID.setForeground(Color.WHITE);
		txtID.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBorder(null);
		txtID.setBounds(127, 370, 148, 26);
		contentPane.add(txtID);
		
		lblNewLabel_4 = new JLabel("Student ID:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 370, 112, 26);
		contentPane.add(lblNewLabel_4);
		
		txtTotalQ = new JTextField();
		txtTotalQ.setText("10");
		txtTotalQ.setOpaque(false);
		txtTotalQ.setForeground(Color.WHITE);
		txtTotalQ.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtTotalQ.setEditable(false);
		txtTotalQ.setColumns(10);
		txtTotalQ.setBorder(null);
		txtTotalQ.setBounds(157, 423, 91, 26);
		contentPane.add(txtTotalQ);
		
		lblNewLabel_5 = new JLabel("Total Question:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(10, 423, 137, 26);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Total Marks:");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_5_1.setBounds(10, 470, 137, 26);
		contentPane.add(lblNewLabel_5_1);
		
		txtTotalM = new JTextField();
		txtTotalM.setText("10");
		txtTotalM.setOpaque(false);
		txtTotalM.setForeground(Color.WHITE);
		txtTotalM.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtTotalM.setEditable(false);
		txtTotalM.setColumns(10);
		txtTotalM.setBorder(null);
		txtTotalM.setBounds(157, 470, 91, 26);
		contentPane.add(txtTotalM);
		
		lblNewLabel_5_2 = new JLabel("Marks Obtained:");
		lblNewLabel_5_2.setForeground(Color.WHITE);
		lblNewLabel_5_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_5_2.setBounds(10, 516, 148, 26);
		contentPane.add(lblNewLabel_5_2);
		
		txtObtainedM = new JTextField();
		txtObtainedM.setText("15");
		txtObtainedM.setOpaque(false);
		txtObtainedM.setForeground(Color.WHITE);
		txtObtainedM.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtObtainedM.setEditable(false);
		txtObtainedM.setColumns(10);
		txtObtainedM.setBorder(null);
		txtObtainedM.setBounds(162, 516, 91, 26);
		contentPane.add(txtObtainedM);
		
		btnBack = new JButton("BACK");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.java.profiles.Students s = new com.java.profiles.Students();
				s.setVisible(true);
			}
		});
		btnBack.setBackground(new Color(51, 51, 255));
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 24));
		btnBack.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		btnBack.setBounds(107, 682, 162, 40);
		contentPane.add(btnBack);
		
		dayFormat = new SimpleDateFormat("EEEE");
		dateFormat = new SimpleDateFormat("dd MMM , yyyy");
	}
}