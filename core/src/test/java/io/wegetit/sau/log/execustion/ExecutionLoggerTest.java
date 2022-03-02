package io.wegetit.sau.log.execustion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ExecutionLoggerApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ExecutionLoggerTest {

    @Autowired
    public ExecutionTestService executionTestService;

    private ByteArrayOutputStream out;

    @BeforeEach
    private void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void execute() throws InterruptedException {
        executionTestService.execute();
        assertThat(out.toString(), matchesPattern(".* ExecutionTestService.execute executed with SUCCESS in \\d* ms.\\r\\n"));
    }
}
