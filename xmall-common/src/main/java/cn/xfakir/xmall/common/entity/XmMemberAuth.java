package cn.xfakir.xmall.common.entity;

import lombok.Data;

@Data
public class XmMemberAuth {
    private Long memberAuthId;
    private Long memberId;
    private String name;
    private String idCard;
    private String authTime;
}
