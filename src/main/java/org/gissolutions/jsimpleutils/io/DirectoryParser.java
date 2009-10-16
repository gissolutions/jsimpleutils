package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DirectoryParser {
	
	protected List<Pattern> acceptPatterns;
	protected List<Pattern> rejectPatterns;

	public DirectoryParser() {
		super();
		this.acceptPatterns = new ArrayList<Pattern>();
		this.rejectPatterns = new ArrayList<Pattern>();
	}
	
	public DirectoryParser(String singleAcceptPattern){
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
//			logger.debug("Files in directory: " + directory.getName() +
//					" - "+ files.length);
			for (File file : files) {
				if (file.isDirectory()) {
					List<String> ofiles = this.find(file);
					if(ofiles.size()>0){
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
		for(Pattern rpat : this.rejectPatterns){
			Matcher matcher = rpat.matcher(file.getAbsolutePath());
			if(matcher.matches()) {
				this.processRejectedFile(file);
				return;
			}
		}
		for(Pattern apat : this.acceptPatterns){
			//logger.debug("Filename: " + file.getAbsolutePath());
			Matcher matcher = apat.matcher(file.getAbsolutePath());
			if (matcher.matches()) {
				fileList.add(file.getAbsolutePath());
				this.processAcceptedFile(file);
			}
		}
	}
	
	private void processRejectedFile(File file) {
		// TODO Auto-generated method stub
		
	}

	
	private  void processAcceptedFile(File file) {
		
	}
	
	public void addAcceptPattern(String acceptPattern) {
		Pattern pat = Pattern.compile(acceptPattern);
		this.addAcceptPattern(pat);
	}
	
	public void addAcceptPattern(Pattern acceptPattern) {
		this.acceptPatterns.add(acceptPattern);
	}
	
}
