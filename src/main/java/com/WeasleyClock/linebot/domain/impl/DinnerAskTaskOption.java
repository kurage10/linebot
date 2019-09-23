package com.weasleyclock.linebot.domain.impl;

import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.message.Message;
import com.weasleyclock.linebot.code.BooleanTaskOptionCode;
import com.weasleyclock.linebot.code.TaskCode;
import com.weasleyclock.linebot.domain.TaskOption;

public class DinnerAskTaskOption {
    // ここの選択肢はどのタスクに紐づく選択肢か。
    private TaskCode taskCode;

    // ここの選択肢自体を管理するコード
    private BooleanTaskOptionCode optionCode;

    // 実行後に送信するメッセージ
    private Message reply;

    
    public boolean isSatisfied(String code){
        if(code.equals(optionCode.toString())){
            return true;
        }
        return false;
    }

}