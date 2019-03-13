package assignment4.bank.models;

import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Bank implements java.io.Serializable, BankProc {
	public HashMap<Person, HashSet<Account>> clients;

	public Bank(HashMap<Person, HashSet<Account>> clients) {
		this.clients = clients;
	}

	public Bank() {
		this.clients = new HashMap<Person, HashSet<Account>>();
	}

	@Override
	public void addPerson(Person p) {
		if (clients.containsKey(p)) {
			System.out.println("The person already exists");
		} else {
			clients.put(p, p.acc);
		}
	}

	@Override
	public void removePerson(Person p) {
		if (!clients.containsKey(p)) {
			System.out.println("The person doesnt exist");
		} else {
			clients.remove(p);
		}
	}

	@Override
	public void addAccount(Account a, Person p) {
		if (!clients.containsKey(p)) {
			System.out.println("The person doesnt exist");
		} else {
			p.addAccount(a);
			clients.get(p).add(a);
		}
	}

	@Override
	public void removeAccount(Account a, Person p) {
		if (!clients.containsKey(p)) {
			System.out.println("The person doesnt exist");
		} else {
			if (!clients.get(p).contains(a)) {
				System.out.println("The account doesnt exist");
			} else {
				p.deleteAccount(a);
				clients.get(p).remove(a);
			}
		}
	}

	@Override
	public JTable getAll() {
		DefaultTableModel tm = new DefaultTableModel();
		tm.addColumn("Client id");
		tm.addColumn("Firstname");
		tm.addColumn("Lastname");
		tm.addColumn("Accounts");

		for (Person p : clients.keySet()) {
			String accounts = "";
			for (Account a : clients.get(p)) {
				if (a instanceof SavingAccount) {
					accounts += "Account id: " + a.getId() + " - saving account;";
				}
				if (a instanceof SpendingAccount) {
					accounts += "Account id: " + a.getId() + " - spending account;";
				}
			}
			tm.addRow(new Object[] { p.getId(), p.getFirstName(), p.getLastName(), accounts });
		}
		JTable result = new JTable(tm);
		return result;
	}

	@Override
	public JTable getPersons() {
		DefaultTableModel tm = new DefaultTableModel();
		tm.addColumn("Client id");
		tm.addColumn("Firstname");
		tm.addColumn("Lastname");
		tm.addColumn("Email");
		tm.addColumn("Address");
		for (Person p : clients.keySet()) {
			tm.addRow(new Object[] { p.getId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getAddress() });
		}
		JTable result = new JTable(tm);
		return result;
	}

	@Override
	public JTable getAccounts() {
		DefaultTableModel tm = new DefaultTableModel();
		tm.addColumn("Account id");
		tm.addColumn("Owner");
		tm.addColumn("Balance");
		tm.addColumn("Type");
		for (Person p : clients.keySet()) {
			for (Account a : clients.get(p)) {
				if (a instanceof SavingAccount) {
					tm.addRow(new Object[] { a.getId(), p.getFirstName() + " " + p.getLastName(), a.getBalance(),
							"Saving account" });
				} else if (a instanceof SpendingAccount) {
					tm.addRow(new Object[] { a.getId(), p.getFirstName() + " " + p.getLastName(), a.getBalance(),
							"Spending account" });
				}
			}
		}
		JTable result = new JTable(tm);
		return result;
	}
}
