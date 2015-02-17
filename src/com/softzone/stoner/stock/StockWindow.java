package com.softzone.stoner.stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StockWindow extends JFrame {

	private JPanel contentPane;
	TextArea over, goingover, other;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StockWindow() {
		setTitle("Stock Details");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				StockWindow.class.getResource("/images/Untitled-2.gif")));

		setBounds(175, 15, 1000, 700);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		over = new TextArea();
		over.setEditable(false);
		over.setForeground(new Color(0, 0, 0));
		over.setBackground(new Color(128, 128, 128));
		over.setBounds(98, 177, 776, 104);
		contentPane.add(over);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPane.setText("Out of stock Items");
		textPane.setForeground(new Color(0, 0, 0));
		textPane.setBackground(SystemColor.controlDkShadow);
		textPane.setBounds(393, 131, 104, 20);
		contentPane.add(textPane);

		JTextPane txtpnOtherItems = new JTextPane();
		txtpnOtherItems.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnOtherItems.setText("Other Items");
		txtpnOtherItems.setForeground(new Color(0, 0, 0));
		txtpnOtherItems.setBackground(SystemColor.controlDkShadow);
		txtpnOtherItems.setBounds(393, 483, 104, 20);
		contentPane.add(txtpnOtherItems);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPane_2.setText("Stocks going over");
		textPane_2.setForeground(new Color(0, 0, 0));
		textPane_2.setBackground(SystemColor.controlDkShadow);
		textPane_2.setBounds(393, 300, 149, 20);
		contentPane.add(textPane_2);

		JLabel label = new JLabel(
				new ImageIcon(
						"C:\\Users\\King\\workspace\\SoftZoneProject\\src\\com\\softzone\\stoner\\images\\Untitled-1.gif"));
		label.setPreferredSize(new Dimension(177, 100));
		label.setBounds(10, 11, 988, 94);
		getContentPane().add(label);

		goingover = new TextArea();
		goingover.setForeground(new Color(0, 0, 0));
		goingover.setBackground(new Color(128, 128, 128));
		goingover.setBounds(98, 344, 776, 117);
		contentPane.add(goingover);

		other = new TextArea();
		other.setForeground(new Color(0, 0, 0));
		other.setBackground(Color.GRAY);
		// other.setBounds(98, 344, 776, 117);
		other.setBounds(98, 530, 776, 111);

		contentPane.add(other);

		JTextPane txtpnItemName = new JTextPane();
		txtpnItemName
				.setText("Item Id                              Item Name                              Amount                              Supplier                              Last Added Date of the Item");
		txtpnItemName.setForeground(Color.WHITE);
		txtpnItemName.setBackground(SystemColor.controlDkShadow);
		txtpnItemName.setBounds(98, 151, 752, 20);
		contentPane.add(txtpnItemName);

		JTextPane txtpnItemIdtitem = new JTextPane();
		txtpnItemIdtitem
				.setText("Item Id                              Item Name                              Amount                              Supplier                              Last Added Date of the Item");
		txtpnItemIdtitem.setForeground(Color.WHITE);
		txtpnItemIdtitem.setBackground(SystemColor.controlDkShadow);
		txtpnItemIdtitem.setBounds(98, 504, 752, 20);
		contentPane.add(txtpnItemIdtitem);

		JTextPane textPane_3 = new JTextPane();
		textPane_3
				.setText("Item Id                              Item Name                              Amount                              Supplier                              Last Added Date of the Item");
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setBackground(SystemColor.controlDkShadow);
		textPane_3.setBounds(98, 318, 752, 20);
		contentPane.add(textPane_3);
	}

	public TextArea getStockOver() {
		return over;
	}

	public TextArea getStockGoingover() {
		return goingover;
	}

	public TextArea getStockOther() {
		return other;
	}

}
