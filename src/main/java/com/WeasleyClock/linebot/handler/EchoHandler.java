package com.weasleyclock.linebot.handler;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.weasleyclock.linebot.aspect.LoggingAspect;
import com.weasleyclock.linebot.service.ActivityService;
import com.weasleyclock.linebot.task.TaskRequestDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;




@LineMessageHandler
public class EchoHandler{
	private final Logger LOGGER = LoggerFactory.getLogger(EchoHandler.class);
	private static int PRIORITY_REPLIED_MESSAGE = 10;
	private static int PRIORITY_COMMON_MESSAGE = 5;
	
	
	@Autowired
	private ActivityService activityService;

	//@Autowired
	//private TaskService taskService;
	
	@EventMapping(priority = 10)
	public Message handleImageMessageEvent(MessageEvent<ImageMessageContent> event){
		LOGGER.info("== recieve image content ==");

		TaskRequestDto request = new TaskRequestDto(getUserId(event), event.getMessage());
		return  activityService.executeTaskInActivity(request);
	}
	
	@EventMapping(priority = 10)
	public Message handleReplyEvent(PostbackEvent event){
		LOGGER.info("== recieve postback content ==");
		TaskRequestDto request = new TaskRequestDto(getUserId(event), event.getPostbackContent());
		return  activityService.executeTaskInActivity(request);
	}

	@EventMapping(priority = 5)
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event){
		LOGGER.info("== recieve text content ==");
		TaskRequestDto request = new TaskRequestDto(getUserId(event), event.getMessage());
		return  activityService.executeTaskInActivity(request);
	}
	private String getUserId(Event event){
		return event.getSource().getSenderId();
	}
	
}