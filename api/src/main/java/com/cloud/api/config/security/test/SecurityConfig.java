package com.cloud.api.config.security.test;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("cloud").password("cloud").authorities("PRODUCT_ADD");
        // auth.userDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http.authorizeRequests().
                antMatchers("/product/add").hasAuthority("PRODUCT_ADD").
                antMatchers("/product/update").hasAuthority("PRODUCT_UPDATE").
                antMatchers("/loginPage").permitAll().
                antMatchers("/errorPage").permitAll().
                anyRequest().
                fullyAuthenticated().
                and().formLogin().//loginPage("/loginPage").loginProcessingUrl("/loginProcess").successForwardUrl("/index").failureUrl("/errorPage").
                and().csrf().disable()
        ;*/


        /*httpBasic 模式*/
        /*http.authorizeRequests().
                antMatchers("/**").
                fullyAuthenticated().
                and().
                httpBasic()
        ;*/
    }
}
