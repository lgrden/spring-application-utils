package io.wegetit.sau.core;

import java.io.PrintStream;

public class SystemOutUtils {
    private final static PrintStream DEFAULT_OUT = System.out;

    private SystemOutUtils() {}

    public static void setSystemOut(PrintStream out) {
        System.setOut(out);
    }

    public static void applyDefaultSystemOut() {
        System.setOut(DEFAULT_OUT);
    }
}
