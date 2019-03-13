package assignment1.polynomials.views;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame {

	public JButton addB;
	private JButton subB;
	private JButton mulB;
	private JButton derB;
	private JButton clrB;
	public JTextField p1T;
	private JLabel p1L;
	public JTextField p2T;
	private JLabel p2L;
	public JTextField resultT;
	private JLabel resultL;

	public View() {
		p1L = new JLabel("First polinom: ");
		p1L.setBounds(10, 50, 100, 20);
		this.add(p1L);
		p1T = new JTextField("");
		p1T.setBounds(110, 50, 350, 20);
		this.add(p1T);
		p2L = new JLabel("Second polinom: ");
		p2L.setBounds(10, 100, 100, 20);
		this.add(p2L);
		p2T = new JTextField("");
		p2T.setBounds(110, 100, 350, 20);
		this.add(p2T);
		resultL = new JLabel("Result: ");
		resultL.setBounds(10, 150, 100, 20);
		this.add(resultL);
		resultT = new JTextField("");
		resultT.setBounds(110, 150, 350, 20);
		this.add(resultT);
		addB = new JButton("Add");
		addB.setBounds(480, 30, 90, 20);
		this.add(addB);
		subB = new JButton("Subtract");
		subB.setBounds(480, 70, 90, 20);
		this.add(subB);
		mulB = new JButton("Multiply");
		mulB.setBounds(480, 110, 90, 20);
		this.add(mulB);
		derB = new JButton("Derivate");
		derB.setBounds(480, 150, 90, 20);
		this.add(derB);
		clrB = new JButton("Clear");
		clrB.setBounds(480, 190, 90, 20);
		this.add(clrB);
		this.setLayout(null);
		this.setTitle("Polynomials");
		this.setSize(600, 280);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void addAl(ActionListener a) {
		addB.addActionListener(a);
	}

	public void subAl(ActionListener a) {
		subB.addActionListener(a);
	}

	public void mulAl(ActionListener a) {
		mulB.addActionListener(a);
	}

	public void derAl(ActionListener a) {
		derB.addActionListener(a);
	}

	public void clrAl(ActionListener a) {
		clrB.addActionListener(a);
	}

}
