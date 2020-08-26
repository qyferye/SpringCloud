package com.cloud.core.security.jwt;

import com.cloud.core.common.constant.SecurityConstant;
import com.cloud.core.common.util.ResponseUtil;
import com.cloud.core.properties.XbootTokenProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private XbootTokenProperties tokenProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = ((UserDetails)authentication.getPrincipal()).getUsername();

        // 登陆成功生成token
        String token;
            // jwt
            token = SecurityConstant.TOKEN_SPLIT + Jwts.builder()
                    //主题 放入用户名
                    .setSubject(username)
                    //自定义属性 放入用户拥有请求权限
                    //失效时间
                    .setExpiration(new Date(System.currentTimeMillis() + tokenProperties.getTokenExpireTime() * 60 * 10000000))
                    //签名算法和密钥
                    .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                    .compact();
        ResponseUtil.out(response, ResponseUtil.resultMap(true,200,"登录成功", token));
    }
}

