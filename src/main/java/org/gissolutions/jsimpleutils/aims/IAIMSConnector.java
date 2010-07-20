package org.gissolutions.jsimpleutils.aims;

public interface IAIMSConnector {
	
	public abstract String getClientServices() throws AIMSConnectionException;

	public abstract String sendRequest(String serviceName, String axl) throws AIMSConnectionException;
	
	/**
	 * @return the host
	 */
	public abstract String getHost();

	public abstract int getPort();

}