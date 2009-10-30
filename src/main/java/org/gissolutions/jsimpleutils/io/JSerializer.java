package org.gissolutions.jsimpleutils.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


import org.gissolutions.jsimpleutils.logging.FormattedLogger;
/**
 * The class JSerializer is used to rapidly serialize and deserialize objects
 * to and from files.
 * @author lberrocal
 *
 * @param <T> Object Type to be Serialized
 */
public class JSerializer<T> {
	private static FormattedLogger logger = (FormattedLogger) FormattedLogger
			.getLogger(JSerializer.class);

	@SuppressWarnings("unchecked")
	public T read(File file) {
		T recovered = null;
		try {
			// use buffering
			InputStream ifile = new FileInputStream(file);
			InputStream buffer = new BufferedInputStream(ifile);
			ObjectInput input = new ObjectInputStream(buffer);
			try {
				// deserialize the List
				recovered = (T) input.readObject();

			} finally {
				input.close();
			}
		} catch (ClassNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		return recovered;
	}

	public void write(T object, File file) {

		// use buffering
		try {
			OutputStream ofile = new FileOutputStream(file);
			OutputStream buffer = new BufferedOutputStream(ofile);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try {
				output.writeObject(object);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}

	}
}
