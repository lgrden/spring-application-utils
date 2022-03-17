package io.wegetit.sau.core.errorhandler;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/error-types")
public class ErrorHandlerRestService {
    private final ErrorHandlerService service;

    @GetMapping
    public ExceptionTypeResponse getTypes() {
        return ExceptionTypeResponse.builder()
                .def(service.getDefaultTypeInfo())
                .list(service.getTypesInfo())
                .build();
    }
}
