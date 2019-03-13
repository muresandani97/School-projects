package assignment4.bank.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import assignment4.bank.views.View;

@SuppressWarnings("unused")
public class Controller {
	private View theView;
	private AccountController ac;
	private ClientController cc;

	public Controller() {
		this.theView = new View();
		this.theView.accountListener(new AccountListener());
		this.theView.clientListener(new ClientListener());
	}

	class AccountListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ac = new AccountController();
		}
	}

	class ClientListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cc = new ClientController();
		}
	}
}
