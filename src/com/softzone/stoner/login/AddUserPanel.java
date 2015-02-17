package com.softzone.stoner.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.TransactionNotInProgressException;

import com.softzone.stoner.state.AdminState;

public class AddUserPanel extends JPanel {
	private JPanel panel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JPasswordField rePasswordText;

	String[] authorityComboBoxItem = { "standard", "admin" };

	private Container contentPane;

	private String user, password, rePassword, authority;
	private boolean adminAuthority;

	// mySql database handeling
	private Connection connection;

	JDOManager jdoManager;
	Database database;
	private JComboBox authorityComboBox;
	private JLabel msgLabel;

	/**
	 * Create the panel.
	 */
	public AddUserPanel() {
		
		setBackground(new Color(105, 105, 105));
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(new Rectangle(20, 36, 386, 157));
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 240));
		lblNewLabel.setBounds(32, 20, 126, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 240));
		lblNewLabel_1.setBounds(32, 54, 126, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password (again)");
		lblNewLabel_2.setForeground(new Color(255, 255, 240));
		lblNewLabel_2.setBounds(32, 88, 126, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Authority");
		lblNewLabel_3.setForeground(new Color(255, 255, 240));
		lblNewLabel_3.setBounds(32, 122, 126, 14);
		panel.add(lblNewLabel_3);

		userText = new JTextField();
		userText.setBounds(168, 15, 157, 20);
		panel.add(userText);
		userText.setColumns(10);

		passwordText = new JPasswordField();
		passwordText.setBounds(168, 50, 157, 20);
		panel.add(passwordText);

		rePasswordText = new JPasswordField();
		rePasswordText.setBounds(168, 85, 157, 20);
		panel.add(rePasswordText);

		authorityComboBox = new JComboBox(authorityComboBoxItem);
		authorityComboBox.setForeground(new Color(255, 255, 240));
		authorityComboBox.setBackground(new Color(169, 169, 169));
		authorityComboBox.setMaximumRowCount(2);
		authorityComboBox.setBounds(168, 120, 157, 20);
		panel.add(authorityComboBox);

		msgLabel = new JLabel("");
		msgLabel.setForeground(new Color(0, 0, 255));
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setBounds(32, 11, 386, 14);
		add(msgLabel);

		JButton addNewUserButton = new JButton("Create User");
		addNewUserButton.setBackground(new Color(169, 169, 169));
		addNewUserButton.setForeground(new Color(255, 255, 240));
		addNewUserButton.setBounds(163, 215, 124, 23);
		add(addNewUserButton);

		addNewUserButton.addActionListener(new loginHandler());
		rePasswordText.addActionListener(new loginHandler());

		try {

			JDOManager.loadConfiguration("jdo-conf-log.xml");
			jdoManager = JDOManager.createInstance("userAccounts");
			database = jdoManager.getDatabase();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		adminAuthority = false;

	}

	private class loginHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			addUser(userText.getText(), passwordText.getText(),
					rePasswordText.getText());
		}

		private void addUser(String tempUser, String tempPassword,
				String tempRePassword) {
			user = tempUser;
			password = tempPassword;
			rePassword = tempRePassword;

			if (password.equals(rePassword) && password.length() >= 8
					&& user.length() >= 4) {
				// setVisible(false);
				try {
					database.begin();
					User userObject = new User();
					userObject.setUserName(user);
					userObject.setPassword(tempRePassword);
					userObject.setAuthority((String) authorityComboBox
							.getSelectedItem());

					// userObject.setUserId(11111);
					database.create(userObject);
					database.commit();
					msgLabel.setText("User [" + user + "] successfully added");
					msgLabel.setForeground(Color.BLUE);
					clearField();
					userText.setText("");

				} catch (Exception s) {
					try {
						database.rollback();
					} catch (TransactionNotInProgressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					clearField();
					msgLabel.setText("User creation failed. User ["
							+ userText.getText() + "] exsist.");
					msgLabel.setForeground(Color.RED);
					System.out.println("Error: " + s.getMessage());

				}
			} else {

				clearField();
				notLoggedIn();
			}
		}

		private void clearField() {
			passwordText.setText("");
			rePasswordText.setText("");
		}

		private void notLoggedIn() {
			if (password.equals("") && rePassword.equals("")) {
				System.out.println("passwords do not match");
				msgLabel.setText("Enter a valid password");
				msgLabel.setForeground(Color.RED);
			} else if (!password.equals(rePassword)) {
				System.out.println("Enter a valid password");
				msgLabel.setText("Passwords do not match");
				msgLabel.setForeground(Color.RED);
			} else if (password.length() < 8) {
				System.out.println("Enter a valid password");
				msgLabel.setText("Minimum password length is 8 characters");
				msgLabel.setForeground(Color.RED);
			} else if (user.length() < 4) {
				System.out.println("Enter a valid username");
				msgLabel.setText("Minimum username length is 4 characters");
				msgLabel.setForeground(Color.RED);
			} else {
				msgLabel.setText("Invalid input");
				msgLabel.setForeground(Color.RED);
			}

		}
	}
}
