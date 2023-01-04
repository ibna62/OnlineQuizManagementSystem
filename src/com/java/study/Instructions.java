package com.java.study;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Instructions extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructions frame = new Instructions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Instructions() {
		setLookAndFeel();
		initialize();
		showFile();
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
	
	private void showFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("use.txt"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				textArea.setText(sb.toString());
			}
			reader.close();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "File Not Found");
		}
	}

	private void initialize() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(660, 500);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 0, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(10, 11, 580, 411);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFocusable(false);
		textArea.setEditable(false);
		textArea.setBorder(null);
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(new Color(102, 51, 204));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 20));
		btnNewButton.setBounds(251, 450, 157, 30);
		contentPane.add(btnNewButton);
	}
}
