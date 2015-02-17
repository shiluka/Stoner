package com.softzone.stoner.state;

import javax.swing.*;

import java.awt.*;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertWindow extends JFrame {

	private Container contentPane;
	private JLabel item_idLabel, typeLabel, nameLabel, supplierLabel,
			locationLabel, prizeLabel, amountLabel, itemDescriptionLable;
	private static JTextField item_idText, typeText, nameText, supplierText,
			locationText, prizeText, amountText;
	private static JTextField descriptionTextArea;
	private JButton btnNewButton, InsertButton;

	public InsertWindow() {
	}

	public JButton getInsertButton() {
		return InsertButton;
	}

	public void startInsertWindow() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
		InsertWindow.class.getResource("/images/Untitled-2.gif")));
		getContentPane().setBackground(new Color(105, 105, 105));
		// initialize bill book window

		setSize(1002, 700);
		setLocation(175, 15);
		setTitle("Insert Item");
		setResizable(false);

		contentPane = getContentPane();
		getContentPane().setLayout(null);
		typeText = new JTextField(15);
		typeText.setForeground(new Color(255, 255, 255));
		typeText.setBackground(new Color(169, 169, 169));
		typeText.setBounds(348, 182, 244, 20);
		getContentPane().add(typeText);

		typeLabel = new JLabel("Type");
		typeLabel.setForeground(new Color(255, 255, 255));
		typeLabel.setBounds(245, 185, 82, 14);
		getContentPane().add(typeLabel);

		item_idLabel = new JLabel("Item ID");
		item_idLabel.setForeground(new Color(255, 255, 255));
		item_idLabel.setBounds(245, 154, 82, 14);
		getContentPane().add(item_idLabel);
		item_idText = new JTextField(15);
		item_idText.setForeground(new Color(255, 255, 255));
		item_idText.setBackground(new Color(169, 169, 169));
		item_idText.setBounds(348, 151, 244, 20);
		getContentPane().add(item_idText);

		nameLabel = new JLabel("Name");
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setBounds(245, 120, 82, 19);
		getContentPane().add(nameLabel);
		nameText = new JTextField(15);
		nameText.setForeground(new Color(255, 255, 255));
		nameText.setBackground(new Color(169, 169, 169));
		nameText.setBounds(348, 116, 244, 23);
		getContentPane().add(nameText);

		supplierLabel = new JLabel("Supplier");
		supplierLabel.setForeground(new Color(255, 255, 255));
		supplierLabel.setBounds(245, 216, 82, 14);
		getContentPane().add(supplierLabel);
		supplierText = new JTextField(15);
		supplierText.setForeground(new Color(255, 255, 255));
		supplierText.setBackground(new Color(169, 169, 169));
		supplierText.setBounds(348, 213, 244, 20);
		getContentPane().add(supplierText);

		prizeLabel = new JLabel("Prize");
		prizeLabel.setForeground(new Color(255, 255, 255));
		prizeLabel.setBounds(245, 310, 82, 14);
		getContentPane().add(prizeLabel);
		prizeText = new JTextField(15);
		prizeText.setForeground(new Color(255, 255, 255));
		prizeText.setBackground(new Color(169, 169, 169));
		prizeText.setBounds(348, 307, 244, 20);
		getContentPane().add(prizeText);

		amountLabel = new JLabel("Amount");
		amountLabel.setForeground(new Color(255, 255, 255));
		amountLabel.setBounds(245, 279, 82, 14);
		getContentPane().add(amountLabel);
		amountText = new JTextField(15);
		amountText.setForeground(new Color(255, 255, 255));
		amountText.setBackground(new Color(169, 169, 169));
		amountText.setBounds(348, 276, 244, 20);
		getContentPane().add(amountText);

		locationLabel = new JLabel("Location");
		locationLabel.setForeground(new Color(255, 255, 255));
		locationLabel.setBounds(245, 247, 82, 14);
		getContentPane().add(locationLabel);
		locationText = new JTextField(15);
		locationText.setForeground(new Color(255, 255, 255));
		locationText.setBackground(new Color(169, 169, 169));
		locationText.setBounds(348, 244, 244, 20);
		getContentPane().add(locationText);

		itemDescriptionLable = new JLabel("Description of the Item");
		itemDescriptionLable.setForeground(new Color(255, 255, 255));
		itemDescriptionLable.setHorizontalAlignment(SwingConstants.CENTER);
		itemDescriptionLable.setBounds(348, 338, 244, 23);
		contentPane.add(itemDescriptionLable);

		JLabel label = new JLabel(
				new ImageIcon(
						"C:\\Users\\King\\workspace\\SoftZoneProject\\src\\com\\softzone\\stoner\\images\\Untitled-1.gif"));
		label.setPreferredSize(new Dimension(177, 100));
		label.setBounds(10, 11, 988, 94);
		getContentPane().add(label);

		InsertButton = new JButton("Insert");

		InsertHandlerClass insert = new InsertHandlerClass();
		insert.setInsertWindow(this);
		InsertButton.addActionListener(insert);

		InsertButton.setBackground(new Color(128, 128, 128));
		InsertButton.setForeground(new Color(255, 255, 255));
		InsertButton.setBounds(349, 609, 243, 23);
		getContentPane().add(InsertButton);

		descriptionTextArea = new JTextField();
		descriptionTextArea.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionTextArea.setForeground(new Color(255, 255, 255));
		descriptionTextArea.setBackground(new Color(169, 169, 169));
		descriptionTextArea.setBounds(348, 372, 244, 226);
		getContentPane().add(descriptionTextArea);
		descriptionTextArea.setColumns(10);

		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminState main = new AdminState();
				main.init();
				main.buildSearchWindow();
				//main.adminAuthority();
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setAutoscrolls(true);
		btnNewButton.setActionCommand("backButton");
		btnNewButton.setBounds(348, 638, 244, 23);
		getContentPane().add(btnNewButton);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void status(boolean status) {
		setVisible(status);

	}

	public JTextField getItem_idText() {
		return item_idText;
	}

	public JTextField getTypeText() {
		return typeText;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public JTextField getSupplierText() {
		return supplierText;
	}

	public JTextField getLocationText() {
		return locationText;
	}

	public JTextField getPrizeText() {
		return prizeText;
	}

	public JTextField getAmountText() {
		return amountText;
	}

	public JTextField getDescriptionTextArea() {
		return descriptionTextArea;
	}

}
