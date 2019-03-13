package assignment5.activities;

import java.util.List;

@SuppressWarnings("unused")
public class Start {

	public static void main(String[] args) {

		List<MonitoredData> activities = Tasks.getActivityList();

		System.out.println("The number of distinct days is : " + Tasks.getDistinctDays());

		Tasks.actionOccurences();

	}
}
