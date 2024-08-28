package com.projeto.socket.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor != null) {
            switch (accessor.getCommand()) {
                case CONNECT:
                    System.out.println("Nova conexão WebSocket estabelecida.");
                    break;
                case DISCONNECT:
                    System.out.println("Conexão WebSocket desconectada.");
                    break;
                case null:
                    break;
                default:
                    break;
            }
        }

        return message;
    }
}
