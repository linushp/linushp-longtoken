# linushp-longtoken

## 类似JwtToken的Java工具

```java 

    public static void main(String[] args) throws Exception {
        byte[] secret = "hello".getBytes();
        String token = LongToken.toLongToken(142453452300L, secret);
        System.out.println(token);
        long value = LongToken.parseLongToken(token, secret, 2);
        System.out.println(value);
    }

```