package com.thecodingcult.chat_service.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class WebSocketChatClient {

    public static void main(String[] args) {
        // Default values
        String url = "ws://localhost:8083/ws-chat";
        String groupId = "defaultGroup";
        String token;

        // Fetch token programmatically
        token = fetchJwtToken();
        if (token == null) {
            System.err.println("Failed to fetch JWT token. Exiting.");
            return;
        }

        // Log connection details
        System.out.println("Connecting to WebSocket URL: " + url);
        System.out.println("Using Group ID: " + groupId);
        System.out.println("Authorization Token: Bearer " + token);

        // Create WebSocket client
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);

        // Configure message converter
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        // Configure scheduler for heartbeats
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();
        stompClient.setTaskScheduler(taskScheduler);

        // Add Authorization header
        WebSocketHttpHeaders httpHeaders = createWebSocketHeaders(token);

        // Connect to WebSocket server
        StompSessionHandler sessionHandler = new CustomStompSessionHandler(groupId);
        try {
            stompClient.connectAsync(url, httpHeaders, sessionHandler).get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Failed to connect: " + e.getMessage());
        }
    }

    private static WebSocketHttpHeaders createWebSocketHeaders(String jwtToken) {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken);
        return headers;
    }

    private static String fetchJwtToken() {
        RestTemplate restTemplate = new RestTemplate();
        String loginUrl = "http://localhost:8080/api/auth/signin";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "san");
        loginRequest.put("password", "san123");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(loginRequest, headers);

        try {
            // Expecting a raw string response (JWT token)
            ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody(); // Return the raw JWT token
            } else {
                System.err.println("Error fetching JWT token: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error fetching JWT token: " + e.getMessage());
        }
        return null;
    }

    private static class CustomStompSessionHandler extends StompSessionHandlerAdapter {
        private final String groupId;

        public CustomStompSessionHandler(String groupId) {
            this.groupId = groupId;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println("Connected to WebSocket server");

            // Subscribe to a dynamic topic
            session.subscribe("/topic/group/" + groupId, new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return String.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    System.out.println("Received: " + payload);
                }
            });

            // Send a message to a dynamic destination
            session.send("/app/chat/" + groupId, "Hello, group " + groupId + "!");
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            System.err.println("Error: " + exception.getMessage());
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            System.err.println("Transport error: " + exception.getMessage());
        }
    }
}