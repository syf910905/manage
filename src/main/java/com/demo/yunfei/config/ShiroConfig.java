package com.demo.yunfei.config;

import com.demo.yunfei.mapper.master.RolePermissionMapper;
import com.demo.yunfei.shiro.UserShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;

/**
 * @author : yunfei
 * @date : 2018/10/30 20:07
 */
@Configuration
public class ShiroConfig {

    @Bean
    public UserShiroRealm userShiroRealm() {
        UserShiroRealm userShiroRealm = new UserShiroRealm();
        return userShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userShiroRealm());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        // 设置拦截器
        LinkedHashMap<String, String> map = new LinkedHashMap<>(15);
        // 对静态资源设置匿名访问
        map.put("/favicon.ico**", "anon");
        map.put("/yf-logo.jpg**", "anon");
        map.put("/css/**", "anon");
        map.put("/docs/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/img/**", "anon");
        map.put("/ajax/**", "anon");
        map.put("/js/**", "anon");
        map.put("/yunfei/**", "anon");
        map.put("/druid/**", "anon");
        map.put("/captcha/captchaImage**", "anon");
        // 退出 logout地址，shiro去清除session
        map.put("/logout", "logout");
        //游客，开发权限
        //开放登陆接口
        map.put("/login", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean("defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator autoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        //指定强制使用cglib为action创建代理对象
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        DelegatingFilterProxy proxy=new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shirFilter");
        registrationBean.setFilter(proxy);
        return registrationBean;
    }



}
