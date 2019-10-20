package com.weasleyclock.linebot.entity;

import org.apache.ibatis.type.Alias;

@Alias("ActivityDefinitionEntity")
public class ActivityDefinitionEntity{
    private int id;
    private String name;
    private int firstTask;

    public ActivityDefinitionEntity() {
    }

    public ActivityDefinitionEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getFirstTask() {
        return this.firstTask;
    }

    public void setFirstTask(int firstTask) {
        this.firstTask = firstTask;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
