package assignment4.bank.models;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Account implements java.io.Serializable{
	private int id;
	private int balance;

	public Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
	}

	public int getId() {
		return this.id;
	}

	public int getBalance() {
		return this.balance;
	}
	
	public void setBalance(int balance) {
		this.balance=balance;
	}

	public int depositMoney(int sum) {
		balance += sum;
		return balance;
	}

	public int withdrawMoney(int sum) {
		if (sum > balance) {
			JOptionPane.showMessageDialog(null, "Insufficient balance");
		} else {
			balance -= sum;
			JOptionPane.showMessageDialog(null, "Money taken");
		}
		return balance;
	}

	public int hashCode() {
		return Integer.toString(id).hashCode();
	}
}
