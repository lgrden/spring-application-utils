package io.wegetit.sau.logger;

public class HttpRequestFilter {

    public boolean logUrl(String url, long time, int status) {
        return true;
    }
}
