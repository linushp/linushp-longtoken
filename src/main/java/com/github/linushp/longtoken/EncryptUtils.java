package com.github.linushp.longtoken;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class EncryptUtils {


    static byte[] exchangeSecret(byte[] bytesObj, byte[] secret) throws NoSuchAlgorithmException {
        int bytesObjLength = bytesObj.length;
        byte[] secretHash = toHashByteValue(secret);
        int secretHashLength = secretHash.length;
        byte[] result = new byte[bytesObjLength];
        System.arraycopy(bytesObj, 0, result, 0, bytesObjLength);
        for (int i = 0; i < bytesObjLength; i++) {
            int secretByte = Math.abs(secretHash[i % secretHashLength]) % bytesObjLength;
            swapByteAB(result, i, secretByte);
        }
        return result;
    }


    static byte[] exchangeSecret2(byte[] bytesObj, byte[] secret) throws NoSuchAlgorithmException {
        int bytesObjLength = bytesObj.length;
        byte[] secretHash = toHashByteValue(secret);
        int secretHashLength = secretHash.length;
        byte[] result = new byte[bytesObjLength];
        System.arraycopy(bytesObj, 0, result, 0, bytesObjLength);
        for (int i = bytesObjLength - 1; i >= 0; i--) {
            int secretByte = Math.abs(secretHash[i % secretHashLength]) % bytesObjLength;
            swapByteAB(result, i, secretByte);
        }
        return result;
    }


    private static void swapByteAB(byte[] bytes, int i, int j) {
        byte c = bytes[i];
        bytes[i] = bytes[j];
        bytes[j] = c;
    }

    private static byte[] toHashByteValue(byte[] secretByteArray) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(secretByteArray);
        return messageDigest.digest();
    }
}
