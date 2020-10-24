package cn.xfakir.xmall.common.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class XmMemberAuthorization {
    private Long id;
    private Long memberId;
    private Integer authorizationType;
    private String identifier;
    private String credential;
    private List<String> roleList;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
