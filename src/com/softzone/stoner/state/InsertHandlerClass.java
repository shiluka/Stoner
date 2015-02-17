package com.softzone.stoner.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertHandlerClass implements ActionListener {
	InsertWindow insert;

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {

			InsertWindow insert = new InsertWindow();

			String item_id = insert.getItem_idText().getText();
			String type = insert.getTypeText().getText();
			String name = insert.getNameText().getText();
			String supplier = insert.getSupplierText().getText();
			String location = insert.getLocationText().getText();
			String prize = insert.getPrizeText().getText();
			String amount = insert.getAmountText().getText();
			String description = insert.getDescriptionTextArea().getText();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/userAccounts", "root", "mysql");

				Statement statement = con.createStatement();

				statement.executeUpdate("INSERT INTO itemTable VALUES( "
						+ item_id + " , '" + type + "' , '" + name + "', '"
						+ supplier + "','" + location + "'," + prize + ","
						+ amount + ",NULL, '" + description + "' , now() ,''"
						+ ")");
				clearTexts();

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("Update success");

		} catch (SQLException insertException) {
			System.out.println(insertException);
		}
	}

	private void clearTexts() {
		insert.getItem_idText().setText("");
		insert.getTypeText().setText("");
		insert.getNameText().setText("");
		insert.getSupplierText().setText("");
		insert.getLocationText().setText("");
		insert.getPrizeText().setText("");
		insert.getDescriptionTextArea().setText("");
		insert.getAmountText().setText("");

	}

	public void setInsertWindow(InsertWindow insert) {
		this.insert = insert;

	}

}
