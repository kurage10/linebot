package com.weasleyclock.linebot.entity;

import org.apache.ibatis.type.Alias;

@Alias("ActivityDefinitionEntity")
public class ActivityDefinitionEntity{
    private int id;
    private String name;

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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
