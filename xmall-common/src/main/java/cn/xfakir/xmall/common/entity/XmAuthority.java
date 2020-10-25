package cn.xfakir.xmall.common.entity;

import org.springframework.security.core.GrantedAuthority;

public class XmAuthority implements GrantedAuthority {
    private String authorityName;

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String getAuthority() {
        return this.authorityName;
    }
}
