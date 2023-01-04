package com.java.subjects;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SubjectWise extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelCSE;
	private JPanel panelMenu;
	private JLabel btnAddQuestion;
	private JPanel panelEEE;
	private JLabel btnEditQuestion;
	private JPanel panelBio;
	private JLabel btnAllQuestion;
	private JPanel panelEng;
	private JLabel btnStudentResult;
	private JPanel HomePage;
	private JPanel AddQuestionPanel;
	private JButton btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubjectWise frame = new SubjectWise();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SubjectWise() {
		setLookAndFeel();
		initialize();
		connect();
		showTime();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextArea txtQuestion;
	private JTextArea ansA;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_3;
	private JTextArea ansC;
	private JTextArea ansD;
	private JTextArea ansB;
	private JLabel lblNumber;
	private JRadioButton opA;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton opB;
	private JRadioButton opC;
	private JRadioButton opD;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnNext;
	private JButton btnSubmit;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblMarks;
	private JPanel panelMarks;
	private JLabel lblMinute;
	private JLabel lblSecond;
	private JLabel lblDot;
	
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
	
	public String number, answer, questionId = "1";
	public int marks = 0, minute = 0, second = 0;
	
	Timer timer;
	Calendar calendar;
	SimpleDateFormat timeFormat,dayFormat,dateFormat;
	String time,day,date;
	
	Timer updateTimer;
	int DELAY = 100;
	private JLabel lblSubject;
	private JButton btnStart;
	
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

		if(questionId.equals("15")) {
			btnNext.setEnabled(false);
		}

		buttonGroup.clearSelection();
	}
	
	public void Questions() {
		try {
			if(lblSubject.getText().equals("COMPUTER SCIENCE AND ENGINEERING (CSE)")) {
				pst = con.prepareStatement("select * from cse where Question_ID = '"+questionId+"'");
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {				
					lblNumber.setText(rs.getString(1));
					txtQuestion.setText(rs.getString(2));
					
					ansA.setText(null);
					ansA.append(rs.getString(3));
					
					ansB.setText(null);
					ansB.append(rs.getString(4));
					
					ansC.setText(null);
					ansC.append(rs.getString(5));
					
					ansD.setText(null);
					ansD.append(rs.getString(6));

					answer = rs.getString(7);
				}
			}
			else if(lblSubject.getText().equals("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)")) {
				pst = con.prepareStatement("select * from eee where Question_ID = '"+questionId+"'");
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {				
					lblNumber.setText(rs.getString(1));
					txtQuestion.setText(rs.getString(2));
					
					ansA.setText(null);
					ansA.append(rs.getString(3));
					
					ansB.setText(null);
					ansB.append(rs.getString(4));
					
					ansC.setText(null);
					ansC.append(rs.getString(5));
					
					ansD.setText(null);
					ansD.append(rs.getString(6));

					answer = rs.getString(7);
				}
			}
			else if(lblSubject.getText().equals("BIOLOGY (ZOOLOGY & BOTANY)")) {
				pst = con.prepareStatement("select * from biology where Question_ID = '"+questionId+"'");
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {				
					lblNumber.setText(rs.getString(1));
					txtQuestion.setText(rs.getString(2));
					
					ansA.setText(null);
					ansA.append(rs.getString(3));
					
					ansB.setText(null);
					ansB.append(rs.getString(4));
					
					ansC.setText(null);
					ansC.append(rs.getString(5));
					
					ansD.setText(null);
					ansD.append(rs.getString(6));

					answer = rs.getString(7);
				}
			}
			else {
				pst = con.prepareStatement("select * from others where Question_ID = '"+questionId+"'");
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {				
					lblNumber.setText(rs.getString(1));
					txtQuestion.setText(rs.getString(2));
					
					ansA.setText(null);
					ansA.append(rs.getString(3));
					
					ansB.setText(null);
					ansB.append(rs.getString(4));
					
					ansC.setText(null);
					ansC.append(rs.getString(5));
					
					ansD.setText(null);
					ansD.append(rs.getString(6));

					answer = rs.getString(7);
				}
			}			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void submit() {
		try {
			check_Ans();
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
		//timer.start();
	}

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
		
		panelMenu = new JPanel();
		panelMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null), new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null)));
		panelMenu.setBounds(5, 4, 370, 759);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		panelCSE = new JPanel();
		panelCSE.setOpaque(false);
		panelCSE.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelCSE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCSE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelCSE.setOpaque(true);
				panelCSE.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelCSE.setOpaque(false);
				panelCSE.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panelCSE.isShowing()) {
					lblSubject.setText("COMPUTER SCIENCE AND ENGINEERING (CSE)");
					int Q_id1 = 0;
					Q_id1 = Q_id1+1;
					questionId = String.valueOf(Q_id1);
					
					marks = 0;
					lblMarks.setText(String.valueOf(marks));
					
					timer.stop();
					second = 0;
					minute = 0;
					lblMinute.setText("00");
					lblSecond.setText("00");
					
					Questions();
					btnStart.setVisible(true);
					
					panelCSE.setEnabled(false);
					panelEEE.setEnabled(true);
					panelBio.setEnabled(true);
					panelEng.setEnabled(true);
				}
			}
		});
		panelCSE.setBackground(new Color(0,0,0,0));
		panelCSE.setBounds(0, 158, 370, 55);
		panelMenu.add(panelCSE);
		panelCSE.setLayout(null);
		
		btnAddQuestion = new JLabel("COMPUTER SCIENCE AND ENGINEERING (CSE)");
		btnAddQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddQuestion.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAddQuestion.setBounds(10, 11, 350, 33);
		panelCSE.add(btnAddQuestion);
		
		panelEEE = new JPanel();
		panelEEE.setLayout(null);
		panelEEE.setOpaque(false);
		panelEEE.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelEEE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelEEE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelEEE.setOpaque(true);
				panelEEE.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelEEE.setOpaque(false);
				panelEEE.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSubject.setText("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)");
				int Q_id1 = 0;
				Q_id1 = Q_id1+1;
				questionId = String.valueOf(Q_id1);
				
				marks = 0;
				lblMarks.setText(String.valueOf(marks));
				
				timer.stop();
				second = 0;
				minute = 0;
				lblMinute.setText("00");
				lblSecond.setText("00");
				
				btnStart.setVisible(true);
				Questions();
				
				panelCSE.setEnabled(true);
				panelEEE.setEnabled(false);
				panelBio.setEnabled(true);
				panelEng.setEnabled(true);
			}
		});
		panelEEE.setBackground(new Color(0,0,0,0));
		panelEEE.setBounds(0, 224, 370, 55);
		panelMenu.add(panelEEE);
		
		btnEditQuestion = new JLabel("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)");
		btnEditQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditQuestion.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEditQuestion.setBounds(10, 11, 350, 33);
		panelEEE.add(btnEditQuestion);
		
		panelBio = new JPanel();
		panelBio.setLayout(null);
		panelBio.setOpaque(false);
		panelBio.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelBio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelBio.setOpaque(true);
				panelBio.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelBio.setOpaque(false);
				panelBio.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSubject.setText("BIOLOGY (ZOOLOGY & BOTANY)");
				int Q_id1 = 0;
				Q_id1 = Q_id1+1;
				questionId = String.valueOf(Q_id1);
				
				marks = 0;
				lblMarks.setText(String.valueOf(marks));
				
				timer.stop();
				second = 0;
				minute = 0;
				lblMinute.setText("00");
				lblSecond.setText("00");
				
				btnStart.setVisible(true);
				Questions();
				
				panelCSE.setEnabled(true);
				panelEEE.setEnabled(true);
				panelBio.setEnabled(false);
				panelEng.setEnabled(true);
			}
		});
		panelBio.setBackground(new Color(0, 0, 0, 0));
		panelBio.setBounds(0, 290, 370, 55);
		panelMenu.add(panelBio);
		
		btnAllQuestion = new JLabel("BIOLOGY (ZOOLOGY & BOTANY)");
		btnAllQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		btnAllQuestion.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAllQuestion.setBounds(10, 11, 350, 33);
		panelBio.add(btnAllQuestion);
		
		panelEng = new JPanel();
		panelEng.setLayout(null);
		panelEng.setOpaque(false);
		panelEng.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelEng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelEng.setOpaque(true);
				panelEng.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelEng.setOpaque(false);
				panelEng.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSubject.setText("ENGLISH GRAMMAR AND OTHERS");
				int Q_id1 = 0;
				Q_id1 = Q_id1+1;
				questionId = String.valueOf(Q_id1);
				
				marks = 0;
				lblMarks.setText(String.valueOf(marks));
				
				timer.stop();
				second = 0;
				minute = 0;
				lblMinute.setText("00");
				lblSecond.setText("00");
				
				btnStart.setVisible(true);
				Questions();
				
				panelCSE.setEnabled(true);
				panelEEE.setEnabled(true);
				panelBio.setEnabled(true);
				panelEng.setEnabled(false);
			}
		});
		panelEng.setBackground(new Color(0, 0, 0, 0));
		panelEng.setBounds(0, 356, 370, 55);
		panelMenu.add(panelEng);
		
		btnStudentResult = new JLabel("ENGLISH GRAMMAR AND OTHERS");
		btnStudentResult.setHorizontalAlignment(SwingConstants.CENTER);
		btnStudentResult.setFont(new Font("Dialog", Font.BOLD, 14));
		btnStudentResult.setBounds(10, 11, 350, 33);
		panelEng.add(btnStudentResult);
		
		btnStart = new JButton("START QUIZ");
		btnStart.setVisible(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
				btnStart.setVisible(false);
			}
		});
		btnStart.setBackground(new Color(0, 51, 255));
		btnStart.setForeground(Color.WHITE);
		btnStart.setFont(new Font("Dialog", Font.BOLD, 24));
		btnStart.setBounds(44, 535, 282, 55);
		panelMenu.add(btnStart);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.java.profiles.Students s = new com.java.profiles.Students();
				s.setVisible(true);
			}
		});
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setBackground(new Color(51, 51, 255));
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 24));
		btnBack.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		btnBack.setBounds(44, 617, 282, 55);
		panelMenu.add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(500, 180, 502, 54);
		contentPane.add(scrollPane);
		
		txtQuestion = new JTextArea();
		txtQuestion.setForeground(Color.WHITE);
		scrollPane.setViewportView(txtQuestion);
		txtQuestion.setEditable(false);
		txtQuestion.setBorder(null);
		txtQuestion.setLineWrap(true);
		txtQuestion.setWrapStyleWord(true);
		txtQuestion.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBackground(new Color(0, 0, 0, 0));
		scrollPane_1.setBounds(500, 260, 500, 50);
		contentPane.add(scrollPane_1);
		
		ansA = new JTextArea();
		scrollPane_1.setViewportView(ansA);
		ansA.setEditable(false);
		ansA.setBorder(null);
		ansA.setForeground(Color.WHITE);
		ansA.setFont(new Font("Dialog", Font.PLAIN, 16));
		ansA.setLineWrap(true);
		ansA.setWrapStyleWord(true);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(null);
		scrollPane_2.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);
		scrollPane_2.setBackground(new Color(0, 0, 0, 0));
		scrollPane_2.setBounds(500, 340, 498, 48);
		contentPane.add(scrollPane_2);
		
		ansB = new JTextArea();
		scrollPane_2.setViewportView(ansB);
		ansB.setEditable(false);
		ansB.setBorder(null);
		ansB.setWrapStyleWord(true);
		ansB.setLineWrap(true);
		ansB.setForeground(Color.WHITE);
		ansB.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(null);
		scrollPane_3.setOpaque(false);
		scrollPane_3.getViewport().setOpaque(false);
		scrollPane_3.setBackground(new Color(0, 0, 0, 0));
		scrollPane_3.setBounds(500, 420, 498, 48);
		contentPane.add(scrollPane_3);
		
		ansC = new JTextArea();
		scrollPane_3.setViewportView(ansC);
		ansC.setEditable(false);
		ansC.setBorder(null);
		ansC.setWrapStyleWord(true);
		ansC.setLineWrap(true);
		ansC.setForeground(Color.WHITE);
		ansC.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBorder(null);
		scrollPane_4.setOpaque(false);
		scrollPane_4.getViewport().setOpaque(false);
		scrollPane_4.setBackground(new Color(0, 0, 0, 0));
		scrollPane_4.setBounds(500, 500, 498, 48);
		contentPane.add(scrollPane_4);
		
		ansD = new JTextArea();
		scrollPane_4.setViewportView(ansD);
		ansD.setEditable(false);
		ansD.setBorder(null);
		ansD.setWrapStyleWord(true);
		ansD.setLineWrap(true);
		ansD.setForeground(Color.WHITE);
		ansD.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		lblNumber = new JLabel("00");
		lblNumber.setVerticalAlignment(SwingConstants.TOP);
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setForeground(Color.WHITE);
		lblNumber.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNumber.setBounds(444, 180, 46, 54);
		contentPane.add(lblNumber);
		
		opA = new JRadioButton("");
		buttonGroup.add(opA);
		opA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		opA.setHorizontalAlignment(SwingConstants.CENTER);
		opA.setVerticalAlignment(SwingConstants.TOP);
		opA.setBounds(444, 260, 50, 50);
		contentPane.add(opA);
		
		opB = new JRadioButton("");
		buttonGroup.add(opB);
		opB.setVerticalAlignment(SwingConstants.TOP);
		opB.setHorizontalAlignment(SwingConstants.CENTER);
		opB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		opB.setBounds(444, 340, 50, 50);
		contentPane.add(opB);
		
		opC = new JRadioButton("");
		buttonGroup.add(opC);
		opC.setVerticalAlignment(SwingConstants.TOP);
		opC.setHorizontalAlignment(SwingConstants.CENTER);
		opC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		opC.setBounds(444, 420, 50, 50);
		contentPane.add(opC);
		
		opD = new JRadioButton("");
		buttonGroup.add(opD);
		opD.setVerticalAlignment(SwingConstants.TOP);
		opD.setHorizontalAlignment(SwingConstants.CENTER);
		opD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		opD.setBounds(444, 500, 50, 50);
		contentPane.add(opD);
		
		btnNext = new JButton("NEXT");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_Ans();
				Questions();
			}
		});
		btnNext.setForeground(Color.WHITE);
		btnNext.setBackground(new Color(51, 51, 255));
		btnNext.setHorizontalAlignment(SwingConstants.LEADING);
		btnNext.setToolTipText("Next");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNext.setBounds(500, 600, 130, 40);
		btnNext.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		ImageIcon nexticon = new ImageIcon(this.getClass().getClassLoader().getResource("next.png"));
		Image ni = nexticon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		nexticon = new ImageIcon(ni);
		btnNext.setIcon(nexticon);
		contentPane.add(btnNext);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(51, 51, 255));
		btnSubmit.setHorizontalAlignment(SwingConstants.LEADING);
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setToolTipText("Submit");
		btnSubmit.setBounds(870, 600, 130, 40);
		btnSubmit.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		ImageIcon sub = new ImageIcon(this.getClass().getClassLoader().getResource("ok2.png"));
		Image sic = sub.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		sub = new ImageIcon(sic);
		btnSubmit.setIcon(sub);
		contentPane.add(btnSubmit);
		
		panelMarks = new JPanel();
		panelMarks.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMarks.setLayout(null);
		panelMarks.setBounds(1056, 4, 310, 763);
		contentPane.add(panelMarks);
		
		lblMinute = new JLabel("00");
		lblMinute.setForeground(Color.WHITE);
		lblMinute.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMinute.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinute.setBounds(105, 101, 50, 50);
		panelMarks.add(lblMinute);
		
		lblSecond = new JLabel("00");
		lblSecond.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecond.setForeground(Color.WHITE);
		lblSecond.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblSecond.setBounds(155, 101, 50, 50);
		panelMarks.add(lblSecond);
		
		lblDot = new JLabel(":");
		lblDot.setHorizontalAlignment(SwingConstants.CENTER);
		lblDot.setForeground(Color.WHITE);
		lblDot.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblDot.setBounds(130, 101, 50, 50);
		panelMarks.add(lblDot);
		
		lblNewLabel_3 = new JLabel("Time Remains");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(31, 66, 247, 36);
		panelMarks.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Total Time: 15 Minutes");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(31, 170, 247, 36);
		panelMarks.add(lblNewLabel_4);
		
		lblNewLabel = new JLabel("Total Marks: 15");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel.setBounds(31, 231, 247, 36);
		panelMarks.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Marks:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(64, 288, 91, 36);
		panelMarks.add(lblNewLabel_1);
		
		lblMarks = new JLabel("");
		lblMarks.setForeground(Color.WHITE);
		lblMarks.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMarks.setBounds(155, 288, 91, 36);
		panelMarks.add(lblMarks);
		
		lblSubject = new JLabel("");
		lblSubject.setForeground(Color.WHITE);
		lblSubject.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setBounds(407, 34, 618, 31);
		contentPane.add(lblSubject);
	}
}
