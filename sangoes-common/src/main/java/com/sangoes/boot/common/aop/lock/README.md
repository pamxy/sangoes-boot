# 分布式锁

   解决:重复提交
   
   XXXApplication
   
   @EnableLock
   
   @CacheLock()
   
   
    @GetMapping
    @CacheLock(prefix = "cacheLock", timeUnit = TimeUnit.DAYS, message = "被我拿到锁啦")
    public String test1(@CacheParam(name = "t1") String t1, @CacheParam String t2) {
        return a + b;
    }