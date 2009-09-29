package org.gissolutions.jsimpleutils.admin;

import org.gissolutions.jsimpleutils.validation.RuleRegExp;

public class ApplicationUser<T> implements IApplicationUser<T> {
	private T userId;
	@RuleRegExp(regExpName="usernamePattern")
	private String username;
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
