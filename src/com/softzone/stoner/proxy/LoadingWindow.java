package com.softzone.stoner.proxy;

//to build gui

import javax.swing.*;

import com.softzone.stoner.state.AdminState;

import java.awt.*;

public class LoadingWindow extends JFrame {

	// frame parameters
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 285;
	private static final int FRAME_X_ORIGIN = 150;
	private static final int FRAME_Y_ORIGIN = 150;

	// components of GUI
	private JLabel picture1, picture2;
	private Container contentPane;

	public LoadingWindow() {
		buildProxyWindow();
	}

	private void buildProxyWindow() {
		
		
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		setTitle("MainWindow");
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminState.class.getResource("/images/no_preview.png")));
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JPanel first = new JPanel(new GridLayout(1, 2));

		picture2 = new JLabel(createImageIcon("proxyImages/soft-logo.gif"));
		picture2.setPreferredSize(new Dimension(495, 235));

		picture1 = new JLabel(createImageIcon("proxyImages/loading-jar.gif"));
		picture1.setPreferredSize(new Dimension(160, 235));

		first.add(picture2);
		first.add(picture1);

		contentPane.add(first);
		contentPane.setBackground(Color.WHITE);

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
