package com.softzone.stoner.proxy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;

public class LoadingWindowNew extends JFrame {

	// frame parameters
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
	private static final int FRAME_X_ORIGIN = 175;
	private static final int FRAME_Y_ORIGIN = 15;

	// components of GUI
	private JLabel picture1, picture2, picture3;
	private Container contentPane;

	/**
	 * Create the frame.
	 */
	public LoadingWindowNew() {

		JLabel picture1;

		JLabel picture2;

		picture1 = new JLabel(createImageIcon("proxyImages/soft-logo.gif"));
		picture2 = new JLabel(createImageIcon("proxyImages/loading-jar.gif"));

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		setTitle("MainWindow");
		setResizable(false);

		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JPanel first = new JPanel(new GridLayout(1, 2));

		picture2 = new JLabel(createImageIcon("proxyImages/soft-logo.gif"));
		picture2.setPreferredSize(new Dimension(495, 235));

		picture1 = new JLabel(createImageIcon("proxyImages/loading-jar.gif"));
		picture1.setPreferredSize(new Dimension(200, 550));

		first.add(picture2);
		first.add(picture1);

		contentPane.add(first);
		contentPane.setBackground(Color.GRAY);
		picture1.setBackground(Color.WHITE);
		picture2.setBackground(Color.WHITE);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	// Returns an ImageIcon, or null if the path was invalid.
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = LoadingWindow.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
