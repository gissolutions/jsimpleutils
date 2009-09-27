package org.gissolutions.jsimpleutils.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		endTime = System.currentTimeMillis() - startTime;
		isRunning = false;
	}

	public String getElapsedTime(SimpleDateFormat timerFormat) {
		endTime = System.currentTimeMillis() - startTime;
		Date dt = new Date(this.endTime);
		return timerFormat.format(dt);
	}

	public String getElapsedTime() {
		return this.getElapsedTime(this.timerFormat);
	}
	public long getElapsed() {
		return System.currentTimeMillis() - startTime;
	}
	public SimpleDateFormat getTimerFormat() {
		return timerFormat;
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
}
