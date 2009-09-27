package org.gissolutions.jsimpleutils.junit;

import java.io.PrintStream;
import java.lang.reflect.Method;

public class JavaAssertionProcessor implements IAssertionProcessor {
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
		int c =0;
		if(res == null) {
			aseTemplate="assertNull(%s.%s());";
			String asert = String.format(aseTemplate, variableName,
					m.getName());
			outputStream.println(asert);
			c++;
			return c;
		}
		if(cls.getName().equals("java.lang.Class")) {
			c++;
			return c;
		}
		if(cls == String.class) {
			aseTemplate="assertEquals(\"%s\",%s.%s());";
			String strRes= (String) res;
			String asert = String.format(aseTemplate, strRes, variableName,
					m.getName());
			outputStream.println(asert);
			c++;
		}else if(cls == Double.class || cls == double.class) {
			aseTemplate="assertEquals(%sd,%s.%s());";
			Double strRes= (Double) res;
			String asert = String.format(aseTemplate, strRes, variableName,
					m.getName());
			outputStream.println(asert);
			c++;
		}else if(cls == Integer.class || cls == int.class) {
			aseTemplate="assertEquals(%s,%s.%s());";
			Integer strRes= (Integer) res;
			String asert = String.format(aseTemplate, strRes, variableName,
					m.getName());
			outputStream.println(asert);
			c++;
		}else if(cls == Boolean.class || cls == boolean.class) {
			aseTemplate="assertEquals(%s,%s.%s());";
			Boolean strRes= (Boolean) res;
			String asert = String.format(aseTemplate, strRes, variableName,
					m.getName());
			outputStream.println(asert);
			c++;
		}else if(cls == java.util.Date.class) {
			aseTemplate="assertEquals(new Date(%sl),%s.%s());";
			java.util.Date strRes= (java.util.Date) res;
			String asert = String.format(aseTemplate, strRes.getTime(), variableName,
					m.getName());
			outputStream.println(asert);
			c++;
		}   
		
		return c;
	}


	public PrintStream getOutputStream() {
		return outputStream;
	}


	public void setOutputStream(PrintStream outputStream) {
		this.outputStream = outputStream;
	}

}
