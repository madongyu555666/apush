package com.apush.api.connection;

/*
 * 加密
 */
public interface Cipher {
	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 */
	byte[] decrypt(byte[] data);

	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 */
	byte[] encrypt(byte[] data);
}
