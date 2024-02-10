package com.example.olebackend.repository;

import com.example.olebackend.domain.mapping.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByLessonIdAndMemberId(Long lessonId, Long memberId) ;
}
