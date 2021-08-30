package com.kolosensei.springboottooltemplate.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zhengyang
 * @description 数据加密解密工具
 */
public class AesEncryptUtils {
    /**
     * 密钥
     */
    private static final String KEY = "gangtiseGangtise";

    /**
     * 参数分别代表 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content    加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     *
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encrypt(String content) {
        String encrypt = "";
        try {
            encrypt = encrypt(content, KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt;
    }

    public static String decrypt(String encryptStr) {
        String decrypt = "";
        try {
            decrypt = decrypt(encryptStr, KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypt;
    }


    public static void main(String[] args) throws Exception {

        String content = "123456";
        System.out.println("加密前：" + content);

        String encrypt = encrypt(content, KEY);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);


        String content2 = "Qwer!234";
        System.out.println("加密前：" + content2);

        String encrypt2 = encrypt(content2, KEY);
        System.out.println("加密后：" + encrypt2);

        String decrypt2 = decrypt(encrypt2, KEY);
        System.out.println("解密后：" + decrypt2);
    }

}
