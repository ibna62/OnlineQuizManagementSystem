package com.java.admin;

import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import net.proteanit.sql.DbUtils;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelCSE;
	private JPanel panelMenu;
	private JLabel btnCSE;
	private JPanel panelEEE;
	private JLabel btnEEE;
	private JPanel panelBio;
	private JLabel btnBio;
	private JPanel panelEng;
	private JLabel btnEng;
	private JPanel HomePage;
	private JPanel QuestionPanel;
	private JLayeredPane layeredPane;
	private JPanel AllQuestionPanel;
	private JTextArea txtQuestion;
	private JButton btnSubmit;
	private JTextArea txtA;
	private JTextArea txtB;
	private JTextArea txtC;
	private JTextArea txtD;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Admin() {
		setLookAndFeel();
		initialize();
		connect();
		//questionLoad();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//int quesNo = 0;
	
	private JTextField txtAns;
	private JLabel lblSubject;
	private JLabel lblQuestionNo;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblimage;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txtSearch;
	private JLabel lblNewLabel;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel btnLogout;
	private JLabel btnHelp;
	private JLabel btnHome;
	private JLabel lblmenu;
	private JLabel lblq;
	private JPanel panelAll;
	private JLabel lblAll;
	private JLabel lblCSE;
	private JLabel lblEEE;
	private JLabel lblBiologyQuestions;
	private JLabel lblOtherQuestions;
	
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
			UIManager.put("TableHeader.font", new Font("NikoshGrameem", Font.BOLD, 16));
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
	
	//Checking if Questions is available in database then shows the question numbers
	private void checkQuestionNumber() {
		try {
			if(lblSubject.getText().equals("COMPUTER SCIENCE AND ENGINEERING (CSE)")) {
				pst = con.prepareStatement("select count(Question_ID) from cse");
				rs = pst.executeQuery();
				if(rs.first()) {
					int id = rs.getInt(1);
					id+=1;
					lblQuestionNo.setText(String.valueOf(id));
				}
				else {
					lblQuestionNo.setText("1");
				}
			}
			else if(lblSubject.getText().equals("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)")) {
				pst = con.prepareStatement("select count(Question_ID) from eee");
				rs = pst.executeQuery();
				if(rs.first()) {
					int id = rs.getInt(1);
					id+=1;
					lblQuestionNo.setText(String.valueOf(id));
				}
				else {
					lblQuestionNo.setText("1");
				}
			}
			else if(lblSubject.getText().equals("BIOLOGY (ZOOLOGY & BOTANY)")) {
				pst = con.prepareStatement("select count(Question_ID) from biology");
				rs = pst.executeQuery();
				if(rs.first()) {
					int id = rs.getInt(1);
					id+=1;
					lblQuestionNo.setText(String.valueOf(id));
				}
				else {
					lblQuestionNo.setText("1");
				}
			}
			else {
				pst = con.prepareStatement("select count(Question_ID) from others");
				rs = pst.executeQuery();
				if(rs.first()) {
					int id = rs.getInt(1);
					id+=1;
					lblQuestionNo.setText(String.valueOf(id));
				}
				else {
					lblQuestionNo.setText("1");
				}
			}
		}
		catch(Exception ex) {
			
		}
	}
	
	public void questionLoad() {
		try {
			pst = con.prepareStatement("select * from questions");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(150);
			    }
			    else if (i == 1) {
			        column.setPreferredWidth(400);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==5) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==6) {
			    	column.setPreferredWidth(150);
			    }
			}
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 16));
			table.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void cseQuestion() {
		try {
			pst = con.prepareStatement("select * from cse");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(150);
			    }
			    else if (i == 1) {
			        column.setPreferredWidth(400);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==5) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==6) {
			    	column.setPreferredWidth(150);
			    }
			}
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 16));
			table.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void eeeQuestion() {
		try {
			pst = con.prepareStatement("select * from eee");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(150);
			    }
			    else if (i == 1) {
			        column.setPreferredWidth(400);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==5) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==6) {
			    	column.setPreferredWidth(150);
			    }
			}
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 16));
			table.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void bioQuestion() {
		try {
			pst = con.prepareStatement("select * from biology");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(150);
			    }
			    else if (i == 1) {
			        column.setPreferredWidth(400);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==5) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==6) {
			    	column.setPreferredWidth(150);
			    }
			}
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 16));
			table.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void otherQuestion() {
		try {
			pst = con.prepareStatement("select * from others");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(150);
			    }
			    else if (i == 1) {
			        column.setPreferredWidth(400);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==5) {
			    	column.setPreferredWidth(300);
			    }
			    else if(i==6) {
			    	column.setPreferredWidth(150);
			    }
			}
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 16));
			table.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	private void initialize() {
		setUndecorated(true);
		setDefaultLookAndFeelDecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
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
		
		btnLogout = new JLabel("");
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new com.java.account.Login().setVisible(true);
			}
		});
		btnLogout.setToolTipText("Logout");
		btnLogout.setBounds(261, 686, 60, 60);
		ImageIcon logoutimage = new ImageIcon(this.getClass().getResource("/logout.png"));
		Image lout = logoutimage.getImage().getScaledInstance(btnLogout.getWidth(), btnLogout.getHeight(), Image.SCALE_SMOOTH);
		logoutimage = new ImageIcon(lout);
		btnLogout.setIcon(logoutimage);
		panelMenu.add(btnLogout);
		
		btnHelp = new JLabel("");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new com.java.study.Instructions().setVisible(true);
			}
		});
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setToolTipText("Help");
		btnHelp.setBounds(154, 686, 60, 60);
		ImageIcon helpimage = new ImageIcon(this.getClass().getResource("/help.png"));
		Image help = helpimage.getImage().getScaledInstance(btnHelp.getWidth(), btnHelp.getHeight(), Image.SCALE_SMOOTH);
		helpimage = new ImageIcon(help);
		btnHelp.setIcon(helpimage);
		panelMenu.add(btnHelp);
		
		btnHome = new JLabel("");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(HomePage);
			}
		});
		btnHome.setToolTipText("Home");
		btnHome.setBounds(47, 686, 60, 60);
		ImageIcon homeimage = new ImageIcon(this.getClass().getResource("/home.png"));
		Image home = homeimage.getImage().getScaledInstance(btnHome.getWidth(), btnHome.getHeight(), Image.SCALE_SMOOTH);
		homeimage = new ImageIcon(home);
		btnHome.setIcon(homeimage);
		panelMenu.add(btnHome);
		
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
					lblSubject.setText(btnCSE.getText());
				}
				switchPanels(QuestionPanel);
				checkQuestionNumber();
			}
		});
		panelCSE.setBackground(new Color(0,0,0,0));
		panelCSE.setBounds(0, 158, 370, 55);
		panelMenu.add(panelCSE);
		panelCSE.setLayout(null);
		
		btnCSE = new JLabel("COMPUTER SCIENCE AND ENGINEERING (CSE)");
		btnCSE.setHorizontalAlignment(SwingConstants.CENTER);
		btnCSE.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCSE.setBounds(10, 11, 350, 33);
		panelCSE.add(btnCSE);
		
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
				if(panelEEE.isShowing()) {
					lblSubject.setText(btnEEE.getText());
				}
				switchPanels(QuestionPanel);
				checkQuestionNumber();
			}
		});
		panelEEE.setBackground(new Color(0,0,0,0));
		panelEEE.setBounds(0, 224, 370, 55);
		panelMenu.add(panelEEE);
		
		btnEEE = new JLabel("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)");
		btnEEE.setHorizontalAlignment(SwingConstants.CENTER);
		btnEEE.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEEE.setBounds(10, 11, 350, 33);
		panelEEE.add(btnEEE);
		
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
				if(panelBio.isShowing()) {
					lblSubject.setText(btnBio.getText());
				}
				switchPanels(QuestionPanel);
				checkQuestionNumber();
			}
		});
		panelBio.setBackground(new Color(0, 0, 0, 0));
		panelBio.setBounds(0, 290, 370, 55);
		panelMenu.add(panelBio);
		
		btnBio = new JLabel("BIOLOGY (ZOOLOGY & BOTANY)");
		btnBio.setHorizontalAlignment(SwingConstants.CENTER);
		btnBio.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBio.setBounds(10, 11, 350, 33);
		panelBio.add(btnBio);
		
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
				if(panelEng.isShowing()) {
					lblSubject.setText(btnEng.getText());
				}
				switchPanels(QuestionPanel);
				checkQuestionNumber();
			}
		});
		panelEng.setBackground(new Color(0, 0, 0, 0));
		panelEng.setBounds(0, 356, 370, 55);
		panelMenu.add(panelEng);
		
		btnEng = new JLabel("ENGLISH GRAMMAR AND OTHERS");
		btnEng.setHorizontalAlignment(SwingConstants.CENTER);
		btnEng.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEng.setBounds(10, 11, 350, 33);
		panelEng.add(btnEng);
		
		panelAll = new JPanel();
		panelAll.setLayout(null);
		panelAll.setOpaque(false);
		panelAll.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelAll.setOpaque(true);
				panelAll.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAll.setOpaque(false);
				panelAll.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(AllQuestionPanel);
			}
		});
		panelAll.setBackground(new Color(0, 0, 0, 0));
		panelAll.setBounds(0, 422, 370, 55);
		panelMenu.add(panelAll);
		
		JLabel btnAll = new JLabel("ALL QUESTIONS LIST");
		btnAll.setHorizontalAlignment(SwingConstants.CENTER);
		btnAll.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAll.setBounds(10, 11, 350, 33);
		panelAll.add(btnAll);
		
		lblmenu = new JLabel("");
		lblmenu.setBounds(0, 0, 370, 759);
		ImageIcon menuimg = new ImageIcon(this.getClass().getResource("/best7.jpg"));
		Image mig = menuimg.getImage().getScaledInstance(lblmenu.getWidth(), lblmenu.getHeight(), Image.SCALE_SMOOTH);
		menuimg = new ImageIcon(mig);
		lblmenu.setIcon(menuimg);
		panelMenu.add(lblmenu);
				
		/*------------------------- Starting Panels Here ----------------------------*/
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(375, 0, 991, 768);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		/*----------------------------- Home Page Panel -----------------------------*/
		HomePage = new JPanel();
		HomePage.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		layeredPane.add(HomePage, "name_248214331803200");
		HomePage.setLayout(null);
		
		lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 991, 768);
		ImageIcon imageicon = new ImageIcon(this.getClass().getClassLoader().getResource("quiz1.jpg"));
		Image image = imageicon.getImage().getScaledInstance(lblimage.getWidth(), lblimage.getHeight(), Image.SCALE_SMOOTH);
		imageicon = new ImageIcon(image);
		lblimage.setIcon(imageicon);
		HomePage.add(lblimage);
		
		/*----------------------------- Add Question Panel -----------------------------*/
		QuestionPanel = new JPanel();
		QuestionPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		layeredPane.add(QuestionPanel, "name_248226911218800");
		QuestionPanel.setLayout(null);
		
		txtQuestion = new JTextArea();
		txtQuestion.setForeground(Color.WHITE);
		txtQuestion.setWrapStyleWord(true);
		txtQuestion.setLineWrap(true);
		txtQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		txtQuestion.setBounds(87, 188, 839, 82);
		txtQuestion.putClientProperty( "JComponent.roundRect", true );
		QuestionPanel.add(txtQuestion);
		
		txtA = new JTextArea();
		txtA.setForeground(Color.WHITE);
		txtA.setWrapStyleWord(true);
		txtA.setLineWrap(true);
		txtA.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtA.setBounds(87, 345, 369, 88);
		QuestionPanel.add(txtA);
		
		txtB = new JTextArea();
		txtB.setForeground(Color.WHITE);
		txtB.setWrapStyleWord(true);
		txtB.setLineWrap(true);
		txtB.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtB.setBounds(557, 345, 369, 88);
		QuestionPanel.add(txtB);
		
		txtC = new JTextArea();
		txtC.setForeground(Color.WHITE);
		txtC.setWrapStyleWord(true);
		txtC.setLineWrap(true);
		txtC.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtC.setBounds(87, 444, 369, 88);
		QuestionPanel.add(txtC);
		
		txtD = new JTextArea();
		txtD.setForeground(Color.WHITE);
		txtD.setWrapStyleWord(true);
		txtD.setLineWrap(true);
		txtD.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtD.setBounds(557, 444, 369, 88);
		QuestionPanel.add(txtD);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(0, 153, 255));
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 25));
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String question,opA,opB,opC,opD,ans;
					int quesNo;
					quesNo = Integer.parseInt(lblQuestionNo.getText());
					question = txtQuestion.getText();
					opA = txtA.getText();
					opB = txtB.getText();
					opC = txtC.getText();
					opD = txtD.getText();
					ans = txtAns.getText();
					
					if(question.equals("") || opA.equals("") || opB.equals("") || opC.equals("")
							|| opD.equals("") || ans.equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill up all the field");
					}
					else {
						if(lblSubject.getText().equals("COMPUTER SCIENCE AND ENGINEERING (CSE)")) {
							pst = con.prepareStatement("insert into cse(Question_ID, Question, Option_A, Option_B, Option_C, Option_D, Answer)values(?,?,?,?,?,?,?)");
							pst.setInt(1, quesNo);
							pst.setString(2, question);
							pst.setString(3, opA);
							pst.setString(4, opB);
							pst.setString(5, opC);
							pst.setString(6, opD);
							pst.setString(7, ans);

							pst.executeUpdate();
						}
						else if(lblSubject.getText().equals("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)")) {
							pst = con.prepareStatement("insert into eee(Question_ID, Question, Option_A, Option_B, Option_C, Option_D, Answer)values(?,?,?,?,?,?,?)");
							pst.setInt(1, quesNo);
							pst.setString(2, question);
							pst.setString(3, opA);
							pst.setString(4, opB);
							pst.setString(5, opC);
							pst.setString(6, opD);
							pst.setString(7, ans);

							pst.executeUpdate();
						}
						else if(lblSubject.getText().equals("BIOLOGY (ZOOLOGY & BOTANY)")) {
							pst = con.prepareStatement("insert into biology(Question_ID, Question, Option_A, Option_B, Option_C, Option_D, Answer)values(?,?,?,?,?,?,?)");
							pst.setInt(1, quesNo);
							pst.setString(2, question);
							pst.setString(3, opA);
							pst.setString(4, opB);
							pst.setString(5, opC);
							pst.setString(6, opD);
							pst.setString(7, ans);

							pst.executeUpdate();
						}
						else {
							pst = con.prepareStatement("insert into others(Question_ID, Question, Option_A, Option_B, Option_C, Option_D, Answer)values(?,?,?,?,?,?,?)");
							pst.setInt(1, quesNo);
							pst.setString(2, question);
							pst.setString(3, opA);
							pst.setString(4, opB);
							pst.setString(5, opC);
							pst.setString(6, opD);
							pst.setString(7, ans);

							pst.executeUpdate();
						}

						JOptionPane.showMessageDialog(null, "Question Submitted Successfully!");
						checkQuestionNumber();
						
						txtQuestion.setText(null);
						txtA.setText(null);
						txtB.setText(null);
						txtC.setText(null);
						txtD.setText(null);
						txtAns.setText(null);
					}
				}
				catch(Exception ex) {
					
				}
			}
		});
		btnSubmit.setBounds(235, 703, 160, 39);
		QuestionPanel.add(btnSubmit);
		
		txtAns = new JTextField();
		txtAns.setForeground(Color.WHITE);
		txtAns.setHorizontalAlignment(SwingConstants.CENTER);
		txtAns.setOpaque(false);
		txtAns.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtAns.setBounds(218, 543, 91, 39);
		QuestionPanel.add(txtAns);
		txtAns.setColumns(10);
		
		lblSubject = new JLabel("");
		lblSubject.setForeground(Color.WHITE);
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblSubject.setBounds(76, 65, 839, 47);
		QuestionPanel.add(lblSubject);
		
		lblQuestionNo = new JLabel("00");
		lblQuestionNo.setForeground(Color.WHITE);
		lblQuestionNo.setVerticalAlignment(SwingConstants.TOP);
		lblQuestionNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionNo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblQuestionNo.setBounds(36, 188, 46, 82);
		QuestionPanel.add(lblQuestionNo);
		
		lblNewLabel_2 = new JLabel("A");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(36, 345, 46, 88);
		QuestionPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("C");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_3.setBounds(36, 444, 46, 88);
		QuestionPanel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("B");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_4.setBounds(507, 345, 46, 88);
		QuestionPanel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("D");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(507, 444, 46, 88);
		QuestionPanel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Answer");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(87, 543, 121, 39);
		QuestionPanel.add(lblNewLabel_6);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(Color.WHITE);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String Id = txtSearch.getText();
					if(lblSubject.getText().equals("COMPUTER SCIENCE AND ENGINEERING (CSE)")) {
						pst = con.prepareStatement("select * from cse where Question_ID = ?");
						pst.setString(1, Id);
						ResultSet rs = pst.executeQuery();
						
						if(Id.equals("")) {
							checkQuestionNumber();
							txtQuestion.setText(null);
							txtA.setText(null);
							txtB.setText(null);
							txtC.setText(null);
							txtD.setText(null);
							txtAns.setText(null);
						}
						else {
							//if(rs.next()) {
							if(rs.first()) {
								lblQuestionNo.setText(rs.getString(1));
								txtQuestion.setText(rs.getString(2));
								txtA.setText(rs.getString(3));
								txtB.setText(rs.getString(4));
								txtC.setText(rs.getString(5));
								txtD.setText(rs.getString(6));
								txtAns.setText(rs.getString(7));
							}
							else {
								JOptionPane.showMessageDialog(null, "Question isn't available!");
							}
						}
					}
					else if(lblSubject.getText().equals("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)")) {
						pst = con.prepareStatement("select * from eee where Question_ID = ?");
						pst.setString(1, Id);
						ResultSet rs = pst.executeQuery();
						
						if(Id.equals("")) {
							checkQuestionNumber();
							txtQuestion.setText(null);
							txtA.setText(null);
							txtB.setText(null);
							txtC.setText(null);
							txtD.setText(null);
							txtAns.setText(null);
						}
						else {
							//if(rs.next()) {
							if(rs.first()) {
								lblQuestionNo.setText(rs.getString(1));
								txtQuestion.setText(rs.getString(2));
								txtA.setText(rs.getString(3));
								txtB.setText(rs.getString(4));
								txtC.setText(rs.getString(5));
								txtD.setText(rs.getString(6));
								txtAns.setText(rs.getString(7));
							}
							else {
								JOptionPane.showMessageDialog(null, "Question isn't available!");
							}
						}
					}
					else if(lblSubject.getText().equals("BIOLOGY (ZOOLOGY & BOTANY)")) {
						pst = con.prepareStatement("select * from biology where Question_ID = ?");
						pst.setString(1, Id);
						ResultSet rs = pst.executeQuery();
						
						if(Id.equals("")) {
							checkQuestionNumber();
							txtQuestion.setText(null);
							txtA.setText(null);
							txtB.setText(null);
							txtC.setText(null);
							txtD.setText(null);
							txtAns.setText(null);
						}
						else {
							//if(rs.next()) {
							if(rs.first()) {
								lblQuestionNo.setText(rs.getString(1));
								txtQuestion.setText(rs.getString(2));
								txtA.setText(rs.getString(3));
								txtB.setText(rs.getString(4));
								txtC.setText(rs.getString(5));
								txtD.setText(rs.getString(6));
								txtAns.setText(rs.getString(7));
							}
							else {
								JOptionPane.showMessageDialog(null, "Question isn't available!");
							}
						}
					}
					else {
						pst = con.prepareStatement("select * from others where Question_ID = ?");
						pst.setString(1, Id);
						ResultSet rs = pst.executeQuery();
						
						if(Id.equals("")) {
							checkQuestionNumber();
							txtQuestion.setText(null);
							txtA.setText(null);
							txtB.setText(null);
							txtC.setText(null);
							txtD.setText(null);
							txtAns.setText(null);
						}
						else {
							//if(rs.next()) {
							if(rs.first()) {
								lblQuestionNo.setText(rs.getString(1));
								txtQuestion.setText(rs.getString(2));
								txtA.setText(rs.getString(3));
								txtB.setText(rs.getString(4));
								txtC.setText(rs.getString(5));
								txtD.setText(rs.getString(6));
								txtAns.setText(rs.getString(7));
							}
							else {
								JOptionPane.showMessageDialog(null, "Question isn't available!");
							}
						}
					}
					
				}
				catch(Exception ex) {
					
				}
			}
		});
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setBounds(424, 654, 129, 30);
		QuestionPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		lblNewLabel = new JLabel("Search Question by Question ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(380, 624, 233, 30);
		QuestionPanel.add(lblNewLabel);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(0, 153, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q_id, ques, opA, opB, opC, opD, ans;
					
					q_id = txtSearch.getText();
					ques = txtQuestion.getText();
					opA = txtA.getText();
					opB = txtB.getText();
					opC = txtC.getText();
					opD = txtD.getText();
					ans = txtAns.getText();
					
					if(lblSubject.getText().equals("COMPUTER SCIENCE AND ENGINEERING (CSE)")) {
						pst = con.prepareStatement("update cse set Question = ?, Option_A = ?, Option_B = ?, Option_C = ?, Option_D = ?, Answer = ? where Question_ID = ?");
						pst.setString(1, ques);
						pst.setString(2, opA);
						pst.setString(3, opB);
						pst.setString(4, opC);
						pst.setString(5, opD);
						pst.setString(6, ans);
						pst.setInt(7, Integer.parseInt(q_id));
						pst.executeUpdate();
					}
					else if(lblSubject.getText().equals("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)")) {
						pst = con.prepareStatement("update eee set Question = ?, Option_A = ?, Option_B = ?, Option_C = ?, Option_D = ?, Answer = ? where Question_ID = ?");
						pst.setString(1, ques);
						pst.setString(2, opA);
						pst.setString(3, opB);
						pst.setString(4, opC);
						pst.setString(5, opD);
						pst.setString(6, ans);
						pst.setInt(7, Integer.parseInt(q_id));
						pst.executeUpdate();
					}
					else if(lblSubject.getText().equals("BIOLOGY (ZOOLOGY & BOTANY)")) {
						pst = con.prepareStatement("update biology set Question = ?, Option_A = ?, Option_B = ?, Option_C = ?, Option_D = ?, Answer = ? where Question_ID = ?");
						pst.setString(1, ques);
						pst.setString(2, opA);
						pst.setString(3, opB);
						pst.setString(4, opC);
						pst.setString(5, opD);
						pst.setString(6, ans);
						pst.setInt(7, Integer.parseInt(q_id));
						pst.executeUpdate();
					}
					else {
						pst = con.prepareStatement("update others set Question = ?, Option_A = ?, Option_B = ?, Option_C = ?, Option_D = ?, Answer = ? where Question_ID = ?");
						pst.setString(1, ques);
						pst.setString(2, opA);
						pst.setString(3, opB);
						pst.setString(4, opC);
						pst.setString(5, opD);
						pst.setString(6, ans);
						pst.setInt(7, Integer.parseInt(q_id));
						pst.executeUpdate();
					}
					
					JOptionPane.showMessageDialog(null, "Updated Successfully!");
					txtQuestion.setText(null);
					txtA.setText(null);
					txtB.setText(null);
					txtC.setText(null);
					txtD.setText(null);
					txtAns.setText(null);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 25));
		btnUpdate.setBounds(453, 703, 160, 39);
		QuestionPanel.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(0, 153, 255));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = txtSearch.getText();
					
					if(lblSubject.getText().equals("COMPUTER SCIENCE AND ENGINEERING (CSE)")) {
						pst = con.prepareStatement("delete from cse where Question_ID = ? ");
						pst.setInt(1, Integer.parseInt(id));
						pst.executeUpdate();
					}
					else if(lblSubject.getText().equals("ELECTRIC AND ELECTRONICS ENGINEERING (EEE)")) {
						pst = con.prepareStatement("delete from eee where Question_ID = ? ");
						pst.setInt(1, Integer.parseInt(id));
						pst.executeUpdate();
					}
					else if(lblSubject.getText().equals("BIOLOGY (ZOOLOGY & BOTANY)")) {
						pst = con.prepareStatement("delete from bioloty where Question_ID = ? ");
						pst.setInt(1, Integer.parseInt(id));
						pst.executeUpdate();
					}
					else {
						pst = con.prepareStatement("delete from others where Question_ID = ? ");
						pst.setInt(1, Integer.parseInt(id));
						pst.executeUpdate();
					}
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					txtQuestion.setText(null);
					txtA.setText(null);
					txtB.setText(null);
					txtC.setText(null);
					txtD.setText(null);
					txtAns.setText(null);
				}
				catch(Exception ex) {
					
				}
			}
		});
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 25));
		btnDelete.setBounds(670, 703, 160, 39);
		QuestionPanel.add(btnDelete);
		
		lblq = new JLabel("");
		lblq.setBounds(0, 0, 991, 768);
		ImageIcon qimg = new ImageIcon(this.getClass().getResource("/quiz1.jpg"));
		Image qi = qimg.getImage().getScaledInstance(lblq.getWidth(), lblq.getHeight(), Image.SCALE_SMOOTH);
		qimg = new ImageIcon(qi);
		lblq.setIcon(qimg);
		QuestionPanel.add(lblq);
		/*_________________________________________________________________________________*/
		
		/*----------------------------- Delete Question Panel -----------------------------*/
		AllQuestionPanel = new JPanel();
		layeredPane.add(AllQuestionPanel, "name_272001641283300");		
		AllQuestionPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 172, 971, 462);
		AllQuestionPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblCSE = new JLabel("CSE QUESTIONS");
		lblCSE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cseQuestion();
			}
		});
		lblCSE.setHorizontalAlignment(SwingConstants.CENTER);
		lblCSE.setForeground(Color.CYAN);
		lblCSE.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCSE.setBounds(30, 46, 204, 24);
		AllQuestionPanel.add(lblCSE);
		
		lblEEE = new JLabel("EEE QUESTIONS");
		lblEEE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eeeQuestion();
			}
		});
		lblEEE.setHorizontalAlignment(SwingConstants.CENTER);
		lblEEE.setForeground(Color.CYAN);
		lblEEE.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblEEE.setBounds(264, 46, 204, 24);
		AllQuestionPanel.add(lblEEE);
		
		lblBiologyQuestions = new JLabel("BIOLOGY QUESTIONS");
		lblBiologyQuestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bioQuestion();
			}
		});
		lblBiologyQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiologyQuestions.setForeground(Color.CYAN);
		lblBiologyQuestions.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBiologyQuestions.setBounds(498, 46, 229, 24);
		AllQuestionPanel.add(lblBiologyQuestions);
		
		lblOtherQuestions = new JLabel("OTHER QUESTIONS");
		lblOtherQuestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				otherQuestion();
			}
		});
		lblOtherQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherQuestions.setForeground(Color.CYAN);
		lblOtherQuestions.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblOtherQuestions.setBounds(757, 46, 204, 24);
		AllQuestionPanel.add(lblOtherQuestions);
		
		lblAll = new JLabel("");
		lblAll.setBounds(0, 0, 991, 768);
		ImageIcon allimg = new ImageIcon(this.getClass().getResource("/quiz1.jpg"));
		Image ai = allimg.getImage().getScaledInstance(lblAll.getWidth(), lblAll.getHeight(), Image.SCALE_SMOOTH);
		allimg = new ImageIcon(ai);
		lblAll.setIcon(allimg);
		AllQuestionPanel.add(lblAll);
	}
}
