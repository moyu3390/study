package com.nijunyang.demo.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 热更新
 * Created by nijunyang on 2020/8/13 10:53
 */
@RestController
@RequestMapping("hot-update")
@RefreshScope
public class HotUpdateController {

    @Value("spring.security.user.name")
    private String user = "tyadmin";
    @Value("spring.security.user.password")
    private String password = "hqX05mQMM9CiHVEu0fW2";
    @Value("server.port")
    private int port = 8010;

    private final static String loginUrl = "/login";
    private final static String refreshUrl = "/actuator/refresh";

    private final static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args){
        HotUpdateController controller= new HotUpdateController();
        HotUpdateParam param = new HotUpdateParam();
        param.setUser("tyadmin");
        param.setPassword("hqX05mQMM9CiHVEu0fW2");
        param.setHostList(Arrays.asList("118.25.76.43"));
        controller.updatePro(param);
    }
    void updatePro(HotUpdateParam updateParam){
        if (user.equals(updateParam.user) && password.equals(updateParam.password)) {
            for (String host : updateParam.hostList) {
                String login = "http://" + host + ":" + port + loginUrl;

                HttpHeaders headersLogin = new HttpHeaders();
                MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("username", user);
                map.add("password", password);
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headersLogin);
                ResponseEntity<String> response = restTemplate.postForEntity(login, request, String.class);
                HttpHeaders httpHeaders = response.getHeaders();
                String cookie = httpHeaders.get("Set-Cookie").get(0);
                System.out.println(cookie);

                String refresh = "http://" + host + ":" + port + refreshUrl;
                HttpHeaders headersRefresh = new HttpHeaders();
                List<String> cookieList =new ArrayList<>();
                cookieList.add(cookie);
                headersRefresh.put(HttpHeaders.COOKIE, cookieList);
                List<String> contentTypeList =new ArrayList<>();
                contentTypeList.add("application/json;charset=UTF-8");
                headersRefresh.put(HttpHeaders.CONTENT_TYPE, contentTypeList);
                HttpEntity<MultiValueMap<String, String>> requestRefresh = new HttpEntity<>(headersRefresh);
                ResponseEntity<String> responseRefresh = restTemplate.postForEntity(refresh, requestRefresh, String.class);
                System.out.println(responseRefresh.getStatusCode());
            }
        }
    }



    @PostMapping
    public ResponseEntity<Integer> updateProperties(@RequestBody HotUpdateParam updateParam) throws Exception {
        if (user.equals(updateParam.user) && password.equals(updateParam.password)) {
            for (String host : updateParam.hostList) {
                String login = "http://" + host + ":" + port + loginUrl;

                HttpHeaders headersLogin = new HttpHeaders();
                MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("username", user);
                map.add("password", password);
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headersLogin);
                ResponseEntity<String> response = restTemplate.postForEntity(login, request, String.class);
                HttpHeaders httpHeaders = response.getHeaders();
                String cookie = httpHeaders.get("Set-Cookie").get(0);
                System.out.println(cookie);

                String refresh = "http://" + host + ":" + port + refreshUrl;
                HttpHeaders headersRefresh = new HttpHeaders();
                List<String> cookieList =new ArrayList<>();
                cookieList.add(cookie);
                headersRefresh.put(HttpHeaders.COOKIE, cookieList);
                List<String> contentTypeList =new ArrayList<>();
                contentTypeList.add("application/json;charset=UTF-8");
                headersRefresh.put(HttpHeaders.CONTENT_TYPE, contentTypeList);
                HttpEntity<MultiValueMap<String, String>> requestRefresh = new HttpEntity<>(headersRefresh);
                ResponseEntity<String> responseRefresh = restTemplate.postForEntity(refresh, requestRefresh, String.class);
                System.out.println(responseRefresh.getStatusCode());
            }
        }

        return ResponseEntity.ok(0);
    }

    @Getter
    @Setter
    public static class HotUpdateParam {
        private String user;
        private String password;
        private List<String> hostList;
    }
}
