package com.github.linushp.longtoken;

public class TokenValue {
    private long longValue;
    private int signSecond;
    private int incNum;
    private int errCode = 0;
    private String errMsg = null;

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

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "TokenValue{" +
                "longValue=" + longValue +
                ", signSecond=" + signSecond +
                ", incNum=" + incNum +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
