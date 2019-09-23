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
    public int insert(@Param("activityId") int activityId, @Param("userId") String userId);
    public int updateIsActiveById(@Param("id") int id, @Param("isActive") boolean isActive);
    public int updateCurrentTaskById(@Param("id") int id, @Param("nextTaskNum") int nextTaskNum);
    public List<TaskEntity> selectFollowingTasks(@Param("activityId") int activityId, @Param("taskNum") int taskNum);
}
