package io.wegetit.sau.log.http;

public class HttpRequestFilter {

    public boolean logUrl(String url, long time, int status) {
        return true;
    }
}
