package io.wegetit.sau.logger;

import io.wegetit.sau.manifest.ManifestRestService;
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
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
    HttpRequestLoggerApplication.class
})
@TestInstance(Lifecycle.PER_CLASS)
public class HttpRequestLoggerTest {

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private HttpServletResponse response;

    @MockBean
    private FilterChain filter;

    @Autowired
    private HttpRequestLoggerFilter logger;

    @Test
    public void doFilter() throws ServletException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURL()).thenReturn(new StringBuffer("someSimleUrl"));
        when(request.getQueryString()).thenReturn("value=ABC");
        when(response.getStatus()).thenReturn(123);
        logger.doFilter(request, response, filter);
        assertThat(out.toString(), matchesPattern(".* GET someSimleUrl\\?value=ABC in \\d* ms. Status 123.\\r\\n"));
    }
}
