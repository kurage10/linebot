package com.weasleyclock.linebot.code;

public enum WellKnownTaskBranchCode implements TaskBranchCode<String> {
    SEQUENTIAL("SEQUENTIAL");

    private String value;

    WellKnownTaskBranchCode(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }


}