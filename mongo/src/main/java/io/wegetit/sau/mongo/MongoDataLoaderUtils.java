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
import java.util.stream.Collectors;

@Slf4j
public class MongoDataLoaderUtils {

    private MongoDataLoaderUtils() {
    }

    public static <T, K> void loadFromJsonIfMissing(MongoRepository<T, K> repository, String file, Class<T> type, Consumer<T>... converters) throws IOException {
        long start = System.currentTimeMillis();
        List<T> found = repository.findAll();
        List<T> data = readFromJson(file, type, converters);
        data = data.stream().filter(d -> !found.contains(d)).collect(Collectors.toList());
        repository.saveAll(data);
        long end = System.currentTimeMillis();
        log.info("Loaded {} with {} elements in {} ms.", type.getSimpleName(), data.size(), (end - start));
    }

    public static <T, K> void loadAllFromJsonIfEmpty(MongoRepository<T, K> repository, String file, Class<T> type, Consumer<T>... converters) throws IOException {
        long count = repository.count();
        if (count == 0) {
            loadAllFromJson(repository, file, type, converters);
        } else {
            log.info("Skipped loading {} as it contains {} elements", type.getSimpleName(), count);
        }
    }

    public static <T, K> void loadAllFromJson(MongoRepository<T, K> repository, String file, Class<T> type, Consumer<T>... converters) throws IOException {
        long start = System.currentTimeMillis();
        List<T> data = readFromJson(file, type, converters);
        repository.saveAll(data);
        long end = System.currentTimeMillis();
        log.info("Loaded {} with {} elements in {} ms.", type.getSimpleName(), data.size(), (end - start));
    }

    public static <T> List<T> readFromJson(String file, Class<T> type, Consumer<T>... converters) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("/changelogs/" + file);
        if (!resource.exists()) {
            throw new IllegalStateException(file + " not found in changelogs.");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> data = objectMapper.readValue(resource.getURL(), objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        if (converters != null) {
            data.forEach(p -> Arrays.asList(converters).forEach(c -> c.accept(p)));
        }
        return data;
    }
}
