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


    //Used to authorize requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/criminals").hasRole("ADMIN") //Only admin can access criminals
                .antMatchers("/victims").hasRole("ADMIN") //Only admin can access victims
                .antMatchers("/users").hasRole("ADMIN") //Only admin can access users
                .anyRequest().authenticated() //Authenticated users are authorized to make any request except the above.
                .and()
                .httpBasic(); //Uses http basic as authentication
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
