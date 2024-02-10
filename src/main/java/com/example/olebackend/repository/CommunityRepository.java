package com.example.olebackend.repository;


import com.example.olebackend.domain.Community;
import com.example.olebackend.domain.enums.CommunityCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    Page<Community> findCommunityByCategory(CommunityCategory category, PageRequest pageRequest);

}
