package com.weasleyclock.linebot.code;

public enum MessagePriorityCode {
    REPLIED_MESSAGE(10), 
    COMMON_MESSAGE(5);
    private int value;
    
    MessagePriorityCode (int value){
        this.value = value;
    }

    public int getValue() {
        return  value;
    }

}