package io.wegetit.sau.mongo;

import io.wegetit.sau.mongo.user.UserEntity;
import io.wegetit.sau.mongo.user.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MongoBaseSetupApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MongoBaseSetupTest {

    @Autowired
    private UserEntityRepository repository;

    @Test
    public void readUser() {
        String uuid = "f53e2f36-12fd-40c2-9588-1f3c716ae52a";
        UserEntity user = repository.findById(uuid).orElseThrow();
        assertEquals(uuid, user.getId());
        assertEquals("John", user.getName());
        assertEquals(LocalDate.of(2000, 1, 12), user.getDob());
    }
}
