package tworeal.Animalcenter.websocket.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import tworeal.Animalcenter.websocket.chat.handler.WebSocketHandler;

/**
 * WebSocketConfig
 *
 * 작성한 handler를 사용하여 WebSocket 활성화 시켜주는 Config
 */

@Configuration
@EnableWebSocket // WebSocket 활성화
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        // 웹소켓 접속 엔드포인트 : /ws/chat
        // 도메인이 달라도 접속 가능하도록 cors 와일드 카드 설정
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
