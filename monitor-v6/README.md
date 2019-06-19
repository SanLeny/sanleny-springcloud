# lemes-monitor
这是一个Hystrix 监控模块



## Dashboard

hystrix 里面提供一个Dashboard（仪表盘）的功能，它是一种监控的功能，可以利用它来进行整体服务的监控。

在pom.xml 中引用jar包

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```

spring boot 的启动类上添加注解  `@EnableHystrixDashboard`  即可

打开浏览器输入：http://server:port/hystrix  

![1560927344113](src/main/resources/static/images/dashboard.png)

按照界面说明输入需要监控的相应模块，点击 **Monitor Stream** 即可看到相关的监控视图

> 注意：需要监控的模块启动类上要加入注解  `@EnableHystrix`  或者  `@EnableCircuitBreaker`



## Turbine

HystrixDashboard 它的主要功能是可以对某一项微服务进行监控，但真实情况下，不可能只对某一个服务进行监控，更多的是对很多服务进行一个整体的监控，这个时候就需要使用到turbine来完成了。

在pom.xml 中引用jar包

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
</dependency>
```

spring boot 的启动类上添加注解 `@EnableTurbine`

配置文件修改：

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:10001/eureka/
    register-with-eureka: false
turbine:
  aggregator:
    cluster-config: default
  #配置Eureka中的serviceId列表，表明监控哪些服务
  app-config: lemes-service-user-client,lemes-service-dpk-client,lemes-service-spk-client
  #设置监控的表达式，通过此表达式表示要获取监控信息名称
  cluster-name-expression: "'default'"
```



特别注意一点，如果需要监控的项目是在外部容器中运行，如 jboos 容器中，需要在项目的配置文件中新增配置

```yaml
eureka:
  instance:
    metadata-map:
      management.port: ${management.port:${server.port}/${spring.application.name}}
```



然后在 Dashboard 的界面中，输入 http://server:port/turbine.stream    点击 **Monitor Stream** 即可看到所有模块的相关监控视图

![1560928654207](src/main/resources/static/images/turbine.png)