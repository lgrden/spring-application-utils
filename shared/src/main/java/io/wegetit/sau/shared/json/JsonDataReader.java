package io.wegetit.sau.shared.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class JsonDataReader {

    private JsonDataReader() {}

    public static <T> List<T> readAndApply(String location, Class<T> type, Consumer<T>... consumers) throws IOException {
        List<T> data = read(location, type);
        apply(data, consumers);
        return data;
    }

    public static <T> List<T> read(String location, Class<T> type) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(location);
        if (!resource.exists()) {
            throw new IllegalStateException(location + " not found.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(resource.getURL(), objectMapper.getTypeFactory().constructCollectionType(List.class, type));
    }

    public static <T> void apply(List<T> data, Consumer<T>... consumers) {
        if (consumers != null) {
            List<Consumer<T>> list = Arrays.asList(consumers);
            data.forEach(p -> list.forEach(c -> c.accept(p)));
        }
    }
}
