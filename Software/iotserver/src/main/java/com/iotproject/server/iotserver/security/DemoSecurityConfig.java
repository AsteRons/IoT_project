package com.iotproject.server.iotserver.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Konfiguracja zabezpieczeń
 */
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * Implementacja definiowania przykłądowych użytkowników
     * Ustawianie dla nich haseł, nazw oraz roli
     * @param auth - obiekt użytkownika
     *
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("Admin").password("{noop}test123").roles("ADMIN");


    }

    /**
     * Konfiguracja zabezpieczeń naszej strony www.
     * Po wiecej informasji odsyła do dokumentacji Spring Security
     * @param http
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/sensorData/showMyLoginPage").permitAll()

                .antMatchers("/sensorData/**").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginProcessingUrl("/sensorData/list") // Submit URL
                    .loginPage("/sensorData/showMyLoginPage")
                    .defaultSuccessUrl("/sensorData/list", true)
                .and()
                .logout()
                .logoutSuccessUrl("/sensorData/showMyLoginPage")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");



    }


}