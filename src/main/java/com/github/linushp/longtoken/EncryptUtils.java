package com.github.linushp.longtoken;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class EncryptUtils {

    private static final int START_COUNT = 8;

    static byte[] exchangeSecret(byte[] bytesObj, byte[] secret) throws NoSuchAlgorithmException {
        int bytesObjLength = bytesObj.length;
        byte[] secretHash = toHashByteValue(secret);
        int secretHashLength = secretHash.length;
        byte[] result = new byte[bytesObjLength];
        System.arraycopy(bytesObj, 0, result, 0, bytesObjLength);
        for (int i = START_COUNT; i < bytesObjLength; i++) {
            int secretByte = Math.abs(secretHash[i % secretHashLength]) % (bytesObjLength - START_COUNT);
            swapByteAB(result, i, secretByte + START_COUNT);
        }
        return result;
    }


    static byte[] exchangeSecret2(byte[] bytesObj, byte[] secret) throws NoSuchAlgorithmException {
        int bytesObjLength = bytesObj.length;
        byte[] secretHash = toHashByteValue(secret);
        int secretHashLength = secretHash.length;
        byte[] result = new byte[bytesObjLength];
        System.arraycopy(bytesObj, 0, result, 0, bytesObjLength);
        for (int i = bytesObjLength - 1; i >= START_COUNT; i--) {
            int secretByte = Math.abs(secretHash[i % secretHashLength]) % (bytesObjLength - START_COUNT);
            swapByteAB(result, i, secretByte + START_COUNT);
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
