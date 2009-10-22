package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.util.Date;
import java.util.EventObject;

public class FileAcceptedEvent extends EventObject {
	
	private static final long serialVersionUID = -2279274810105324325L;
	private File acceptedFile;
	private final Date fileAcceptedOn;
	
	public FileAcceptedEvent(Object source, File acceptedFile) {
		this(source);
		this.acceptedFile = acceptedFile;
	}

	public FileAcceptedEvent(Object source) {
		super(source);
		this.fileAcceptedOn = new Date();
	}

	/**
	 * @return the acceptedFile
	 */
	public File getAcceptedFile() {
		return acceptedFile;
	}

	/**
	 * @param acceptedFile the acceptedFile to set
	 */
	public void setAcceptedFile(File acceptedFile) {
		this.acceptedFile = acceptedFile;
	}

	/**
	 * @return the fileAcceptedOn
	 */
	public Date getFileAcceptedOn() {
		return fileAcceptedOn;
	}

}
