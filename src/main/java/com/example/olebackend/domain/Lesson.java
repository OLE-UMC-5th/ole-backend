package com.example.olebackend.domain;

import com.example.olebackend.domain.common.BaseEntity;
import com.example.olebackend.domain.enums.Approved;
import com.example.olebackend.domain.enums.Type;
import com.example.olebackend.domain.enums.Week;
import com.example.olebackend.domain.mapping.LectureTeacher;
import com.example.olebackend.domain.mapping.MemberApply;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title ; // 수업명

    private String description ; // 수업 설명

    @Enumerated(EnumType.STRING)
    private Type lessonType ;      // 수업 방식

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id" )
    private SubCategory subCategory ;

    private String supplies ; // 준비물

    private int price ; // 수강료
    private Boolean priceStatus; // 수강료 유무

    private String refund ; // 환불규정

    private String place ; // 수업 장소

    private LocalDateTime lessonStartDate ; // 강의 시작일

    private LocalDateTime lessonEndDate ; // 강의 종료일

    @Enumerated(EnumType.STRING)
    private Week lectureWeekDay ; // 강의 진행 요일

    private LocalDateTime lessonStartTime ; // 강의 시작 시간

    private LocalDateTime lessonEndTime ; // 강의 종료 시간

    @Enumerated(EnumType.STRING)
    private Approved approved ; // 승인 여부

    private LocalDateTime gatherStartDate ; // 모집 시작일

    private LocalDateTime gatherEndDate ; // 모집 마감일

    private Boolean gatherStatus; // 모집중 여부

    private int limitCount ; // 신청 제한 인원

    private int currentCount ; // 현재 신청 인원

    @ColumnDefault("0")
    private int views ; // 조회수
    public void incrementViews() {
        this.views++;
    }

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @Builder.Default
    private List<NonMember> nonMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @Builder.Default
    private List<LectureTeacher> lectureTeacherList = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberApply> memberApplyList = new ArrayList<>();


    @OneToMany(mappedBy = "lesson", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<File> fileList = new ArrayList<>() ;


}
