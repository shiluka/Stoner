package com.softzone.stoner.stock;

//to connect mySQL database
import java.sql.*;
import java.util.ArrayList;
import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.Database;

import com.softzone.stoner.state.Item;


//Object mapping(binding purposes)

public class StockManager {
	Connection connect;
	Database database;
	String all = "";
	String goingover = "";
	String outofstock = "";
	String haveenough = "";

	public StockManager(Connection connect) {
		this.connect = connect;
	}

	public void GetStockDetailsFromDatabase() {

		try {

			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM itemTable");
			ArrayList<String> less = new ArrayList();
			ArrayList<String> over = new ArrayList();
			ArrayList<String> fine = new ArrayList();
			String item_idText;
			String itemname;
			String amount;
			String supplier;
			String ts;
			Item tempItemObject = null;
			while (rs.next()) {

				item_idText = rs.getString("item_id");
				itemname = rs.getString("name");
				amount = rs.getString("amount");
				supplier = rs.getString("supplier");
				ts = rs.getString("ts");
				if (Integer.parseInt(amount) == 0) {

					over.add(item_idText + "                                 "
							+ itemname
							+ "                                         "
							+ amount + "                                      "
							+ supplier + "                              " + ts);

				} else if (Integer.parseInt(amount) < 50) {
					less.add(item_idText
							+ "                                   " + itemname
							+ "                                          "
							+ amount + "                                      "
							+ supplier + "                              " + ts);
				}

				else if (Integer.parseInt(amount) >= 50) {
					fine.add(item_idText + "                                 "
							+ itemname
							+ "                                          "
							+ amount + "                                      "
							+ supplier + "                              " + ts);

				}
			}

			for (int i = 0; i < less.size(); i++) {
				goingover = goingover + less.get(i) + "\n";

			}
			for (int i = 0; i < over.size(); i++) {
				outofstock = outofstock + over.get(i) + "\n";

			}

			for (int i = 0; i < fine.size(); i++) {
				haveenough = haveenough + fine.get(i) + "\n";

			}

		} catch (SQLException exception) {

		}

		// return all;
	}

	public String goingover() {
		return goingover;
	}

	public String outofstock() {
		return outofstock;
	}

	public String haveenough() {
		return haveenough;
	}

}
