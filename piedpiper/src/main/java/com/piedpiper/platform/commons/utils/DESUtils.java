package com.piedpiper.platform.commons.utils;

import java.io.PrintStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;

public class DESUtils {
	private static final byte[] DES_KEY = { 101, 118, 101, 114, 98, 114, 105, 103, 104, 116 };

	public static String encryptBasedDes(String data) {
		String encryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			String ceb = "everbright";
			DESKeySpec deskey = new DESKeySpec(ceb.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(1, key, sr);
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return encryptedData;
	}

	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(2, key, sr);
			decryptedData = new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(cryptData)));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return decryptedData;
	}

	public static void main(String[] arg0) {
		System.out.println(encryptBasedDes("cape"));
		System.out.println(encryptBasedDes("avicit"));
	}
}
