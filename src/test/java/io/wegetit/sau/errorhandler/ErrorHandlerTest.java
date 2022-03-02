package io.wegetit.sau.errorhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ErrorHandlerApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ErrorHandlerTest {

    @Autowired
    private ErrorHandlerRestService errorHandlerRestService;

    @Autowired
    private ErrorHandlerService errorHandlerService;

    @MockBean
    private HttpServletRequest request;

    @BeforeEach
    private void setUp() {
        when(request.getContextPath()).thenReturn("aaa");
        when(request.getServletPath()).thenReturn("bbb");

        errorHandlerService.registerType(ExceptionType.builder().errorClass(EntityNotFoundException.class)
            .status(HttpStatus.NOT_FOUND).evalMessage(p -> "MSG: " + p.getMessage()).build());
    }

    @Test
    public void testRestEndpoint() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorHandlerRestService.getTypes().getDef().getStatus());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorHandlerRestService.getTypes().getDef().getStatusText());
        assertEquals("EXCEPTION", errorHandlerRestService.getTypes().getDef().getCode());
        assertThat(errorHandlerRestService.getTypes().getList(), hasSize(13));
    }

    @Test
    public void handleNPE() {
        ResponseEntity<ErrorResponse> response = errorHandlerService.handle(new NullPointerException("Test message"), request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getBody().getStatusText());
        assertEquals("EXCEPTION", response.getBody().getCode());
        assertEquals("Test message", response.getBody().getMessage());
        assertEquals("/aaa/bbb", response.getBody().getPath());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void handleENFE() {
        ResponseEntity<ErrorResponse> response = errorHandlerService.handle(new EntityNotFoundException(), request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.NOT_FOUND, response.getBody().getStatusText());
        assertEquals("ENTITY_NOT_FOUND", response.getBody().getCode());
        assertEquals("MSG: Entity not found.", response.getBody().getMessage());
        assertEquals("/aaa/bbb", response.getBody().getPath());
        assertNotNull(response.getBody().getTimestamp());
    }
}
