package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.util.Date;
import java.util.EventObject;

public class FileRejectedEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4928167374449708701L;
	private File rejectedFile;
	private final Date fileRejectedOn;
	
	public FileRejectedEvent(Object source) {
		super(source);
		this.fileRejectedOn = new Date();
	}
	
	public FileRejectedEvent(Object source, File rejectedFile) {
		this(source);
		this.rejectedFile = rejectedFile;
	}

	/**
	 * @return the rejectedFile
	 */
	public File getRejectedFile() {
		return rejectedFile;
	}

	/**
	 * @param rejectedFile the rejectedFile to set
	 */
	public void setRejectedFile(File rejectedFile) {
		this.rejectedFile = rejectedFile;
	}

	/**
	 * @return the fileRejectedOn
	 */
	public Date getFileRejectedOn() {
		return fileRejectedOn;
	}

}
