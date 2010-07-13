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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * Encrypts and decrypts text using the DES Algorithm.
 * Use Base64 enconding and decoding developed by J. Hunter.
 * <blockquote><pre>
 * Encryptor enc = new Encryptor();
 * String clear = "Hello World";
 * String encrypted  = enc.Encrypt(clear);
 * System.out.println("\"" + clear + "\" encrypted to \"" + encrypted 
                +"\" with key \"" + enc.getSecretKeyString() +"\"") ;
 * </pre></blockquote>
 * The output would be: <br/>
 * "Hello World" encrypted to "uKo7xHvkwGoUxdjd4/Pb5Q==" with key "Hw51l21ShpE="
 * <br/>
 * To decrypt using the secretkey generated in the above code:
 * <blockquote><pre>
 * String key ="Hw51l21ShpE=";
 * Encryptor enc = new Encryptor(key);
 * String encrypted = "uKo7xHvkwGoUxdjd4/Pb5Q==";
 * String decrypted  = enc.Decrypt(encrypted);
 * System.out.println("\"" + encrypted + "\" decrypted to \"" + decrypted 
               +"\" with key \"" + enc.getSecretKeyString() +"\"") ;
 * </pre></blockquote>
 * The output would be: <br/>
 * "uKo7xHvkwGoUxdjd4/Pb5Q==" decrypted to "Hello World" with key "Hw51l21ShpE="
 * @version 1.0
 * @author L. Berrocal
 */
public class Encryptor
{

    private SecretKey secretKey;
    private String _Algorithm = "DES";
    private String _Enconding = "UTF-8";
    private Cipher _EcryptionCipher;
    private Cipher _DecryptionCipher;
	private final Base64 bsf;

    /**
     * Creates an intance of an Encryptor object auto generating a key.
     */
    public Encryptor()
    {
    	bsf = new Base64(true);
        try
        {
            this.secretKey = KeyGenerator.getInstance(this._Algorithm).generateKey();
            this.init();
        } catch (NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
        }
		
    }

    /** Creates a new instance of Encryptor
     * @param vSecretKey Secret key to encode and decode
     */
    public Encryptor(SecretKey vSecretKey)
    {
    	bsf = new Base64(true);
        this.secretKey = vSecretKey;
        this.init();
      
    }

    /**
     * Creates a new instance of Encryptor
     * @param vSecretKeyString Secret key to encode and decode
     */
    public Encryptor(String vSecretKeyString)
    {
    	bsf = new Base64(true);
        try
        {
            byte[] b = this.base64Decode(vSecretKeyString);
            this.secretKey = new SecretKeySpec(b, this._Algorithm);
            this.init();
        } catch (java.io.IOException ex)
        {
            ex.printStackTrace();
        }
        
    }

    private void init()
    {
        try
        {
            this._EcryptionCipher = Cipher.getInstance(this._Algorithm);
            this._EcryptionCipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            this._DecryptionCipher = Cipher.getInstance(this._Algorithm);
            this._DecryptionCipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        } catch (InvalidKeyException ex)
        {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
        } catch (NoSuchPaddingException ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * Retuns the secret key.
     * @return String containing the secret key.
     */
    public String getSecretKeyString()
    {
        byte[] keybytes = this.secretKey.getEncoded();
        return this.base64Encode(keybytes);
    //return new sun.misc.BASE64Encoder().encode(this._SecretKey.getEncoded());
    }
    /**
     * Decrypts encrypted text
     * @param vEncryptedText Encrypted text
     * @return clear text
     */
    public String decrypt(String vEncryptedText)
    {
        byte[] dec;
        byte[] utf8;
        String result = "";
        try
        {

//
//     DECODE BASE64 TO GET BYTES.
//
            //dec = new sun.misc.BASE64Decoder().decodeBuffer(vEncryptedText);
            dec = this.base64Decode(vEncryptedText);
//     DECRYPT.
//
            utf8 = this._DecryptionCipher.doFinal(dec);
//
//     DECODE THE STRING INTO BYTES USING UTF-8.
//
            result = new String(utf8, this._Enconding);
        } catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
        } catch (IllegalBlockSizeException ex)
        {
            ex.printStackTrace();
        } catch (IllegalStateException ex)
        {
            ex.printStackTrace();
        } catch (BadPaddingException ex)
        {
            ex.printStackTrace();
        } catch (java.io.IOException ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    /**
     * Encrypts clear text
     * @param vClearText Clear text
     * @return Encrypted String
     */
    public String encrypt(String vClearText)
    {
        byte[] enc;
        byte[] utf8;
        String result = "";

        try
        {

            utf8 = vClearText.getBytes(this._Enconding);
            enc = this._EcryptionCipher.doFinal(utf8);
            //result = new sun.misc.BASE64Encoder().encode(enc);
            result = this.base64Encode(enc);
        } catch (UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
        } catch (IllegalBlockSizeException ex)
        {
            ex.printStackTrace();
        } catch (IllegalStateException ex)
        {
            ex.printStackTrace();
        } catch (BadPaddingException ex)
        {
            ex.printStackTrace();
        } 
        return result;
    }

    private byte[] base64Decode(String data) throws IOException
    {
    	return bsf.decode(data);
//    	
//        try {
//			return (byte[]) bsf.decode(data);
//		} catch (DecoderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
        //com.oreilly.servlet.Base64Decoder.decodeToBytes(data, _Enconding);

    // sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
    // return dec.decodeBuffer(data);
    }

    String base64Encode(byte[] bytes)
    {
    	//Base64 bsf = new Base64(true);
    	//byte[] bs = bsf.encode(bytes);
    	return bsf.encodeToString(bytes);
    	//String st = new String(bytes);
    	//return st;
        //return com.oreilly.servlet.Base64Encoder.encode(bytes, _Enconding);
    //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
    //return enc.encode(bytes);
    }
    
    public static String generateKey() throws NoSuchAlgorithmException {
    	
    		Base64 bsf = new Base64(true);	
           SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
           byte[] keybytes = secretKey.getEncoded();
           return  bsf.encodeToString(keybytes);
       
    }
}

