package io.wegetit.sau.core.log.execustion;

import io.wegetit.sau.core.SystemOutUtils;
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
    }

    @Test
    public void execute() throws InterruptedException {
        SystemOutUtils.setSystemOut(new PrintStream(out));
        executionTestService.execute();
        SystemOutUtils.applyDefaultSystemOut();
        assertThat(out.toString().trim(), matchesPattern(".* ExecutionTestService.execute executed with SUCCESS in \\d* ms."));
    }
}
