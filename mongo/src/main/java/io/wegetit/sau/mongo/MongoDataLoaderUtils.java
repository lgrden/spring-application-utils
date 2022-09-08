package io.wegetit.sau.mongo;

import io.wegetit.sau.shared.json.JsonDataReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class MongoDataLoaderUtils {

    private MongoDataLoaderUtils() {
    }

    public static <T, K> void load(MongoRepository<T, K> repository, Class<T> type, Supplier<List<T>> supplier, Consumer<T>... converters) {
        long start = System.currentTimeMillis();
        List<T> data = supplier.get();
        JsonDataReader.apply(data, converters);
        repository.saveAll(data);
        long end = System.currentTimeMillis();
        log.info("Loaded {} with {} elements in {} ms.", type.getSimpleName(), data.size(), (end - start));
    }

    public static <T, K> void loadFromJsonIfMissing(MongoRepository<T, K> repository, String file, Class<T> type, Consumer<T>... converters) throws IOException {
        long start = System.currentTimeMillis();
        List<T> found = repository.findAll();
        List<T> data = JsonDataReader.read("/changelogs/" + file, type);
        data = data.stream().filter(d -> !found.contains(d)).collect(Collectors.toList());
        JsonDataReader.apply(data, converters);
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
        List<T> data = repository.saveAll(JsonDataReader.readAndApply("/changelogs/" + file, type, converters));
        long end = System.currentTimeMillis();
        log.info("Loaded {} with {} elements in {} ms.", type.getSimpleName(), data.size(), (end - start));
    }
}
