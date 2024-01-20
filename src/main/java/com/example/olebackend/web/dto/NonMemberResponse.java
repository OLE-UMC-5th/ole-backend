package com.example.olebackend.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class NonMemberResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplyResultDTO{
        Long nonMemberId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getApplicationsResultDTO{

        String title; //강의 제목
        LocalDateTime lessonStartDate; // 강의 시작일
        LocalDateTime lessonEndDate; // 강의 종료일
        LocalDateTime lessonStartTime; // 강의 시작 시간
        LocalDateTime lessonEndTime; // 강의 종료 시간
        List<LessonResponse.getTeacherNameDTO> lessonTeacherList; // 강사
        String place; //강의 장소
    }

}
