package org.gissolutions.jsimpleutils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.apt.AnnotationProcessors;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class NoteAnnotationProcessorFactory implements
		AnnotationProcessorFactory {

	public NoteAnnotationProcessorFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AnnotationProcessor getProcessorFor(
			Set<AnnotationTypeDeclaration> declarations,
			AnnotationProcessorEnvironment env) {
		AnnotationProcessor result;

		if (declarations.isEmpty()) {
			result = AnnotationProcessors.NO_OP;
		} else {
			// Next Step - implement this class:
			result = new NoteAnnotationProcessor(env);
		}
		return result;

	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Collections.singletonList("org.gissolutions.jsimpleutils.validation.RuleRegExp");

	}

	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptyList();

	}

}
