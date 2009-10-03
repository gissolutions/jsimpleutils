package org.gissolutions.jsimpleutils.admin;
/**
 * 
 * @author luisberrocal
 *
 * @param <T>
 */
public interface IApplicationUser<T> {

	/**
	 * @return the userId
	 */
	public T getUserId();

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(T userId);

	/**
	 * @return the username
	 */
	public String getUsername();

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username);

	/**
	 * @return the password
	 */
	public String getPassword();

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password);

}
