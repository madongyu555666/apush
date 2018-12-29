
package com.apush.tools.crypto;


import com.apush.api.Constants;

import java.util.Base64;

/**
 * base64加密
 * @author madongyu-ds
 *
 */
public class Base64Utils {

    /**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     *
     * @param base64 base64
     * @return 源二进制数据
     */
    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64.getBytes(Constants.UTF_8));
    }

    /**
     * <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     *
     * @param bytes base64
     * @return BASE64后的二进制数据
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes), Constants.UTF_8);
    }

}