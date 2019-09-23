package com.weasleyclock.linebot.handler;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.weasleyclock.linebot.code.MessagePriorityCode;
import com.weasleyclock.linebot.service.ActivityService;




@LineMessageHandler
public class EchoHandler{
	private static int PRIORITY_REPLIED_MESSAGE = 10;
	private static int PRIORITY_COMMON_MESSAGE = 5;
	
	
	@Autowired
	private ActivityService activityService;

	//@Autowired
	//private TaskService taskService;

	@EventMapping(priority = 5)
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event){
		return activityService.startActivity(event);
	}
}