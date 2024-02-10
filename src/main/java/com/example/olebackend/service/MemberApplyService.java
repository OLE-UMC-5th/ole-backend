package com.example.olebackend.service;

import com.example.olebackend.apiPayLoad.exception.GeneralException;
import com.example.olebackend.converter.MemberApplyConverter;
import com.example.olebackend.converter.MemberConverter;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.NonMember;
import com.example.olebackend.domain.mapping.MemberApply;
import com.example.olebackend.repository.LessonRepository;
import com.example.olebackend.repository.MemberApplyRepository;
import com.example.olebackend.repository.MemberRepository;
import com.example.olebackend.web.dto.NonMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.olebackend.apiPayLoad.code.status.ErrorStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberApplyService {

    private final MemberRepository memberRepository;
    private final LessonRepository lessonRepository;
    private final MemberApplyRepository memberApplyRepository;
    public MemberApply applyLesson(Long lessonId, Long memberId) {

        Lesson lesson=lessonRepository.findById(lessonId)
                .orElseThrow(() ->new GeneralException(LESSON_NOT_FOUND));

        Member member=memberRepository.findById(memberId)
                .orElseThrow(() ->new GeneralException(MEMBER_NOT_FOUND));

        if(memberApplyRepository.existsByLessonIdAndMemberId(lessonId, memberId)){
            throw new GeneralException(LESSONAPPLY_ALREADY_EXISTS);
        }

        MemberApply memberApply = MemberApplyConverter.toMemberApply(lesson,member);

        return memberApplyRepository.save(memberApply);
    }

    @Transactional
    public void cancelLesson(Long lessonId,Long memberId) {

        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isEmpty()) {
            throw new GeneralException(LESSON_NOT_FOUND);
        }

        if (!memberApplyRepository.existsByLessonIdAndMemberId(lessonId, memberId)) {
            throw new GeneralException(LESSONAPPLY_NOT_FOUND);
        }

        memberApplyRepository.removeByLessonIdAndMemberId(lessonId, memberId);
    }

    public List<MemberApply> getApplication(Long memberId) {

        if (!memberApplyRepository.existsByMemberId(memberId)) {
            throw new GeneralException(MEMBER_NOT_FOUND);
        }
        List<MemberApply> memberApplyList= memberApplyRepository.findAllByMemberId(memberId);

        return memberApplyList;
    }
}
