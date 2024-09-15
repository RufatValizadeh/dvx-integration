package az.dvx.dvx.util;

import az.dvx.dvx.dto.EncryptionResponseDto;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptographyUtil {
    public static EncryptionResponseDto encrypt(String voen,String aesKey) {
//        String aesKey = getRandomAesKey();
//        String str = "{\n" +
//                "  \"voen\": \"9900050571\"\n" +
//                "}";
//        String encryptedPayload = encryptTextUsingAES(str, aesKey);
//        String encryptedKey = encryptAESKey(aesKey, "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANU/opWxzKrS5RcMHLxhSthfGbOw7Wf9bym4Edm8VUXCpTLg0tCaFzmHCoym/L48jQMCRDLQAt/mll4hJ15S7TkCAwEAAQ==");
        String str = String.format("{\n  \"voen\": \"%s\"\n}", voen);

        return EncryptionResponseDto.builder()
                .encryptedKey(encryptAESKey(aesKey, "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANU/opWxzKrS5RcMHLxhSthfGbOw7Wf9bym4Edm8VUXCpTLg0tCaFzmHCoym/L48jQMCRDLQAt/mll4hJ15S7TkCAwEAAQ=="))
                .encryptedPayload(encryptTextUsingAES(str, aesKey))
                .build();
    }

    public static String  decrypt(String encryptedText, String aesKeyString) {
        byte[] decodedKey = Base64.getDecoder().decode(aesKeyString);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = null;
        byte[] bytePlainText = null;
        try {
            aesCipher = Cipher.getInstance("AES");

            aesCipher.init(Cipher.DECRYPT_MODE, originalKey);
            bytePlainText = aesCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        } catch (InvalidKeyException
                 | IllegalBlockSizeException
                 | BadPaddingException
                 | NoSuchPaddingException
                 | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return new String(bytePlainText);
    }

    private static String getRandomAesKey() {
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        byte[] raw = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(raw);
    }

    private static String encryptTextUsingAES(String plainText, String aesKeyString) {
        byte[] decodedKey = Base64.getDecoder().decode(aesKeyString);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");


        Cipher aesCipher = null;
        byte[] byteCipherText = null;
        try {
            aesCipher = Cipher.getInstance("AES");

            aesCipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byteCipherText = aesCipher.doFinal(plainText.getBytes());
        } catch (InvalidKeyException
                 | IllegalBlockSizeException
                 | BadPaddingException
                 | NoSuchPaddingException
                 | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(byteCipherText);
    }

    private static String encryptAESKey(String plainAESKey, String sPublicKey) {
        PublicKey publicKey = convertStringToPublicKey(sPublicKey);
        return encryptAESKey(plainAESKey, publicKey);
    }

    private static PublicKey convertStringToPublicKey(String sPublicKey) {
        PublicKey publicKey = null;
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(sPublicKey.getBytes("utf-8"));
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(privateKeyBytes);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            publicKey = fact.generatePublic(keySpec);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return publicKey;
    }

    private static String encryptAESKey(String plainAESKey, PublicKey publicKey) {
        Cipher cipher = null;
        String encryptedKey = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedKey = Base64.getEncoder().encodeToString(cipher.doFinal(plainAESKey.getBytes()));
        } catch (InvalidKeyException
                 | IllegalBlockSizeException
                 | BadPaddingException
                 | NoSuchPaddingException
                 | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encryptedKey;
    }

}
