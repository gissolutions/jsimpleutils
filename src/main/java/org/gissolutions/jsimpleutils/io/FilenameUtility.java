package org.gissolutions.jsimpleutils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The class {@link FilenameUtility} contains methods used to manipulate filename and file sizes.
 * @author luisberrocal
 *
 */
public class FilenameUtility {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(FilenameUtility.class);
	public enum FileSizeFormat{
		MegaBytes(2),
		GigaBytes(3),
		KiloBytes(1),
		Bytes(0);
		public final double multiplier;
		FileSizeFormat(int power){
			this.multiplier = Math.pow(1024, power);
		}
		
	}
	/**
	 * Returns the filename of the file without the path.
	 * @param fileName supplied filename
	 * @return filename of the file without the path
	 */
	public static String getFilename(String fileName) {
		File tmpFile = new File(fileName);
		return tmpFile.getName();
		
	}
	/**
	 * Returns the extension for the filename supplied without the dot.
	 * @param filename name of the file supplied. Can be relative path
	 * @return file extension without the dot.
	 */
	public static String getFileExtension(String filename) {
		File tmpFile = new File(filename);
		//tmpFile.getName();
		int whereDot = tmpFile.getName().lastIndexOf('.');
		if (0 < whereDot && whereDot <= tmpFile.getName().length() - 2) {
			return tmpFile.getName().substring(whereDot + 1);
		}
		return "";
	}
	/**
	 * Inserts todays timestamp to a supplied filename with the format yyyyMMdd_HHmmss.
	 * For a file like c:/temp/photo.gif the this method would return c:/temp/photo_20100611_090015.gif
	 * if ranned on 6/jun/2010 at 9:00:15 am.
	 * @param filename Filename to append the date
	 * @return File with the date appended.
	 */
	public static String insertDate(String filename) {
		File f = new File(filename);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		int whereDot = f.getAbsolutePath().lastIndexOf('.');
		String t = f.getAbsolutePath().substring(0, whereDot);
		String ext = getFileExtension(filename);
		
		return t + "_" + sdf.format(new Date()) + "." + ext;
		
	}
	/**
	 * Replaces the extension of a file.
	 * @param filename Filename to be changed
	 * @param newExtension new extension without the dot.
	 * @return Filename with the new extension.
	 */
	public static String replaceExtension(String filename, String newExtension) {
		File tmpFile = new File(filename);
		//tmpFile.getName();
		int whereDot = tmpFile.getAbsolutePath().lastIndexOf('.');
		String n = tmpFile.getAbsolutePath().substring(0, whereDot) + 
		 "." + newExtension;
		return n;
	}
	
	public static double getFileSize(String filename, FileSizeFormat format) {
		File tmpFile = new File(filename);;
		String size = Long.toString(tmpFile.length());
		double sized = Double.parseDouble(size);
		double result = sized / format.multiplier;
		return result;
	}
	public static double getFileSize(String filename, FileSizeFormat format, int decimalPlaces) {
		File tmpFile = new File(filename);;
		String size = Long.toString(tmpFile.length());
		double sized = Double.parseDouble(size);
		double result = sized / format.multiplier;
		return round(result, decimalPlaces);
	}
	public static double round(double d, int decimalPlace){
	    // see the Javadoc about why we use a String in the constructor
	    // http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
	    BigDecimal bd = new BigDecimal(Double.toString(d));
	    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	  }
	public static String md5Hash(String filename) throws FileNotFoundException {
		MessageDigest digest;
		String output =null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw new RuntimeException("Unable to process file for MD5", e);
		}
		File f = new File(filename);
		InputStream is;
		
			is = new FileInputStream(f);
						
		byte[] buffer = new byte[8192];
		int read = 0;
		try {
			while( (read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}		
			byte[] md5sum = digest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			output = bigInt.toString(16);
			//System.out.println("MD5: " + output);
		}
		catch(IOException e) {
			throw new RuntimeException("Unable to process file for MD5", e);
		}
		finally {
			try {
				is.close();
			}
			catch(IOException e) {
				throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
			}
		}
		return output;

	}
}
