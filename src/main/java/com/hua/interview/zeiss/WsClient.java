package com.hua.interview.zeiss;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hua.interview.zeiss.api.entity.Message;
import com.hua.interview.zeiss.api.entity.Payload;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Configuration
public class WsClient {

    private static final Logger logger = LoggerFactory.getLogger(WsClient.class);

    public static WebSocketClient client;
    private Map<String, Payload> machines = new ConcurrentHashMap<>();

    @Bean
    public void webScoketClient() {
        try {
            client = new WebSocketClient(new URI("ws://machinestream.herokuapp.com/ws"), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    logger.info("Connection established");
                }

                @Override
                public void onMessage(String msg) {
                    logger.info("Received message - " + msg);
                    Gson gson = new GsonBuilder().create();
                    try {
                        Message message = gson.fromJson(msg, Message.class);
                        machines.put(message.getPayload().getMachineId(), message.getPayload());
                    } catch (Exception e) {
                        logger.error("Error parsing json, please use the paste the one from the question", e);
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
            logger.info("Trying to connect");
        }

        new Thread(() -> {
            try {
                logger.info("Will timeout after 60 seconds...");
                TimeUnit.SECONDS.sleep(60);
                if (client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                    client.close();
                    logger.info("Timeed out after 60 seconds, application closed");
                    System.exit(1);
                }
            } catch (InterruptedException e) {
                logger.info("InterruptedException", e);
            }

        }).start();
    }

    @Bean
    public Map<String, Payload> machines() {
        return machines;
    }
}
