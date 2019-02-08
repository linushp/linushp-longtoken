package com.github.linushp.longtoken;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SeedValidToken {
    public static String toSeedToken(String seed, byte[] secret) throws NoSuchAlgorithmException {
        long longSeed = hashSeedToLong(seed);
        return LongToken.toLongToken(longSeed, secret);
    }

    public static boolean validateSeedToken(String token, String seed, byte[] secret, int active_second) throws Exception {
        ParsedValue parsedValue;
        try {
            parsedValue = LongToken.parseLongToken(token, secret, active_second);
        } catch (LongTokenException e) {
            return false;
        }
        long longSeed2 = hashSeedToLong(seed);
        return parsedValue.getLongValue() == longSeed2;
    }


//    public static void main(String[] args) throws Exception {
//        byte[] secret = "dafnsk".getBytes();
//        String seed = "23233223";
//        String sss = toSeedToken(seed, secret);
//        System.out.println(sss);
//        boolean xxxx = validateSeedToken(sss, seed, secret, 2);
//        System.out.println(xxxx);
//    }

    private static long hashSeedToLong(String seed) throws NoSuchAlgorithmException {
        if (seed == null) {
            seed = "";
        }
        byte[] xxx = seed.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); //32
        messageDigest.update(xxx);
        byte[] hashBytes = messageDigest.digest();
        byte[] longBytes = new byte[8];
        System.arraycopy(hashBytes, 0, longBytes, 0, 8);
        longBytes[4] = hashBytes[16];
        longBytes[6] = hashBytes[30];
        BigInteger bigInteger = new BigInteger(longBytes);
        return bigInteger.longValue();
    }
}
