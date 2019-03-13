package assignment5.activities;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {

	public static List<MonitoredData> getActivityList() {
		List<MonitoredData> activities = new ArrayList<MonitoredData>();
		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			stream.forEach(line -> activities.add(
					new MonitoredData(line.split("\t\t")[0], line.split("\t\t")[1], line.split("\t\t")[2].trim())));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return activities;
	}

	public static long getDistinctDays() {
		List<Integer> days = new ArrayList<Integer>();
		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
			stream.forEach(line -> days.add(Integer.parseInt(line.split("\t\t")[0].split(" ")[0].split("-")[0]
					+ line.split("\t\t")[0].split(" ")[0].split("-")[1]
					+ line.split("\t\t")[0].split(" ")[0].split("-")[2])));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return days.stream().distinct().count();
	}

	public static void actionOccurences() {
		List<MonitoredData> activities = Tasks.getActivityList();
		Map<String, Integer> occurences = activities.stream().collect(Collectors.toMap(MonitoredData::getActivity,
				MonitoredData::one, (oldValue, newValue) -> oldValue + 1, HashMap::new));
		try {
			PrintWriter writer = new PrintWriter("occurences.txt", "UTF-8");
			for (String s : occurences.keySet()) {
				writer.println(s + " " + occurences.get(s));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
