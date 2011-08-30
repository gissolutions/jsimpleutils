package org.gissolutions.jsimpleutils.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagParser {
	private final Pattern pattern;
	public final static String DEFAULT_TAG_PATTERN ="#([\\p{L}\\._\\d]*)";
	public TagParser() {
		this.pattern = Pattern.compile(DEFAULT_TAG_PATTERN);
	}
	
	public List<String> parse(String line){
		List<String> tagNames = new ArrayList<String>();
		if(line != null && line.length() > 0) {
			Matcher m = pattern.matcher(line);
			boolean res = m.find();
			while(res) {
				String name = m.group(1);
				tagNames.add(name);
				res = m.find();
			}
		}
		
		return tagNames;
	}
	
}
