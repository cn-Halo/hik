package com.hik.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzm on 2017/8/30.
 */
public class HttpUtil {

    public static HttpClient getClient() {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(100000);
        connectionManager.getParams().setSoTimeout(100000);
        HttpClient httpclient = new HttpClient(connectionManager);
        return httpclient;
    }

    public static Map<String, Object> post(String url, NameValuePair[] parametesrBody) throws IOException {
        Map<String, Object> re = new HashMap<String, Object>();
        HttpClient httpclient = getClient();
        PostMethod post = new PostMethod(url);
        post.setRequestBody(parametesrBody);
        int result = httpclient.executeMethod(post);
        re.put("statusCode", result);
        re.put("responseBody", JSON.parseObject(post.getResponseBodyAsString()));
        return re;
    }
}
