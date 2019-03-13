package assignment1.polynomials.controllers;

import javax.swing.JOptionPane;

import assignment1.polynomials.models.Operations;
import assignment1.polynomials.models.Polinom;
import assignment1.polynomials.views.View;

public class Controller {
	private View theView;

	public Controller() {
		theView = new View();
		theView.addAl(e -> {
			try {
				Polinom p1 = Operations.stringToPolinom(theView.p1T.getText());
				Polinom p2 = Operations.stringToPolinom(theView.p2T.getText());
				Polinom result = Operations.addPolinoms(p1, p2);
				theView.resultT.setText(Operations.polinomToString(result));
				theView.repaint();
			} catch (IndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Invalid input data");
			}
		});
		theView.subAl(e -> {
			try {
				Polinom p1 = Operations.stringToPolinom(theView.p1T.getText());
				Polinom p2 = Operations.stringToPolinom(theView.p2T.getText());
				Polinom result = Operations.subtractPolinoms(p1, p2);
				if (Operations.polinomToString(result).equals("")) {
					theView.resultT.setText("0");
				} else {
					theView.resultT.setText(Operations.polinomToString(result));
				}
				theView.repaint();
			} catch (IndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Invalid input data");
			}
		});
		theView.mulAl(e -> {
			try {
				Polinom p1 = Operations.stringToPolinom(theView.p1T.getText());
				Polinom p2 = Operations.stringToPolinom(theView.p2T.getText());
				Polinom result = Operations.multiplyPolinoms(p1, p2);
				theView.resultT.setText(Operations.polinomToString(result));
				theView.repaint();
			} catch (IndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Invalid input data");
			}
		});
		theView.derAl(e -> {
			try {
				Polinom p = Operations.stringToPolinom(theView.p1T.getText());
				Polinom result = Operations.derivatePolinom(p);
				theView.resultT.setText(Operations.polinomToString(result));
				theView.repaint();
			} catch (IndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(theView, "Invalid input data");
			}
		});
		theView.clrAl(e -> {
			theView.p1T.setText("");
			theView.p2T.setText("");
			theView.resultT.setText("");
			theView.repaint();
		});
	}
}
