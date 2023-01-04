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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.java.account.Login;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;

public class Students extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelMenu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Students frame = new Students();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Students() {
		setLookAndFeel();
		initialize();
		connect();
		chk();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnResult;
	private JPanel panelStartQuiz;
	private JLayeredPane layeredPane;
	private JPanel panelHome;
	private JPanel panelProfile;
	private JPanel panelResult;
	private JTextField txtName;
	private JTextField txtID;
	private JTextField txtCollege;
	private JTextField txtUsername;
	private JPasswordField txtPass;
	private JTextField txtDept;
	private JButton btnEdit;
	private JLabel lblStartQuizTest;
	private JPanel panelViewProfile;
	private JLabel lblViewProfile;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JPanel panelViewResult;
	private JLabel lblViewResult;
	private JPanel panelSubjectWise;
	private JLabel lblSubjectWiseQuiz;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtWork;
	private JLabel btnLogout;
	private JLabel btnHelp;
	private JLabel btnHome;
	private JLabel btnCSE;
	private JLabel btnEEE;
	private JLabel btnBiology;
	private JLabel btnViewOtherResult;
	private JLabel lblpro;
	private JLabel lblimage;
	private JLabel lbltbl;
	private JLabel lblmenu;
	private JLabel btnRead;
	
	
	
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
	
	@SuppressWarnings("unused")
	private void chk() {
		com.java.account.Login lg = new com.java.account.Login();
		txtName.setText(com.java.account.Login.name1);
		txtID.setText(com.java.account.Login.id1);
		txtCollege.setText(com.java.account.Login.institute1);
		txtUsername.setText(com.java.account.Login.username1);
		txtPass.setText(com.java.account.Login.password1);
		txtDept.setText(com.java.account.Login.department1);
		txtWork.setText(com.java.account.Login.signUp1);
	}
	
	private void loadResult() {
		try {
			String id = com.java.account.Login.id1;
			pst = con.prepareStatement("select * from result where ID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table.getColumnModel().getColumn(i);
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
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table.setRowHeight(30);
		} 
		catch (Exception ex) {

		}
	}
	
	private void cseResult() {
		try {
			String id = com.java.account.Login.id1;
			pst = con.prepareStatement("select * from cseresult where ID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table.getColumnModel().getColumn(i);
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
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table.setRowHeight(30);
		} 
		catch (Exception ex) {

		}
	}
	
	private void eeeResult() {
		try {
			String id = com.java.account.Login.id1;
			pst = con.prepareStatement("select * from eeeresult where ID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table.getColumnModel().getColumn(i);
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
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table.setRowHeight(30);
		} 
		catch (Exception ex) {

		}
	}
	
	private void biologyResult() {
		try {
			String id = com.java.account.Login.id1;
			pst = con.prepareStatement("select * from biologyresult where ID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table.getColumnModel().getColumn(i);
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
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table.setRowHeight(30);
		} 
		catch (Exception ex) {

		}
	}
	
	private void othersResult() {
		try {
			String id = com.java.account.Login.id1;
			pst = con.prepareStatement("select * from otherresult where ID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

			table.setShowHorizontalLines(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
			    column = table.getColumnModel().getColumn(i);
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
			table.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			table.setRowHeight(30);
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
		contentPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
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
		contentPane.setLayout(null);
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
		panelMenu.setBounds(5, 4, 370, 759);
		panelMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null), new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0, 0), null, null, null)));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		panelStartQuiz = new JPanel();
		panelStartQuiz.setLayout(null);
		panelStartQuiz.setOpaque(false);
		panelStartQuiz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelStartQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelStartQuiz.setOpaque(true);
				panelStartQuiz.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelStartQuiz.setOpaque(false);
				panelStartQuiz.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				com.java.exam.Question q = new com.java.exam.Question();
				q.setVisible(true);
			}
		});
		panelStartQuiz.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelStartQuiz.setBackground(new Color(0, 0, 0, 0));
		panelStartQuiz.setBounds(0, 300, 370, 55);
		panelMenu.add(panelStartQuiz);
		
		lblStartQuizTest = new JLabel("START QUIZ TEST");
		lblStartQuizTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartQuizTest.setFont(new Font("Dialog", Font.BOLD, 22));
		lblStartQuizTest.setBounds(10, 11, 350, 33);
		panelStartQuiz.add(lblStartQuizTest);
		
		panelViewProfile = new JPanel();
		panelViewProfile.setLayout(null);
		panelViewProfile.setOpaque(false);
		panelViewProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelViewProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelViewProfile.setOpaque(true);
				panelViewProfile.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelViewProfile.setOpaque(false);
				panelViewProfile.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panelProfile);
			}
		});
		panelViewProfile.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelViewProfile.setBackground(new Color(0, 0, 0, 0));
		panelViewProfile.setBounds(0, 366, 370, 55);
		panelMenu.add(panelViewProfile);
		
		lblViewProfile = new JLabel("VIEW PROFILE");
		lblViewProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewProfile.setFont(new Font("Dialog", Font.BOLD, 22));
		lblViewProfile.setBounds(10, 11, 350, 33);
		panelViewProfile.add(lblViewProfile);
		
		panelViewResult = new JPanel();
		panelViewResult.setLayout(null);
		panelViewResult.setOpaque(false);
		panelViewResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelViewResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelViewResult.setOpaque(true);
				panelViewResult.setBackground(Color.decode("#0150BE"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelViewResult.setOpaque(false);
				panelViewResult.setBackground(new Color(0,0,0,0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				loadResult();
				switchPanels(panelResult);
			}
		});
		panelViewResult.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelViewResult.setBackground(new Color(0, 0, 0, 0));
		panelViewResult.setBounds(0, 432, 370, 55);
		panelMenu.add(panelViewResult);
		
		lblViewResult = new JLabel("VIEW RESULT");
		lblViewResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewResult.setFont(new Font("Dialog", Font.BOLD, 22));
		lblViewResult.setBounds(10, 11, 350, 33);
		panelViewResult.add(lblViewResult);
		
		panelSubjectWise = new JPanel();
		panelSubjectWise.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelSubjectWise.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelSubjectWise.setOpaque(true);
				panelSubjectWise.setBackground(Color.decode("#0150BE"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelSubjectWise.setOpaque(false);
				panelSubjectWise.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new com.java.subjects.SubjectWise().setVisible(true);
			}
		});
		panelSubjectWise.setLayout(null);
		panelSubjectWise.setOpaque(false);
		panelSubjectWise.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0,0,0,0), null, null, null));
		panelSubjectWise.setBackground(new Color(0, 0, 0, 0));
		panelSubjectWise.setBounds(0, 498, 370, 55);
		panelMenu.add(panelSubjectWise);
		
		lblSubjectWiseQuiz = new JLabel("SUBJECT WISE QUIZ");
		lblSubjectWiseQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectWiseQuiz.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSubjectWiseQuiz.setBounds(10, 11, 350, 33);
		panelSubjectWise.add(lblSubjectWiseQuiz);
		
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
		btnLogout.setBounds(284, 686, 60, 60);
		ImageIcon logoutimage = new ImageIcon(this.getClass().getResource("/logout.png"));
		Image image = logoutimage.getImage().getScaledInstance(btnLogout.getWidth(), btnLogout.getHeight(), Image.SCALE_SMOOTH);
		logoutimage = new ImageIcon(image);
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
		btnHelp.setBounds(198, 686, 60, 60);
		ImageIcon helpimage = new ImageIcon(this.getClass().getResource("/help.png"));
		Image image1 = helpimage.getImage().getScaledInstance(btnHelp.getWidth(), btnHelp.getHeight(), Image.SCALE_SMOOTH);
		helpimage = new ImageIcon(image1);
		btnHelp.setIcon(helpimage);
		panelMenu.add(btnHelp);
		
		btnHome = new JLabel("");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanels(panelHome);
			}
		});
		btnHome.setToolTipText("Home");
		btnHome.setBounds(112, 686, 60, 60);
		ImageIcon homeimage = new ImageIcon(this.getClass().getResource("/home.png"));
		Image image2 = homeimage.getImage().getScaledInstance(btnHome.getWidth(), btnHome.getHeight(), Image.SCALE_SMOOTH);
		homeimage = new ImageIcon(image2);
		btnHome.setIcon(homeimage);
		panelMenu.add(btnHome);
		
		btnRead = new JLabel("");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new com.java.study.Study().setVisible(true);
			}
		});
		btnRead.setToolTipText("General Knowledge");
		btnRead.setBounds(26, 686, 60, 60);
		ImageIcon readimage = new ImageIcon(this.getClass().getResource("/history.png"));
		Image img4 = readimage.getImage().getScaledInstance(btnRead.getWidth(), btnRead.getHeight(), Image.SCALE_SMOOTH);
		readimage = new ImageIcon(img4);
		btnRead.setIcon(readimage);
		panelMenu.add(btnRead);
		
		lblmenu = new JLabel("");
		lblmenu.setBounds(0, 0, 370, 759);
		ImageIcon menuimg = new ImageIcon(this.getClass().getResource("/best4.jpg"));
		Image mig = menuimg.getImage().getScaledInstance(lblmenu.getWidth(), lblmenu.getHeight(), Image.SCALE_SMOOTH);
		menuimg = new ImageIcon(mig);
		lblmenu.setIcon(menuimg);
		panelMenu.add(lblmenu);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(376, 0, 990, 768);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panelHome = new JPanel();
		layeredPane.add(panelHome, "name_671791341500");
		panelHome.setLayout(null);
		
		lblpro = new JLabel("");
		lblpro.setBounds(104, 0, 990, 768);
		lblpro.setBounds(0, 0, 991, 768);
		ImageIcon pro = new ImageIcon(this.getClass().getResource("/book9.jpg"));
		Image p = pro.getImage().getScaledInstance(lblpro.getWidth(), lblpro.getHeight(), Image.SCALE_SMOOTH);
		pro = new ImageIcon(p);
		lblpro.setIcon(pro);
		panelHome.add(lblpro);
		
		panelProfile = new JPanel();
		panelProfile.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		layeredPane.add(panelProfile, "name_674833851500");
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
					
					pst = con.prepareStatement("update student set Name = ?, ID = ?, Institution = ?, Department = ?, Designation = ?, Username =?, Password = ? where Username = ? and Password = ?");
					
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
						pst = con.prepareStatement("delete from student where Username = ? and Password = ?");
						
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
		
		lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 990, 768);
		lblpro.setBounds(0, 0, 991, 768);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/book9.jpg"));
		Image img = icon.getImage().getScaledInstance(lblpro.getWidth(), lblpro.getHeight(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		lblimage.setIcon(icon);
		panelProfile.add(lblimage);
		
		panelResult = new JPanel();
		layeredPane.add(panelResult, "name_693477211600");
		panelResult.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 970, 365);
		panelResult.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnCSE = new JLabel("View CSE Result");
		btnCSE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cseResult();
			}
		});
		btnCSE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCSE.setForeground(Color.CYAN);
		btnCSE.setHorizontalAlignment(SwingConstants.CENTER);
		btnCSE.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCSE.setBounds(89, 56, 135, 19);
		panelResult.add(btnCSE);
		
		btnEEE = new JLabel("View EEE Result");
		btnEEE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eeeResult();
			}
		});
		btnEEE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEEE.setForeground(Color.CYAN);
		btnEEE.setHorizontalAlignment(SwingConstants.CENTER);
		btnEEE.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnEEE.setBounds(313, 56, 135, 19);
		panelResult.add(btnEEE);
		
		btnBiology = new JLabel("View Biology Result");
		btnBiology.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				biologyResult();
			}
		});
		btnBiology.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBiology.setForeground(Color.CYAN);
		btnBiology.setHorizontalAlignment(SwingConstants.CENTER);
		btnBiology.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnBiology.setBounds(537, 56, 140, 19);
		panelResult.add(btnBiology);
		
		btnViewOtherResult = new JLabel("View Other Result");
		btnViewOtherResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				othersResult();
			}
		});
		btnViewOtherResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewOtherResult.setForeground(Color.CYAN);
		btnViewOtherResult.setHorizontalAlignment(SwingConstants.CENTER);
		btnViewOtherResult.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnViewOtherResult.setBounds(766, 56, 135, 19);
		panelResult.add(btnViewOtherResult);
		
		lbltbl = new JLabel("");
		lbltbl.setBounds(0, 0, 990, 768);
		lblpro.setBounds(0, 0, 991, 768);
		ImageIcon tbl = new ImageIcon(this.getClass().getResource("/book9.jpg"));
		Image t = tbl.getImage().getScaledInstance(lbltbl.getWidth(), lbltbl.getHeight(), Image.SCALE_SMOOTH);
		tbl = new ImageIcon(t);
		lbltbl.setIcon(tbl);
		panelResult.add(lbltbl);
	}
}
