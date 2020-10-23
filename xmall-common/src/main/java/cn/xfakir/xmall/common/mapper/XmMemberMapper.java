package cn.xfakir.xmall.common.mapper;

import cn.xfakir.xmall.common.config.BaseMapper;
import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.entity.XmMemberAuthorization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface XmMemberMapper extends BaseMapper<XmMember> {
    XmMember getMemberByAuthorization(XmMemberAuthorization xmMemberAuthorization);

    XmMember getMemberByMemberId(Long id);
}
