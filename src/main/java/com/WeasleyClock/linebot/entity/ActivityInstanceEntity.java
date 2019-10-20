package com.weasleyclock.linebot.entity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.apache.ibatis.type.Alias;

@Alias("ActivityInstanceEntity")
public class ActivityInstanceEntity{
    private int id;
    private boolean isActive;
    private int activityId;
    private String userId;
    private int currentTaskId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ActivityInstanceEntity() {
    }

    public ActivityInstanceEntity(int id, boolean isActive, int activityId, String userId, int currentTaskId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.isActive = isActive;
        this.activityId = activityId;
        this.userId = userId;
        this.currentTaskId = currentTaskId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getActivityId() {
        return this.activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCurrentTaskId() {
        return this.currentTaskId;
    }

    public void setCurrentTaskId(int currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", activityId='" + getActivityId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", currentTaskNum='" + getCurrentTaskId() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}