package consulting.sit.catenax.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain httpSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
//                .mvcMatchers("/images/**").permitAll()
//                .mvcMatchers("/js/**").permitAll()
//                .mvcMatchers(HttpMethod.GET, "/glossary/**").permitAll()
//                .mvcMatchers("/glossary/**").hasAnyRole("Admin", "Glossaryverwalter")
//                .mvcMatchers("/project/**").hasAnyAuthority("Admin", "Editor", "Salesperson")
//                .anyRequest().authenticated()
                .and()
                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789");
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}
