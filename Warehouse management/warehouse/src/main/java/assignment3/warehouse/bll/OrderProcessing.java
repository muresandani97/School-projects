package assignment3.warehouse.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import assignment3.warehouse.connection.ConnectionFactory;
import assignment3.warehouse.dao.CustomerDAO;
import assignment3.warehouse.dao.OrderDAO;
import assignment3.warehouse.dao.ProductDAO;
import assignment3.warehouse.models.Customer;
import assignment3.warehouse.models.Order;
import assignment3.warehouse.models.Product;
import assignment3.warehouse.presentation.OrderView;

/**
 * This class is a sort of controller handling the frame form the gui used to
 * manage the ordersfrom the database
 */
public class OrderProcessing {
	private OrderView theView;
	
	/**
	 * This is the constructor for this class which instantiates a new object of the
	 * class OrderView which will create a new frame for managing orders.It also
	 * instantiates inner classes which handle the buttons from the view.
	 */
	public OrderProcessing() {
		this.theView = new OrderView();
		this.theView.pressInsert(new InsertL());
		this.theView.pressEdit(new EditL());
		this.theView.pressDelete(new DeleteL());
		this.theView.pressClose(new CloseL());
		this.theView.pressViewAll(new ViewAllL());
	}

	/**
	 * This is the inner class which handles the button for insertion of new orders
	 * .It uses the insert method from the OrderDAO class.It also calls the
	 * method for generating a bill and modifies the remained quantity for the
	 * bought product
	 */
	class InsertL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Product p = ProductDAO.findByID(theView.getProductID());
				Customer c = CustomerDAO.findByID(theView.getCustomerID());
				if (p != null && c != null) {
					if (p.getQuantity() >= theView.getQuantity()) {
						Order o = new Order(theView.getID(), theView.getProductID(), theView.getCustomerID(),
								theView.getQuantity(), theView.getStatus());
						OrderDAO.insertOrder(o);
						p.setQuantity(p.getQuantity() - theView.getQuantity());
						ProductDAO.updateProduct(p);
						BillGenerator.generateBill(theView.getID(), c.getFirstName(), c.getLastName(), c.getAddress(),
								c.getEmail(), p.getName(), theView.getQuantity(), theView.getQuantity() * p.getPrice());
					} else
						JOptionPane.showMessageDialog(theView, "The quantity requred is not availible");
				} else
					JOptionPane.showMessageDialog(theView, "Invalid customer or product");
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
	
	/**
	 * This is the inner class which handles the button for updating orders.It
	 * uses the update method from the OrderDAO class.
	 */
	class EditL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Product p = ProductDAO.findByID(theView.getProductID());
				Customer c = CustomerDAO.findByID(theView.getCustomerID());
				if (p != null && c != null) {
					Order o = new Order(theView.getID(), theView.getProductID(), theView.getCustomerID(),
							theView.getQuantity(), theView.getStatus());
					OrderDAO.updateOrder(o);
				}
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
	
	/**
	 * This is the inner class which handles the button for deleting 
	 * orders.It uses the delete method from the OrderDAO class.
	 */
	class DeleteL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Product p = ProductDAO.findByID(theView.getProductID());
				Customer c = CustomerDAO.findByID(theView.getCustomerID());
				if (p != null && c != null) {
					Order o = new Order(theView.getID(), theView.getProductID(), theView.getCustomerID(),
							theView.getQuantity(), theView.getStatus());
					OrderDAO.deleteOrder(o);
				}
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
	/**
	 * This is the inner class which handles the close button.It closes the current
	 * frame which is an object from the class OrderView.
	 */
	class CloseL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setVisible(false);
		}
	}
	/**
	 * This is the inner class which handles the button for getting all 
	 * orders.It uses the getAll method from the OrderDAO class.
	 */
	class ViewAllL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			theView.displayTable(OrderDAO.getAll());
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
}
