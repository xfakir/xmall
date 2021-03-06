package cn.xfakir.xmall.common.entity;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class XmMember {
    @Id
    private Long id;
    private Long memberId;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
