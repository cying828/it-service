package com.it.consume.service;

import com.it.message.handle.KafkaSendResultHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Service
public class ProducerServiceImpl implements ProducerService{
    @Resource
    private KafkaSendResultHandler producerListener;
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendKafkaMsg(String value) {
        try {
            //发送消息前配置回调
            kafkaTemplate.setProducerListener(producerListener);
            //发送消息改为同步添加.get()
            kafkaTemplate.send("kafka-test","cying",value).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
