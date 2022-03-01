package io.wegetit.sau.http;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Slf4j
@Order(0)
public class HttpRequestLogger implements Filter {

    private final HttpRequestFilter filter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        long start = System.currentTimeMillis();
        chain.doFilter(httpRequest, httpResponse);
        long end = System.currentTimeMillis();

        String url = buildUrlInfo(httpRequest);
        long duration = end - start;
        int status = httpResponse.getStatus();
        if (filter.logUrl(url, duration, status)) {
            log.info("{} in {} ms. Status {}.", url, duration, status);
        }
    }

    public static String buildUrlInfo(HttpServletRequest httpRequest) {
        StringBuilder fullUrl = new StringBuilder();
        fullUrl.append(httpRequest.getMethod()).append(" ").append(httpRequest.getRequestURL());
        if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
            fullUrl.append('?').append(httpRequest.getQueryString());
        }
        return fullUrl.toString();
    }
}
