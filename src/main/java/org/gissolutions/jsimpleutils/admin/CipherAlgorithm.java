package org.gissolutions.jsimpleutils.admin;
/**
 * Represents the Cipher Algorithm as describes in Appendix A: Standard Names of
 * JavaTM Cryptography Extension (JCE) Reference Guide for the JavaTM 2 SDK, Standard Edition, v 1.4
 *  (http://download.oracle.com/docs/cd/E17476_01/javase/1.4.2/docs/guide/security/jce/JCERefGuide.html#AppA)
 * @author LBerrocal
 *
 */
public enum CipherAlgorithm {
	/**
	 * Advanced Encryption Standard as specified by NIST in a draft FIPS. Based on the 
	 * Rijndael algorithm by Joan Daemen and Vincent Rijmen, AES is a 128-bit block cipher 
	 * supporting keys of 128, 192, and 256 bits
	 */
	AES("AES"),
	/**
	 * The block cipher designed by Bruce Schneier
	 */
	BLOWFISH("Blowfish"),
	/**
	 * The Digital Encryption Standard as described in FIPS PUB 46-2
	 */
	DES("DES"),
	/**
	 * Triple DES Encryption (DES-EDE). 
	 */
	DESEDE("DESede");
	
	private final String name;
	private CipherAlgorithm(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
