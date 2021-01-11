package com.bothsavage.configuration;

import ch.qos.logback.core.db.DBHelper;
import com.bothsavage.bean.Pet;
import com.bothsavage.bean.TestCondition;
import com.bothsavage.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
