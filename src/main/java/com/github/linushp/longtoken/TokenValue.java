package com.github.linushp.longtoken;

public class TokenValue {
    private long longValue;
    private int signSecond;
    private int incNum;

    public TokenValue(long longValue, int signSecond, int incNum) {
        this.longValue = longValue;
        this.signSecond = signSecond;
        this.incNum = incNum;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public int getSignSecond() {
        return signSecond;
    }

    public void setSignSecond(int signSecond) {
        this.signSecond = signSecond;
    }

    public int getIncNum() {
        return incNum;
    }

    public void setIncNum(int incNum) {
        this.incNum = incNum;
    }

    @Override
    public String toString() {
        return "ParsedValue{" +
                "longValue=" + longValue +
                ", signSecond=" + signSecond +
                ", incNum=" + incNum +
                '}';
    }
}
