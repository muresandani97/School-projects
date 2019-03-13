package assignment4.bank.views;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AccountView extends JFrame {
	private JLabel idLabel = new JLabel("AccountID ");
	private JLabel ownerLabel = new JLabel("Owner");
	private JLabel balanceLabel = new JLabel("Balance");
	private JLabel typeLabel = new JLabel("Type");
	private JTextField idTF = new JTextField("");
	private JTextField ownerTF = new JTextField("");
	private JTextField balanceTF = new JTextField("");
	private JTextField typeTF = new JTextField("");
	private JButton insertB = new JButton("Insert");
	private JButton editB = new JButton("Update");
	private JButton deleteB = new JButton("Delete");
	private JButton closeB = new JButton("Close");
	private JButton viewAllB = new JButton("View all");
	private JButton depositB = new JButton("Deposit money");
	private JButton withdrawB = new JButton("Withrdaw money");
	private JLabel sumLabel  = new JLabel("Sum :");
	private JTextField sumTF = new JTextField("");
	public JScrollPane tableSP = new JScrollPane();
	public JTable table = new JTable();

	public AccountView() {
		idLabel.setBounds(70, 50, 100, 20);
		this.add(idLabel);
		idTF.setBounds(150, 50, 30, 20);
		this.add(idTF);
		ownerLabel.setBounds(70, 102, 100, 20);
		this.add(ownerLabel);
		ownerTF.setBounds(150, 102, 100, 20);
		this.add(ownerTF);
		balanceLabel.setBounds(70, 150, 100, 20);
		this.add(balanceLabel);
		balanceTF.setBounds(150, 150, 50, 20);
		this.add(balanceTF);
		typeLabel.setBounds(70, 200, 100, 20);
		this.add(typeLabel);
		typeTF.setBounds(150, 200, 100, 20);
		this.add(typeTF);
		insertB.setBounds(450, 50, 100, 30);
		this.add(insertB);
		editB.setBounds(450, 100, 100, 30);
		this.add(editB);
		deleteB.setBounds(450, 150, 100, 30);
		this.add(deleteB);
		closeB.setBounds(450, 250, 100, 30);
		this.add(closeB);
		viewAllB.setBounds(450, 200, 100, 30);
		this.add(viewAllB);
		depositB.setBounds(600, 100, 150, 30);
		this.add(depositB);
		withdrawB.setBounds(600, 150, 150, 30);
		this.add(withdrawB);
		sumLabel.setBounds(600, 200, 150, 30);
		this.add(sumLabel);
		sumTF.setBounds(650,200,100,30);
		this.add(sumTF);

		this.setLayout(null);
		this.setTitle("Manage accounts");
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

	public void pressViewAll(ActionListener a) {
		viewAllB.addActionListener(a);
	}

	public void pressClose(ActionListener a) {
		closeB.addActionListener(a);
	}
	
	public void pressDeposit(ActionListener a) {
		depositB.addActionListener(a);
	}
	
	public void pressWithdraw(ActionListener a) {
		withdrawB.addActionListener(a);
	}

	public int getID() {
		return Integer.parseInt(idTF.getText());
	}

	public String getName() {
		return ownerTF.getText();
	}

	public int getBalance() {
		return Integer.parseInt(balanceTF.getText());
	}

	public String getT() {
		return typeTF.getText();
	}
	
	public int getSum() {
		return Integer.parseInt(sumTF.getText());
	}

	public void displayTable(JTable table) {
		this.table = table;
		tableSP = new JScrollPane(table);
		tableSP.setBounds(80, 300, 600, 400);
		tableSP.setVisible(true);
		this.add(tableSP);
		this.repaint();
	}

	public void clear() {
		idTF.setText("");
		ownerTF.setText("");
		balanceTF.setText("");
		typeTF.setText("");
		sumTF.setText("");
		this.repaint();
	}
}
