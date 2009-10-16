package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.util.Date;
import java.util.EventObject;

public class FileAcceptedEvent extends EventObject {
	private File acceptedFile;
	private Date fileAcceptedOn;
	
	public FileAcceptedEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
