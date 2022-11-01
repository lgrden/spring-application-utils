package io.wegetit.sau.data;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class QueryUtils {
    private QueryUtils(){}

    public static String readQuery(String file) {
        try {
            InputStream is = QueryUtils.class.getClassLoader().getResourceAsStream("sql/" + file);
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Problem reading sql/" + file + " from classpath.", e);
        }
    }
}
