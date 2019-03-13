package assignment2.queues.models;

public class Task {
	private int processingTime;
	private int arrivingTime;
	private int taskNo;
	public int waitingTime;

	public Task(int processingTime, int arrivingTime, int taskNo) {
		this.processingTime = processingTime;
		this.arrivingTime = arrivingTime;
		this.taskNo = taskNo;
	}

	public synchronized int getProcessingTime() {
		return this.processingTime;
	}

	public int getArrivingTime() {
		return this.arrivingTime;
	}

	public int getTaskNo() {
		return this.taskNo;
	}

}
