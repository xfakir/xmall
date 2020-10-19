package cn.xfakir.xmall.common.sms;

import cn.hutool.http.HttpRequest;
import com.qiniu.http.Client;
import com.qiniu.http.MethodType;
import com.qiniu.util.Json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MessageManager {
    /**
     * Auth 对象
     * 该类需要使用鉴权，所以需要指定Auth对象
     */
    private final Auth auth;
    /**
     * Configuration 对象
     * 该类相关的域名配置，解析配置，HTTP请求超时时间设置等
     */
    private SmsConfig smsConfig;

    /**
     * 构建一个新的 SmsManager 对象
     *
     * @param auth Auth对象
     */
    public MessageManager(Auth auth) {
        this.auth = auth;
        this.smsConfig = new SmsConfig();
    }

    public MessageManager(Auth auth, SmsConfig cfg) {
        this.auth = auth;
        this.smsConfig = cfg.clone();
    }

    public void sendMessage(String templateId, String[] mobiles, Map<String, String> parameters) {
        String requestUrl = String.format("%s/v1/message", smsConfig.smsHost());
        StringMap bodyMap = new StringMap();
        bodyMap.put("template_id", templateId);
        bodyMap.put("mobile", mobiles);
        bodyMap.put("parameters", parameters);
        Map<String,Object> map = new HashMap<>();
        map.put("template_id", templateId);
        map.put("mobiles", mobiles);
        map.put("parameters", parameters);
        post(requestUrl, Json.encode(map).getBytes());
    }
    private void post(String url, byte[] body) {
        Map<String,String> headers = composeHeader(url, MethodType.POST.toString(), body, "application/json");
        String result = HttpRequest.post(url)
                .headerMap(headers,true)
                .body(body)
                .execute()
                .body();
        System.out.println(headers);
        System.out.println(result);
    }

    private Map<String,String> composeHeader(String url, String method, byte[] body, String contentType) {
        StringMap headers = auth.authorizationV2(url, method, body, contentType);
        headers.put("Content-Type", contentType);
        Map<String,String> map = new HashMap<>();
        Set set = headers.keySet();
        for (Iterator iterator = set.iterator();iterator.hasNext();) {
            String key = (String) iterator.next();
            String value = (String) headers.get(key);
            map.put(key,value);
        }
        return map;
    }
}
