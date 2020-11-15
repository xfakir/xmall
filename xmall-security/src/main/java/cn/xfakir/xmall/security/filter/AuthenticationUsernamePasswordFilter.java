package cn.xfakir.xmall.security.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class AuthenticationUsernamePasswordFilter extends AbstractAuthenticationProcessingFilter  {
    private boolean postOnly = true;
    private String usernameParameter = "username";
    private String passwordParameter = "password";

    @Autowired
    private ObjectMapper objectMapper;

    public AuthenticationUsernamePasswordFilter() {
        super(new AntPathRequestMatcher("/test/login", "POST"));
    }


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String params = getRequestPayload(request);
            if (params != null && !params.equals("")) {
                Map<String,String> paramMap = objectMapper.readValue(params, Map.class);
                String username = paramMap.get(this.usernameParameter);
                String password = paramMap.get(this.passwordParameter);
                System.out.println("username: " + username);
                System.out.println("password: " + password);
                if (username == null) {
                    username = "";
                }

                if (password == null) {
                    password = "";
                }

                username = username.trim();
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(null, null));
    }

    private String getRequestPayload(HttpServletRequest req) throws JsonProcessingException {
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
