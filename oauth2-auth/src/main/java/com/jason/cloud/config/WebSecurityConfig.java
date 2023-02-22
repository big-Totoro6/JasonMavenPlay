package com.jason.cloud.config;

import com.jason.cloud.common.KeyStoreKeyFactory;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SpringSecurity配置
 * Edited by jason on 2023/2/20.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * 从Java配置中删除不推荐使用的antMatchers、mvcMatchers和regexMatchers助手方法。
     * 而是使用requestMatchers或HttpSecurity#securityMatchers
     * 删除WebSecurityConfigurerAdapter。相反，创建SecurityFilterChain bean。
     * 官方提供的模板 filterChain
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/testAuth").hasAnyAuthority("ADMIN","TEST")
                        .requestMatchers("/adminAuth").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * 放开这些请求的拦截
     *
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore/**", "/ignore2")
                // ignore all URLs that start with /resources/ or /static/
                .requestMatchers("/resources/**")
                .requestMatchers("/static/**");
    }

}
