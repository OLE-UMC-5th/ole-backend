package com.example.olebackend.web.dto;

import lombok.*;

import java.time.LocalDateTime;

public class LikeResponse {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LikeResponseDto{

        Long lessonId ;
        String imgUrl ;
        String title; // 수업명
        LocalDateTime startDate; // 강의 시작일
        LocalDateTime endDate; // 강의 종료일
        LocalDateTime startTime; // 강의 시작 시간
        LocalDateTime endTime; // 강의 종료 시간
        Integer leftDays ;
    } // SubCategoryDto

} // SurveyResponse
