
package com.weasleyclock.linebot.repository;

import com.weasleyclock.linebot.entity.TaskEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskRepository {
    public TaskEntity selectById(int id);
}