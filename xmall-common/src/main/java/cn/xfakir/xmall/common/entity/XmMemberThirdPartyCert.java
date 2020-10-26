package cn.xfakir.xmall.common.entity;

import lombok.Data;

import java.util.Date;


@Data
public class XmMemberThirdPartyCert {
    private Long id;
    private Long memberId;
    private Integer authType;
    private String identifier;
    private String credential;
    private Date bindTime;
}
