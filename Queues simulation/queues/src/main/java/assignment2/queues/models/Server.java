package assignment2.queues.models;

import java.util.ArrayList;

import assignment2.queues.views.LiveView;

@SuppressWarnings("unused")

public class Server implements Runnable {
	public ArrayList<Task> tasks;
	private boolean isOpen;
	private int serverNo;

	public Server(int serverNo) {
		this.serverNo = serverNo;
		this.tasks = new ArrayList<Task>();
		this.isOpen = false;
	}

	public int getServerNo() {
		return this.serverNo;
	}

	public synchronized void openServer() {
		this.isOpen = true;
	}

	public synchronized int getWaitingTime() {
		int wt = 0;
		for (Task t : tasks)
			wt += t.getProcessingTime();
		return wt;
	}

	public synchronized boolean isOpen() {
		return this.isOpen;
	}

	public synchronized int getNoOfTasks() {
		return this.tasks.size();
	}

	public synchronized boolean isEmpty() {
		return this.tasks.isEmpty();
	}

	public synchronized void addTask(Task newTask) {
		tasks.add(newTask);
	}

	@Override
	public void run() {
		int cnt;
		try {
			while (!tasks.isEmpty()) {
				cnt = 0;
				Task t = tasks.get(0);
				int wt = t.getProcessingTime();
				Thread.sleep(wt);
				LiveView.textArea.append(
						"Task " + t.getTaskNo() + " was finised. Processing time: " + t.getProcessingTime() + "\n");
				tasks.remove(t);
				if (this.isEmpty())
					this.isOpen = false;
				LiveView.repaintD();
				for (Task t1 : tasks)
					cnt++;
				LiveView.textArea.append(("Server " + this.getServerNo() + " : " + cnt + " tasks remained" + "\n"));
			}
		} catch (InterruptedException e) {
			System.out.println("Thread wasnt done");
		}
	}
}
