package com.xxxx.yeb.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 添加STOMP协议的端点(addEndpoint("/ws/ep")),ebSocket客户端或SockJS客户端连接服务端访问的地址
     * 添加允许跨域访问:addInterceptors(new HttpSessionHandshakeInterceptor())
     * 指定端点使用SockJS协议:withSockJS()
     * 
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").
                addInterceptors(new HttpSessionHandshakeInterceptor()).withSockJS();
    }

    /**
     *  服务器推送给客户端的前缀：setApplicationDestinationPrefixes
     *  客户端发送给服务端的前缀： setApplicationDestinationPrefixes
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/queue");
    }
}
