/**
 * www.yiji.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package com.global.com.global.common.service.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

import com.yjf.common.lang.security.DESPlus;

/**
 * 
 * @Filename EncryptionUtil.java
 * 
 * @Description 摘要密文工具
 * 
 * @Version 1.0
 * 
 * @Author karott
 * 
 * @Email chenlin@yiji.com
 * 
 * @History <li>Author: karott</li> <li>Date: 2012-7-13</li> <li>Version: 1.0</li>
 * <li>Content: create</li>
 * 
 */
public class EncryptionUtil {
	
	public enum DigestALGEnum {
		SHA,
		MD5;
	}
	
	public static byte[] digestData(String data, DigestALGEnum alg) {
		
		byte[] cData = new byte[] {};
		try {
			MessageDigest digest = MessageDigest.getInstance(alg.toString());
			
			String firstLetter = data.substring(0, 1);
			String lastLetter = data.substring(data.length() - 1);
			String tmpData = firstLetter + "<|" + data + "|>" + lastLetter;
			
			tmpData = data + tmpData + data;
			digest.update(tmpData.getBytes("UTF8"));
			
			cData = digest.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cData;
	}
	
	public static String digestDataStr(String data, DigestALGEnum alg) {
		return new String(digestData(data, alg));
	}
	
	public static char[] encodeWithHex(byte[] data) {
		return Hex.encodeHex(data);
	}
	
	public static String encodeWithHexStr(String data, DigestALGEnum alg) {
		return new String(encodeWithHex(digestData(data, alg)));
	}
	
	public static String encodeWithHexStrNoDigest(String data) {
		byte[] pData = new byte[] {};
		try {
			pData = data.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Hex.encodeHex(pData));
	}
	
	public static String decodeWithHexStrNoDigest(String data) {
		char[] cData = null;
		byte[] pData = null;
		try {
			cData = data.toCharArray();
			pData = Hex.decodeHex(cData);
			//非空校验
			if (pData == null) {
				throw new Exception();
			} else {
				return new String(pData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//		return new String(pData);
		return "";
	}
	
	public static void main(String[] args) {
		//- for tmp
		System.out.println(encodeWithHexStrNoDigest("root86815300"));
		System.err.println(decodeWithHexStrNoDigest(encodeWithHexStrNoDigest("root86815300")));
		System.out.println(encodeWithHexStr("a111111", DigestALGEnum.MD5));
		System.out.println(encodeWithHexStr("a111111", DigestALGEnum.SHA));
	}
	
	/**
	 * des加密
	 * @param key 密钥
	 * @param strIn 加密原文
	 * @return
	 */
	public static String desEncrypt(String key, String str) {
		try {
			return DESPlus.safeEncrypt(key, str);
		} catch (Exception ex) {
			//			logger.error("desEncrypt err key=" + key + " str=" + str, ex);
			ex.printStackTrace();
		}
		return "";
	}
	
	/**
	 * DES解密
	 * 
	 * @param key
	 * @param str
	 * @return
	 */
	public static String desDecrypt(String key, String str) {
		try {
			return DESPlus.safeDecrypt(key, str);
		} catch (Exception ex) {
			//			logger.error("desDecrypt err key=" + key + " str=" + str, ex);
			ex.printStackTrace();
		}
		return "";
	}
}
