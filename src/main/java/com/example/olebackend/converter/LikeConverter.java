package com.example.olebackend.converter;

import com.example.olebackend.domain.File;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.SubCategory;
import com.example.olebackend.web.dto.LikeResponse;
import com.example.olebackend.web.dto.SurveyResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class LikeConverter {

    /*
    설문조사
     */
    public static LikeResponse.LikeResponseDto toLikeResponse(Lesson lesson){

        // '대표 이미지여부' 속성이 true인 파일 필터링
        Optional<File> representativeFile = lesson.getFileList()
                .stream()
                .filter(e -> e.isRepresent())
                .findAny() ;

        // 대표 이미지가 존재하는 경우에만 imgUrl을 설정, 아니면 null
        String imgUrl =  representativeFile.map(File::getPath).orElse(null);

        return LikeResponse.LikeResponseDto.builder()
                .lessonId(lesson.getId())
                .title(lesson.getTitle())
                .startDate(lesson.getLessonStartDate())
                .endDate(lesson.getLessonEndDate())
                .startTime(lesson.getLessonStartTime())
                .endTime(lesson.getLessonEndTime())
                .imgUrl(imgUrl)
                .leftDays((int)ChronoUnit.DAYS.between(LocalDate.now(), lesson.getGatherEndDate()))
                .build();
    }


}
