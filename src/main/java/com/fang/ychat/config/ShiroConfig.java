package com.fang.ychat.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")SecurityManager securityManager){
        System.out.println("*********shiro config*********");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String ,String > filterChain = new LinkedHashMap<String,String>();
        filterChain.put("/add","authc");
        filterChain.put("/info","authc");
       filterChain.put("/chat","authc");
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/chat");
        factoryBean.setUnauthorizedUrl("/errorpage");
        factoryBean.setFilterChainDefinitionMap(filterChain);
        return factoryBean;
    }



    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean(name = "myRealm")
    public MyRealm myRealm (){
        return new MyRealm();
    }

    // 结合thymeleaf使用shiro页面标签语法
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
