package io.wegetit.sau.core.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MessageResolverApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MessageResolverTest {

    @Autowired
    private MessageResolver messageResolver;

    @Test
    public void getMessage() {
        assertEquals("Hello John Doe, how are you?", messageResolver.getMessage("hello", "John Doe"));
        assertEquals("Hallo John Doe, wie geht es Ihnen?", messageResolver.getMessage("hello", Locale.GERMAN, "John Doe"));
        assertEquals("Hello John Doe, how are you?", messageResolver.getMessage("hello", Locale.FRENCH, "John Doe"));
        assertEquals("not-exists", messageResolver.getMessage("not-exists"));
    }
}
