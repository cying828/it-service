package com.it.message.handle;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSendResultHandler implements ProducerListener {
    private static final Logger log = LoggerFactory.getLogger(KafkaSendResultHandler.class);

    /**
     * kafka发送成功回调
     * @param producerRecord
     * @param recordMetadata
     */
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        String key = producerRecord.key().toString();
        String topic = producerRecord.topic();
        log.info("key：{}，topic：{}， 发送成功回调",key,topic);
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        String key = producerRecord.key().toString();
        String topic = producerRecord.topic();
        log.info("key：{}，topic：{}， 发送异常回调",key,topic);
    }
}
