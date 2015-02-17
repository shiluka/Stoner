package com.softzone.stoner.state;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

import java.util.Vector;

import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.PersistenceException;

import com.softzone.stoner.bill.Bill;
import com.softzone.stoner.bill.BillBook;
import com.softzone.stoner.bill.BillBookStorage;
import com.softzone.stoner.bill.BillBookWindowNew;
import com.softzone.stoner.database.DbConnector;

import com.softzone.stoner.login.AddUserToLoggin;
import com.softzone.stoner.login.UserList;
import com.softzone.stoner.purchasements.addInvoiceWindow;
import com.softzone.stoner.purchasements.addSupplierWindow;

import com.softzone.stoner.stock.StockManager;
import com.softzone.stoner.stock.StockWindow;

public class State extends JFrame{
	
	
	private Container contentPane;

	// frame parameters
	private static final int FRAME_WIDTH = 1002;
	private static final int FRAME_HEIGHT = 700;
	private static final int FRAME_X_ORIGIN = 175;
	private static final int FRAME_Y_ORIGIN = 15;
	// components of GUI
	private JTextField searchText;
	private JLabel picture;
	private JButton itemSearchButton, addToCartButton, printBillButton;
	private JLabel item_idLabel, typeLabel, nameLabel, supplierLabel,
			locationLabel, prizeLabel, amountLabel, timeStampLabel,
			billDetailsLable, itemDescriptionLable;
	private JTextField item_idText, typeText, nameText, supplierText,
			locationText, prizeText, amountText, timeStampText;
	private JList<?> itemList;
	private TextArea billTextArea, descriptionTextArea;

	private Bill bill;
	private BillBook billBook;
	private BillBookStorage billBookStorage;

	// billBook window
	private BillBookWindowNew billBookWindow;
	// for the total prize of bill
	private double totalPrize;
	// menu items
	private JMenu showMenu;
	private JMenu editMenu;
	private JMenu userMenu;
	private JMenu helpMenu;
	private JMenu supplierMenu;
	// jdbc connection
	private Connection connection;
	// for object mapping purposes (object binding into databases)
	private JDOManager jdoManager;
	private Database database;

	private StockWindow stockWindow;
	
	private static String userName="";
	
	private JLabel user;
	
	
	protected void StandardUserAuthority(){
		userMenu.setEnabled(false);
		editMenu.setEnabled(false);
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void buildSearchWindow() {
		
	
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminState.class.getResource("/images/Untitled-2.gif")));
		getContentPane().setBackground(new Color(105, 105, 105));

		stockWindow = new StockWindow();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		setTitle("MainWindow");
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
		typeText.setEditable(false);

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
		item_idText.setEditable(false);

		nameLabel = new JLabel("Name");
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setBounds(245, 120, 82, 19);
		getContentPane().add(nameLabel);
		nameText = new JTextField(15);
		nameText.setBackground(new Color(169, 169, 169));
		nameText.setBounds(348, 116, 244, 23);
		getContentPane().add(nameText);
		nameText.setEditable(false);

		supplierLabel = new JLabel("Supplier");
		supplierLabel.setForeground(new Color(255, 255, 255));
		supplierLabel.setBounds(245, 216, 82, 14);
		getContentPane().add(supplierLabel);
		supplierText = new JTextField(15);
		supplierText.setBackground(new Color(169, 169, 169));
		supplierText.setBounds(348, 213, 244, 20);
		getContentPane().add(supplierText);
		supplierText.setEditable(false);

		prizeLabel = new JLabel("Prize");
		prizeLabel.setForeground(new Color(255, 255, 255));
		prizeLabel.setBounds(245, 341, 82, 14);
		getContentPane().add(prizeLabel);
		prizeText = new JTextField(15);
		prizeText.setBackground(new Color(169, 169, 169));
		prizeText.setBounds(348, 338, 244, 20);
		getContentPane().add(prizeText);
		prizeText.setEditable(false);
		timeStampText = new JTextField(15);
		timeStampText.setBackground(new Color(169, 169, 169));
		timeStampText.setBounds(348, 244, 244, 20);
		getContentPane().add(timeStampText);
		timeStampText.setEditable(false);

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
		amountText.setEditable(false);

		locationLabel = new JLabel("Location");
		locationLabel.setForeground(new Color(255, 255, 255));
		locationLabel.setBounds(245, 279, 82, 14);
		getContentPane().add(locationLabel);
		locationText = new JTextField(15);
		locationText.setBackground(new Color(169, 169, 169));
		locationText.setBounds(348, 275, 244, 20);
		getContentPane().add(locationText);
		locationText.setEditable(false);

		billDetailsLable = new JLabel("Bill Details");
		billDetailsLable.setFont(new Font("MS Reference Sans Serif",
				Font.PLAIN, 12));
		billDetailsLable.setHorizontalAlignment(SwingConstants.CENTER);
		billDetailsLable.setBounds(648, 118, 314, 23);
		billDetailsLable.setForeground(new Color(255, 255, 255));
		billDetailsLable.setLabelFor(billDetailsLable);
		billDetailsLable.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(billDetailsLable);

		billTextArea = new TextArea();
		billTextArea.setBackground(new Color(128, 128, 128));
		billTextArea.setBounds(648, 156, 314, 168);
		billTextArea.setPreferredSize(new Dimension(290, 90));
		billTextArea.setEditable(false);
		contentPane.add(billTextArea);

		picture = new JLabel(createImageIcon("images/no_preview.png"));
		picture.setBounds(239, 420, 353, 220);
		picture.setPreferredSize(new Dimension(177, 100));
		getContentPane().add(picture);

		itemDescriptionLable = new JLabel("Description of the Item");
		itemDescriptionLable.setForeground(new Color(255, 255, 255));
		itemDescriptionLable.setHorizontalAlignment(SwingConstants.CENTER);
		itemDescriptionLable.setBounds(10, 401, 189, 23);
		contentPane.add(itemDescriptionLable);
		
		 user = new JLabel("You logged in as "+ userName);
	     user.setForeground(new Color(0,0,0));
	     user.setHorizontalAlignment(SwingConstants.CENTER);
	     user.setBounds(800,620, 189, 23);
		 contentPane.add(user);

		descriptionTextArea = new TextArea();
		descriptionTextArea.setForeground(new Color(255, 255, 255));
		descriptionTextArea.setBackground(new Color(128, 128, 128));
		descriptionTextArea.setBounds(10, 420, 189, 220);
		descriptionTextArea.setPreferredSize(new Dimension(300, 100));
		descriptionTextArea.setEditable(false);
		contentPane.add(descriptionTextArea);

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
					Statement statement;
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost/userAccounts", "root", "mysql");
					statement = con.createStatement();

					//Statement statement = connection.createStatement();
					ResultSet rs = statement
							.executeQuery("SELECT * FROM itemTable WHERE name = '"
									+ itemName + "'");

					//Item tempItemObject = null;

					if (rs.next()) {
						//tempItemObject = (Item) database.load(Item.class,
							//	Integer.parseInt(rs.getString("item_id")));
						
						item_idText.setText(rs.getString("item_id"));
						typeText.setText(rs.getString("type"));
						nameText.setText(rs.getString("name"));
						supplierText.setText(rs.getString("supplier"));
						locationText.setText(rs.getString("location"));
						prizeText.setText(rs.getString("prize"));
						amountText.setText(rs.getString("amount"));
						descriptionTextArea.setText(rs.getString("description"));
						picture.setIcon(createImageIcon("images/" + rs.getString("imageName")
								+ ".gif"));
					}

					//setInformationIntoTexts(tempItemObject);

					timeStampText.setText(rs.getString("ts"));

				} catch (SQLException exception) {
					displaySQLErrors(exception);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		loadItems();
		
		
		
		

		addToCartButton = new JButton("Add to Cart");
		addToCartButton.setForeground(new Color(255, 255, 255));
		addToCartButton.setBackground(new Color(169, 169, 169));
		addToCartButton.setBounds(648, 357, 137, 23);
		contentPane.add(addToCartButton);

		printBillButton = new JButton("Print Bill");
		printBillButton.setForeground(new Color(255, 255, 255));
		printBillButton.setBackground(new Color(169, 169, 169));
		printBillButton.setBounds(820, 357, 142, 23);
		contentPane.add(printBillButton);

		JLabel label = new JLabel(new ImageIcon(
				"C:\\Users\\King\\workspace\\SoftZoneProject\\bin\\images\\Untitled-1.gif"));
		label.setPreferredSize(new Dimension(177, 100));
		label.setBounds(10, 11, 988, 94);
		getContentPane().add(label);

		show();
		addToCartButton.addActionListener(new CartHandlerClass());
		printBillButton.addActionListener(new PrintBillHandlerClass());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// initialize jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("unable to connect to driver");
		}

		// initialize bill
		bill = new Bill();
		billBook = new BillBook();
		billBookStorage = new BillBookStorage("C:\\Users\\King\\workspace\\SoftZoneProject\\src\\com\\softzone\\stoner\\billBook_Test_1.data");

		try {
			billBook = billBookStorage.read();
		} catch (IOException ioException) {
			System.out.println("error in writing bill book");
		}

		// create menu bar
		createMenuBar();

	}

	//mathod of creating menu bar
	private void createMenuBar() {
		// menu creating
		// create show menu and its menu item
		createShowMenu();
		createEditMenu();
		createSupplierMenu();
		createUserMenu();
		createHelpMenu();
		// and add it to the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(showMenu);
		menuBar.add(editMenu);
		menuBar.add(supplierMenu);
		menuBar.add(userMenu);
		menuBar.add(helpMenu);
		
	}
	
	// connect to the database
		public void init() {
			DbConnector dbcon = new DbConnector();
			database = dbcon.getDatabase();
			connection = dbcon.getConnection();
		}

		// to load items in JList
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
				displaySQLErrors(e);
			}
			itemList.setListData(v);

		}
		
		
		

		// load similar items when the typed item is not there
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
				displaySQLErrors(e);
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
			descriptionTextArea.setText(tempItemObject.getDescription());
			picture.setIcon(createImageIcon("images/" + tempItemObject.getPic()
					+ ".gif"));
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
		
		private void whenUpdateButtonClicked() {
			addToCartButton.setVisible(true);
			printBillButton.setVisible(true);
			// itemSearchButton.setVisible(false);

			setTextsUnEditable();
		}

		private void whenInsertMenuClicked() {
			addToCartButton.setVisible(false);
			printBillButton.setVisible(false);

			searchText.setEnabled(false);
			itemSearchButton.setEnabled(false);

		}

		private void whenInsertButtonClicked() {
			addToCartButton.setVisible(true);
			printBillButton.setVisible(true);
			// itemSearchButton.setVisible(false);

			setTextsUnEditable();
			item_idText.setEditable(false);

			searchText.setEnabled(true);
			itemSearchButton.setEnabled(true);

		}
		
		private void setTextsUnEditable() {
			typeText.setEditable(false);
			nameText.setEditable(false);
			supplierText.setEditable(false);
			locationText.setEditable(false);
			prizeText.setEditable(false);
			amountText.setEditable(false);
		}

		private void whenUpdateMenuClicked() {
			addToCartButton.setVisible(false);
			printBillButton.setVisible(false);

			searchText.setEnabled(true);
			itemSearchButton.setEnabled(true);
			item_idText.setEditable(false);

		}


		

		// Returns an ImageIcon, or null if the path was invalid.
		protected static ImageIcon createImageIcon(String path) {
			java.net.URL imgURL = AdminState.class.getResource(path);
			if (imgURL != null) {
				return new ImageIcon(imgURL);
			} else {
				System.err.println("Couldn't find file: " + path);
				return null;
			}
		}

		private class PrintBillHandlerClass implements ActionListener {

			public void actionPerformed(ActionEvent e) {

				if (totalPrize == 0) {
					JOptionPane.showMessageDialog(null, "no items in the bill: ",
							"Error", JOptionPane.PLAIN_MESSAGE);
				} else {

					billTextArea.setText("");

					try {
						billBook = billBookStorage.read();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					billBook.addBill(bill);

					try {
						billBookStorage.write(billBook);
					} catch (IOException ioException) {
						System.out.println("error in writing bill book");
					}

					billTextArea.append("total Prize: " + bill.netTotalPrize);

					bill.write();

					bill = new Bill();

					totalPrize = 0;

					Thread eraseThread = new Thread(new Runnable() {
						public void run() {

						}

					});

					try {
						eraseThread.start();
						eraseThread.sleep(2000);
						billTextArea.setText("");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		}

		private class CartHandlerClass implements ActionListener {

			String s = "";

			public void actionPerformed(ActionEvent e) {
				String amountStr = JOptionPane.showInputDialog("Enter amount: ");

				if (amountStr.equals("") || Integer.parseInt(amountStr) == 0) {
					JOptionPane.showMessageDialog(null,
							"Please Enter correct amount: ", "Error",
							JOptionPane.PLAIN_MESSAGE);
				} else {

					int amountInt = Integer.parseInt(amountStr);

					String tempItem = searchText.getText();
					double tempPrize = 0;

					try {
						Statement statement;
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection(
								"jdbc:mysql://localhost/userAccounts", "root", "mysql");
						
						
						statement = con.createStatement();
						ResultSet rs = statement
								.executeQuery("SELECT * FROM itemTable WHERE name = '"
										+ tempItem + "'");
						int currentAmount = 0;
						int buyAmount = 0;
						if (rs.next()) {
							tempPrize = Double.parseDouble(rs.getString("prize"));
							currentAmount = Integer
									.parseInt(rs.getString("amount"));

							if ((currentAmount - amountInt < 0)) {
								JOptionPane.showMessageDialog(null,
										"not enough items in stock: ", "Error",
										JOptionPane.PLAIN_MESSAGE);
							} else {
								// create bill
								bill.addBillRecord(tempItem, amountInt, tempPrize);

								rs.close();
								/*int i = statement.executeUpdate("UPDATE itemTable "
										+ "SET amount = "
										+ (currentAmount - amountInt)
										+ " WHERE name = '" + tempItem + "'");*/
								
								updateTable(currentAmount - amountInt,tempItem);
								setTexts(tempItem);
								
								
								totalPrize += amountInt * tempPrize;
								s = tempItem + "  " + amountInt + "  "
										+ amountInt * tempPrize + "\n";
								billTextArea.append(s);
								System.out.println(s);


							}
							
						} else {
							System.out.println("there is no such item");
						}

					} catch (SQLException exception) {
						displaySQLErrors(exception);
					} catch (ClassNotFoundException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}

			}
		}
		
		public void setTexts(String tempItem){
			Statement statement;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/userAccounts", "root", "mysql");
				statement = con.createStatement();

				ResultSet rs = statement
						.executeQuery("SELECT * FROM itemTable WHERE name = '"
								+ tempItem + "'");
				if (rs.next()) {

					
					amountText.setText(rs.getString("amount"));
					rs.close();

					/*totalPrize += amountInt * tempPrize;
					s = tempItem + "  " + amountInt + "  "
							+ amountInt * tempPrize + "\n";
					billTextArea.append(s);
					System.out.println(s);*/

				} else {
					System.out.println("there is no such item");
				}

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
		
		
		private void updateTable(int amount,String tempItem){
			Statement statement;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost/userAccounts", "root", "mysql");
				statement = con.createStatement();

				/*statement.executeUpdate("UPDATE itemTable " + "SET type='"
						+ type + "', " + "name='" + name + "'," + "supplier='"
						+ supplier + "'," + "location='" + location + "',"
						+ "prize='" + prize + "'," + "amount='" + amount + "',"
						+ "ts = now()," + "description='" + description + "' "
						+ "WHERE item_id = '" + item_id + "'");*/
				
				statement.executeUpdate("UPDATE itemTable "
						+ "SET amount = "
						+ amount
						+ " WHERE name = '" + tempItem + "'");

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
					displaySQLErrors(exception);
				} catch (PersistenceException persistenceException) {
					persistenceException.printStackTrace();
				}

				itemList.addListSelectionListener(new ListSelectionListener() {

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
							displaySQLErrors(exception);
						} catch (PersistenceException persistenceException) {
							persistenceException.printStackTrace();
						}

					}
				});

			}
		}

		private void displaySQLErrors(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
		// making menu items
			private void createSupplierMenu() {
				JMenuItem item;
				supplierMenu = new JMenu("Supplier");
				item = new JMenuItem("Add Supplier"); 
				item.addActionListener(new MenuHandlerClass());
				supplierMenu.add(item);

				item = new JMenuItem("Add Invoice");
				item.addActionListener(new MenuHandlerClass());
				supplierMenu.add(item);
			}

		// making menu items
		private void createShowMenu() {
			JMenuItem item;
			showMenu = new JMenu("Show");
			item = new JMenuItem("Bill Book"); // Bill Book
			item.addActionListener(new MenuHandlerClass());
			showMenu.add(item);

			item = new JMenuItem("Stock");
			item.addActionListener(new MenuHandlerClass());
			showMenu.add(item);
		}

		// making menu items
		private void createUserMenu() {
			JMenuItem item;
			userMenu = new JMenu("Users");
			item = new JMenuItem("Add User");
			item.addActionListener(new MenuHandlerClass());
			userMenu.add(item);

			item = new JMenuItem("User List");
			item.addActionListener(new MenuHandlerClass());
			userMenu.add(item);
		}
		
		// making menu items
			private void createHelpMenu() {
				JMenuItem item;
				helpMenu = new JMenu("Help");
				item = new JMenuItem("About");
				item.addActionListener(new MenuHandlerClass());
				helpMenu.add(item);

			}

		private void createEditMenu() {
			JMenuItem item;
			editMenu = new JMenu("Item");

			item = new JMenuItem("Insert");
			item.addActionListener(new MenuHandlerClass());
			editMenu.add(item);

			item = new JMenuItem("Update");
			item.addActionListener(new MenuHandlerClass());
			editMenu.add(item);

			item = new JMenuItem("Delete");
			item.addActionListener(new MenuHandlerClass());
			editMenu.add(item);
		}

		private class MenuHandlerClass implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				String menuName;
				menuName = event.getActionCommand();
				if (menuName.equals("Bill Book")) {
					System.out.println("Show Bill book");
					billBookWindow = new BillBookWindowNew();
					billBookWindow.display();
				} else if (menuName.equals("Stock")) {

					StockManager sm = new StockManager(connection);
					sm.GetStockDetailsFromDatabase();
					String goingover = sm.goingover();
					String outofstock = sm.outofstock();
					String haveenough = sm.haveenough();

					stockWindow.getStockOver().setText(outofstock);

					stockWindow.getStockGoingover().setText(goingover);
					stockWindow.getStockOther().setText(haveenough);
					stockWindow.setVisible(true);
				}

				else if (menuName.equals("Delete")) {
					EditItems delete = new EditItems();
					delete.startUpdateWindow();
					delete.status(true);

				} else if (menuName.equals("Update")) {
					EditItems update = new EditItems();
					update.startUpdateWindow();
					update.status(true);

					setVisible(false);
				} else if (menuName.equals("Insert")) {
					InsertWindow insert = new InsertWindow();
					insert.startInsertWindow();
					insert.status(true);
					setVisible(false);
				} else if (menuName.equals("Add User")) {
					new AddUserToLoggin().buildWindow();

				} else if (menuName.equals("User List")) {
					new UserList().buildWindow();

				}
				else if (menuName.equals("About")) {
					new Help().display();

				}
				else if (menuName.equals("Add Supplier")) {
					addSupplierWindow asw = addSupplierWindow.getUniqueAddSupWindow();
					asw.setVisible(true);

				}
				else if (menuName.equals("Add Invoice")) {
					new addInvoiceWindow().setVisible(true);

				}

				else {
				}

			}
		}

		private class UpdateHandlerClass implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE itemTable "
							+ "SET type='" + typeText.getText() + "', " + "name='"
							+ nameText.getText() + "'," + "supplier='"
							+ supplierText.getText() + "'," + "location='"
							+ locationText.getText() + "'," + "prize='"
							+ prizeText.getText() + "'," + "amount='"
							+ amountText.getText() + "'," + "ts = now() "
							+ "WHERE item_id = '" + item_idText.getText() + "'");
					descriptionTextArea.setText("");
					descriptionTextArea.setText("Updated " + i
							+ "rows successfully");
					clearTexts();
					itemList.removeAll();
					loadItems();
					whenUpdateButtonClicked();
				} catch (SQLException updateException) {
					displaySQLErrors(updateException);
				}
			}
		}

		public ItemHandlerClass getItemHandler() {
			return (new ItemHandlerClass());
		}

		public UpdateHandlerClass getUpdateHandler() {
			return (new UpdateHandlerClass());
		}


}
