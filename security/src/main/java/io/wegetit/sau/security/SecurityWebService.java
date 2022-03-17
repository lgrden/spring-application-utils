package io.wegetit.sau.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class SecurityWebService extends WebSecurityConfigurerAdapter {

    private final SecurityAuthenticationProvider securityAuthenticationProvider;
    private final SecurityAuthenticationService securityAuthenticationService;

    private final SecurityProperties properties;

    private static class SecurityFilter implements Filter {

        private final SecurityAuthenticationService authenticationService;
        private final SecurityProperties properties;

        public SecurityFilter(SecurityAuthenticationService authenticationService, SecurityProperties properties) {
            this.authenticationService = authenticationService;
            this.properties = properties;
            log.info("Setup security filter with {} request parameter/header.", properties.getAuthTokenName());
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
            SecurityContextHolder.clearContext();

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String token = request.getParameter(properties.getAuthTokenName());
            if (StringUtils.isEmpty(token)) {
                token = httpRequest.getHeader(properties.getAuthTokenName());
            }

            if (StringUtils.isNotEmpty(token)) {
                Authentication auth = authenticationService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            chain.doFilter(request, response);
        }
    }

    @Override
    public void configure(WebSecurity web) {
        log.info("Web ignoring ant matchers = {}", properties.getWebIgnoringAntMatchers());

        properties.getWebIgnoringAntMatchers().forEach(p -> web.ignoring().antMatchers(p));

        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        web.httpFirewall(firewall);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        log.info("CSRF is {}.", properties.isCsrfDisabled());
        if (properties.isCsrfDisabled()) {
            http.csrf().disable();
        }
        log.info("CORS is {}.", properties.isCorsDisabled());
        if (properties.isCorsDisabled()) {
            http.cors().disable();
        }

        http.addFilterAfter(new SecurityFilter(securityAuthenticationService, properties), BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(securityAuthenticationProvider);
    }
}
