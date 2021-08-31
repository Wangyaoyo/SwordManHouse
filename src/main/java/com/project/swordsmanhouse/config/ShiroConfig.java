package com.project.swordsmanhouse.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wy
 * @version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(
            @Qualifier(value = "userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier(value = "securityManager") DefaultWebSecurityManager securityManager
    ){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
            拦截：
                anon: 无需认证
                authc: 必须先认证
                user: 必须使用记住我才能访问
                perms: 拥有对某个资源的权限可访问
                role: 拥有对某个角色的权限可访问
         */

        //添加shiro的内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        //访问/order和/check下的请求必须认证(登录)
        filterMap.put("/order/*","authc");
        filterMap.put("/check/*","authc");
        filterMap.put("/order/*/*","authc");
        filterMap.put("/check/*/*","authc");
        //访问/check下的请求必须有check权限
        filterMap.put("/check/*", "perms[check]");
        filterMap.put("/check/*/*", "perms[check]");
        //order请求下的所有请求都是认证后皆可访问

//        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置拦截器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
}
