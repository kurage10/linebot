package com.weasleyclock.linebot.exception;

public class TaskNotFoundException extends WeasleyClockAppException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public TaskNotFoundException (){
        super("実行すべきタスクが見つかりませんでした。");
    }

}
