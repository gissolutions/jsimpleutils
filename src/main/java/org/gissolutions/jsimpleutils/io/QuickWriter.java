package org.gissolutions.jsimpleutils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class QuickWriter {
	private final File file;
	private BufferedWriter writer;
	private final char separator;
	private final char eol;
	private final String encoding;
	
	public QuickWriter(File file) throws UnsupportedEncodingException, FileNotFoundException {
		super();
		this.file = file;
		this.separator=',';
		eol ='\n';
		encoding = "UTF8";
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encoding));
	}
	
	public QuickWriter(File file, String encoding, char separator, char eol) {
		super();
		this.file = file;
		this.encoding = encoding;
		this.separator = separator;
		this.eol = eol;
	}

	public QuickWriter(File file, String encoding, char separator) {
		this(file, encoding,separator,'\n');
	}

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
}
