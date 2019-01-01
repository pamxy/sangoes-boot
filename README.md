# sangoes-boot

   sangoes-boot开源,由springboot2.x+mybatis-plus+rabbitmq+redis+oauth2.0+react+antd等完成的单体架构，
   旨在解决Java单体工程的快速搭建和部署，sangoes-cloud(分布式架构)正在开发中....

## 使用说明

## 相关技术

   后端:
   * springboot2.x spring生态
   * mybatis-Plus
   * hutools
   * oauth2.x jwtt
   * redis
   * rabbitmq
   * HikariCP连接池
   * elk
   * oss和fastdfs
   * docker docker-compose
   
   前端:
   * react.js
   * umijs
   * antd

## 相关
    
   前端:
   
   * sangoes-boot:https://github.com/sangoes/sangoes-boot
   
   后端:
    
   * sangoes-web:https://github.com/sangoes/sangoes-web

## 模块解释
    sangoes-boot --父工程
         -dockerfile --docker配置
         -docs --文档
         -sangoes-common --工具集合
         -sangoes-generator --自动生产
         -sangeoes-uc --主程序
            -security --安全类
            -config --配置类
            -modules --模块
                -admin --基础服务
                -ms --消息服务
         -sql --sangoes-boot sql
         -docker.sh --docker 部署脚本

## 功能列表

   * 登录:账号登录(密码登录) 短信登录(暂时没接入短信方)
   * 用户管理:用户添加 用户删除 用户修改 绑定角色 修改密码 批量删除用户 绑定部门
   * 角色管理:添加角色 删除角色 修改角色 绑定菜单权限 批量删除角色
   * 菜单管理:添加菜单 修改菜单 删除菜单 添加权限 修改权限 删除权限 批量删除权限
   * 上传文件:OSS(阿里云)
   * 工具:cache正则删除 
   * 文档管理
   * 授权管理:添加授权 删除授权 批量删除授权
   * 部门管理:添加部门 编辑部门 删除部门
   * 字典管理:添加字典 添加子字典 删除 编辑
    
## 待做

   * <del>部门管理</del> 
   * <del>字典管理</del>
   * 队列
   * ELK
   * 个人中心 个人设置
   * CacheCloud
   * 限流
   * 白名单 黑名单
   * 使用文档
   * 消息中心
   * 一二级缓存
   * 监控
   * 数据库读写分离
   * 文件管理
   * 定时(计划)管理
   * api数据加密解密

## 开发
   1.环境
   * java1.8+idea/vscode
   * 若出现getter/setter错误,请安装 IntelliJ Lombok plugin
   
   2.导入sql
   * 创建sql create database boot
   * 导入sql sangoes-boot/sql/boot.sql
   
   3.修改配置
   * sangoes-boot/sangoes-uc/src/resources/application.yml
   
   4.运行
   * sangoes-boot/sangoes-uc/src/UCApplication
 
## 部署
   * 安装docker docker-compose
   * 导入sql
   * 运行sangoes-boot/docker.sh
   
## 注意
   * 使用redis cache约定
    
        * @Cacheable(value=,key=) value的值尽可能不与key的重复
        * @Caching() 多用
        * @CacheRegexRemove(key=) key为表达式
        * @CacheRegexRemove(value=) 尽量少用 如果value表达式的值和key的表达式的值重复将会一起删除 *尽量少用*
    

## 问题

   * VSCode 中运行 sangoes-generator 报错:Evaluation failed because the thread is not suspended
       
         出现此报错的原因是：vscode的内置调试控制台不支持java输入。所以需要在debug配置文件里修改调试控制台，只需将console属性修改为internalTerminal或者externalTerminal即可。即内置或外置终端

## 感谢

   [mybatis-plus](https://github.com/baomidou/mybatis-plus)
   
   [hutools](https://github.com/looly/hutool)
   
   [umijs](https://github.com/umijs/umi)
    
   [antd](https://github.com/ant-design/ant-design)
    