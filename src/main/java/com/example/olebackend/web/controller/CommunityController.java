package com.example.olebackend.web.controller;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.converter.CommunityConverter;
import com.example.olebackend.domain.Community;
import com.example.olebackend.domain.enums.CommunityCategory;
import com.example.olebackend.service.CommunityService;
import com.example.olebackend.web.dto.CommunityRequest;
import com.example.olebackend.web.dto.CommunityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    @Operation(summary = "소통하러올래 목록 조회 API")
    @Parameters({
            @Parameter(name = "category", description = "소통하러올래 게시글 필터링 기준입니다. (궁금해요, 같이해요)"),
            @Parameter(name = "page", description = "페이지 번호이며, 1페이지부터 시작입니다."),
    })
    public ApiResponse<CommunityResponse.getCommunityListDTO> getCommunityList(@RequestParam(required = false) CommunityCategory category,
                                                                               @RequestParam(required = false, defaultValue = "1") Integer page) {
        Page<Community> communityList = communityService.getCommunityList(category, page);
        return ApiResponse.onSuccess(CommunityConverter.toCommunityListDTO(communityList));
    }

    @GetMapping("/{communityId}")
    @Operation(summary = "소통하러올래 게시글 상세 조회 API")
    public ApiResponse<CommunityResponse.getCommunityDetailDTO> getCommunityDetail(@PathVariable Long communityId) {
        Optional<Community> community = communityService.getCommunity(communityId);
        return ApiResponse.onSuccess(CommunityConverter.toCommunityDetailDTO(community));
    }

    @PostMapping("/{communityId}/comments")
    @Operation(summary = "소통하러올래 댓글 작성 API")
    public ApiResponse<Object> addCommunityComments(@PathVariable Long communityId,
                                                    @RequestParam(name = "memberId") Long memberId,
                                                    @RequestBody @Valid CommunityRequest.toCommunityComment request) {
        communityService.submitComment(communityId, memberId, request);
        return ApiResponse.onSuccess(null);
    }


}
