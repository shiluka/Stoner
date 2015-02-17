package com.softzone.stoner.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.sql.*;

public class BillToDatabase {
	private String billId;
	private String description;
	private double total;
	Timestamp timestamp;

	public void setData(String billId, ArrayList<BillRecord> recordlist,
			double totalAmount) {

		total = totalAmount;
		this.billId = billId;
		description = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = null;
		try {
			date = sdf.parse(billId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timestamp = new Timestamp(date.getTime());
		// t2 = timestamp.toString();

		for (int i = 0; i < recordlist.size(); i++) {
			description += "item:" + recordlist.get(i).getItemName()
					+ " amount:" + recordlist.get(i).getAmount() + " subtotal:"
					+ recordlist.get(i).getAmount() + "  ";
		}
	}

	public void updateDb() {
		Statement statement;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/userAccounts", "root", "mysql");
			statement = con.createStatement();
			statement
					.executeUpdate("INSERT INTO billTable (bill_id,date,details,totalAmount) VALUES ('"
							+ billId
							+ "','"
							+ timestamp
							+ "','"
							+ description
							+ "','" + total + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
