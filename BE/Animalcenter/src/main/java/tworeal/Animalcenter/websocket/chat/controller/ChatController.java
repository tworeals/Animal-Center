package tworeal.Animalcenter.websocket.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tworeal.Animalcenter.websocket.chat.dto.ChatRoom;
import tworeal.Animalcenter.websocket.chat.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat") // RESTful API
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllChatRoom() {
        return chatService.findAllRoom();
    }
}
