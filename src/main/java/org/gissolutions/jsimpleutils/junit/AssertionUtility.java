package org.gissolutions.jsimpleutils.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AssertionUtility {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AssertionUtility.class);
	private List<IAssertionProcessor> processors;
	private PrintStream outputStream;
	private int maxLevel = 3;
	private int currentLevel;

	public AssertionUtility() {
		this(System.out);
	}

	public AssertionUtility(PrintStream outpuStream) {
		init(outpuStream);
	}

	public AssertionUtility(File outputFile) throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(outputFile);
		init(new PrintStream(fos));
	}

	private void init(PrintStream outpuStream) {
		this.outputStream = outpuStream;
		this.processors = new ArrayList<IAssertionProcessor>();
		this.addProcessor(new JavaAssertionProcessor());
	}

	public void writeAssertions(Object obj, String varName) {
		this.currentLevel = 1;
		this.writeAssertionsLevel(obj, varName, currentLevel);
		this.outputStream.close();
	}

	private void writeAssertionsLevel(Object obj, String varName, int thisLevel) {
		List<Method> methods = ObjectSpy.getGetters(obj);
		for (Method method : methods) {
			Object[] param = null;
			Object res = null;
			try {
				res = method.invoke(obj, param);
			} catch (IllegalArgumentException e) {
				String msg = "%s: %s | Method: %s.%s() VarName: %s ";
				msg = String
						.format(msg, e.getClass().getName(), 
								e.getMessage(),
								method.getDeclaringClass().getName(),
								method.getName(), varName);
				logger.error(msg);
			} catch (IllegalAccessException e) {
				String msg = "%s: %s | Method: %s.%s() VarName: %s ";
				msg = String
						.format(msg, e.getClass().getName(), 
								e.getMessage(),
								method.getDeclaringClass().getName(),
								method.getName(), varName);
				logger.error(msg);
			} catch (InvocationTargetException e) {
				String msg = "%s: %s | Method: %s.%s() VarName: %s ";
				msg = String
						.format(msg, e.getClass().getName(), 
								e.getMessage(),
								method.getDeclaringClass().getName(),
								method.getName(), varName);
				logger.error(msg);
			}
			Class<?> cls = method.getReturnType();
			int p = 0;
			for (IAssertionProcessor proc : this.processors) {
				p += proc.proccessAssertion(varName, method, res, cls);
			}
			if (p == 0) {
				if (thisLevel >= maxLevel) {
					String msg = "Method '%s' skipped type '%s' not supported";
					msg = String.format(msg, method.getName(), cls.getName());
					logger.warn(msg);
				}else {
					String def ="%1$s %2$s = %3$s.%4$s();";
					String var = ObjectSpy.propertyName(method)+ currentLevel;
					def = String.format(def, cls.getName(), var,
							varName, method.getName());
					this.outputStream.println();
					this.outputStream.println(String.format("// Variable %s level %s",
							var, thisLevel));
					this.outputStream.println(def);
					this.writeAssertionsLevel(res, var, thisLevel+1);
					
				}
			}
		}
		//this.currentLevel++;
		
	}

	public void addProcessor(IAssertionProcessor processor) {
		processor.setOutputStream(this.outputStream);
		this.processors.add(processor);
	}


	

	

	public List<IAssertionProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<IAssertionProcessor> processors) {
		this.processors = processors;
	}

	public PrintStream getOutpuStream() {
		return outputStream;
	}

	public void setOutpuStream(PrintStream outpuStream) {
		this.outputStream = outpuStream;
	}
}
