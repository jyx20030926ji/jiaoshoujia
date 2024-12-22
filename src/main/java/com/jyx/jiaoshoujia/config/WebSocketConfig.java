package com.jyx.jiaoshoujia.config;



import com.jyx.jiaoshoujia.interceptor.TokenHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private  final WebSocketHandler webSocketHandler;
    private final TokenHandshakeInterceptor tokenHandshakeInterceptor;
    public WebSocketConfig(WebSocketHandler webSocketHandler,TokenHandshakeInterceptor tokenHandshakeInterceptor)
    {
        this.webSocketHandler=webSocketHandler;
        this.tokenHandshakeInterceptor=tokenHandshakeInterceptor;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler,"/ws").setAllowedOrigins("*").addInterceptors(tokenHandshakeInterceptor);
    }
}
