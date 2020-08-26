package com.cloud.core.security.jwt;

import cn.hutool.core.util.StrUtil;
import com.cloud.core.common.constant.SecurityConstant;
import com.cloud.core.common.util.ResponseUtil;
import com.cloud.core.properties.XbootTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter   {
    private XbootTokenProperties tokenProperties;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, XbootTokenProperties tokenProperties) {
        super(authenticationManager);
        this.tokenProperties = tokenProperties;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //从头中获取参数accessToken
        String header = request.getHeader(SecurityConstant.HEADER);
        if(StringUtils.isBlank(header)){
            //头中没有从参数中获取accessToken
            header = request.getParameter(SecurityConstant.HEADER);
        }                                                   //不使用redis存储token                          token不是以  "Bearer "开头
        Boolean notValid = StringUtils.isBlank(header) || !header.startsWith(SecurityConstant.TOKEN_SPLIT);
        if (notValid) {
            chain.doFilter(request, response);
           // ResponseUtil.out(response,ResponseUtil.resultMap(false,401,"认证失败,请输入token!"));
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = checkToken(header, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            e.toString();
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken checkToken(String header, HttpServletResponse response) {
        String username = "";
        try {
                // 解析token
                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                        .parseClaimsJws(header.replace(SecurityConstant.TOKEN_SPLIT, ""))
                        .getBody();
                // 获取用户名
                  username = claims.getSubject();
            } catch (ExpiredJwtException e) {
                ResponseUtil.out(response, ResponseUtil.resultMap(false,401,"登录已失效，请重新登录"));
            } catch (Exception e){
                log.error(e.toString());
                ResponseUtil.out(response, ResponseUtil.resultMap(false,500,"解析token错误"));
            }

        if(StrUtil.isNotBlank(username)) {
            //Exrick踩坑提醒 此处password不能为null
            User principal = new User(username, "", new ArrayList<>());
            List<GrantedAuthority> list =new ArrayList<>();
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(principal, null, list);
        }
        return null;
    }
}

