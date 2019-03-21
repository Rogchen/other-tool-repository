package com.rogchen.passworddemo;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/21 16:11
 **/
@Slf4j
public class AesUtils {

    static String iv = "d22b0a851e014f7b";


    private final static Base64.Encoder encoder = Base64.getEncoder();
    private final static Base64.Decoder decoder = Base64.getDecoder();

    private static final String defaultCharset = "UTF-8";
    private static final String KEY_AES = "AES";
    public static final String KEY = "8192553d3db81630";

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        return doAES(data, key, iv.getBytes(), Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        return doAES(data, key, iv.getBytes(), Cipher.DECRYPT_MODE);
    }

    public static String doAES(String data, String secretKey, byte[] iv, int mode) {

        try {
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = decoder.decode(data);
            }

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), KEY_AES);
            cipher.init(mode, skeySpec, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(encoder.encode(result));
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
            log.error("aes error:", e);
        }
        return null;
    }

    /**
     * 直接加密程base64
     *
     * @param data
     * @param key
     * @return
     */
    public static String encodeToBase64(String data, String key) {
        try {
            String token = new String(encoder.encode(encrypt(data, key).getBytes("utf-8"))).trim();
            return token;
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * base64直接解密操作
     *
     * @param data
     * @param key
     * @return
     */
    public static String decryptBase64ToString(String data, String key) {
        //base64专程string
        try {
            String base64 = new String(decoder.decode(data),"UTF-8");
            return decrypt(base64,key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //指定了iv是16位，所以key必须也是16位
    public static String generateKey(){
        byte[] bt = new byte[iv.getBytes().length];
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int size = base.length();
        StringBuffer randomCode = new StringBuffer(16);
        // 随机产生4位数字的验证码。
        Random random = new Random();
        for (int i = 0; i < bt.length; i++) {
            // 得到随机产生的验证码数字。
            int start = random.nextInt(size);
            String strRand = base.substring(start, start + 1);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        bt = randomCode.toString().getBytes();
        try {
            return new String(bt,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Mtp5aI7HW0Al7P1x";
    }


    public static void main(String[] args) {
        String key = generateKey();
        System.out.println(key);
        String param = encrypt("{\"aaa\":\"234\"}",key);
        System.out.println(param);
        String value = decrypt(param,key);
        System.out.println(value);
        System.out.println(iv.getBytes().length);
    }
}
