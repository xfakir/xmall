package cn.xfakir.xmall.member.service;

import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.entity.XmMemberAuthorization;

public interface MemberService {
    void signUp(XmMember member);

    XmMember getMemberById(Long id);

    XmMember getMember(XmMemberAuthorization xmMemberAuthorization);
}
