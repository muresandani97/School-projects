package assignment3.warehouse.models;

/**
 * This class is the model class for the customers of the warehouse.Objects of
 * this class will be used to receive data from the data base, corresponding to
 * the table customer from the database.
 */
public class Customer {
	private int customerID;
	private String firstName;
	private String lastName;
	private String address;
	private String email;

	/**
	 * This is the constructor for the class customer.It is used to instantiate objects
	 * from this class which will be useful to get data from the database
	 *
	 * @param customerID
	 *            an integer which uniquely identifies the customer
	 * @param firstName
	 *            a String which represents the firstname of the customer
	 * @param lastName
	 *            a String which represents the lastname of the customer
	 * @param address
	 *             a String which represents the address of the customer
	 * @param email
	 *             a String which represents the email of the customer
	 * 
	 */
	public Customer(int customerID, String firstName, String lastName, String address, String email) {
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
