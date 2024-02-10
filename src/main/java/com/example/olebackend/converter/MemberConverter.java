package com.example.olebackend.converter;

import com.example.olebackend.domain.*;
import com.example.olebackend.web.dto.MemberResponse;

public class MemberConverter {
    public static MemberResponse.JoinResultDTO toJoinResultDTO(Long memberId){
        return MemberResponse.JoinResultDTO.builder()
                .memberId(memberId)
                .build();
    }
}
