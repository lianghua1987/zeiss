package com.hua.interview.zeiss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class WsClient {

    private static final Logger logger = LoggerFactory.getLogger(WsClient.class);

    public static WebSocketClient client;

    @Bean
    public void webScoketClient() {
        try {
            client = new WebSocketClient(new URI("ws://localhost:8081/chat"), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    logger.info("Connection established");
                }

                @Override
                public void onMessage(String msg) {
                    logger.info("Received message - " + msg);
                    if (msg.equals("over")) {
                        client.close();
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    logger.info("Connection closed");
                }

                @Override
                public void onError(Exception e) {
                    logger.error("Connection error: ", e);
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        client.connect();
        while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            logger.info("creating connection");
        }
    }
}
