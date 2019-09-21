package com.weasleyclock.linebot.handler;


import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import com.weasleyclock.linebot.service.MemberExistenceService;;




@LineMessageHandler
public class EchoHandler{

	@Autowired
	private MemberExistenceService memberExistenceService;
	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event){
		//memberExistenceService.registerMemberLocation();
		final String message = event.getMessage().getText();
		if(message.startsWith("schedule")){
			return memberExistenceService.createDinnerConfirmMessage();
		}else{
			return new TextMessage(message);
		} 
	}
	@EventMapping
    public void handleDefaultMessageEvent(Event event) {
	}
}