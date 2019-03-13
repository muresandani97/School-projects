package assignment3.warehouse.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import assignment3.warehouse.connection.ConnectionFactory;
import assignment3.warehouse.models.Product;

/**
 * This class is used to access the fields from the table product from the
 * database
 */
public class ProductDAO {

	/**
	 * This method searches in the table product from the database a product with
	 * the unique id specified as parameter.
	 * 
	 * @param productID
	 *            an integer which uniquely identifies the product to be searched
	 * @return the product searched if found.If not it returns null.
	 */
	public static Product findByID(int productID) {
		Product searchedProduct = null;
		
		try {
			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			findStatement = dbConnect.prepareStatement("SELECT * FROM product where productID= ?");
			findStatement.setLong(1, productID);
			rs = findStatement.executeQuery();
			rs.next();
			searchedProduct = new Product(productID, rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"));
		} catch (SQLException e) {

		} 
		return searchedProduct;
	}

	/**
	 * This method inserts a new product given as a parameter, in the database.
	 * 
	 * @param newProduct
	 *            an instance of the class Product which corresponds to the product
	 *            which will be inserted
	 */
	public static void insertProduct(Product newProduct) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			if (findByID(newProduct.getProductID()) == null) {
				insertStatement = dbConnect
						.prepareStatement("INSERT INTO product (productID,name,quantity,price) values ('"
								+ newProduct.getProductID() + "','" + newProduct.getName() + "','"
								+ newProduct.getQuantity() + "','" + newProduct.getPrice() + "')");
				insertStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Product inserted");
			} else
				JOptionPane.showMessageDialog(null, "The product already exists");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method deletes a product, given as a parameter, if it exists in the
	 * database.
	 * 
	 * @param toBeDeleted
	 *            an instance of the class Product which corresponds to the product
	 *            which will be deleted
	 */
	public static void deleteProduct(Product toBeDeleted) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			if (findByID(toBeDeleted.getProductID()) != null) {
				deleteStatement = dbConnect
						.prepareStatement("DELETE FROM product where productID= '" + toBeDeleted.getProductID() + "'");
				deleteStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Product deleted");
			} else
				JOptionPane.showMessageDialog(null, "The product does not exist");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Product can't be deleted because there is an order with it");
		}

	}

	/**
	 * This method updates a product, given as a parameter, if it exists in the
	 * database.
	 * 
	 * @param updatedProduct
	 *            an instance of the class Product which corresponds to the product
	 *            which will be edited
	 */
	public static void updateProduct(Product updatedProduct) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			if (findByID(updatedProduct.getProductID()) != null) {
				updateStatement = dbConnect.prepareStatement(
						"UPDATE product SET productID= ?, name= ?, quantity= ?, price= ? WHERE productID= ?");
				updateStatement.setInt(1, updatedProduct.getProductID());
				updateStatement.setString(2, updatedProduct.getName());
				updateStatement.setInt(3, updatedProduct.getQuantity());
				updateStatement.setInt(4, updatedProduct.getPrice());
				updateStatement.setInt(5, updatedProduct.getProductID());
				updateStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Product updated");
			} else
				JOptionPane.showMessageDialog(null, "The product does not exist");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method gets all fields from the table product in the database , creates
	 * and returns a table containig all the entries form the table in the database
	 * 
	 * @return an instance of the class JTable with all the entries form the table
	 *         in database
	 */
	public static JTable getAll() {
		
		DefaultTableModel tm = new DefaultTableModel();
		try {
			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement getAllStatement = null;
			ResultSet rs = null;
			getAllStatement = dbConnect.prepareStatement("SELECT * FROM product");
			rs = getAllStatement.executeQuery();
			rs.next();
			Product p = new Product(rs.getInt("productID"), rs.getString("name"), rs.getInt("quantity"),
					rs.getInt("price"));
			for (Field f : p.getClass().getDeclaredFields()) {
				tm.addColumn(f.getName());
			}
			tm.addRow(new Object[] { rs.getInt("productID"), rs.getString("name"), rs.getInt("quantity"),
					rs.getInt("price") });
			while (rs.next()) {
				tm.addRow(new Object[] { rs.getInt("productID"), rs.getString("name"), rs.getInt("quantity"),
						rs.getInt("price") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JTable result = new JTable(tm);
		return result;
	}

}
