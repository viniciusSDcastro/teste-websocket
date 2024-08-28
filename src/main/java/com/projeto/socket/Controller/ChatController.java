package com.projeto.socket.Controller;

import com.projeto.socket.Models.ChatMessage;
import com.projeto.socket.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/test")
    public ResponseEntity<ChatMessage> lerMensagem(){
        System.out.println("teste");
        return  ResponseEntity.ok(new ChatMessage("mensagem dw teste!"));
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chat/123")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestParam String chatId, @RequestParam String message) {
        chatService.sendMessageToChat(chatId, message);
        return ResponseEntity.ok().build();
    }
}
