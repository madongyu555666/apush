package com.apush.tools.common;

/**
 * string,StringBuilder,StringBuffer
 * @author madongyu-ds
 *
 */
public final class Strings {
    public static final String EMPTY = "";
    
    /**
     * 判断是否为空
     * String 继承于CharSequence，也就是说String也是CharSequence类型。
CharSequence是一个接口，它只包括length(), charAt(int index), subSequence(int start, int end)这几个API接口。除了String实现了CharSequence之外，StringBuffer和StringBuilder也实现了CharSequence接口。
需要说明的是，CharSequence就是字符序列，String, StringBuilder和StringBuffer本质上都是通过字符数组实现的！
     * @param text
     * @return
     */
    public static boolean isBlank(CharSequence text) {
        if (text == null || text.length() == 0) return true;
        for (int i = 0, L = text.length(); i < L; i++) {
            if (!Character.isWhitespace(text.charAt(i))) return false;
        }
        return true;
    }
    /**
     * 去除空格
     * @param s
     * @return
     */
    public static String trimAll(CharSequence s) {
        if (s == null || s.length() == 0) return Strings.EMPTY;
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0, L = s.length(); i < L; i++) {
            char c = s.charAt(i);
            if (c != ' ') sb.append(c);
        }
        return sb.toString();
    }
}
