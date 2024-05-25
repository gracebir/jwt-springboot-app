package com.grace.jwt_authentication.config;

import com.grace.jwt_authentication.user.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(crfConfig-> crfConfig.disable())
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authConfig-> {
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers("errros").permitAll();

                    authConfig.requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority(Permissions.READ_ALL_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority(Permissions.SAVE_ONE_PRODUCT.name());

                    authConfig.anyRequest().denyAll();
                });

        return httpSecurity.build();
    }
}
