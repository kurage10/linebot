package com.weasleyclock.linebot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.weasleyclock.linebot.code.BooleanTaskOptionCode;
import com.weasleyclock.linebot.entity.ActivityInstanceEntity;
import com.weasleyclock.linebot.entity.TaskEntity;
import com.weasleyclock.linebot.entity.UserEntity;
import com.weasleyclock.linebot.exception.TaskNotFoundException;
import com.weasleyclock.linebot.repository.ActivityInstanceRepository;
import com.weasleyclock.linebot.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskExecService{
    
    private static Logger LOGGER = LoggerFactory.getLogger(TaskExecService.class);
    @Autowired
    private ActivityInstanceRepository activityInstanceRepository;
    /*
    public Message startTask(Event event){
        String userId = event.getSource().getSenderId();
        

        List<ActivityInstanceEntity> userActivity = activityInstanceRepository.selectInstanceByUserId(userId);

    }*/
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Message executeNextTask(ActivityInstanceEntity instance, PostbackEvent event) throws TaskNotFoundException {
        
        int currentTaskNum = instance.getCurrentTaskNum();
        int activityInstanceId = instance.getId();
        int activityDefinitionId = instance.getActivityId();
        LOGGER.info("[start] retrieve Task"); 
        List<TaskEntity> nextTasks = activityInstanceRepository.selectFollowingTasks(activityDefinitionId, currentTaskNum);
        LOGGER.info(
            "[end] retrieve task" + 
            nextTasks.toString() + 
            " currentTaskNum: " + currentTaskNum + 
            " activityInstanceId: "+ activityInstanceId); 
        // TODO: こいつが各インスタンスの有効無効の判定/更新まで行っているからどうにかした方がいいかも。
        if (nextTasks.isEmpty()){
            LOGGER.info("[start] Not Found Task"); 
            throw new TaskNotFoundException();
        }
        // 本当はここでなにがしか処理をするんだろうね
        TaskEntity taskEntity = nextTasks.get(0);
        LOGGER.info("[start] exec Task");
        switch(taskEntity.getCode()) {
            case ASK_MIDNIGHT:
                return executeAskMidnightTask(instance, event);
            case ASK_DINNER:
                return executeAskDinnerTask(instance, event);
            default:
                return createUnknownCommandMessage();
        }

    }
   
    private static TemplateMessage createMidnightConfirmMessage(){
        List<Action> actions = new ArrayList<>();
        actions.add(new PostbackAction("yes", BooleanTaskOptionCode.USER_REPLY_YES.toString(), "Yes"));
        actions.add(new PostbackAction("no", BooleanTaskOptionCode.USER_REPLY_NO.toString(), "No"));
        return new TemplateMessage("お泊まりかどうか", new ButtonsTemplate(null, "お泊まりの有無", "家には帰ってくる？", actions));
    }
    private static TextMessage createUnknownCommandMessage(){
        return new TextMessage("すみません。よくわかりません。");
    }

    // TODO　タスクって実はイベント発火で抽象化すべきなんじゃないか
    private Message executeAskDinnerTask(ActivityInstanceEntity instance, PostbackEvent event){
        int currentTaskNum = instance.getCurrentTaskNum();
        int activityInstanceId = instance.getId();

        BooleanTaskOptionCode taskOptionCode = BooleanTaskOptionCode.valueOf(event.getPostbackContent().getData());
        switch(taskOptionCode){
            case USER_REPLY_YES:
                LOGGER.info("晩御飯がいる.");        
            break;
            case USER_REPLY_NO:  
                LOGGER.info("晩御飯がいらない");        
            break;
        }
        activityInstanceRepository.updateCurrentTaskById(activityInstanceId, currentTaskNum+1);
        return createMidnightConfirmMessage(); 
    }
    private Message executeAskMidnightTask(ActivityInstanceEntity instance, PostbackEvent event){
        int currentTaskNum = instance.getCurrentTaskNum();
        int activityInstanceId = instance.getId();

        BooleanTaskOptionCode code = BooleanTaskOptionCode.valueOf(event.getPostbackContent().getData());
        switch(code){
            case USER_REPLY_YES:
                LOGGER.info("家に帰ってくる.");        
            break;
            case USER_REPLY_NO:  
                LOGGER.info("家に帰ってこない。");        
            break;
        }

        activityInstanceRepository.updateCurrentTaskById(activityInstanceId, currentTaskNum+1);
        activityInstanceRepository.updateIsActiveById(activityInstanceId, false);
        return new TextMessage("質問にお答えいただきありがとうございます！");
    }

}