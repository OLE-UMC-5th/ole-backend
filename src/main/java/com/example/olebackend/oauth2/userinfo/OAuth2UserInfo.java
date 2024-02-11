package com.example.olebackend.oauth2.userinfo;

import java.util.Map;
public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;
    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();
    public abstract String getNickname();
//    public abstract String getEmail();
//    public abstract String getGender();

    // 기타 생년월일 등 받아오기
}
