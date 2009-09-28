package org.gissolutions.jsimpleutils.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SimpleValidator<T> implements IValidator<T> {
	private Map<String, Pattern> patterns;
	
	public SimpleValidator() {
		patterns = new HashMap<String, Pattern>();
	}

	@Override
	public void validate(T object) {
		// TODO Auto-generated method stub

	}

}
