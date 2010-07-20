package org.gissolutions.jsimpleutils.aims;

public enum PingType {
	APPLICATION_SERVER("appserverping"),
	CONNECTOR_PING("connectorping");
	
	private final String command;
	PingType(String cmd){
		command =cmd;
	}
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
}
