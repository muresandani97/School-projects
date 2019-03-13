package assignment4.bank.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import assignment4.bank.models.Bank;
import assignment4.bank.models.Person;
import assignment4.bank.serialize.Serializer;
import assignment4.bank.views.ClientView;

public class ClientController {
	private ClientView theView;
	private Bank theBank;

	public ClientController() {
		this.theView = new ClientView();
		this.theView.pressInsert(new InsertListener());
		this.theView.pressEdit(new EditListener());
		this.theView.pressDelete(new DeleteListener());
		this.theView.pressClose(new CloseListener());
		this.theView.pressViewAll(new ViewAllListener());
	}

	class InsertListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				Person p = new Person(theView.getID(), theView.getFirstName(), theView.getLastName(),
						theView.getEmail(), theView.getAddress());
				boolean ok = true;
				for (Person p1 : theBank.clients.keySet()) {
					if (p1.getId() == p.getId()) {
						ok = false;
					}

				}
				if (ok == true) {
					theBank.addPerson(p);
					JOptionPane.showMessageDialog(theView, "Client inserted");
				} else {
					JOptionPane.showMessageDialog(theView, "The client already exists");
				}
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(theView, "Complete all fields");
			}
		}
	}

	class EditListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				int i = theView.table.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel) theView.table.getModel();
				Person p = null;
				for (Person p1 : theBank.clients.keySet()) {
					if (p1.getId() == Integer.parseInt(tm.getValueAt(i, 0).toString())) {
						p = p1;
					}
				}
				if (p != null) {
					theBank.removePerson(p);
				}
				Person updatedClient = new Person(theView.getID(), theView.getFirstName(), theView.getLastName(),
						theView.getEmail(), theView.getAddress());

				theBank.addPerson(updatedClient);
				JOptionPane.showMessageDialog(theView, "Client updated");
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(theView, "Complete all fields");
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Select a client from the table");
			}
		}
	}

	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				theBank = Serializer.deserialize();
				int i = theView.table.getSelectedRow();
				DefaultTableModel tm = (DefaultTableModel) theView.table.getModel();
				Person p = null;
				for (Person p1 : theBank.clients.keySet()) {
					if (p1.getId() == Integer.parseInt(tm.getValueAt(i, 0).toString())) {
						p = p1;
					}
				}
				if (p != null) {
					theBank.removePerson(p);
					JOptionPane.showMessageDialog(theView, "Client deleted");
				}
				theView.tableSP.setVisible(false);
				theView.clear();
				Serializer.serialize(theBank);
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Select a client from the table");
			}
		}
	}

	class CloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theView.setVisible(false);
		}
	}

	class ViewAllListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theBank = Serializer.deserialize();
			theView.displayTable(theBank.getPersons());
		}
	}

}
