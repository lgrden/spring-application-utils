package io.wegetit.sau.core.log.http;

import io.wegetit.sau.core.SystemOutUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HttpRequestLoggerApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class HttpRequestLoggerTest {

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private HttpServletResponse response;

    @MockBean
    private FilterChain filter;

    @Autowired
    private HttpRequestLogger logger;

    private ByteArrayOutputStream out;

    @BeforeEach
    private void setUp() {
        out = new ByteArrayOutputStream();
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURL()).thenReturn(new StringBuffer("someSimpleUrl"));
        when(request.getQueryString()).thenReturn("value=ABC");
    }

    @Test
    public void httpRequestHasBeenLogged() throws ServletException, IOException {
        when(response.getStatus()).thenReturn(123);
        SystemOutUtils.setSystemOut(new PrintStream(out));
        logger.doFilter(request, response, filter);
        SystemOutUtils.applyDefaultSystemOut();
        assertThat(out.toString().trim(), matchesPattern(".*GET someSimpleUrl\\?value=ABC in \\d* ms. Status 123."));
    }

    @Test
    public void httpRequestHasNotBeenLogged() throws ServletException, IOException {
        when(response.getStatus()).thenReturn(234);
        SystemOutUtils.setSystemOut(new PrintStream(out));
        logger.doFilter(request, response, filter);
        SystemOutUtils.applyDefaultSystemOut();
        assertEquals(out.toString(), "");
    }
}
