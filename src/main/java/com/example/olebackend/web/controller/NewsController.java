package com.example.olebackend.web.controller;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.converter.NewsConverter;
import com.example.olebackend.domain.News;
import com.example.olebackend.domain.enums.NewsCategory;
import com.example.olebackend.service.NewsService;
import com.example.olebackend.web.dto.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    @Operation(summary = "올래생활뉴스 목록 조회 API")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호이며, 1페이지부터 시작입니다."),
    })
    public ApiResponse<NewsResponse.getNewsListDTO> getNewsList(@RequestParam(required = false) NewsCategory category,
                                                                @RequestParam(required = false, defaultValue = "1") Integer page) {
        Page<News> newsList = newsService.getNewsList(category, page);
        return ApiResponse.onSuccess(NewsConverter.toNewsListDTO(newsList));
    }

    @GetMapping("/{newsId}")
    @Operation(summary = "올래생활뉴스 글 상세 조회 API")
    public ApiResponse<NewsResponse.getNewsDetailDTO> getNewsDetail(@PathVariable Long newsId) {
        Optional<News> news = newsService.getNewsDetail(newsId);
        return ApiResponse.onSuccess(NewsConverter.toNewsDetailDTO(news));
    }
}
