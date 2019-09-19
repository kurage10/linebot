package com.weasleyclock.linebot.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MemberLocationService {

    private final Logger LOGGER = LoggerFactory.getLogger(MemberLocationService.class);
    @Async
    public void registerMemberLocation(){ 
        try{
            LOGGER.info("[start] register family member location to DB");
            TimeUnit.SECONDS.sleep(60);
            LOGGER.info("[end] register family member location to DB");
        }catch (InterruptedException e) {
            LOGGER.info("Interrupted");
        }
    }     

}