package com.example.metrics;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

@Component
public class MetricsConfiguration {

    private HTTPServer server;

    public static final Counter messagesReceived = Counter.build()
            .name("telegram_messages_received_total")
            .help("Total messages received")
            .register();

    @PostConstruct
    public void init() throws IOException {
        // Start Prometheus HTTP server to expose metrics
        server = new HTTPServer(9092);

        // Register JVM default metrics
        DefaultExports.initialize();
    }

    @PreDestroy
    public void shutdown() {
        // Stop the Prometheus HTTP server
        if (server != null) {
            server.close();
        }
    }
}
