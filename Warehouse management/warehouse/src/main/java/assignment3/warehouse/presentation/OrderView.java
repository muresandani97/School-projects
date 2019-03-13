package assignment3.warehouse.presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 * This is the Class which will generate frames for handling orders in the
 * database.It is controlled by instances of OrderProcessing.
 */
@SuppressWarnings("serial")
public class OrderView extends JFrame {

	private JLabel idLabel = new JLabel("OrderID ");
	private JLabel pidLabel = new JLabel("ProductID ");
	private JLabel cidLabel = new JLabel("CustomerID ");
	private JLabel qntLabel = new JLabel("Quantity ");
	private JLabel statusLabel = new JLabel("Status ");
	private JTextField idTF = new JTextField("");
	private JTextField pidTF = new JTextField("");
	private JTextField cidTF = new JTextField("");
	private JTextField qntTF = new JTextField("");
	private JTextField statusTF = new JTextField("");
	private JButton insertB = new JButton("Place order");
	private JButton editB = new JButton("Edit order");
	private JButton deleteB = new JButton("Delete order");
	private JButton closeB = new JButton("Close");
	private JButton viewAllB = new JButton("View all");
	public JScrollPane tableSP= new JScrollPane();

	public OrderView() {
		idLabel.setBounds(70, 50, 100, 20);
		this.add(idLabel);
		idTF.setBounds(150, 50, 30, 20);
		this.add(idTF);
		pidLabel.setBounds(70, 90, 100, 20);
		this.add(pidLabel);
		pidTF.setBounds(150, 90, 30, 20);
		this.add(pidTF);
		cidLabel.setBounds(70, 130, 100, 20);
		this.add(cidLabel);
		cidTF.setBounds(150, 130, 30, 20);
		this.add(cidTF);
		qntLabel.setBounds(70, 170, 100, 20);
		this.add(qntLabel);
		qntTF.setBounds(150, 170, 50, 20);
		this.add(qntTF);
		statusLabel.setBounds(70, 210, 100, 20);
		this.add(statusLabel);
		statusTF.setBounds(150, 210, 100, 20);
		this.add(statusTF);
		insertB.setBounds(500, 50, 120, 30);
		this.add(insertB);
		editB.setBounds(500, 100, 120, 30);
		this.add(editB);
		deleteB.setBounds(500, 150, 120, 30);
		this.add(deleteB);
		closeB.setBounds(500, 250, 120, 30);
		this.add(closeB);
		viewAllB.setBounds(500, 200, 120, 30);
		this.add(viewAllB);
		this.setLayout(null);
		this.setTitle("Place orders");
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

	public int getProductID() {
		return Integer.parseInt(pidTF.getText());
	}

	public int getCustomerID() {
		return Integer.parseInt(cidTF.getText());
	}

	public int getQuantity() {
		return Integer.parseInt(qntTF.getText());
	}

	public String getStatus() {
		return statusTF.getText();
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
		pidTF.setText("");
		cidTF.setText("");
		qntTF.setText("");
		statusTF.setText("");
		this.repaint();
	}

}
