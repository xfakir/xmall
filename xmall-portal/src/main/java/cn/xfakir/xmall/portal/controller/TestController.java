package cn.xfakir.xmall.portal.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.xfakir.xmall.common.web.XmResult;
import cn.xfakir.xmall.security.entity.SecurityMemeber;
import org.springframework.security.core.context.SecurityContext;
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

    @RequestMapping("/signIn")
    public XmResult signIn(@RequestParam String username,@RequestParam String password){
        System.out.println(username);
        System.out.println(password);
        Map<String ,String > map = new HashMap<>();
        map.put("token","token");
        return new XmResult(200,"success",map);
    }

    @RequestMapping("/user")
    public XmResult user() {
        Map<String ,String > map = new HashMap<>();
        map.put("username","smm");
        return new XmResult(200,"success",map);
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
