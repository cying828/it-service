package com.it.message.listener;


import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TopicListener {

    /**
     * kafka支持多种类型的键值对，采用string类型效率最高
     * @param record
     */
    @KafkaListener(topics={"kafka-test"},groupId = "system-test")
    public void listen(ConsumerRecord<String,String> record, Acknowledgment ack){
        record.value();
        System.out.print(record.value());
        //手动提交offset
        ack.acknowledge();
    }
}
