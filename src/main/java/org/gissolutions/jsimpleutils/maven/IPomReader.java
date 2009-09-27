package org.gissolutions.jsimpleutils.maven;

import java.io.File;

public interface IPomReader {
	public abstract Artifact read(File pomFile) throws PomParsingException;
}
