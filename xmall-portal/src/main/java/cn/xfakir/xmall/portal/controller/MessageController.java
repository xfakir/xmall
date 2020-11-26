package cn.xfakir.xmall.portal.controller;

import cn.xfakir.xmall.common.uitls.RedisUtil;
import cn.xfakir.xmall.common.web.XmResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Random;

@Validated
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/code")
    public XmResult sendMessage(
            @Size(min = 11,max = 11,message = "error")
            @RequestParam(name = "phone",required = true) String phone
        ) {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(9999));
        HashMap<String,String> map = new HashMap<>();
        map.put("code",code);
        return new XmResult(200,"success",map);
    }
}
