package com.apush.common.security;

import com.apush.tools.config.CC;
import com.apush.tools.crypto.RSAUtils;

import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 密码箱
 * @author madongyu-ds
 *
 */
public final class CipherBox {
    public final int aesKeyLength = CC.mp.security.aes_key_length;
    public static final CipherBox I = new CipherBox();
    private SecureRandom random = new SecureRandom();
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public RSAPrivateKey getPrivateKey() {
        if (privateKey == null) {
            String key = CC.mp.security.private_key;
            try {
                privateKey = (RSAPrivateKey) RSAUtils.decodePrivateKey(key);
            } catch (Exception e) {
                throw new RuntimeException("load private key ex, key=" + key, e);
            }
        }
        return privateKey;
    }

    public RSAPublicKey getPublicKey() {
        if (publicKey == null) {
            String key = CC.mp.security.public_key;
            try {
                publicKey = (RSAPublicKey) RSAUtils.decodePublicKey(key);
            } catch (Exception e) {
                throw new RuntimeException("load public key ex, key=" + key, e);
            }
        }
        return publicKey;
    }

    public byte[] randomAESKey() {
        byte[] bytes = new byte[aesKeyLength];
        random.nextBytes(bytes);
        return bytes;
    }

    public byte[] randomAESIV() {
        byte[] bytes = new byte[aesKeyLength];
        random.nextBytes(bytes);
        return bytes;
    }

    public byte[] mixKey(byte[] clientKey, byte[] serverKey) {
        byte[] sessionKey = new byte[aesKeyLength];
        for (int i = 0; i < aesKeyLength; i++) {
            byte a = clientKey[i];
            byte b = serverKey[i];
            int sum = Math.abs(a + b);
            int c = (sum % 2 == 0) ? a ^ b : b ^ a;
            sessionKey[i] = (byte) c;
        }
        return sessionKey;
    }

    public int getAesKeyLength() {
        return aesKeyLength;
    }
}
