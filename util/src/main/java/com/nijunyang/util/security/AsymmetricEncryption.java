package com.nijunyang.util.security;

import javafx.util.Pair;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Description:
 * Created by nijunyang on 2020/5/7 10:24
 */
public class AsymmetricEncryption {


    public static void main(String[] args) throws GeneralSecurityException, IOException {
        Pair<String, String> keyPair = createKeyPair();
        String cipherText = encrypt("123456".getBytes("utf-8"), keyPair.getKey());
        System.out.println(cipherText);
        String plainText = decrypt(cipherText.getBytes("utf-8"), keyPair.getValue());
        System.out.println(plainText);

    }


    /**
     * 生成秘钥对
     * @return
     * @throws GeneralSecurityException
     */
    public static Pair<String, String> createKeyPair() throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Pair<String, String> pair = new Pair<>(
                new BASE64Encoder().encodeBuffer(keyPair.getPublic().getEncoded()),
                new BASE64Encoder().encodeBuffer(keyPair.getPrivate().getEncoded()));
        return pair;
    }

    /**
     * 公钥加密
     * @param message
     * @param publicKeyStr
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String encrypt(byte[] message, String publicKeyStr) throws GeneralSecurityException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytes = new BASE64Decoder().decodeBuffer(publicKeyStr);
        KeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return new String(cipher.doFinal(message), "utf-8");
    }

    /**
     * 私钥解密
     * @param input
     * @param privateKeyStr
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String decrypt(byte[] input, String privateKeyStr) throws GeneralSecurityException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytes = new BASE64Decoder().decodeBuffer(privateKeyStr);
        KeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(input), "utf-8");
    }

}
