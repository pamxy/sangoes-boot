# 分布式限流

### 用法

1. 在启动类上加入@EnableLimiter
2. 在需要限速的controller上加入@RateLimiter(prefix = "test", period = 100, count = 10)

    
    @RateLimiter(prefix = "test")
    public String test1(@RedisParam(name = "a") String a, @RedisParam String b) {
        return a + b;
    }

## TODO
 需要完善
 