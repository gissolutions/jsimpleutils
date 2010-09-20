/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gissolutions.jsimpleutils.admin;

//import com.sun.corba.se.internal.util.SUNVMCID;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

/**
 * Encrypts and decrypts text using the DES Algorithm. Use Base64 enconding and
 * decoding. <blockquote>
 * 
 * <pre>
 * Encryptor enc = new Encryptor();
 * String clear = &quot;Hello World&quot;;
 * String encrypted = enc.Encrypt(clear);
 * System.out.println(&quot;\&quot;&quot; + clear + &quot;\&quot; encrypted to \&quot;&quot; + encrypted
 * 		+ &quot;\&quot; with key \&quot;&quot; + enc.getSecretKeyString() + &quot;\&quot;&quot;);
 * </pre>
 * 
 * </blockquote> The output would be: <br/>
 * "Hello World" encrypted to "uKo7xHvkwGoUxdjd4/Pb5Q==" with key "Hw51l21ShpE=" <br/>
 * To decrypt using the secretkey generated in the above code: <blockquote>
 * 
 * <pre>
 * String key = &quot;Hw51l21ShpE=&quot;;
 * Encryptor enc = new Encryptor(key);
 * String encrypted = &quot;uKo7xHvkwGoUxdjd4/Pb5Q==&quot;;
 * String decrypted = enc.Decrypt(encrypted);
 * System.out.println(&quot;\&quot;&quot; + encrypted + &quot;\&quot; decrypted to \&quot;&quot; + decrypted
 * 		+ &quot;\&quot; with key \&quot;&quot; + enc.getSecretKeyString() + &quot;\&quot;&quot;);
 * </pre>
 * 
 * </blockquote> The output would be: <br/>
 * "uKo7xHvkwGoUxdjd4/Pb5Q==" decrypted to "Hello World" with key "Hw51l21ShpE="
 * 
 * @version 1.0
 * @author L. Berrocal
 */
public class Encryptor {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Encryptor.class);

	private SecretKey secretKey;
	private CipherAlgorithm algorithm = CipherAlgorithm.DES;
	private String _Enconding = "UTF-8";
	private Cipher _EcryptionCipher;
	private Cipher _DecryptionCipher;
	private final Base64 bsf;

	/**
	 * Creates an intance of an Encryptor object auto generating a key.
	 */
	public Encryptor() {
		bsf = new Base64(true);
		try {
			this.secretKey = KeyGenerator.getInstance(this.algorithm.getName())
					.generateKey();
			this.init();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Creates a new instance of Encryptor
	 * 
	 * @param vSecretKey
	 *            Secret key to encode and decode
	 */
	public Encryptor(SecretKey vSecretKey) {
		bsf = new Base64(true);
		this.secretKey = vSecretKey;
		this.init();

	}

	/**
	 * Creates a new instance of Encryptor
	 * 
	 * @param vSecretKeyString
	 *            Secret key to encode and decode
	 */
	public Encryptor(String vSecretKeyString) {
		bsf = new Base64(true);

		try {
			byte[] b = this.base64Decode(vSecretKeyString);
			this.secretKey = new SecretKeySpec(b, this.algorithm.getName());
			this.init();
		} catch (java.io.IOException ex) {
			ex.printStackTrace();
		}

	}

	private void init() {
		try {
			this._EcryptionCipher = Cipher
					.getInstance(this.algorithm.getName());
			this._EcryptionCipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
			this._DecryptionCipher = Cipher.getInstance(this.algorithm
					.getName());
			this._DecryptionCipher.init(Cipher.DECRYPT_MODE, this.secretKey);
		} catch (InvalidKeyException ex) {
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (NoSuchPaddingException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Retuns the secret key.
	 * 
	 * @return String containing the secret key.
	 */
	public String getSecretKeyString() {
		byte[] keybytes = this.secretKey.getEncoded();
		return this.base64Encode(keybytes);
		// return new
		// sun.misc.BASE64Encoder().encode(this._SecretKey.getEncoded());
	}

	/**
	 * Decrypts encrypted text
	 * 
	 * @param vEncryptedText
	 *            Encrypted text
	 * @return clear text
	 */
	public String decrypt(String vEncryptedText) {
		byte[] dec;
		byte[] utf8;
		String result = "";
		try {

			//
			// DECODE BASE64 TO GET BYTES.
			//
			// dec = new sun.misc.BASE64Decoder().decodeBuffer(vEncryptedText);
			dec = this.base64Decode(vEncryptedText);
			// DECRYPT.
			//
			utf8 = this._DecryptionCipher.doFinal(dec);
			//
			// DECODE THE STRING INTO BYTES USING UTF-8.
			//
			result = new String(utf8, this._Enconding);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IllegalBlockSizeException ex) {
			ex.printStackTrace();
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
		} catch (BadPaddingException ex) {
			ex.printStackTrace();
		} catch (java.io.IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Encrypts clear text
	 * 
	 * @param vClearText
	 *            Clear text
	 * @return Encrypted String
	 */
	public String encrypt(String vClearText) {
		byte[] enc;
		byte[] utf8;
		String result = "";

		try {
			logger.debug("Clear text: " + vClearText);
			logger.debug("Clear text  len: " + vClearText.length());
			utf8 = vClearText.getBytes(this._Enconding);
			logger.debug("Orginal bytes length: " + utf8.length);
			enc = this._EcryptionCipher.doFinal(utf8);
			logger.debug("Encrypted bytes length: " + enc.length);
			result = this.base64Encode(enc);
			logger.debug("Base64 String length: " + result.length());
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (IllegalBlockSizeException ex) {
			ex.printStackTrace();
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
		} catch (BadPaddingException ex) {
			ex.printStackTrace();
		}
		// return result.substring(0, result.length()-2);
		return result;
	}

	private byte[] base64Decode(String data) throws IOException {
		// return Base64.decodeBase64(data);
		return bsf.decode(data);
	}

	String base64Encode(byte[] bytes) {
		return Base64.encodeBase64URLSafeString(bytes);

	}

	public static String generateKey() throws NoSuchAlgorithmException {

		// Base64 bsf = new Base64(true);
		// SecretKey secretKey =
		// KeyGenerator.getInstance(CipherAlgorithm.DES.getName()).generateKey();
		// byte[] keybytes = secretKey.getEncoded();
		// return bsf.encodeToString(keybytes);
		return generateKey(null);

	}

	public static String generateKey(CipherAlgorithm algorithm)
			throws NoSuchAlgorithmException {
		if (algorithm == null) {
			algorithm = CipherAlgorithm.DES;
		}
		//Base64 bsf = new Base64(true);
		SecretKey secretKey = KeyGenerator.getInstance(algorithm.getName())
				.generateKey();
		byte[] keybytes = secretKey.getEncoded();
		return Base64.encodeBase64URLSafeString(keybytes);

	}
    /**
     * Para encriptar MAPGIS01 con el SecretKey MAPDAMIN102
     * org.gissolutions.jsimpleutils.admin.Encryptor -s MAPDAMIN102 -e MAPGIS01
     * @param args
     * @throws NoSuchAlgorithmException
     */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		JSAPResult config = processArguments(args);
		String data = config.getString("data");
		String sk = config.getString("SecretKey");
		Encryptor enc = null;
		if (sk == null) {
			sk = Encryptor.generateKey();
		}
		enc = new Encryptor(sk);
		String mode = null;
		String encrypted = null;
		String decrypted = null;
		if (config.getBoolean("encrypt")) {
			encrypted = enc.encrypt(data);
			mode = "Encryption";
			decrypted = data;

		} else if (config.getBoolean("decrypt")) {
			decrypted = enc.decrypt(data);
			mode = "Decryption";
			encrypted = data;
		}
		System.out.println("Mode          : " + mode.toUpperCase());
		System.out.println("Data          : '" + data + "'");
		System.out.println("Encryption key: " + sk);
		System.out.println("**********************************");
		System.out.println("Clear Text: " + decrypted);
		System.out.println("Encryption: " + encrypted);
	}

	@SuppressWarnings("unchecked")
	protected static JSAPResult processArguments(String[] args) {
		JSAPResult config = null;
		JSAP jsap = new JSAP();

		UnflaggedOption opt1 = new UnflaggedOption("data").setStringParser(
				JSAP.STRING_PARSER).setRequired(false).setGreedy(true);
		opt1.setHelp("Data to encrypt of decript");

		FlaggedOption opt2 = new FlaggedOption("SecretKey").setStringParser(
				JSAP.STRING_PARSER).setRequired(false).setShortFlag('s')
				.setLongFlag(JSAP.NO_LONGFLAG);
		opt2.setHelp("Secret Key");

		Switch sw1 = new Switch("encrypt").setShortFlag('e').setLongFlag(
				"encrypt");
		sw1.setHelp("Encrypt the data");

		Switch sw2 = new Switch("decrypt").setShortFlag('d').setLongFlag(
				"decrypt");
		sw2.setHelp("Decrypt the data");
		//
		// Switch sw3 = new Switch("md5hash").setShortFlag('h').setLongFlag(
		// "md5hash");
		// sw3.setHelp("Muestra la configuración de la base de datos");

		try {
			jsap.registerParameter(opt1);
			jsap.registerParameter(opt2);
			jsap.registerParameter(sw1);
			jsap.registerParameter(sw2);
			// jsap.registerParameter(sw3);
		} catch (JSAPException e1) {
			e1.printStackTrace();
		}

		config = jsap.parse(args);
		if (!config.success()) {
			Iterator t = config.getErrorMessageIterator();
			while (t.hasNext()) {
				String str = (String) t.next();
				System.err.println("Error message: " + str);
			}
			System.err.println();
			System.err.println("Usage: java " + Encryptor.class.getName());
			System.err.println("                " + jsap.getUsage());
			System.err.println();
			// show full help as well
			System.err.println(jsap.getHelp());
			System.exit(1);
		}

		return config;
	}
}
