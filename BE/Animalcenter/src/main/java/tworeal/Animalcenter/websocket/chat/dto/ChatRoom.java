package tworeal.Animalcenter.websocket.chat.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import tworeal.Animalcenter.websocket.chat.service.ChatService;

import java.util.HashSet;
import java.util.Set;

/**
 * ChatRoom
 *
 * 입장한 클라이언트의 정보 필요하므로 WebSocketSession 정보리스트를 필드로 갖음
 * 채팅방에는 입장, 채팅 두가지의 기능이 있으므로 handleAction을 통해 분기처리
 *
 * 채팅방 입장 시 ChatRoom의 session 정보에 클라이언트의 session리스트 추가 후
 * ChatRoom에 메세지 도착 시 ChatRoom이 모든 session에 메세지 발송
 */

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessage message, ChatService service) {
        if (message.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        sendMessage(message, service);
    }

    public <T> void sendMessage(T message, ChatService service) {
        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
    }
}
