package cn.xfakir.xmall.common.mapper;

import cn.xfakir.xmall.common.config.BaseMapper;
import cn.xfakir.xmall.common.entity.XmMemberAuthorization;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XmMemberAuthorizationMapper extends BaseMapper<XmMemberAuthorization> {
    XmMemberAuthorization getAuthorizationByIdentifier(String identifier);
}
