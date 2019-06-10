  ## 环境配置
    需要安装 zookeeper
  
  
  ## 启动类加上 @EnableElasticJob
  
  ## 配置 application.yml
  elastic:
    zookeeper:
      serverLists: 127.0.0.1:2181
      namespace: 0=Beijing,1=Shanghai,2=Guangzhou
  ## 类实现接口SimpleJob 类上添加 @Schedule     
 @Schedule(cron = "0/1 * * * * ?",eventTraceRdbDataSource = "dataSource")
 public class JobService implements SimpleJob {
     /**
      * 执行作业.
      *
      * @param shardingContext 分片上下文
      */
     @Override
     public void execute(ShardingContext shardingContext) {
         log.error("ddddd:{}","dsafdsafdsafdsafes");
     }
 }