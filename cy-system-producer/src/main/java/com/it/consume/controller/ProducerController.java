package com.it.consume.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Cying
 * @Date 2022/6/24 17:23
 * @Description
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource
    private ProducerService producerServiceImpl;
    @RequestMapping("/getOne")
    public void getOne() {
        producerServiceImpl.sendKafkaMsg();
    }

}
