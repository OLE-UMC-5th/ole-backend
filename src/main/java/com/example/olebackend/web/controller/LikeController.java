package com.example.olebackend.web.controller;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.converter.LikeConverter;
import com.example.olebackend.converter.SurveyConverter;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.service.LikeService;
import com.example.olebackend.web.dto.LikeResponse;
import com.example.olebackend.web.dto.SurveyResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", allowedHeaders = "*") // 임시 (나중에 필터로 수정되어야함)
@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService ;

    @PostMapping("/lesson/{lessonId}/like")
    @Operation(summary = "찜 하기(위시리스트 추가)")
    public ApiResponse<String> pressLike(
            @PathVariable(name = "lessonId") Long lessonId,
            @RequestParam(name = "memberId") Long memberId){

        likeService.addToWishlist(lessonId, memberId);
        return ApiResponse.onSuccess("isSuccess");
    }

    @DeleteMapping("/lesson/{lessonId}/like")
    @Operation(summary = "찜 취소(위시리스트 삭제)")
    public ApiResponse<String> pressUnLike(
            @PathVariable(name = "lessonId") Long lessonId,
            @RequestParam(name = "memberId") Long memberId){

        likeService.removeFromWishlist(lessonId, memberId);
        return ApiResponse.onSuccess("isSuccess");
    }
    @GetMapping("/member/likes")
    @Operation(summary = "찜 목록 조회")
    public ApiResponse<List<LikeResponse.LikeResponseDto>> likes(
            @RequestParam(name = "memberId") Long memberId){

        List<Lesson> likeList = likeService.likeList(memberId) ;
        List<LikeResponse.LikeResponseDto> result = likeList.stream()
                .map(s -> LikeConverter.toLikeResponse(s))
                .collect(Collectors.toList());
        return ApiResponse.onSuccess(result);
    }
}
