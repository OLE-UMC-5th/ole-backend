package com.example.olebackend.repository;

import com.example.olebackend.domain.Community;
import com.example.olebackend.domain.News;
import com.example.olebackend.domain.enums.CommunityCategory;
import com.example.olebackend.domain.enums.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findNewsByCategory(NewsCategory category, PageRequest pageRequest);

}
