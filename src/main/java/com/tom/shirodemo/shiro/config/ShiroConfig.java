package com.tom.shirodemo.shiro.config;

import com.tom.shirodemo.shiro.config.CustomRealm;
import com.tom.shirodemo.shiro.config.MyFormAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirofilter(SecurityManager manager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean() ;
        //配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(manager);
        //登陆controller的mapper 地址
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //未认证的地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth.jsp");
        //登陆后跳转到指定地址
        Map<String,Filter> filtersMap = new LinkedHashMap<>() ;
        filtersMap.put("authc", new MyFormAuthenticationFilter()) ;
        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String,String> map = new LinkedHashMap<>() ;
        map.put("unauth.jsp","anon") ;
        map.put("/common/**", "anon") ;
        map.put("/easyui/**", "anon") ;
        map.put("/css/**", "anon");
        map.put("/images/**", "anon");
        map.put("/hello", "anon") ;
        map.put("/login","anon" ) ;
        map.put("/logout","authc" ) ;
        map.put("/**", "authc") ;
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean ;
    }

    @Bean
    public MyFormAuthenticationFilter customFormAuthenticationFilter(){
        System.out.println("ShiroConfiguration.formAuthenticationFilter()");
        MyFormAuthenticationFilter customFormAuthenticationFilter = new MyFormAuthenticationFilter();
        return customFormAuthenticationFilter;
    }

    @Bean
    public FilterRegistrationBean shiroFilterRegistration(MyFormAuthenticationFilter myFormAuthenticationFilter) {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(myFormAuthenticationFilter);
        filterRegistration.setEnabled(false);
        return filterRegistration;
    }

    @Bean
    public SecurityManager securityManager(Realm realm,EhCacheManager ehCacheManager,SessionManager sessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager() ;
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        return defaultWebSecurityManager ;
    }

    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher credentialsMatcher){
        CustomRealm customRealm  = new CustomRealm() ;
        customRealm.setCredentialsMatcher(credentialsMatcher);
        customRealm.setCachingEnabled(false); ;
        return customRealm ;
    }

    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher() ;
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher ;
    }
    //    权限配置（注解方式）
    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor() ;
        return  lifecycleBeanPostProcessor ;
    }

    /**
     * 开启shiro权限注解功能
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 缓存管理
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        return new EhCacheManager() ;
    }

    /**
     * session id
     * @return
     */
    @Bean
    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator(){
        return  new JavaUuidSessionIdGenerator() ;
    }

    @Bean
    public SessionDAO sessionDAO(JavaUuidSessionIdGenerator javaUuidSessionIdGenerator){
        SessionDAO sessionDAO = new EnterpriseCacheSessionDAO() ;
        ((EnterpriseCacheSessionDAO) sessionDAO).setActiveSessionsCacheName("shiro-activeSessionCache");
        ((EnterpriseCacheSessionDAO) sessionDAO).setSessionIdGenerator(javaUuidSessionIdGenerator);
        return sessionDAO ;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("mldn-session-id") ;
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie ;
    }

    @Bean
    public SessionManager sessionManager(SimpleCookie simpleCookie, SessionDAO sessionDAO, EhCacheManager cacheManager){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager() ;
        sessionManager.setSessionIdCookie(simpleCookie);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setGlobalSessionTimeout(60000*20);
        sessionManager.setSessionValidationInterval(60000*10);
        return sessionManager ;
    }

}
