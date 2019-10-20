package com.weasleyclock.linebot.task;

import java.util.ArrayList;
import java.util.List;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.weasleyclock.linebot.annotation.LinebotTask;
import com.weasleyclock.linebot.code.BooleanTaskBranchCode; // 今回はYES NOで違いがない
import com.weasleyclock.linebot.code.WellKnownTaskBranchCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LinebotTask(name = "ASK_DINNER")
public class AskDinnerTask extends BaseTask {
    private final Logger LOGGER = LoggerFactory.getLogger(AskDinnerTask.class);
    public AskDinnerTask(){}
    @Override
    public Message start(TaskRequestDto request) {
        LOGGER.info("=== Start Ask Dinner ===");
        return createDinnerConfirmMessage();
    }

    @Override
    public WellKnownTaskBranchCode execute(TaskRequestDto request) {
        LOGGER.info("=== Execute Ask Dinner ===");
        return WellKnownTaskBranchCode.SEQUENTIAL; 
    }
    /*
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
    }*/
    private TemplateMessage createDinnerConfirmMessage(){
        List<Action> actions = new ArrayList<>();
        actions.add(new PostbackAction("yes", BooleanTaskBranchCode.USER_REPLY_YES.toString(), BooleanTaskBranchCode.USER_REPLY_YES.toString()));
        actions.add(new PostbackAction("no", BooleanTaskBranchCode.USER_REPLY_NO.toString(), BooleanTaskBranchCode.USER_REPLY_NO.toString()));
        return new TemplateMessage("夕飯の有無", new ButtonsTemplate(null, "夕飯の有無", "晩御飯は必要？", actions));
    }
}