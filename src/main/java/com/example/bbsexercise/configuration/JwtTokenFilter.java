package com.example.bbsexercise.configuration;

import com.example.bbsexercise.domain.entitiy.User;
import com.example.bbsexercise.service.UserService;
import com.example.bbsexercise.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request 토큰 없으면 거절
        final String requestHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("requestHeader={}", requestHeader);

        if (requestHeader != null || !requestHeader.startsWith("Bearer ")) {
            log.error("헤더를 가져오는 과정에서 에러가 났습니다. 헤더가 Null 이거나 잘못되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 추출에 실패할 경우 거절
        String token;
        try {
            token = requestHeader.split(" ")[1].trim();
        } catch (Exception e) {
            log.error("token 추출에 실패하였습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰이 만료되어있을 경우 거절
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 허락하는 경우

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("", null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
