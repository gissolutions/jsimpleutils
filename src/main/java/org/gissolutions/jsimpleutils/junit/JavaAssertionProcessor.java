package org.gissolutions.jsimpleutils.junit;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.gissolutions.jsimpleutils.logging.FormattedLogger;

public class JavaAssertionProcessor implements IAssertionProcessor {
	private static FormattedLogger logger = FormattedLogger
			.getLogger(JavaAssertionProcessor.class);
	PrintStream outputStream;

	public JavaAssertionProcessor() {
		outputStream = System.out;
	}

	public JavaAssertionProcessor(PrintStream psout) {
		super();
		this.outputStream = psout;
	}

	@Override
	public int proccessAssertion(String variableName, Method m, Object res,
			Class<?> cls) {
		String aseTemplate;
		int c = 0;
		if (res == null) {
			aseTemplate = "assertNull(%s.%s());";
			String asert = String
					.format(aseTemplate, variableName, m.getName());
			outputStream.println(asert);
			c++;
			return c;
		}
		if (cls.getName().equals("java.lang.Class")) {
			c++;
			return c;
		}
		if (cls == String.class) {
			aseTemplate = "assertEquals(\"%s\",%s.%s());";
			String strRes = (String) res;
			String asert = String.format(aseTemplate, strRes, variableName, m
					.getName());
			outputStream.println(asert);
			c++;
		} else if (cls == Double.class || cls == double.class) {
			aseTemplate = "assertEquals(%sd,%s.%s());";
			Double strRes = (Double) res;
			String asert = String.format(aseTemplate, strRes, variableName, m
					.getName());
			outputStream.println(asert);
			c++;
		} else if (cls == Integer.class || cls == int.class) {
			aseTemplate = "assertEquals(%s,%s.%s());";
			Integer strRes = (Integer) res;
			String asert = String.format(aseTemplate, strRes, variableName, m
					.getName());
			outputStream.println(asert);
			c++;
		} else if (cls == Boolean.class || cls == boolean.class) {
			aseTemplate = "assertEquals(%s,%s.%s());";
			Boolean strRes = (Boolean) res;
			String asert = String.format(aseTemplate, strRes, variableName, m
					.getName());
			outputStream.println(asert);
			c++;
		} else if (cls == java.util.Date.class) {
			aseTemplate = "assertEquals(new Date(%sl),%s.%s());";
			java.util.Date strRes = (java.util.Date) res;
			String asert = String.format(aseTemplate, strRes.getTime(),
					variableName, m.getName());
			outputStream.println(asert);
			c++;
		} else {
			logger.debug("Skipped class %s for method %s", cls.getName(), m
					.getName());
			Type returnType = m.getGenericReturnType();

			if (returnType instanceof ParameterizedType) {
				ParameterizedType type = (ParameterizedType) returnType;
				Type[] typeArguments = type.getActualTypeArguments();
				for (Type typeArgument : typeArguments) {
					Class typeArgClass = (Class) typeArgument;
					logger.debug("typeArgClass = %s", typeArgClass);
				}
			}
			browseField(m);

		}

		return c;
	}

	private void browseField(Method m) {
		String fieldname = ObjectSpy.propertyName(m);
		try {

			// logger.debug("Declaring class %s",
			// m.getDeclaringClass().getName());
			Field field = m.getDeclaringClass().getDeclaredField(fieldname);

			Type genericFieldType = field.getGenericType();

			if (genericFieldType instanceof ParameterizedType) {
				ParameterizedType aType = (ParameterizedType) genericFieldType;
				Type[] fieldArgTypes = aType.getActualTypeArguments();
				for (Type fieldArgType : fieldArgTypes) {
					Class fieldArgClass = (Class) fieldArgType;
					logger.debug("fieldArgClass = %s", fieldArgClass);
				}
			}

			Class<?> btype = field.getType();
			Type gtype = field.getGenericType();
			if (gtype instanceof ParameterizedType) {
				ParameterizedType agType = (ParameterizedType) gtype;
				logger.debug("XXXXXXXXXXXXXXXXX");
			}

		} catch (SecurityException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (NoSuchFieldException e) {
			String msg = "Field '%s' does not exist for class '%s'";
			msg = String
					.format(msg, fieldname, m.getDeclaringClass().getName());
			logger.error(msg);
		}
	}

	public PrintStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(PrintStream outputStream) {
		this.outputStream = outputStream;
	}

}
