package assignment4.bank.junit;

import org.junit.Test;

import assignment4.bank.models.Account;
import assignment4.bank.models.Bank;
import assignment4.bank.models.Person;
import assignment4.bank.models.SavingAccount;
import assignment4.bank.models.SpendingAccount;
import junit.framework.TestCase;

public class Tests extends TestCase {
	Person p1, p2;
	Account a1, a2, a3, a4;
	Bank b;

	public void setUp() {
		p1 = new Person(1, "Bob", "Dylan", "Bakers str", "bob.dylan");
		p2 = new Person(2, "Tim", "Berg", "Singers", "avicii@gmail.com");
		a1 = new SavingAccount(1, 2);
		a2 = new SavingAccount(2, 7);
		a3 = new SpendingAccount(3, 2);
		a4 = new SpendingAccount(4, 10);
		a1.setBalance(4000);
		a2.setBalance(7000);
		a3.setBalance(700);
		a3.setBalance(850);
		b = new Bank();
	}

	@Test
	public void testDeposit() {
		int result = 4800;
		a1.depositMoney(800);
		assertEquals(a1.getBalance(), result);
	}

	@Test
	public void testWithdraw() {
		int result = 10;
		a2.withdrawMoney(6990);
		assertEquals(a2.getBalance(), result);
	}

	@Test
	public void testAddPerson() {
		b.addPerson(p1);
	}

	@Test
	public void testRemovePerson() {
		b.addPerson(p2);
		b.removePerson(p2);
	}

	@Test
	public void testAddAccount() {
		b.addPerson(p1);
		b.addPerson(p2);
		b.addAccount(a1, p1);
		b.addAccount(a3, p1);
		b.addAccount(a2, p2);
		b.addAccount(a4, p2);
	}

	@Test
	public void testRemoveAccount() {
		b.addPerson(p1);
		b.addPerson(p2);
		b.addAccount(a1, p1);
		b.addAccount(a3, p1);
		b.addAccount(a2, p2);
		b.addAccount(a4, p2);
		b.removeAccount(a1, p1);
		b.removeAccount(a3, p1);
		b.removeAccount(a2, p2);
		b.removeAccount(a4, p2);
	}
}
