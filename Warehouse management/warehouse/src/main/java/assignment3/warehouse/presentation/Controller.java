package assignment3.warehouse.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import assignment3.warehouse.bll.ClientAdmin;
import assignment3.warehouse.bll.OrderProcessing;
import assignment3.warehouse.bll.WarehouseAdmin;

/**
 * This is the class which handles the first frame from the GUI and creates
 * objects of classes ClientAdmin,WarehouseAdmin and OrderProcessing which will
 * contol the new frames.
 */
@SuppressWarnings("unused")
public class Controller {

	private View theView;
	private ClientAdmin ca;
	private WarehouseAdmin wa;
	private OrderProcessing op;

	public Controller() {
		this.theView = new View();
		theView.orderListener(new OrderL());
		theView.clientListener(new ClientL());
		theView.productListener(new ProductL());
	}

	class OrderL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			op = new OrderProcessing();
		}
	}

	class ClientL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ca = new ClientAdmin();
		}
	}

	class ProductL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			wa = new WarehouseAdmin();
		}
	}
}
