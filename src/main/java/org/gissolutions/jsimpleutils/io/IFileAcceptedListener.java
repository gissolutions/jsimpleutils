package org.gissolutions.jsimpleutils.io;

import java.util.EventListener;

public interface IFileAcceptedListener extends EventListener {
	public void fileAcceptedOcurred(FileAcceptedEvent event);
}
