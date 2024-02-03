package com.example.olebackend.domain;

import com.example.olebackend.domain.common.BaseEntity;
import com.example.olebackend.domain.enums.Gender;
import com.example.olebackend.domain.enums.Role;
import com.example.olebackend.domain.enums.SocialType;
import com.example.olebackend.domain.mapping.Likes;
import com.example.olebackend.domain.mapping.MemberApply;
import com.example.olebackend.repository.MemberRepository;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNum; // 전화번호

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private Long birthYear; // 생년

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender; // 성별

    @Column(nullable = false)
    private boolean mailAgree; // 메일 수신 동의

    @Column(nullable = false)
    private boolean smsAgree; // 문자 수신 동의


    @OneToOne(mappedBy = "member",fetch = FetchType.LAZY)
    private File file ;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Likes> likes = new ArrayList<>() ;

    // ======================================

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 유저 권한설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(updatePassword);
    }


    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberApply> memberApplyList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Community> memberCommunityList = new ArrayList<>();

}
