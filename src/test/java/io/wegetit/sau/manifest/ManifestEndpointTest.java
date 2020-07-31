package io.wegetit.sau.manifest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
    ManifestEndpointApplication.class
})
@TestInstance(Lifecycle.PER_CLASS)
public class ManifestEndpointTest {

    @MockBean
    private ServletContext servletContext;

    @Autowired
    private ManifestRestService service;

    @Test
    public void getManifestMap() throws IOException {
        when(servletContext.getResourceAsStream("/META-INF/MANIFEST.MF"))
            .thenReturn(ManifestEndpointTest.class.getResourceAsStream("/TEST_MANIFEST.MF"));
        Map<String, String> map = service.getManifestMap();
        assertThat(map.size(), is(2));
        assertThat(map, hasEntry("Manifest-Version", "1.0"));
        assertThat(map, hasEntry("Created-By", "Maven Archiver 3.4.0"));
    }
}
