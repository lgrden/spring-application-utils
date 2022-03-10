package io.wegetit.sau.mongo.user;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.wegetit.sau.shared.json.JsonLocalDate;
import io.wegetit.sau.shared.json.JsonLocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "users")
@TypeAlias("user")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {
    @Id
    private String id;
    private String name;
    @JsonSerialize(using = JsonLocalDate.Serializer.class)
    @JsonDeserialize(using = JsonLocalDate.Deserializer.class)
    private LocalDate dob;
    @JsonSerialize(using = JsonLocalDateTime.Serializer.class)
    @JsonDeserialize(using = JsonLocalDateTime.Deserializer.class)
    private LocalDateTime created;
}
