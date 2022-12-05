
package com.it.gateway.filter;

import com.it.common.core.constant.SecurityConstants;
import com.it.gateway.config.RequestHostContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;

/**
 * <p>
 * 全局拦截器，作用所有的微服务
 * <p>
 * 1. 对请求头中参数进行处理 from 参数进行清洗
 * 2. 重写StripPrefix = 1,支持全局
 * <p>
 * 支持swagger添加X-Forwarded-Prefix header  （F SR2 已经支持，不需要自己维护）
 */
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 标识当前是否开发环境，如果是，则有请求客户端有限匹配
     */
    @Value("${com.cy.devFlag}")
    private Boolean devFlag;


    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 这一句话如果是线上环境，不能使用！
        if (devFlag) {
            this.addRequestClientId(exchange);
        }

        // 1. 清洗请求头中from 参数
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(httpHeaders -> httpHeaders.remove(SecurityConstants.FROM)).build();


        // 2. 重写StripPrefix
        addOriginalRequestUrl(exchange, request.getURI());
        String rawPath = request.getURI().getRawPath();
        //截取前缀url 1，暂时改为0 不进行截取
        String newPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(rawPath, "/"))
                .skip(1L).collect(Collectors.joining("/"));
        ServerHttpRequest newRequest = request.mutate()
                .path(newPath)
                .build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());

        return chain.filter(exchange.mutate()
                .request(newRequest.mutate()
                        .build()).build());
    }

    /**
     * 将request的客户端IP存到map中，方便开发环境的路由转发
     *
     * @param exchange 请求信息
     */
    private void addRequestClientId(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Route route = exchange.getAttribute("org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute");
        String host = getIpAddress(request);
        RequestHostContainer.INSTANCE.addRequestHost(route.getId(), host);
    }



    /**
     * 获取用户IP
     * @param request
     * @return
     */
    public static String getIpAddress(ServerHttpRequest request) {
        String ip = getHeader(request,"x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeader(request,"Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeader(request,"WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeader(request,"HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = getHeader(request,"HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getHostString();
        }
        return ip;
    }

    /**
     * 从header中获取值
     * @param request
     * @param headerName
     * @return
     */
    public static String getHeader(ServerHttpRequest request, String headerName) {
        List<String> header = request.getHeaders().get(headerName);
        if (header == null || header.size() == 0) {
            return "unknown";
        }
        return header.get(0);
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
