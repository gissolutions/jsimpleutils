package org.gissolutions.jsimpleutils.logging;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 * http://www.devdaily.com/java/jwarehouse/jakarta-log4j-1.2.8/examples/subclass/index.shtml
 * @author luisberrocal
 *
 */
public class FormattedLogger extends Logger {
	private static FormatLoggerFactory myFactory = new FormatLoggerFactory();
	static String FQCN = FormattedLogger.class.getName() + ".";

	public FormattedLogger(String name) {
		super(name);
		
	}

	public static Category getInstance(String name) {
		return Logger.getLogger(name, myFactory);
	}

	public static Logger getLogger(String name) {
		return Logger.getLogger(name, myFactory);
	}
	
	public static Logger getLogger(Class<?> cls) {
		return  Logger.getLogger(cls.getName(), myFactory);
	}
	
	public void debug(String template, Object...args){
		
		String msg = String.format(template, args);
		super.log(FQCN, Level.DEBUG, msg, null);
		//super.debug(msg);
	}
	
	public void info(String template, Object...args){
		String msg = String.format(template, args);
		super.log(FQCN, Level.INFO, msg, null);
	}
	
	public void warn(String template, Object...args){
		String msg = String.format(template, args);
		super.log(FQCN, Level.WARN, msg, null);
	}
	
	public void error(String template, Object...args){
		String msg = String.format(template, args);
		super.log(FQCN, Level.ERROR, msg, null);
	}
	
}
