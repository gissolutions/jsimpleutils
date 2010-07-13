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
	public void testDecrypt() {
		
		String clear ="Take me to the moon";
		
		String encrypted = encryptor.encrypt(clear);
		logger.debug(clear  + " = > " + encrypted);
	}

	@Test
	public void testEncrypt() {
		String encrypted = "HaU2O8xN7SKs1LePyjweQgY8zaWQw43k";
		String clear = encryptor.decrypt(encrypted);
		logger.debug(clear  + " < = " + encrypted);
		
	}
	
	@Test
	public void testGenerateKey() {
		String key = null;
		try {
			key = Encryptor.generateKey();
		} catch (NoSuchAlgorithmException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			fail(msg);
		}
		logger.debug("Key: " + key);
	}
}
