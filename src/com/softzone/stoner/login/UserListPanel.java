package com.softzone.stoner.login;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.softzone.stoner.state.AdminState;

public class UserListPanel extends JPanel {
	private JList userList;
	private JButton userDeleteButton;
	private JTextField userNameText;
	private JTextField authorityText;

	private Connection connection;
	private JPanel userInfoPanel;
	private JScrollPane scrollPane;
	private JLabel lblUserList;
	private JLabel msgLabel;

	/**
	 * Create the panel.
	 */
	public UserListPanel() {
		
		setBackground(new Color(105, 105, 105));
		setLayout(null);

		userList = new JList();
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setVisibleRowCount(10);

		JPanel userListPanel = new JPanel();
		userListPanel.setBackground(new Color(169, 169, 169));
		userListPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		userListPanel.setBounds(10, 37, 197, 217);
		userListPanel.setLayout(null);
		scrollPane = new JScrollPane(userList);
		scrollPane.setBounds(3, 30, 191, 181);
		userListPanel.add(scrollPane);
		add(userListPanel);

		lblUserList = new JLabel("User List");
		lblUserList.setForeground(new Color(255, 255, 240));
		lblUserList.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserList.setBounds(10, 11, 177, 14);
		userListPanel.add(lblUserList);

		userInfoPanel = new JPanel();
		userInfoPanel.setBackground(new Color(128, 128, 128));
		userInfoPanel.setVisible(false);
		userInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		userInfoPanel.setBounds(243, 37, 197, 217);
		add(userInfoPanel);
		userInfoPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 240));
		lblNewLabel.setBounds(27, 55, 124, 14);
		userInfoPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Authority");
		lblNewLabel_1.setForeground(new Color(255, 255, 240));
		lblNewLabel_1.setBounds(27, 111, 124, 14);
		userInfoPanel.add(lblNewLabel_1);

		authorityText = new JTextField();
		authorityText.setEditable(false);
		authorityText.setBounds(27, 136, 124, 20);
		userInfoPanel.add(authorityText);
		authorityText.setColumns(10);

		userNameText = new JTextField();
		userNameText.setEditable(false);
		userNameText.setBounds(27, 80, 124, 20);
		userInfoPanel.add(userNameText);
		userNameText.setColumns(10);

		JLabel lblUserDetails = new JLabel("User Details");
		lblUserDetails.setForeground(new Color(255, 255, 240));
		lblUserDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserDetails.setBounds(10, 11, 177, 14);
		userInfoPanel.add(lblUserDetails);

		userDeleteButton = new JButton("Delete User");
		userDeleteButton.setBackground(new Color(128, 128, 128));
		userDeleteButton.setForeground(new Color(255, 255, 240));
		userDeleteButton.setBounds(46, 181, 105, 23);
		userDeleteButton.addActionListener(new UserHandlerClass());
		userInfoPanel.add(userDeleteButton);

		msgLabel = new JLabel("");
		msgLabel.setForeground(new Color(0, 0, 255));
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setBounds(77, 11, 281, 14);
		add(msgLabel);

		init();

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
		loadUserList();
	}

	private void loadUserList() {

		loadUser();
		userList.setVisibleRowCount(5);

		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				String userName = (String) userList.getSelectedValue();

				String userathority = "";
				try {

					Statement statement = connection.createStatement();
					ResultSet rs = statement
							.executeQuery("SELECT * FROM addusertable WHERE username = '"
									+ userName + "'");

					ResultSet authrt = (statement
							.executeQuery("SELECT authority from addusertable WHERE username = '"
									+ userName + "'"));
					if (authrt.next()) {
						userathority = authrt.getString("authority");

						userNameText.setText(userName);
						authorityText.setText(userathority);

						userInfoPanel.setVisible(true);
					}

				} catch (SQLException exception) {
					displaySQLErrors(exception);
				}

			}
		});

	}

	private void loadUser() {
		Vector v = new Vector();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT username FROM addusertable");
			while (rs.next()) {
				v.addElement(rs.getString("username"));
			}
			rs.close();
		} catch (SQLException e) {
			displaySQLErrors(e);
		}
		userList.setListData(v);

	}

	private void displaySQLErrors(SQLException e) {
		System.out.println("SQLException: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}

	private class UserHandlerClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String userName = null;

			try {
				userName = (String) userList.getSelectedValue();
				Statement statement = connection.createStatement();

				ResultSet results = statement
						.executeQuery("SELECT count(*) FROM addusertable WHERE username != '"
								+ userName + "' AND authority = 'admin'");

				if (results.next()) {
					int count = results.getInt(1);

					if (count > 0) {
						int i = statement
								.executeUpdate("DELETE FROM addusertable WHERE username = '"
										+ userName + "'");
						msgLabel.setText("User [" + userName
								+ "] deleted successfully");
						msgLabel.setForeground(Color.BLUE);

					} else {

						msgLabel.setText("User [" + userName
								+ "] is the only admin user");
						msgLabel.setForeground(Color.RED);
					}

				} else {

					msgLabel.setText("User [" + userName + "] delete failed");
					msgLabel.setForeground(Color.RED);
				}

			} catch (SQLException deleteException) {
				displaySQLErrors(deleteException);

				msgLabel.setText("User [" + userName + "] delete failed");
				msgLabel.setForeground(Color.RED);
			}
			
			
			
			
			

			userNameText.setText("");
			authorityText.setText("");

			userList.removeAll();
			loadUser();

		}

	}
}
