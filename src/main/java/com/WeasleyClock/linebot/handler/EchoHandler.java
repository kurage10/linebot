package com.weasleyclock.linebot.handler;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.weasleyclock.linebot.service.MemberLocationService;

@LineMessageHandler
public class EchoHandler{

	@Autowired
	private MemberLocationService memberLocationService;
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoHandler.class);
	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event){
		LOGGER.info("event: "+event);
		memberLocationService.registerMemberLocation();
		final String originalMessageText = event.getMessage().getText();
		return new TextMessage(originalMessageText);
	}
	@EventMapping
    public void handleDefaultMessageEvent(Event event) {
		LOGGER.info("event: "+event);
	}
}