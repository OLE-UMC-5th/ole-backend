package com.example.olebackend.web.dto;

import com.example.olebackend.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getMemberDetailDTO{
        // 다 가져와라
        String id;
        String email;
        String name;
        Long birthYear;
        String phoneNum;
        String address;
        Gender gender;
        Boolean mailAgree;
        Boolean smsAgree;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
    }
}
