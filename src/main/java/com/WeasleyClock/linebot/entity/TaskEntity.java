package com.weasleyclock.linebot.entity;

import com.weasleyclock.linebot.code.TaskCode;

import org.apache.ibatis.type.Alias;

@Alias("TaskEntity")
public class TaskEntity{
    private int id;
    private TaskCode code;

    public TaskEntity() {
    }

    public TaskEntity(int id, String code) {
        this.id = id;
        this.code = TaskCode.valueOf(code);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskCode getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = TaskCode.valueOf(code);
    }

    public TaskEntity id(int id) {
        this.id = id;
        return this;
    }

    public TaskEntity code(TaskCode code) {
        this.code = code;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }

}