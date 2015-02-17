package com.softzone.stoner.state;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.PersistenceException;

import com.softzone.stoner.database.*;


public class EditItems extends JFrame {
	private static JTextField searchText;
	private static JLabel picture;
	private static JButton itemSearchButton;
	private static JLabel item_idLabel, typeLabel, nameLabel, supplierLabel,
			locationLabel, prizeLabel, amountLabel, timeStampLabel,
			itemDescriptionLable;
	private static JTextField item_idText, typeText, nameText, supplierText,
			locationText, prizeText, amountText, timeStampText;
	private static JList<?> itemList;
	private static Container contentPane;
	private static JTextField descriptionArea;
	private static JButton UpdateButton;
	private static JButton DeleteButton;
	private static JButton backButton;
	private Connection connection;
	private Database database;

	public EditItems() {
	}

	public void startUpdateWindow() {
		// TODO Auto-generated method stub

		DbConnector db = new DbConnector();
		database = db.getDatabase();
		connection = db.getConnection();

		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"C:\\Users\\King\\workspace\\SoftZoneProject\\bin\\images\\Untitled-2.jpg"));
		getContentPane().setBackground(new Color(105, 105, 105));

		setSize(1002, 700);
		setLocation(175, 15);

		setTitle("Update OR Delete Item");
		setResizable(false);

		contentPane = getContentPane();
		getContentPane().setLayout(null);

		itemSearchButton = new JButton("Item Search");
		itemSearchButton.setForeground(new Color(255, 255, 255));
		itemSearchButton.setBackground(new Color(128, 128, 128));
		itemSearchButton.setBounds(10, 168, 189, 28);
		getContentPane().add(itemSearchButton);

		itemSearchButton.addActionListener(new ItemHandlerClass());

		searchText = new JTextField("", 15);
		searchText.setBackground(new Color(128, 128, 128));
		searchText.setBounds(10, 116, 189, 23);
		getContentPane().add(searchText);
		typeText = new JTextField(15);
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
		item_idText.setBackground(new Color(169, 169, 169));
		item_idText.setBounds(348, 151, 244, 20);
		getContentPane().add(item_idText);

		nameLabel = new JLabel("Name");
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setBounds(245, 120, 82, 19);
		getContentPane().add(nameLabel);
		nameText = new JTextField(15);
		nameText.setBackground(new Color(169, 169, 169));
		nameText.setBounds(348, 116, 244, 23);
		getContentPane().add(nameText);

		supplierLabel = new JLabel("Supplier");
		supplierLabel.setForeground(new Color(255, 255, 255));
		supplierLabel.setBounds(245, 216, 82, 14);
		getContentPane().add(supplierLabel);
		supplierText = new JTextField(15);
		supplierText.setBackground(new Color(169, 169, 169));
		supplierText.setBounds(348, 213, 244, 20);
		getContentPane().add(supplierText);

		prizeLabel = new JLabel("Prize");
		prizeLabel.setForeground(new Color(255, 255, 255));
		prizeLabel.setBounds(245, 341, 82, 14);
		getContentPane().add(prizeLabel);
		prizeText = new JTextField(15);
		prizeText.setBackground(new Color(169, 169, 169));
		prizeText.setBounds(348, 338, 244, 20);
		getContentPane().add(prizeText);
		timeStampText = new JTextField(15);
		timeStampText.setBackground(new Color(169, 169, 169));
		timeStampText.setBounds(348, 244, 244, 20);
		getContentPane().add(timeStampText);

		timeStampLabel = new JLabel("TimeStamp");
		timeStampLabel.setForeground(new Color(255, 255, 255));
		timeStampLabel.setBounds(245, 247, 82, 14);
		getContentPane().add(timeStampLabel);

		amountLabel = new JLabel("Amount");
		amountLabel.setForeground(new Color(255, 255, 255));
		amountLabel.setBounds(245, 310, 82, 14);
		getContentPane().add(amountLabel);
		amountText = new JTextField(15);
		amountText.setBackground(new Color(169, 169, 169));
		amountText.setBounds(348, 307, 244, 20);
		getContentPane().add(amountText);

		locationLabel = new JLabel("Location");
		locationLabel.setForeground(new Color(255, 255, 255));
		locationLabel.setBounds(245, 279, 82, 14);
		getContentPane().add(locationLabel);
		locationText = new JTextField(15);
		locationText.setBackground(new Color(169, 169, 169));
		locationText.setBounds(348, 275, 244, 20);
		getContentPane().add(locationText);

		picture = new JLabel(createImageIcon("images/no_preview.png"));
		picture.setBounds(239, 420, 353, 220);
		picture.setPreferredSize(new Dimension(177, 100));
		getContentPane().add(picture);

		itemDescriptionLable = new JLabel("Description of the Item");
		itemDescriptionLable.setForeground(new Color(255, 255, 255));
		itemDescriptionLable.setHorizontalAlignment(SwingConstants.CENTER);
		itemDescriptionLable.setBounds(645, 116, 341, 23);
		contentPane.add(itemDescriptionLable);

		JScrollPane itemListScrollPane = new JScrollPane();
		itemListScrollPane.setBounds(10, 222, 189, 168);
		contentPane.add(itemListScrollPane);

		itemList = new JList();
		itemList.setBackground(new Color(128, 128, 128));
		itemListScrollPane.setViewportView(itemList);
		itemList.setVisibleRowCount(3);

		itemList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				String itemName = (String) itemList.getSelectedValue();
				searchText.setText(itemName);

				try {

					Statement statement = connection.createStatement();
					ResultSet rs = statement
							.executeQuery("SELECT * FROM itemTable WHERE name = '"
									+ itemName + "'");

					Item tempItemObject = null;

					if (rs.next()) {
						tempItemObject = (Item) database.load(Item.class,
								Integer.parseInt(rs.getString("item_id")));
					}

					setInformationIntoTexts(tempItemObject);

					timeStampText.setText(rs.getString("ts"));

				} catch (SQLException exception) {
					System.out.println(exception);
				} catch (PersistenceException persistenceException) {
					persistenceException.printStackTrace();
				}

			}
		});
		loadItems();

		JLabel label = new JLabel(
				new ImageIcon(
						"C:\\Users\\King\\workspace\\SoftZoneProject\\bin\\images\\Untitled-1.gif"));
		label.setPreferredSize(new Dimension(177, 100));
		label.setBounds(10, 11, 988, 94);
		getContentPane().add(label);

		descriptionArea = new JTextField();
		descriptionArea.setForeground(new Color(255, 255, 255));
		descriptionArea.setBackground(new Color(128, 128, 128));
		descriptionArea.setBounds(645, 164, 341, 226);
		getContentPane().add(descriptionArea);
		descriptionArea.setColumns(10);

		UpdateButton = new JButton("Update");
		UpdateButton.setForeground(new Color(255, 255, 255));
		UpdateButton.setBackground(new Color(128, 128, 128));
		UpdateButton.setBounds(645, 494, 89, 23);
		getContentPane().add(UpdateButton);
		UpdateButton.addActionListener(new updateListener());

		DeleteButton = new JButton("Delete");
		DeleteButton.setForeground(new Color(255, 255, 255));
		DeleteButton.setBackground(new Color(128, 128, 128));
		DeleteButton.setBounds(897, 494, 89, 23);
		getContentPane().add(DeleteButton);
		DeleteButton.addActionListener(new deleteHandler());

		backButton = new JButton("Back");
		backButton.setBackground(new Color(128, 128, 128));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBounds(776, 617, 89, 23);
		getContentPane().add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminState main = new AdminState();
				main.init();
				main.buildSearchWindow();
				//main.adminAuthority();
				main.setVisible(true);
				setVisible(false);
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void loadItems() {
		Vector v = new Vector();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT name FROM itemTable");
			while (rs.next()) {
				v.addElement(rs.getString("name"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		itemList.setListData(v);

	}

	private void loadSimilarItems(String itemName) {
		Vector v = new Vector();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM itemTable WHERE name LIKE '"
							+ itemName.substring(0, 1) + "%'");
			while (rs.next()) {
				v.addElement(rs.getString("name"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		itemList.removeAll();
		itemList.setListData(v);
	}

	private void setInformationIntoTexts(Item tempItemObject) {
		item_idText.setText(String.valueOf(tempItemObject.getItemId()));
		typeText.setText(tempItemObject.getType());
		nameText.setText(tempItemObject.getName());
		supplierText.setText(tempItemObject.getSupplier());
		locationText.setText(tempItemObject.getLocation());
		prizeText.setText(String.valueOf(tempItemObject.getPrize()));
		amountText.setText(String.valueOf(tempItemObject.getAmount()));
		descriptionArea.setText(tempItemObject.getDescription());
		picture.setIcon(createImageIcon("images/" + tempItemObject.getPic()
				+ ".gif"));
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = AdminState.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void status(boolean status) {
		setVisible(status);

	}

	private class ItemHandlerClass implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String tempItem = searchText.getText();
			System.out.println(tempItem);

			try {

				Statement statement = connection.createStatement();
				ResultSet rs = statement
						.executeQuery("SELECT * FROM itemTable WHERE name = '"
								+ tempItem + "'");

				Item tempItemObject = null;

				if (rs.next()) {
					tempItemObject = (Item) database.load(Item.class,
							Integer.parseInt(rs.getString("item_id")));
				} else {
					loadSimilarItems(tempItem);
				}

				setInformationIntoTexts(tempItemObject);
				timeStampText.setText(rs.getString("ts"));

			} catch (SQLException exception) {
				System.out.println(exception);
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			}

			itemList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent event) {
					String itemName = (String) itemList.getSelectedValue();
					// otherwise doesnt work
					searchText.setText(itemName);

					try {

						Statement statement = connection.createStatement();
						ResultSet rs = statement
								.executeQuery("SELECT * FROM itemTable WHERE name = '"
										+ itemName + "'");

						Item tempItemObject = null;

						if (rs.next()) {
							tempItemObject = (Item) database.load(Item.class,
									Integer.parseInt(rs.getString("item_id")));
						}

						setInformationIntoTexts(tempItemObject);
						timeStampText.setText(rs.getString("ts"));

					} catch (SQLException exception) {
						System.out.println(exception);
					} catch (PersistenceException persistenceException) {
						persistenceException.printStackTrace();
					}
				}
			});
		}
	}

	private class updateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String item_id = item_idText.getText();
			String type = typeText.getText();
			String name = nameText.getText();
			String supplier = supplierText.getText();
			String location = locationText.getText();
			String prize = prizeText.getText();
			String amount = amountText.getText();
			String description = descriptionArea.getText();

			Statement statement;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/userAccounts", "root", "mysql");
				statement = con.createStatement();

				statement.executeUpdate("UPDATE itemTable " + "SET type='"
						+ type + "', " + "name='" + name + "'," + "supplier='"
						+ supplier + "'," + "location='" + location + "',"
						+ "prize='" + prize + "'," + "amount='" + amount + "',"
						+ "ts = now()," + "description='" + description + "' "
						+ "WHERE item_id = '" + item_id + "'");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
				;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void clearTexts() {
		item_idText.setText("");
		typeText.setText("");
		nameText.setText("");
		supplierText.setText("");
		locationText.setText("");
		prizeText.setText("");
		amountText.setText("");
		timeStampText.setText("");
	}

	private class deleteHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/userAccounts", "root", "mysql");

				Statement statement = con.createStatement();
				int i = statement
						.executeUpdate("DELETE FROM itemTable WHERE name = '"
								+ nameText.getText() + "'");
				descriptionArea.setText("");
				descriptionArea.setText("Deleted " + i + "rows successfully");
				clearTexts();

				itemList.removeAll();
				loadItems();

			} catch (SQLException deleteException) {
				System.out.println(deleteException);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
