package org.gissolutions.jsimpleutils.io;

import java.util.EventListener;

public interface IFileRejectedListener extends EventListener {
	public void fileRejectedOcurred(FileRejectedEvent event);
}
