package org.gissolutions.jsimpleutils.validation;

public interface IValidator<T> {
	public abstract void validate(T object);
}
