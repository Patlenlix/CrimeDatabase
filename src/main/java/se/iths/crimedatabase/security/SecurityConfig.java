package se.iths.crimedatabase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/showCrimes").hasRole("ADMIN")
                .antMatchers("/showUsers").hasRole("ADMIN")
                .antMatchers("/showCategories").hasRole("ADMIN")
                .antMatchers("/showVictims").hasRole("ADMIN")
                .antMatchers("/showCriminals").hasRole("ADMIN")
                .antMatchers("/showAddresses").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().usernameParameter("username")
                .loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
