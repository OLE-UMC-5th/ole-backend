package com.example.olebackend.oauth2;

import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.enums.Role;
import com.example.olebackend.domain.enums.SocialType;
import com.example.olebackend.oauth2.userinfo.KakaoOAuth2UserInfo;
import com.example.olebackend.oauth2.userinfo.NaverOAuth2UserInfo;
import com.example.olebackend.oauth2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {
    private String nameAttributeKey;
    private OAuth2UserInfo oauth2UserInfo;

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo){
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    public static OAuthAttributes of(SocialType socialType, String userNameAttributeName,
                                     Map<String,Object> attributes){
        if(socialType == SocialType.NAVER){
            return ofNaver(userNameAttributeName, attributes);
        }
        return ofKakao(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }
    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }
    public Member toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        return Member.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .name(oauth2UserInfo.getName())
                .role(Role.USER)
                .build();
    }


}
