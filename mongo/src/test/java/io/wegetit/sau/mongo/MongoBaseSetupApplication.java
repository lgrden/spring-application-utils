package io.wegetit.sau.mongo;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@EnableMongoBaseSetup
@SpringBootApplication
public class MongoBaseSetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoBaseSetupApplication.class, args);
    }
}
