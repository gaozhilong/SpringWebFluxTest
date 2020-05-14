# Spring Web Flux性能测试

### 使用的环境及测试工具

* [gatling 3.3.1](https://gatling.io/)
* [Spring Boot 2.2.7](https://spring.io/projects/spring-boot)

### 场景
因为使用webflux遇到了一起问题，所以决定写一个测试来看一下究竟这东西性能如何。

结论是如果接口都是快速响应并且本机的cpu核数足够多，这东西确实很快。但是如果接口响应时间有很慢的这东西就会出现莫名其妙的性能瓶颈。应该与核心调度的线程数有关。


因为应用的接口包含post，get情况，所以测试时使用了三个接口，get一个实体，get一个实体集合，post一个实体。
因为接口会存在不同的响应延迟，所以在服务类中模拟了接口响应不同延迟的情况（FooService.java），当然我测试情况有些极端，接口延迟在0-5秒。

servletapp使用的是tomcat的非阻塞io提供服务，修改了tomcat配置的最大连接数为500
webfluxapp使用的是spring boot的默认webflux web服务。

测试脚本为SpringAppTest.scala。测试报告因为机器不同会有所不同，可以自行尝试修改并发用户的数量。

