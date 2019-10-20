package com.weasleyclock.linebot.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.function.Predicate;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.weasleyclock.linebot.annotation.LinebotTask;
import com.weasleyclock.linebot.code.TaskBranchCode;
import com.weasleyclock.linebot.entity.TaskEntity;
import com.weasleyclock.linebot.exception.WeasleyClockAppException;
import com.weasleyclock.linebot.repository.ActivityInstanceRepository;
import com.weasleyclock.linebot.repository.TaskRepository;
import com.weasleyclock.linebot.task.BaseTask;
import com.weasleyclock.linebot.task.TaskRequestDto;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class TaskExecService{
    
    private static Logger LOGGER = LoggerFactory.getLogger(TaskExecService.class);
    @Autowired
    private ActivityInstanceRepository activityInstanceRepository;

    @Autowired
    private TaskRepository taskRepository;
    /*
    public Message startTask(Event event){
        String userId = event.getSource().getSenderId();
        

        List<ActivityInstanceEntity> userActivity = activityInstanceRepository.selectInstanceByUserId(userId);

    }*/
    //@Transactional(isolation = Isolation.READ_COMMITTED)

    public Message startTask(int taskId, TaskRequestDto request) throws WeasleyClockAppException {
       Class<? extends BaseTask> taskClass = searchTaskClass(taskId); 
       try{
           Constructor<? extends BaseTask> constructor = taskClass.getConstructor();
           BaseTask task = constructor.newInstance();
           LOGGER.info(" create instance - task: " + task.getClass().toString());
 
           Message startMessage = task.start(request);
           return startMessage; 
       } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
           throw new WeasleyClockAppException(ex);
       }
    }
    public TaskBranchCode executeTask(int taskId, TaskRequestDto request) throws WeasleyClockAppException {
       Class<? extends BaseTask> taskClass = searchTaskClass(taskId); 
       try{
           Constructor<? extends BaseTask> constructor = taskClass.getConstructor();
           BaseTask task = constructor.newInstance();
           LOGGER.info(" create instance - task: " + task.getClass().toString());
           
           return task.execute(request);

       } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
           throw new WeasleyClockAppException(ex);
       }
    }
    
    private Class<? extends BaseTask> searchTaskClass (int taskId) {
        LOGGER.info(" retrieve task ");
        TaskEntity taskEntity = taskRepository.selectById(taskId);
        Reflections reflections = new Reflections("com.weasleyclock.linebot.task");
        Set<Class<? extends BaseTask>> allTask = reflections.getSubTypesOf(BaseTask.class);

        Predicate<Class<? extends BaseTask>> checkTaskName = ( c -> {
            LinebotTask annotation = c.getAnnotation(LinebotTask.class);
            return taskEntity.getCode().equals(annotation.name()); 
        });

        return allTask.stream().filter(checkTaskName).findFirst().get();
    } 

    

    private static TextMessage createUnknownCommandMessage(){
        return new TextMessage("すみません。よくわかりません。");
    }

    // TODO　タスクって実はイベント発火で抽象化すべきなんじゃないか
    // TOOD このサービスにタスクの詳細まで漏れ出ているのがやだ
    // TODO なんかサービスいっぱいすぎて気持ちわりーなと思ったけど、これは多分コンテキストの分離が必要なやつだ....
    // TODO 確実にタスク管理 まではいいけど、タスクの実行処理自体は別のコンテキストなので。だからcleanupはどこかに共通で入れればいい。
    // 
    // TOOD このサービスにタスクの詳細まで漏れ出ているのがやだ

}