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
import assignment3.warehouse.models.Order;

/**
 * This class is used to access the fields from the table order from the
 * database
 */
public class OrderDAO {

	/**
	 * This method searches in the table order from the database an order with the
	 * unique id specified as parameter.
	 * 
	 * @param orderID
	 *            an integer which uniquely identifies the order to be searched
	 * @return the order searched if found.If not it returns null.
	 */
	public static Order findByID(int orderID) {
		Order searchedOrder = null;
		
		try {
			Connection dbConnect = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;
			findStatement = dbConnect.prepareStatement("SELECT * FROM warehouse.order where orderID= ?");
			findStatement.setLong(1, orderID);
			rs = findStatement.executeQuery();
			rs.next();
			searchedOrder = new Order(orderID, rs.getInt("productID"), rs.getInt("customerID"), rs.getInt("quantity"),
					rs.getString("status"));
		} catch (SQLException e) {

		} 
		return searchedOrder;
	}

	/**
	 * This method inserts a new order, given as a parameter, in the database.
	 * 
	 * @param newOrder
	 *            an instance of the class Order which corresponds to the order
	 *            which will be inserted
	 */
	public static void insertOrder(Order newOrder) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		try {
			if (findByID(newOrder.getOrderID()) == null) {
				insertStatement = dbConnect.prepareStatement(
						"INSERT INTO warehouse.order (orderID,productID,customerID,quantity,status) values ('"
								+ newOrder.getOrderID() + "','" + newOrder.getProductID() + "','"
								+ newOrder.getCustomerID() + "','" + newOrder.getQuantity() + "','"
								+ newOrder.getStatus() + "')");
				insertStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Order placed");
			} else
				JOptionPane.showMessageDialog(null, "Order already exists");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method deletes an order , given as a parameter, if it exists in the
	 * database.
	 * 
	 * @param toBeDeleted
	 *            an instance of the class Order which corresponds to the order
	 *            which will be deleted
	 */
	public static void deleteOrder(Order toBeDeleted) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			if (findByID(toBeDeleted.getOrderID()) != null) {
				deleteStatement = dbConnect.prepareStatement(
						"DELETE FROM warehouse.order where orderID= '" + toBeDeleted.getOrderID() + "'");
				deleteStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Order deleted");
			} else
				JOptionPane.showMessageDialog(null, "Order does not exist");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method updates an order given as a parameter, if it exists in the
	 * database.
	 * 
	 * @param updatedOrder
	 *            an instance of the class Order which corresponds to the order
	 *            which will be edited
	 */
	public static void updateOrder(Order updatedOrder) {
		Connection dbConnect = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			if (findByID(updatedOrder.getOrderID()) != null) {
				updateStatement = dbConnect.prepareStatement(
						"UPDATE warehouse.order SET orderID= ?, productID= ?, customerID= ?, quantity= ?, status= ? WHERE orderID= ?");
				updateStatement.setInt(1, updatedOrder.getOrderID());
				updateStatement.setInt(2, updatedOrder.getProductID());
				updateStatement.setInt(3, updatedOrder.getCustomerID());
				updateStatement.setInt(4, updatedOrder.getQuantity());
				updateStatement.setString(5, updatedOrder.getStatus());
				updateStatement.setInt(6, updatedOrder.getOrderID());
				updateStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Order updated");
			} else
				JOptionPane.showMessageDialog(null, "Order does not exist");
		} catch (SQLException e) {

		}
	}

	/**
	 * This method gets all fields from the table order in the database , creates
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
			getAllStatement = dbConnect.prepareStatement("SELECT * FROM warehouse.order");
			rs = getAllStatement.executeQuery();
			rs.next();
			Order o = new Order(rs.getInt("orderID"), rs.getInt("productID"), rs.getInt("customerID"),
					rs.getInt("quantity"), rs.getString("status"));
			for (Field f : o.getClass().getDeclaredFields()) {
				tm.addColumn(f.getName());
			}
			tm.addRow(new Object[] { rs.getInt("orderID"), rs.getInt("productID"), rs.getInt("customerID"),
					rs.getInt("quantity"), rs.getString("status") });
			while (rs.next()) {
				tm.addRow(new Object[] { rs.getInt("orderID"), rs.getInt("productID"), rs.getInt("customerID"),
						rs.getInt("quantity"), rs.getString("status") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JTable result = new JTable(tm);
		return result;
	}
}
