package cn.xfakir.xmall.common.entity;

import lombok.Data;

@Data
public class XmMember {
    private Long memberId;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private Integer status;
    private String signInTime;
}
