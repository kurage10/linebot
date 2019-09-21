package com.weasleyclock.linebot.domain;

import com.linecorp.bot.model.message.Message;

public interface Task {
    public Message execute();
}