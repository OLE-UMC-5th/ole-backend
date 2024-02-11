package com.example.olebackend.converter;

import com.example.olebackend.domain.*;
import com.example.olebackend.web.dto.MemberResponse;

public class MemberConverter {
    public static MemberResponse.getJoinResultDTO toJoinResultDTO(Long memberId){
        return MemberResponse.getJoinResultDTO.builder()
                .memberId(memberId)
                .build();
    }
    public static MemberResponse.getLoginResultDTO toLoginResultDTO(Long memberId){
        return MemberResponse.getLoginResultDTO.builder()
                .memberId(memberId)
                .build();
    }
    public static MemberResponse.getLoginResultDTO toLoginResultDTO(){
        return MemberResponse.getLoginResultDTO.builder()
                .build();
    }
}
