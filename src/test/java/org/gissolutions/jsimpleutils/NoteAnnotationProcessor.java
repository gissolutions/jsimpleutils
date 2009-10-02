package org.gissolutions.jsimpleutils;

import java.util.Collection;
import java.util.Map;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.AnnotationMirror;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.AnnotationTypeElementDeclaration;
import com.sun.mirror.declaration.AnnotationValue;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.util.SourcePosition;

public class NoteAnnotationProcessor implements AnnotationProcessor {
	private AnnotationProcessorEnvironment environment;

	private AnnotationTypeDeclaration noteDeclaration;

	public NoteAnnotationProcessor(AnnotationProcessorEnvironment env) {
		environment = env;
		// get the annotation type declaration for our 'Note' annotation.
		// Note, this is also passed in to our annotation factory - this
		// is just an alternate way to do it.
		noteDeclaration = (AnnotationTypeDeclaration) environment
				.getTypeDeclaration("org.gissolutions.jsimpleutils.validation.RuleRegExp");
	}

	@Override
	public void process() {
		// Get all declarations that use the note annotation.
		Collection<Declaration> declarations = environment
				.getDeclarationsAnnotatedWith(noteDeclaration);
		for (Declaration declaration : declarations) {
			processNoteAnnotations(declaration);
		}
	}

	private void processNoteAnnotations(Declaration declaration) {
		// Get all of the annotation usage for this declaration.
		// the annotation mirror is a reflection of what is in the source.
		Collection<AnnotationMirror> annotations = declaration
				.getAnnotationMirrors();
		// iterate over the mirrors.
		for (AnnotationMirror mirror : annotations) {
			// if the mirror in this iteration is for our note declaration...
			if (mirror.getAnnotationType().getDeclaration().equals(
					noteDeclaration)) {

				// print out the goodies.
				SourcePosition position = mirror.getPosition();
				Map<AnnotationTypeElementDeclaration, AnnotationValue> values = mirror
						.getElementValues();

				System.out.println("Declaration: " + declaration.toString());
				System.out.println("Position: " + position);
				System.out.println("Values: ");
				for (Map.Entry<AnnotationTypeElementDeclaration, AnnotationValue> entry : values
						.entrySet()) {
					AnnotationTypeElementDeclaration elemDecl = entry.getKey();
					AnnotationValue value = entry.getValue();
					System.out.println("    " + elemDecl + "=" + value);
				}
			}
		}
	}

}
