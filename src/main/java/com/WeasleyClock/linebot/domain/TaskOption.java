package com.weasleyclock.linebot.domain;

import java.util.function.Function;

import com.linecorp.bot.model.message.Message;
import com.weasleyclock.linebot.code.AppTaskOptionCode;
import com.weasleyclock.linebot.code.TaskCode;
import com.weasleyclock.linebot.entity.TaskEntity;

public abstract class TaskOption {
    // ここの選択肢はどのタスクに紐づく選択肢か。
    private TaskCode taskCode;
    
    // ここの選択肢自体を管理するコード
    private AppTaskOptionCode optionCode;
    
    // 実行後に送信するメッセージ
    private Message reply;
    
    public abstract Message executeTaskOption();


    public TaskOption() {
    }

    public TaskOption(TaskCode taskCode, AppTaskOptionCode optionCode, Message reply) {
        this.taskCode = taskCode;
        this.optionCode = optionCode;
        this.reply = reply;
    }

    public TaskCode getTaskCode() {
        return this.taskCode;
    }

    public void setTaskCode(TaskCode taskCode) {
        this.taskCode = taskCode;
    }

    public AppTaskOptionCode getOptionCode() {
        return this.optionCode;
    }

    public void setOptionCode(AppTaskOptionCode optionCode) {
        this.optionCode = optionCode;
    }

    public Message getReply() {
        return this.reply;
    }

    public void setReply(Message reply) {
        this.reply = reply;
    }

    public TaskOption taskCode(TaskCode taskCode) {
        this.taskCode = taskCode;
        return this;
    }

    public TaskOption optionCode(AppTaskOptionCode optionCode) {
        this.optionCode = optionCode;
        return this;
    }

    public TaskOption reply(Message reply) {
        this.reply = reply;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " taskCode='" + getTaskCode() + "'" +
            ", optionCode='" + getOptionCode() + "'" +
            ", reply='" + getReply() + "'" +
            "}";
    }



    // そのタスクを実行する条件が満たされているかどうかを判定する。
    public boolean isSatisfied(String code){
        if(code.equals(optionCode.toString())){
            return true;
        }
        return false;
    }
}