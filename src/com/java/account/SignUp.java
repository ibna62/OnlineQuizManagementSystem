package com.java.account;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class SignUp extends JFrame {
	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JPanel panelname;
	private JTextField txtName;
	private JTextField namePlaceHolder;
	private JPanel panelid;
	private JTextField txtId;
	private JTextField idPlaceHolder;
	private JPanel paneluniversity;
	private JTextField txtVarsity;
	private JTextField universityPlaceHolder;
	@SuppressWarnings("rawtypes")
	private JComboBox workBox;
	@SuppressWarnings("rawtypes")
	private JComboBox groupBox;
	private JPanel paneluser;
	private JTextField txtUsername;
	private JTextField userPlaceHolder;
	private JPanel panelpass;
	private JPasswordField passField;
	private JTextField passPlaceHolder;
	private JButton btnRegister;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SignUp() {
		setLookAndFeel();
		initialize();
		connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	String name, username, password, department, signUpAs, id, college;
	private JLabel lblimage;
	private JLabel lblNewLabel;
	private JButton btnBack;
	private JButton btnClear;
	
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
			//FlatDarkLaf.setup();
			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
			UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(0, 102, 255)));
			UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
			UIManager.put("ComboBox.foreground", new ColorUIResource(Color.WHITE));
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

	private void initialize() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		
		panelname = new JPanel();
		panelname.setBounds(60, 200, 543, 41);
		panelname.setOpaque(false);
		
		txtName = new JTextField();
		txtName.setForeground(Color.WHITE);
		txtName.setBounds(10, 10, 523, 31);
		txtName.setCaretColor(Color.WHITE);
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					namePlaceHolder.setVisible(false);
					namePlaceHolder.setEnabled(false);
			    }
			    else if(txtName.getText().equals("")) {
			    	namePlaceHolder.setVisible(true);
			    	namePlaceHolder.setEnabled(false);
			    }
			}
		});
		txtName.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtName.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		txtName.setOpaque(false);
		txtName.setColumns(10);
		
		namePlaceHolder = new JTextField();
		namePlaceHolder.setBounds(11, 8, 483, 31);
		namePlaceHolder.setDisabledTextColor(Color.GRAY);
		namePlaceHolder.setOpaque(false);
		namePlaceHolder.setBorder(null);
		namePlaceHolder.setEnabled(false);
		namePlaceHolder.setEditable(false);
		namePlaceHolder.setFont(new Font("Dialog", Font.PLAIN, 22));
		namePlaceHolder.setForeground(Color.DARK_GRAY);
		namePlaceHolder.setText("Enter Name");
		namePlaceHolder.setColumns(10);
		
		panelid = new JPanel();
		panelid.setBounds(60, 270, 543, 41);
		panelid.setOpaque(false);
		
		txtId = new JTextField();
		txtId.setForeground(Color.WHITE);
		txtId.setBounds(10, 10, 523, 31);
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					idPlaceHolder.setVisible(false);
					idPlaceHolder.setEnabled(false);
			    }
			    else if(txtId.getText().equals("")) {
			    	idPlaceHolder.setVisible(true);
			    	idPlaceHolder.setEnabled(false);
			    }
			}
		});
		txtId.setOpaque(false);
		txtId.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtId.setColumns(10);
		txtId.setCaretColor(Color.WHITE);
		txtId.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		
		idPlaceHolder = new JTextField();
		idPlaceHolder.setBounds(11, 8, 483, 31);
		idPlaceHolder.setText("Enter Your ID");
		idPlaceHolder.setOpaque(false);
		idPlaceHolder.setForeground(Color.DARK_GRAY);
		idPlaceHolder.setFont(new Font("Dialog", Font.PLAIN, 22));
		idPlaceHolder.setEnabled(false);
		idPlaceHolder.setEditable(false);
		idPlaceHolder.setDisabledTextColor(Color.GRAY);
		idPlaceHolder.setColumns(10);
		idPlaceHolder.setBorder(null);
		
		paneluniversity = new JPanel();
		paneluniversity.setBounds(60, 340, 543, 41);
		paneluniversity.setOpaque(false);
		
		txtVarsity = new JTextField();
		txtVarsity.setForeground(Color.WHITE);
		txtVarsity.setBounds(10, 10, 523, 31);
		txtVarsity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					universityPlaceHolder.setVisible(false);
					universityPlaceHolder.setEnabled(false);
			    }
			    else if(txtVarsity.getText().equals("")) {
			    	universityPlaceHolder.setVisible(true);
			    	universityPlaceHolder.setEnabled(false);
			    }
			}
		});
		txtVarsity.setOpaque(false);
		txtVarsity.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtVarsity.setColumns(10);
		txtVarsity.setCaretColor(Color.WHITE);
		txtVarsity.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		
		universityPlaceHolder = new JTextField();
		universityPlaceHolder.setBounds(11, 8, 483, 31);
		universityPlaceHolder.setText("Enter University / College");
		universityPlaceHolder.setOpaque(false);
		universityPlaceHolder.setForeground(Color.DARK_GRAY);
		universityPlaceHolder.setFont(new Font("Dialog", Font.PLAIN, 22));
		universityPlaceHolder.setEnabled(false);
		universityPlaceHolder.setEditable(false);
		universityPlaceHolder.setDisabledTextColor(Color.GRAY);
		universityPlaceHolder.setColumns(10);
		universityPlaceHolder.setBorder(null);
		
		workBox = new JComboBox();
		workBox.setForeground(Color.WHITE);
		workBox.setBounds(763, 340, 203, 41);
		workBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		/*workBox.putClientProperty( "JComponent.roundRect", true );*/
		workBox.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		workBox.setModel(new DefaultComboBoxModel(new String[] {"SIGNUP AS", "TEACHER", "STUDENT"}));
		
		groupBox = new JComboBox();
		groupBox.setForeground(Color.WHITE);
		groupBox.setModel(new DefaultComboBoxModel(new String[] {"DEPARTMENT", "CSE", "EEE", "ELL", "LAW", "BBA", "EB", "CCE", "ETE", "ARCHITECTURE", "BANGLA", "PHARMACY"}));
		groupBox.setBounds(995, 340, 291, 41);
		groupBox.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		groupBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnRegister = new JButton("");
		btnRegister.setToolTipText("Create");
		btnRegister.setBackground(new Color(51, 51, 255));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					name = txtName.getText();
					id = txtId.getText();
					college = txtVarsity.getText();
					username = txtUsername.getText();
					password = passField.getText();
					signUpAs = workBox.getSelectedItem().toString();
					department = groupBox.getSelectedItem().toString();
					
					if(name.equals("") || id.equals("") || college.equals("") || username.equals("")
							|| password.equals("") || signUpAs.equals("") || department.equals("")) {
						JOptionPane.showMessageDialog(null, "Please Fill up all the field");
					}
					else {
						if(signUpAs.equals("STUDENT")) {
							pst = con.prepareStatement("select Username from student where Username = ?");
							pst.setString(1, username);
							ResultSet rs = pst.executeQuery();

							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "Username already exists");
								txtUsername.setText("");
								groupBox.setSelectedItem("");
							}
							else {
								pst = con.prepareStatement("insert into student(Name,ID,Institution,Department,Designation,Username,Password)values(?,?,?,?,?,?,?)");

								pst.setString(1, name);
								pst.setString(2, id);
								pst.setString(3, college);
								pst.setString(4, department);
								pst.setString(5, signUpAs);
								pst.setString(6, username);
								pst.setString(7, password);
								pst.executeUpdate();

								txtName.setText("");
								txtId.setText("");
								txtVarsity.setText("");
								txtUsername.setText("");
								workBox.setSelectedItem("");
								groupBox.setSelectedItem("");
								passField.setText("");
								txtName.requestFocus();
								
								JOptionPane.showMessageDialog(null, "Account Created Successfully");
								dispose();
								com.java.account.Login lg = new com.java.account.Login();
								lg.setVisible(true);
							}
						}
						else if(signUpAs.equals("TEACHER")) {
							pst = con.prepareStatement("select Username from teacher where Username = ?");
							pst.setString(1, username);
							ResultSet rs = pst.executeQuery();

							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "Username already exists");
								txtUsername.setText("");
								groupBox.setSelectedItem("");
							}
							else {
								pst = con.prepareStatement("insert into teacher(Name,ID,Institution,Department,Designation,Username,Password)values(?,?,?,?,?,?,?)");

								pst.setString(1, name);
								pst.setString(2, id);
								pst.setString(3, college);
								pst.setString(4, department);
								pst.setString(5, signUpAs);
								pst.setString(6, username);
								pst.setString(7, password);
								pst.executeUpdate();
								
								txtName.setText("");
								txtId.setText("");
								txtVarsity.setText("");
								txtUsername.setText("");
								workBox.setSelectedItem("");
								groupBox.setSelectedItem("");
								passField.setText("");
								txtName.requestFocus();
								
								JOptionPane.showMessageDialog(null, "Account Created Successfully");
								dispose();
								com.java.account.Login lg = new com.java.account.Login();
								lg.setVisible(true);
							}
						}
					}
				}
				catch(Exception ex) {
					
				}
			}
		});
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 30));
		btnRegister.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		btnRegister.setBounds(792, 520, 50, 50);
		ImageIcon btnicon = new ImageIcon(this.getClass().getClassLoader().getResource("ok2.png"));
		Image bi = btnicon.getImage().getScaledInstance(btnRegister.getWidth(), btnRegister.getHeight(), Image.SCALE_SMOOTH);
		btnicon = new ImageIcon(bi);
		btnRegister.setIcon(btnicon);
		
		btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.java.account.Login lg = new Login();
				lg.setVisible(true);
			}
		});
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setToolTipText("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 30));
		btnBack.setBackground(new Color(51, 51, 255));
		btnBack.setBounds(524, 520, 50, 50);
		btnBack.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		ImageIcon backimage = new ImageIcon(this.getClass().getClassLoader().getResource("10.png"));
		Image bck = backimage.getImage().getScaledInstance(btnBack.getWidth(), btnBack.getHeight(), Image.SCALE_SMOOTH);
		backimage = new ImageIcon(bck);
		btnBack.setIcon(backimage);
		contentPane.add(btnBack);
		
		btnClear = new JButton("");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText(name);
				namePlaceHolder.setVisible(true);
				namePlaceHolder.setEnabled(false);
				
				txtId.setText(null);
				idPlaceHolder.setVisible(true);
				idPlaceHolder.setEnabled(false);
				
				txtVarsity.setText(null);
				universityPlaceHolder.setVisible(true);
				universityPlaceHolder.setEnabled(false);
				
				txtUsername.setText(null);
				userPlaceHolder.setVisible(true);
		    	userPlaceHolder.setEnabled(false);
		    	
		    	passField.setText(null);
		    	passPlaceHolder.setVisible(true);
		    	passPlaceHolder.setEnabled(false);
		    	
		    	workBox.setSelectedIndex(0);
		    	groupBox.setSelectedIndex(0);
		    	
		    	txtName.requestFocus();
			}
		});
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setToolTipText("Clear All");
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Dialog", Font.BOLD, 30));
		btnClear.setBackground(new Color(51, 51, 255));
		btnClear.setBounds(658, 520, 50, 50);
		btnClear.putClientProperty( "JComponent.outline",new ColorUIResource(Color.WHITE));
		ImageIcon clricon = new ImageIcon(this.getClass().getClassLoader().getResource("remove.png"));
		Image ci = clricon.getImage().getScaledInstance(btnClear.getWidth(), btnClear.getHeight(), Image.SCALE_SMOOTH);
		clricon = new ImageIcon(ci);
		btnClear.setIcon(clricon);
		contentPane.add(btnClear);
		
		paneluser = new JPanel();
		paneluser.setLayout(null);
		paneluser.setOpaque(false);
		paneluser.setBounds(753, 200, 543, 41);
		contentPane.add(paneluser);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.WHITE);
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					userPlaceHolder.setVisible(false);
					userPlaceHolder.setEnabled(false);
			    }
			    else if(txtUsername.getText().equals("")) {
			    	userPlaceHolder.setVisible(true);
			    	userPlaceHolder.setEnabled(false);
			    }
			}
		});
		txtUsername.setOpaque(false);
		txtUsername.setFont(new Font("Dialog", Font.PLAIN, 22));
		txtUsername.setColumns(10);
		txtUsername.setCaretColor(Color.WHITE);
		txtUsername.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		txtUsername.setBounds(10, 10, 523, 31);
		paneluser.add(txtUsername);
		
		userPlaceHolder = new JTextField();
		userPlaceHolder.setText("Enter Username");
		userPlaceHolder.setOpaque(false);
		userPlaceHolder.setForeground(Color.DARK_GRAY);
		userPlaceHolder.setFont(new Font("Dialog", Font.PLAIN, 22));
		userPlaceHolder.setEnabled(false);
		userPlaceHolder.setEditable(false);
		userPlaceHolder.setDisabledTextColor(Color.GRAY);
		userPlaceHolder.setColumns(10);
		userPlaceHolder.setBorder(null);
		userPlaceHolder.setBounds(11, 8, 483, 31);
		paneluser.add(userPlaceHolder);
		
		panelpass = new JPanel();
		panelpass.setLayout(null);
		panelpass.setOpaque(false);
		panelpass.setBounds(753, 270, 543, 41);
		
		passField = new JPasswordField();
		passField.setForeground(Color.WHITE);
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
		passField.setFont(new Font("Dialog", Font.PLAIN, 22));
		passField.setOpaque(false);
		passField.setColumns(10);
		passField.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.WHITE));
		passField.setBounds(10, 10, 523, 31);
		panelpass.add(passField);
		
		passPlaceHolder = new JTextField();
		passPlaceHolder.setDisabledTextColor(Color.GRAY);
		passPlaceHolder.setText("Enter Password");
		passPlaceHolder.setOpaque(false);
		passPlaceHolder.setForeground(Color.DARK_GRAY);
		passPlaceHolder.setFont(new Font("Dialog", Font.PLAIN, 22));
		passPlaceHolder.setEnabled(false);
		passPlaceHolder.setEditable(false);
		passPlaceHolder.setColumns(10);
		passPlaceHolder.setBorder(null);
		passPlaceHolder.setBounds(11, 8, 483, 31);
		
		
		contentPane.setLayout(null);
		contentPane.add(panelname);
		panelname.setLayout(null);
		panelname.add(txtName);
		panelname.add(namePlaceHolder);
		contentPane.add(panelid);
		panelid.setLayout(null);
		panelid.add(txtId);
		panelid.add(idPlaceHolder);
		contentPane.add(paneluniversity);
		paneluniversity.setLayout(null);
		paneluniversity.add(txtVarsity);
		paneluniversity.add(universityPlaceHolder);
		panelpass.add(passField);
		panelpass.add(passPlaceHolder);
		contentPane.add(workBox);
		contentPane.add(groupBox);
		contentPane.add(btnRegister);
		contentPane.add(panelpass);
		
		lblNewLabel = new JLabel("SIGN UP");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel.setBounds(469, 50, 427, 41);
		contentPane.add(lblNewLabel);
		
		lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 1366, 768);
		ImageIcon imageicon = new ImageIcon(this.getClass().getClassLoader().getResource("quiz7.jpg"));
		Image image = imageicon.getImage().getScaledInstance(lblimage.getWidth(), lblimage.getHeight(), Image.SCALE_SMOOTH);
		imageicon = new ImageIcon(image);
		lblimage.setIcon(imageicon);
		contentPane.add(lblimage);
	}
}
