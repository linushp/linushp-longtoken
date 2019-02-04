# linushp-longtoken

## 类似JwtToken的Java工具

跟JWT相比对优势:
1. 对生成的Token(包括签名，数据，时间戳) 使用Base58编码,编码后长度固定66个字符，便于数据库存储的需求。
2. 不仅对数据进行了签名，保证数据不可篡改，而且对数据进行了简单对加密，在不知道密码对情况下无法知道原文是什么。

跟JWT相比对劣势:
1. 只能对Long型数据进行反解。其他类型数据无法解析回原文，只能校验是否真实。


### 对Long进行签名并Base58序列化成Token，以及反序列化

```

    public static void main(String[] args) throws Exception {
        byte[] secret = "hello".getBytes();
        String token = LongToken.toLongToken(142453452300L, secret);
        System.out.println(token);
        long value = LongToken.parseLongToken(token, secret, 2);
        System.out.println(value);
    }

```


### 对String进行签名并Base58成Token，以及校验Token合法性

```

    public static void main(String[] args) throws Exception {
        byte[] secret = "dafnsk".getBytes();
        String seed = "23233223";
        String sss = toSeedToken(seed, secret);
        System.out.println(sss);
        boolean xxxx = validateSeedToken(sss, seed, secret, 2);
        System.out.println(xxxx);
    }

```


