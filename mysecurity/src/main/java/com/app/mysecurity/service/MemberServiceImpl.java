package com.app.mysecurity.service;

import com.app.mysecurity.constant.Role;
import com.app.mysecurity.domain.MemberDTO;
import com.app.mysecurity.domain.OAuthAttributes;
import com.app.mysecurity.entity.Member;
import com.app.mysecurity.provider.MemberDetail;
import com.app.mysecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession session;

//    인증 검사시 인코딩된 비밀번호로 검사하기 때문에
//    회원가입시 반드시 encode()를 사용해주어야 한다.
    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        memberRepository.save(toEntity(memberDTO));
    }

    @Override
    @Transactional
    public void join(MemberDTO memberDTO) {
           memberRepository.findAllByMemberEmail(memberDTO.getMemberEmail()).ifPresent(member ->{
               member.setMemberBirth(memberDTO.getMemberBirth());
           });
    }

    //    spring security에서 DBMS의 회원 정보를 가져올 때 사용될 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findAllByMemberId(username).orElseThrow(() ->
                            new UsernameNotFoundException(username+"not Found"));
        return MemberDetail.builder()
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberRole(member.getMemberRole())
                .build();
    }


    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        로그인 완료 후 정보를 담기 위한 준비
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        로그인된 사용자의 정보 불러오기
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

//      어떤 기업의 OAuth를 사용했는 지를 구분(naver, kakao, mac, facebook, ...)
//        member = save(attribute)

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);

        if(member.getId() == null){
            memberRepository.save(member);
        }else{
            member.update(attributes.getName(), attributes.getMobile(), attributes.getEmail());
        }

        session.setAttribute("member", new MemberDTO(member));

        log.info("================================================");

        log.info(((MemberDTO)session.getAttribute("member")).getMemberEmail());

        log.info("================================================");

//        OAuth를 통해 전달받은 정보를 DTO로 변환하여 session에 저장
//        session에 객체를 저장하기 위해 직렬화 사용
//        다시 가져올 때에는 역직렬화를 통해 원본 객체를 생성
//        세션에 정보 한 개를 담아놓고 매번 SELECT 쿼리를 발생 시키는 것 보다
//        전체 정보를 담는 객체를 세션에 저장하는 것이 성능상 좋다.

        return
                new DefaultOAuth2User(Collections
                        .singleton(
                                new SimpleGrantedAuthority(
                                        member
                                                .getMemberRole()
                                                .getSecurityRole()))
                        , attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    @Transactional
//    변경감지는 트랜잭셔널이 있어야 영속상태 유지가 가능함. 사용하는 곳에서 전부 써야함
    public Member saveOrUpdate(OAuthAttributes authAttributes){
        Optional<Member> memberForSavingOrUpdate = memberRepository
                .findAllByMemberEmail(authAttributes.getEmail());
        Member member = null;
        if(memberForSavingOrUpdate.isPresent()){
            member  = memberForSavingOrUpdate.get();
            memberForSavingOrUpdate.get().setMemberName(authAttributes.getName());
        }
        else{
            member  = authAttributes.toEntity();
        }
        return member;
    }



}
