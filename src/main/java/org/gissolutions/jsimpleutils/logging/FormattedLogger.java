package org.gissolutions.jsimpleutils.logging;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

public class FormattedLogger extends Logger {
	private static FormatLoggerFactory myFactory = new FormatLoggerFactory();

	public FormattedLogger(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Category getInstance(String name) {
		return Logger.getLogger(name, myFactory);
	}

	public static FormattedLogger getLogger(String name) {
		return (FormattedLogger) Logger.getLogger(name, myFactory);
	}
	
	public static FormattedLogger getLogger(Class<?> cls) {
		return (FormattedLogger) Logger.getLogger(cls.getName(), myFactory);
	}
	
	public void debug(String template, Object...args){
		String msg = String.format(template, args);
		super.debug(msg);
	}
	
	public void info(String template, Object...args){
		String msg = String.format(template, args);
		super.info(msg);
	}
	
	public void warn(String template, Object...args){
		String msg = String.format(template, args);
		super.warn(msg);
	}
}
