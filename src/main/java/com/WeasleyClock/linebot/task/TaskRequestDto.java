
package com.weasleyclock.linebot.task;

import java.net.URI;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.postback.PostbackContent;

public class TaskRequestDto {
    private String userId;
    private String text;
    private URI uri;
    
    public TaskRequestDto(String userId, PostbackContent content) {
        //this.userId = event.getSource().getSenderId(); 
        this.userId = userId;
        this.text = content.getData();
    }
    public TaskRequestDto(String userId, ImageMessageContent content) {
        this.userId = userId; 
        this.uri = content.getContentProvider().getOriginalContentUrl();
    }
    public TaskRequestDto(String userId, TextMessageContent content) {
        this.userId = userId;
        this.text = content.getText();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URI getUri() {
        return this.uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }


    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", text='" + getText() + "'" +
            ", uri='" + getUri() + "'" +
            "}";
    }




}