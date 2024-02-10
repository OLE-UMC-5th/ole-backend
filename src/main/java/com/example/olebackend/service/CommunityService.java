package com.example.olebackend.service;

import com.example.olebackend.apiPayLoad.exception.GeneralException;
import com.example.olebackend.converter.CommunityConverter;
import com.example.olebackend.domain.Community;
import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.enums.CommunityCategory;
import com.example.olebackend.domain.mapping.CommunityComment;
import com.example.olebackend.repository.CommunityCommentRepository;
import com.example.olebackend.repository.CommunityRepository;
import com.example.olebackend.repository.MemberRepository;
import com.example.olebackend.web.dto.CommunityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.olebackend.apiPayLoad.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;
    private final CommunityCommentRepository communityCommentRepository;

    public Page<Community> getCommunityList(CommunityCategory category, Integer page) {

        Page<Community> communityList;
        
        if (category == null) {
            communityList = communityRepository.findAll(PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt")));

        } else {
            communityList = communityRepository.findCommunityByCategory(category, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt")));

        }

        // 전체 페이지 수 이상의 값을 입력했을 때
        if (page > communityList.getTotalPages()) {
            throw new GeneralException(PAGE_NOT_FOUND);
        }
        return communityList;


    }

    @Transactional
    public Optional<Community> getCommunity(Long communityId) {
        Optional<Community> community = communityRepository.findById(communityId);

        // communityId에 해당하는 글이 없는 경우
        if (community.isEmpty()) {
            throw new GeneralException(COMMUNITY_NOT_FOUND);
        }

        community.ifPresent(Community::incrementViews);
        communityRepository.save(community.get());

        return community;
    }

    @Transactional
    public CommunityComment submitComment(Long communityId, Long memberId, CommunityRequest.toCommunityComment request) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new GeneralException(COMMUNITY_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MEMBER_NOT_FOUND));

        CommunityComment communityComments = CommunityConverter.toCommunityComment(community, member, request);
        return communityCommentRepository.save(communityComments);
    }


}
