package pl.coderslab.snakesprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public	class SecurityConfig extends	WebSecurityConfigurerAdapter	{

    @Autowired
    private PasswordEncoder passwordEncoder; // Spring 2.x requires passwords to be hashed

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .and()
                .withUser("admin1")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .and()
        .withUser("moderator")
        .password(passwordEncoder.encode(("moderator123")))
        .roles("MODERATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/all").anonymous()
                .antMatchers("/content", "/logout", "/restricted").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/moderator").hasRole("MODERATOR")
                .antMatchers("/remove").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers("/update").hasRole("USER")
                .antMatchers("/").permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }
}