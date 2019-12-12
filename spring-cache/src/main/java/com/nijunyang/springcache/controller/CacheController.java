package com.nijunyang.springcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * Created by nijunyang on 2019/12/12 13:59
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/cache/{name}")
    public String testCache(@PathVariable String name) {
        return cacheService.cache(name);
    }
}
