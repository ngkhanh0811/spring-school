package com.example.springschool.filter;

import com.example.springschool.entity.User;
import com.example.springschool.service.impl.JWTUtils;
import com.example.springschool.service.impl.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenFilter extends UsernamePasswordAuthenticationFilter {
    private static final String TOKEN_HEADER = "Authorization";
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserService userService;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;


        String token = httpRequest.getHeader(TOKEN_HEADER);

        if (!StringUtils.isEmpty(token))
        {
            String username = jwtUtils.getUsernameFromToken(token);
            User user = userService.getByName(username);
            // todo: check time of failed login
            if (user!=null){
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;
                // Lấy ra thông tin chi tiết người dùng
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                        enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, user.getAuthorities());
                // Sử dụng authentication bằng username, password
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                // Set giá trị username password vào details source
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            //todo: is token valid?
            //todo: is user exist?
        }
        chain.doFilter(request, response);
    }
}
