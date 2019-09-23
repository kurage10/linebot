package com.weasleyclock.linebot.code;

public enum BooleanTaskOptionCode implements AppTaskOptionCode<Boolean> {
    USER_REPLY_YES(true), 
    USER_REPLY_NO(false);

    private Boolean value;

    BooleanTaskOptionCode(Boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }


}