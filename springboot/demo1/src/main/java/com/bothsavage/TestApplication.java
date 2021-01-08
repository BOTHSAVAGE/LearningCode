package com.bothsavage;

import ch.qos.logback.core.db.DBHelper;
import com.bothsavage.bean.User;
import com.bothsavage.configuration.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.bothsavage")
//@EnableConfigurationProperties
public class TestApplication {
    public static void main(String[] args) {
        //ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(TestApplication.class, args);
        //获取当前所有组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
        }
        //从容器中获得(单利)
        User user = (User) run.getBean("test");
        User user2 = (User) run.getBean("test");
        System.out.println(user==user2);

        //获取配置裂
        //MyConfig$$EnhancerBySpringCGLIB  本身为代理对象，因为proxyBeanMethods的作用
        MyConfig myConfig = (MyConfig) run.getBean(MyConfig.class);
        User user3 = myConfig.returnUser();
        System.out.println(user3==user);//true
        System.out.println(myConfig);


        //验证import,默认组件的名字就是全类名
        DBHelper bean = run.getBean(DBHelper.class);
        System.out.println(bean);

        //验证@conditional,springboot底层有很多这个
        System.out.println(run.containsBean("returnTestConditon"));

        //只有在容器中的组件才能加载configuraporpertirse
        Object testConfig = run.getBean("TestConfig");
        System.out.println(testConfig);

        //获取容器中有多少组件
        int beanDefinitionCount = run.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);


    }
}
