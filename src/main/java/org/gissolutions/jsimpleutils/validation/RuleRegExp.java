package org.gissolutions.jsimpleutils.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface RuleRegExp {
	String regExpName();
}
