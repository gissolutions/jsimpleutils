package org.gissolutions.jsimpleutils.junit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectSpy {
	
	public static List<Method> getGetters(Object obj) {
		List<Method> lst = new ArrayList<Method>();
		Class<?> c = obj.getClass();
		for (Method m : c.getMethods()) {
			if (isMethodGetter(m)) {
				lst.add(m);
			}
		}
		return lst;
	}
	

	public static boolean isMethodGetter(Method m) {
		if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
			if (m.getParameterTypes().length == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isMethodSetter(Method m) {
		if (m.getName().startsWith("set")) {
			if (m.getParameterTypes().length == 1) {
				return true;
			}
		}
		return false;
	}

	public static String propertyName(Method m) {
		String propName =null;
		if (m.getName().startsWith("get")) {
			propName= m.getName().substring(3, m.getName().length());
		} else if (m.getName().startsWith("is")) {
			propName= m.getName().substring(2, m.getName().length());
		} else {
			propName= m.getName();
		}
		return propName.substring(0, 1).toLowerCase() + propName.substring(1, propName.length());
	}
	
}
