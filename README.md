# SpringBoot-SecKill-Scala


### Scala语言实现的慕课网秒杀系统【暂时完成，待优化】

        语言与主体框架支持
            SpringBoot1.5.8
            Scala2.12.3
            JDK1.8
            
        其他技术
            RabbitMQ
            Redis
            Validation JSR303
            Mybatis
            Thymeleaf
            Bootstrap
            fastjson
            commons-codec
            druid
            
            
### 部分代码预览
            
mybatis接口：        
![](https://github.com/jxnu-liguobin/SpringBoot-SecKill-Scala/blob/master/SpringBoot-SecKill-Scala/src/main/resources/images/mybatis%E6%8E%A5%E5%8F%A3.png)
拦截器接口实现：
![](https://github.com/jxnu-liguobin/SpringBoot-SecKill-Scala/blob/master/SpringBoot-SecKill-Scala/src/main/resources/images/%E6%8B%A6%E6%88%AA%E5%99%A8.png)


### 写该项目理由有以下几点：

        1、学习新的编程范式，没有好的资源，所以重构已有项目
        2、熟悉Scala基本语法
        3、熟悉慕课网秒杀系统

### 代码需要注意的地方

        1、刚刚写完，存在bug，没有进行压测，性能没比较，而且可能还有BUG，欢迎发邮件给我指出bug。
        2、基本没有使用复杂特性，会Java的学起来很容易。
        3、由于Scala注解与Java相差不小，或者说我还没有找到很好的兼容已有接口的办法，所以暂时注解部分由Java替代。
        4、本项目中不存在分号，return只保留部分歧义的地方，Java程序员要注意。
        5、本项目没有使用高级特性，如高阶函数，闭包，但是使用到了隐式转换，类型通配符，Scala泛型，包括各种类，构造，特质，伴生对象等等。
        6、本项目集合一律使用Java集合，并指定别名为：JavaHashMap,JavaList类似取名。基本类型是混用的
        7、为了配置Java的set/get，采用了与Java兼容的写法，找个需要完善也可以，但是得去掉100%的Java代码
        8、实体类仅仅实现了toString方法，需要注意，没有其他！
        9、部分代码强行移植，缺乏观赏性，待优化。
        10、需要针对函数式编程进行优化
 


### 其他需要注意的地方：

        1、前台将与慕课网完全一致，可以忽略了。
        2、后台预计纯Scala实现，不排除无法实现或不方便实现的则使用Java，【实际99% Scala，仅是注解暂时没用】
        3、非Scala爱好者忽略本项目，本人不提供也没有时间答疑，也就是说你要自己搞，除非是项目BUG
        4、该Scala版本的版权归本人所有。
        5、遵循MIT开源
        
        
 
### 如何使用

        1、使用就很暴力了，要想自动初始化数据库，就给mysql新建一个库，叫seckill
        2、把resources/sql下的schema.xml与data.xml放到resources下，启动主类即可
        3、想手动，就把那两个文件分别去mysql执行一遍吧
        4、IDEA貌似可以直接启动Scala，但是Eclipse必须以Scala Appliction启动，或者以SpringBoot方式启动。注意环境是否全部装好了
