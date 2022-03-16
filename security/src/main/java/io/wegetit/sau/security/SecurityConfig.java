package io.wegetit.sau.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private SecurityAuthenticationService securityAuthenticationService;

    @Override
    public void configure(WebSecurity web) {
        // General
        web.ignoring().antMatchers("/");
        web.ignoring().antMatchers("/actuator/**");
        web.ignoring().antMatchers("/security/**");
        web.ignoring().antMatchers("/error-types");
        web.ignoring().antMatchers("/system-info");

        // Swagger
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-ui/**");
        web.ignoring().antMatchers("/v3/api-docs/**");

        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        web.httpFirewall(firewall);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.csrf().disable();
        http.cors().disable();

        http.addFilterAfter(new SecurityFilter(securityAuthenticationService), BasicAuthenticationFilter.class);
        log.info("Loaded security configuration.");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
