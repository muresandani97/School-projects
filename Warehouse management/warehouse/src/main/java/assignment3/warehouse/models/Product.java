package assignment3.warehouse.models;

/**
 * This class is the model class for the products in the warehouse.Objects of
 * this class will be used to receive data from the data base, corresponding to
 * the table product from the database.
 */

public class Product {
	private int productID;
	private String name;
	private int quantity;
	private int price;
	

	/**
	 * This is the constructor for the class product.It is used to instantiate
	 * objects from this class which will be useful to get data from the database
	 *
	 * @param productID
	 *            an integer which uniquely identifies the product
	 * @param name
	 *            a string representing the name of the product
	 * @param quantity
	 *            an integer representing the availible quantity for a product
	 * @param price
	 *            an integer representing the price of the product
	 * 
	 */
	public Product(int productID, String name, int quantity, int price) {
		this.productID = productID;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getProductID() {
		return this.productID;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public int getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

}
