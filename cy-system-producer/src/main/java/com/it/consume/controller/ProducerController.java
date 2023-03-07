package com.it.consume.controller;

import com.it.consume.service.ProducerService;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/getOne/{value}")
    public void getOne(@PathVariable(name = "value") String value) {
        producerServiceImpl.sendKafkaMsg(value);
    }

}
