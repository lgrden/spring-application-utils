package io.wegetit.sau.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class MongoJsonLoaderUtils {

    private MongoJsonLoaderUtils() {
    }

    public static <T, K> void loadAllIfEmpty(MongoRepository<T, K> repository, String file, Class<T> type, Consumer<T>... converters) throws IOException {
        long count = repository.count();
        if (count == 0) {
            loadAll(repository, file, type, converters);
        } else {
            log.info("Skipped loading {} as it contains {} elements", type.getSimpleName(), count);
        }
    }

    public static <T, K> void loadAll(MongoRepository<T, K> repository, String file, Class<T> type, Consumer<T>... converters) throws IOException {
        long start = System.currentTimeMillis();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("/changelogs/" + file);
        if (!resource.exists()) {
            throw new IllegalStateException(file + " not found in changelogs.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> data = objectMapper.readValue(resource.getURL(), objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        if (converters != null) {
            data.forEach(p -> {
                Arrays.asList(converters).forEach(c -> c.accept(p));
            });
        }
        repository.saveAll(data);
        long end = System.currentTimeMillis();
        log.info("Loaded {} with {} elements in {} ms.", type.getSimpleName(), data.size(), (end - start));
    }
}
