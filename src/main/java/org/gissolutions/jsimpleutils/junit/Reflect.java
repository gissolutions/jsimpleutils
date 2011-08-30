package org.gissolutions.jsimpleutils.junit;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;

import org.gissolutions.jsimpleutils.validation.BusinessError;
/**
 * From http://www.ibm.com/developerworks/library/j-cwt11085.html
 * @author luisberrocal
 *
 */
public class Reflect {
	 private static HashSet<String> s_processed = new HashSet<String>();
	    
	    private static void describe(String lead, Field field) {
	        
	        // get base and generic types, check kind
	        Class<?> btype = field.getType();
	        Type gtype = field.getGenericType();
	        if (gtype instanceof ParameterizedType) {
	            
	            // list basic parameterized type information
	            ParameterizedType ptype = (ParameterizedType)gtype;
	            System.out.println(lead + field.getName() +
	                " is of parameterized type");
	            System.out.println(lead + ' ' + btype.getName());
	            
	            // print list of actual types for parameters
	            System.out.print(lead + " using types (");
	            Type[] actuals = ptype.getActualTypeArguments();
	            for (int i = 0; i < actuals.length; i++) {
	                if (i > 0) {
	                    System.out.print(" ");
	                }
	                Type actual = actuals[i];
	                if (actual instanceof Class<?>) {
	                    System.out.print(((Class<?>)actual).getName());
	                } else {
	                    System.out.print(actuals[i]);
	                }
	            }
	            System.out.println(")");
	            
	            // analyze all parameter type classes
	            for (int i = 0; i < actuals.length; i++) {
	                Type actual = actuals[i];
	                if (actual instanceof Class<?>) {
	                    analyze(lead, (Class<?>)actual);
	                }
	            }
	            
	        } else if (gtype instanceof GenericArrayType) {
	            
	            // list array type and use component type
	            System.out.println(lead + field.getName() +
	                " is array type " + gtype);
	            gtype = ((GenericArrayType)gtype).getGenericComponentType();
	            
	        } else {
	            
	            // just list basic information
	            System.out.println(lead + field.getName() +
	                " is of type " + btype.getName());
	        }
	        
	        // analyze the base type of this field
	        analyze(lead, btype);
	    }
	    
	    private static void analyze(String lead, Class<?> clas) {
	        
	        // substitute component type in case of an array
	        if (clas.isArray()) {
	            clas = clas.getComponentType();
	        }
	        
	        // make sure class should be expanded
	        String name = clas.getName();
	        if (!clas.isPrimitive() && !clas.isInterface() &&
	            !name.startsWith("java.lang.") && !s_processed.contains(name)) {
	            
	            // print introduction for class
	            s_processed.add(name);
	            System.out.println(lead + "Class " +
	                clas.getName() + " details:");
	            
	            // process each field of class
	            String indent = lead + ' ';
	            Field[] fields = clas.getDeclaredFields();
	            for (int i = 0; i < fields.length; i++) {
	                Field field = fields[i];
	                if (!Modifier.isStatic(field.getModifiers())) {
	                    describe(indent, field);
	                }
	            }
	        }
	    }
	    
	    public static void main(String[] args) throws Exception {
	    	BusinessError<Integer> be = new BusinessError<Integer>();
	    	Class<?> clas = be.getClass();
	        analyze("", clas);
	    }
}
