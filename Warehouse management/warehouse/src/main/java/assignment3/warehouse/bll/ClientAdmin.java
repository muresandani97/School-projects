package assignment3.warehouse.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import assignment3.warehouse.connection.ConnectionFactory;
import assignment3.warehouse.dao.CustomerDAO;
import assignment3.warehouse.models.Customer;
import assignment3.warehouse.presentation.CustomerView;

/**
 * This class is a sort of controller handling the frame form the gui used to
 * manage the customers from the database
 */
public class ClientAdmin {
	private CustomerView theView;

	/**
	 * This is the constructor for this class which instantiates a new object of the
	 * class CustomerView which will create a new frame for managing clients.It also
	 * instantiates inner classes which handle the buttons from the view.
	 */
	public ClientAdmin() {
		this.theView = new CustomerView();
		this.theView.pressInsert(new InsertL());
		this.theView.pressEdit(new EditL());
		this.theView.pressDelete(new DeleteL());
		this.theView.pressClose(new CloseL());
		this.theView.pressViewAll(new ViewAllL());
	}

	/**
	 * This is the inner class which handles the button for insertion of new
	 * customers.It uses the insert method from the CustomerDAO class.
	 */
	class InsertL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Customer c = new Customer(theView.getID(), theView.getFirstName(), theView.getLastName(),
						theView.getAddress(), theView.getEmail());
				CustomerDAO.insertCustomer(c);
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}

	/**
	 * This is the inner class which handles the button for updating customers.It
	 * uses the update method from the CustomerDAO class.
	 */
	class EditL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Customer c = new Customer(theView.getID(), theView.getFirstName(), theView.getLastName(),
						theView.getAddress(), theView.getEmail());
				CustomerDAO.updateCustomer(c);
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}

	/**
	 * This is the inner class which handles the button for deleting 
	 * customers.It uses the delete method from the CustomerDAO class.
	 */
	class DeleteL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Customer c = new Customer(theView.getID(), theView.getFirstName(), theView.getLastName(),
						theView.getAddress(), theView.getEmail());
				CustomerDAO.deleteCustomer(c);
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}

	/**
	 * This is the inner class which handles the close button.It closes the current
	 * frame which is an object from the class CustomerView.
	 */
	class CloseL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setVisible(false);
		}
	}

	/**
	 * This is the inner class which handles the button for getting all 
	 * customers.It uses the getAll method from the CustomerDAO class.
	 */
	class ViewAllL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			theView.displayTable(CustomerDAO.getAll());
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}

}
