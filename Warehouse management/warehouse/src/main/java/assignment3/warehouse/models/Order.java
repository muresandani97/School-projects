package assignment3.warehouse.models;


/**
 * This class is the model class for the orders of the warehouse.Objects of
 * this class will be used to receive data from the data base, corresponding to
 * the table order from the database.
 */
public class Order {
	private int orderID;
	private int productID;
	private int customerID;
	private int quantity;
	private String status;

	
	/**
	 * This is the constructor for the class order.It is used to instantiate
	 * objects from this class which will be useful to get data from the database
	 *
	 * @param orderID
	 *            an integer which uniquely identifies the order
	 * @param productID
	 *            an integer which uniquely identifies the ordered product
	 * @param customerID
	 *             an integer which uniquely identifies the customer who made the order
	 * @param quantity
	 *            an integer representing the ordered quantity 
	 * 
	 */
	public Order(int orderID, int productID, int customerID, int quantity, String status) {
		this.orderID = orderID;
		this.productID = productID;
		this.customerID = customerID;
		this.quantity = quantity;
		this.status = status;
	}

	public int getOrderID() {
		return this.orderID;
	}

	public int getProductID() {
		return this.productID;
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
