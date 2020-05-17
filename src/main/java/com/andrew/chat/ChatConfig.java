package com.andrew.chat;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

/**
 * ChatConfig Class
 *
 * @author andrew
 * @date 2019/8/1
 */
public class ChatConfig implements ServerApplicationConfig {
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return null;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {
        System.out.println("ChatConfigSize="+set.size());
        return set;
    }
}
