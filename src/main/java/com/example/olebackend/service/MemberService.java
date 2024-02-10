package com.example.olebackend.service;

import com.example.olebackend.apiPayLoad.exception.GeneralException;
import com.example.olebackend.converter.MemberConverter;
import com.example.olebackend.domain.Lesson;
import com.example.olebackend.domain.Member;
import com.example.olebackend.domain.enums.Role;
import com.example.olebackend.domain.mapping.MemberApply;
import com.example.olebackend.repository.LessonRepository;
import com.example.olebackend.repository.MemberApplyRepository;
import com.example.olebackend.repository.MemberRepository;
import com.example.olebackend.web.dto.MemberLoginRequest;
import com.example.olebackend.web.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.olebackend.apiPayLoad.code.status.ErrorStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final LessonRepository lessonRepository;
    private final MemberApplyRepository memberApplyRepository;

    public void signUp(MemberSignUpRequest memberSignUpDto) throws Exception{

        if(memberRepository.findByEmail(memberSignUpDto.getEmail()).isPresent()){
            throw new Exception("이미 가입된 이메일입니다.");
        }

        if(memberRepository.findByPhoneNum(memberSignUpDto.getPhoneNum()).isPresent()){
            throw new Exception("이미 가입된 전화번호입니다.");
        }

        Member member = Member.builder()
                .email(memberSignUpDto.getEmail())
                .password(memberSignUpDto.getPassword())
                .name(memberSignUpDto.getName())
                .phoneNum(memberSignUpDto.getPhoneNum())
                .birthYear(memberSignUpDto.getBirthYear())
                .address(memberSignUpDto.getAddress())
                .gender(memberSignUpDto.getGender())
                .mailAgree(memberSignUpDto.getMailAgree())
                .smsAgree(memberSignUpDto.getSmsAgree())
                .role(Role.USER)
                .build();

        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
    }
}
