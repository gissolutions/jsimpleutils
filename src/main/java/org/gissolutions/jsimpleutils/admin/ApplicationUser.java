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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationUser<T> other = (ApplicationUser<T>) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
