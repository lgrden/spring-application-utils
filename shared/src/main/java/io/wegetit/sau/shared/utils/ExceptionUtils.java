package io.wegetit.sau.shared.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionUtils {
    private ExceptionUtils() {}

    public static String convertToLineTrace(Throwable t) {
        List<String> stack = new ArrayList<>();
        while (t != null) {
            stack.add("[" + t.getClass().getName() + ":" + t.getMessage() + "]");
            t = t.getCause();
        }
        return stack.stream().collect(Collectors.joining(" / "));
    }
}
