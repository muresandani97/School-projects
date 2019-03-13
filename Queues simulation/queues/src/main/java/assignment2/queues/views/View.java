package assignment2.queues.views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class View extends JFrame {

	private JLabel processingLabel = new JLabel("Processing interval: ");
	private JLabel arrivingLabel = new JLabel("Arriving interval: ");
	public JTextField processingTextMin = new JTextField("");
	public JTextField processingTextMax = new JTextField("");
	public JTextField arrivingTextMin = new JTextField("");
	public JTextField arrivingTextMax = new JTextField("");
	private JLabel serversLabel = new JLabel("Number of servers");
	public JTextField serversText = new JTextField("");
	private JLabel tasksLabel = new JLabel("Number of tasks");
	public JTextField tasksText = new JTextField("");
	private JLabel timeLabel = new JLabel("Time limit");
	public JTextField timeText = new JTextField("");
	private JLabel perLabel = new JLabel("Tasks per server");
	public JTextField perText = new JTextField("");
	private JButton start = new JButton("Start");
	private JButton clear = new JButton("Clear");

	public View() {
		this.setTitle("Queues");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 275);
		this.setVisible(true);
		this.setLayout(null);
		processingLabel.setBounds(10, 20, 120, 20);
		this.add(processingLabel);
		arrivingLabel.setBounds(10, 50, 120, 20);
		this.add(arrivingLabel);
		processingTextMin.setBounds(130, 20, 35, 20);
		this.add(processingTextMin);
		processingTextMax.setBounds(180, 20, 35, 20);
		this.add(processingTextMax);
		arrivingTextMin.setBounds(130, 50, 35, 20);
		this.add(arrivingTextMin);
		arrivingTextMax.setBounds(180, 50, 35, 20);
		this.add(arrivingTextMax);
		serversLabel.setBounds(10, 80, 120, 20);
		this.add(serversLabel);
		serversText.setBounds(155, 80, 35, 20);
		this.add(serversText);
		tasksLabel.setBounds(10, 110, 120, 20);
		this.add(tasksLabel);
		tasksText.setBounds(155, 110, 35, 20);
		this.add(tasksText);
		timeLabel.setBounds(10, 140, 120, 20);
		this.add(timeLabel);
		timeText.setBounds(155, 140, 35, 20);
		this.add(timeText);
		perLabel.setBounds(10, 170, 120, 20);
		this.add(perLabel);
		perText.setBounds(155, 170, 35, 20);
		this.add(perText);
		start.setBounds(40, 200, 80, 20);
		this.add(start);
		clear.setBounds(160, 200, 80, 20);
		this.add(clear);
	}

	public void clear() {
		this.processingTextMin.setText("");
		this.processingTextMax.setText("");
		this.arrivingTextMin.setText("");
		this.arrivingTextMax.setText("");
		this.serversText.setText("");
		this.tasksText.setText("");
		this.timeText.setText("");
		this.perText.setText("");
	}

	public void pressStart(ActionListener action) {
		this.start.addActionListener(action);
	}

	public void pressClear(ActionListener action) {
		this.clear.addActionListener(action);
	}

	public int getProcessingMin() {
		return Integer.parseInt(processingTextMin.getText());
	}

	public int getProcessingMax() {
		return Integer.parseInt(processingTextMax.getText());
	}

	public int getArrivingMin() {
		return Integer.parseInt(arrivingTextMin.getText());
	}

	public int getArrivingMax() {
		return Integer.parseInt(arrivingTextMax.getText());
	}

	public int getServersNo() {
		return Integer.parseInt(serversText.getText());
	}

	public int getTasksNo() {
		return Integer.parseInt(tasksText.getText());
	}

	public int getTimeLimit() {
		return Integer.parseInt(timeText.getText());
	}

	public int getPer() {
		return Integer.parseInt(perText.getText());
	}

}
