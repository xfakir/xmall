package cn.xfakir.xmall.member.service.impl;

import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.entity.XmMemberAuthorization;
import cn.xfakir.xmall.common.mapper.XmMemberMapper;
import cn.xfakir.xmall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private XmMemberMapper memberMapper;

    @Override
    public void signUp(XmMember member) {
        Date date = new Date();
        member.setCreateTime(date);
        memberMapper.insert(member);
    }

    @Override
    public XmMember getMemberById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public XmMember getMember(XmMemberAuthorization xmMemberAuthorization) {
        return memberMapper.getMemberByAuthorization(xmMemberAuthorization);
    }
}
