package cn.xfakir.xmall.security.service;

import cn.xfakir.xmall.common.entity.XmAuthority;
import cn.xfakir.xmall.common.entity.XmMember;
import cn.xfakir.xmall.common.entity.XmMemberAuthorization;
import cn.xfakir.xmall.common.entity.XmRole;
import cn.xfakir.xmall.common.mapper.XmMemberAuthorizationMapper;
import cn.xfakir.xmall.common.mapper.XmMemberMapper;
import cn.xfakir.xmall.security.entity.SecurityMemeber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class XmUserDetailService implements UserDetailsService {

    @Autowired
    private XmMemberAuthorizationMapper xmMemberAuthorizationMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        XmMemberAuthorization memberAuthorization = xmMemberAuthorizationMapper.getAuthorizationByIdentifier(s);
        if (memberAuthorization == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        for (XmRole role : memberAuthorization.getRoleList()) {
            list.add(role);
            list.addAll(role.getAuthorityList());
        }
        SecurityMemeber securityMemeber = new SecurityMemeber(memberAuthorization.getIdentifier(),memberAuthorization.getCredential(),true,true,true,true,list);
        securityMemeber.setMemberId(memberAuthorization.getMemberId());
        return securityMemeber;
    }
}
