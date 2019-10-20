package com.weasleyclock.linebot.entity;

import com.weasleyclock.linebot.code.TaskCode;

import org.apache.ibatis.type.Alias;

@Alias("TaskEntity")
public class TaskEntity{
    private int id;
    private String code;
    private String serviceName;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TaskEntity id(int id) {
        this.id = id;
        return this;
    }

    public TaskEntity code(String code) {
        this.code = code;
        return this;
    }

    public TaskEntity(int id, String code, String serviceName) {
        this.id = id;
        this.code = code;
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }

}