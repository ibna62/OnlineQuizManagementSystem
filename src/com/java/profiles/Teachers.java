package com.java.profiles;

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
import javax.swing.JPasswordField;
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

public class Teachers extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelAddQuestion;
	private JPanel panelMenu;
	private JLabel btnAddQuestion;
	private JPanel panelEditQuestion;
	private JLabel btnEditQuestion;
	private JPanel panelAllQuestion;
	private JLabel btnAllQuestion;
	private JPanel panelStudentResult;
	private JLabel btnStudentResult;
	private JPanel HomePage;
	private JPanel AddQuestionPanel;
	private JLayeredPane layeredPane;
	private JPanel EditQuestionPanel;
	private JPanel AllQuestionPanel;
	private JPanel StudentResultPanel;
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
					Teachers frame = new Teachers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Teachers() {
		setLookAndFeel();
		initialize();
		connect();
		checkQuestionNumber();
		//questionLoad();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//int quesNo = 0;
	
	private JTextField txtAns;
	private JLabel lblNewLabel;
	private JLabel lblQuestionNo;
	private JTextField txtSearch;
	private JLabel lblNewLabel_1;
	private JButton btnUpdate;
	private JTextArea updQuestion;
	private JTextArea updA;
	private JTextArea updB;
	private JTextArea updC;
	private JTextArea updD;
	private JTextField updAns;
	private JButton btnDelete;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblimage;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JPanel panelAddQuestion_1;
	private JPanel panelProfile;
	private JTextField txtName;
	private JTextField txtID;
	private JTextField txtCollege;
	private JTextField txtUsername;
	private JPasswordField txtPass;
	private JTextField txtDept;
	private JButton btnEdit;
	private JTextField txtWork;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JLabel btnLogout;
	private JLabel btnHelp;
	private JLabel btnHome;
	private JLabel lblmenuImage;
	private JLabel imgQues;
	private JLabel lbleditimg;
	private JLabel lbltimage;
	private JLabel lblrimg;
	private JLabel lblpro;
	
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
			pst = con.prepareStatement("select count(Question_ID) from questions");
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
		catch(Exception ex) {
			
		}
	}
	
	private void questionLoad() {
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
	
	private void loadResult() {
		try {
			pst = con.prepareStatement("select * from result");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));

			table_1.setShowHorizontalLines(true);
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table_1.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(360);
			    }
			    if (i == 1) {
			        column.setPreferredWidth(150);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(320);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(150);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(100);
			    }
			}
			table_1.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table_1.setRowHeight(30);
		} 
		catch (Exception ex) {

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
		
		panelAddQuestion = new JPanel();
		panelAddQuestion.setOpaque(false);
		panelAddQuestion.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelAddQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAddQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelAddQuestion.setOpaque(true);
				panelAddQuestion.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAddQuestion.setOpaque(false);
				panelAddQuestion.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//checkQuestionNumber();
				switchPanels(AddQuestionPanel);
			}
		});
		panelAddQuestion.setBackground(new Color(0,0,0,0));
		panelAddQuestion.setBounds(0, 186, 370, 55);
		panelMenu.add(panelAddQuestion);
		panelAddQuestion.setLayout(null);
		
		btnAddQuestion = new JLabel("ADD NEW QUESTION");
		btnAddQuestion.setForeground(Color.WHITE);
		btnAddQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		btnAddQuestion.setBounds(10, 11, 350, 33);
		panelAddQuestion.add(btnAddQuestion);
		
		panelEditQuestion = new JPanel();
		panelEditQuestion.setLayout(null);
		panelEditQuestion.setOpaque(false);
		panelEditQuestion.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelEditQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelEditQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelEditQuestion.setOpaque(true);
				panelEditQuestion.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelEditQuestion.setOpaque(false);
				panelEditQuestion.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(EditQuestionPanel);
			}
		});
		panelEditQuestion.setBackground(new Color(0,0,0,0));
		panelEditQuestion.setBounds(0, 236, 370, 55);
		panelMenu.add(panelEditQuestion);
		
		btnEditQuestion = new JLabel("EDIT QUESTION");
		btnEditQuestion.setForeground(Color.WHITE);
		btnEditQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		btnEditQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		btnEditQuestion.setBounds(10, 11, 350, 33);
		panelEditQuestion.add(btnEditQuestion);
		
		panelAllQuestion = new JPanel();
		panelAllQuestion.setLayout(null);
		panelAllQuestion.setOpaque(false);
		panelAllQuestion.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelAllQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAllQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelAllQuestion.setOpaque(true);
				panelAllQuestion.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAllQuestion.setOpaque(false);
				panelAllQuestion.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				questionLoad();
				switchPanels(AllQuestionPanel);
			}
		});
		panelAllQuestion.setBackground(new Color(0, 0, 0, 0));
		panelAllQuestion.setBounds(0, 302, 370, 55);
		panelMenu.add(panelAllQuestion);
		
		btnAllQuestion = new JLabel("ALL QUESTIONS");
		btnAllQuestion.setForeground(Color.WHITE);
		btnAllQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		btnAllQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		btnAllQuestion.setBounds(10, 11, 350, 33);
		panelAllQuestion.add(btnAllQuestion);
		
		panelStudentResult = new JPanel();
		panelStudentResult.setLayout(null);
		panelStudentResult.setOpaque(false);
		panelStudentResult.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelStudentResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelStudentResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelStudentResult.setOpaque(true);
				panelStudentResult.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelStudentResult.setOpaque(false);
				panelStudentResult.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				loadResult();
				switchPanels(StudentResultPanel);
			}
		});
		panelStudentResult.setBackground(new Color(0, 0, 0, 0));
		panelStudentResult.setBounds(0, 368, 370, 55);
		panelMenu.add(panelStudentResult);
		
		btnStudentResult = new JLabel("STUDENT'S RESULT");
		btnStudentResult.setForeground(Color.WHITE);
		btnStudentResult.setHorizontalAlignment(SwingConstants.CENTER);
		btnStudentResult.setFont(new Font("Dialog", Font.BOLD, 22));
		btnStudentResult.setBounds(10, 11, 350, 33);
		panelStudentResult.add(btnStudentResult);
		
		panelAddQuestion_1 = new JPanel();
		panelAddQuestion_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAddQuestion_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelAddQuestion_1.setOpaque(true);
				panelAddQuestion_1.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelAddQuestion_1.setOpaque(false);
				panelAddQuestion_1.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panelProfile);
			}
		});
		panelAddQuestion_1.setLayout(null);
		panelAddQuestion_1.setOpaque(false);
		panelAddQuestion_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelAddQuestion_1.setBackground(new Color(0, 0, 0, 0));
		panelAddQuestion_1.setBounds(0, 120, 370, 55);
		panelMenu.add(panelAddQuestion_1);
		
		JLabel lblViewProfile = new JLabel("VIEW PROFILE");
		lblViewProfile.setForeground(Color.WHITE);
		lblViewProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewProfile.setFont(new Font("Dialog", Font.BOLD, 22));
		lblViewProfile.setBounds(10, 11, 350, 33);
		panelAddQuestion_1.add(lblViewProfile);
		
		lblmenuImage = new JLabel("");
		lblmenuImage.setBounds(0, 0, 370, 759);
		ImageIcon menuimage = new ImageIcon(this.getClass().getClassLoader().getResource("best.jpg"));
		Image mim = menuimage.getImage().getScaledInstance(lblmenuImage.getWidth(), lblmenuImage.getHeight(), Image.SCALE_SMOOTH);
		menuimage = new ImageIcon(mim);
		lblmenuImage.setIcon(menuimage);
		panelMenu.add(lblmenuImage);
		
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
		AddQuestionPanel = new JPanel();
		AddQuestionPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		layeredPane.add(AddQuestionPanel, "name_248226911218800");
		AddQuestionPanel.setLayout(null);
		
		txtQuestion = new JTextArea();
		txtQuestion.setForeground(Color.WHITE);
		txtQuestion.setWrapStyleWord(true);
		txtQuestion.setLineWrap(true);
		txtQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		txtQuestion.setBounds(87, 188, 839, 82);
		txtQuestion.putClientProperty( "JComponent.roundRect", true );
		AddQuestionPanel.add(txtQuestion);
		
		txtA = new JTextArea();
		txtA.setForeground(Color.WHITE);
		txtA.setWrapStyleWord(true);
		txtA.setLineWrap(true);
		txtA.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtA.setBounds(87, 345, 369, 88);
		AddQuestionPanel.add(txtA);
		
		txtB = new JTextArea();
		txtB.setForeground(Color.WHITE);
		txtB.setWrapStyleWord(true);
		txtB.setLineWrap(true);
		txtB.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtB.setBounds(557, 345, 369, 88);
		AddQuestionPanel.add(txtB);
		
		txtC = new JTextArea();
		txtC.setForeground(Color.WHITE);
		txtC.setWrapStyleWord(true);
		txtC.setLineWrap(true);
		txtC.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtC.setBounds(87, 444, 369, 88);
		AddQuestionPanel.add(txtC);
		
		txtD = new JTextArea();
		txtD.setForeground(Color.WHITE);
		txtD.setWrapStyleWord(true);
		txtD.setLineWrap(true);
		txtD.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtD.setBounds(557, 444, 369, 88);
		AddQuestionPanel.add(txtD);
		
		btnSubmit = new JButton("SUBMIT");
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
						pst = con.prepareStatement("insert into questions(Question_ID, Question, Option_A, Option_B, Option_C, Option_D, Answer)values(?,?,?,?,?,?,?)");
						pst.setInt(1, quesNo);
						pst.setString(2, question);
						pst.setString(3, opA);
						pst.setString(4, opB);
						pst.setString(5, opC);
						pst.setString(6, opD);
						pst.setString(7, ans);

						pst.executeUpdate();

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
		btnSubmit.setBounds(380, 693, 233, 39);
		AddQuestionPanel.add(btnSubmit);
		
		txtAns = new JTextField();
		txtAns.setForeground(Color.WHITE);
		txtAns.setHorizontalAlignment(SwingConstants.CENTER);
		txtAns.setOpaque(false);
		txtAns.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtAns.setBounds(218, 579, 91, 39);
		AddQuestionPanel.add(txtAns);
		txtAns.setColumns(10);
		
		lblNewLabel = new JLabel("Question");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(87, 121, 91, 47);
		AddQuestionPanel.add(lblNewLabel);
		
		lblQuestionNo = new JLabel("00");
		lblQuestionNo.setForeground(Color.WHITE);
		lblQuestionNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestionNo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblQuestionNo.setBounds(183, 121, 91, 47);
		AddQuestionPanel.add(lblQuestionNo);
		
		lblNewLabel_2 = new JLabel("A");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(36, 345, 46, 88);
		AddQuestionPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("C");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_3.setBounds(36, 444, 46, 88);
		AddQuestionPanel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("B");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_4.setBounds(507, 345, 46, 88);
		AddQuestionPanel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("D");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_5.setBounds(507, 444, 46, 88);
		AddQuestionPanel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Answer");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(87, 579, 121, 39);
		AddQuestionPanel.add(lblNewLabel_6);
		
		imgQues = new JLabel("");
		imgQues.setBounds(0, 0, 991, 768);
		ImageIcon design = new ImageIcon(this.getClass().getResource("/quiz7.jpg"));
		Image q = design.getImage().getScaledInstance(imgQues.getWidth(), imgQues.getHeight(), Image.SCALE_SMOOTH);
		design = new ImageIcon(q);
		imgQues.setIcon(design);
		AddQuestionPanel.add(imgQues);
		/*_________________________________________________________________________________*/
		
		/*----------------------------- Update Question Panel -----------------------------*/
		EditQuestionPanel = new JPanel();
		layeredPane.add(EditQuestionPanel, "name_271993377097300");	
		EditQuestionPanel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSearch.setForeground(Color.WHITE);
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String Id = txtSearch.getText();
					pst = con.prepareStatement("select * from questions where Question_ID = ?");
					pst.setString(1, Id);
					ResultSet rs = pst.executeQuery();
					
					if(Id.equals("")) {
						updQuestion.setText(null);
						updA.setText(null);
						updB.setText(null);
						updC.setText(null);
						updD.setText(null);
						updAns.setText(null);
					}
					else {
						//if(rs.next()) {
						if(rs.first()) {
							updQuestion.setText(rs.getString(2));
							updA.setText(rs.getString(3));
							updB.setText(rs.getString(4));
							updC.setText(rs.getString(5));
							updD.setText(rs.getString(6));
							updAns.setText(rs.getString(7));
						}
						else {
							JOptionPane.showMessageDialog(null, "Question isn't available!");
						}
					}
				}
				catch(Exception ex) {
					
				}
			}
		});
		txtSearch.setBounds(265, 77, 91, 28);
		EditQuestionPanel.add(txtSearch);
		txtSearch.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Question Number");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setBounds(60, 73, 179, 28);
		EditQuestionPanel.add(lblNewLabel_1);
		
		updQuestion = new JTextArea();
		updQuestion.setForeground(Color.WHITE);
		updQuestion.setLineWrap(true);
		updQuestion.setWrapStyleWord(true);
		updQuestion.setFont(new Font("Dialog", Font.BOLD, 22));
		updQuestion.setBounds(60, 151, 830, 98);
		EditQuestionPanel.add(updQuestion);
		
		updA = new JTextArea();
		updA.setForeground(Color.WHITE);
		updA.setLineWrap(true);
		updA.setWrapStyleWord(true);
		updA.setFont(new Font("Dialog", Font.PLAIN, 22));
		updA.setBounds(60, 288, 369, 88);
		EditQuestionPanel.add(updA);
		
		updB = new JTextArea();
		updB.setForeground(Color.WHITE);
		updB.setLineWrap(true);
		updB.setWrapStyleWord(true);
		updB.setFont(new Font("Dialog", Font.PLAIN, 22));
		updB.setBounds(521, 288, 369, 88);
		EditQuestionPanel.add(updB);
		
		updD = new JTextArea();
		updD.setForeground(Color.WHITE);
		updD.setLineWrap(true);
		updD.setWrapStyleWord(true);
		updD.setFont(new Font("Dialog", Font.PLAIN, 22));
		updD.setBounds(521, 400, 369, 88);
		EditQuestionPanel.add(updD);
		
		updC = new JTextArea();
		updC.setForeground(Color.WHITE);
		updC.setLineWrap(true);
		updC.setWrapStyleWord(true);
		updC.setFont(new Font("Dialog", Font.PLAIN, 22));
		updC.setBounds(60, 400, 369, 88);
		EditQuestionPanel.add(updC);
		
		updAns = new JTextField();
		updAns.setHorizontalAlignment(SwingConstants.CENTER);
		updAns.setForeground(Color.WHITE);
		updAns.setFont(new Font("Dialog", Font.PLAIN, 20));
		updAns.setColumns(10);
		updAns.setBounds(179, 521, 91, 28);
		EditQuestionPanel.add(updAns);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(0, 153, 255));
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 22));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q_id, ques, opA, opB, opC, opD, ans;
					
					q_id = txtSearch.getText();
					ques = updQuestion.getText();
					opA = updA.getText();
					opB = updB.getText();
					opC = updC.getText();
					opD = updD.getText();
					ans = updAns.getText();
					
					pst = con.prepareStatement("update questions set Question = ?, Option_A = ?, Option_B = ?, Option_C = ?, Option_D = ?, Answer = ? where Question_ID = ?");
					pst.setString(1, ques);
					pst.setString(2, opA);
					pst.setString(3, opB);
					pst.setString(4, opC);
					pst.setString(5, opD);
					pst.setString(6, ans);
					pst.setInt(7, Integer.parseInt(q_id));
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Updated Successfully!");
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(339, 652, 140, 33);
		EditQuestionPanel.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(0, 153, 255));
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 22));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = txtSearch.getText();
					pst = con.prepareStatement("delete from questions where Question_ID = ? ");
					pst.setInt(1, Integer.parseInt(id));
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
				}
				catch(Exception ex) {
					
				}
			}
		});
		btnDelete.setBounds(510, 652, 140, 33);
		EditQuestionPanel.add(btnDelete);
		
		JLabel lblNewLabel_7 = new JLabel("Answer");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(60, 521, 117, 28);
		EditQuestionPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("A");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_8.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(10, 288, 46, 88);
		EditQuestionPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("B");
		lblNewLabel_8_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1.setForeground(Color.WHITE);
		lblNewLabel_8_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_8_1.setBounds(470, 288, 46, 88);
		EditQuestionPanel.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8_2 = new JLabel("C");
		lblNewLabel_8_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_8_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_2.setForeground(Color.WHITE);
		lblNewLabel_8_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_8_2.setBounds(10, 400, 46, 88);
		EditQuestionPanel.add(lblNewLabel_8_2);
		
		JLabel lblNewLabel_8_3 = new JLabel("D");
		lblNewLabel_8_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_8_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_3.setForeground(Color.WHITE);
		lblNewLabel_8_3.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_8_3.setBounds(470, 400, 46, 88);
		EditQuestionPanel.add(lblNewLabel_8_3);
		
		lbleditimg = new JLabel("");
		lbleditimg.setBounds(0, 0, 991, 768);
		imgQues.setBounds(0, 0, 991, 768);
		ImageIcon edit = new ImageIcon(this.getClass().getResource("/quiz7.jpg"));
		Image ed = edit.getImage().getScaledInstance(lbleditimg.getWidth(), lbleditimg.getHeight(), Image.SCALE_SMOOTH);
		edit = new ImageIcon(ed);
		lbleditimg.setIcon(edit);
		EditQuestionPanel.add(lbleditimg);
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
		
		lbltimage = new JLabel("");
		lbltimage.setBounds(0, 0, 991, 768);
		ImageIcon tbl = new ImageIcon(this.getClass().getResource("/quiz7.jpg"));
		Image t = tbl.getImage().getScaledInstance(lbltimage.getWidth(), lbltimage.getHeight(), Image.SCALE_SMOOTH);
		tbl = new ImageIcon(t);
		lbltimage.setIcon(tbl);
		AllQuestionPanel.add(lbltimage);
		/*_________________________________________________________________________________*/
		
		/*----------------------------- Student Result Panel -----------------------------*/
		StudentResultPanel = new JPanel();
		layeredPane.add(StudentResultPanel, "name_272007106950900");
		StudentResultPanel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 171, 970, 365);
		StudentResultPanel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		lblrimg = new JLabel("");
		lblrimg.setBounds(0, 0, 991, 768);
		ImageIcon str = new ImageIcon(this.getClass().getResource("/quiz7.jpg"));
		Image ts = str.getImage().getScaledInstance(lblrimg.getWidth(), lblrimg.getHeight(), Image.SCALE_SMOOTH);
		str = new ImageIcon(ts);
		lblrimg.setIcon(str);
		StudentResultPanel.add(lblrimg);
		
		panelProfile = new JPanel();
		layeredPane.add(panelProfile, "name_111802299985400");
		panelProfile.setLayout(null);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setForeground(Color.WHITE);
		txtName.setOpaque(false);
		txtName.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtName.setBounds(72, 204, 397, 32);
		panelProfile.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel.setBounds(72, 179, 289, 24);
		panelProfile.add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblId.setBounds(72, 288, 289, 24);
		panelProfile.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setOpaque(false);
		txtID.setForeground(Color.WHITE);
		txtID.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtID.setColumns(10);
		txtID.setBounds(72, 313, 397, 32);
		panelProfile.add(txtID);
		
		JLabel lblCollegeinstitution = new JLabel("College/Institution");
		lblCollegeinstitution.setForeground(Color.WHITE);
		lblCollegeinstitution.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblCollegeinstitution.setBounds(72, 403, 289, 24);
		panelProfile.add(lblCollegeinstitution);
		
		txtCollege = new JTextField();
		txtCollege.setEditable(false);
		txtCollege.setOpaque(false);
		txtCollege.setForeground(Color.WHITE);
		txtCollege.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtCollege.setColumns(10);
		txtCollege.setBounds(72, 428, 397, 32);
		panelProfile.add(txtCollege);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblUsername.setBounds(536, 179, 289, 24);
		panelProfile.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setOpaque(false);
		txtUsername.setForeground(Color.WHITE);
		txtUsername.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtUsername.setColumns(10);
		txtUsername.setBounds(536, 204, 397, 32);
		panelProfile.add(txtUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPassword.setBounds(536, 288, 289, 24);
		panelProfile.add(lblPassword);
		
		txtPass = new JPasswordField();
		txtPass.setEditable(false);
		txtPass.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtPass.setBounds(536, 313, 397, 32);
		panelProfile.add(txtPass);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setForeground(Color.WHITE);
		lblDepartment.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblDepartment.setBounds(536, 403, 289, 24);
		panelProfile.add(lblDepartment);
		
		txtDept = new JTextField();
		txtDept.setEditable(false);
		txtDept.setOpaque(false);
		txtDept.setForeground(Color.WHITE);
		txtDept.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtDept.setColumns(10);
		txtDept.setBounds(536, 428, 397, 32);
		panelProfile.add(txtDept);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setEditable(true);
				txtID.setEditable(true);
				txtCollege.setEditable(true);
				txtUsername.setEditable(true);
				txtPass.setEditable(true);
				txtDept.setEditable(true);
			}
		});
		btnEdit.setBackground(new Color(51, 51, 255));
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 24));
		btnEdit.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		btnEdit.setBounds(247, 627, 150, 32);
		panelProfile.add(btnEdit);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name, id, inst, dep, work, username, pass, luser, lpass;
					
					luser = com.java.account.Login.user;
					lpass = com.java.account.Login.pass;					
					name = txtName.getText();
					id = txtID.getText();
					inst = txtCollege.getText();
					dep = txtDept.getText();
					work = txtWork.getText();
					username = txtUsername.getText();
					pass = txtPass.getText();
					
					pst = con.prepareStatement("update teacher set Name = ?, ID = ?, Institution = ?, Department = ?, Designation = ?, Username =?, Password = ? where Username = ? and Password = ?");
					
					pst.setString(1, name);
					pst.setString(2, id);
					pst.setString(3, inst);
					pst.setString(4, dep);
					pst.setString(5, work);
					pst.setString(6, username);
					pst.setString(7, pass);
					pst.setString(8, luser);
					pst.setString(9, lpass);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Account Updated Successfully");
				}
				catch(Exception ex) {
					
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 24));
		btnUpdate.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		btnUpdate.setBackground(new Color(51, 51, 255));
		btnUpdate.setBounds(443, 627, 150, 32);
		panelProfile.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to delete your account?","Warning!",JOptionPane.YES_NO_OPTION)==0) {
					String luser,lpass;
					
					luser = com.java.account.Login.user;
					lpass = com.java.account.Login.pass;
					
					try {
						pst = con.prepareStatement("delete from teacher where Username = ? and Password = ?");
						
						pst.setString(1, luser);
						pst.setString(2, lpass);
						
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
						dispose();
						new com.java.account.Login().setVisible(true);
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 24));
		btnDelete.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		btnDelete.setBackground(new Color(51, 51, 255));
		btnDelete.setBounds(642, 627, 150, 32);
		panelProfile.add(btnDelete);
		
		txtWork = new JTextField();
		txtWork.setVisible(false);
		txtWork.setText((String) null);
		txtWork.setOpaque(false);
		txtWork.setForeground(Color.WHITE);
		txtWork.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtWork.setEditable(false);
		txtWork.setColumns(10);
		txtWork.setBounds(293, 69, 397, 32);
		panelProfile.add(txtWork);
		
		lblpro = new JLabel("");
		lblpro.setBounds(0, 0, 991, 768);
		ImageIcon pro = new ImageIcon(this.getClass().getResource("/quiz7.jpg"));
		Image p = pro.getImage().getScaledInstance(lblpro.getWidth(), lblpro.getHeight(), Image.SCALE_SMOOTH);
		pro = new ImageIcon(p);
		lblpro.setIcon(pro);
		panelProfile.add(lblpro);
	}
}
