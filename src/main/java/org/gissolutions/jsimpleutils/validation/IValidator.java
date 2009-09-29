package org.gissolutions.jsimpleutils.validation;

import java.util.List;

public interface IValidator<T> {
	public abstract void validate(T object);
	public abstract List<BusinessError<T>> getErrors();
}
