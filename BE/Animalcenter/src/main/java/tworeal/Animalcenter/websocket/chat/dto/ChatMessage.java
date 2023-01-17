package tworeal.Animalcenter.websocket.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * ChatMessage
 *
 * 채팅 메세지 주고받기위한 Dto
 */

@Getter
@Setter
public class ChatMessage {
    private MessageType type; // 메세지 타입
    private String roomId; // 채팅방 번호
    private String sender; // 보낸사람
    private String message;

    // 메세지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK;
    }
}
