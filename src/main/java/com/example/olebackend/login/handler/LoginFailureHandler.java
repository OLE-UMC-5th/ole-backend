package com.example.olebackend.login.handler;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.converter.MemberConverter;
import com.example.olebackend.web.dto.MemberResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 로그인 실패 시 처리하는 핸들러
 * SimpleUrlAuthenticationFailureHandler를 상속받아서 구현
 */
@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        log.info("로그인에 실패했습니다. 메시지 : {}", exception.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse<MemberResponse.getLoginResultDTO> apiResponse = loginFailureResponse();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        // HTTP 응답 본문에 ApiResponse JSON 문자열 작성
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonResponse);

    }
    public ApiResponse<MemberResponse.getLoginResultDTO> loginFailureResponse() {
        return ApiResponse.onFailure("401", "로그인 실패! 이메일이나 비밀번호를 확인해주세요.",
                null);
    }

}