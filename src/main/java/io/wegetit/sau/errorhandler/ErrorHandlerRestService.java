package io.wegetit.sau.errorhandler;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/error-types")
@RestController
@AllArgsConstructor
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
