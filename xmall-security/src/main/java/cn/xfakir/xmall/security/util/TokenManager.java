package cn.xfakir.xmall.security.util;

import cn.hutool.core.codec.Base64;
import cn.xfakir.xmall.common.uitls.RedisUtil;
import cn.xfakir.xmall.security.entity.SecurityMemeber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Component
public class TokenManager {
    private static final String MEMBER_TOKEN = "MEMBER_TOKEN";

    @Autowired
    private RedisUtil redisUtil;

    public String getToken(SecurityMemeber securityMemeber) {
        return generateToken(securityMemeber.getMemberId());
    }

    public String generateToken(Long memberId) {
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append("xmToken:")
                .append(memberId).append(":")
                .append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())).append(":")
                .append(new Random().nextInt((999999 - 111111 + 1)) + 111111);
        return Base64.encode(tokenBuilder);
    }

    public String saveToken(SecurityMemeber member) {
        String token = generateToken(member.getMemberId());
        //redisUtil.zsetAdd(MEMBER_TOKEN,token, (double) System.currentTimeMillis());
        redisUtil.set(token,member,60*60*24);
        return token;
    }

    public void deleteToken(SecurityMemeber memeber) {
        Object object = redisUtil.get(String.valueOf(memeber.getMemberId()));
        if (object != null) {
            String token = (String) object;
            redisUtil.del(token);
        }
    }

    public SecurityMemeber getMemberByToken(String token) {
        SecurityMemeber memeber = (SecurityMemeber) redisUtil.get(token);
        return Objects.isNull(memeber)?memeber:null;
    }


}
