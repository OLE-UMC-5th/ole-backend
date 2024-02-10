package com.example.olebackend.repository;

import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.NonMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonMemberRepository extends JpaRepository<NonMember, Long> {

    boolean existsByPhoneNum(String phoneNum);

    void removeByPhoneNumAndLessonId(String phoneNum, Long lessonId);

    List<NonMember> findAllByPhoneNum(String phoneNum);

    Boolean existsByLessonIdAndPhoneNum(Long lessonId, String phoneNum);
}
