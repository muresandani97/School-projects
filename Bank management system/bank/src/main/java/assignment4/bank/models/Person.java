package assignment4.bank.models;

import java.util.HashSet;

@SuppressWarnings("serial")
public class Person implements java.io.Serializable {
	public HashSet<Account> acc = new HashSet<Account>();
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;

	public Person(int id, String firstName, String lastName, String email, String address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	public int getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getAddress() {
		return this.address;
	}

	public int addAccount(Account newAccount) {
		if (!acc.contains(newAccount)) {
			acc.add(newAccount);
		}
		return 0;
	}

	public int deleteAccount(Account newAccount) {
		if (acc.contains(newAccount)) {
			acc.remove(newAccount);
		}
		return 0;
	}

	public int hashCode() {
		return Integer.toString(id).hashCode();
	}

}
