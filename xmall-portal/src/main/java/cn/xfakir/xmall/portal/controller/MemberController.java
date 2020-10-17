package cn.xfakir.xmall.portal.controller;

import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/signUp")
    public Xresult signUp(XmMember member) {
        memberService.signUp(member);
        return ;
    }
}
