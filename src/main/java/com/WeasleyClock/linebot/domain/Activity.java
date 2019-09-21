package com.weasleyclock.linebot.domain;

import java.util.List;

public class Activity{
    private int nextTaskIndex;
    private List<Task> taskLists;
    public Task getNextTask(){
        return taskLists.get(++nextTaskIndex);
    }  


    public Activity(List<Task> taskLists) {
        this.nextTaskIndex = 0;
        this.taskLists = taskLists;
    }

    public int getNextTaskIndex() {
        return this.nextTaskIndex;
    }

    public void setNextTaskIndex(int nextTaskIndex) {
        this.nextTaskIndex = nextTaskIndex;
    }

    public List<Task> getTaskLists() {
        return this.taskLists;
    }

    public void setTaskLists(List<Task> taskLists) {
        this.taskLists = taskLists;
    }

    public Activity nextTaskIndex(int nextTaskIndex) {
        this.nextTaskIndex = nextTaskIndex;
        return this;
    }

    public Activity taskLists(List<Task> taskLists) {
        this.taskLists = taskLists;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " nextTaskIndex='" + getNextTaskIndex() + "'" +
            ", taskLists='" + getTaskLists() + "'" +
            "}";
    }
}