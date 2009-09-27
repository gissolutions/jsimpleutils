package org.gissolutions.jsimpleutils.validation;

import java.util.Map;
import java.util.regex.Pattern;

public class SimpleValidator<T> implements IValidator<T> {
	private Map<String, Pattern> patterns;
	
	public SimpleValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(T object) {
		// TODO Auto-generated method stub

	}

}
