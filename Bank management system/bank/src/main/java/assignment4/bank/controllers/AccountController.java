package assignment4.bank.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import assignment4.bank.models.Account;
import assignment4.bank.models.Bank;
import assignment4.bank.models.Person;
import assignment4.bank.models.SavingAccount;
import assignment4.bank.models.SpendingAccount;
import assignment4.bank.serialize.Serializer;
import assignment4.bank.views.AccountView;

public class AccountController {
	private AccountView theView;
	private Bank theBank;

	public AccountController() {
		this.theView = new AccountView();
		this.theView.pressInsert(new InsertListener());
		this.theView.pressEdit(new EditListener());
		this.theView.pressDelete(new DeleteListener());
		this.theView.pressClose(new CloseListener());
		this.theView.pressViewAll(new ViewAllListener());
		this.theView.pressDeposit(new DepositListener());
		this.theView.pressWithdraw(new WithdrawListener());
	}

	class InsertListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				Account a = null;
				Person p = null;
				boolean ok = true;
				if (theView.getT().equals("Spending")) {
					a = new SpendingAccount(theView.getID(), 4);
					a.setBalance(theView.getBalance());
				} else {
					a = new SavingAccount(theView.getID(), 4);
					a.setBalance(theView.getBalance());
					int interest = (int) ((SavingAccount) a).computeWinnings();
					a.setBalance(theView.getBalance()+interest);
				}
				for (Person p1 : theBank.clients.keySet()) {
					String owner = p1.getFirstName() + " " + p1.getLastName();
					if (owner.equals(theView.getName())) {
						p = p1;
					}
					for (Account a1 : p1.acc) {
						if (a.getId() == a1.getId()) {
							ok = false;
						}
					}
				}
				if (p == null) {
					JOptionPane.showMessageDialog(theView, "The owner is not a client");
				} else {
					if (ok) {
						theBank.addAccount(a, p);
						JOptionPane.showMessageDialog(theView, "Account added");
					} else {
						JOptionPane.showMessageDialog(theView, "The account already exist");
					}
				}
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(theView, "Complete all fields");
			}
		}
	}

	class EditListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				int i = theView.table.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel) theView.table.getModel();
				Person p = null;
				Account a = null;
				for (Person p1 : theBank.clients.keySet()) {
					String owner = p1.getFirstName() + " " + p1.getLastName();
					if (owner.equals(tm.getValueAt(i, 1).toString())) {
						p = p1;
					}
					for (Account a1 : p1.acc) {
						if (a1.getId() == Integer.parseInt(tm.getValueAt(i, 0).toString())) {
							a = a1;
						}
					}
				}
				if (p != null) {
					theBank.removeAccount(a, p);
				}
				if (theView.getT().equals("Spending")) {
					a = new SpendingAccount(theView.getID(), 4);
				} else {
					a = new SavingAccount(theView.getID(), 4);
				}
				a.setBalance(theView.getBalance());
				theBank.addAccount(a, p);
				JOptionPane.showMessageDialog(theView, "Account updated");
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(theView, "Complete all fields");
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Select an account from the table");
			}
		}
	}

	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				int i = theView.table.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel) theView.table.getModel();
				Person p = null;
				Account a = null;
				for (Person p1 : theBank.clients.keySet()) {
					String owner = p1.getFirstName() + " " + p1.getLastName();
					if (owner.equals(tm.getValueAt(i, 1).toString())) {
						p = p1;
					}
					for (Account a1 : p1.acc) {
						if (a1.getId() == Integer.parseInt(tm.getValueAt(i, 0).toString())) {
							a = a1;
						}
					}
				}
				if (p != null) {
					theBank.removeAccount(a, p);
					JOptionPane.showMessageDialog(theView, "Account deleted");
				}
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Select an account from the table");
			}
		}
	}

	class ViewAllListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theBank = Serializer.deserialize();
			theView.displayTable(theBank.getAccounts());
		}
	}

	class CloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setVisible(false);
		}
	}

	class DepositListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				int i = theView.table.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel) theView.table.getModel();
				Person p = null;
				for (Person p1 : theBank.clients.keySet()) {
					String owner = p1.getFirstName() + " " + p1.getLastName();
					if (owner.equals(tm.getValueAt(i, 1).toString())) {
						p = p1;
					}
				}
				for (Account a1 : p.acc) {
					if (a1.getId() == Integer.parseInt(tm.getValueAt(i, 0).toString())) {
						if (a1 instanceof SavingAccount) {
							int interest = (int) ((SavingAccount) a1).computeWinnings();
							a1.depositMoney(theView.getSum() + interest);
							JOptionPane.showMessageDialog(theView, "Money deposited");
						} else {
							a1.depositMoney(theView.getSum());
							JOptionPane.showMessageDialog(theView, "Money deposited");
						}

					}
				}
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(theView, "Complete the sum");
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Select an account from the table");
			}
		}
	}

	class WithdrawListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				int i = theView.table.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel) theView.table.getModel();
				Person p = null;
				for (Person p1 : theBank.clients.keySet()) {
					String owner = p1.getFirstName() + " " + p1.getLastName();
					if (owner.equals(tm.getValueAt(i, 1).toString())) {
						p = p1;
					}
				}
				for (Account a1 : p.acc) {
					if (a1.getId() == Integer.parseInt(tm.getValueAt(i, 0).toString())) {
						if (a1 instanceof SpendingAccount) {
							int tax = (int) ((SpendingAccount) a1).computeTaxes(theView.getSum());
							a1.withdrawMoney(theView.getSum() + tax);
						} else {
							a1.withdrawMoney(theView.getSum());
						}
					}
				}
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(theView, "Complete the sum");
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Select an account from the table");
			}
		}
	}
}
