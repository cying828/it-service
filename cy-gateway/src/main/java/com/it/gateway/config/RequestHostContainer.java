package com.it.gateway.config;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum RequestHostContainer {

    INSTANCE;

    /**
     * 请求容器，每个请求都根据serverID分类，放在list中，list中存储的是请求的客户端IP
     */
    private Map<String, List<String>> map = new HashMap();

    /**
     * 每次请求将客户端IP根据请求的服务名不同，放在map中
     *
     * @param serverName  服务名称
     * @param requestHost 客户端IP
     */
    public synchronized void addRequestHost(String serverName, String requestHost) {
        int max = 50;
        if (map.containsKey(serverName)) {
            List<String> hosts = map.get(serverName);
            hosts.add(requestHost);
            if (hosts.size() >= max) {
                hosts.clear();
            }
        } else {
            List<String> hosts = new LinkedList<>();
            hosts.add(requestHost);
            if (hosts.size() >= max) {
                hosts.clear();
            }
            map.put(serverName, hosts);
        }
    }

    /**
     * 获取这个服务名最旧的一次请求的客户端IP，并从list中删除
     *
     * @param serverName 服务名称
     * @return 根据服务名返回一个该服务的请求客户端IP
     */
    public synchronized String getRequestHost(String serverName) {
        if (map.containsKey(serverName)) {
            List<String> hosts = map.get(serverName);
            if (hosts.size() == 0) {
                return null;
            }
            String hostIp = hosts.get(hosts.size() - 1);
            hosts.remove(hosts.size() - 1);
            if (hosts.size() >= 10) {
                hosts.clear();
            }
            return hostIp;
        }
        return null;
    }

    /**
     * 打印剩余服务待处理服务数量
     *
     * @param serverName 服务名称
     */
    public synchronized void getRequestSize(String serverName) {
        int size = 0;
        if (map.containsKey(serverName)) {
            size = map.get(serverName).size();
        }
        System.out.println("剩余待处理服务数量为：" + size);
    }

}
