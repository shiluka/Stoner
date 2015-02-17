package com.softzone.stoner.database;

import java.sql.Connection;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDOManager;


public class DbConnector {
	private static Database database;
	private static Connection connection;
	public DbConnector(){
		connectToDB();
		}
	//connect to the database
	
	private void connectToDB(){
		try {
			
			JDOManager jdoManager;
			JDOManager.loadConfiguration("jdo-conf.xml");
			jdoManager = JDOManager.createInstance("userAccounts");
			database = jdoManager.getDatabase();
			database.begin();
			connection = database.getJdbcConnection();
			
			if(connection ==null){
				System.out.println("sdgsfoibohsidhosidbn");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Database getDatabase(){return database;}
	public Connection getConnection(){return connection;}
}
