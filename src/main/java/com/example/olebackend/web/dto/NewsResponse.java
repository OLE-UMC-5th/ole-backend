package com.example.olebackend.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class NewsResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getNewsPreviewDTO {

        Long id;
        LocalDateTime createdAt;
        String category;
        String title;
        String content;
        String author;
        String filePath;


    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getNewsListDTO {

        List<NewsResponse.getNewsPreviewDTO> newsList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getNewsDetailDTO {

        String title;
        String content;
        String category;
        String author;
        LocalDateTime createdAt;
        List<NewsResponse.getNewsFileDTO> fileList;
        int views;

    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getNewsFileDTO {

        String filePath;

    }

}
