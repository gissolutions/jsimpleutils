package org.gissolutions.jsimpleutils.admin;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.gissolutions.jsimpleutils.junit.TestConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EncryptorTest {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EncryptorTest.class);
	private Encryptor encryptor;
	public EncryptorTest() {
		TestConfiguration.getInstance();
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		encryptor = new Encryptor("_p4m1qTlC5E");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncrypt() {
		
		String clear ="Take me to the moon";
		String expectedEncrypted = "HaU2O8xN7SKs1LePyjweQgY8zaWQw43k";
		
		String encrypted = encryptor.encrypt(clear);
		logger.debug(clear  + " = > " + encrypted);
		assertEquals(expectedEncrypted, encrypted);
		
		clear ="Panamá Áereo niño";
		encrypted = encryptor.encrypt(clear);
		logger.debug(clear  + " = > " + encrypted);
		expectedEncrypted="P5SUUG89ZgiunT__Cs0Bz_Dn6C_4XEeA";
		assertEquals(expectedEncrypted, encrypted);
		
		clear = "estoy desarrollando una red de mercadeo online, una red de " +
				"sistema binariooo";
		encrypted = encryptor.encrypt(clear);
		expectedEncrypted="yYnyXgSoLad8PeZDdFkA-iE9YRV9_-bhdcoEUUEjWgk8WczoM8-Pn7rBHkQbZNygOCwdu5IX_yfZ" +
				"SsQZFgSiO2t-RzVNGPIrvg1nMmNnYZ8";
		assertEquals(expectedEncrypted, encrypted);
		logger.debug(clear  + " = > " + encrypted);
	}

	@Test
	public void testDecrypt() {
		String originalClear = "Take me to the moon";
		String encrypted = "HaU2O8xN7SKs1LePyjweQgY8zaWQw43k";
		String clear = encryptor.decrypt(encrypted);
		logger.debug(clear  + " < = " + encrypted);		
		assertEquals(originalClear, clear);
		
	}
	
	@Test
	public void testGenerateKey() {
		String key = null;
		try {
			for(CipherAlgorithm c: CipherAlgorithm.values()) {
				key = Encryptor.generateKey(c);
				logger.debug("Key for " + c.getName() + ": " + key);
			}
			
		} catch (NoSuchAlgorithmException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
		
	}
}
