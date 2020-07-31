package io.wegetit.sau.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(0)
public class HttpRequestLoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        long start = System.currentTimeMillis();
        chain.doFilter(httpRequest, httpResponse);
        long end = System.currentTimeMillis();
        log.info("{} in {} ms. Status {}.", buildUrlInfo(httpRequest), (end-start), httpResponse.getStatus());
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
