package org.gissolutions.jsimpleutils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
/**
 * The class QuickWriter is used to quickly create a file and write a character
 * separated values.  By default the enconding is UTF-8, the value separator is
 * comma (',') and the end of file character is '\n'.
 * @author lberrocal
 *
 */
public class QuickWriter {
	private final File file;
	private BufferedWriter writer;
	private final char separator;
	private final char eol;
	private final String encoding;
	
	public QuickWriter(File file) throws UnsupportedEncodingException, FileNotFoundException {
		this(file,"UTF-8",',','\n');
	
	}
	public QuickWriter(File file, String encoding) throws UnsupportedEncodingException, FileNotFoundException {
		this(file,encoding,',','\n');		
	}
	public QuickWriter(File file, String encoding, char separator, char eol) throws UnsupportedEncodingException, FileNotFoundException {
		super();
		this.file = file;
		this.encoding = encoding;
		this.separator = separator;
		this.eol = eol;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encoding));
	}

	public QuickWriter(File file, String encoding, char separator) throws UnsupportedEncodingException, FileNotFoundException {
		this(file, encoding,separator,'\n');
	}
	/**
	 * The method write a line with the toString of the objects separated a separator.
	 * @param objects Objects to write
	 * @throws IOException
	 */
	public void writeln(Object...objects) throws IOException {
		StringBuffer sb = new StringBuffer();
		int count=0;
		for (Object obj : objects) {
			count++;
			sb.append(obj);
			if(count != objects.length) {
				sb.append(this.separator);	
			}
		}
		sb.append(this.eol);
		this.writer.write(sb.toString());
		
	}
	public void close() throws IOException {
		this.writer.close();
	}

	public File getFile() {
		return file;
	}

	public char getSeparator() {
		return separator;
	}

	public char getEol() {
		return eol;
	}

	public String getEncoding() {
		return encoding;
	}
}
