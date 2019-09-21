package com.weasleyclock.linebot.handler;

import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;


@LineMessageHandler
public class MemberExistenceMessageHandler {
    @EventMapping
	public Message handleDinnerConfirmMessageEvent(PostbackEvent event){
        return new TextMessage("登録しますね");
	}
}