package io.wegetit.sau.mongo;

import com.github.cloudyrock.mongock.ChangeSet;
import io.wegetit.sau.mongo.user.UserEntity;
import io.wegetit.sau.mongo.user.UserEntityRepository;

import java.io.IOException;

@com.github.cloudyrock.mongock.ChangeLog(order = "001")
public class ChangeLog {

    @ChangeSet(order = "001", id = "loadUsers", author = "grlu", runAlways = true)
    public void loadCurrencies(UserEntityRepository repository) throws IOException {
        MongoJsonLoaderUtils.loadAllIfEmpty(repository, "users.json", UserEntity.class);
    }
}
