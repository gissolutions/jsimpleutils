package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.regex.Pattern;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.gissolutions.jsimpleutils.io.FilenameUtility.FileSizeFormat;


public class FileListerTask extends Task {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(FileListerTask.class);
	private File directory;
	private File outputFile;
	private QuickWriter writer;
	private String singleAcceptPattern;

	public FileListerTask() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		try {
			writer = new QuickWriter(this.outputFile);
			writer.writeln("NAME","PATH", "SIZE");
			DirectoryScanner ds = new DirectoryScanner();
			ds.addAcceptPattern(Pattern.compile(singleAcceptPattern, Pattern.CASE_INSENSITIVE));
			ds.addFileAcceptedListener(new FileListerTask.WriteEventListener());
			ds.find(this.directory);
			writer.close();
			log("File written " +this.outputFile.getAbsolutePath(), Project.MSG_INFO);
		} catch (UnsupportedEncodingException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new BuildException(e);
		} catch (FileNotFoundException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new BuildException(e);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new BuildException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.tools.ant.Task#init()
	 */
	@Override
	public void init() throws BuildException {
		super.init();
		String configfile = "/log4jprops.xml";
		URL url = this.getClass().getResource(configfile);

		DOMConfigurator.configure(url);
		logger.info("Log4j configured " + url.getFile());
	}

	class WriteEventListener implements IFileAcceptedListener {

		@Override
		public void fileAcceptedOcurred(FileAcceptedEvent event) {
			
			try {
				File f = event.getAcceptedFile();
				double size = FilenameUtility.getFileSize(f.getAbsolutePath(),
						FileSizeFormat.MegaBytes, 2);
				writer.writeln(f.getName(), f.getAbsolutePath(), size);
				log("File matched " + f.getName(), Project.MSG_INFO);
			} catch (Exception e) {
				String msg = "%s: %s";
				msg = String
						.format(msg, e.getClass().getName(), e.getMessage());
				logger.error(msg);
//				String msg = "Could not write for file '%s': %s";
//				msg = String.format(msg, f.getAbsolutePath(), e.getMessage());
//				logger.error(msg);
			}

		}

	}

	/**
	 * @return the directory
	 */
	public File getDirectory() {
		return directory;
	}

	/**
	 * @return the outputFile
	 */
	public File getOutputFile() {
		return outputFile;
	}

	/**
	 * @return the singleAcceptPattern
	 */
	public String getSingleAcceptPattern() {
		return singleAcceptPattern;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(File directory) {
		this.directory = directory;
	}

	/**
	 * @param outputFile the outputFile to set
	 */
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * @param singleAcceptPattern the singleAcceptPattern to set
	 */
	public void setSingleAcceptPattern(String singleAcceptPattern) {
		this.singleAcceptPattern = singleAcceptPattern;
	}
}
