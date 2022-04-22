package io.wegetit.sau.shared.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

@Slf4j
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) {
        log.info("Reading yml properties from {}", encodedResource.getResource().getFilename());
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        Properties properties = factory.getObject();
        return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
    }
}
