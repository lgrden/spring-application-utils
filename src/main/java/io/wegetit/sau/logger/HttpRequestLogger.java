package io.wegetit.sau.logger;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

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
        long time = end - start;
        int status = httpResponse.getStatus();
        if (filter.logUrl(url, time, status)) {
            log.info("{} in {} ms. Status {}.", url, time, status);
        }
    }

    private static String buildUrlInfo(HttpServletRequest httpRequest) {
        StringBuilder fullUrl = new StringBuilder();
        fullUrl.append(httpRequest.getMethod());
        fullUrl.append(" ");
        fullUrl.append(httpRequest.getRequestURL());
        if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
            fullUrl.append('?').append(httpRequest.getQueryString());
        }
        return fullUrl.toString();
    }
}
