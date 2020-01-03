package com.nijunyang.springboot;

import com.nijunyang.codegen.service.TestControllerService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 
 * Created by nijunyang on 2020/1/3 17:26
 */
@Service
public class TestImpl implements TestControllerService {
    @Override
    public String test(HttpServletRequest request, String username) {
        return null;
    }
}
