package com.example.states;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.fluentd.logger.FluentLogger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageHandler {
    private static final FluentLogger LOG = FluentLogger.getLogger("app", "127.0.0.1", 24224);
    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getName());

    public void handleMessage(Update update) {
        LOGGER.info("Received update: " + "update");

        if (update.hasMessage() && update.getMessage().getFrom() != null) {
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();

            LOGGER.info("Processing message from user: " + "userName");


            Map<String, Object> data = new HashMap<>();
            data.put("UserID", userId);
            data.put("UserName", userName);
            data.put("Message", update.getMessage().getText());  // для тестування

            LOG.log("UserData", data);
        }
    }
}
