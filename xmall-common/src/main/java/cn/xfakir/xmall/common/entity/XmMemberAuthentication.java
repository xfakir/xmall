package cn.xfakir.xmall.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * 实名认证
 */
@Data
public class XmMemberAuthentication {
    private Long id;
    private Long memberId;
    private String name;
    private String idCard;
    private Date createTime;
    private Date updateTime;
}
