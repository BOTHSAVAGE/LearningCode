package com.bothsavage.configuration;

import ch.qos.logback.core.db.DBHelper;
import com.bothsavage.bean.Pet;
import com.bothsavage.bean.TestCondition;
import com.bothsavage.bean.User;
import com.bothsavage.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * proxyBeanMethods默认为true
 * 代理bean的方法
 *
 *
 * FULL模式  - true
 * LITE模式 - false  最大的更新
 *
 * 解决组件依赖的问题
 */
@Import(DBHelper.class)
@Configuration(proxyBeanMethods = true)
public class MyConfig {

    /**
     * 默认方法名作ID,这里是test
     * 无论注册方法调用多少次，都是单实例，手动调用也算调用
     * @return
     */
    @Bean("test")
    public User returnUser(){
        //proxyBeanMethods=true 的话，此时Pet对象全局只有一个
        //如果调成了false就不是IOC里面的宠物
        //lite 模式会让springboot 启动很快，因为不会去检查bean里面的依赖
        User a = new User("a",returnPe());
        return a;
    }

    @Bean("pet")
    public Pet returnPe(){
        return new  Pet("pet",12);
    }


    @Bean
    @ConditionalOnBean(name="pet")
    public TestCondition returnTestConditon(){
        return new TestCondition();
    }



    //webMVCfongiure 可以定制springMVc的功能
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            /**
             * 自定义的内容协商策略
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

                Map<String, MediaType> mediaTypeMap= new HashMap<>();
                mediaTypeMap.put("json",MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml",MediaType.APPLICATION_XML);
                mediaTypeMap.put("gg",MediaType.parseMediaType("application/x-guigu"));


                //指定支持哪些参数对应的媒体类型
                ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);


                //请求头协商管理器，strategies里面就是所有的内容协商策略，如果要重写的话就要把之前的内容协商期的也放回去

                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();

                configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy,headerContentNegotiationStrategy));

            }

            //加入自己的消息转换器
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MessageConverter());
            }

            @Override
            public void addFormatters(FormatterRegistry registry) {
                    registry.addConverter(new Converter<String, Pet>() {
                        @Override
                        public Pet convert(String source) {
                            //解析
                            if(!StringUtils.isEmpty(source)){
                                Pet pet = new Pet();
                                String[] strings = source.split(",");
                                pet.setPetName(strings[0]);
                                pet.setPetAge(Integer.parseInt(strings[1]));
                                return pet;
                            }
                            return null;
                        }
                    });
            }
        };
    }

}
