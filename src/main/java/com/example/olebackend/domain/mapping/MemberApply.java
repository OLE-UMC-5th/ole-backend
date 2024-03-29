package com.example.olebackend.domain.mapping;

import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.common.BaseEntity;
import com.example.olebackend.domain.enums.ApplicationStatus;
import com.example.olebackend.domain.enums.Completed;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberApply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Completed attendanceStatus ; //수강 여부

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus ; // 승인 여부
}
