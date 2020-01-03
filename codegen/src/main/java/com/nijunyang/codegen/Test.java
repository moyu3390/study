package com.nijunyang.codegen;

import com.nijunyang.codegen.util.PathUtils;
import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Description: 
 * Created by nijunyang on 2020/1/3 10:24
 */
public class Test {

    @org.junit.Test
    public void test() throws IOException {
        String filePath = PathUtils.combinePath("src", "main", "resources", "rest.yml");
        String info = FileUtils.readFileToString(new File(filePath));

        //将yml文件转化为Swagger对象
        Swagger swagger = new SwaggerParser().parse(info);

        //JavaControllerCodegen（存放类的信息，类型对应["integer", "Integer"]表等等），用于扩展一些自定义功能
        JavaControllerCodegen controllerCodegen = new JavaControllerCodegen();
        ClientOptInput input = new ClientOptInput().opts(new ClientOpts()).swagger(swagger);
        input.setConfig(controllerCodegen);

        ApiCodegen apiCodegen = new ApiCodegen();
        apiCodegen.opts(input).generate();
    }
}
