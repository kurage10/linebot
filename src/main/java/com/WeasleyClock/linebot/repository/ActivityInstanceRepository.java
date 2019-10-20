package com.weasleyclock.linebot.repository;

import java.util.List;

import com.weasleyclock.linebot.entity.ActivityDefinitionEntity;
import com.weasleyclock.linebot.entity.ActivityInstanceEntity;
import com.weasleyclock.linebot.entity.TaskEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ActivityInstanceRepository{
    public List<ActivityInstanceEntity> selectInstanceByUserId(String userId);
    public ActivityDefinitionEntity selectDefinition(String name);
    public int insert(@Param("activityId") int activityId, @Param("userId") String userId, @Param("firstTask") int firstTask);
    public int updateIsActiveById(@Param("id") int id, @Param("isActive") boolean isActive);
    public int updateCurrentTaskById(@Param("id") int id, @Param("nextTask") int nextTask);
    // TODO この次のタスクを実行する周りはDBの構造変えたからもう一度考えないといけない。
    public TaskEntity selectFollowingTasks(@Param("activityId") int activityId, @Param("taskId") int currentTaskId, @Param("branchCode") String branchCode);
}
