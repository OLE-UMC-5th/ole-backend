package com.example.olebackend.web.controller;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.converter.CommunityConverter;
import com.example.olebackend.domain.Community;
import com.example.olebackend.service.CommunityService;
import com.example.olebackend.web.dto.CommunityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;
    @GetMapping
    @Operation(summary = "소통하러올래 목록 조회 API")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호이며, 1페이지부터 시작입니다."),
    })
    public ApiResponse<CommunityResponse.getCommunityListDTO> getCommunityList(@RequestParam(required = false, defaultValue = "1") Integer page) {
        Page<Community> communityList = communityService.getCommunityList(page);
        return ApiResponse.onSuccess(CommunityConverter.toCommunityListDTO(communityList));
    }
}