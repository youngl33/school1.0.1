package com.school.config;

import com.school.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
                //安全关闭服务接口，拥有ADMIN权限的用户可以访问该rul
                // 其他地址的访问均需验证权限（需要登录）
                .anyRequest().authenticated().and()
                //开启basic认证，若不添加此项，将不能通过curl的basic方式传递用户信息
                .httpBasic().disable()
                .csrf().disable();

        /*http
                //.httpBasic().disable();
                .authorizeRequests()
                .antMatchers("/login/**","/home","/css/**","/font-awesome","/img/**","/js/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login/index")
                .loginProcessingUrl("/login/index/signin")
                .defaultSuccessUrl("/user/index");*/
    }


}
