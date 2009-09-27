package org.gissolutions.jsimpleutils.maven;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class JDPomReader implements IPomReader {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(JDPomReader.class);
	public JDPomReader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Artifact read(File pomFile) throws PomParsingException {
		Artifact artifact = new Artifact();
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(pomFile);
			Element projectElement = doc.getRootElement();
			Element el = projectElement.getChild("artifactId");
			if(el != null) {
				artifact.setArtifactId(el.getText());
			}
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

}
