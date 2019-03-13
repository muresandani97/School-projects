package assignment4.bank.views;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class View extends JFrame {
	private JButton clientB = new JButton("Manage clients");
	private JButton accountB = new JButton("Manage accounts");

	public View() {
		clientB.setBounds(90, 100, 150, 30);
		this.add(clientB);
		accountB.setBounds(90, 150, 150, 30);
		this.add(accountB);
		this.setLayout(null);
		this.setTitle("Bank");
		this.setSize(350, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void clientListener(ActionListener a) {
		clientB.addActionListener(a);
	}

	public void accountListener(ActionListener a) {
		accountB.addActionListener(a);
	}

}
