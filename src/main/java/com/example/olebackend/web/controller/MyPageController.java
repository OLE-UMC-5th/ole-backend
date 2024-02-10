package com.example.olebackend.web.controller;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.converter.MemberApplyConverter;
import com.example.olebackend.domain.mapping.MemberApply;
import com.example.olebackend.service.MemberApplyService;
import com.example.olebackend.web.dto.MemberApplyResponse;
import com.example.olebackend.web.dto.NonMemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MemberApplyService memberApplyService;
    @PostMapping ("/member/lessons")
    @Operation(summary = "신청 내역 조회 API")
    public ApiResponse<MemberApplyResponse.getApplicationListResultDTO> getLessons(
            @RequestParam Long memberId) {
        List<MemberApply> memeberApplyList = memberApplyService.getApplication(memberId);
        return ApiResponse.onSuccess(MemberApplyConverter.toApplicationListDTO(memeberApplyList));
    }

}
