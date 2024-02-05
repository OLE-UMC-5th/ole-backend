package com.example.olebackend.converter;

import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.enums.Attended;
import com.example.olebackend.domain.mapping.MemberApply;
import com.example.olebackend.web.dto.MemberApplyResponse;
import com.example.olebackend.web.dto.MemberResponse;

public class MemberApplyConverter {
    public static MemberApply toMemberApply(Lesson lesson, Member member){

        return MemberApply.builder()
                .lesson(lesson)
                .member(member)
                .attendanceStatus(Attended.ATTENDING)
                .build();
    }

    public static MemberApplyResponse.applyResultDTO toApplyresultDTO(MemberApply memebrApply){
        return MemberApplyResponse.applyResultDTO.builder()
                .memberApplyId(memebrApply.getId())
                .build();
    }
}