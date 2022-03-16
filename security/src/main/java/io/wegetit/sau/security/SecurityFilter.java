package io.wegetit.sau.security;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
public class SecurityFilter implements Filter {

    public static final String AUTH_TOKEN = "auth_token";

    private SecurityAuthenticationService authenticationService;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        SecurityContextHolder.clearContext();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = request.getParameter(AUTH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = httpRequest.getHeader(AUTH_TOKEN);
        }

        if (StringUtils.isNotEmpty(token)) {
            Authentication auth = authenticationService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
