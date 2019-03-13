package assignment3.warehouse.presentation;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class is the first part of the gui which the user will see.Objects of
 * this class are frames on which the user can chose between managing clients,
 * managing products and placing orders.
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private JButton orderB = new JButton("Place an order");
	private JButton clientB = new JButton("Manage customers");
	private JButton productB = new JButton("Manage products");

	public View() {
		orderB.setBounds(90, 50, 150, 30);
		this.add(orderB);
		clientB.setBounds(90, 100, 150, 30);
		this.add(clientB);
		productB.setBounds(90, 150, 150, 30);
		this.add(productB);
		this.setLayout(null);
		this.setTitle("Warehouse manager");
		this.setSize(350, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void orderListener(ActionListener a) {
		orderB.addActionListener(a);
	}

	public void clientListener(ActionListener a) {
		clientB.addActionListener(a);
	}

	public void productListener(ActionListener a) {
		productB.addActionListener(a);
	}

}
