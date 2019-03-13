package assignment2.queues.views;

import java.awt.Graphics;

import javax.swing.JPanel;

import assignment2.queues.models.Server;
import assignment2.queues.models.SimulationManager;
import assignment2.queues.models.Task;

@SuppressWarnings({ "unused", "serial" })

public class LivePanel extends JPanel {
	SimulationManager sm;

	public LivePanel(SimulationManager sm) {
		this.sm = sm;
	}

	public synchronized void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		int i = 50, j = 100, cnt = 0, m = 80;
		Server server;

		while (cnt < sm.noOfServers) {
			gr.drawImage(LiveView.server, i, 0, null);
			i = i + 150;
			cnt++;
		}

		cnt = 0;
		while (cnt < sm.noOfServers) {
			server = sm.servers[cnt];
			j = 100;
			for (Task t : server.tasks) {
				gr.drawImage(LiveView.task, m, j, null);
				j = j + 50;
			}
			m += 150;
			cnt++;
		}
	}
}
