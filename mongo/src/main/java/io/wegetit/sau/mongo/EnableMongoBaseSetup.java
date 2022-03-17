package io.wegetit.sau.mongo;

import io.wegetit.sau.shared.configuration.BaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableMongoBaseSetup.MongoBaseSetupConfiguration.class)
public @interface EnableMongoBaseSetup {

    @Slf4j
    @Configuration
    class MongoBaseSetupConfiguration extends BaseConfiguration {

        @Lazy
        @Autowired
        private MongoTemplate mongoTemplate;

        @Bean
        public MongoCustomConversions mongoCustomConversions() {
            List<Converter<?, ?>> converters = new ArrayList<>();
            converters.add(new LocalDateConverter.LocalDateFromDateConverter());
            converters.add(new LocalDateConverter.LocalDateToDateConverter());
            converters.add(new LocalDateTimeConverter.LocalDateTimeFromDateConverter());
            converters.add(new LocalDateTimeConverter.LocalDateTimeToDateConverter());
            return new MongoCustomConversions(converters);
        }

        @EventListener(ContextRefreshedEvent.class)
        public void initIndicesAfterStartup() {
            MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext = mongoTemplate.getConverter().getMappingContext();
            IndexResolver resolver = new MongoPersistentEntityIndexResolver(mappingContext);
            mappingContext.getPersistentEntities().stream().filter(it -> it.isAnnotationPresent(Document.class)).forEach(it -> {
                IndexOperations indexOps = mongoTemplate.indexOps(it.getType());
                log.info("Creating mongo index for {}.", it.getType().getCanonicalName());
                resolver.resolveIndexFor(it.getType()).forEach(indexOps::ensureIndex);
            });
        }
    }
}
