 package com.piedpiper.platform.commons.utils;
 
 import java.io.PrintStream;
 import java.security.Key;
 import java.security.NoSuchAlgorithmException;
 import javax.crypto.Cipher;
 import javax.crypto.KeyGenerator;
 import javax.crypto.SecretKey;
 import javax.crypto.spec.SecretKeySpec;
 
 public class AESUtils
 {
   private static final String KEY_ALGORITHM = "AES";
   private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
   
   public static byte[] initSecretKey()
   {
     KeyGenerator kg = null;
     try {
       kg = KeyGenerator.getInstance("AES");
     } catch (NoSuchAlgorithmException e) {
       e.printStackTrace();
       return new byte[0];
     }
     
 
     kg.init(128);
     
     SecretKey secretKey = kg.generateKey();
     return secretKey.getEncoded();
   }
   
   private static Key toKey(byte[] key)
   {
     return new SecretKeySpec(key, "AES");
   }
   
   public static byte[] encrypt(byte[] data, Key key) throws Exception {
     return encrypt(data, key, "AES/ECB/PKCS5Padding");
   }
   
   public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
     return encrypt(data, key, "AES/ECB/PKCS5Padding");
   }
   
   public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm)
     throws Exception
   {
     Key k = toKey(key);
     return encrypt(data, k, cipherAlgorithm);
   }
   
   public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm)
     throws Exception
   {
     Cipher cipher = Cipher.getInstance(cipherAlgorithm);
     
     cipher.init(1, key);
     
     return cipher.doFinal(data);
   }
   
   public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
     return decrypt(data, key, "AES/ECB/PKCS5Padding");
   }
   
   public static byte[] decrypt(byte[] data, Key key) throws Exception {
     return decrypt(data, key, "AES/ECB/PKCS5Padding");
   }
   
   public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm)
     throws Exception
   {
     Key k = toKey(key);
     return decrypt(data, k, cipherAlgorithm);
   }
   
   public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm)
     throws Exception
   {
     Cipher cipher = Cipher.getInstance(cipherAlgorithm);
     
     cipher.init(2, key);
     
     return cipher.doFinal(data);
   }
   
   private static String showByteArray(byte[] data) {
     if (null == data) {
       return null;
     }
     StringBuilder sb = new StringBuilder("{");
     for (byte b : data) {
       sb.append(b).append(",");
     }
     sb.deleteCharAt(sb.length() - 1);
     sb.append("}");
     return sb.toString();
   }
   
 
 
 
   public static String parseByte2HexStr(byte[] buf)
   {
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < buf.length; i++)
     {
       String hex = Integer.toHexString(buf[i] & 0xFF);
       if (hex.length() == 1)
       {
         hex = '0' + hex;
       }
       sb.append(hex.toUpperCase());
     }
     return sb.toString();
   }
   
 
 
 
 
   public static byte[] parseHexStr2Byte(String hexStr)
   {
     if (hexStr.length() < 1)
       return null;
     byte[] result = new byte[hexStr.length() / 2];
     for (int i = 0; i < hexStr.length() / 2; i++)
     {
       int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
       int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
       result[i] = ((byte)(high * 16 + low));
     }
     return result;
   }
   
   public static void main(String[] args) throws Exception {
     byte[] key = initSecretKey();
     
     Key k = toKey(key);
     
     String data = "AES数据";
     System.out.println("加密前数据: string:" + data);
     byte[] encryptData = encrypt(data.getBytes(), k);
     String aes = parseByte2HexStr(encryptData);
     System.out.println("加密后数据: byte[]:" + aes);
     System.out.println();
     byte[] decryptData = decrypt(parseHexStr2Byte(aes), k);
     
     System.out.println("解密后数据: string:" + new String(decryptData));
   }
 }


