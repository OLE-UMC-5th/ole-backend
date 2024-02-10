package com.example.olebackend.service;

import com.example.olebackend.apiPayLoad.exception.GeneralException;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.mapping.Likes;
import com.example.olebackend.repository.LessonRepository;
import com.example.olebackend.repository.LikeRepository;
import com.example.olebackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.olebackend.apiPayLoad.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LessonRepository lessonRepository ;
    private final MemberRepository memberRepository ;
    private final LikeRepository likeRepository ;

    @Transactional
    public void addToWishlist(Long lessonId, Long memberId){

        Lesson lesson=lessonRepository.findById(lessonId)
                .orElseThrow(() ->new GeneralException(LESSON_NOT_FOUND));

        Member member=memberRepository.findById(memberId)
                .orElseThrow(() ->new GeneralException(MEMBER_NOT_FOUND));

        if(likeRepository.findByLessonIdAndMemberId(lessonId, memberId).isPresent())
            throw new GeneralException(ALREADY_IN_WISHLIST) ;

        Likes createdLikes = Likes.creaLikes(lesson,member) ;
        likeRepository.save(createdLikes) ;
    }


    @Transactional
    public void removeFromWishlist(Long lessonId, Long memberId){

        Likes likes = likeRepository.findByLessonIdAndMemberId(lessonId, memberId)
                .orElseThrow(()-> new GeneralException(ALREADY_CANCELED));

        if (likes != null) {
            likeRepository.delete(likes); // 멤버 likes 목록에서도 삭제되었는지 확인 필요
        }
    }
    @Transactional
    public List<Lesson> likeList(Long memberId){

        Member member = memberRepository.findById(memberId).get() ;
        List<Likes> likes = member.getLikes();
        List<Lesson> lessons = likes.stream().map(e -> e.getLesson()).collect(Collectors.toList());
        if(!lessons.isEmpty())
            return lessons ;
        else
            throw new GeneralException(LESSON_NOT_FOUND) ;

    }
}
