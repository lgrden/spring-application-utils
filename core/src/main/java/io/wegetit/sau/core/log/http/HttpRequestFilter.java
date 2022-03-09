package io.wegetit.sau.core.log.http;

public interface HttpRequestFilter {
    boolean logUrl(String url, long time, int status);
}
