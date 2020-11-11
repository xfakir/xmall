package cn.xfakir.xmall.portal.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/product")
    public String getProduct() {
        return "product";
    }

    @RequestMapping("/order")
    public String getOrder(){
        return "order";
    }

    @RequestMapping("/code")
    public String callBack(@RequestParam String code) {
        String url = "http://localhost:8080/oauth/token";
        System.out.println(code);
        Map<String, Object> param = new HashMap<>();
        param.put("code",code);
        param.put("grant_type","authorization_code");
        param.put("redirect_url","http://localhost:3000/callback");
        param.put("scope","read_user_info");
        String result = HttpRequest.post(url).form(param).basicAuth("client-a","client-a-secret").execute().body();
        return result;
    }

}
