package com.nijunyang.arthas.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nijunyang
 * @since 2021/4/30
 */
@RequestMapping
@RestController
public class Controller {

    @GetMapping("/test")
    public ResponseEntity<Object> test(Param param) {
        return ResponseEntity.ok(param);
    }


    @Getter
    @Setter
    static class Param {
        private String id;
        private String sex;
    }

}
