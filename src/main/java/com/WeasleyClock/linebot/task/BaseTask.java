package com.weasleyclock.linebot.task;

import com.linecorp.bot.model.message.Message;
import com.weasleyclock.linebot.code.TaskBranchCode;

public abstract class BaseTask {
    public BaseTask(){}
    public abstract Message start(TaskRequestDto request);
    public abstract TaskBranchCode execute(TaskRequestDto request);
}