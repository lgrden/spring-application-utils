package io.wegetit.sau.manifest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.jar.Manifest;
import javax.servlet.ServletContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("${manifest.endpoint}")
@Slf4j
public class ManifestRestService {

    private ServletContext servletContext;

    @GetMapping
    @ResponseBody
    public Map<String, String> getManifestMap() {
        Optional<Manifest> manifest = getManifest();
        if (!manifest.isPresent()) {
            return Map.of();
        }
        Map<String, String> map = new HashMap<>();
        manifest.get().getMainAttributes().keySet().stream().map(p -> p.toString()).forEach(p -> map.put(p, manifest.get().getMainAttributes().getValue(p)));
        return map;
    }

    private Optional<Manifest> getManifest() {
        try {
            return Optional.of(new Manifest(servletContext.getResourceAsStream("/META-INF/MANIFEST.MF")));
        } catch (Exception e) {
            log.warn("Problem reading MANIFEST.MF");
        }
        return Optional.empty();
    }
}
