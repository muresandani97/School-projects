package assignment5.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoredData {

	private String startTime;
	private String endTime;
	private String activity;

	public MonitoredData(String startTime, String endTime, String activity) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public String getActivity() {
		return this.activity;
	}

	public Date getStartTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date st = null;
		try {

			st = formatter.parse(startTime);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return st;
	}

	public Date getEndTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date et = null;
		try {

			et = formatter.parse(endTime);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return et;
	}

	public Date getDuration() {
		return new Date(getEndTime().getTime() - getStartTime().getTime() - 7200000);
	}

	public int one() {
		return 1;
	}
}
