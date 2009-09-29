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
//			logger.debug("field: %s", 
//					field.getName());
			for (Annotation annotation : annotations) {
				if(annotation instanceof RuleRegExp) {
					RuleRegExp rre = (RuleRegExp) annotation;
					try {
						field.setAccessible(true);
						String fieldValue = (String) field.get(object);
						logger.debug("field: %s value: %s  regexp: %s", 
								field.getName(), fieldValue,
								rre.regExpName());
						
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); 
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	


}
