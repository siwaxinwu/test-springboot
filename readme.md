<parent>这个标签主要的作用就是用于版本控制。
这也就是引入的WEB模块starter的时候不用指定版本号<version>标签的原因，因为在spring-boot-starter-parent中已经指定了，
类似于一种继承的关系，父亲已经为你提供了，你只需要选择用不用就行。
引入spring-boot-starter-web就能使用Spring mvc的功能
spring-boot-starter-web这个starter中其实内部引入了Spring、springmvc、tomcat的相关依赖，当然能够直接使用Spring MVC相关的功能了。

其实Spring Boot为了能够适配每一个组件，都会提供一个starter，但是这些启动器的一些信息不能在内部写死啊，
比如数据库的用户名、密码等，肯定要由开发人员指定啊，于是就统一写在了一个Properties类中，
在Spring Boot启动的时候根据前缀名+属性名称从配置文件中读取，比如WebMvcProperties，
其中定义了一些Spring Mvc相关的配置，前缀是spring.mvc。如下：
@ConfigurationProperties(prefix = "spring.mvc")
public class WebMvcProperties {}

ComponentScan：这个注解并不陌生，Spring中的注解，包扫描的注解，这个注解的作用就是在项目启动的时候扫描启动类的同类级以及下级包中的Bean。
@SpringBootConfiguration完全就是@Configuration注解，@Configuration是Spring中的注解，表示该类是一个配置类，
因此我们可以在启动类中做一些配置类可以做的事，比如注入一个Bean。

@Import，什么功能呢？快速导入Bean到IOC容器中
@EnableAutoConfiguration这个注解的作用也就一目了然了，无非就是@Import的一种形式而已，在项目启动的时候向IOC容器中快速注入Bean而已。

YML是一种新式的格式，层级鲜明，个人比较喜欢使用的一种格式，注意如下：
字符串可以不加引号，若加双引号则输出特殊字符，若不加或加单引号则转义特殊字符
数组类型，短横线后面要有空格；对象类型，冒号后面要有空格
YAML是以空格缩进的程度来控制层级关系，但不能用tab键代替空格，大小写敏感

@ConfigurationProperties注解能够很轻松的从配置文件中取值，优点如下：
支持批量的注入属性，只需要指定一个前缀prefix
支持复杂的数据类型，比如List、Map
对属性名匹配的要求较低，比如user-name，user_name，userName，USER_NAME都可以取值
支持JAVA的JSR303数据校验

如何从自定义配置文件中取值？
Spring Boot在启动的时候会自动加载application.xxx和bootsrap.xxx，但是为了区分，有时候需要自定义一个配置文件，
那么如何从自定义的配置文件中取值呢？此时就需要配合@PropertySource这个注解使用了。
@PropertySource默认加载xxx.properties类型的配置文件，不能加载YML格式的配置文件，怎么破？？？
@PropertySource指定加载自定义的配置文件，默认只能加载properties格式，但是可以指定factory属性来加载YML格式的配置文件。
几种常见的日志级别由低到高分为：TRACE < DEBUG < INFO < WARN < ERROR < FATAL。

常见的日志框架有log4j、logback、log4j2。
log4j这个日志框架显示是耳熟能详了，在Spring开发中是经常使用，但是据说log4j官方已经不再更新了，而且在性能上比logback、log4j2差了很多。
logback是由log4j创始人设计的另外一个开源日志框架，logback相比之于log4j性能提升了10以上，初始化内存加载也更小了。
作为的Spring Boot默认的日志框架肯定是有着不小的优势。
Spring Boot默认的日志框架是logback，既然Spring Boot能够将其纳入的默认的日志系统，肯定是有一定的考量的，因此实际开发过程中还是不要更换。

上面是将所有的日志的级别都改成了DEBUG，Spring Boot还支持package级别的日志级别调整，格式为：logging.level.xxx=xxx，
Spring Boot中日志默认是输出到控制台的，但是在生产环境中显示不可行的，因此需要配置日志输出到日志文件中。
其中有两个重要配置如下：
logging.file.path：指定日志文件的路径
logging.file.name：日志的文件名，默认为spring.log
注意：官方文档说这两个属性不能同时配置，否则不生效，因此只需要配置一个即可。

logging.pattern.console：控制台的输出格式
logging.pattern.file：日志文件的输出格式

logging.pattern.console=%d{yyyy/MM/dd-HH:mm:ss} [%thread] %-5level %logger- %msg%n
logging.pattern.file=%d{yyyy/MM/dd-HH:mm} [%thread] %-5level %logger- %msg%n
%d{HH:mm:ss.SSS}——日志输出时间
%thread——输出日志的进程名字，这在Web应用以及异步任务处理中很有用
%-5level——日志级别，并且使用5个字符靠左对齐
%logger- ——日志输出者的名字
%msg——日志消息
%n——平台的换行符

logging.config=classpath:logging-config.xml
这个配置是自己定义的日志文件名字

configuration节点
这是一个根节点，其中的各个属性如下：
scan：当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod：设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，
此属性生效。默认的时间间隔为1分钟。
debug：当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
root节点
这是一个必须节点，用来指定基础的日志级别，只有一个level属性，默认值是DEBUG。该节点可以包含零个或者多个元素，
子节点是appender-ref，标记这个appender将会添加到这个logger中。
contextName节点
标识一个上下文名称，默认为default，一般用不到
property节点
标记一个上下文变量，属性有name和value，定义变量之后可以使用${}来获取。
appender节点
用来格式化日志输出节点，有两个属性name和class，class用来指定哪种输出策略，常用就是控制台输出策略和文件输出策略。
这个节点很重要，通常的日志文件需要定义三个appender，分别是控制台输出，常规日志文件输出，异常日志文件输出。
该节点有几个重要的子节点，如下：
filter：日志输出拦截器，没有特殊定制一般使用系统自带的即可，但是如果要将日志分开，比如将ERROR级别的日志输出到一个文件中，
将除了ERROR级别的日志输出到另外一个文件中，此时就要拦截ERROR级别的日志了。
encoder：和pattern节点组合用于具体输出的日志格式和编码方式。
file: 节点用来指明日志文件的输出位置，可以是绝对路径也可以是相对路径
rollingPolicy: 日志回滚策略，在这里我们用了TimeBasedRollingPolicy，基于时间的回滚策略,有以下子节点fileNamePattern，
必要节点，可以用来设置指定时间的日志归档。
maxHistory : 可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件,，例如设置为30的话，则30天之后，旧的日志就会被删除
totalSizeCap: 可选节点，用来指定日志文件的上限大小，例如设置为3GB的话，那么到了这个值，就会删除旧的日志
logger节点
可选节点，用来具体指明包的日志输出级别，它将会覆盖root的输出级别。该节点有几个重要的属性如下：
name：指定的包名
level：可选，日志的级别
addtivity：可选，默认为true，将此logger的信息向上级传递，将有root节点定义日志打印。如果设置为false，将不会上传，
此时需要定义一个appender-ref节点才会输出。
SLF4j只是一个门面，共有两大特性。一是静态绑定、二是桥接。
Spring Boot其实默认内嵌了Tomcat，当然默认的端口号也是8080，如果需要修改的话，只需要在配置文件中添加如下一行配置即可:
server.port=9090
如何自定义项目路径？
server.servlet.context-path=/springboot01
以上的端口和项目路径改了之后，只需要访问http://localhost:9090/springboot01/user/1即可。

在前后端分离的项目中大部分的接口基本都是返回JSON字符串，因此对返回的JSON也是需要定制一下，比如日期的格式，NULL值是否返回等等内容。
可以在配置文件application.properties中设置指定的格式，这属于全局配置，如下：
spring.jackson.date-format= yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone= GMT+8

也可以在实体属性中标注@JsonFormat这个注解，属于局部配置，会覆盖全局配置，如下：
@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
private Date birthday;
Jackson还有很多的属性可以配置，这里就不再一一介绍了，所有的配置前缀都是spring.jackson。

Spring MVC中的拦截器（Interceptor）类似于Servlet中的过滤器（Filter），它主要用于拦截用户请求并作相应的处理。
例如通过拦截器可以进行权限验证、记录请求信息的日志、判断用户是否登录等。
其实想要在Spring Boot生效其实很简单，只需要定义一个配置类，实现WebMvcConfigurer这个接口，
并且实现其中的addInterceptors()方法即可，
无需添加此注解@Component，在启动类添加@ServletComponentScan注解后，会自动将带有@WebFilter的注解进行注入！
过滤器内容相对简单些，但是在实际开发中不可或缺，比如常用的权限控制框架Shiro，Spring Security，内部都是使用过滤器，
了解一下对以后的深入学习有着固本的作用。
WebMvcConfigurer这个接口中定义了MVC相关的各种组件，比如拦截器，视图解析器等等的定制方法，需要定制什么功能，只需要实现即可。
扩展MVC其实很简单，只需要以下步骤：
创建一个MVC的配置类，并且标注@Configuration注解。
实现WebMvcConfigurer这个接口，并且实现需要的方法。
在Spring Boot之前的版本还可以继承一个抽象类WebMvcConfigurerAdapter，不过在2.3.4.RELEASE这个版本中被废弃了，
早期的SSM架构中想要搭建一个MVC其实挺复杂的，需要配置视图解析器，资源映射处理器，DispatcherServlet等等才能正常运行，
但是为什么Spring Boot仅仅是添加一个WEB模块依赖即能正常运行呢？依赖如下：
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency> 
其实这已经涉及到了Spring Boot高级的知识点了，在这里就简单的说一下，Spring Boot的每一个starter都会有一个自动配置类，
什么是自动配置类呢？自动配置类就是在Spring Boot项目启动的时候会自动加载的类，能够在启动期间就配置一些默认的配置。
WEB模块的自动配置类是WebMvcAutoConfiguration。
WebMvcAutoConfiguration这个配置类中还含有如下一个子配置类WebMvcAutoConfigurationAdapter，如下：
@Configuration(proxyBeanMethods = false)
@Import(EnableWebMvcConfiguration.class)
@EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
@Order(0)
public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {}
WebMvcAutoConfigurationAdapter这个子配置类实现了WebMvcConfigurer这个接口，这个正是MVC扩展接口，这个就很清楚了。
自动配置类是在项目启动的时候就加载的，因此Spring Boot会在项目启动时加载WebMvcAutoConfigurationAdapter这个MVC扩展配置类，
提前完成一些默认的配置（比如内置了默认的视图解析器，资源映射处理器等等），这也就是为什么没有配置什么MVC相关的东西依然能够运行。

早在Spring 3.x就已经提出了@ControllerAdvice，可以与@ExceptionHandler、@InitBinder、@ModelAttribute 等注解注解配套使用，
这几个此处就不再详细解释了。
这几个注解小眼一瞟只有@ExceptionHandler与异常有关啊，翻译过来就是异常处理器。其实异常的处理可以分为两类，分别是局部异常处理和全局异常处理。
局部异常处理：@ExceptionHandler和@Controller注解搭配使用，只有指定的controller层出现了异常才会被@ExceptionHandler捕获到，
实际生产中怕是有成百上千个controller了吧，显然这种方式不合适。
全局异常处理：既然局部异常处理不合适了，自然有人站出来解决问题了，于是就有了@ControllerAdvice这个注解的横空出世了，
@ControllerAdvice搭配@ExceptionHandler彻底解决了全局统一异常处理。当然后面还出现了@RestControllerAdvice这个注解，
其实就是@ControllerAdvice和@ResponseBody结晶。


进入controller之前异常一般是javax.servlet.ServletException类型的异常，因此在全局异常处理的时候需要统一处理。几个常见的异常如下：
NoHandlerFoundException：客户端的请求没有找到对应的controller，将会抛出404异常。
HttpRequestMethodNotSupportedException：若匹配到了（匹配结果是一个列表，不同的是http方法不同，如：Get、Post等），
则尝试将请求的http方法与列表的控制器做匹配，若没有对应http方法的控制器，则抛该异常
HttpMediaTypeNotSupportedException：然后再对请求头与控制器支持的做比较，比如content-type请求头，
若控制器的参数签名包含注解@RequestBody，但是请求的content-type请求头的值没有包含application/json，
那么会抛该异常（当然，不止这种情况会抛这个异常）
MissingPathVariableException：未检测到路径参数。比如url为：/user/{userId}，参数签名包含@PathVariable("userId")，
当请求的url为/user，在没有明确定义url为/user的情况下，会被判定为：缺少路径参数
@Conditionalxxx这类注解表示某种判断条件成立时才会执行相关操作。掌握该类注解，有助于日常开发，框架的搭建。
@Conditional注解是从Spring4.0才有的，可以用在任何类型或者方法上面，通过@Conditional注解可以配置一些条件判断，
当所有条件都满足的时候，被@Conditional标注的目标才会被Spring容器处理。
@Conditional的使用很广，比如控制某个Bean是否需要注册，在Spring Boot中的变形很多，
比如@ConditionalOnMissingBean、@ConditionalOnBean等等，

@Conditional注解实现的原理很简单，就是通过org.springframework.context.annotation.Condition这个接口判断是否应该执行操作。
@Conditional注解判断条件与否取决于value属性指定的Condition实现，其中有一个matches()方法，返回true表示条件成立，反之不成立，

假设有这样一个需求，需要根据运行环境注入不同的Bean，Windows环境和Linux环境注入不同的Bean。
什么是配置类，有哪些？
类上被@Component、 @ComponentScan、@Import、@ImportResource、@Configuration标注的以及类中方法有@Bean的方法。
如何判断配置类，在源码中有单独的方法：org.springframework.context.annotation.ConfigurationClassUtils#isConfigurationCandidate。


条件判断在什么时候执行？
条件判断的执行分为两个阶段，如下：
配置类解析阶段(ConfigurationPhase.PARSE_CONFIGURATION)：在这个阶段会得到一批配置类的信息和一些需要注册的Bean。
Bean注册阶段(ConfigurationPhase.REGISTER_BEAN)：将配置类解析阶段得到的配置类和需要注册的Bean注入到容器中。
默认都是配置解析阶段，其实也就够用了，但是在Spring Boot中使用了ConfigurationCondition，这个接口可以自定义执行阶段，
比如@ConditionalOnMissingBean都是在Bean注册阶段执行，因为需要从容器中判断Bean。
这个两个阶段有什么不同呢？：其实很简单的，配置类解析阶段只是将需要加载配置类和一些Bean（被@Conditional注解过滤掉之后）收集起来，
而Bean注册阶段是将的收集来的Bean和配置类注入到容器中，
如果在配置类解析阶段执行Condition接口的matches()接口去判断某些Bean是否存在IOC容器中，这个显然是不行的，因为这些Bean还未注册到容器中。


ConfigurationCondition接口
这个接口相比于@Condition接口就多了一个getConfigurationPhase()方法，可以自定义执行阶段。
这个接口在需要指定执行阶段的时候可以实现，比如需要根据某个Bean是否在IOC容器中来注入指定的Bean，
则需要指定执行阶段为Bean的注册阶段（ConfigurationPhase.REGISTER_BEAN）。
@Conditional中的Condition判断条件可以指定多个，默认是按照先后顺序执行

上述例子会依次按照Condition1、Condition2、Condition3执行。
默认按照先后顺序执行，但是当我们需要指定顺序呢？很简单，有如下三种方式：
实现PriorityOrdered接口，指定优先级
实现Ordered接口接口，指定优先级
使用@Order注解来指定优先级
根据排序的规则，PriorityOrdered的会排在前面，然后会再按照order升序，最后可以顺序是：Condtion3->Condtion2->Condtion1


Spring Boot中常用的一些注解
Spring Boot中大量使用了这些注解，常见的注解如下：
@ConditionalOnBean：当容器中有指定Bean的条件下进行实例化。
@ConditionalOnMissingBean：当容器里没有指定Bean的条件下进行实例化。
@ConditionalOnClass：当classpath类路径下有指定类的条件下进行实例化。
@ConditionalOnMissingClass：当类路径下没有指定类的条件下进行实例化。
@ConditionalOnWebApplication：当项目是一个Web项目时进行实例化。
@ConditionalOnNotWebApplication：当项目不是一个Web项目时进行实例化。
@ConditionalOnProperty：当指定的属性有指定的值时进行实例化。
@ConditionalOnExpression：基于SpEL表达式的条件判断。
@ConditionalOnJava：当JVM版本为指定的版本范围时触发实例化。
@ConditionalOnResource：当类路径下有指定的资源时触发实例化。
@ConditionalOnJndi：在JNDI存在的条件下触发实例化。
@ConditionalOnSingleCandidate：当指定的Bean在容器中只有一个，或者有多个但是指定了首选的Bean时触发实例化。

WEB模块的自动配置类WebMvcAutoConfiguration
常见的@Bean和@ConditionalOnMissingBean注解结合使用，意思是当容器中没有InternalResourceViewResolver这种类型的Bean才会注入。
这样写有什么好处呢？好处很明显，可以让开发者自定义需要的视图解析器，如果没有自定义，则使用默认的，这就是Spring Boot为自定义配置提供的便利。

Spring Boot 如何整合 xxx
Spring Boot 在整合任何一个组件的时候都会先添加一个依赖starter，
比如整合的Mybatis有一个mybatis-spring-boot-starter，依赖如下：
<dependency>
         <groupId>org.mybatis.spring.boot</groupId>
         <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.0.0</version>
</dependency>
自己自带的starter在后面，自己集成的starter在前面
每一个starter基本都会有一个自动配置类，命名方式也是类似的，格式为：xxxAutoConfiguration，
比如Mybatis的自动配置类就是MybatisAutoConfiguration，Redis的自动配置类是RedisAutoConfiguration，
WEB模块的自动配置类是WebMvcAutoConfiguration。
比如WebMvcAutoConfiguration类上标了一个如下注解：
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
以上这行代码的意思就是当前IOC容器中没有WebMvcConfigurationSupport这个类的实例时自动配置类才会生效，
这也就是在配置类上标注@EnableWebMvc会导致自动配置类WebMvcAutoConfiguration失效的原因。

下面列出了常用的几种注解，如下：
@ConditionalOnBean：当容器中有指定Bean的条件下进行实例化。
@ConditionalOnMissingBean：当容器里没有指定Bean的条件下进行实例化。
@ConditionalOnClass：当classpath类路径下有指定类的条件下进行实例化。
@ConditionalOnMissingClass：当类路径下没有指定类的条件下进行实例化。
@ConditionalOnWebApplication：当项目是一个Web项目时进行实例化。
@ConditionalOnNotWebApplication：当项目不是一个Web项目时进行实例化。
@ConditionalOnProperty：当指定的属性有指定的值时进行实例化。
@ConditionalOnExpression：基于SpEL表达式的条件判断。
@ConditionalOnJava：当JVM版本为指定的版本范围时触发实例化。
@ConditionalOnResource：当类路径下有指定的资源时触发实例化。
@ConditionalOnJndi：在JNDI存在的条件下触发实例化。
@ConditionalOnSingleCandidate：当指定的Bean在容器中只有一个，或者有多个但是指定了首选的Bean时触发实例化。

注意EnableConfigurationProperties注解
EnableConfigurationProperties这个注解常标注在配置类上，使得@ConfigurationProperties标注的配置文件生效，
这样就可以在全局配置文件（application.xxx）配置指定前缀的属性了。
在Redis的自动配置类RedisAutoConfiguration上方标注如下一行代码：
@EnableConfigurationProperties(RedisProperties.class)
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
 private int database = 0;
 private String url;
 private String host = "localhost";
 private String password;
  .....
}
@ConfigurationProperties这个注解指定了全局配置文件中以spring.redis.xxx为前缀的配置都会映射到RedisProperties的指定属性中，
其实RedisProperties这个类中定义了Redis的一些所需属性，比如host，IP地址，密码等等。    
@EnableConfigurationProperties注解就是使得指定的配置生效，能够将全局配置文件中配置的属性映射到相关类的属性中。    
引入一个组件后往往需要改些配置，我们都知道在全局配置文件中可以修改，但是不知道前缀是什么，
可以改哪些属性，因此找到@EnableConfigurationProperties这个注解后就能找到对应的配置前缀以及可以修改的属性了。


@Import注解
快速导入一个Bean或者配置类到IOC容器中。
@Import这个注解通常标注在自动配置类上方，并且一般都是导入一个或者多个配置类。
比如Redis的自动配置类RedisAutoConfiguration上有如下一行代码：
@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })
这个@Import同时引入了Lettuce和Jedis两个配置类了，因此如果你的Redis需要使用Jedis作为连接池的话，
想要知道Jedis都要配置什么，此时就应该看看JedisConnectionConfiguration这个配置类了。

@AutoConfigurexxx这类注解决定了自动配置类的加载顺序，比如AutoConfigureAfter（在指定自动配置类之后）、
AutoConfigureBefore（在指定自动配置类之前）、AutoConfigureOrder（指定自动配置类的优先级）。
为什么要注意顺序呢？因为某些组件往往之间是相互依赖的，
比如Mybatis和DataSource，肯定要先将数据源相关的东西配置成功才能配置Mybatis吧。@AutoConfigurexxx这类注解正是解决了组件之间相互依赖的问题。
比如MybatisAutoConfiguration上方标注了如下一行代码：
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
这个行代码意思很简单，就是MybatisAutoConfiguration这个自动配置在DataSourceAutoConfiguration这个之后加载，
因为你需要我，多么简单的理由。

Spring Boot整合JSR-303只需要添加一个starter即可，如下：
<dependency>
    <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

内嵌的注解有哪些？
Bean Validation 内嵌的注解很多，基本实际开发中已经够用了，注解如下：
注解	详细信息
@Null	被注释的元素必须为 null
@NotNull	被注释的元素必须不为 null
@AssertTrue	被注释的元素必须为 true
@AssertFalse	被注释的元素必须为 false
@Min(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max, min)	被注释的元素的大小必须在指定的范围内
@Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past	被注释的元素必须是一个过去的日期
@Future	被注释的元素必须是一个将来的日期
@Pattern(value)	被注释的元素必须符合指定的正则表达式

以上是Bean Validation的内嵌的注解，但是Hibernate Validator在原有的基础上也内嵌了几个注解，如下。
注解	详细信息
@Email	被注释的元素必须是电子邮箱地址
@Length	被注释的字符串的大小必须在指定的范围内
@NotEmpty	被注释的字符串的必须非空
@Range	被注释的元素必须在合适的范围内
以上约束标记完成之后，要想完成校验，需要在controller层的接口标注@Valid注解以及声明一个BindingResult类型的参数来接收校验的结果。

记住OpenApiDocumentationConfiguration这个配置类，初步看来这是个BUG，本人也不想深入，里面的代码写的实在拙劣，注释都不写。
只有在配置类标注了@EnableOpenApi这个注解才会生成Swagger文档


创建SpringApplication和执行run()方法

创建Springapplication对象的时候需要执行的步骤：
Springapplication的属性
this.primarySources = class com.roy.APP
将主启动类设置到集合中存储起来
this.webApplicationType = SERVLET
设置应用类型
这里我引入了spring-boot-starter-web,肯定是Servlet的web程序。
设置初始化器（Initializer），启动过程待调用
this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));
这也告诉我们自定义一个ApplicationContextInitializer只需要实现接口，在spring.factories文件中设置即可。
，最终调用的是#SpringFactoriesLoader.loadSpringFactories()方法。
loadSpringFactories()方法就不再详细解释了，
其实就是从类路径META-INF/spring.factories中加载ApplicationContextInitializer的值。

SpringApplication的构建都是为了run()方法启动做铺垫，构造方法中总共就有几行代码，
最重要的部分就是设置应用类型、设置初始化器、设置监听器。
「注意」：初始化器和这里的监听器都要放置在spring.factories文件中才能在这一步骤加载，否则不会生效，
因此此时IOC容器还未创建，即使将其注入到IOC容器中也是不会生效的。


Spring Boot 启动流程

Springapplication.run()方法
获取、启动运行过程监听器
环境构建
创建IOC容器
IOC容器的前置处理
刷新容器
IOC容器的后置处理
发出结束执行的事件
执行runners

SpringApplication的构建都是为了run()方法启动做铺垫，构造方法中总共就有几行代码，最重要的部分就是设置应用类型、设置初始化器、设置监听器。
初始化器和这里的监听器都要放置在spring.factories文件中才能在这一步骤加载，否则不会生效，因此此时IOC容器还未创建，
即使将其注入到IOC容器中也是不会生效的。

SpringApplicationRunListener这个监听器和ApplicationListener不同，它是用来监听应用程序启动过程的，接口的各个方法含义如下：
public interface SpringApplicationRunListener {
    // 在run()方法开始执行时，该方法就立即被调用，可用于在初始化最早期时做一些工作
    void starting();
    // 当environment构建完成，ApplicationContext创建之前，该方法被调用
    void environmentPrepared(ConfigurableEnvironment environment);
    // 当ApplicationContext构建完成时，该方法被调用
    void contextPrepared(ConfigurableApplicationContext context);
    // 在ApplicationContext完成加载，但没有被刷新前，该方法被调用
    void contextLoaded(ConfigurableApplicationContext context);
    // 在ApplicationContext刷新并启动后，CommandLineRunners和ApplicationRunner未被调用前，该方法被调用
    void started(ConfigurableApplicationContext context);
    // 在run()方法执行完成前该方法被调用
    void running(ConfigurableApplicationContext context);
    // 当应用运行出错时该方法被调用
    void failed(ConfigurableApplicationContext context, Throwable exception);
}


如何获取运行监听器？
在SpringApplication#run()方法中，源码如下：
//从spring.factories中获取监听器
SpringApplicationRunListeners listeners = getRunListeners(args);
跟进getRunListeners()方法，其实还是调用了loadFactoryNames()方法从spring.factories文件中获取值，如下：
org.springframework.boot.SpringApplicationRunListener=org.springframework.boot.context.event.EventPublishingRunListener
最终注入的是EventPublishingRunListener这个实现类，创建实例过程肯定是通过反射了

 环境构建
这一步主要用于加载系统配置以及用户的自定义配置(application.properties)，源码如下，在run()方法中：

@AutoConfigurationPackage：自动配置包注解，默认将主配置类(@SpringBootApplication)所在的包及其子包里面的所有组件扫描到IOC容器中。
@AutoConfigurationPackage默认将主配置类(@SpringBootApplication)所在的包及其子包里面的所有组件扫描到IOC容器中。

@EnableAutoConfiguration注解的详细解释
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {}

@Import(AutoConfigurationImportSelector.class)

