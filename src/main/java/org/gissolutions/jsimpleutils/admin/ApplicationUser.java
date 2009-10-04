package org.gissolutions.jsimpleutils.admin;

import java.io.Serializable;

import org.gissolutions.jsimpleutils.validation.RuleRegExp;
/**
 * 
 * @author luisberrocal
 *
 * @param <T>
 */
public class ApplicationUser<T> implements IApplicationUser<T>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7678536256810356854L;
	private T userId;
	@RuleRegExp(regExpName="username-pattern")
	private String username;
	@RuleRegExp(regExpName="password-pattern")
	private String password;
	
	@Override
	public T getUserId() {
		return userId;
	}
	@Override
	public void setUserId(T userId) {
		this.userId = userId;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
}
