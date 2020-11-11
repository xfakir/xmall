package cn.xfakir.xmall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 配置资源服务器
 */
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /*@Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resource1") // 配置资源id，这里的资源id和授权服务器中的资源id一致
                .stateless(true)// 设置这些资源仅基于令牌认证
        .tokenStore(redisTokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        *//**
         * 这里配置需要token验证的url
         * 这些url也可以在WebSecurityConfigurerAdapter中配置
         * 对于相同的url，如果二者都进行了配置
         * 则优先进入ResourceServerConfigurerAdapter，进行token验证
         * 不会进入ResourceServerConfigurerAdapter 进行basic auth或表单验证
         *//*
        http.authorizeRequests()
                .antMatchers("/test/order/**").authenticated()
                .anyRequest().permitAll();
    }*/
}
