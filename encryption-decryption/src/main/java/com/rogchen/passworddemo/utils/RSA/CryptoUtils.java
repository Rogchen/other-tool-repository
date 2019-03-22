package com.rogchen.passworddemo.utils.RSA;


import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

public class CryptoUtils {
    //验证旧的公私玥还是依旧可以使用
    public static final byte[] rsapri = Base64.decodeBase64("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJafgrMdTF32Ix3PojqGgvxPuSyXQtfswKem8WZbY50rPhwX94w66E+iWh+PoL3YXVXbAYG2v4msyD5ctAS3L1fkzz2wCITFLpnQr7QyX+MOhd7xQhGn8zCR8bEzB7AzUfxdVMo+WfEGzaPAv6ihHICbawAtplzW0NXSt6rMEI3XAgMBAAECgYEAhOO0r4yw3NGVh+6x5crZc/xulp3ZtqHGE+RhZPpKd9c4FBc9K7apUeEefGv+2KA1MNNpj+UJNlF/1i16EhMAq8EKPRNThtyf7sogamNDOjXeYKzVae5WxvPt//kdXEFiTd96Hif0fw+9qBarJBwFpgzsvpzZSKGaBQ1Y0efBKIkCQQDe0DmraQ7nbEMI4oY4g+xX8Xesqkuqy7rk6qQ8lTlncjWpn3SqAD2vtyolswMVJzoG+1oYyNNqFJ1KXT8FYcVFAkEArQ6zoE/PQ8lPdi0Mqk3QTflXszeHxn1Xmfn1hfGnhq8L5A1BoQleYpUtIzplhUGJ0AoaRwmsCOFCDxOtj6dSawJBANC+tfjAEWWBCQCO8P6LoeX2ZI9QcFmQSwViSwIzL7eZyWqj6DHIzgIMkSEGqDGuMXAPFia4J1FwBLiByaQqKB0CQQCrzEB+ETzNKnf79VTxbDYycimh/KqJXb0tWntm8TNB/VxslMxmNYrBix44LqzB0QIiySS1COEEH7sYhIx0Y8LpAkAGOkK/z5rzcbFsc0yfsFOyAJMRDW4Ti8EtM9rxmoKPDmyRpqO2ccqGVIjzRbOoTG5oOxM2c2pRS850f/Kc/7Z6");
    public static final byte[] rsapuk = Base64.decodeBase64("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWn4KzHUxd9iMdz6I6hoL8T7ksl0LX7MCnpvFmW2OdKz4cF/eMOuhPolofj6C92F1V2wGBtr+JrMg+XLQEty9X5M89sAiExS6Z0K+0Ml/jDoXe8UIRp/MwkfGxMwewM1H8XVTKPlnxBs2jwL+ooRyAm2sALaZc1tDV0reqzBCN1wIDAQAB");
    public final static String rsa_key = "RSA_KEY";
    private static RSAPublicKey rsaPublicKey;
    private static RSAPrivateKey rsaPrivateKey;

    public final static Map<String, String> gegerateKey() {
        try {
            Map<String, String> param = new HashMap<>();
            KeyPair keyPair = RSACrypt.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            param.put("puk", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
            param.put("pri", Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
            return param;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static Map<String, String> gegerateKey(String password) {
        try {
            Map<String, String> param = new HashMap<>();
            KeyPair keyPair = RSACrypt.generateKeyPair(password);
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            param.put("puk", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
            param.put("pri", Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
            return param;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密转base64输出
     *
     * @param str
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String encryptStrByPuk(String str) throws Exception {
        byte[] encrypted = RSACrypt.encryptByPublicKey(str.getBytes(), rsaPublicKey.getEncoded());
        return Base64.encodeBase64String(encrypted);
    }
    /**
     * 公钥加密转base64输出
     *
     * @param str
     * @param puk 公钥
     * @return
     * @throws Exception
     */
    public static String encryptStrByPuk(String str,String puk) throws Exception {
        byte[] encrypted = RSACrypt.encryptByPublicKey(str.getBytes(), Base64.decodeBase64(puk));
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * 私钥解密转string
     *
     * @param str
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String descryptStrByPri(String str) throws Exception {
        byte[] encrypted = RSACrypt.decryptByPrivateKey(Base64.decodeBase64(str), rsaPrivateKey.getEncoded());
        return StringUtils.newStringUtf8(encrypted);
    }
    /**
     * 私钥解密转string
     *
     * @param str
     * @param pri 私钥
     * @return
     * @throws Exception
     */
    public static String descryptStrByPri(String str,String pri) throws Exception {
        byte[] encrypted = RSACrypt.decryptByPrivateKey(Base64.decodeBase64(str), Base64.decodeBase64(pri));
        return StringUtils.newStringUtf8(encrypted);
    }
    /**
     * 私钥加密转base64
     *
     * @param str
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String encryptStrByPri(String str) throws Exception {
        byte[] encrypted = RSACrypt.encryptByPrivateKey(str.getBytes(), rsaPrivateKey.getEncoded());
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * 私钥加密转base64
     *
     * @param str
     * @param pri
     * @return
     * @throws Exception
     */
    public static String encryptStrByPri(String str,String pri) throws Exception {
        byte[] encrypted = RSACrypt.encryptByPrivateKey(str.getBytes(), Base64.decodeBase64(pri));
        return Base64.encodeBase64String(encrypted);
    }

    /**
     * 公钥解密转String
     *
     * @param str
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String decryptStrByPuk(String str) throws Exception {
        byte[] encrypted = RSACrypt.decryptByPublicKey(Base64.decodeBase64(str), rsaPublicKey.getEncoded());
        return StringUtils.newStringUtf8(encrypted);
    }

    /**
     * 公钥解密转String
     *
     * @param str
     * @param puk 公钥
     * @return
     * @throws Exception
     */
    public static String decryptStrByPuk(String str,String puk) throws Exception {
        byte[] encrypted = RSACrypt.decryptByPublicKey(Base64.decodeBase64(str),  Base64.decodeBase64(puk));
        return StringUtils.newStringUtf8(encrypted);
    }

    public static void main(String[] args) throws Exception {
        try {
            KeyPair keyPair = RSACrypt.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public Key : " + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            System.out.println("Private Key : " + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //公钥加密-私钥解密
        String pk = encryptStrByPuk("abc123456");
        System.out.println("公钥加密后转->base64:" + pk);
        System.out.println(descryptStrByPri(pk));
        //私钥加密-公钥解密
        String prk = encryptStrByPri("abc123456");
        System.out.println("私钥钥加密后转->base64:" + prk);
        System.out.println(decryptStrByPuk(prk));
    }
}
