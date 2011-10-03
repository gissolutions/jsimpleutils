package org.gissolutions.jsimpleutils.sql.android;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Exploder<T> {
	private final char delimiter;
	private final char padding = ' ';
	private StringBuilder sb;

	public Exploder(char delimiter) {
		super();
		this.delimiter = delimiter;

	}

	public Exploder() {
		this(',');
	}
	public String explode(T[] array) {
		return this.explode(array, null, null);
	}
	public String explode(T[] array, String prefix) {
		return this.explode(array, prefix, null);
	}
	public String explode(T[] array, String prefix, String suffix) {
		sb = new StringBuilder();
		int c = 1;
		for (T t : array) {
			if(prefix != null) sb.append(prefix);
			sb.append(t.toString());
			if(suffix != null) sb.append(suffix);
			if (c != array.length) {
				sb.append(delimiter);
				sb.append(padding);
			}
			c++;
		}
		return sb.toString();
	}

	public String explode(List<T> list) {
		sb = new StringBuilder();
		int c = 1;
		for (T t : list) {
			sb.append(t.toString());
			if (c != list.size()) {
				sb.append(delimiter);
				sb.append(padding);
			}
			c++;
		}
		return sb.toString();
	}

	public String explode(List<T> list, String methodName) {
		Method method;

		sb = new StringBuilder();
		int c = 1;
		for (T t : list) {
			try {
				method = t.getClass().getMethod(methodName, new Class[] {});
				Object obj = method.invoke(t, new Object[] {});
				sb.append(obj.toString());
			} catch (SecurityException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			// sb.append(t.toString());
			if (c != list.size()) {
				sb.append(delimiter);
				sb.append(padding);
			}
			c++;
		}
		return sb.toString();
	}
}
