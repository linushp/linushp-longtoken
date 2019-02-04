package com.github.linushp.longtoken;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class EncryptUtils {

    /**
     * 通过异或实现的简单加密
     */
    static byte[] xorSecret(byte[] bytesObj, byte[] secret) throws NoSuchAlgorithmException {
        byte[] secretHash = toHashByteValue(secret);
        byte[] result = new byte[bytesObj.length];
        for (int i = 0; i < bytesObj.length; i++) {
            byte originByte = bytesObj[i];
            byte secretByte = secretHash[i % secretHash.length];
            result[i] = (byte) (originByte ^ secretByte);
        }
        return result;
    }


    private static byte[] toHashByteValue(byte[] secretByteArray) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(secretByteArray);
        return messageDigest.digest();
    }
}
