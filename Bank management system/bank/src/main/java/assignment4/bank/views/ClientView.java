package assignment4.bank.views;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ClientView extends JFrame {
	private JLabel idLabel = new JLabel("ClientID ");
	private JLabel fnLabel = new JLabel("Firstname ");
	private JLabel lnLabel = new JLabel("Lastname ");
	private JLabel addressLabel = new JLabel("Address ");
	private JLabel mailLabel = new JLabel("Email ");
	private JTextField idTF = new JTextField("");
	private JTextField fnTF = new JTextField("");
	private JTextField lnTF = new JTextField("");
	private JTextField addressTF = new JTextField("");
	private JTextField mailTF = new JTextField("");
	private JButton insertB = new JButton("Insert");
	private JButton editB = new JButton("Update");
	private JButton deleteB = new JButton("Delete");
	private JButton closeB = new JButton("Close");
	private JButton viewAllB = new JButton("View all");
	public JScrollPane tableSP = new JScrollPane();
	public JTable table = new JTable();

	public ClientView() {
		idLabel.setBounds(70, 50, 100, 20);
		this.add(idLabel);
		idTF.setBounds(150, 50, 30, 20);
		this.add(idTF);
		fnLabel.setBounds(70, 90, 100, 20);
		this.add(fnLabel);
		fnTF.setBounds(150, 90, 100, 20);
		this.add(fnTF);
		lnLabel.setBounds(70, 130, 100, 20);
		this.add(lnLabel);
		lnTF.setBounds(150, 130, 100, 20);
		this.add(lnTF);
		addressLabel.setBounds(70, 170, 100, 20);
		this.add(addressLabel);
		addressTF.setBounds(150, 170, 200, 20);
		this.add(addressTF);
		mailLabel.setBounds(70, 210, 100, 20);
		this.add(mailLabel);
		mailTF.setBounds(150, 210, 200, 20);
		this.add(mailTF);
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
		this.setTitle("Manage clients");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	public void pressClose(ActionListener a) {
		closeB.addActionListener(a);
	}

	public void pressViewAll(ActionListener a) {
		viewAllB.addActionListener(a);
	}

	public int getID() {
		return Integer.parseInt(idTF.getText());
	}

	public String getFirstName() {
		return fnTF.getText();
	}

	public String getLastName() {
		return lnTF.getText();
	}

	public String getAddress() {
		return addressTF.getText();
	}

	public String getEmail() {
		return mailTF.getText();
	}

	public void displayTable(JTable table) {
		this.table = table;
		tableSP = new JScrollPane(table);
		tableSP.setBounds(80, 300, 600, 400);
		this.add(tableSP);
		tableSP.setVisible(true);
		this.repaint();
	}

	public void clear() {
		idTF.setText("");
		fnTF.setText("");
		lnTF.setText("");
		addressTF.setText("");
		mailTF.setText("");
		this.repaint();
	}
}
