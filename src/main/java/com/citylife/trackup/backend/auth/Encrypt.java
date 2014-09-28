package com.citylife.trackup.backend.auth;

import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * 
 */
public class Encrypt {

    public static String encrypt(String msg) {
        String result = "";
        try {
            // 128 bit
            byte[] ivBytes = new byte[16];
            byte[] keyBytes = "citylife20130609trackup".getBytes("UTF-8");
            System.arraycopy(keyBytes, 0, ivBytes, 0, Math.min(keyBytes.length, 16));
            SecretKey secret = new SecretKeySpec(ivBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, secret);

            result = new String(Base64.encode(cipher.doFinal(msg.getBytes())));
        } catch (Exception e) {
            throw new RestAuthenticationException("", e.getCause());
        }

        return result;
    }

    public static String decrypt(String info) {

        String plaintext ="";

        try {
            byte[] ivBytes = new byte[16];
            byte[] keyBytes = "citylife20130609trackup".getBytes("UTF-8");
            System.arraycopy(keyBytes, 0, ivBytes, 0, Math.min(keyBytes.length, 16));

            SecretKey secret = new SecretKeySpec(ivBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret);

            plaintext = new String(cipher.doFinal(Base64.decode(info.getBytes())), "UTF-8");
        } catch (Exception e) {
            throw new RestAuthenticationException("Authorization failed.", e.getCause());
        }
        return plaintext;
    }

    public static void main(String[] args) {
          System.out.println(encrypt("e4d465ee-f6bd-4495-bac1-f5e0f24734f9"));
        System.out.println(decrypt("DJ4QqFoue9RsBOCoWZSYvtP59d/Uc6LgrU3++woOnxhihK7AeK6ou4oJnzzQgx6u"));
    }
}
