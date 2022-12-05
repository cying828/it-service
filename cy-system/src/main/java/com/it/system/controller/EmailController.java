package com.it.system.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.it.system.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @Author Cying
 * @Date 2022/6/20 11:06
 * @Description
 */
@RestController
@RequestMapping("/system")
@RefreshScope //支持nacos刷新功能
public class EmailController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmailService emailServiceImpl;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final String SERVICE_URL = "http://cy-system-consume1";

    @RequestMapping("/send")
    public String sendMsg() {
        emailServiceImpl.sendMsg();
        return "发送成功！！！";
    }

    /**
     * 测试负载均衡
     * @return
     */
    @RequestMapping("/rest")
    public String getRestT() {

        ResponseEntity<String> entity = restTemplate.getForEntity(SERVICE_URL + "/consume/getOne", String.class);
        HttpStatus statusCode = entity.getStatusCode();
        System.out.println("状态码："+statusCode);

        return entity.getBody();
    }

    /**
     * 测试nacos负载均衡
     * @return
     */
    @RequestMapping("/nacos/rest")
    public String getNacosRest() {

        List<ServiceInstance> instances = discoveryClient.getInstances("cy-system-consume1");
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);

        ResponseEntity<String> entity = restTemplate.getForEntity("http://" + serviceInstance.getServiceId() + ":" + serviceInstance.getPort()+"/consume/getOne", String.class);

        System.out.println(Thread.currentThread().getName()+"--执行了");
        return entity.getBody();
    }



    @RequestMapping("/getOne")
    @SentinelResource(value = "getOne")
    public String getData() {
        return emailServiceImpl.getOne();
    }

    @RequestMapping("/getOne2")
    @SentinelResource(value = "getOne2")
    public String getData2() {
        return emailServiceImpl.getOne2();
    }

}
