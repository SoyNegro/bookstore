package dev.coffeecult.bookstore.security.jwt;

import dev.coffeecult.bookstore.security.service.BaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {
    private BaseUserDetailsService baseUserDetailsService;
    private AuthEntryPointJWT authEntryPointJWT;

    public WebSecurityConfig(BaseUserDetailsService baseUserDetailsService, AuthEntryPointJWT authEntryPointJWT){
        this.baseUserDetailsService = baseUserDetailsService;
        this.authEntryPointJWT = authEntryPointJWT;
    }

    @Bean
    public AuthTokenFilter authTokenFilter(){
        return new AuthTokenFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(baseUserDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
     http.cors().and().csrf().disable().
             exceptionHandling().authenticationEntryPoint(authEntryPointJWT)
             .and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
             .authorizeRequests().antMatchers("/api/auth/**").permitAll()
             .antMatchers("/api/public/**").permitAll()
             .anyRequest().authenticated();

     http.authenticationProvider(daoAuthenticationProvider());
     http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
     return http.getObject();
    }

}
