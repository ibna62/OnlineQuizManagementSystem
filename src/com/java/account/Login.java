package com.java.account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.CompoundBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class Login extends JFrame {
	private String[] img = { "/demo1.jpg", "/demo2.jpg", "/demo3.jpg", "/demo4.jpg", "/demo5.jpg", "/demo6.jpg", "/demo7.jpg" };
	
	private JPanel contentPane;
	private JPanel LoginPanel;
	private JPanel panelpic;
	private JLabel slideshow;
	private JPanel paneluser;
	private JTextField txtUserName;
	private JTextField userPlaceHolder;
	private JLabel userPic;
	private JTextField passPlaceHolder;
	private JPasswordField passField;
	private JPanel panelpass;
	private JLabel passPic;
	private JLabel showPass;
	private JLabel hidePass;
	private JPanel paneluser_1;
	private JLabel workingPic;
	private JComboBox loginBox;
	private JLabel lblNewLabel;
	private JLabel lblsignup;
	private JLabel lblNewLabel_1;
	private JLabel loginImage;
	private JLabel lblimg;
	private JButton btnLogin;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setLookAndFeel();
		initialize();
		connect();
		slideShow();
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
	
	public void slideShow() {
		Timer timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = (int) Math.floor(Math.random() * 7);
				String image = img[n];
				ImageIcon icon = new ImageIcon(this.getClass().getResource(image));
				Image img1 = icon.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
				icon = new ImageIcon(img1);
				slideshow.setIcon(icon);
			}
		});
		timer.start();
	}
	
	private void setLookAndFeel() {
		try {
			FlatDarkLaf.setup();
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
	
	public static String name1, id1, institute1, username1, password1, signUp1, department1;
	public static String user,pass,work;

	private void initialize() {
		setUndecorated(true);
		setDefaultLookAndFeelDecorated(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelExit = new JPanel();
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
		panelExit.setBounds(1340, 8, 13, 13);
		contentPane.add(panelExit);
		panelExit.setLayout(null);

		lblExit = new JLabel("x");
		lblExit.setBorder(null);
		lblExit.setFont(new Font("Corbel", Font.BOLD, 15));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(0, 1, 13, 13);
		panelExit.add(lblExit);

		panelMinimize = new JPanel();
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
		panelMinimize.setBounds(1300, 8, 16, 14);
		contentPane.add(panelMinimize);

		lblMinimize = new JLabel("-");
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setFont(new Font("Colonna MT", Font.BOLD, 30));
		lblMinimize.setBorder(null);
		lblMinimize.setBounds(0, 0, 14, 13);
		panelMinimize.add(lblMinimize);
		
		LoginPanel = new JPanel();
		LoginPanel.setBounds(884, 145, 472, 623);
		LoginPanel.setBackground(new Color(0, 0, 51));
		contentPane.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		panelpic = new JPanel();
		panelpic.setBounds(0, 145, 883, 623);
		panelpic.setOpaque(false);
		panelpic.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		panelpic.setBackground(new Color(51, 0, 102));
		contentPane.add(panelpic);
		panelpic.setLayout(null);
		
		slideshow = new JLabel("");
		slideshow.setBounds(10, 11, 863, 601);
		slideshow.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		ImageIcon slideImage = new ImageIcon(this.getClass().getClassLoader().getResource("Book29.jpg"));
		Image si = slideImage.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
		slideImage = new ImageIcon(si);
		slideshow.setIcon(slideImage);
		panelpic.add(slideshow);
		
		paneluser = new JPanel();
		paneluser.setOpaque(false);
		paneluser.setBounds(0, 100, 471, 41);
		LoginPanel.add(paneluser);
		paneluser.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setCaretColor(Color.WHITE);
		txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					userPlaceHolder.setVisible(false);
					userPlaceHolder.setEnabled(false);
			    }
			    else if(txtUserName.getText().equals("")) {
			    	userPlaceHolder.setVisible(true);
			    	userPlaceHolder.setEnabled(false);
			    }
			}
		});
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUserName.setForeground(Color.WHITE);
		txtUserName.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 153, 255)));
		txtUserName.setOpaque(false);
		txtUserName.setBounds(10, 10, 402, 31);
		paneluser.add(txtUserName);
		txtUserName.setColumns(10);
		
		userPlaceHolder = new JTextField();
		userPlaceHolder.setDisabledTextColor(Color.GRAY);
		userPlaceHolder.setOpaque(false);
		userPlaceHolder.setBorder(null);
		userPlaceHolder.setEnabled(false);
		userPlaceHolder.setEditable(false);
		userPlaceHolder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userPlaceHolder.setForeground(Color.DARK_GRAY);
		userPlaceHolder.setText("Enter Username");
		userPlaceHolder.setBounds(11, 8, 402, 31);
		paneluser.add(userPlaceHolder);
		userPlaceHolder.setColumns(10);
		
		userPic = new JLabel("");
		userPic.setBounds(422, 2, 44, 39);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/user.png"));
		Image img2 = ii.getImage().getScaledInstance(userPic.getWidth(), userPic.getHeight(), Image.SCALE_SMOOTH);
		ii = new ImageIcon(img2);
		userPic.setIcon(ii);
		paneluser.add(userPic);
		
		panelpass = new JPanel();
		panelpass.setLayout(null);
		panelpass.setOpaque(false);
		panelpass.setBounds(0, 160, 471, 41);
		LoginPanel.add(panelpass);
		
		passField = new JPasswordField();
		passField.setEchoChar('•');
		passField.setCaretColor(Color.WHITE);
		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					passPlaceHolder.setVisible(false);
					passPlaceHolder.setEnabled(false);
			    }
			    else if(passField.getText().equals("")) {
			    	passPlaceHolder.setVisible(true);
			    	passPlaceHolder.setEnabled(false);
			    }
			}
		});
		passField.setForeground(Color.WHITE);
		passField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passField.setOpaque(false);
		passField.setColumns(10);
		passField.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 153, 255)));
		passField.setBounds(10, 10, 402, 31);
		panelpass.add(passField);
		
		passPlaceHolder = new JTextField();
		passPlaceHolder.setDisabledTextColor(Color.GRAY);
		passPlaceHolder.setText("Enter Password");
		passPlaceHolder.setOpaque(false);
		passPlaceHolder.setForeground(Color.DARK_GRAY);
		passPlaceHolder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passPlaceHolder.setEnabled(false);
		passPlaceHolder.setEditable(false);
		passPlaceHolder.setColumns(10);
		passPlaceHolder.setBorder(null);
		passPlaceHolder.setBounds(11, 8, 402, 31);
		panelpass.add(passPlaceHolder);
		
		passPic = new JLabel("");
		passPic.setBounds(422, 2, 44, 39);
		ImageIcon ii1 = new ImageIcon(this.getClass().getResource("/preview.png"));
		Image img1 = ii1.getImage().getScaledInstance(passPic.getWidth(), passPic.getHeight(), Image.SCALE_SMOOTH);
		ii1 = new ImageIcon(img1);
		passPic.setIcon(ii1);
		panelpass.add(passPic);
		
		showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				passField.setEchoChar((char) 0);
				showPass.setVisible(false);
				hidePass.setVisible(true);
			}
		});
		showPass.setBounds(10, 207, 25, 22);
		ImageIcon show = new ImageIcon(this.getClass().getResource("/eye_120px.png"));
		Image img3 = show.getImage().getScaledInstance(showPass.getWidth(), showPass.getHeight(), Image.SCALE_SMOOTH);
		show = new ImageIcon(img3);
		showPass.setIcon(show);
		LoginPanel.add(showPass);
		
		hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passField.setEchoChar('•');
				showPass.setVisible(true);
				hidePass.setVisible(false);
			}
		});
		hidePass.setVisible(false);
		hidePass.setBounds(10, 207, 25, 22);
		ImageIcon hide = new ImageIcon(this.getClass().getResource("/invisible_120px.png"));
		Image img4 = hide.getImage().getScaledInstance(hidePass.getWidth(), hidePass.getHeight(), Image.SCALE_SMOOTH);
		hide = new ImageIcon(img4);
		hidePass.setIcon(hide);
		LoginPanel.add(hidePass);
		
		paneluser_1 = new JPanel();
		paneluser_1.setLayout(null);
		paneluser_1.setOpaque(false);
		paneluser_1.setBounds(0, 237, 471, 41);
		LoginPanel.add(paneluser_1);
		
		workingPic = new JLabel("");
		workingPic.setBounds(422, 2, 44, 39);
		ImageIcon wrk = new ImageIcon(this.getClass().getResource("/workingAs.png"));
		Image img5 = wrk.getImage().getScaledInstance(workingPic.getWidth(), workingPic.getHeight(), Image.SCALE_SMOOTH);
		wrk = new ImageIcon(img5);
		workingPic.setIcon(wrk);
		paneluser_1.add(workingPic);
		
		loginBox = new JComboBox();
		loginBox.setModel(new DefaultComboBoxModel(new String[] {"Login As", "Teacher", "Student", "Admin"}));
		loginBox.putClientProperty( "JComponent.outline",new ColorUIResource((Color) new Color(0, 153, 255)));
		loginBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginBox.setBounds(10, 10, 402, 31);
		paneluser_1.add(loginBox);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user = txtUserName.getText();
					pass = passField.getText();
					work = loginBox.getSelectedItem().toString();
					
					if (user.equals("") || pass.equals("") || work.equals("Login As")) {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
					}
					
					else {
						
						if(loginBox.getSelectedItem().equals("Student")) {
							pst = con.prepareStatement("select Name,ID,Institution,Department,Designation,Username,Password from student where Username = ? and Password = ? and Designation = ?");
							pst.setString(1, user);
							pst.setString(2, pass);
							pst.setString(3, work);
							ResultSet rs = pst.executeQuery();

							if (rs.next() == true) {
								name1 = rs.getString(1);
								id1 = rs.getString(2);
								institute1 = rs.getString(3);
								department1 = rs.getString(4);
								signUp1 = rs.getString(5);
								username1 = rs.getString(6);
								password1 = rs.getString(7);

								JOptionPane.showMessageDialog(null, "Logging in Successfully");
								dispose();
								com.java.profiles.Students s = new com.java.profiles.Students();
								s.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Wrong Username or Password");
							}
						}
						
						else if(loginBox.getSelectedItem().equals("Teacher")) {
							pst = con.prepareStatement("select Name,ID,Institution,Department,Designation,Username,Password from teacher where Username = ? and Password = ? and Designation = ?");
							pst.setString(1, user);
							pst.setString(2, pass);
							pst.setString(3, work);
							ResultSet rs = pst.executeQuery();

							if (rs.next() == true) {
								name1 = rs.getString(1);
								id1 = rs.getString(2);
								institute1 = rs.getString(3);
								department1 = rs.getString(4);
								signUp1 = rs.getString(5);
								username1 = rs.getString(6);
								password1 = rs.getString(7);

								JOptionPane.showMessageDialog(null, "Logging in Successfully");
								dispose();
								com.java.admin.Admin a = new com.java.admin.Admin();
								a.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Wrong Username or Password");
							}
						}
						
						else if(loginBox.getSelectedItem().equals("Admin")) {
							if(user.equals("admin") && pass.equals("123")) {
								JOptionPane.showMessageDialog(null, "Logging in Successfully");
								dispose();
								com.java.admin.Admin admin = new com.java.admin.Admin();
								admin.setVisible(true);
							}
							else {
								JOptionPane.showMessageDialog(null, "Wrong Username or Password");
							}
						}
					}
				}
				catch (SQLException ex) {

				}
			}
		});
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(new Color(30, 144, 255));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 28));
		btnLogin.setBounds(92, 400, 287, 41);
		btnLogin.putClientProperty( "JComponent.outline",new ColorUIResource(Color.CYAN));
		LoginPanel.add(btnLogin);
		
		lblNewLabel = new JLabel("New User?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(0, 153, 255));
		lblNewLabel.setBounds(141, 460, 68, 21);
		LoginPanel.add(lblNewLabel);
		
		lblsignup = new JLabel("SignUp");
		lblsignup.setToolTipText("Click here to crate new account");
		lblsignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblsignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				com.java.account.SignUp s = new com.java.account.SignUp();
				s.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblsignup.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblsignup.setForeground(new Color(0, 153, 255));
			}
		});
		lblsignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblsignup.setForeground(new Color(0, 153, 255));
		lblsignup.setFont(new Font("Dialog", Font.BOLD, 16));
		lblsignup.setBounds(216, 460, 57, 21);
		LoginPanel.add(lblsignup);
		
		lblNewLabel_1 = new JLabel("Now");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(279, 460, 31, 21);
		LoginPanel.add(lblNewLabel_1);
		
		loginImage = new JLabel("");
		loginImage.setBorder(new MatteBorder(4, 0, 4, 4, (Color) new Color(0, 0, 51)));
		loginImage.setBounds(0, 0, 471, 623);
		ImageIcon design = new ImageIcon(this.getClass().getResource("/best55.jpg"));
		Image image = design.getImage().getScaledInstance(loginImage.getWidth(), loginImage.getHeight(), Image.SCALE_SMOOTH);
		design = new ImageIcon(image);
		loginImage.setIcon(design);
		LoginPanel.add(loginImage);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME TO QUIZ PRACTICE APPLICATION");
		lblNewLabel_2.setFont(new Font("Nikosh", Font.BOLD, 38));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(135, 206, 250));
		lblNewLabel_2.setBounds(0, 20, 1366, 84);
		contentPane.add(lblNewLabel_2);
		
		lblimg = new JLabel("");
		lblimg.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		lblimg.setBounds(0, 0, 1366, 768);
		ImageIcon titleimg = new ImageIcon(this.getClass().getClassLoader().getResource("best55.jpg"));//29
		Image img11 = titleimg.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_SMOOTH);
		titleimg = new ImageIcon(img11);
		lblimg.setIcon(titleimg);
		contentPane.add(lblimg);
	}
}
