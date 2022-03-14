package io.wegetit.sau.core.system.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@Builder
@AllArgsConstructor
public class SystemConnection {

    private String serverName;
    private String serverAddress;
    private String serverPort;

    public static SystemConnection of(Environment environment) {
        return SystemConnection.builder()
                .serverName(getLocalHostName())
                .serverAddress(getLocalHostAddress())
                .serverPort(environment.getProperty("server.port"))
                .build();
    }

    private static String getLocalHostName() {
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            return localAddress.getHostName();
        } catch (UnknownHostException e) {
            return "N/A";
        }
    }

    private static String getLocalHostAddress() {
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            return localAddress.getHostAddress();
        } catch (UnknownHostException e) {
            return "N/A";
        }
    }
}
