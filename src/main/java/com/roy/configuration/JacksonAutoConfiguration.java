package com.roy.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * description：
 * @ConditionalOnMissingBean这个注解的意思很简单，就是当IOC容器中没有指定Bean的时候才会注入，
 * 言下之意就是当容器中不存在ObjectMapper这个Bean会使用这里生成的，类似于一种生效的条件。
 * 言外之意就是只需要自定义一个ObjectMapper然后注入到IOC容器中，
 * 那么这个自动配置类JacksonAutoConfiguration中注入的将会失效，也就达到了覆盖的作用了。
 * author：dingyawu
 * date：created in 0:00 2020/11/22
 * history:
 */
@Configuration
public class JacksonAutoConfiguration {
    @Bean
    @Primary
    @ConditionalOnMissingBean
    ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        //return builder.createXmlMapper(false).build();
        builder.locale(Locale.CHINA);
        builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
//        builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
//        builder.modules(new CustomTimeModule());

        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        //遇到未知属性的时候抛出异常，//为true 会抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);


        //objectMapper.registerModule(new CustomTimeModule());

        return objectMapper;
    }
}
