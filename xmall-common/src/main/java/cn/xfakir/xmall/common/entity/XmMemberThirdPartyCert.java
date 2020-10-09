package cn.xfakir.xmall.common.entity;

import lombok.Data;


@Data
public class XmMemberThirdPartyCert {
    private Long memberThirdPartyCertId;
    private Long memberId;
    private Integer authType;
    private String identifier;
    private String credential;
    private String bindTime;
}
