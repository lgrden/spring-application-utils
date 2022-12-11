package io.wegetit.sau.shared.utils;

import org.apache.commons.lang3.StringUtils;

public class Obfuscator {

    private Obfuscator() {}

    public static String obfuscate(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        return StringUtils.leftPad("", value.length(), "*");
    }
}
