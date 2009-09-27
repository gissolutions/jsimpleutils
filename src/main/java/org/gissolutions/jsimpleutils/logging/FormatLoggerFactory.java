package org.gissolutions.jsimpleutils.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class FormatLoggerFactory implements LoggerFactory {

	@Override
	public Logger makeNewLoggerInstance(String arg0) {
		return new FormattedLogger(arg0);
	}

}
