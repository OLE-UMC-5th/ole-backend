package com.example.olebackend.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo{
    public KakaoOAuth2UserInfo(Map<String, Object> attributes){
        super(attributes);
    }

    @Override
    public String getId(){
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname(){
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        if(account == null){
            return null;
        }
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if(profile == null){
            return null;
        }
        return (String) profile.get("nickname");
    }
//    @Override
//    public String getEmail(){
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        if(response == null){
//            return null;
//        }
//        return(String) response.get("email");
//    }
//    @Override
//    public String getGender(){
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        if(response == null){
//            return null;
//        }
//        return(String) response.get("gender");
//    }

}
