package com.example.olebackend.apiPayLoad.code.status;

import com.example.olebackend.apiPayLoad.code.BaseErrorCode;
import com.example.olebackend.apiPayLoad.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 교육 관련 응답
    LESSON_NOT_FOUND(HttpStatus.NOT_FOUND, "LESSON4001", "해당하는 교육이 없습니다."),
    ORDER_CRITERIA_INVALID(HttpStatus.BAD_REQUEST, "LESSON4002", "존재하지 않는 정렬 조건입니다."),
    LESSONAPPLY_ALREADY_EXISTS(HttpStatus.NOT_FOUND, "LESSON4001", "이미 신청한 교육입니다."),
    LESSONAPPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "LESSON4001", "신청 정보가 존재하지 않습니다."),

    // 카테고리 관련 응답
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY4001", "해당하는 카테고리가 없습니다."),
    SUBCATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "SUBCATEGORY4001", "해당 카테고리에 대한 세부 카테고리가 없습니다."),

    // 페이징 관련 응답
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "PAGE4001", "존재하지 않는 페이지 입니다."),

    // 검색 관련 응답
    KEYWORD_NOT_FOUND(HttpStatus.BAD_REQUEST, "SEARCH4001", "검색어를 입력하세요"),

    // 소통하러 올래 관련 응답
    COMMUNITY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMUNITY4001", "존재하지 않는 글 입니다."),

    // 올래생활뉴스 관련 응답
    NEWS_NOT_FOUND(HttpStatus.NOT_FOUND, "NEWS4001", "존재하지 않는 글 입니다."),

    //비회원 관련 응답
    NON_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "NONMEMBER4001", "회원 정보가 존재하지 않습니다."),

    //회원 관련 응답
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "회원 정보가 존재하지 않습니다."),

    // 좋아요 관련 응답
    ALREADY_IN_WISHLIST(HttpStatus.NOT_FOUND, "LIKE001", "이미 좋아요 목록에 존재합니다."),
    ALREADY_CANCELED(HttpStatus.NOT_FOUND, "LIKE4002", "이미 취소되었습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}