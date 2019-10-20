package com.weasleyclock.linebot.entity;

public class ActivityTaskRelationEntity {
    private int id;
    private int activityId;
    private int taskId;
    private int nextTask;
    private String branchCode;

    public ActivityTaskRelationEntity() {
    }

    public ActivityTaskRelationEntity(int id, int activityId, int taskId, int nextTask, String branchCode) {
        this.id = id;
        this.activityId = activityId;
        this.taskId = taskId;
        this.nextTask = nextTask;
        this.branchCode = branchCode;
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

    public int getNextTask() {
        return this.nextTask;
    }

    public void setNextTask(int nextTask) {
        this.nextTask = nextTask;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", activityId='" + getActivityId() + "'" +
            ", taskId='" + getTaskId() + "'" +
            ", taskNum='" + getBranchCode() + "'" +
            "}";
    }

}