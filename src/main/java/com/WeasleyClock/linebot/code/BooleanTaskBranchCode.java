package com.weasleyclock.linebot.code;

public enum BooleanTaskBranchCode implements TaskBranchCode<Boolean> {
    USER_REPLY_YES(true), 
    USER_REPLY_NO(false);

    private Boolean value;

    BooleanTaskBranchCode(Boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }


}