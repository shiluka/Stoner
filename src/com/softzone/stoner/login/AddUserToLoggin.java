package com.softzone.stoner.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.TransactionNotInProgressException;

public class AddUserToLoggin extends JFrame {

	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 300;
	private static final int X_ORIGIN = 250;
	private static final int Y_ORIGIN = 200;

	private Container contentPane;

	private JPanel addUserPanel;

	public AddUserToLoggin() {

		buildWindow();

	}

	public void buildWindow() {

		contentPane = getContentPane();
		getContentPane().setLayout(new BorderLayout(0, 0));

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(X_ORIGIN, Y_ORIGIN);
		setTitle("LoginSystem");
		setResizable(false);

		addUserPanel = new AddUserPanel();

		contentPane.add(addUserPanel, BorderLayout.CENTER);
		setVisible(true);

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(X_ORIGIN, Y_ORIGIN);
		setTitle("AddUserToLogginSystem");

	}
}
