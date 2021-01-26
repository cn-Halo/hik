package com.hik.service;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzm on 2019/8/10.
 */
public class HikService {

    static {
        ArtemisConfig.host = "10.8.76.211"; // 代理API网关nginx服务器ip端口
        ArtemisConfig.appKey = "25091766";  // 秘钥appkey
        ArtemisConfig.appSecret = "xpKnXx7pNB2mVRbCQOKb";// 秘钥appSecret
    }

    private final static String ARTEMIS_PATH = "/artemis";

    public static String door(JSONObject jsonBody) {

        final String getCamsApi = ARTEMIS_PATH + "/api/acs/v1/door/events";
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", getCamsApi);//根据现场环境部署确认是http还是https
            }
        };

        String body = jsonBody.toJSONString();
        String result = ArtemisHttpUtil
            .doPostStringArtemis(path, body, null, null, "application/json",
                null);// post请求application/json类型参数
        return result;
    }

}
