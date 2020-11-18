package cn.xfakir.xmall.security.filter;

import cn.xfakir.xmall.security.entity.SecurityMemeber;
import cn.xfakir.xmall.security.service.XmUserDetailService;
import cn.xfakir.xmall.security.util.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    private static final String FILTER_APPLIED = "__spring_security_demoFilter_filterApplied";

    private final XmUserDetailService userDetailService;
    private final String tokenHeader;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthorizationTokenFilter(XmUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
        this.tokenHeader = "Authorization";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);
        SecurityMemeber memeber = null;
        if (requestHeader != null) {
            String token = requestHeader.trim();
            memeber = tokenManager.getMemberByToken(token);
        }
        if (Objects.nonNull(memeber)) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(memeber,memeber.getPassword(),memeber.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        /*String params = getRequestPayload(httpServletRequest);
        if (params != null && !params.equals("")) {
            Map<String,String> paramMap = objectMapper.readValue(params, Map.class);
            String username = paramMap.get("username");
            String password = paramMap.get("password");
            System.out.println("------------------");
            System.out.println(username);
            System.out.println(password);
            System.out.println("------------------");
            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            authRequest.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }*/

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = req.getReader();) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
