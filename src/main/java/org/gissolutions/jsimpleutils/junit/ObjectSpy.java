package org.gissolutions.jsimpleutils.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class ObjectSpy {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ObjectSpy.class);

	private PrintStream outputStream;

	public ObjectSpy() {
		outputStream = System.out;
	}

	public ObjectSpy(PrintStream psout) {
		super();
		this.outputStream = psout;
	}

	public ObjectSpy(File outputFile) throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(outputFile);
		this.outputStream = new PrintStream(fos);
	}

	public void listFields(Object obj) {
		Class<?> c = obj.getClass();
		String template = "%1$-15s %2$s";
		String display = String.format(template, "Class:", c.getName());
		outputStream.println(display);
		display = String.format(template, "Modifiers:", Modifier.toString(c.getModifiers()));
		outputStream.println(display);
		Type cgen  =c.getGenericSuperclass();
		if(cgen instanceof ParameterizedType) {
			outputStream.println("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
		}
		TypeVariable<?>[] typesParam =c.getTypeParameters();
		for (TypeVariable<?> typeVariable : typesParam) {
			display = String.format(template, "Type Var Name:", typeVariable.getName());
			outputStream.println(display);
			
//			display = String.format(template, "Type Var Type:", 
//					typeVariable.getGenericDeclaration().toString());
//			outputStream.println(display);
			
		}
		
		outputStream.println("=======================================");
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			//String template = "%1$-16s %2$s";
			display = String.format(template, "Name:", field.getName());
			outputStream.println(display);

			display = String.format(template, "Type:", field.getType()
					.getName());
			outputStream.println(display);
			display = String.format(template, "Generic Type:", field
					.getGenericType().toString());
			outputStream.println(display);

			display = String.format(template, "Modifiers:", Modifier
					.toString(field.getModifiers()));
			outputStream.println(display);

			Type gtype = field.getGenericType();
//			 ParameterizedType ptype =
//			        (ParameterizedType)field.getGenericType();
			if (gtype instanceof ParameterizedType) {

				// list the raw type information
				ParameterizedType ptype = (ParameterizedType) gtype;
				Type rtype = ptype.getRawType();
				outputStream.println("rawType is instance of "
						+ rtype.getClass().getName());
				outputStream.println(" (" + rtype + ")");

				// list the actual type arguments
				Type[] targs = ptype.getActualTypeArguments();
				outputStream.println("actual type arguments are:");
				for (int j = 0; j < targs.length; j++) {
					outputStream.println(" instance of "
							+ targs[j].getClass().getName() + ":");
					outputStream.println("  (" + targs[j] + ")");
				}

				
			}
			outputStream.println("=======================================");
		}
	}

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

	public static Method getSetterForProperty(Object obj, String propertyName,
			Class<?> parameterType) {
		Class<?> c = obj.getClass();
		String setterName = "set" + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1, propertyName.length());

		Method method = null;
		try {
			method = c.getMethod(setterName, parameterType);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return method;
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
		String propName = null;
		if (m.getName().startsWith("get")) {
			propName = m.getName().substring(3, m.getName().length());
		} else if (m.getName().startsWith("is")) {
			propName = m.getName().substring(2, m.getName().length());
		} else {
			propName = m.getName();
		}
		return propName.substring(0, 1).toLowerCase()
				+ propName.substring(1, propName.length());
	}

}
