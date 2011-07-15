package org.gissolutions.jsimpleutils.validation;

import java.io.Serializable;

public class BusinessError<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 185513078776543636L;
	private String description;
	private T source;
	private Exception exception;
	
	public BusinessError() {
		super();
	}
	
	
	public BusinessError(T source, String description) {
		super();
		this.source = source;
		this.description = description;
	}


	public BusinessError(T source, String description, Exception exception) {
		super();
		this.source = source;
		this.description = description;
		this.exception = exception;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BusinessError<?>))
			return false;
		BusinessError<?> other = (BusinessError<?>) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}


	/**
	 * @return the source
	 */
	public T getSource() {
		return source;
	}


	/**
	 * @param source the source to set
	 */
	public void setSource(T source) {
		this.source = source;
	}


	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}


	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
