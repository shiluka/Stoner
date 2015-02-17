package com.softzone.stoner.login;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.softzone.stoner.proxy.WindowProxy;


import java.awt.event.*;
import java.sql.*;

import java.util.*;

public class LoginWindow extends JFrame {

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 150;
	private static final int X_ORIGIN = 500;
	private static final int Y_ORIGIN = 500;

	private JButton logButton;
	private JLabel userLabel, passwordLabel;
	private JTextField userText;
	private JPasswordField passwordText;

	private Container contentPane;

	private boolean adminAuthority;
	private boolean loggedSuccess;

	// mySql database handeling
	private Connection connection;

	private JLabel msgLab, errormsg;
	private JTextField errortxt;

	private final JPanel contentPanel = new JPanel();

	private static LoginWindow uniqueLoginInstance;
	


	// load to jdbc driver
	private LoginWindow() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("unable to find and load driver");
			System.exit(1);
		}
		adminAuthority = false;
		loggedSuccess = false;

	}

	public static LoginWindow getUniqueLoginInstance() {
		if (uniqueLoginInstance == null) {
			uniqueLoginInstance = new LoginWindow();
		}

		return uniqueLoginInstance;
	}

	public boolean getAdminAuthority() {
		return adminAuthority;
	}

	public boolean isLoggedSuccess() {
		return loggedSuccess;
	}

	// build gui
	public void buildWindow() {

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(X_ORIGIN, Y_ORIGIN);
		setTitle("LoginSystem");
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				LoginWindow.class.getResource("/images/Untitled-2.gif")));
		setTitle("Log In");

		setBounds(550, 100, 427, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton Logbutton = new JButton("New button");
		Logbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		errortxt = new JTextField();
		errortxt.setEditable(false);
		errortxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		errortxt.setForeground(Color.RED);
		errortxt.setBounds(72, 272, 266, 27);
		contentPanel.add(errortxt);
		errortxt.setColumns(10);
		errortxt.setVisible(false);

		JLabel lblLogButton = new JLabel("Log Button");
		lblLogButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLogButton.setBounds(252, 310, 111, 21);
		contentPanel.add(lblLogButton);

		Logbutton.setIcon(new ImageIcon(LoginWindow.class
				.getResource("/images/button.gif")));
		Logbutton.setBounds(343, 307, 68, 54);
		contentPanel.add(Logbutton);

		JLabel userTxt = new JLabel("User Name");
		userTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		userTxt.setBounds(0, 79, 91, 33);
		contentPanel.add(userTxt);

		JLabel passwordTxt = new JLabel("Password");
		passwordTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordTxt.setBounds(0, 216, 81, 33);
		contentPanel.add(passwordTxt);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(UIManager
				.getColor("CheckBox.interiorBackground"));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(LoginWindow.class
				.getResource("/images/r.gif")));
		lblNewLabel.setBounds(0, 0, 428, 366);
		contentPanel.add(lblNewLabel);

		userText = new JTextField();
		userText.setForeground(Color.WHITE);
		userText.setBackground(Color.GRAY);
		userText.setBounds(112, 85, 146, 21);
		contentPanel.add(userText);
		userText.setColumns(10);

		passwordText = new JPasswordField();
		passwordText.setForeground(Color.WHITE);
		passwordText.setBackground(Color.GRAY);
		passwordText.setBounds(91, 222, 140, 20);
		contentPanel.add(passwordText);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Logbutton.addActionListener(new loginHandler());
		passwordText.addActionListener(new loginHandler());
		setVisible(true);

	}

	private void connectToDB() {
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/userAccounts?user=root&password=mysql");
		} catch (SQLException e) {
			System.out.printf("SQLException: " + e.getMessage() + "\n");
			System.out.printf("SQLState: " + e.getSQLState() + "\n");
			System.out.printf("VendorError: " + e.getErrorCode() + "\n");
		}
	}

	public void init() {
		connectToDB();
	}

	private class loginHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String tempUser = userText.getText();
			String tempPwd = passwordText.getText();

			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement
						.executeQuery("SELECT * FROM addUserTable WHERE username = '"
								+ tempUser + "'");

				if (rs.next() && (tempPwd.equals(rs.getString("password")))) {

					String dbAuthority = rs.getString("authority");

					// if logging successfull
					System.out.println("loged successfully as " + dbAuthority);
					loggedSuccess = true;
					setVisible(false);

					if (dbAuthority.equals("admin")) {
						adminAuthority = true;
					} else {
						adminAuthority = false;
					}

					System.out.println("admin authority: " + adminAuthority);

					WindowProxy window = new WindowProxy(dbAuthority,tempUser);
					
					

				} else {
					errortxt.setText(" Incorrect User name or password");
					// if logging unsuccessful
					errortxt.setVisible(true);

					clearField();
					// if logging unsuccessful
					System.out.println("username or password incorrect");
				}

			} catch (SQLException s) {
				System.out.println("Error");
			}

		}

	}

	private void clearField() {
		passwordText.setText("");
		userText.setText("");
	}


}
