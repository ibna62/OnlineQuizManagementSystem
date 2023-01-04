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

public class Study extends JFrame {

	private JPanel contentPane;
	private JPanel panelExit;
	private JPanel panelMinimize;
	private JLabel lblExit;
	private JLabel lblMinimize;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Study frame = new Study();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Study() {
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
			BufferedReader reader = new BufferedReader(new FileReader("GK.txt"));
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
		setSize(1366, 768);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		
		scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(0, 32, 1366, 736);
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
	}
}
