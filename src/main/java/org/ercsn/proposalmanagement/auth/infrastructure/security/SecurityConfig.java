package org.ercsn.proposalmanagement.auth.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            RestUserPasswordAuthenticationFilter filter) throws Exception {
        http
             .csrf(AbstractHttpConfigurer::disable)
             .authorizeHttpRequests(auth -> auth
                     .requestMatchers("/api/auth/login")
                     .permitAll()
                     .anyRequest()
                     .authenticated())
             .addFilterAt(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails influencer = User.withUsername("influencer")
                                    .password(passwordEncoder.encode("password"))
                                    .roles("INFLUENCER")
                                    .build();

        UserDetails brand = User.withUsername("brand")
                                .password(passwordEncoder.encode("password"))
                                .roles("BRAND")
                                .build();
        return new InMemoryUserDetailsManager(influencer, brand);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
