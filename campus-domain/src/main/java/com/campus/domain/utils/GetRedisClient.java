package com.campus.domain.utils;

import cn.com.hisee.common.utils.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * TO DO :redisClient 单例的实现
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-05-21 15:59
 */
@Component
public class GetRedisClient {
    /***redis 相关配置**/
    @Value("${local.redis.ip}")
    private  String host;
    @Value("${local.redis.port}")
    private  int port;
    @Value("${local.redis.password}")
    private  String redisPassWord;
    /***redis data相关配置**/
    @Value("${spring.redis.host}")
    private  String dataHost;
    @Value("${spring.redis.port}")
    private  int dataPort;
    @Value("${spring.redis.password}")
    private  String dataRedisPassWord;

    private RedisClient redisClient;
    private RedisClient dataRedisClient;

    @PostConstruct
    public void init(){
        String auth = null;
        if (StringUtils.isNotEmpty(redisPassWord)){
            auth = redisPassWord;
        }
        String dateAuth = null;
        if (StringUtils.isNotEmpty(dataRedisPassWord)){
            dateAuth = dataRedisPassWord;
        }
        redisClient =  new RedisClient(host, port,0,auth,3000);
        dataRedisClient =  new RedisClient(dataHost, dataPort,0,dateAuth,3000);
    }

   public RedisClient getRedisClient(){
       return  redisClient;
   }
   public RedisClient getDataRedisClient(){
        return  dataRedisClient;
    }

}
