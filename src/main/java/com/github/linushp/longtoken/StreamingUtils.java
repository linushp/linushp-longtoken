package com.github.linushp.longtoken;


public class StreamingUtils {


    public static void writeInt(int v, byte[] buffer, int offset) {
        buffer[offset] = (byte) (v & 0xFF);
        buffer[offset + 1] = (byte) ((v >> 8) & 0xFF);
        buffer[offset + 2] = (byte) ((v >> 16) & 0xFF);
        buffer[offset + 3] = (byte) ((v >> 24) & 0xFF);
    }


    public static void writeLong(long v, byte[] buffer, int offset) {
        buffer[offset] = (byte) (v & 0xFF);
        buffer[offset + 1] = (byte) ((v >> 8) & 0xFF);
        buffer[offset + 2] = (byte) ((v >> 16) & 0xFF);
        buffer[offset + 3] = (byte) ((v >> 24) & 0xFF);

        buffer[offset + 4] = (byte) ((v >> 32) & 0xFF);
        buffer[offset + 5] = (byte) ((v >> 40) & 0xFF);
        buffer[offset + 6] = (byte) ((v >> 48) & 0xFF);
        buffer[offset + 7] = (byte) ((v >> 56) & 0xFF);
    }


    public static int readInt(byte[] src, int offset) {
        int a = src[offset + 0] & 0xFF;
        int b = src[offset + 1] & 0xFF;
        int c = src[offset + 2] & 0xFF;
        int d = src[offset + 3] & 0xFF;

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    public static long readUInt(byte[] src, int offset) {
        long a = src[offset + 0] & 0xFF;
        long b = src[offset + 1] & 0xFF;
        long c = src[offset + 2] & 0xFF;
        long d = src[offset + 3] & 0xFF;

        return a + (b << 8) + (c << 16) + (d << 24);
    }

    public static long readLong(byte[] src, int offset) {
        long a = readUInt(src, offset);
        long b = readUInt(src, offset + 4);
        return (a & 0xFFFFFFFF) + ((b & 0xFFFFFFFF) << 32);
    }

}
