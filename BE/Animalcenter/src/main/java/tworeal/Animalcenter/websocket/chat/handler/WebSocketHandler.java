package tworeal.Animalcenter.websocket.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tworeal.Animalcenter.websocket.chat.dto.ChatMessage;
import tworeal.Animalcenter.websocket.chat.dto.ChatRoom;
import tworeal.Animalcenter.websocket.chat.service.ChatService;

/**
 * WebSocketHandler v1
 *
 * socket 통신 : 서버 1 : 클라이언트 N 관계
 * 서버에서 여러 클라이언트가 발송한 메세지를 받아 처리할 Handler 필요
 * TextWebSocketHandler를 상속받아 작성
 * 클라이언트로부터 받은 메세지를 console.log에 출력 -> 클라이언트에게 환영 메세지 전송
 */

@RequiredArgsConstructor
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
//        TextMessage textMessage = new TextMessage("채팅 서버에 오신것을 환영합니다.");
//        session.sendMessage(textMessage);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
}
