package com.it.system.service.impl;

import com.it.common.api.feign.RemoteConsume1Service;
import com.it.system.service.EmailService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Cying
 * @Date 2022/6/20 11:26
 * @Description
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

//    @Resource
//    private RemoteConsume1Service remoteConsume1Service;


    public String sendMsg() {
        kafkaTemplate.send("kafka-test","key","附带信息");
        return "成功";
    }

    @Override
    public String getOne() {
//        remoteConsume1Service.getOne();
        return "";
    }

    @Override
    public String getOne2() {
        return null;
    }
}
