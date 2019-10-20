package com.weasleyclock.linebot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MemberExistenceService {

    @Async
    public void registerMemberLocation(){ 
        try{
            TimeUnit.SECONDS.sleep(60);
        }catch (InterruptedException e) {
        }
    }     

    

}