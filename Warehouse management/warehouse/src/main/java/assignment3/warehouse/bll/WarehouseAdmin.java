package assignment3.warehouse.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import assignment3.warehouse.connection.ConnectionFactory;
import assignment3.warehouse.dao.ProductDAO;
import assignment3.warehouse.models.Product;
import assignment3.warehouse.presentation.ProductView;

/**
 * This class is a sort of controller handling the frame form the gui used to
 * manage the products from the database
 */
public class WarehouseAdmin {
	private ProductView theView;
	
	/**
	 * This is the constructor for this class which instantiates a new object of the
	 * class ProductView which will create a new frame for managing products.It also
	 * instantiates inner classes which handle the buttons from the view.
	 */
	public WarehouseAdmin() {
		this.theView = new ProductView();
		this.theView.pressInsert(new InsertL());
		this.theView.pressEdit(new EditL());
		this.theView.pressDelete(new DeleteL());
		this.theView.pressClose(new CloseL());
		this.theView.pressViewAll(new ViewAllL());
	}
	
	/**
	 * This is the inner class which handles the button for insertion of new
	 * products .It uses the insert method from the ProductDAO class.
	 */
	class InsertL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Product p = new Product(theView.getID(), theView.getName(), theView.getQuantity(), theView.getPrice());
				ProductDAO.insertProduct(p);

			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
	
	/**
	 * This is the inner class which handles the button for updating products.It
	 * uses the update method from the ProductDAO class.
	 */
	class EditL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Product p = new Product(theView.getID(), theView.getName(), theView.getQuantity(), theView.getPrice());
				ProductDAO.updateProduct(p);
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
	
	/**
	 * This is the inner class which handles the button for deleting 
	 * products.It uses the delete method from the ProductDAO class.
	 */
	class DeleteL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.tableSP.setVisible(false);
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			try {
				Product p = new Product(theView.getID(), theView.getName(), theView.getQuantity(), theView.getPrice());
				ProductDAO.deleteProduct(p);
			} catch (NumberFormatException ex1) {
				JOptionPane.showMessageDialog(theView, "Invalid input");
			}
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
	
	/**
	 * This is the inner class which handles the close button.It closes the current
	 * frame which is an object from the class ProductView.
	 */
	class CloseL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setVisible(false);
		}
	}
	
	/**
	 * This is the inner class which handles the button for getting all 
	 * products.It uses the getAll method from the ProductDAO class.
	 */
	class ViewAllL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ConnectionFactory cf = new ConnectionFactory();
			Connection dbConnect = cf.createConnection();
			theView.displayTable(ProductDAO.getAll());
			theView.clear();
			ConnectionFactory.close(dbConnect);
		}
	}
}
