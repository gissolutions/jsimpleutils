package org.gissolutions.jsimpleutils.text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Slugifier {
	public static final String DEFAULT_ENCODING = "UTF-8";
	public enum SlugAttribute{
		CHANGE_ENCODING(2),
		REPLACE_MULTI_SPACES(4),
		REPLACE_SPECIAL_CHARS(8);
		private final int value;
		private SlugAttribute(int value) {
			this.value=value;
		}
		public int getValue() {
			return value;
		}
		public String toBinary(){
			String binaryStr = Long.toString(value,2);
			return String.format("%8s", binaryStr).replace(' ', '0');	
		}
		public boolean is(int attribute){
			return (this.value & attribute) == this.value;
		}
	}
	private  int attributes;
	
	public Slugifier(SlugAttribute...attributes) {
		for (SlugAttribute slugAttribute : attributes) {
			this.attributes |= slugAttribute.getValue(); 
		}
	}
	
	public String buildSlug(String input) throws UnsupportedEncodingException {
		 if (input == null || input.length() == 0) return "";
		 String toReturn = input;
		 if(SlugAttribute.REPLACE_SPECIAL_CHARS.is(this.attributes)) {
			 toReturn = normalize(input);
		 }
		 if(SlugAttribute.REPLACE_MULTI_SPACES.is(attributes)) {
			 toReturn = cleanSpaces(toReturn);
		 }
		 toReturn = toReturn.replace(" ", "-");
		 toReturn = toReturn.toLowerCase();
		 if(SlugAttribute.CHANGE_ENCODING.is(attributes)) {
	        toReturn = URLEncoder.encode(toReturn, DEFAULT_ENCODING);
		 }
		 return toReturn;
	}
	
	public static String slugify(String input) throws UnsupportedEncodingException {
        if (input == null || input.length() == 0) return "";
        String toReturn = normalize(input);
        toReturn = cleanSpaces(toReturn);
        toReturn = toReturn.replace(" ", "-");
        toReturn = toReturn.toLowerCase();
        toReturn = URLEncoder.encode(toReturn, DEFAULT_ENCODING);
        return toReturn;
    }
 
    private static String normalize(String input) {
        if (input == null || input.length() == 0) return "";
        return Normalizer.normalize(input, Form.NFD).replaceAll("[^\\p{ASCII}]","");
    }
    private static String cleanSpaces(String input) {
    	return input.replaceAll("^ +| +$|( )+", "$1");

    }
}
