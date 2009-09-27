package org.gissolutions.jsimpleutils.maven;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.gissolutions.jsimpleutils.junit.ObjectSpy;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

public class JDPomReader implements IPomReader {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(JDPomReader.class);
	private Namespace ns;
	public JDPomReader() {
		ns = Namespace.getNamespace("http://maven.apache.org/POM/4.0.0");
	}

	@Override
	public Artifact read(File pomFile) throws PomParsingException {
		Artifact artifact = new Artifact();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(pomFile);
			Element projectElement = doc.getRootElement();
			processProperty(artifact, projectElement,"artifactId");
			processProperty(artifact, projectElement,"groupId");
			processProperty(artifact, projectElement,"version");
		} catch (JDOMException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new PomParsingException(msg,e);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new PomParsingException(msg,e);
		}
		
		return artifact;
	}

	private void processProperty(Artifact artifact, Element projectElement, String propName) throws PomParsingException {
		Element el = projectElement.getChild(propName,ns);
		if(el != null) {
			String val = el.getText();
			Method meth =ObjectSpy.getSetterForProperty(artifact, propName, String.class);
			if(meth != null ) {
				try {
					meth.invoke(artifact, val);
				} catch (IllegalArgumentException e) {
					String msg = "%s: %s";
					msg = String.format(msg, e.getClass().getName(), e
							.getMessage());
					logger.error(msg);
					throw new PomParsingException(msg,e);
				} catch (IllegalAccessException e) {
					String msg = "%s: %s";
					msg = String.format(msg, e.getClass().getName(), e
							.getMessage());
					logger.error(msg);
					throw new PomParsingException(msg,e);
				} catch (InvocationTargetException e) {
					String msg = "%s: %s";
					msg = String.format(msg, e.getClass().getName(), e
							.getMessage());
					logger.error(msg);
					throw new PomParsingException(msg,e);
				}
			}
		}
	}

}
