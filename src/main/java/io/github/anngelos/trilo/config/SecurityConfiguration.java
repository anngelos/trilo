package io.github.anngelos.trilo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails user = User.builder()
            .username("malasarte")
            .password(passwordEncoder().encode("malasarte"))
            .build();

    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity  http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/users").authenticated()
                    .anyRequest().permitAll()
            ).httpBasic(httpBasic -> {});
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
