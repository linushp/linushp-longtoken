package com.github.linushp.longtoken;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

public class LongToken {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static String toLongToken(long longValue, byte[] secret) throws NoSuchAlgorithmException {
        int signSecond = (int) (System.currentTimeMillis() / 1000);
        int incNum = atomicInteger.incrementAndGet();
        TokenValue tokenValue = new TokenValue(longValue, signSecond, incNum);
        return toLongToken(tokenValue, secret);
    }


    public static String toLongToken(TokenValue tokenValue, byte[] secret) throws NoSuchAlgorithmException {
        byte[] byteArrayData = new byte[16];
        StreamingUtils.writeLong(tokenValue.getLongValue(), byteArrayData, 0);
        StreamingUtils.writeInt(tokenValue.getSignSecond(), byteArrayData, 8);
        StreamingUtils.writeInt(tokenValue.getIncNum(), byteArrayData, 12);

        byte[] hashSign = toHashByteValue(byteArrayData, secret); // 256 位

        byte[] byteArrayAll = mergeByteArray(hashSign, byteArrayData);//256 + 128
        byteArrayAll = EncryptUtils.exchangeSecret(byteArrayAll, secret);
        return Base58.encode(byteArrayAll);
    }


    public static TokenValue parseLongToken(String tokenString, byte[] secret, int active_second) throws NoSuchAlgorithmException {
        byte[] byteArrayAll = Base58.decode(tokenString);
        byteArrayAll = EncryptUtils.exchangeSecret2(byteArrayAll, secret);

        byte[] hashSign = new byte[32];
        byte[] byteArrayData = new byte[16];

        System.arraycopy(byteArrayAll, 0, hashSign, 0, 32);
        System.arraycopy(byteArrayAll, 32, byteArrayData, 0, 16);

        long longValue = StreamingUtils.readLong(byteArrayData, 0);
        int signSecond = StreamingUtils.readInt(byteArrayData, 8);
        int incNum = StreamingUtils.readInt(byteArrayData, 12);

        int nowSecond = (int) (System.currentTimeMillis() / 1000);

        byte[] hashSign2 = toHashByteValue(byteArrayData, secret); // 128位

        TokenValue tokenValue = new TokenValue(longValue, signSecond, incNum);

        if (!isEqualByteArray(hashSign, hashSign2)) {
            tokenValue.setErrCode(1);
            tokenValue.setErrMsg("ValidateSignFailed");
        } else if (signSecond + active_second < nowSecond) {
            tokenValue.setErrCode(2);
            tokenValue.setErrMsg("LongTokenExpired");
        }

        return tokenValue;
    }


    public static void main(String[] args) throws Exception {

        System.out.println(System.currentTimeMillis());
        for (int i = 99990; i < 100000; i++) {
            byte[] secret = "hello".getBytes();
            String token = toLongToken(i, secret);
            System.out.println(token);
//            Thread.sleep(1000 * 2);
            TokenValue parsedValue = parseLongToken(token, "hello".getBytes(), 1);
            System.out.println(parsedValue.toString());
        }
        System.out.println(System.currentTimeMillis());

    }


    private static boolean isEqualByteArray(byte[] bytes1, byte[] bytes2) {
        if (bytes1.length != bytes2.length) {
            return false;
        }
        for (int i = 0; i < bytes1.length; i++) {
            byte b1 = bytes1[i];
            byte b2 = bytes2[i];
            if (b1 != b2) {
                return false;
            }
        }
        return true;
    }


    private static byte[] toHashByteValue(byte[] byteArray, byte[] secretByteArray) throws NoSuchAlgorithmException {
        byte[] xxx = mergeByteArray(byteArray, secretByteArray);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(xxx);
        return messageDigest.digest();
    }


    private static byte[] mergeByteArray(byte[] bytes1, byte[] bytes2) {
        byte[] bytesResult = new byte[bytes1.length + bytes2.length];
        System.arraycopy(bytes1, 0, bytesResult, 0, bytes1.length);
        System.arraycopy(bytes2, 0, bytesResult, bytes1.length, bytes2.length);
        return bytesResult;
    }


}
