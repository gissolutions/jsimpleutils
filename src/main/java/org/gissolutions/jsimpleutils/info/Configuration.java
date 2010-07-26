package org.gissolutions.jsimpleutils.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuration extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3072561823584816416L;
	private String separator="\\|";
	public Configuration() {
		super();
		
	}

	public Configuration(Properties defaults) {
		super(defaults);		
	}
	
	public List<String> getStringList(String key){
		List<String> lst = new ArrayList<String>();
    	String p = this.getProperty(key);
    	String[] parts = p.split(separator);
    	for (String string : parts) {
			lst.add(string);
		}
    	return lst;		
	}
	
	public List<Integer> getIntegerList(String key){
		List<Integer> lst = new ArrayList<Integer>();
    	String p = this.getProperty(key);
    	String[] parts = p.split(separator);
    	for (String string : parts) {
    		int i = Integer.parseInt(string);
			lst.add(i);
		}
    	return lst;		
	}
	
	
	
}
