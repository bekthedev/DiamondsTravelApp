
package edu.bekthedev.diamondstravel.config;

import edu.bekthedev.diamondstravel.service.XmlUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final XmlUserDetailsService userDetailsService;

    // Inject custom user details service that reads users from XML
    public SecurityConfig(XmlUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Define security rules: what routes are public, what requires login
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/login", "/css/**", "/images/**").permitAll() // public pages
                .anyRequest().authenticated() // everything else
            )
            .formLogin(login -> login
                .loginPage("/login") // use our custom login page
                .defaultSuccessUrl("/search", true) // redirect after login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // custom logout route
                .logoutSuccessUrl("/")// redirect after logout
                .permitAll()
            )
            .build();
    }
    // Provide authentication manager that uses our XML-based user details service
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService);
        return authBuilder.build();
    }

}