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
 * separated values.  By default the encoding is UTF-8, the value separator is
 * comma (',') and the end of file character is '\n'.
 * @author lberrocal
 *
 */
public class QuickWriter {
	
	private static final char DEFAULT_EOL = '\n';
	private static final String DEFAULT_COMMENT_CHARS ="#";
	public enum Encoding{
		UTF_8("UTF-8"), ISO_8859_1("ISO-8859-1");
		private final String name;
		
		private Encoding(String name) {
			this.name = name;
		}
		public String toString(){
			return this.name;
		}
	}
	private final File file;
	private BufferedWriter writer;
	private final char separator;
	private final char eol;
	private final String encoding;
	private final String commentChars;
	
	public QuickWriter(File file) throws UnsupportedEncodingException, FileNotFoundException {
		this(file,Encoding.UTF_8,',',DEFAULT_EOL);
	
	}
	@Deprecated
	public QuickWriter(File file, String encoding) throws UnsupportedEncodingException, FileNotFoundException {
		this(file,encoding,',',DEFAULT_EOL);		
	}
	@Deprecated
	public QuickWriter(File file, String encoding, char separator, char eol) throws UnsupportedEncodingException, FileNotFoundException {
		super();
		this.file = file;
		this.encoding = encoding;
		this.separator = separator;
		this.eol = eol;
		this.commentChars = DEFAULT_COMMENT_CHARS;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encoding));
	}
	@Deprecated
	public QuickWriter(File file, String encoding, char separator) throws UnsupportedEncodingException, FileNotFoundException {
		this(file, encoding,separator,DEFAULT_EOL);
	}
	
	public QuickWriter(File file, Encoding encoding, char separator, char eol) throws FileNotFoundException {
		super();
		this.file = file;
		this.encoding = encoding.toString();
		this.separator = separator;
		this.eol = eol;
		this.commentChars = DEFAULT_COMMENT_CHARS;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),this.encoding));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding:  " + this.encoding, e);
		}
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
	
	public void writeComment(String comment) throws IOException{
		this.writer.write(this.commentChars + comment + this.eol);
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
