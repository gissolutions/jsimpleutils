package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.util.Date;
import java.util.EventObject;

public class FileIgnoredEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4928167374449708701L;
	private File file;
	private final Date ocurredOn;
	
	public FileIgnoredEvent(Object source) {
		super(source);
		this.ocurredOn = new Date();
	}
	
	public FileIgnoredEvent(Object source, File file) {
		this(source);
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the ocurredOn
	 */
	public Date getOcurredOn() {
		return ocurredOn;
	}
	
	

}
