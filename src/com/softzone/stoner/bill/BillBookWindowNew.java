package com.softzone.stoner.bill;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import java.sql.*;

import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.Database;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;
import java.awt.Toolkit;



public class BillBookWindowNew extends JFrame {

	private JPanel contentPane;
	private JTextField searchText;
	/**
	 * Launch the application.
	
	/**
	 * Create the frame.
	 */
	String bill_id, date, details,totalamount;
	
	java.sql.Connection connection;
	JList list_1;
	TextArea description;
	  Statement statement; 
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

	public BillBookWindowNew() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BillBookWindowNew.class.getResource("/images/Untitled-2.gif")));
		connectToDB();
		setTitle("Bill Book");
		
		setBounds(175, 15, 1000, 700);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		  JLabel label = new JLabel(new ImageIcon(
				  "C:\\Users\\King\\workspace\\SoftZoneProject\\src\\com\\softzone\\stoner\\images\\Untitled-1.gif"
				  )); label.setPreferredSize(new Dimension(177, 100));
				  label.setBounds(10, 11, 988, 94); getContentPane().add(label);
		
		 list_1 = new JList();
		 list_1.setBackground(new Color(128, 128, 128));
		list_1.setBounds(85, 237, 297, 366);
		loadItems();
		contentPane.add(list_1);
		
	     description = new TextArea();
	     description.setBackground(new Color(128, 128, 128));
		description.setBounds(450, 237, 447, 371);
		contentPane.add(description);
		
		JButton btnSearchABill = new JButton("Search a Bill");
		btnSearchABill.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchABill.setForeground(new Color(255, 255, 255));
		btnSearchABill.setBackground(new Color(128, 128, 128));
		btnSearchABill.setBounds(164, 191, 126, 28);
		contentPane.add(btnSearchABill);
		
		searchText = new JTextField();
		searchText.setBackground(new Color(128, 128, 128));
		searchText.setBounds(85, 142, 297, 28);
		contentPane.add(searchText);
		searchText.setColumns(10);
        show();
		btnSearchABill.addActionListener(new BillHandlerClass());

		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				String id = (String) list_1.getSelectedValue();
				searchText.setText(id);

				try {

					Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM billtable WHERE bill_id= '"+ id + "'");

					if (rs.next()) {
						bill_id=rs.getString("bill_id");
						date=rs.getString("date");
						details=rs.getString("details");
						totalamount=rs.getString("totalAmount");

					} else {
						System.out.println("there is no such item");
					}

				} catch (SQLException exception) {
					displaySQLErrors(exception);
				}
				description.setText("Bill_ID           :"+bill_id+"\n\n"+ "Issued On    :"+ date +"\n\n"+"Items (item name , amount , price)\n          "+details+"\n\nTotal          " +
						"     :"+totalamount);
				

			}

			private void displaySQLErrors(SQLException exception) {
				// TODO Auto-generated method stub
				
			}
		});


	}
	private void loadItems() {
		Vector v = new Vector();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT bill_id FROM billtable");
			while (rs.next()) {
				v.addElement(rs.getString("bill_id"));
			}
			rs.close();
		} catch (SQLException e) {
			//displaySQLErrors(e);
		}
		list_1.setListData(v);

	}
	private class BillHandlerClass implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String tempItem = searchText.getText();
			System.out.println(tempItem);

			try {

				Statement statement = connection.createStatement();
				ResultSet rs = statement
						.executeQuery("SELECT * FROM billtable WHERE bill_id = '"
								+ tempItem + "'");

				if (rs.next()) {
					bill_id=rs.getString("bill_id");
					date=rs.getString("data");
					details=rs.getString("details");
					totalamount=rs.getString("totalAmount");
					
				} else {
					//loadSimilarItems(tempItem);
					System.out.println("there is no such item");
				}

			} catch (SQLException exception) {
				//displaySQLErrors(exception);
			}

			description.setText("Bill_ID           :"+bill_id+"\n\n"+ "Issued On    :"+ date +"\n\n"+"Items (item name, amount, price)\n          "+details+"\n\nTotal               :"+totalamount);
			list_1.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					String id= (String) list_1.getSelectedValue();
					// otherwise doesnt work
					searchText.setText(id);

					try {

						Statement statement = connection.createStatement();
						ResultSet rs = statement
								.executeQuery("SELECT * FROM billtable WHERE bill_id = '"
										+ id + "'");

						if (rs.next()) {
							bill_id=rs.getString("bill_id");
							date=rs.getString("data");
							details=rs.getString("details");
							totalamount=rs.getString("totalAmount");

						} else {
							System.out.println("there is no such item");
						}

					} catch (SQLException exception) {
						//displaySQLErrors(exception);
					}
					description.setText("Bill_ID           :"+bill_id+"\n\n"+ "Issued On    :"+ date +"\n\n"+"Items (item name, amount , price)\n          "+details+"\n\nTotal               :"+totalamount);

				}
			});

		}

	}
	public void display(){
		setVisible(true);
	}
}
