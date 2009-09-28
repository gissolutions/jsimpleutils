package org.gissolutions.jsimpleutils.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.gissolutions.jsimpleutils.logging.FormattedLogger;
import org.gissolutions.jsimpleutils.logging.FormattedLoggerTest;

public class SimpleValidator<T> implements IValidator<T> {
	private static FormattedLogger logger =  FormattedLogger.getLogger(SimpleValidator.class);
	private Map<String, Pattern> patterns;
	
	public SimpleValidator() {
		patterns = new HashMap<String, Pattern>();
	}

	@Override
	public void validate(T object) {
		Class<T> cls = (Class<T>) object.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation instanceof RuleRegExp) {
					RuleRegExp rre = (RuleRegExp) annotation;
					logger.debug("field: %s regexp: %s", 
							field.getName(),
							rre.regExpName());
				}
			}
		}
	}
	
	public void validate2(Object obj) {
		Field[] fields = obj.getClass().getFields();
		
		
	}

}
