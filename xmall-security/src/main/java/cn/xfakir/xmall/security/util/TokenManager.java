package cn.xfakir.xmall.security.util;

import cn.xfakir.xmall.security.entity.SecurityMemeber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    @Autowired
    private RedisTemplate redisTemplate;

    public String getToken(SecurityMemeber securityMemeber) {
        return generateToken(securityMemeber.getMemberId());
    }

    private String generateToken(Long memberId) {
        return "";
    }


}
