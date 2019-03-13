package assignment3.warehouse.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to generate, and close connections with the database.
 */
public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse";
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection connection;

	/**
	 * This is the constructor for the class and it has no parameters.
	 */
	public ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a new connection with the database.
	 * 
	 * @return the connection which was created
	 */
	public Connection createConnection() {
		try {
			new ConnectionFactory();
			connection = DriverManager.getConnection(DBURL, USER, PASS);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * This method provides the current open connection
	 * 
	 * @return the connection which is open at a certain moment
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * This method closes the connection given as a parameter
	 * 
	 * @param the
	 *            connection to be closed
	 */

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method closes the statement given as a parameter
	 * 
	 * @param the
	 *            statement to be closed
	 */
	public static void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method closes the result set given as a parameter
	 * 
	 * @param the
	 *            result set to be closed
	 */
	public static void close(ResultSet resultset) {
		try {
			if (resultset != null) {
				resultset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
