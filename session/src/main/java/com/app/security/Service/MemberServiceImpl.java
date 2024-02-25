package com.app.security.Service;

import com.app.security.constant.Role;
import com.app.security.domain.MemberDTO;
import com.app.security.entity.Member;
import com.app.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    @Override
    public void join(MemberDTO memberDTO) {
        memberDTO.setMemberRole(Role.MEMBER);
        memberRepository.save(toEntity(memberDTO));
    }

    @Override
    public Optional<Member> login(String memberId, String memberPassword) {
        return Optional.ofNullable(memberRepository.findByMemberIdAndMemberPassword(memberId,memberPassword));
    }
}
