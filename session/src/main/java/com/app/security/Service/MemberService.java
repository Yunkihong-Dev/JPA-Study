package com.app.security.Service;

import com.app.security.domain.MemberDTO;
import com.app.security.entity.Member;

import java.util.Optional;

public interface MemberService {
    public void join(MemberDTO memberDTO);
    public Optional<Member> login(String memberId, String memberPassword);

    public default Member toEntity(MemberDTO memberDTO){
        return  Member.builder().memberName(memberDTO.getMemberName())
                .memberId(memberDTO.getMemberId())
                .memberPassword(memberDTO.getMemberPassword())
                .memberEmail(memberDTO.getMemberEmail())
                .memberBirth(memberDTO.getMemberBirth())
                .memberPhoneNumber(memberDTO.getMemberPhoneNumber())
                .memberRole(memberDTO.getMemberRole())
                .build();
    }
}
