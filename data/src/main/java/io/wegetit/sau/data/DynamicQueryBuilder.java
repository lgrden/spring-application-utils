package io.wegetit.sau.data;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicQueryBuilder {

    private String query;
    private Map<String, String> params = new HashMap<>();

    private DynamicQueryBuilder() {}

    public static DynamicQueryBuilder builder() {
        return new DynamicQueryBuilder();
    }

    public DynamicQueryBuilder query(String query) {
        this.query = query;
        return this;
    }

    public DynamicQueryBuilder param(String name, String value) {
        params.put(name, value);
        return this;
    }

    public String build() {
        if (StringUtils.isEmpty(query)) {
            throw new IllegalStateException("Query not specified.");
        }
        List<String> allParameters = collectAllParameters();
        StringBuilder sb = new StringBuilder();
        Arrays.asList(query.split("\\r?\\n")).stream().forEach(line -> {
            if (!removeLine(allParameters, line)) {
                line = StringUtils.trim(line);
                line = applyLineParameters(line);
                sb.append(line).append(" ");
            }
        });
        return StringUtils.trim(sb.toString());
    }

    private List<String> collectAllParameters() {
        List<String> list = new ArrayList<>();
        Matcher matcher = Pattern.compile("--\\w+").matcher(query);
        while (matcher.find()) {
            list.add(StringUtils.substring(matcher.group(), 2));
        }
        return list;
    }

    private boolean removeLine(List<String> allParameters, String line) {
        Iterator<String> it = allParameters.iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (line.contains("--" + key) && !params.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    private String applyLineParameters(String line) {
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (line.contains(":" + key)) {
                line = line.replace(":" + key, params.get(key));
                line = line.replace("--" + key, "");
            }
        }
        return line;
    }
}
