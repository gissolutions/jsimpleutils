package org.gissolutions.jsimpleutils.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Basado en
 * http://www.javaworld.com/javaworld/javatips/jw-javatip76.html?page=2 esta
 * pendiente de verificar la implementación rádpida mostrada en
 * http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
 * 
 * @author LBerrocal
 * 
 */
public class ObjectCloner {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ObjectCloner.class);

	static public Object deepCopy(Object oldObj) throws ObjectCloningException {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		ByteArrayOutputStream bos = new ByteArrayOutputStream(); // A
		try {
			oos = new ObjectOutputStream(bos); // B
			// serialize and pass the object
			oos.writeObject(oldObj); // C
			oos.flush(); // D
			ByteArrayInputStream bin = new ByteArrayInputStream(bos
					.toByteArray()); // E
			ois = new ObjectInputStream(bin); // F
			// return the new object
			return ois.readObject();
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new ObjectCloningException(e);
		} catch (ClassNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new ObjectCloningException(e);
		} finally {
			try {
				oos.close();
				ois.close();
			} catch (IOException e) {
				String msg = "%s: %s";
				msg = String
						.format(msg, e.getClass().getName(), e.getMessage());
				logger.error(msg);
			}
		}

		// G
		// }
		// catch(Exception e)
		// {
		// String msg = "%s: %s";
		// msg = String.format(msg, e.getClass().getName(), e.getMessage());
		// logger.error(msg);
		// throw(e);
		// }
		// finally
		// {
		// oos.close();
		// ois.close();
		// }
	}

}
