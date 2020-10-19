package cn.xfakir.xmall.portal.controller;

import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.sms.Auth;
import cn.xfakir.xmall.common.sms.MessageManager;
import cn.xfakir.xmall.common.system.Xresult;
import cn.xfakir.xmall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Value("${qiniu.access_key}")
    private String ACCESS_KEY;

    @Value("${qiniu.secret_key}")
    private String SECRET_KEY;

    @RequestMapping("/signUp")
    public Xresult signUp(XmMember member) {
        memberService.signUp(member);
        return null;
    }

    @RequestMapping("/test")
    public String send(String phone) {
        System.out.println(ACCESS_KEY);
        Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
        MessageManager messageManager = new MessageManager(auth);
        Map<String,String> map = new HashMap<>();
        map.put("code","1023");
        messageManager.sendMessage("1154931928005087232",new String[] {"18679159286"},map);
        return "success";
    }
}
