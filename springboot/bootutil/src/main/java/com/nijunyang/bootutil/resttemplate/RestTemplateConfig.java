package com.nijunyang.bootutil.resttemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Description: 
 * Created by nijunyang on 2019/12/25 11:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "xx.server")
public class RestTemplateConfig {

    private int restConnectTimeout;

    private int restReadTimeout;

    private String  baseUrl;

    /**
     * 封装自己的RestTemplate
     * http://127.0.0.1:8080/test/secret
     * 配置baseUrl http://127.0.0.1:8080
     * 后续使用myRestTemplate 去调用其他服务时只需传入/test/secret
     *
     * @return                     RestTemplate
     */
    @Bean(name = "myRestTemplate")
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory httpClientFactory = this.httpClientFactory(restReadTimeout, restConnectTimeout);
        RestTemplate restTemplate = new RestTemplate(httpClientFactory);
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        jsonMessageConverter.setSupportedMediaTypes(Lists.newArrayList(MediaType.ALL));
        restTemplate.setMessageConverters(Lists.newArrayList(jsonMessageConverter));
        if(!StringUtils.isEmpty(baseUrl)) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(baseUrl);
            DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(uriComponentsBuilder);
            restTemplate.setUriTemplateHandler(uriBuilderFactory);
        }
        return restTemplate;
    }

    private SimpleClientHttpRequestFactory httpClientFactory(int readTimeOut, int connectTimeOut) {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(readTimeOut);
        httpRequestFactory.setOutputStreaming(false);
        httpRequestFactory.setConnectTimeout(connectTimeOut);
        return httpRequestFactory;
    }
}
