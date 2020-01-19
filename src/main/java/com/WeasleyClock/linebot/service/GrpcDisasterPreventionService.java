package com.weasleyclock.linebot.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.weasleyclock.disasterprevention.EquipmentOuterClass.InsertRequest;
import io.grpc.weasleyclock.disasterprevention.EquipmentServiceGrpc;

@Service
public class GrpcDisasterPreventionService{

    private final Logger LOGGER = LoggerFactory.getLogger(GrpcDisasterPreventionService.class);
    
    
    @Autowired
    private EurekaClient client;

    public void insertEquipment(String category, String expiredDate, String uri){
        LOGGER.info(" === start insert ===");
        if(client != null){

            InstanceInfo instanceInfo = client.getNextServerFromEureka("disasterprevention", false);
            ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                    .usePlaintext()
                    .build(); 
            LOGGER.info(" === start call method===");
            EquipmentServiceGrpc.EquipmentServiceFutureStub stub = EquipmentServiceGrpc.newFutureStub(channel); 
            
            LOGGER.info(" === create request ===");
            InsertRequest request = InsertRequest.newBuilder().setCategory(category).setExpiredDate(expiredDate).setUri(uri).build(); 

            LOGGER.info(" === insert ===");
            stub.insert(request); 

        }else{
            LOGGER.info(" === why ????? ===");

        }

    }
    public void echoService() {
       LOGGER.info("I'm alive!"); 
    }

}