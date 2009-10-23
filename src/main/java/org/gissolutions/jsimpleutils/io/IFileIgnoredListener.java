package org.gissolutions.jsimpleutils.io;

import java.util.EventListener;

public interface IFileIgnoredListener extends EventListener {
	public void fileIgnoredOcurred(FileIgnoredEvent event);
}
