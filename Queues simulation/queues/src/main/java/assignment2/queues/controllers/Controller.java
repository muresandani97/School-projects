package assignment2.queues.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import assignment2.queues.models.SimulationManager;
import assignment2.queues.views.LiveView;
import assignment2.queues.views.View;

public class Controller {

	private View view;
	private LiveView liveView;
	private SimulationManager sm;

	public Controller() {
		this.view = new View();
		view.pressStart(new StartListener());
		view.pressClear(new ClearListener());
	}

	class StartListener implements ActionListener {
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			try {
				sm = new SimulationManager(view.getTimeLimit() * 1000, view.getProcessingMin() * 1000,
						view.getProcessingMax() * 1000, view.getArrivingMin() * 1000, view.getArrivingMax() * 1000,
						view.getServersNo(), view.getTasksNo(), view.getPer());
				liveView = new LiveView(sm);
				liveView.bottomPanel.setVisible(true);
				(new Thread(sm)).start();
				liveView.repaintD();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(view, "You must introduce valid numbers on all fields");
			}

		}
	}

	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.clear();
		}
	}
}
