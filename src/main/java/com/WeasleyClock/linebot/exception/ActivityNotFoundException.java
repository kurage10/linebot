package com.weasleyclock.linebot.exception;

public class ActivityNotFoundException extends WeasleyClockAppException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public ActivityNotFoundException (){
        super("ご依頼のアクティビティはご用意できていません。");
    }
}
