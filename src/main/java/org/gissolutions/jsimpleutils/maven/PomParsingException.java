package org.gissolutions.jsimpleutils.maven;

public class PomParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2889137739467934776L;

	public PomParsingException() {
	}

	public PomParsingException(String message) {
		super(message);

	}

	public PomParsingException(Throwable cause) {
		super(cause);

	}

	public PomParsingException(String message, Throwable cause) {
		super(message, cause);
	}

}
