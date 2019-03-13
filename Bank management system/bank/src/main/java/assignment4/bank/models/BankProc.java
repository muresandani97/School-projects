package assignment4.bank.models;

import javax.swing.JTable;

public interface BankProc {
		public void addPerson(Person p);
		public void removePerson(Person p);
		public void addAccount(Account a, Person p);
		public void removeAccount(Account a, Person p);
		public JTable getAll();
		public JTable getPersons();
		public JTable getAccounts();
}
