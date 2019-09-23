package com.weasleyclock.linebot.service;

import java.util.ArrayList;
import java.util.List;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.weasleyclock.linebot.code.BooleanTaskOptionCode;
import com.weasleyclock.linebot.entity.ActivityDefinitionEntity;
import com.weasleyclock.linebot.entity.ActivityInstanceEntity;
import com.weasleyclock.linebot.exception.ActivityNotFoundException;
import com.weasleyclock.linebot.exception.WeasleyClockAppException;
import com.weasleyclock.linebot.repository.ActivityInstanceRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    private ActivityInstanceRepository activityInstanceRepository;

    @Autowired
    private TaskExecService taskExecService;

    public Message executeTask(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getSenderId();
        LOGGER.info("[result]: " + userId);
        List<ActivityInstanceEntity> userActivity = activityInstanceRepository.selectInstanceByUserId(userId);

        Message message = null;
        // TODO ここ、インスタンスを作る処理ができていない
        try {
            if (userActivity.isEmpty()) {
                LOGGER.info("[start]: " + "start create instance");
                userActivity = createActivityInstance(event);
                return createDinnerConfirmMessage(); 
            }
            LOGGER.info("[start]: " + "start return message");
            for (ActivityInstanceEntity instance : userActivity) {
                message = taskExecService.executeNextTask(instance, event);
            }
        }
        catch (WeasleyClockAppException exception) {
            message = new TextMessage(exception.getMessage());
        } 
        return message;
    }

    private List<ActivityInstanceEntity> createActivityInstance(MessageEvent<TextMessageContent>  event) throws ActivityNotFoundException{
        
        String activityName = event.getMessage().getText();
        String userId = event.getSource().getSenderId();

        ActivityDefinitionEntity activityDef = activityInstanceRepository.selectDefinition(activityName);
        if(activityDef == null) {
            throw new ActivityNotFoundException(); 
        }
        activityInstanceRepository.insert(activityDef.getId(), userId);

        return activityInstanceRepository.selectInstanceByUserId(userId);
        
    } 
     // TODO リファクタリング。なんか三つも同じようなメッセージ抱えてもしょうがない。
     private static TemplateMessage createDinnerConfirmMessage(){
        List<Action> actions = new ArrayList<>();
        actions.add(new PostbackAction("yes", BooleanTaskOptionCode.USER_REPLY_YES.toString(), BooleanTaskOptionCode.USER_REPLY_YES.toString()));
        actions.add(new PostbackAction("no", BooleanTaskOptionCode.USER_REPLY_NO.toString(), BooleanTaskOptionCode.USER_REPLY_NO.toString()));
        return new TemplateMessage("夕飯の有無", new ButtonsTemplate(null, "夕飯の有無", "晩御飯は必要？", actions));
    }
}