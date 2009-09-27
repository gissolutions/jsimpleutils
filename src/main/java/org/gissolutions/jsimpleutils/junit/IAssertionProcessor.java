package org.gissolutions.jsimpleutils.junit;

import java.io.PrintStream;
import java.lang.reflect.Method;

public interface IAssertionProcessor {
	public int proccessAssertion(String variableName,
			Method m, Object res, Class<?> cls);

	public PrintStream getOutputStream();

	public void setOutputStream(PrintStream outputStream);
}
