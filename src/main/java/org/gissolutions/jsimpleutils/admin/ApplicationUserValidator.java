package org.gissolutions.jsimpleutils.admin;

import java.util.Collection;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.gissolutions.jsimpleutils.validation.AbstractValidator;
/**
 * 
 * @author luisberrocal
 *
 * @param <T>
 */
public class ApplicationUserValidator<T> extends
		AbstractValidator<ApplicationUser<T>> {
	private static FormattedLogger logger = FormattedLogger
			.getLogger(ApplicationUserValidator.class);
	private XMLConfiguration configuration;
	
	public ApplicationUserValidator() {
		super();
		try
		{
		    ///jsimpleutils/src/main/java/org/gissolutions/jsimpleutils/admin/ApplicationUserValidator.java
		    XMLConfiguration.setDelimiter('~');
			XMLConfiguration config = new XMLConfiguration("org/gissolutions/jsimpleutils/admin/ApplicationUser.rules.xml");
			Object prop = config.getProperty("rules.regexprule[@name]");
			int count = ((Collection) prop).size();
			for (int i = 0; i < count; i++) {
				String template ="rules.regexprule(%s)[@name]";
				String propName = String.format(template, i);
				String nameVal = config.getString(propName);
				logger.debug("name %s", nameVal);
				
				template ="rules.regexprule(%s)[@pattern]";
				String strPattern = String.format(template, i);
				String patVal = config.getString(strPattern);
				logger.debug("Pattern %s", patVal);
				this.addPattern(nameVal, patVal);
			}
		}
		catch(ConfigurationException e)
		{
		    String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}

	}

}
