package io.wegetit.sau.core.log.execustion;

import org.junit.jupiter.api.AfterEach;
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

    private PrintStream original;
    private ByteArrayOutputStream out;

    @BeforeEach
    private void setUp() {
        original = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    private void tearDown() {
        System.setOut(original);
    }

    @Test
    public void execute() throws InterruptedException {
        executionTestService.execute();
        assertThat(out.toString().trim(), matchesPattern(".* ExecutionTestService.execute executed with SUCCESS in \\d* ms."));
    }
}
