package com.microastudio.iforms.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author peng
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';

    private static final String UNKNOWN = "unknown";

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 获得当天是周几
     */
    public static String getWeekDay() {
        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    /**
     * 生成随机数字
     *
     * @param length
     * @return
     */
    public static final String randomNumber(int length) {
        char[] numbersAndLetters = null;
        java.util.Random randGen = null;
        if (length < 1) {
            return null;
        }
        // Init of pseudo random number generator.
        if (randGen == null) {
            if (randGen == null) {
                randGen = new java.util.Random();
                // Also initialize the numbersAndLetters array
                numbersAndLetters = ("0123456789").toCharArray();
            }
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
        }
        return new String(randBuffer);
    }

    public static Long getRandomUserId() {
        String first = randomNumber(1);
        first = "0".equals(first) ? "8" : first;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String date = simpleDateFormat.format(new Date());
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        String rand = first + date + rannum;
        return Long.valueOf(rand);
    }

    /**
     * 随机生成length长度数字
     *
     * @param length
     * @return
     */
    public static String generatePassWord(int length) {
        StringBuilder passWorduilder = new StringBuilder();
        Random RANDOM = new Random();
        for (int i = 0; i < length; i++) {
            passWorduilder.append(RANDOM.nextInt(9));
        }
        return passWorduilder.toString();
    }

    /**
     * MD5加密
     *
     * @param orgString
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String md5Encrypt(String orgString) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(orgString.getBytes());
            byte[] b = md.digest();
            return byte2hex(b);
        } catch (java.security.NoSuchAlgorithmException ne) {
            throw new IllegalStateException("System doesn't support your  Algorithm.");
        }
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) // 二行制转字符串
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }
}
