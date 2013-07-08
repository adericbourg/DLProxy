package controllers;

import java.net.InetSocketAddress;
import java.net.Proxy;

import play.Play;

import com.google.common.base.Strings;

/**
 * @author adericbourg
 */
public class ProxyConfiguration {

    private static final String GLOBAL_PROXY_ADDRESS = "network.proxy.address";
    private static final String GLOBAL_PROXY_PORT = "network.proxy.port";
    private static final String PROTOCOL_PROXY_ADDRESS = "network.proxy.%s.address";
    private static final String PROTOCOL_PROXY_PORT = "network.proxy.%s.port";

    static boolean needProxy(String url) {
        String protocol = extractProtocol(url);
        if (Strings.isNullOrEmpty(protocol)) {
            return false;
        }
        return !Strings.isNullOrEmpty(getProxyAddress(protocol));
    }

    static Proxy getProxy(String url) {
        String protocol = extractProtocol(url);
        return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(getProxyAddress(protocol), getProxyPort(protocol)));
    }

    private static String extractProtocol(String url) {
        if (url == null) {
            return null;
        }
        return url.split(":")[0];
    }

    private static String getProxyAddress(String protocol) {
        String protocolProxyAddress = Play.application().configuration().getString(String.format(PROTOCOL_PROXY_ADDRESS, protocol));
        if (Strings.isNullOrEmpty(protocol)) {
            return Play.application().configuration().getString(GLOBAL_PROXY_ADDRESS);

        }
        return protocolProxyAddress;
    }

    private static int getProxyPort(String protocol) {
        String port = Play.application().configuration().getString(String.format(PROTOCOL_PROXY_PORT, protocol));
        if (Strings.isNullOrEmpty(port)) {
            port = Play.application().configuration().getString(GLOBAL_PROXY_PORT);

        }
        return Integer.valueOf(port).intValue();
    }
}
