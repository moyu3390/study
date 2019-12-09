package com.nijunyang.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 
 * Created by nijunyang on 2019/12/9 10:05
 */
public class HttpDemo {
    @Test
    public void testWuliangye() throws Exception {

        //登录获取token
        String  loginResponse = testIsvLogin();
        //提取token信息
        TokenModel tokenModel = JsonUtils.readJson2Entity(loginResponse, TokenModel.class);
        String token = tokenModel.getAccessToken();
        //        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpZCI6MTAwMDE1NiwiZXNlSWQiOjEsImRlcHRJZCI6MSwibWFzdGVyIjpmYWxzZSwiY3JlYXRlZEJ5IjoxLCJhdWQiOiIwOThmNmJjZDQ2MjFkMzczY2FkZTRlODMyNjI3YjRmNiIsImlzcyI6IklTVlNlcnZlciIsImV4cCI6MTU3NDM5NzM5NCwibmJmIjoxNTc0Mzg1Mzk0fQ.8CyallUdXNekhxQMGxCPZfg5TbHwv7rynlNapDwnhDg";
        //第三方码导入
        //        testThirdCodeImport(token);
        //二维码关联关系上传
        //        codeRelativeImport(token);
        //操作环节数据上传
        //        outDataImport(token);

        //        productImport(token);

        dealerImport(token);
    }


    public String testIsvLogin() throws Exception {
        String loginUrl = "http://ucode-test.aax6.cn/api-gateway/data/isv/login/v1";
        URL url = new URL(loginUrl);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        httpURLConnection.connect();
        String body = "{\"account\":\"17611111111\",\n" +
                "  \"password\":\"qwertyuiop123456\"}";
        //        String body = "{\"account\":\"wly\",\n" +
        //                "  \"password\":\"wuliangye1212345\"}";
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
        try {
            writer.write(body);
            writer.flush();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                try {
                    String line;
                    StringBuffer result = new StringBuffer();
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    System.out.println(result);
                    return result.toString();
                } finally {
                    reader.close();
                }
            } else {
                System.out.println(responseCode);
                throw new RuntimeException(httpURLConnection.getResponseMessage());
            }
        } finally {
            writer.close();
        }
    }


    public void testThirdCodeImport(String token) throws Exception {
        //复合表单类型的接口,文件和参数一起
        String thirdCodeImportUrl =
                "http://ucode-test.aax6.cn/api-gateway/code/isv/third-part-code-handle/package/v1?companyId=1000098";
        HttpPost post = new HttpPost(thirdCodeImportUrl);
        post.setHeader("Connection", "Keep-Alive");
        post.setHeader("Charset", "UTF-8");
        //        post.setHeader("Content-Type", "multipart/form-data"); 工具会自己识别，加了这句反而会找不到multipart boundary
        post.setHeader("Authorization", "Bearer " + token);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        String filePath = PathUtils.combinePath("src", "main", "resources", "thirdtest-3.zip");
        //当前不能超过20M
        multipartEntityBuilder.addBinaryBody("file", new File(filePath));

        Map<String, String> paramMap = new HashMap<>(5);
        paramMap.put("md5", "ace6b053e507a09a7c11da84a13bf704");
        paramMap.put("eseConfigId", "1000497");//配置ID 标明此批码的域名。（创建码配置生成的，界面可见）
        paramMap.put("batchName", "2019112615");//批次名字（后台会生成第三方码批次）
        paramMap.put("purpose", "TEST_USE");
        paramMap.put("deptId", "1000251");//部门ID？暂留如何获取
        //拼接参数
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue(),
                    ContentType.create("text/plain", Charset.forName("UTF-8")));
        }
        HttpEntity httpEntity = multipartEntityBuilder.build();
        post.setEntity(httpEntity);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.out.println("responseCode: " + responseCode + " ; msg:" + EntityUtils.toString(response.getEntity()));
        }
    }

    public void codeRelativeImport(String token) throws Exception {
        //复合表单类型的接口,文件和参数一起
        StringBuffer preUrl = new StringBuffer("http://ucode-test.aax6.cn/api-gateway/dm/isv/links/v1/logs/file?");

        String codeRelativeImportUrl = preUrl.toString();

        HttpPost post = new HttpPost(codeRelativeImportUrl);
        post.setHeader("Connection", "Keep-Alive");
        post.setHeader("Charset", "UTF-8");
        post.setHeader("Authorization", "Bearer " + token);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
        String filePath = PathUtils.combinePath("src", "main", "resources", "thirdtest-3.txt");
        multipartEntityBuilder.addBinaryBody("file", new File(filePath));
        multipartEntityBuilder.addTextBody("companyId","1000098");
        //        multipartEntityBuilder.addTextBody("linkCode","portalMaterialBind");//平台指定
        multipartEntityBuilder.addTextBody("linkCode","portalMaterialAct");//平台指定
        multipartEntityBuilder.addTextBody("unBinding","true");//
        multipartEntityBuilder.addTextBody("parentType","PRODUCT");
        multipartEntityBuilder.addTextBody("codeType","UCODE");
        multipartEntityBuilder.addTextBody("childrenAppend","true");
        multipartEntityBuilder.addTextBody("brotherAppend","false");
        multipartEntityBuilder.addTextBody("partitionKey","QA.AAX6.CN/Q");
        multipartEntityBuilder.addTextBody("replacementKey","HTTP://QA.AAX6.CN/Q/");
        multipartEntityBuilder.addTextBody("codeSourceType","CODE_IMPORT");
        multipartEntityBuilder.addTextBody("checkRule","presingle_skip_Ccs>8");
        multipartEntityBuilder.addTextBody("dataJson", "{\n" + "    \"productCode\": 1000196,\n"
                + "    \"productBatch\": \"20191122\",\n" + "    \"productDate\": 1574398078000,\n"
                + "    \"outDate\": 1574398078000,\n" + "    \"dealerCode\": \"11111\"\n" + "  }");


        HttpEntity httpEntity = multipartEntityBuilder.build();
        post.setEntity(httpEntity);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.out.println("responseCode: " + responseCode + " ; msg:" + EntityUtils.toString(response.getEntity()));
        }

    }

    public void outDataImport(String token) throws Exception {
        StringBuffer preUrl = new StringBuffer("http://ucode-test.aax6.cn/api-gateway/dm/isv/links/v1/logs?companyId=1000098");
        String outImportUrl = preUrl.toString();
        HttpPost post = new HttpPost(outImportUrl);
        post.setHeader("Connection", "Keep-Alive");
        post.setHeader("Charset", "UTF-8");
        post.setHeader("Authorization", "Bearer " + token);

        String json = "{\n" +
                "    \"linkCode\":\"portalMaterialAct\",\n" +
                "    \"unBinding\":false,\n" +
                "    \"codeType\":\"UCODE\",\n" +
                "    \"codeSourceType\":\"CODE_IMPORT\",\n" +
                "    \"dataJson\":{\"productID\": 1000196, \"productFactoryID\": 1000103},\n" +
                "    \"tags\":[\"HTTP://QA.AAX6.CN/Q/BI+.R$-S1AOS*Y5LV.Z\"]\n" +
                "}";
        StringEntity stringEntity = new StringEntity(json, "UTF-8");
        stringEntity.setContentType("application/json");
        post.setEntity(stringEntity);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.out.println("responseCode: " + responseCode + " ; msg:" + EntityUtils.toString(response.getEntity()));
        }

    }

    public void productImport(String token) throws Exception{
        StringBuffer preUrl = new StringBuffer("http://ucode-test.aax6.cn/api-gateway/data/isv/products/v1?companyId=1000105");
        String outImportUrl = preUrl.toString();
        HttpPost post = new HttpPost(outImportUrl);
        post.setHeader("Connection", "Keep-Alive");
        post.setHeader("Charset", "UTF-8");
        post.setHeader("Authorization", "Bearer " + token);

        String json = "[{\n" +
                "\t\"name\": \"绵柔尖庄\",\n" +
                "\t\"status\": \"ENABLE\",\n" +
                "\t\"info\": \"\",\n" +
                "\t\"images\": \"\",\n" +
                "\t\"secondCategory\": 1000143,\n" +
                "\t\"productId\": 375,\n" +
                "\t\"deptId\": \"1000251\",\n" +
                "\t\"category\": 1000142,\n" +
                "\t\"brandId\": 147,\n" +
                "\t\"barcode\": \"1321\",\n" +
                "\t\"barcodeBrand\": \"1231\",\n" +
                "\t\"salesPrice\": 0.0,\n" +
                "\t\"usefulLife\": 1000,\n" +
                "\t\"lifeUnit\": \"DAY\",\n" +
                "\t\"systemCustom\": \"qwqwqw\",\n" +
                "\t\"userCustom\": \"qwqwqwqw\"\n" +
                "}]";
        StringEntity stringEntity = new StringEntity(json, "UTF-8");
        stringEntity.setContentType("application/json");
        post.setEntity(stringEntity);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.out.println("responseCode: " + responseCode + " ; msg:" + EntityUtils.toString(response.getEntity()));
        }

    }

    private void dealerImport(String token) throws Exception{
        StringBuffer preUrl = new StringBuffer("http://ucode-test.aax6.cn/api-gateway/data/isv/dealers/v1?companyId=1000098");
        String outImportUrl = preUrl.toString();
        HttpPost post = new HttpPost(outImportUrl);
        post.setHeader("Connection", "Keep-Alive");
        post.setHeader("Charset", "UTF-8");
        post.setHeader("Authorization", "Bearer " + token);


        String json = "[{\n" +
                "\t\"name\": \"五粮液系列酒品牌营销公司职工酒2\",\n" +
                "\t\"level\": 1,\n" +
                "\t\"status\": \"DISABLE\",\n" +
                "\t\"code\": \"153712\",\n" +
                "\t\"sellerId\": 53711,\n" +
                "\t\"deptId\": \"1000251\",\n" +
                "\t\"dealerType\": \"RETAIL\"\n" +
                "}]";
        //        String json = "[{\n" + "\t\"name\": \"防邪办\",\n" + "\t\"level\": 1,\n" + "\t\"deptId\": \"1000251\",\n"
        //                + "\t\"sellerId\": 53713,\n" + "\t\"dealerType\": \"RETAIL\",\n" + "\t\"parentId\": 0,\n"
        //                + "\t\"status\": \"DISABLE\",\n" + "\t\"code\": \"153713\"\n" + "}, {\n" + "\t\"name\": \"未分配部门\",\n"
        //                + "\t\"level\": 1,\n" + "\t\"deptId\": \"1000251\",\n" + "\t\"sellerId\": 53714,\n"
        //                + "\t\"dealerType\": \"RETAIL\",\n" + "\t\"parentId\": 0,\n" + "\t\"status\": \"DISABLE\",\n"
        //                + "\t\"code\": \"153714\"\n" + "}]";
        StringEntity stringEntity = new StringEntity(json, "UTF-8");
        stringEntity.setContentType("application/json");
        post.setEntity(stringEntity);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.out.println("responseCode: " + responseCode + " ; msg:" + EntityUtils.toString(response.getEntity()));
        }
    }
}
