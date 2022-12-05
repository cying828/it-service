package com.it.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.it.common.core.constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @Author Cying
 * @Date 2022/6/28 14:34
 * @Description
 */
@Slf4j
@Component
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {

    private static final String PASSWORD = "password";
    private static final String KEY_ALGORITHM = "AES";
    @Value("${security.encode.key:1234567812345678}")
    private String encodeKey;


    @SneakyThrows
    public static String decryptAES(String data,String pass) {
        AES aes = new AES(Mode.CBC, Padding.NoPadding,
                new SecretKeySpec(pass.getBytes(),KEY_ALGORITHM),
                new IvParameterSpec(pass.getBytes()));
        byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result,StandardCharsets.UTF_8);
    }


    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
                //判断是否登录请求
                if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
                    return chain.filter(exchange);
                }
                URI uri = request.getURI();
                String queryParam = uri.getRawQuery();
                HashMap<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);
                String password = paramMap.get(PASSWORD);

                if (StrUtil.isNotBlank(password)) {
                    try {
                        password = decryptAES(password, encodeKey);
                    } catch (Exception e) {
                        log.error("密码解密失败:{}", password);
                        return Mono.error(e);
                    }
                    paramMap.put(PASSWORD,password.trim());
                }
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(HttpUtil.toParams(paramMap))
                        .build(true)
                        .toUri();
                ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
                return chain.filter(exchange.mutate().request(newRequest).build());
            }
        };
    }




    /**
     *  ================= 测试加解密=======================
     */

    private static final String encodeDKey = "zykjzykjzykjzykj";


    public static void main(String[] args) {
        //测试加密  123123
        String password = "123456";
        String encryptAES = encryptAES(password);
        System.out.println(encryptAES);

        String decryptAES = decryptAES1(encryptAES, encodeDKey);
        System.out.println(decryptAES);
    }

    //加密
    public static String encryptAES(String psw) {
        AES aes = new AES(Mode.CBC,Padding.PKCS5Padding,
                new SecretKeySpec(encodeDKey.getBytes(),KEY_ALGORITHM),
                new IvParameterSpec(encodeDKey.getBytes()));
        return aes.encryptBase64(psw.getBytes());
    }
    //解密
    public static String decryptAES1(String data,String pass) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding,
                new SecretKeySpec(pass.getBytes(),KEY_ALGORITHM),
                new IvParameterSpec(pass.getBytes()));
        byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result,StandardCharsets.UTF_8);
    }
}
