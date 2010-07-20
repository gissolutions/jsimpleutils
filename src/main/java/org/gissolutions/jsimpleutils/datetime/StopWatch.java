package org.gissolutions.jsimpleutils.datetime;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class StopWatch is a convience class to benchmark processes.
 * 
 * @author luisberrocal
 * 
 */
public class StopWatch {
	private long startTime;
	private long endTime;
	private SimpleDateFormat timerFormat;
	// private Thread updater;
	private boolean isRunning = false;

	public StopWatch() {
		super();
		timerFormat = new SimpleDateFormat("mm : ss.SSS");
		this.start();
	}

	public void start() {
		startTime = System.currentTimeMillis();
		isRunning = true;
	}

	public void stop() {
		endTime = System.currentTimeMillis();
		isRunning = false;
	}

	public String getElapsedTime(SimpleDateFormat timerFormat) {
		long tel = getElapsed();
		Date dt = new Date(tel);
		return timerFormat.format(dt);
	}

	/**
	 * Use {@link StopWatch#getElapsedFormatted()} instead
	 * 
	 * @return
	 */
	@Deprecated
	public String getElapsedTime() {
		return this.getElapsedTime(this.timerFormat);
	}

	public long getElapsed() {
		if (isRunning) {
			return System.currentTimeMillis() - startTime;
		} else {
			return endTime - startTime;
		}
	}

	public String getElapsedFormatted() {
		long tEndTime = getElapsed();
		return StopWatch.timeAsStringShort(tEndTime);

	}

	public SimpleDateFormat getTimerFormat() {
		return timerFormat;
	}
	public double getElapsedSeconds() {		
		long tEndTime = getElapsed();
		return (double) tEndTime /1000;
	}
	public void setTimerFormat(SimpleDateFormat timerFormat) {
		this.timerFormat = timerFormat;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * takes milliseconds and converts them into a String using all relevant
	 * time measures up to days
	 */
	public static String timeAsString(long milliSecs) {
		String s = "";
		int days = (int) (milliSecs / (1000 * 60 * 60 * 24));
		int hours = (int) (milliSecs % (1000 * 60 * 60 * 24)) / 3600000;
		int minutes = (int) (milliSecs % 3600000) / 60000;
		double seconds = (double) (milliSecs % 60000) / 1000;
		if (days != 0) {
			s += days + " days, ";
		}
		if (hours != 0) {
			s += hours + " h, ";
		}
		if (minutes != 0) {
			s += minutes + " min, ";
		}
		s += seconds + " sec";
		return s;
	}

	/**
	 * takes milliseconds and converts them into a short String. The format is
	 * 
	 * <pre>
	 * h:mm:ss
	 * </pre>
	 * 
	 * .
	 */
	public static String timeAsStringShort(long milliSecs) {
		DecimalFormat iFormatter = new DecimalFormat("00");
		DecimalFormat dFormatter = new DecimalFormat("00.000");
		int hours = Math.abs((int) milliSecs / 3600000);
		int minutes = Math.abs((int) (milliSecs % 3600000) / 60000);
		double seconds = Math.abs((double) (milliSecs % 60000) / 1000);
		String template = "%s:%s:%s";
		String s = String.format(template, iFormatter.format(hours), iFormatter
				.format(minutes), dFormatter.format(seconds));
		// String s = hours + ":";
		// s += ((minutes < 10) ? ("0" + minutes) : String.valueOf(minutes));
		// s += ":";
		// s += ((seconds < 10) ? ("0" + seconds) : String.valueOf(seconds));
		if (milliSecs < 0)
			s = "-" + s;
		return s;
	}

}
