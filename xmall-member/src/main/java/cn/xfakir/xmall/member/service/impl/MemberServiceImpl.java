package cn.xfakir.xmall.member.service.impl;

import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.mapper.XmMemberMapper;
import cn.xfakir.xmall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private XmMemberMapper memberMapper;

    @Override
    public void signUp(XmMember member) {
        memberMapper.insert(member);
    }
}
