package org.gissolutions.jsimpleutils.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gissolutions.jsimpleutils.logging.FormattedLogger;

public abstract class AbstractValidator<T> implements IValidator<T> {
	private static FormattedLogger logger =  FormattedLogger.getLogger(AbstractValidator.class);
	private Map<String, Pattern> patterns;
	private List<BusinessError<T>> errors;
	
	public AbstractValidator() {
		patterns = new HashMap<String, Pattern>();
	}

	@Override
	public void validate(T object) {
		errors = new ArrayList<BusinessError<T>>();
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
						Pattern pat = this.patterns.get(rre.regExpName());
						if(pat !=null) {
							Matcher match = pat.matcher(fieldValue);
							if(!match.matches()) {
								String msg ="El valor '%s' del campo '%s' no cumple con la expresión '%s'";
								msg = String.format(msg, fieldValue, field.getName(), pat.pattern());
								errors.add(new BusinessError<T>(object, msg));
							}
						}else {
							String msg ="El patron '%s' no se encontró para el campo '%s'";
							msg = String.format(msg, rre.regExpName(), field.getName());
							errors.add(new BusinessError<T>(object, msg));
						}
					} catch (IllegalArgumentException e) {
						String msg = "%s: %s";
						msg = String.format(msg, e.getClass().getName(), e
								.getMessage());
						logger.error(msg);
						errors.add(new BusinessError<T>(object, msg, e));
					} catch (IllegalAccessException e) {
						String msg = "%s: %s";
						msg = String.format(msg, e.getClass().getName(), e
								.getMessage());
						logger.error(msg);
						errors.add(new BusinessError<T>(object, msg, e));
					}
					
				}
			}
		}
	}

	@Override
	public List<BusinessError<T>> getErrors() {
		return errors;
	}
	
	public void addPattern(String name, Pattern pattern) {
		this.patterns.put(name, pattern);		
	}
	public void addPattern(String name, String regexp) {
		Pattern pat = Pattern.compile(regexp);
		this.addPattern(name, pat);
	}
	public void removePattern(String name) {
		this.patterns.remove(name);
	}

	/**
	 * @return the patterns
	 */
	public Map<String, Pattern> getPatterns() {
		return patterns;
	}
}
