package com.example.olebackend.oauth2.handler;

import com.example.olebackend.domain.enums.Role;
import com.example.olebackend.jwt.service.JwtService;
import com.example.olebackend.oauth2.CustomOAuth2User;
import com.example.olebackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        log.info("OAuth2 Login Success");
        try{
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            // 최초 OAuth 로그인 시 Guest, 그리고 회원가입 페이지로 Redirect
            if(oAuth2User.getRole() == Role.GUEST){
                String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
                String redirectUrl = "http://localhost:3000/auth/socialJoin?accessToken=" + accessToken;
                response.addHeader(jwtService.getAccessHeader(), "Bearer "+ accessToken);
                response.sendRedirect(redirectUrl);

                jwtService.sendAccessAndRefreshToken(response, accessToken, null);
            }else{
                loginSuccess(response, oAuth2User);
            }
        }catch(Exception e){
            throw e;
        }
    }
    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtService.createRefreshToken();
        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
    }
}


