package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.event.EventListenerList;

/**
 * The class DirectoryScanner is used to find files and directories based on regular expressions patterns.
 * @author LBerrocal
 *
 */
public class DirectoryScanner {

	protected List<Pattern> acceptPatterns;
	protected List<Pattern> rejectPatterns;
	private EventListenerList listenerList;

	public DirectoryScanner() {
		super();
		this.acceptPatterns = new ArrayList<Pattern>();
		this.rejectPatterns = new ArrayList<Pattern>();
		this.listenerList = new EventListenerList();
	}

	public DirectoryScanner(String singleAcceptPattern) {
		this();
		addAcceptPattern(singleAcceptPattern);
	}

	public List<String> find(File directory, String singleAcceptPattern) {
		this.acceptPatterns = new ArrayList<Pattern>();
		addAcceptPattern(singleAcceptPattern);
		return this.find(directory);
	}

	public List<String> find(File directory) {
		List<String> fileList = new ArrayList<String>();
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			// logger.debug("Files in directory: " + directory.getName() +
			// " - "+ files.length);
			for (File file : files) {
				if (file.isDirectory()) {
					List<String> ofiles = this.find(file);
					if (ofiles.size() > 0) {
						fileList.addAll(ofiles);
					}
				} else {
					matchFile(fileList, file);
				}

			}
		}
		return fileList;

	}

	private void matchFile(List<String> fileList, File file) {
		for (Pattern rpat : this.rejectPatterns) {
			Matcher matcher = rpat.matcher(file.getAbsolutePath());
			if (matcher.matches()) {
				this.processRejectedFile(file);
				return;
			}
		}
		for (Pattern apat : this.acceptPatterns) {
			// logger.debug("Filename: " + file.getAbsolutePath());
			Matcher matcher = apat.matcher(file.getAbsolutePath());
			if (matcher.matches()) {
				fileList.add(file.getAbsolutePath());
				this.processAcceptedFile(file);
				return;
			}
		}
		this.processIgnoredFile(file);
	}

	private void processIgnoredFile(File file) {
		FileIgnoredEvent evt = new FileIgnoredEvent(this, file);
		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == IFileIgnoredListener.class) {
				((IFileIgnoredListener) listeners[i + 1])
						.fileIgnoredOcurred(evt);
			}
		}
		
	}

	private void processRejectedFile(File file) {
		FileRejectedEvent evt = new FileRejectedEvent(this, file);
		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == IFileRejectedListener.class) {
				((IFileRejectedListener) listeners[i + 1])
						.fileRejectedOcurred(evt);
			}
		}

	}

	private void processAcceptedFile(File file) {
		FileAcceptedEvent evt = new FileAcceptedEvent(this, file);
		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == IFileAcceptedListener.class) {
				((IFileAcceptedListener) listeners[i + 1])
						.fileAcceptedOcurred(evt);
			}
		}
	}

	public void addAcceptPattern(String acceptPattern) {
		Pattern pat = Pattern.compile(acceptPattern);
		this.addAcceptPattern(pat);
	}

	public void addAcceptPattern(Pattern acceptPattern) {
		this.acceptPatterns.add(acceptPattern);
	}

	public void addFileAcceptedListener(IFileAcceptedListener faListener) {
		this.listenerList.add(IFileAcceptedListener.class, faListener);
	}

	public void addFileRejectedListener(IFileRejectedListener frListener) {
		this.listenerList.add(IFileRejectedListener.class, frListener);
	}
	
	public void addFileIgnoredListener(IFileIgnoredListener fiListener) {
		this.listenerList.add(IFileIgnoredListener.class, fiListener);
	}
}
