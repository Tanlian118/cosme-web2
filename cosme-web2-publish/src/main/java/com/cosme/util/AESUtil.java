/**
 * 天空城 ©2012. 天空城通行证系统 passport.tiankong520.com
 * 
 * @(#)AESUtil3.java V0.0.1 2010-11-18
 */
package com.cosme.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESUtil {
	private static final Log logger = LogFactory.getLog(AESUtil.class);

	public AESUtil() {
	}

	public static String makeKey() {
		KeyGenerator kgen;
		try {
			kgen = KeyGenerator.getInstance("AES");
		}
		catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		kgen.init(128); // 192 and 256 bits may not be available 
		// Generate the secret key specs. 
		SecretKey key = kgen.generateKey();
		byte[] raw = key.getEncoded();
		String content = byte2hex(raw);
		return content;
	}

	public static Key getKey(String publickey) {
		//		publickey = "1556272FD0F3D48E1923240296A6F869";

		//logger.info("publickey:" + publickey);
		byte[] bytes = hex2byte(publickey);
		return new SecretKeySpec(bytes, "AES");
	}

	/**
	 * 对于content超过15位的时候和IOS不兼容，使用encryptAES代替
	 * @param content
	 * @param publickey
	 * @return
	 */
	@Deprecated
	public static String encrypt(String content, String publickey) {
		byte[] bytes = hex2byte(publickey);
		Key key = new SecretKeySpec(bytes, "AES");
		try {
			Cipher cp = Cipher.getInstance("AES");
			cp.init(Cipher.ENCRYPT_MODE, key);

			byte[] ptext = content.getBytes();
			byte[] ctext = cp.doFinal(ptext);

			return byte2hex(ctext);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 对于content超过15位的时候和IOS不兼容，使用decryptAES代替
	 * @param content
	 * @param publickey
	 * @return
	 */
	@Deprecated
	public static String decrypt(String content, String publickey) {
		Key key = getKey(publickey);
		try {
			Cipher cp = Cipher.getInstance("AES");
			cp.init(Cipher.DECRYPT_MODE, key); // 初始化 
			byte[] ptext = cp.doFinal(hex2byte(content)); // 解密 
			return new String(ptext); // 重新显示明文 
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			//兼容IOS
			try {
				byte[] enCodeFormat = hex2byte(publickey);
				SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
				
				byte[] initParam = hex2byte("00000000000000000000000000000000");
				IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
				
				// 指定加密的算法、工作模式和填充方式
				Cipher cipher;
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
				byte[] ptext = cipher.doFinal(hex2byte(content)); // 解密 
				return new String(ptext); // 重新显示明文 
			} catch (Exception e2) {
				logger.error(e2.getMessage(), e2);
			}
		}
		return null;
	}

	public static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 != 0) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}

	private static String byte2hex(byte b[]) {
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < b.length; n++) {
			String stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
				sb.append("0");
			}
			sb.append(stmp);
		}
		return sb.toString().toUpperCase();
	}

	private static void test() {
		String password = "29D9625FABD93D46561871240D6624C4";//5A8412AC0A89BCB80E58D1A0913E3076
		System.out.println("密钥:" + password);

		String content = "12456987";
		content = AESUtil.encrypt(content, password);
		//content = "9B596E6A1073CF3DF784743D051523F456D4CBA1A60141957B066C7581EE6D4F2F4D8747873F91071FD0213E001C2113";
		System.out.println("密文:" + content);

		content = AESUtil.decrypt(content, password);
		System.out.println("明文:" + content);

	}

	public static void main(String[] args) throws Exception {
		//run com.duowan.udb.util.AESUtil
		//		AESUtil.makeKey();
//		AESUtil.test();
		
		System.out.println(decrypt("08CBFFBDDF9CAF187CA565F64FC92C7408DF4B039F0B82D97FDF6FD7AD30DF57", "15B6272FA0F3D48E192C240296A6F8D9"));
		
		System.out.println(encrypt("6227003324560252367", "15B6272FA0F3D48E192C240296A6F8D9"));
		System.out.println(encryptAES("6227003324560252367", "15B6272FA0F3D48E192C240296A6F8D9"));
		
		System.out.println(encrypt("13148931916", "15B6272FA0F3D48E192C240296A6F8D9"));
		System.out.println(encryptAES("13148931916", "15B6272FA0F3D48E192C240296A6F8D9"));
	}

	public static String encryptAES(String content, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 注意，为了能与 iOS 统一
		// 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
		byte[] enCodeFormat = hex2byte(key);
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
		
		byte[] initParam = hex2byte("00000000000000000000000000000000");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		
		// 指定加密的算法、工作模式和填充方式
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encryptedBytes = cipher.doFinal(content.getBytes("UTF-8"));
		return byte2hex(encryptedBytes);
	}

	public static String decryptAES(String content, String key) {
		try {
			// 注意，为了能与 iOS 统一
			// 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
			byte[] enCodeFormat = hex2byte(key);
			SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
			
			byte[] initParam = hex2byte("00000000000000000000000000000000");
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			
			// 指定加密的算法、工作模式和填充方式
			Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] ptext = cipher.doFinal(hex2byte(content)); // 解密 
			return new String(ptext); // 重新显示明文 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//兼容旧的加密方式
			try {
				return decrypt(content, key);
			} catch (Exception e2) {
				logger.error(e2.getMessage(), e2);
			}
		}
		return null;
	}
}
