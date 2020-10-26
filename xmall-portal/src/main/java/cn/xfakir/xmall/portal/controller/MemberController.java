package cn.xfakir.xmall.portal.controller;

import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.entity.XmMemberAuthorization;
import cn.xfakir.xmall.common.mapper.XmMemberAuthorizationMapper;
import cn.xfakir.xmall.common.sms.Auth;
import cn.xfakir.xmall.common.sms.MessageManager;
import cn.xfakir.xmall.common.system.Xresult;
import cn.xfakir.xmall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private XmMemberAuthorizationMapper authorizationMapper;


    @RequestMapping("/signUp")
    public Xresult signUp(XmMember member) {
        memberService.signUp(member);
        return null;
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(XmMemberAuthorization xmMemberAuthorization) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String credential = xmMemberAuthorization.getCredential();
        xmMemberAuthorization.setCredential(passwordEncoder.encode(credential));
        authorizationMapper.updateCredential(xmMemberAuthorization);
        return "success";
    }

    @RequestMapping("/getMember")
    public XmMember getMember(Long id) {
        return memberService.getMemberById(id);
    }

    @RequestMapping("/getMemberByPwd")
    public XmMember getMember(XmMemberAuthorization xmMemberAuthorization) {
        return memberService.getMember(xmMemberAuthorization);
    }

    /*@RequestMapping("/test")
    public String send(String phone) {
        System.out.println(ACCESS_KEY);
        Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
        MessageManager messageManager = new MessageManager(auth);
        Map<String,String> map = new HashMap<>();
        map.put("code","1023");
        messageManager.sendMessage("1154931928005087232",new String[] {"18679159286"},map);
        return "success";
    }*/
}
