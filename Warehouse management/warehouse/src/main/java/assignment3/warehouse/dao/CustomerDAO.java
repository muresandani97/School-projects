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
import assignment3.warehouse.models.Customer;

/**
 * This class is used to access the fields from the table customer from the
 * database
 */
public class CustomerDAO {
	

	/**
	 * This method searches in the table customer from the database a customer with
	 * the unique id specified as parameter.
	 * 
	 * @param customerID
	 *            an integer which uniquely identifies the customer to be searched
	 * @return the customer searched if found.If not it returns null.
	 */
	public static Customer findByID(int customerID) {
		Customer searchedCustomer = null;
		try {
			
			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			findStatement = dbConnect.prepareStatement("SELECT * FROM customer where customerID= ?");
			findStatement.setLong(1, customerID);
			rs = findStatement.executeQuery();
			rs.next();
			searchedCustomer = new Customer(customerID, rs.getString("firstName"), rs.getString("lastName"),
					rs.getString("address"), rs.getString("email"));
		} catch (SQLException e) {

		} 
		return searchedCustomer;
	}

	/**
	 * This method inserts a new customer, given as a parameter, in the database.
	 * 
	 * @param newCustomer
	 *            an instance of the class Customer which corresponds to the
	 *            customer which will be inserted
	 */
	public static void insertCustomer(Customer newCustomer) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			if (findByID(newCustomer.getCustomerID()) == null) {
				insertStatement = dbConnect
						.prepareStatement("INSERT INTO customer (customerID,firstName,lastName,address,email) values ('"
								+ newCustomer.getCustomerID() + "','" + newCustomer.getFirstName() + "','"
								+ newCustomer.getLastName() + "','" + newCustomer.getAddress() + "','"
								+ newCustomer.getEmail() + "')");
				insertStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Customer inserted");
			} else
				JOptionPane.showMessageDialog(null, "Customer already exist");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method deletes a customer, given as a parameter, if it exists in the
	 * database.
	 * 
	 * @param toBeDeleted
	 *            an instance of the class Customer which corresponds to the
	 *            customer which will be deleted
	 */
	public static void deleteCustomer(Customer toBeDeleted) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			if (findByID(toBeDeleted.getCustomerID()) != null) {
				deleteStatement = dbConnect.prepareStatement(
						"DELETE FROM customer where customerID= '" + toBeDeleted.getCustomerID() + "'");
				deleteStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Customer deleted");
			} else
				JOptionPane.showMessageDialog(null, "Customer does not exist");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Customer can't be deleted because has an order on his name");
		}
	}

	/**
	 * This method updates a customer, given as a parameter, if it exists in the
	 * database.
	 * 
	 * @param updatedCustomer
	 *            an instance of the class Customer which corresponds to the
	 *            customer which will be edited
	 */
	public static void updateCustomer(Customer updatedCustomer) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			if (findByID(updatedCustomer.getCustomerID()) != null) {
				updateStatement = dbConnect.prepareStatement(
						"UPDATE customer SET customerID= ?, firstName= ?, lastName= ?, address= ?, email =? WHERE customerID= ?");
				updateStatement.setInt(1, updatedCustomer.getCustomerID());
				updateStatement.setString(2, updatedCustomer.getFirstName());
				updateStatement.setString(3, updatedCustomer.getLastName());
				updateStatement.setString(4, updatedCustomer.getAddress());
				updateStatement.setString(5, updatedCustomer.getEmail());
				updateStatement.setInt(6, updatedCustomer.getCustomerID());
				updateStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Customer updated");
			} else
				JOptionPane.showMessageDialog(null, "Customer does not exist");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method gets all fields from the table customer in the database , creates
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
			getAllStatement = dbConnect.prepareStatement("SELECT * FROM customer");
			rs = getAllStatement.executeQuery();
			rs.next();
			Customer c = new Customer(rs.getInt("customerID"), rs.getString("firstName"), rs.getString("lastName"),
					rs.getString("address"), rs.getString("email"));
			for (Field f : c.getClass().getDeclaredFields()) {
				tm.addColumn(f.getName());
			}
			tm.addRow(new Object[] { rs.getInt("customerID"), rs.getString("firstName"), rs.getString("lastName"),
					rs.getString("address"), rs.getString("email") });
			while (rs.next()) {
				tm.addRow(new Object[] { rs.getInt("customerID"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("address"), rs.getString("email") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JTable result = new JTable(tm);
		return result;
	}
}
