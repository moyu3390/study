package com.nijunyang.shiro.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author nijunyang
 * @since 2021/3/10
 */
@Service
public class AuthCacheService implements InitializingBean {

    private Cache<String, String> cacheToken;

    private HashMap<Integer, Subject> loginMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        cacheToken = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(3600, TimeUnit.SECONDS)
                .concurrencyLevel(10)
                .recordStats()
                .build();
    }

    public void cacheLogin(Integer userId, Subject subject) {
        loginMap.put(userId, subject);
    }

    public Subject getCacheLogin(Integer userId) {
        return loginMap.get(userId);
    }

    public void removeCacheLogin(Integer userId) {
        loginMap.remove(userId);
    }

    public void cacheToken(String userId, String jwt) {
        cacheToken.put(userId, jwt);
    }

    public String getToken(String userId) {
        return cacheToken.getIfPresent(userId);
    }

    public void removeToken(Integer userId) {
        cacheToken.invalidate(userId);
    }
}
