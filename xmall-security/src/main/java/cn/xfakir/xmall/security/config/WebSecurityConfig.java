package cn.xfakir.xmall.security.config;

import cn.xfakir.xmall.security.filter.AuthenticationUsernamePasswordFilter;
import cn.xfakir.xmall.security.filter.AuthorizationTokenFilter;
import cn.xfakir.xmall.security.handler.XmAccessDeniedHandler;
import cn.xfakir.xmall.security.handler.XmAuthenticationEntryPoint;
import cn.xfakir.xmall.security.handler.XmAuthenticationFailureHandler;
import cn.xfakir.xmall.security.handler.XmAuthenticationSuccessHandler;
import cn.xfakir.xmall.security.service.XmUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private XmUserDetailService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private XmAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private XmAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private XmAuthenticationEntryPoint authenticationEntryPoint;

    /*@Autowired
    private AuthorizationTokenFilter authorizationTokenFilter;*/

    @Autowired
    private XmAccessDeniedHandler accessDeniedHandler;

    /*@Autowired
    private AuthenticationUsernamePasswordFilter authenticationUsernamePasswordFilter;*/

    @Bean
    AuthenticationUsernamePasswordFilter authenticationUsernamePasswordFilter() throws Exception {
        AuthenticationUsernamePasswordFilter authenticationUsernamePasswordFilter = new AuthenticationUsernamePasswordFilter();
        authenticationUsernamePasswordFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationUsernamePasswordFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationUsernamePasswordFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return authenticationUsernamePasswordFilter;
    }

    @Bean
    AuthorizationTokenFilter authorizationTokenFilter() {
        return new AuthorizationTokenFilter(userDetailsService);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        /*auth.inMemoryAuthentication()
                .withUser("smm")
                .password(passwordEncoder.encode("123"))
                .authorities(Collections.emptyList());*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.csrf().disable()
                .cors()
                .and()
                .formLogin()
                .loginProcessingUrl("/test/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/test/product").permitAll();
        registry.anyRequest().authenticated()
                .and()
                .userDetailsService(userDetailsService)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .addFilterAt(authenticationUsernamePasswordFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
