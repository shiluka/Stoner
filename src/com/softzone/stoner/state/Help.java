package com.softzone.stoner.state;

import java.awt.BorderLayout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Help extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Help() {
		setTitle("About BMS");

		setBounds(550, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(412, 159, -282, -148);
		contentPane.add(scrollPane);

		JButton okButton = new JButton("OK");
		okButton.setBackground(new Color(169, 169, 169));
		okButton.setForeground(new Color(255, 255, 255));

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		okButton.setBounds(186, 206, 89, 23);
		contentPane.add(okButton);

		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(169, 169, 169));
		textArea.setBounds(127, 22, 297, 140);
		contentPane.add(textArea);
		textArea.setText("\n Version : BMS 1.0 \n Build ID: 20131011\n (c) Copyright BMS contributors 2013.\n All rights reserved.\n Visit https://sites.google.com/\n site/softzonezz" );
		
		//JLabel lblNewLabel = new JLabel("New label");
		//lblNewLabel.setIcon(new ImageIcon(Help.class.getResource("/images/softzone1.gif")));
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(
				  "C:\\Users\\King\\workspace\\SoftZoneProject\\src\\com\\softzone\\stoner\\state\\images\\softzone1.gif"));
		
		
		lblNewLabel.setBounds(8, 22, 109, 77);
		contentPane.add(lblNewLabel);
	}
	
	public void display(){
		setVisible(true);
	}
}
