package com.weasleyclock.linebot.handler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.weasleyclock.linebot.service.ActivityService;




@LineMessageHandler
public class EchoHandler{

	
	
	@Autowired
	private ActivityService activityService;
	@EventMapping
	public Message handleReplyEvent(PostbackEvent event){
		return taskService.executeFollowingTask(event);
	}
	
	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event){
		return activityService.instanciate(event);
	}
}