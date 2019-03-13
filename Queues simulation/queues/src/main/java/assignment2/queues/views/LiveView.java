package assignment2.queues.views;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import assignment2.queues.models.SimulationManager;

@SuppressWarnings("serial")
public class LiveView extends JFrame {

	public SimulationManager sm;
	private JPanel fullPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	public static LivePanel bottomPanel;
	public ImageIcon serverI = new ImageIcon(getClass().getResource("server.png"));
	public static Image server;
	public ImageIcon taskI = new ImageIcon(getClass().getResource("task.png"));
	public static Image task;
	public static JTextArea textArea;
	public static JTextArea statTextArea;

	public LiveView(SimulationManager sm) {
		this.sm = sm;
		server = serverI.getImage();
		task = taskI.getImage();
		bottomPanel = new LivePanel(sm);
		statTextArea = new JTextArea(5, 20);
		statTextArea.setBounds(30, 200, 200, 70);
		statTextArea.setVisible(false);
		topPanel.add(statTextArea);
		textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		scrollPane.setBounds(650, 30, 750, 250);
		scrollPane.setEnabled(false);
		topPanel.add(scrollPane);
		topPanel.setMinimumSize(getMinimumSize());
		topPanel.setLayout(null);
		topPanel.setBackground(Color.DARK_GRAY);
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setVisible(false);
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		fullPanel.add(topPanel);
		fullPanel.add(bottomPanel);

		this.setTitle("Queues");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(fullPanel);
		this.pack();
		this.setVisible(true);
	}

	public static void repaintD() {
		bottomPanel.repaint();
	}
}
