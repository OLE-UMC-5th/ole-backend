package com.example.olebackend.web.controller;

import com.example.olebackend.apiPayLoad.ApiResponse;
import com.example.olebackend.apiPayLoad.exception.GeneralException;
import com.example.olebackend.converter.LessonConverter;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.repository.LessonRepository;
import com.example.olebackend.repository.specification.LessonSpecification;
import com.example.olebackend.service.LessonService;
import com.example.olebackend.web.dto.LessonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.olebackend.apiPayLoad.code.status.ErrorStatus.KEYWORD_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;
    private final LessonRepository lessonRepository;

    @GetMapping("/{lessonId}")
    @Operation(summary = "교육 상세 조회 API")
    public ApiResponse<LessonResponse.getLessonDetailDTO> getLessonDetail(@PathVariable Long lessonId) {
        Optional<Lesson> lesson = lessonService.getLessonDetail(lessonId);
        return ApiResponse.onSuccess(LessonConverter.toLessonDetailDTO(lesson));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "카테고리별 교육 상세 조회 API")
    @Parameters({
            @Parameter(name = "keyword", description = "검색할 키워드입니다."),
            @Parameter(name = "page", description = "페이지 번호이며, 1페이지부터 시작입니다."),
    })
    public ApiResponse<LessonResponse.getLessonListByCategoryAndSearchDTO> getLessonListByCategory(@PathVariable Long categoryId,
                                                                                                   @RequestParam(required = false, value = "keyword") String keyword,
                                                                                                   @RequestParam(required = false, defaultValue = "1") Integer page) {
        Specification<Lesson> spec = (root, query, criteriaBuilder) -> null;

        // 키워드가 있으면 -> 카테고리별로 조회한 교육 리스트 내에서 keyword 포함한 교육 리턴
        if (keyword != null) {
            spec = spec.and(LessonSpecification.findByKeyword(keyword));
            Page<Lesson> lessonList = lessonService.getLessonListBySpecification(categoryId, spec, page);
            return ApiResponse.onSuccess(LessonConverter.toLessonListByCategoryAndSearchDTO(lessonList));
        }

        // 키워드가 없으면 -> 단순 카테고리별 교육 조회
        else {
            Page<Lesson> lessonList = lessonService.getLessonListByCategory(categoryId, page);
            return ApiResponse.onSuccess(LessonConverter.toLessonListByCategoryAndSearchDTO(lessonList));

        }
    }

    @GetMapping
    @Operation(summary = "조건별 교육 조회 API")
    @Parameters({
            @Parameter(name = "orderBy", description = "교육을 조회할 정렬 기준입니다."),
    })
    public ApiResponse<LessonResponse.getLessonListOrderByCriteriaDTO> getLessonListByOrderCriteria(@RequestParam(required = false, defaultValue = "id", value = "orderBy") String orderCriteria) {

        List<Lesson> lessonList = lessonService.getLessonListByOrderCriteria(orderCriteria);

        return ApiResponse.onSuccess(LessonConverter.toLessonListOrderByCriteriaDTO(lessonList));
    }

    @GetMapping("/search")
    @Operation(summary = "검색 API")
    public ApiResponse<LessonResponse.getLessonListByCategoryAndSearchDTO> getLessonListBySearch(@RequestParam(required = false, value = "keyword") String keyword,
                                                                                                 @RequestParam(required = false, value = "fee") Boolean priceStatus,
                                                                                                 @RequestParam(required = false, value = "status") Boolean gatherStatus,
                                                                                                 @RequestParam(required = false, defaultValue = "1") Integer page) {
        Specification<Lesson> spec = (root, query, criteriaBuilder) -> null;

        if (priceStatus != null)
            spec = spec.and(LessonSpecification.findByPriceStatus(priceStatus));
        if (gatherStatus != null)
            spec = spec.and(LessonSpecification.findByGatherStatus(gatherStatus));
        if (keyword != null)
            spec = spec.and(LessonSpecification.findByKeyword(keyword));
        if (keyword == null && priceStatus == null && gatherStatus == null)
            throw new GeneralException(KEYWORD_NOT_FOUND);

        Page<Lesson> lessonList = lessonService.getLessonListBySearch(spec, page);
        return ApiResponse.onSuccess(LessonConverter.toLessonListByCategoryAndSearchDTO(lessonList));
    }

}

