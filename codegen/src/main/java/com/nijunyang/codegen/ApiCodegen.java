package com.nijunyang.codegen;

import com.nijunyang.codegen.util.PathUtils;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.properties.DateProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 
 * Created by nijunyang on 2020/1/3 10:33
 */
public class ApiCodegen extends DefaultGenerator {

    public List<File> generate() {

        Map<String,Path> pathMap = swagger.getPaths();
        createController(pathMap);
        Map<String, Model> definitionMap = swagger.getDefinitions();
        createModel(definitionMap);

        return null;
    }

    private void createModel(Map<String, Model> definitionMap) {
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        for (Map.Entry<String, Model> entry : definitionMap.entrySet()) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.className = entry.getKey();
            beanDefinition.modelPackage = config.modelPackage();;
            List <Map<String, String>> fieldList = new ArrayList<>();

            Map<String, Property> propertyMap = entry.getValue().getProperties();
            //基础类型
            for (Map.Entry<String, Property> propertyEntry : propertyMap.entrySet()) {
                Map<String, String> fieldMap = new HashMap<>();
                fieldMap.put("type", typeTransform(propertyEntry.getValue()));
                fieldMap.put("field", propertyEntry.getKey());
                fieldList.add(fieldMap);
            }
            beanDefinition.fieldList = fieldList;
            beanDefinitionList.add(beanDefinition);
        }
        for (BeanDefinition definition : beanDefinitionList) {
            String beanTemplateFilePath = PathUtils.combinePath("src", "main", "resources", "bean.mustache");
            String beanOutputFilePath = PathUtils
                    .combinePath("src", "main", "java", "com", "nijunyang", "codegen", "model", definition.className + ".java");
            String templateFileInfo = "";
            try {

                //bean
                templateFileInfo = FileUtils.readFileToString(new File(beanTemplateFilePath));
                Template template = Mustache.compiler().compile(templateFileInfo);
                String result = template.execute(definition);
                FileUtils.write(new File(beanOutputFilePath), result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private String typeTransform(Property property) {
        Map<String, String> typeMap = config.typeMapping();
        System.out.println(typeMap);
        if ("integer".equals(property.getType()) && "int64".equals(property.getFormat()) ) {
            return "Long";
        }
//        if (property instanceof DateProperty) {
//            return "Date";
//        }
        return typeMap.get(property.getType());

    }

    //生成controller文件 同时生成service接口文件
    private void createController(Map<String, Path> pathMap) {
        List<ApiDefinition> apiDefinitionList = new ArrayList<>();
        for (Map.Entry<String,Path> entry : pathMap.entrySet())
        {
            ApiDefinition info = new ApiDefinition();
            info.apiPackage = config.apiPackage();
            info.modelPackage = config.modelPackage();
            info.basePath = swagger.getBasePath();
            info.servicePackage = "com.nijunyang.codegen.service";

            List <Map<String, Object>> infoList = new ArrayList<>();
            List <Map<String, String>> importList = new ArrayList<>();
            Map<String, Object> infoMap =  new HashMap<>();
            infoMap.put("urlName", entry.getKey());
            Path path = entry.getValue();
            String className = changeTypeAndGetClassName(path, infoMap, importList);
            info.className = className;
            infoMap.put("path", path);
            infoList.add(infoMap);
            info.infoList = infoList;
            info.importList = importList;
            apiDefinitionList.add(info);
        }

        for (ApiDefinition info : apiDefinitionList) {
            String apiOutputFilePath =
                    PathUtils.combinePath("src", "main", "java", "com", "nijunyang", "codegen", "api", info.className + ".java");
            String apiTemplateFilePath = PathUtils.combinePath("src", "main", "resources", "controller.mustache");

            String serviceTemplateFilePath = PathUtils.combinePath("src", "main", "resources", "service.mustache");
            String serviceOutputFilePath =
                    PathUtils.combinePath("src", "main", "java", "com", "nijunyang", "codegen", "service", info.className + "Service" + ".java");

            String templateFileInfo = "";
            try {

                //service接口
                templateFileInfo = FileUtils.readFileToString(new File(serviceTemplateFilePath));
                Template template = Mustache.compiler().compile(templateFileInfo);
                String result = template.execute(info);
                //生成service接口文件
                FileUtils.write(new File(serviceOutputFilePath), result);

                //api
                //获取模板信息
                templateFileInfo = FileUtils.readFileToString(new File(apiTemplateFilePath));
                //生成模板
                template = Mustache.compiler().compile(templateFileInfo);
                //解析模板
                Map<String, String> importMap = new HashMap<>();
                importMap.put("import", "com.nijunyang.codegen.service." + info.className + "Service");
                info.importList.add(importMap);
                result = template.execute(info);
                //生成Controller文件
                FileUtils.write(new File(apiOutputFilePath), result);

                //bean

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO 字段类型转换及类名获取。未完善所有类型
    private String changeTypeAndGetClassName(Path path, Map<String, Object> infoMap, List <Map<String, String>> importList)
    {
        List<Parameter> parameterList;
        Map<String, String> typeMap = config.typeMapping();
        String className = "";
        if (path.getGet() != null)
        {
            infoMap.put("hasGet", true);
            if (className.isEmpty()) {
                className = path.getGet().getTags().get(0);
            }
            parameterList = path.getGet().getParameters();
            for (Parameter parameter : parameterList)
            {
                PathParameter pathParameter = (PathParameter)parameter;
                pathParameter.setType(typeMap.get(pathParameter.getType()));
            }
            Property property = path.getGet().getResponses().get("200").getSchema();
            if (property != null)
            {
                if (property instanceof RefProperty) {
                    RefProperty refProperty = (RefProperty)property;
                    infoMap.put("responseType", refProperty.getSimpleRef());
                    Map<String, String> map = new HashMap<>();
                    map.put("import", config.modelPackage() + "." + refProperty.getSimpleRef());
                    importList.add(map);
                }
                if (property instanceof StringProperty) {
                    StringProperty stringProperty = (StringProperty)property;
                    infoMap.put("responseType", typeMap.get(stringProperty.getType()));
                }
            }
        }
        //TODO 其他几种请求 put，post,delete...
        return className;

    }

    class ApiDefinition
    {
        public String apiPackage;
        public String modelPackage;
        public String servicePackage;
        public String basePath;
        public String className;
        public List <Map<String, String>> importList;
        public List <Map<String, Object>> infoList;
    }

    class BeanDefinition
    {
        public String modelPackage;
        public String className;
        public List <Map<String, String>> importList;
        public List <Map<String, Object>> infoList;
        public List <Map<String, String>> fieldList;
    }
}
