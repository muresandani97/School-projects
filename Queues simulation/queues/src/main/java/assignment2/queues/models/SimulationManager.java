package assignment2.queues.models;

import java.util.ArrayList;
import java.util.Random;
import assignment2.queues.views.LiveView;

public class SimulationManager implements Runnable {
	private int timeLimit;
	private int maxProcessingTime;
	private int minProcessingTime;
	private int maxArrivingTime;
	private int minArrivingTime;
	public int noOfServers;
	public int noOfTasks;
	public int tasksPerServer;
	private int peakHour = 0;
	private int totalWaitingTime = 0;

	public ArrayList<Task> tasks = new ArrayList<Task>();
	public Server[] servers;

	public SimulationManager(int timeLimit, int minProcessingTime, int maxProcessingTime, int minArrivingTime,
			int maxArrivingTime, int noOfServers, int noOfTasks, int tasksPerServer) {
		this.timeLimit = timeLimit;
		this.minProcessingTime = minProcessingTime;
		this.maxProcessingTime = maxProcessingTime;
		this.minArrivingTime = minArrivingTime;
		this.maxArrivingTime = maxArrivingTime;
		this.noOfServers = noOfServers;
		this.noOfTasks = noOfTasks;
		this.tasksPerServer = tasksPerServer;
		tasks = generateRandomTasks();
		servers = new Server[noOfServers];
		for (int i = 0; i < noOfServers; i++) {
			servers[i] = new Server(i + 1);
		}
	}

	public Task minArrivingTimeTask() {
		int min = tasks.get(0).getArrivingTime();
		for (Task t : tasks)
			if (t.getArrivingTime() <= min)
				min = t.getArrivingTime();
		for (Task t : tasks)
			if (t.getArrivingTime() == min)
				return t;
		return null;
	}

	public Server pickServer() {
		int min = servers[0].getWaitingTime();
		for (Server s : servers)
			if (s.getWaitingTime() <= min)
				min = s.getWaitingTime();
		for (Server s : servers)
			if (s.getWaitingTime() == min && s.getNoOfTasks() < tasksPerServer)
				return s;
		return null;
	}

	public synchronized void setOpen(Server ser) {
		ser.openServer();
		Thread t = new Thread(ser);
		t.start();
	}

	public ArrayList<Task> generateRandomTasks() {
		ArrayList<Task> generatedTasks = new ArrayList<Task>();
		for (int i = 0; i < noOfTasks; i++) {
			Random rand = new Random();
			int processingTime = rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
			int arrivalTime = rand.nextInt(maxArrivingTime - minArrivingTime) + minArrivingTime;
			int taskNo = rand.nextInt(99);
			Task t = new Task(processingTime, arrivalTime, taskNo);
			generatedTasks.add(t);
		}
		return generatedTasks;
	}

	@Override
	public void run() {
		Server s = null;
		int currentTime = 1000;
		int cnt = this.noOfTasks;
		try {
			while (!tasks.isEmpty() && currentTime < timeLimit) {
				Task t = minArrivingTimeTask();
				if (t == null)
					System.out.println("There are no tasks");
				int wt = 0;
				Thread.sleep(t.getArrivingTime());
				s = pickServer();
				if (s == null) {
					Thread.sleep(maxProcessingTime);
					s = pickServer();
					peakHour = currentTime;
				}
				for (Task t1 : s.tasks)
					wt += t1.getProcessingTime();
				totalWaitingTime += wt;
				t.waitingTime = wt;
				s.addTask(t);
				if (!s.isOpen())
					setOpen(s);
				LiveView.textArea.append("Task " + t.getTaskNo() + " arrived at server " + s.getServerNo()
						+ " with arriving time " + t.getArrivingTime() + "\n");
				LiveView.repaintD();
				this.tasks.remove(t);
				this.noOfTasks--;
				currentTime+=1000;
			}

			LiveView.statTextArea.append("\n");
			LiveView.statTextArea.append("Average of waiting time: " + totalWaitingTime / cnt + "\n");
			LiveView.statTextArea.append("Peak hour: " + peakHour + "\n");
			LiveView.statTextArea.append("\n");
			LiveView.statTextArea.setVisible(true);

		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted");
		}
	}
}
