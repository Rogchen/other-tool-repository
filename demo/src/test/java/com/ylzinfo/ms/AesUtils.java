import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Description: aes工具类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/1/4 10:08
 **/
public class AesUtils {

    private final static String defaultCharset = "UTF-8";
    private static final String KEY_AES = "AES";
    private static final String IV = "98iu7h90nbfe46ha";
    private final static Base64.Encoder encoder = Base64.getEncoder();
    private final static Base64.Decoder decoder = Base64.getDecoder();

    public String encrypt(String data, String key) {
        return doAesCustom(data, key, Cipher.ENCRYPT_MODE);
    }

    public String decoder(String data, String key) {
        return doAesCustom(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 通过随机生成的密钥来加密
     *
     * @param data
     * @param key
     * @param mode
     * @return
     */
    private String doAes(String data, String key, int mode) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }
            //判断加密还是解密
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = decoder.decode(data);
            }
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            kgen.init(128, new SecureRandom(key.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.根据指定算法AES自成密码器
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Cipher cipher = Cipher.getInstance(KEY_AES);
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(encoder.encode(result));
            } else {
                return new String(result, defaultCharset);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 自定义密钥生成器:iv必须是16字节/key必须是16字节的倍数
     * * AES/CBC/NoPadding 要求
     * 密钥必须是16字节长度的；Initialization vector (IV) 必须是16字节
     * 待加密内容的字节长度必须是16的倍数，如果不是16的倍数，就会出如下异常：
     * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes
     * <p>
     * 由于固定了位数，所以对于被加密数据有中文的, 加、解密不完整
     * <p>
     * 可 以看到，在原始数据长度为16的整数n倍时，假如原始数据长度等于16*n，则使用NoPadding时加密后数据长度等于16*n，
     * 其它情况下加密数据长 度等于16*(n+1)。在不足16的整数倍的情况下，假如原始数据长度等于16*n+m[其中m小于16]，
     * 除了NoPadding填充之外的任何方 式，加密数据长度都等于16*(n+1).
     *
     * @param data
     * @param key
     * @return
     */
    private String doAesCustom(String data, String key, int mode) {
        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }
            //判断加密还是解密
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = decoder.decode(data);
            }
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), KEY_AES);
            cipher.init(mode, skeySpec, new IvParameterSpec(IV.getBytes()));
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(encoder.encode(result));
            } else {
                return new String(result, defaultCharset);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        AesUtils aesTest = new AesUtils();
        String e = aesTest.encrypt("213123", "82e32374rta273f8");
        System.out.println(e);
        String b = aesTest.decoder(e, "82e32374rta273f8");
        System.out.println(b);
    }

    /**
     * 获取16位随机字符
     */
    @Test
    public void ivTest() {
        byte[] b = new byte[16];
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int size = base.length();
        // 随机产生4位数字的验证码。
        for (int i = 0; i < b.length; i++) {
            // 得到随机产生的验证码数字。
            int start = RandomUtils.nextInt(0,size);
            String strRand = base.substring(start, start + 1);
            b[i] = strRand.getBytes()[0];
        }
        System.out.println(new String(b));
    }
}
