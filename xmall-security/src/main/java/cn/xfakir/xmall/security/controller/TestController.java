package cn.xfakir.xmall.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
