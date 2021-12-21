package com.example.demo.config;


import com.example.demo.Security.AuthProviderImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.demo.Security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImple authProviderImple;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers("/bus","/bus/add","/bus/ad","/bus/search", "/bus/edit/{id}","/bus/edit/{id}", "/bus/delite/{id}", "/bus/info/{id}",
                        "/breaking","/breaking/add","/breaking/ad","/breaking/delite/{id}","/breaking/edit/{id}", "/breaking/edit/{id}", "/breaking/search", "/breaking/formreport", "/breaking/breakingreport" ,
                        "/admin", "/admin/add", "/admin/ad", "/admin/search", "/admin/edit/{id}", "/admin/edit/{id}", "/admin/delite/{id}",
                        "/driver", "/driver/add", "/driver/ad", "/driver/search", "/driver/delite/{id}", "/driver/edit/{id}", "/driver/edit/{id}", "/driver/info/{id}" ,
                        "/profit", "/profit/add", "/profit/ad", "/profit/delite/{id}", "/profit/edit/{id}", "/profit/edit/{id}",  "/profit/search",
                        "/repair", "/repair/add", "/repair/ad", "/repair/search", "/repair/edit/{id}", "/repair/edit/{id}", "/repair/delite/{id}", "/repair/info/{id}",
                        "/route", "/route/add", "/route/ad", "/route/search", "/route/delite/{id}", "/route/edit/{id}", "/route/edit/{id}", "/route/info/{id}",
                        "/work", "/work/add", "/work/ad", "/work/delite/{id}", "/work/edit/{id}", "/work/edit/{id}", "/work/search").authenticated()
                .and().csrf().disable()
                .formLogin().loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .and().exceptionHandling().accessDeniedPage("/bus")
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProviderImple);
    }
}
