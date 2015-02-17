package com.softzone.stoner.state;

import java.awt.Container;

import javax.swing.JFrame;


//to build gui
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.PersistenceException;

import com.softzone.stoner.bill.*;
import com.softzone.stoner.database.*;
import com.softzone.stoner.login.*;
import com.softzone.stoner.stock.*;


public class StandardUserState extends State{

	public void buildSearchWindow() {
		super.buildSearchWindow();
		super.StandardUserAuthority();
	}
	
	public StandardUserState(){
		
	}

}
