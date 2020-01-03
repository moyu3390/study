package com.nijunyang.codegen;

import io.swagger.codegen.languages.JavaClientCodegen;

/**
 * Description: 
 * Created by nijunyang on 2020/1/3 10:28
 */
public class JavaControllerCodegen extends JavaClientCodegen {
    public JavaControllerCodegen()
    {
        super.apiPackage = "com.nijunyang.codegen.api";
        super.modelPackage = "com.nijunyang.codegen.model";
//        super.modelTemplateFiles.put("bean.mustache", ".java");
//        super.apiTemplateFiles.put("controller.mustache", ".java");
    }
}
