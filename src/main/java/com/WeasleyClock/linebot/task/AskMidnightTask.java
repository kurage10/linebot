
package com.weasleyclock.linebot.task;

import java.util.ArrayList;
import java.util.List;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.weasleyclock.linebot.annotation.LinebotTask;
import com.weasleyclock.linebot.code.BooleanTaskBranchCode;
import com.weasleyclock.linebot.code.WellKnownTaskBranchCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LinebotTask(name = "ASK_MIDNIGHT")
public class AskMidnightTask extends BaseTask {
    public AskMidnightTask(){}
    private final Logger LOGGER = LoggerFactory.getLogger(AskMidnightTask.class);

    @Override
    public Message start(TaskRequestDto request) {
        LOGGER.info("=== Start Ask Midnight ===");
        return createMidnightConfirmMessage();
    }

    @Override
    public WellKnownTaskBranchCode execute(TaskRequestDto request) {
        LOGGER.info("=== Execute Ask Midnight ===");
        return WellKnownTaskBranchCode.SEQUENTIAL; 
    }
    private TemplateMessage createMidnightConfirmMessage(){
        List<Action> actions = new ArrayList<>();
        actions.add(new PostbackAction("yes", BooleanTaskBranchCode.USER_REPLY_YES.toString(), "Yes"));
        actions.add(new PostbackAction("no", BooleanTaskBranchCode.USER_REPLY_NO.toString(), "No"));
        return new TemplateMessage("お泊まりかどうか", new ButtonsTemplate(null, "お泊まりの有無", "家には帰ってくる？", actions));
    }
    /*
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
    }*/
}