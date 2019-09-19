package com.WeasleyClock.linebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class LinebotApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinebotApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LinebotApplication.class, args);
	}

	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event){
		LOGGER.info("event: "+event);
		final String originalMessageText = event.getMessage().getText();
		return new TextMessage(originalMessageText);
	}
	@EventMapping
    public void handleDefaultMessageEvent(Event event) {
		LOGGER.info("event: "+event);
	}
}
