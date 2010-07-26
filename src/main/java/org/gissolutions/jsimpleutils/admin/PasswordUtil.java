package org.gissolutions.jsimpleutils.admin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

public class PasswordUtil {
	
	private final String passwordPattern="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";	
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PasswordUtil.class);
	private RegularExpression regExp;
	
	public PasswordUtil() {
		this.regExp = new RegularExpression(this.passwordPattern);
	}
	
	public boolean isPasswordValid(String pwd){
		return this.regExp.matches(pwd);
	}
	/**
	 * Retorna la edad de la fecha de la contraseña suministrada.
	 * @param passwordDate Fecha de último cambio de la contraseña
	 * @return Edad en días de la contraseña-
	 */
	public static int getAge(Date passwordDate) {

		Calendar pwdDate = Calendar.getInstance();
		pwdDate.setTime(passwordDate);
		Calendar startDate = new GregorianCalendar();
		Calendar dateNow = (Calendar) startDate.clone();
		int daysBetween = 0;
		while (pwdDate.before(dateNow)) {
			pwdDate.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;

	}
	public static String MD5Digest(String source){
		return MD5Digest(source, "UTF8");
	}
	/**
	 * Crea un hash a partrir de la fuente suministrada usando el algoritmo
	 * MD5 y un text encoding en UTF-8. Este método
	 * permite encriptar passwords que Tomcat pude leee.
	 * @param source fuente de texto del que se quiere obtener el hash.
	 * @param charsetName TODO
	 * @return hash de la fuente
	 */
	public static String MD5Digest(String source, String charsetName) {
		MessageDigest digest;
		try {
			digest = java.security.MessageDigest.getInstance("MD5");
			//String charsetName = "UTF8";
			digest.update(source.getBytes(charsetName));
			byte[] hash = digest.digest();
			String strHash = byteArrayToHexString(hash);
			return strHash;
//			StringBuffer hexString = new StringBuffer();
//			for (int i = 0; i < hash.length; i++) {
//				hexString.append(Integer.toHexString(0xFF & hash[i]));
//			}
//			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			return null;
		} catch (UnsupportedEncodingException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			return null;

		}

	}

	/**
	 * Convert a byte[] array to readable string format. This makes the "hex"
	 * readable!
	 * from http://www.devx.com/tips/Tip/13540
	 * 
	 * @return result String buffer in String format
	 * @param in
	 *            byte[] buffer to convert to string format
	 */
	public static String byteArrayToHexString(byte in[]) {
		byte ch = 0x00;
		int i = 0;
		if (in == null || in.length <= 0)
			return null;

		String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F" };
		StringBuffer out = new StringBuffer(in.length * 2);

		while (i < in.length) {
			ch = (byte) (in[i] & 0xF0); // Strip off high nibble
			ch = (byte) (ch >>> 4);
			// shift the bits down
			ch = (byte) (ch & 0x0F);
			// must do this is high order bit is on!
			out.append(pseudo[(int) ch]); // convert thenibble to a String
											// Character
			ch = (byte) (in[i] & 0x0F); // Strip off low nibble
			out.append(pseudo[(int) ch]); // convert the nibble to a String
											// Character
			i++;
		}
		String rslt = new String(out);
		return rslt.toLowerCase();
	}
	/**
	 * Retorna el patron de expresión regular que comple con:
	 * <ol>
	 * <li>La contraseña debe contener al menos una letra en mayúscula</li>
	 * <li>La contraseña debe contener al menos una letra en minúscula</li>
	 * <li>La contraseña debe contener al menos un número o caracter especial</li>
	 * <li>La contraseña debe tener una longitud no menor de 8 caracteres</li>
	 * </ol>
	 * @return
	 */
	public String getPasswordPattern() {
		return passwordPattern;
	}

}
