package com.projeto.socket.Services;

import com.projeto.socket.Models.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageToChat(String chatId, String messageContent) {
        // Formata o tópico para o chat específico
        String destination = "/topic/chat/" + chatId;
        System.out.println(destination);
        // Cria a mensagem (pode ser um objeto mais complexo, se necessário)
        ChatMessage chatMessage = new ChatMessage(messageContent);

        // Envia a mensagem para o tópico

        messagingTemplate.convertAndSend(destination, chatMessage);
    }
}
