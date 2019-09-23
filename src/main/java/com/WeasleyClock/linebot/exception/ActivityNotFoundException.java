package com.weasleyclock.linebot.exception;

public class ActivityNotFoundException extends WeasleyClockAppException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ActivityNotFoundException (){
        super("ご要望のタスクが見つかりませんでした。");
    }
}
