package com.example.springschool.config;

import com.example.springschool.filter.AuthEndpoint;
import com.example.springschool.filter.AuthenFilter;
import com.example.springschool.filter.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
    @Bean
    public AuthenFilter authenticationFilter() throws Exception{
        AuthenFilter authenticationFilter = new AuthenFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }

    @Bean
    public AuthEndpoint authEndpoint(){return new AuthEndpoint();}
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {return new CustomAccessDeniedHandler();}
    protected  void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/v1/login");
        http.authorizeRequests().antMatchers("/api/v1/login**").permitAll();
        http.antMatcher("/api/v1/**").httpBasic().authenticationEntryPoint(authEndpoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/v1/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/api/v1/**").access("hasRole('ROLE_ADMIN')").and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
