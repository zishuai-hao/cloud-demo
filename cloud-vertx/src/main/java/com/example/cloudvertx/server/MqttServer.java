package com.example.cloudvertx.server;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServerOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class MqttServer {
    public static final String MQTT_TOPIC = "hello_topic";
    private final Vertx vertx;

    @PostConstruct
    public void init() {
        log.info("init mqtt server");
        final MqttServerOptions mqttServerOptions = new MqttServerOptions();
        final io.vertx.mqtt.MqttServer mqttServer = io.vertx.mqtt.MqttServer.create(vertx, mqttServerOptions);
        mqttServer
                .endpointHandler(endpoint -> {
                    log.info("endpoint: {}", endpoint.toString());
                    endpoint.accept(true);
                    endpoint.publishHandler(publish -> {
                                // 服务质量
                                final MqttQoS mqttQoS = publish.qosLevel();
                                log.info("服务质量：{}", mqttQoS.value());
                                switch (mqttQoS) {
                                    // 至少一次消息
                                    case AT_LEAST_ONCE:
                                        endpoint.publishAcknowledge(publish.messageId());
                                        // 正好一次
                                    case EXACTLY_ONCE:
                                        endpoint.publishReceived(publish.messageId());
                                }
                                send(endpoint);
                            })
                            .publishReleaseHandler(endpoint::publishComplete);
//                            .publishAcknowledge()
                })
                .listen(result -> {
                    log.info("result: {}", result);
                });
    }

    private void send(MqttEndpoint endpoint) {
        Buffer payload = Buffer.buffer("server: hello world.");
        endpoint.publish(MqttServer.MQTT_TOPIC, payload, MqttQoS.AT_MOST_ONCE, false, false, s -> {
            if (s.succeeded()) {
                log.info("===>Server publish success: {}", s.result());
            } else {
                log.error("===>Server publish fail: ", s.cause());
            }
        });
    }
}
