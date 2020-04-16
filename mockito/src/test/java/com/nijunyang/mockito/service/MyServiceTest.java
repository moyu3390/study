package com.nijunyang.mockito.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Description:
 * Created by nijunyang on 2020/4/16 15:10
 */
//@RunWith(PowerMockRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@PrepareForTest({MyService.class})
@SuppressStaticInitializationFor({"com.nijunyang.mockito.service.MyService"}) //阻止静态代码块执行
public class MyServiceTest {

    MyService myService;

    @Before
    public void setUp() throws Exception {
        myService = PowerMockito.mock(MyService.class);
    }

    @Test
    public void test1() {
    }
}