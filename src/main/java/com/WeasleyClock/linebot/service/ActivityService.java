package com.weasleyclock.linebot.service;

import java.util.List;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.weasleyclock.linebot.code.TaskBranchCode;
import com.weasleyclock.linebot.entity.ActivityDefinitionEntity;
import com.weasleyclock.linebot.entity.ActivityInstanceEntity;
import com.weasleyclock.linebot.entity.TaskEntity;
import com.weasleyclock.linebot.exception.ActivityNotFoundException;
import com.weasleyclock.linebot.exception.WeasleyClockAppException;
import com.weasleyclock.linebot.repository.ActivityInstanceRepository;
import com.weasleyclock.linebot.task.TaskRequestDto;

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
    public <T> Message executeTaskInActivity(TaskRequestDto request){
        // こいつはko
        //String userId = event.getSource().getSenderId();
        LOGGER.info("user: " + request.getUserId());
        
        // 実行中のactivityInstanceを取得
        try{
            List<ActivityInstanceEntity> userActivities = activityInstanceRepository.selectInstanceByUserId(request.getUserId()); 
            if(userActivities.isEmpty()){
                LOGGER.info("create activity instance.");
                int firstTask = createActivityInstance(request);
                LOGGER.info("first task ID: " + firstTask);
                return taskExecService.startTask(firstTask, request); 
            } else {
                // タスクの実行
                LOGGER.info("execute task on an activity instance.");
                ActivityInstanceEntity instance = userActivities.get(0);
                int currentTaskId = instance.getCurrentTaskId();
                TaskBranchCode branchCode= taskExecService.executeTask(currentTaskId, request); 
                
                // 次のタスクの更新
                LOGGER.info("update next task - current: " + currentTaskId + " branch: " + branchCode.toString());
                TaskEntity nextTask = activityInstanceRepository.selectFollowingTasks(instance.getActivityId(), currentTaskId, branchCode.toString());
                
                if(nextTask == null) {
                    activityInstanceRepository.updateIsActiveById(instance.getId(), false);
                    return new TextMessage("アクティビティが完了しました.");
                }
                
                activityInstanceRepository.updateCurrentTaskById(instance.getId(), nextTask.getId());
                return taskExecService.startTask(nextTask.getId(), request); 
            }
        } catch (WeasleyClockAppException exception){
            return new TextMessage(exception.getMessage()); 
        }
        
    }

    private int createActivityInstance(TaskRequestDto request) throws ActivityNotFoundException{
        
        ActivityDefinitionEntity activityDef = activityInstanceRepository.selectDefinition(request.getText());
        if(activityDef == null) {
            throw new ActivityNotFoundException(); 
        }
        activityInstanceRepository.insert(activityDef.getId(), request.getUserId(), activityDef.getFirstTask());

        return activityDef.getFirstTask();
    } 
  
}