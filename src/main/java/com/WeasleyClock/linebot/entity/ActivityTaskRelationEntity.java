package com.weasleyclock.linebot.entity;

public class ActivityTaskRelationEntity {
    private int id;
    private int activityId;
    private int taskId;
    private int taskNum;


    public ActivityTaskRelationEntity() {
    }

    public ActivityTaskRelationEntity(int id, int activityId, int taskId, int taskNum) {
        this.id = id;
        this.activityId = activityId;
        this.taskId = taskId;
        this.taskNum = taskNum;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public ActivityTaskRelationEntity id(int id) {
        this.id = id;
        return this;
    }

    public ActivityTaskRelationEntity activityId(int activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityTaskRelationEntity taskId(int taskId) {
        this.taskId = taskId;
        return this;
    }

    public ActivityTaskRelationEntity taskNum(int taskNum) {
        this.taskNum = taskNum;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", activityId='" + getActivityId() + "'" +
            ", taskId='" + getTaskId() + "'" +
            ", taskNum='" + getTaskNum() + "'" +
            "}";
    }

}