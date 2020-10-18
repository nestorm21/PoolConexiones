package com.nesu.ConnectionPool;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface{

	private JFrame frame;
	public static JTextArea area;

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(156, 11, 268, 266);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		UserInterface.area = textArea;
		scrollPane.setViewportView(textArea);
		
		JButton button = new JButton("10");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<10; i++) {
					new Client();
				}
			}
		});
		button.setBounds(31, 11, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("100");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<100; i++) {
					new Client();
				}
			}
		});
		button_1.setBounds(31, 59, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("1000");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<1000; i++) {
					new Client();
				}
			}
		});
		button_2.setBounds(31, 110, 89, 23);
		frame.getContentPane().add(button_2);
		
		JButton btnNewButton = new JButton("10000");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<10000; i++) {
					new Client();
				}
			}
		});
		btnNewButton.setBounds(31, 157, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button_3 = new JButton("100000");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<100000; i++) {
					new Client();
				}
			}
		});
		button_3.setBounds(31, 205, 89, 23);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("1000000");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i<1000000; i++) {
					new Client();
				}
			}
		});
		button_4.setBounds(31, 254, 89, 23);
		frame.getContentPane().add(button_4);
	}
}
