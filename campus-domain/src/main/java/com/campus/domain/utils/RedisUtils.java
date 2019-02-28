package com.campus.domain.utils;

import cn.com.hisee.common.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TO DO :Redis 相关操作方法
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-05-18 15:03
 */
@Component
public class RedisUtils {
    /***redis 相关配置**/
    /***redis 相关配置**/
    @Value("${local.redis.ip}")
    private  String host;
    @Value("${local.redis.port}")
    private  int port;
    @Value("${local.redis.password}")
    private  String redisPassWord;
    /***接口服务地址**/
    @Value("${default.server.url}")
    private  String serverUrl;
    @Autowired
    GetRedisClient getRedisClient;
    /**添加缓存***/
    public void addCache(String  entity, String key , int second) {
       /* RedisClient redisClient = new RedisClient(host, port,0,redisPassWord,5);*/
        RedisClient redisClient = getRedisClient.getRedisClient();
        try {
            redisClient.set(key,entity);
            redisClient.expire(key,second);
        }catch (Exception e){
            e.printStackTrace();
            redisClient.del(key);
        }


    }
    /***获取缓存***/
    public String getCache(String key) {
       /* RedisClient redisClient = new RedisClient(host, port,0,redisPassWord,5);*/
        RedisClient redisClient = getRedisClient.getRedisClient();
        String s = redisClient.get(key);
        return s ;
    }
}
