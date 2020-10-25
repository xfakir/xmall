package cn.xfakir.xmall.portal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
