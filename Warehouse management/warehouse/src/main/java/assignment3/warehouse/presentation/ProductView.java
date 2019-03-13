package assignment3.warehouse.presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * This is the Class which will generate frames for handling products in the
 * database.It is controlled by instances of WarehouseAdmin.
 */
@SuppressWarnings("serial")
public class ProductView extends JFrame {
	private JLabel idLabel = new JLabel("ProductID ");
	private JLabel nameLabel = new JLabel("Name ");
	private JLabel qntLabel = new JLabel("Quantity ");
	private JLabel priceLabel = new JLabel("Price ");
	private JTextField idTF = new JTextField("");
	private JTextField nameTF = new JTextField("");
	private JTextField qntTF = new JTextField("");
	private JTextField priceTF = new JTextField("");
	private JButton insertB = new JButton("Insert");
	private JButton editB = new JButton("Update");
	private JButton deleteB = new JButton("Delete");
	private JButton closeB = new JButton("Close");
	private JButton viewAllB = new JButton("View all");
	public JScrollPane tableSP = new JScrollPane();

	public ProductView() {
		idLabel.setBounds(70, 50, 100, 20);
		this.add(idLabel);
		idTF.setBounds(150, 50, 30, 20);
		this.add(idTF);
		nameLabel.setBounds(70, 102, 100, 20);
		this.add(nameLabel);
		nameTF.setBounds(150, 102, 100, 20);
		this.add(nameTF);
		qntLabel.setBounds(70, 150, 100, 20);
		this.add(qntLabel);
		qntTF.setBounds(150, 150, 50, 20);
		this.add(qntTF);
		priceLabel.setBounds(70, 200, 100, 20);
		this.add(priceLabel);
		priceTF.setBounds(150, 200, 50, 20);
		this.add(priceTF);
		insertB.setBounds(500, 50, 100, 30);
		this.add(insertB);
		editB.setBounds(500, 100, 100, 30);
		this.add(editB);
		deleteB.setBounds(500, 150, 100, 30);
		this.add(deleteB);
		closeB.setBounds(500, 250, 100, 30);
		this.add(closeB);
		viewAllB.setBounds(500, 200, 100, 30);
		this.add(viewAllB);

		this.setLayout(null);
		this.setTitle("Manage products");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	public void pressInsert(ActionListener a) {
		insertB.addActionListener(a);
	}

	public void pressEdit(ActionListener a) {
		editB.addActionListener(a);
	}

	public void pressDelete(ActionListener a) {
		deleteB.addActionListener(a);
	}

	public void pressViewAll(ActionListener a) {
		viewAllB.addActionListener(a);
	}

	public void pressClose(ActionListener a) {
		closeB.addActionListener(a);
	}

	public int getID() {
		return Integer.parseInt(idTF.getText());
	}

	public String getName() {
		return nameTF.getText();
	}

	public int getQuantity() {
		return Integer.parseInt(qntTF.getText());
	}

	public int getPrice() {
		return Integer.parseInt(priceTF.getText());
	}

	public void displayTable(JTable table) {
		tableSP = new JScrollPane(table);
		tableSP.setBounds(80, 300, 600, 400);
		tableSP.setVisible(true);
		this.add(tableSP);
		this.repaint();
	}

	public void clear() {
		idTF.setText("");
		nameTF.setText("");
		qntTF.setText("");
		priceTF.setText("");
		this.repaint();
	}
}
