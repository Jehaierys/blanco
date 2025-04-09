package example.com.blanco.config;


import example.com.blanco.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        // Define CORS rules
//        configuration.setAllowedOrigins(List.of("http://localhost:8080")); // Allowed origin
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH")); // Allowed methods
//        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Allowed headers
//        configuration.setAllowCredentials(true); // Allow credentials
//
//        // Map CORS configuration to specific endpoints
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/users/**", configuration);
//        source.registerCorsConfiguration("/transfer/**", configuration);
//
//        return source;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
//                                .requestMatchers("/**").hasRole("USER")
//                                .requestMatchers("/ admin/**").hasRole("ADMIN")
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/login/**").permitAll()
                )
//                .cors((httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource()))
                .logout((logout) ->
                        logout
                                .deleteCookies("remove")
                                .invalidateHttpSession(true)
                                .logoutUrl("/logout")
                                .permitAll()
//                                .addLogoutHandler((LogoutHandler) new SimpleUrlLogoutSuccessHandler())
                                .logoutSuccessUrl("/login")
                                .permitAll()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginPage("/login")
                                .permitAll()
                                .failureUrl("/login?failed")
                                .loginProcessingUrl("/login")
                )
                .userDetailsService(userService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}