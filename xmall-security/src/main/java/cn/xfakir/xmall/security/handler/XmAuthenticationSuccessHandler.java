package cn.xfakir.xmall.security.handler;

import cn.hutool.core.map.MapUtil;
import cn.xfakir.xmall.common.web.XmResult;
import cn.xfakir.xmall.security.entity.SecurityMemeber;
import cn.xfakir.xmall.security.util.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class XmAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        SecurityMemeber securityMemeber = (SecurityMemeber) authentication.getPrincipal();
        tokenManager.deleteToken(securityMemeber);
        String token = tokenManager.saveToken(securityMemeber);
        log.info("----------------用户'{}'登录成功，开始执行初始化-----------------------",securityMemeber.getUsername());
        httpServletResponse.getWriter()
                .write(objectMapper.writeValueAsString(new XmResult(200,"登录成功!", MapUtil.of(new String[][] {
                        {"token",token}
                }))));
    }
}
